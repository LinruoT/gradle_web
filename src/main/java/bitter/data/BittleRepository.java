package bitter.data;

import bitter.domain.Bitter;
import bitter.domain.Bittle;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
//public interface BittleRepository {
//    List<Bittle> findBittles(Long max, int count);//bittleID最大值，需要的bittle个数
//    Bittle findOne(Long bittleId);
//    int save(Bittle bittle);
//}

//Spring data自动会生成实现类，复杂查询放在 BittleRepositoryCustom，实现放在 同名+Impl
@Transactional
public interface BittleRepository extends JpaRepository<Bittle,Long>,BittleRepositoryCustom{
    @Query("select s from Bittle s order by s.id desc")
    List<Bittle> findAllBittles(); //自定义查询

    List<Bittle> readAllByIdNotNullOrderByIdDesc(); //遵循命名模式的查询，spring data自动实现
    
    @CachePut(value = "bittleCache",key = "'BittleId' +#result.id.toString()")
    Bittle save(Bittle bittle);

}

