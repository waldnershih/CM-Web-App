/**
 * 
 */
package fit5042.controllers;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import fit5042.mbeans.ContactManagedBean;

/**
 * @author user
 *
 */
@RequestScoped
@Named("addContact")
public class AddContact {
	@ManagedProperty(value="#{contactManagedBean}") 
    ContactManagedBean contactManagedBean;
	
	private boolean showForm = true;
	
	private Contact contact;
	
	ContactApplication app;

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public boolean isShowForm() {
		return showForm;
	}

	public void setShowForm(boolean showForm) {
		this.showForm = showForm;
	}
	
	public AddContact() 
    {
        ELContext context
                = FacesContext.getCurrentInstance().getELContext();

        app  = (ContactApplication) FacesContext.getCurrentInstance()
                        .getApplication()
                        .getELResolver()
                        .getValue(context, null, "contactApplication");
        
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        contactManagedBean = (ContactManagedBean) FacesContext.getCurrentInstance().getApplication()
        .getELResolver().getValue(elContext, null, "contactManagedBean");
    }
	
	public void addContact(Contact localContact) {
       try
       {
            contactManagedBean.addContactToDB(localContact);

            app.searchAll();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Contact has been added succesfully"));
       }
       catch (Exception ex)
       {
           
       }
        showForm = true;
    }
}
