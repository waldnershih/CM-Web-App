package fit5042.controllers;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Hsuan-Yu Shih
 */
@RequestScoped
@Named("searchContact")
public class SearchContact {
    private Contact contact;
    
    ContactApplication app;
    
    private int searchByInt;
    private String searchByCustomerString;
    private String searchByString;
    private String searchByName;
    
    public ContactApplication getApp() {
        return app;
    }
    
    public Contact getContact(){
        return contact;
    }
    
    public int getSearchByInt() {
        return searchByInt;
    }

    public String getSearchByCustomerString() {
		return searchByCustomerString;
	}

	public String getSearchByName() {
        return searchByName;
    }

    public String getSearchByString() {
        return searchByString;
    }
    
    public SearchContact() {
        ELContext context
                = FacesContext.getCurrentInstance().getELContext();

        app = (ContactApplication) FacesContext.getCurrentInstance()
                        .getApplication()
                        .getELResolver()
                        .getValue(context, null, "contactApplication");
        
        app.updateContactList();
    }

    public void searchContactById(int contactId) {
       try
       {
            app.searchContactById(contactId);
       }
       catch (Exception ex)
       {
           
       }
    }
    
    public void searchContactByName(String contactName) {
       try
       {
            app.searchContactByName(contactName);
       }
       catch (Exception ex)
       {
           
       }
    }
    
    public void searchContactsByCustomer(String customerName) {
        try
        {
             app.searchContactsByCustomer(customerName);
        }
        catch (Exception ex)
        {
            
        }
     }
   
    public void searchAll() {
       try
       {
             app.searchAll();
       }
       catch (Exception ex)
       {
           
       }
    }
    
    public void setApp(ContactApplication app) {
        this.app = app;
    }
 
    public void setContact(Contact contact){
        this.contact = contact;
    }
    
    
    public void setSearchByInt(int searchByInt) {
        this.searchByInt = searchByInt;
    }

	public void setSearchByCustomerString(String searchByCustomerString) {
		this.searchByCustomerString = searchByCustomerString;
	}
    
    public void setSearchByName(String searchByName) {
        this.searchByName = searchByName;
    }

	public void setSearchByString(String searchByString) {
		this.searchByString = searchByString;
	}

}

