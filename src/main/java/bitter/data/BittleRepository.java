package bitter.data;

import bitter.domain.Bitter;
import bitter.domain.Bittle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
//public interface BittleRepository {
//    List<Bittle> findBittles(Long max, int count);//bittleID最大值，需要的bittle个数
//    Bittle findOne(Long bittleId);
//    int save(Bittle bittle);
//}

//Spring data自动会生成实现类
public interface BittleRepository extends JpaRepository<Bittle,Long> {
//    List<Bittle> findBittles(Long max, int count);//bittleID最大值，需要的bittle个数

    List<Bittle> readAllByIdNotNullOrderByIdDesc();
}
