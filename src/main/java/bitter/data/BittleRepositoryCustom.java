package bitter.data;

import bitter.domain.Bittle;

import java.util.List;

//更复杂的查询，不能用@Query实现的
public interface BittleRepositoryCustom {
    List<Bittle> findBittles(Long max, int count);//bittleID最大值，需要的bittle个数

    Bittle findOneWithCache(Long id);
}
