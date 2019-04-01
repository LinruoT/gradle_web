package bitter.data;

import bitter.domain.Bitter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

//@Repository
@Transactional
public class HibernateBitterRepository implements BitterRepository{
    private SessionFactory sessionFactory;
    @Autowired
    public HibernateBitterRepository(SessionFactory sessionFactory) {
        System.out.println("bitterRepo收到的sessionFactory: "+sessionFactory);
        this.sessionFactory=sessionFactory;
    }
    private Session currentSession() {
        try {
            System.out.println("currentSession: " + sessionFactory.getCurrentSession());
            return sessionFactory.getCurrentSession();
        }catch (Exception e){
            sessionFactory.openSession();
            return sessionFactory.getCurrentSession();
        }
    }

    public long count() {
        return  findAll().size();
    }

    public List<Bitter> findAll() {
        return (List<Bitter>) currentSession()
                .createCriteria(Bitter.class).list();
    }

    public Bitter findOne(long id) {
        return (Bitter) currentSession().get(Bitter.class, id);

    }

    @Override

    public Bitter save(Bitter bitter) {
        Serializable id = currentSession().save(bitter);
        return new Bitter((Long) id,
                bitter.getUsername(),
                bitter.getPassword(),
                bitter.getFirstName(),
                bitter.getLastName(),
                bitter.getEmail());
    }

    @Override

    public Bitter findByUsername(String username) {
        return (Bitter) currentSession()
                .createCriteria(Bitter.class)
                .add(Restrictions.eq("username", username))
                .list().get(0);
    }
}
