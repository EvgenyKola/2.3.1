package web.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaQuery;

import java.util.List;

public class UserDAO {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("user");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static List<User> getAllUsers() {
        EntityManager em = getEntityManager();
        CriteriaQuery<User> criteria = em.getCriteriaBuilder().createQuery(User.class);
        criteria.select(criteria.from(User.class));
        List<User> users = em.createQuery(criteria).getResultList();
        em.close();
        return users;
    }
}
