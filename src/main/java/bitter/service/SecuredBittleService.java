package bitter.service;

import bitter.data.BittleRepository;
import bitter.domain.Bittle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

@Service
public class SecuredBittleService implements BittleService {
    private BittleRepository bittleRepository;
    @Autowired
    public SecuredBittleService(BittleRepository bittleRepository) {
        this.bittleRepository=bittleRepository;
    }

    @Override
    @Secured({"ROLE_BITTER","ROLE_ADMIN"})
    public Bittle addBittle(Bittle bittle) {
        System.out.println("有权限add bittle");
        return null;
    }

    @Override
    @Secured({"ROLE_BITTER","ROLE_ADMIN"})
    public boolean forceDeleteBittle(Bittle bittle,String bitterName) {
        try{
            if(bitterName.equals(bittle.getBitter().getUsername())){
                bittleRepository.delete(bittle.getId());
                System.out.println("forceDeleteBittle: 删除成功");
                return true;
            } else {
                System.out.println("forceDeleteBittle: 认证失败，操作用户"+bitterName+" bittle作者"+bittle.getBitter().getUsername());
                return false;
            }
        } catch (Exception e) {
            System.out.println("forceDeleteBittle: 删除失败，异常："+e.getMessage());
            return false;
        }

    }
}
