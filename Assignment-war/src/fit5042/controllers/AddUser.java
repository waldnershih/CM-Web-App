/**
 * 
 */
package fit5042.controllers;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import fit5042.mbeans.UserManagedBean;

/**
 * @author user
 *
 */
@RequestScoped
@Named("addUser")
public class AddUser {
	@ManagedProperty(value="#{userManagedBean}") 
    UserManagedBean userManagedBean;
	
	private boolean showForm = true;
	
	private User user;
	
	UserApplication app;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isShowForm() {
		return showForm;
	}

	public void setShowForm(boolean showForm) {
		this.showForm = showForm;
	}
	
	public AddUser() 
    {
        ELContext context
                = FacesContext.getCurrentInstance().getELContext();

        app  = (UserApplication) FacesContext.getCurrentInstance()
                        .getApplication()
                        .getELResolver()
                        .getValue(context, null, "userApplication");
        
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        userManagedBean = (UserManagedBean) FacesContext.getCurrentInstance().getApplication()
        .getELResolver().getValue(elContext, null, "userManagedBean");
    }
	
	public void addUser(User localUser) {
       try
       {
            userManagedBean.addUser(localUser);

            app.searchAll();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User has been added succesfully"));
       }
       catch (Exception ex)
       {
           
       }
        showForm = true;
    }
}
