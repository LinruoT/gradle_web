package bitter.data;

import bitter.domain.Bitter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * custom bitter repo impl
 * <p>
 * todo: add methods to get relationship
 */
public class BitterRepositoryImpl implements BitterRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Bitter> findFollowsByUsername(Bitter currentBitter) {
        return (List<Bitter>) entityManager
                .createQuery("select b from Bitter_Relation r " +
                        "left join Bitter b on r.Follow_id=b.id where r.Bitter_id=:id and r.RelationType=1")
                .setParameter("id", currentBitter.getId())
                .getResultList();
    }

    @Override
    public List<Bitter> findFansByUsername(Bitter currentBitter) {
        return null;
    }

    @Override
    public Boolean follow(Bitter bitter, Bitter currentBitter) {
        return null;
    }

    @Override
    public Boolean unfollow(Bitter bitter, Bitter currentBitter) {
        return null;
    }
}
