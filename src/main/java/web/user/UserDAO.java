package web.user;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public List<User> getAllUsers() {
        CriteriaQuery<User> criteria = entityManager.getCriteriaBuilder().createQuery(User.class);
        criteria.select(criteria.from(User.class));
        return entityManager.createQuery(criteria).getResultList();
    }
}
