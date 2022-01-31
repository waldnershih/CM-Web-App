package fit5042.controllers;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import fit5042.mbeans.UserManagedBean;

/**
 *
 * @author Hsuan-Yu Shih
 */
@RequestScoped
@Named("removeUser")
public class RemoveUser {
    @ManagedProperty(value="#{userManagedBean}") 
    UserManagedBean userManagedBean;
    
    private boolean showForm = true;
    private User user;
    UserApplication app;
    
    public void setUser(User user){
        this.user = user;
    }
    
    public User getUser(){
        return user;
    }
    
    public boolean isShowForm() {
        return showForm;
    }

    public RemoveUser() {
        ELContext context
                = FacesContext.getCurrentInstance().getELContext();

        app = (UserApplication) FacesContext.getCurrentInstance()
                        .getApplication()
                        .getELResolver()
                        .getValue(context, null, "userApplication");
        
        app.updateUserList();
        
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        userManagedBean = (UserManagedBean) FacesContext.getCurrentInstance().getApplication()
        .getELResolver().getValue(elContext, null, "userManagedBean");
    }

    public void removeUser(int userId) {
       try
       {
            userManagedBean.removeUser(userId);
            
            app.searchAll();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User has been deleted succesfully"));     
       }
       catch (Exception ex)
       {
           
       }
       showForm = true;
    }
}

