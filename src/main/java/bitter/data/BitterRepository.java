package bitter.data;

import bitter.domain.Bitter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//用户库接口
//public interface BitterRepository {
//    Bitter save(Bitter bitter);
//    Bitter findByUsername(String username);
//}

//Spring data自动会生成实现类
public interface BitterRepository extends JpaRepository<Bitter,Long>, BitterRepositoryCustom {

    Bitter findByUsername(String username);
    List<Bitter> findByUsernameOrFirstNameOrLastNameLike(String username, String firstName,String lastName);
}