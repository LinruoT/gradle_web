package bitter.data;

import bitter.domain.Bitter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

@Repository
@Transactional
public class JpaBitterRepository implements BitterRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    public Bitter save(Bitter bitter) {
        em.persist(bitter);
        return bitter;
    }

    @Override
    public Bitter findByUsername(String username) {
        return (Bitter)em.createQuery("select s from Bitter s where s.username=?1")
                .setParameter(1, username).getSingleResult();
    }

    public Bitter findById(long id) {
        return em.find(Bitter.class,id);
    }
}
