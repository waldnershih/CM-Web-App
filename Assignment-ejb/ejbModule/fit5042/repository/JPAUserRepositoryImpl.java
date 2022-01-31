package fit5042.repository;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

import fit5042.repository.entities.Contact;
import fit5042.repository.entities.User;
import fit5042.repository.entities.Customer;
import fit5042.repository.entities.IndustryType;

@Stateless
public class JPAUserRepositoryImpl implements UserRepository {

	@PersistenceContext(unitName = "Assignment-ejbPU")
    private EntityManager entityManager;
	
	@Override
	public void addUser(User user) throws Exception {
		List<User> users = entityManager.createNamedQuery(User.GET_ALL_QUERY_NAME).getResultList();
        user.setUserId(users.get(0).getUserId() + 1);
        entityManager.persist(user);		
        entityManager.flush();
	}

	@Override
	public void editUser(User user) throws Exception {
		try {
            entityManager.merge(user);
        } catch (Exception ex) {

        }	
	}

	@Override
	public List<User> getAllUsers() throws Exception {
		return entityManager.createNamedQuery(User.GET_ALL_QUERY_NAME).getResultList();
	}

	@Override
	public void removeUser(int userId) throws Exception {
		User user = this.searchUserById(userId);
    	if (user != null) {
    		entityManager.remove(user);
    	}
	}

	@Override
	public User searchUserById(int userId) throws Exception {
		User user = entityManager.find(User.class, userId);
	    return user;
	}

	@Override
	public User searchUserByName(String userName) throws Exception {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    	CriteriaQuery<User> cQuery = builder.createQuery(User.class);
    	Root<User> c = cQuery.from(User.class);
    	cQuery.select(c).where(builder.equal(c.get("userName"), userName));
        List<User> users = entityManager.createQuery(cQuery).getResultList();
        if (users != null) {
        	return users.get(0);
        }
    	return null;
	}
}
