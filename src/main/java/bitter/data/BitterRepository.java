package bitter.data;

import bitter.domain.Bitter;
import org.springframework.data.jpa.repository.JpaRepository;

//用户库接口
//public interface BitterRepository {
//    Bitter save(Bitter bitter);
//    Bitter findByUsername(String username);
//}

//Spring data自动会生成实现类
public interface BitterRepository extends JpaRepository<Bitter,Long> {
    Bitter findByUsername(String username);
    int countByUsernameNotNull(String username);
}