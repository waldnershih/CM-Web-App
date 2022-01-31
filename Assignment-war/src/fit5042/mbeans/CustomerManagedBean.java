/**
 * 
 */
package fit5042.mbeans;

import fit5042.repository.ContactRepository;
import fit5042.repository.CustomerRepository;
import fit5042.repository.IndustryTypeRepository;
import fit5042.repository.UserRepository;
import fit5042.repository.entities.Customer;
import fit5042.repository.entities.IndustryType;
import fit5042.repository.entities.User;
import fit5042.repository.entities.Address;
import fit5042.repository.entities.Contact;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 * @author user
 *
 */
@ManagedBean(name = "customerManagedBean")
@SessionScoped

public class CustomerManagedBean implements Serializable{
	
	@EJB
	ContactRepository contactRepository;
	
	@EJB
    CustomerRepository customerRepository;
	
	@EJB
    IndustryTypeRepository industryTypeRepository;
	
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
     * Creates a new instance of CustomerManagedBean
     */
    public CustomerManagedBean() {
    	this.initializeUser();
    }
    
    public void addCustomer(Customer customer) {
        try {
             customerRepository.addCustomer(customer);
        } catch (Exception ex) {
            Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editCustomer(Customer customer, String industryTypeName, String userName) {
        try {           
        	if (industryTypeName.length()!= 0) {
        		IndustryType industryType = this.findIndustryTypeByLocalIndustryType(industryTypeName);
                customer.setIndustryType(industryType);
        	}
        	if (userName.length()!= 0) {
        		User user = this.findUserByLocalUser(userName);
                customer.setUser(user);
        	}
            customerRepository.editCustomer(customer);
            updateContactUser(customer.getCustomerId());
            
        } catch (Exception ex) {
            Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Customer> getAllCustomers() {
        try {
        	if (user.getUserType().equals("admin")) {
        		return customerRepository.getAllCustomers();
        	}
        	return searchCustomersByUser(user.getUserName());
        } catch (Exception ex) {
            Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void removeCustomer(int customerId) {
        try {
            customerRepository.removeCustomer(customerId);
        } catch (Exception ex) {
            Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Customer searchCustomerById(int customerId) {
        try {
            Customer customer = customerRepository.searchCustomerById(customerId);
            if (customer.getUser()!=null||user!=null) {
	        	if (user.getUserType().equals("admin") || customer.getUser().getUserId()== user.getUserId()) {
	        		return customer;
	            }
        	}
        } catch (Exception ex) {
            Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }    

	public List<Customer> searchCustomersByUser(String userString) {
		try {
			User user = this.findUserByLocalUser(userString);
        	return customerRepository.searchCustomersByUser(user);
        } catch (Exception ex) {
            Logger.getLogger(UserManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
 
     return null;
	}
    
    public Customer searchCustomerByName(String customerName) {
        try {
        	Customer customer = customerRepository.searchCustomerByName(customerName);
        	if (customer.getUser()!=null || user!=null) {
	        	if (user.getUserType().equals("admin") || customer.getUser().getUserId()== user.getUserId()) {
	        		return customer;
	            }
        	}
        } catch (Exception ex) {
            Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    } 
    
    public List<Customer> searchCustomersByIndustryType(IndustryType industryType) {
		try {
            return customerRepository.searchCustomersByIndustryType(industryType);
        } catch (Exception ex) {
            Logger.getLogger(UserManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     return null;
	}
    
    public void addCustomer(fit5042.controllers.Customer localCustomer) {
        Customer customer = convertCustomerToEntity(localCustomer);

        try {
            customerRepository.addCustomer(customer);
        } catch (Exception ex) {
            Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private Customer convertCustomerToEntity(fit5042.controllers.Customer localCustomer){
        Customer customer = new Customer();
        String streetNumber = localCustomer.getStreetNumber();
        String streetAddress = localCustomer.getStreetAddress();
        String suburb = localCustomer.getSuburb();
        String postcode = localCustomer.getPostcode();
        String state = localCustomer.getState();
        Address address = new Address(streetNumber, streetAddress, suburb, postcode, state);
        customer.setAddress(address);
        customer.setCustomerBusinessPartner(localCustomer.getCustomerBusinessPartner());
        customer.setCustomerEmail(localCustomer.getCustomerEmail());
        customer.setCustomerId(localCustomer.getCustomerId());
        customer.setCustomerName(localCustomer.getCustomerName());
        customer.setCustomerNationality(localCustomer.getCustomerNationality());
        customer.setCustomerPhoneNumber(localCustomer.getCustomerPhoneNumber());
        
        IndustryType industryType = this.findIndustryTypeByLocalIndustryType(localCustomer.getIndustryType());
        customer.setIndustryType(industryType);

        User user = this.findUserByLocalUser(this.user.getUserName());
        customer.setUser(user);
        return customer;
    }
    
    private IndustryType findIndustryTypeByLocalIndustryType(String localIndustryType) {
    	if (localIndustryType.length()==0) {
    		return null;
    	}
        try {
        	IndustryType industryType = industryTypeRepository.searchIndustryTypeByName(localIndustryType);
        	return industryType;
        } catch (Exception ex) {
            Logger.getLogger(IndustryTypeManagedBean.class.getName()).log(Level.SEVERE, null, ex);
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
    //////////////////////////////////////////////////////////////////////////////////
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
    
    public void updateContactUser(int customerId) {
    	try {
    		Customer customer = this.searchCustomerById(customerId);
    		List<Contact> contacts = contactRepository.searchContactsByCustomer(customer);
    		User user = customer.getUser();
        	for (Contact contact : contacts) {
            	contact.setUser(user);
                contactRepository.editContact(contact);
        	}
        	if (contacts.size()!=0) {
        		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Customer has been updated succesfully"));
        	}else {
        		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Contact updated has been failed"));
        	}
    	} catch (Exception ex) {
            Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
