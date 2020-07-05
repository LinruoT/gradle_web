package bitter.data;

import bitter.domain.Bittle;
import org.springframework.cache.annotation.CachePut;

import java.util.List;

/**
 * The interface Bittle repository custom.
 * 更复杂的查询，不能用@Query实现的
 * @author mac
 */
public interface BittleRepositoryCustom {
    /**
     * Find bittles list.
     *
     * @param max   the max
     * @param count the count
     * @return the list
     */
    List<Bittle> findBittles(Long max, int count);//bittleID最大值，需要的bittle个数

    /**
     * Find one with cache bittle.
     *
     * @param id the id
     * @return the bittle
     */
    Bittle findOneWithCache(Long id);

//    Bittle save(Bittle bittle);
}
