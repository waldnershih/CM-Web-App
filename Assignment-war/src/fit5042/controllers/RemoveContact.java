package fit5042.controllers;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import fit5042.mbeans.ContactManagedBean;

/**
 *
 * @author Hsuan-Yu Shih
 */
@RequestScoped
@Named("removeContact")
public class RemoveContact {
    @ManagedProperty(value="#{contactManagedBean}") 
    ContactManagedBean contactManagedBean;
    
    private boolean showForm = true;
    private Contact contact;
    ContactApplication app;
    
    public void setContact(Contact contact){
        this.contact = contact;
    }
    
    public Contact getContact(){
        return contact;
    }
    
    public boolean isShowForm() {
        return showForm;
    }

    public RemoveContact() {
        ELContext context
                = FacesContext.getCurrentInstance().getELContext();

        app = (ContactApplication) FacesContext.getCurrentInstance()
                        .getApplication()
                        .getELResolver()
                        .getValue(context, null, "contactApplication");
        
        app.updateContactList();
        
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        contactManagedBean = (ContactManagedBean) FacesContext.getCurrentInstance().getApplication()
        .getELResolver().getValue(elContext, null, "contactManagedBean");
    }

    public void removeContact(int contactId) {
       try
       {
            contactManagedBean.removeContact(contactId);
            
            app.searchAll();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Contact has been deleted succesfully"));     
       }
       catch (Exception ex)
       {
           
       }
       showForm = true;
    }
}

