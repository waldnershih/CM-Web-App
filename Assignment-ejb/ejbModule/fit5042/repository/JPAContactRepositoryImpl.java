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

import fit5042.repository.ContactRepository;
import fit5042.repository.entities.Contact;
import fit5042.repository.entities.Customer;
import fit5042.repository.entities.User;

/**
 * @author Hsuan-Yu Shih
 *
 */
@Stateless
public class JPAContactRepositoryImpl implements ContactRepository {

	@PersistenceContext(unitName = "Assignment-ejbPU")
    private EntityManager entityManager;

	@Override
	public void addContact(Contact contact) throws Exception {
		List<Contact> contacts = entityManager.createNamedQuery(Contact.GET_ALL_QUERY_NAME).getResultList();
        contact.setContactId(contacts.get(0).getContactId() + 1);
		entityManager.persist(contact);		
        entityManager.flush();
	}

	@Override
	public void editContact(Contact contact) throws Exception {
		try {
            entityManager.merge(contact);
        } catch (Exception ex) {

        }		
	}

	@Override
	public List<Contact> getAllContacts() throws Exception {
		List<Contact> contacts= entityManager.createNamedQuery(Contact.GET_ALL_QUERY_NAME).getResultList();
		return contacts;
	}
	
	@Override
	public void removeContact(int contactId) throws Exception {
		Contact contact = this.searchContactById(contactId);
    	if (contact != null) {
    		entityManager.remove(contact);
    	}		
	}

	@Override
	public Contact searchContactById(int contactId) throws Exception {
		Contact contact = entityManager.find(Contact.class, contactId);
	    return contact;
	}

	/**
	@Override
	public List<Contact> searchContactsByCustomer(Customer customer) throws Exception {
		customer = entityManager.find(Customer.class, customer.getCustomerId());
        entityManager.refresh(customer);
        return customer.getContacts();
	}
	*/
	
	@Override
	public List<Contact> searchContactsByCustomer(Customer customer) throws Exception {
		List<Contact> contacts= entityManager.createNamedQuery(Contact.GET_ALL_QUERY_NAME).getResultList();
		List<Contact> outcomes = new ArrayList<>();
		for (Contact contact : contacts) {
			if (contact.getCustomer()!= null) {
				if (contact.getCustomer().getCustomerId()== customer.getCustomerId()) {
					outcomes.add(contact);
				}
			}
		}
		return outcomes;
	}	
	
	@Override
	public List<Contact> searchContactsByName(String contactName) throws Exception {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    	CriteriaQuery<Contact> cQuery = builder.createQuery(Contact.class);
    	Root<Contact> c = cQuery.from(Contact.class);
    	cQuery.select(c).where(builder.equal(c.get("contactName"), contactName));
        List<Contact> contacts = entityManager.createQuery(cQuery).getResultList();
    	return contacts;
	}
	

	@Override
	public List<Contact> searchContactsByUser(User user) throws Exception {
		List<Contact> contacts= entityManager.createNamedQuery(Contact.GET_ALL_QUERY_NAME).getResultList();
		List<Contact> outcomes = new ArrayList<>();
		for (Contact contact : contacts) {
			if (contact.getUser()!= null) {
				if (contact.getUser().getUserId()== user.getUserId()) {
					outcomes.add(contact);
				}
			}
		}
		return outcomes;
	}

}
