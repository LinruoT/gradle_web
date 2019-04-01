package bitter.data;

import bitter.domain.Bittle;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

//@Repository
@Transactional
public class HibernateBittleRepository implements BittleRepository {
    private SessionFactory sessionFactory;
    @Autowired
    public HibernateBittleRepository(SessionFactory sessionFactory) {
        System.out.println("bittleRepo收到的sessionFactory: "+sessionFactory);
        this.sessionFactory = sessionFactory;
    }
    private Session currentSession() {
        try {
            System.out.println("cur!~!!!!!!!!!!!!!!!!!!");
            System.out.println("currentSession: " + sessionFactory.getCurrentSession());
            return sessionFactory.getCurrentSession();
        }catch (Exception e){
            sessionFactory.openSession();
            return sessionFactory.getCurrentSession();
        }
    }

    public long count() {
        return findAll().size();
    }

    public void delete(long id) {
        currentSession().delete(findOne(id));
    }

    public List<Bittle> findAll() {
        return (List<Bittle>) bittleCriteria().list();
    }
    @Override
    public List<Bittle> findBittles(Long max, int count) {
        return (List<Bittle>) bittleCriteria()
                .setMaxResults(count)
                .list();
    }

    @Override
    public Bittle findOne(Long id) {
        return (Bittle) currentSession().get(Bittle.class,id);
    }

    @Override
    public int save(Bittle bittle) {
        Serializable id = currentSession().save(bittle);

        return 0;
    }

    public Criteria bittleCriteria() {
        return currentSession()
                .createCriteria(Bittle.class)
                .addOrder(Order.desc("id"));
    }
}
