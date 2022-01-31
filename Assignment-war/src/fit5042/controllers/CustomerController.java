/**
 * 
 */
package fit5042.controllers;
import javax.inject.Named;

import fit5042.mbeans.ContactManagedBean;
import fit5042.repository.entities.Address;

import java.util.ArrayList;
import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

/**
 * @author user
 *
 */
@Named(value = "customerController")
@RequestScoped
public class CustomerController {		
	private int customerId;
	private ArrayList<Contact> contacts;
	private fit5042.repository.entities.Customer customer;
	ContactApplication app;
	
	public CustomerController() {
			customerId = Integer.valueOf(FacesContext.getCurrentInstance()
	                .getExternalContext()
	                .getRequestParameterMap()
	                .get("customerID"));
	        customer = getCustomer();
	        if (customer.getAddress()== null) {
	        	Address address = new Address();
	        	customer.setAddress(address);
	        }
	        
	        //contacts = new ArrayList<>();
	        //updateContactList(customer);	        
	}
	
	public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

	public ArrayList<Contact> getContacts() {
		return contacts;
	}

	private void setContacts(ArrayList<Contact> newContacts) {
		this.contacts = newContacts;
	}

	public void updateContactList(fit5042.repository.entities.Customer customer) {
		if (contacts != null && contacts.size() > 0)
        {
            
        }
        else
        {
            contacts.clear();
            //contacts = app.searchCotnactsByCustomer(customer));           
            
        }
	}
	
	public fit5042.repository.entities.Customer getCustomer() {
        if (customer == null) {
            ELContext context = FacesContext.getCurrentInstance().getELContext();
            CustomerApplication app = (CustomerApplication) FacesContext.getCurrentInstance()
                            .getApplication()
                            .getELResolver()
                            .getValue(context, null, "customerApplication");
            return app.getCustomers().get(--customerId);
        }
        return customer;
    }
}
