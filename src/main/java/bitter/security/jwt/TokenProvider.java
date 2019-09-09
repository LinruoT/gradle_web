package bitter.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

@Component
public class TokenProvider implements InitializingBean {
    private static final String AUTHORITIES_KEY = "auth";

    private Key key;
    private long tokenValidityInMilliseconds;
    private long tokenValidityInMillisecondsForRememberMe;

    public TokenProvider(){}

    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytes;
        String secret = "NDI3M2RiYzUzZmRhN2UxYjkyMDIxN2FhMjk4Y2IzZTE4NTY0OTE1MDRmZDUwYmNiNzVlMTJiYTFjN2VmMzRiYWMyN2QwMjY1MjA2NGEwY2MwMzFhOGQ3YzI3ZTE4NDZiYjI2YmM0NjhiYjNjODdiYmY3NTExMjExYTdkYTc4ZTU=";
        keyBytes = Decoders.BASE64.decode(secret);
        key = Keys.hmacShaKeyFor(keyBytes);
        tokenValidityInMilliseconds = 86400*1000;
        tokenValidityInMillisecondsForRememberMe = 259200000;
    }


    public String createToken(Authentication authentication, boolean rememberMe) {
        //getAuthorities()转换为string
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        Date validity;
        if (rememberMe) {
            validity = new Date(now + this.tokenValidityInMillisecondsForRememberMe);
        } else {
            validity = new Date(now + this.tokenValidityInMilliseconds);
        }
        //create jwt
        return Jwts.builder()
                .setSubject(authentication.getName()) //存username
                .claim(AUTHORITIES_KEY, authorities) //存authorities string
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
    }


    public Authentication getAuthentication(String token) {
        Claims claims =
                Jwts.parser().setSigningKey(key).parseClaimsJws(token)
                .getBody();
        //获取authorities string转换为getAuthorities()
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
        //获取username
        //组成principal
        User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }


    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(authToken);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            System.out.println("Invalid JWT signature.");
        } catch (ExpiredJwtException e) {
            System.out.println("Expired JWT token.");
        } catch (UnsupportedJwtException e) {
            System.out.println("Unsupported JWT token.");
        } catch (IllegalArgumentException e) {
            System.out.println("JWT token compact of handler are invalid.");
        }
        return false;
    }
}
