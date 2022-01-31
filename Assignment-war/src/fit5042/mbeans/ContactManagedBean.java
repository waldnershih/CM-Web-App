/**
 * 
 */
package fit5042.mbeans;

import fit5042.repository.ContactRepository;
import fit5042.repository.CustomerRepository;
import fit5042.repository.UserRepository;
import fit5042.repository.entities.Contact;
import fit5042.repository.entities.Customer;
import fit5042.repository.entities.User;

import javax.ejb.EJB;

import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 * @author user
 *
 */
@ManagedBean(name = "contactManagedBean")
@SessionScoped

public class ContactManagedBean implements Serializable{		
	@EJB
    ContactRepository contactRepository;
	
	@EJB
    CustomerRepository customerRepository;
	
	@EJB
    UserRepository userRepository;
	
	private User user;

	
	public User getUser() {
		this.initializeUser();
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
     * Creates a new instance of ContactManagedBean
     */
    public ContactManagedBean() {
    	this.initializeUser();
    }
       
    public void addContact(Contact contact) {
        try {
        	contactRepository.addContact(contact);
        } catch (Exception ex) {
            Logger.getLogger(ContactManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editContact(Contact contact, String customerString) {
        try {  
        	if (customerString != null) {
        		Customer customer = this.findCustomerByLocalCustomer(customerString);
                contact.setCustomer(customer);
        	}
        	User customerUser = contact.getCustomer().getUser();
        	contact.setUser(customerUser);
            contactRepository.editContact(contact);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Contact has been updated succesfully"));
        } catch (Exception ex) {
            Logger.getLogger(ContactManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Contact> getAllContacts() {
        try {
        	if (user.getUserType().equals("admin")) {
        		return contactRepository.getAllContacts();
        	}
        	return searchContactsByUser(user.getUserName());
        } catch (Exception ex) {
            Logger.getLogger(ContactManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void removeContact(int contactId) {
        try {
            contactRepository.removeContact(contactId);
        } catch (Exception ex) {
            Logger.getLogger(ContactManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Contact searchContactById(int contactId) {
        try {
        	Contact contact = contactRepository.searchContactById(contactId);
            if (contact.getUser()!=null||user!=null) {
	        	if (user.getUserType().equals("admin") || contact.getUser().getUserId()== user.getUserId()) {
	        		return contact;
	            }
        	}
        } catch (Exception ex) {
            Logger.getLogger(ContactManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public List<Contact> searchContactsByCustomer(String customerName) {
        try {
        	Customer customer = findCustomerByLocalCustomer(customerName);
            return contactRepository.searchContactsByCustomer(customer);
        } catch (Exception ex) {
            Logger.getLogger(ContactManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
	public List<Contact> searchContactsByUser(String userName) {
		try {
			User user = this.findUserByLocalUser(userName);
	        return contactRepository.searchContactsByUser(user);
	    } catch (Exception ex) {
	        Logger.getLogger(UserManagedBean.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    
	 return null;
	}
    
    public List<Contact> searchContactsByName(String contactName) {
        try {
        	List<Contact> contacts = contactRepository.searchContactsByName(contactName);
        	List<Contact> outcomes = new ArrayList<>();
        	for (Contact contact : contacts) {
        		if (contact.getUser()!=null || user!=null) {
        			if (user.getUserType().equals("admin")||contact.getUser().getUserId()==user.getUserId()) {
        				outcomes.add(contact);
        			}
        		}
        	}
        	return outcomes;
        } catch (Exception ex) {
            Logger.getLogger(ContactManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    } 
    
    public void addContactToDB(fit5042.controllers.Contact localContact) {
        Contact contact = convertContactToEntity(localContact);
        try {
            contactRepository.addContact(contact);
        } catch (Exception ex) {
            Logger.getLogger(ContactManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private Contact convertContactToEntity(fit5042.controllers.Contact localContact){
        Contact contact = new Contact();
        contact.setContactId(localContact.getContactId());
        contact.setContactEmail(localContact.getContactEmail());
        contact.setContactGender(localContact.getContactGender());
        contact.setContactName(localContact.getContactName());
        contact.setContactPhoneNumber(localContact.getContactPhoneNumber());
        
        Customer customer = this.findCustomerByLocalCustomer(localContact.getCustomer());
        contact.setCustomer(customer);
        contact.setUser(customer.getUser());
        return contact;
    }
    
    private Customer findCustomerByLocalCustomer(String localCustomer) {
    	if (localCustomer.length()==0) {
    		return null;
    	}
        try {
        	Customer customer = customerRepository.searchCustomerByName(localCustomer);
        	return customer;
        } catch (Exception ex) {
            Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private User findUserByLocalUser(String localUser) {
    	if (localUser.length()==0) {
    		return null;
    	}
        try {
        	User user = userRepository.searchUserByName(localUser);
        	return user;
        } catch (Exception ex) {
            Logger.getLogger(UserManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    ////////////////////////////////////////////////////////////////////////////////////////
    private void initializeUser() {
    	Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        try {
        	if (principal != null) {
        		user = userRepository.searchUserByName(principal.getName());
	        }
        } catch (Exception ex) {
            Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
}
