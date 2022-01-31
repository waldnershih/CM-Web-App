/**
 * 
 */
package fit5042.controllers;

import java.util.ArrayList;
import java.util.Arrays;

import javax.enterprise.context.ApplicationScoped;
import fit5042.mbeans.UserManagedBean;
import javax.inject.Named;
import fit5042.repository.entities.User;
import javax.el.ELContext;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

/**
 * @author user
 *
 */

@Named(value = "userApplication")
@ApplicationScoped
public class UserApplication {
	
	@ManagedProperty(value="#{userManagedBean}") 
    UserManagedBean userManagedBean;
	
	private final ArrayList<String> USER_TYPE = new ArrayList<String>(Arrays.asList("admin", "staff"));
	private ArrayList<User> users;
	
	private boolean showForm = true;
	
	public ArrayList<String> getUSER_TYPE() {
		return USER_TYPE;
	}

	public boolean isShowForm() {
        return showForm;
    }
	
	public UserApplication() throws Exception {       
        users = new ArrayList<>();
        
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        userManagedBean = (UserManagedBean) FacesContext.getCurrentInstance().getApplication()
        .getELResolver().getValue(elContext, null, "userManagedBean");
        
        updateUserList();
    }
	
	public ArrayList<User> getUsers() {
        return users;
    }
	
	private void setUsers(ArrayList<User> newUsers) {
        this.users = newUsers;
    }
	
	public void updateUserList() {
        if (users != null && users.size() > 0)
        {
            
        }
        else
        {
            users.clear();

            for (fit5042.repository.entities.User user : userManagedBean.getAllUsers())
            {
                users.add(user);
            }

            setUsers(users);
        }
    }

	public void searchUserById(int userId) {
        users.clear();
        
        users.add(userManagedBean.searchUserById(userId));
    }
	
	public void searchUserByName(String userName) {
		users.clear();
        
        users.add(userManagedBean.searchUserByName(userName));
    }	
	
	public void searchAll() {
        users.clear();
        
        for (fit5042.repository.entities.User user : userManagedBean.getAllUsers())
        {
            users.add(user);
        }
        
        //???
        setUsers(users);
    }
}
