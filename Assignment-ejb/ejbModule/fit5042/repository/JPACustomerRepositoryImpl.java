/**
 * 
 */
package fit5042.repository;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import fit5042.repository.CustomerRepository;
import fit5042.repository.entities.Contact;
import fit5042.repository.entities.Customer;
import fit5042.repository.entities.IndustryType;
import fit5042.repository.entities.User;
/**
 * @author user
 *
 */
@Stateless
public class JPACustomerRepositoryImpl implements CustomerRepository {

	@PersistenceContext(unitName = "Assignment-ejbPU")
    private EntityManager entityManager;
	
	@Override
	public void addCustomer(Customer customer) throws Exception {
		List<Customer> customers = entityManager.createNamedQuery(Customer.GET_ALL_QUERY_NAME).getResultList();
        customer.setCustomerId(customers.get(0).getCustomerId() + 1); 
        entityManager.persist(customer);		
        entityManager.flush();
	}

	@Override
	public void editCustomer(Customer customer) throws Exception {
		try {
            entityManager.merge(customer);
        } catch (Exception ex) {

        }		
	}

	@Override
	public List<Customer> getAllCustomers() throws Exception {
		return entityManager.createNamedQuery(Customer.GET_ALL_QUERY_NAME).getResultList();
	}

	@Override
	public void removeCustomer(int customerId) throws Exception {
		Customer customer = this.searchCustomerById(customerId);
    	if (customer != null) {
    		entityManager.remove(customer);
    	}			
	}

	@Override
	public Customer searchCustomerById(int customerId) throws Exception {
		Customer customer = entityManager.find(Customer.class, customerId);
	    return customer;
	}

	@Override
	public Customer searchCustomerByName(String customerName) throws Exception {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    	CriteriaQuery<Customer> cQuery = builder.createQuery(Customer.class);
    	Root<Customer> c = cQuery.from(Customer.class);
    	cQuery.select(c).where(builder.equal(c.get("customerName"), customerName));
        List<Customer> customers = entityManager.createQuery(cQuery).getResultList();
        if (customers != null) {
        	return customers.get(0);
        }
    	return null;
	}

	 @Override
	 public List<Customer> searchCustomersByUser(User user) throws Exception {		
		List<Customer> customers= entityManager.createNamedQuery(Customer.GET_ALL_QUERY_NAME).getResultList();
		List<Customer> outcomes = new ArrayList<>();
		for (Customer customer : customers) {
			if (customer.getUser()!= null) {
				if (customer.getUser().getUserId()== user.getUserId()) {
					outcomes.add(customer);
				}
			}
		}
		return outcomes;
	 }
	
	@Override
	public List<Customer> searchCustomersByIndustryType(IndustryType industryType) throws Exception {
		industryType = entityManager.find(IndustryType.class, industryType.getIndustryTypeId());
		entityManager.refresh(industryType);
		return industryType.getCustomers();
	}

}
