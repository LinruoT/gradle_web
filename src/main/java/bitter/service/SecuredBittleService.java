package bitter.service;

import bitter.domain.Bittle;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

@Service
public class SecuredBittleService implements BittleService {
    @Override
    @Secured({"ROLE_BITTER","ROLE_ADMIN"})
    public Bittle addBittle(Bittle bittle) {
        System.out.println("有权限add bittle");
        return null;
    }
}
