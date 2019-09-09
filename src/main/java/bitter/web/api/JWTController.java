package bitter.web.api;

import bitter.security.jwt.JWTFilter;
import bitter.security.jwt.TokenProvider;
import bitter.web.api.VM.LoginVM;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Controller to authenticate users.
 */
@RestController
@RequestMapping("/api")
public class JWTController {
    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public JWTController(TokenProvider tokenProvider,AuthenticationManager authenticationManager) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @RequestMapping(value = "/auth",method = RequestMethod.POST)
    public ResponseEntity<JWTToken> auth(@Valid @RequestBody LoginVM loginVM) {
        UsernamePasswordAuthenticationToken authToken=
                new UsernamePasswordAuthenticationToken(loginVM.getUsername(),loginVM.getPassword());
        System.out.println("正在认证："+loginVM.getUsername());
        Authentication authentication = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        boolean rememberMe = (loginVM.isRememberMe() == null) ? false : loginVM.isRememberMe();
        String jwt = tokenProvider.createToken(authentication,rememberMe);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER,"Bearer "+ jwt);
        return new ResponseEntity<>(new JWTToken(jwt),httpHeaders, HttpStatus.OK);

    }





    static class JWTToken {
        private String idToken;
        JWTToken(String idToken) {
            this.idToken = idToken;
        }
        @JsonProperty("id_token")
        public String getIdToken() {
            return idToken;
        }

        public void setIdToken(String idToken) {
            this.idToken = idToken;
        }
    }
}
