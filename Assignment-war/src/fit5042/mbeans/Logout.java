/**
 * 
 */
package fit5042.mbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 * @author user
 *
 */
@ManagedBean(name = "logout")
@RequestScoped
public class Logout {
	private String homePage;
	
	public Logout() {
		homePage = "index.xhtml";
	}	
	
	public String getHomePage() {
		return homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	public void logout(){
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().invalidateSession();
        try {
			context.getExternalContext().redirect(homePage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
