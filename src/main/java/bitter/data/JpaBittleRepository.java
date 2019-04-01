package bitter.data;

import bitter.domain.Bittle;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class JpaBittleRepository implements BittleRepository{
    @PersistenceContext
    private EntityManager entityManager;

    public long count() {
        return findAll().size();
    }

    public List<Bittle> findRecent() {
        return findRecent(10);
    }
    public List<Bittle> findRecent(int count) {
        return (List<Bittle>) entityManager.createQuery("select s from Bittle s order by s.id desc")
                .setMaxResults(count)
                .getResultList();
    }
    @Override
    public List<Bittle> findBittles(Long max, int count) {
        return (List<Bittle>) entityManager.createQuery("select s from Bittle s order by s.id desc")
                .setMaxResults(count)
                .getResultList();
    }

    @Override
    public Bittle findOne(Long id) {
        return entityManager.find(Bittle.class, id);
    }
    public void delete(long id) {
        entityManager.remove(findOne(id));
    }
    public List<Bittle> findAll() {
        return (List<Bittle>) entityManager.createQuery("select s from Bittle s").getResultList();
    }
    @Override
    public int save(Bittle bittle) {
        System.out.println("saving bittle: "+bittle.getMessage());
        entityManager.persist(bittle);
        return 0;
    }
}
