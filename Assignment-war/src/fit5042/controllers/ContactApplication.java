/**
 * 
 */
package fit5042.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import javax.enterprise.context.ApplicationScoped;
import fit5042.mbeans.ContactManagedBean;
import javax.inject.Named;
import fit5042.repository.entities.Contact;
import javax.el.ELContext;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

/**
 * @author user
 *
 */

@Named(value = "contactApplication")
@ApplicationScoped
public class ContactApplication {
	
	@ManagedProperty(value="#{contactManagedBean}") 
    ContactManagedBean contactManagedBean;
	
	private final ArrayList<String> GENDERS = new ArrayList<String>(Arrays.asList("Male", "Female", "Others"));
	
	private ArrayList<Contact> contacts;
	
	private boolean showForm = true;

	public ArrayList<String> getGENDERS() {
		return GENDERS;
	}

	public boolean isShowForm() {
        return showForm;
    }
	
	public ContactApplication() throws Exception {       
        contacts = new ArrayList<>();
        
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        contactManagedBean = (ContactManagedBean) FacesContext.getCurrentInstance().getApplication()
        .getELResolver().getValue(elContext, null, "contactManagedBean");
        
        updateContactList();
    }
	
	public ArrayList<Contact> getContacts() {
        return contacts;
    }
	
	private void setContacts(ArrayList<Contact> newContacts) {
        this.contacts = newContacts;
    }
	
	public void updateContactList() {
        if (contacts != null && contacts.size() > 0)
        {
            
        }
        else
        {
            contacts.clear();
            if (contactManagedBean.getAllContacts()!=null) {
	            for (fit5042.repository.entities.Contact contact : contactManagedBean.getAllContacts())
	            {
	                contacts.add(contact);
	            }
	
	            setContacts(contacts);
            }
        }
    }

	public void searchContactById(int contactId) {
        contacts.clear();
        
        contacts.add(contactManagedBean.searchContactById(contactId));
    }
///////////////////////////////////////////////////////////////////////////////////////////////	
	public ArrayList<Contact> searchContactsByCustomer(String customerName) {
		contacts.clear();
		for (fit5042.repository.entities.Contact contact : contactManagedBean.searchContactsByCustomer(customerName))
        {
        	contacts.add(contact);
        }
        return contacts;
	}
//////////////////////////////////////////////////////////////////////////////////////////////	
	public void searchContactByName(String contactName) {
		contacts.clear();
        
        for (fit5042.repository.entities.Contact contact : contactManagedBean.searchContactsByName(contactName))
        {
        	contacts.add(contact);
        }
        setContacts(contacts);
    }	
	
	public void searchAll() {
        contacts.clear();
        
        for (fit5042.repository.entities.Contact contact : contactManagedBean.getAllContacts())
        {
            contacts.add(contact);
        }
        
        setContacts(contacts);
    }
}
