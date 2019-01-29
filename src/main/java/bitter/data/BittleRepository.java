package bitter.data;

import bitter.Bittle;

import java.util.List;
public interface BittleRepository {
    List<Bittle> findBittles(Long max, int count);//bittleID最大值，需要的bittle个数
    Bittle findOne(Long bittleId);
    void save(Bittle bittle);
}
