/**
 * 
 */
package fit5042.controllers;
import javax.inject.Named;
import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

/**
 * @author user
 *
 */
@Named(value = "contactController")
@RequestScoped
public class ContactController {
	private int contactId;
	private fit5042.repository.entities.Contact contact;
	
	public ContactController() {
			contactId = Integer.valueOf(FacesContext.getCurrentInstance()
	                .getExternalContext()
	                .getRequestParameterMap()
	                .get("contactID"));
	        contact = getContact();
	}
	
	public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }
    
	public fit5042.repository.entities.Contact getContact() {
        if (contact == null) {
            ELContext context = FacesContext.getCurrentInstance().getELContext();
            ContactApplication app = (ContactApplication) FacesContext.getCurrentInstance()
                            .getApplication()
                            .getELResolver()
                            .getValue(context, null, "contactApplication");
            return app.getContacts().get(--contactId);
        }
        return contact;
    }
}
