package bitter.data;

import bitter.domain.Bittle;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

//对repoCustom的实现类，和only-jpa的方案一样
// spring data在生成repo实现的时候会查找到相同接口后缀Impl的类
public class BittleRepositoryImpl implements BittleRepositoryCustom{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Bittle> findBittles(Long max, int count) {
        return (List<Bittle>) entityManager.createQuery("select s from Bittle s order by s.id desc")
                .setMaxResults(count)
                .getResultList();
    }
}
