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
@Named(value = "userController")
@RequestScoped
public class UserController {
	private int userId;
	private fit5042.repository.entities.User user;
	
	public UserController() {
			userId = Integer.valueOf(FacesContext.getCurrentInstance()
	                .getExternalContext()
	                .getRequestParameterMap()
	                .get("userID"));
	        user = getUser();
	}
	
	public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

	public fit5042.repository.entities.User getUser() {
        if (user == null) {
            ELContext context = FacesContext.getCurrentInstance().getELContext();
            UserApplication app = (UserApplication) FacesContext.getCurrentInstance()
                            .getApplication()
                            .getELResolver()
                            .getValue(context, null, "userApplication");
            return app.getUsers().get(--userId);
        }
        return user;
    }
}
