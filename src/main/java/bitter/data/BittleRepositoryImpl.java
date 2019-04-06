package bitter.data;

import bitter.domain.Bittle;
import org.hibernate.annotations.Cache;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

//对repoCustom的实现类，和only-jpa的方案一样
// spring data在生成repo实现的时候会查找到相同接口后缀Impl的类
public class BittleRepositoryImpl implements BittleRepositoryCustom{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
//    @Cacheable(value = "recentBittles",key = "'oneMinuteBittles'") //List<E> 需要用GenericJackson2JsonRedisSerializer，spring data 1.6.0以上
    public List<Bittle> findBittles(Long max, int count) {
        return (List<Bittle>) entityManager.createQuery("select s from Bittle s order by s.id desc")
                .setMaxResults(count)
                .getResultList();
    }

    @Override
    @Cacheable(value = "bittleCache",key = "'BittleId' +#id.toString()")
    public Bittle findOneWithCache(Long id) {
        return entityManager.find(Bittle.class,id);
    }
}
