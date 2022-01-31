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
@Named("searchUser")
public class SearchUser {
    private User user;
    
    UserApplication app;
    
    private int searchByInt;
    private String searchByString;
    private String searchByName;
    
    public UserApplication getApp() {
        return app;
    }
    
    public User getUser(){
        return user;
    }
    
    public int getSearchByInt() {
        return searchByInt;
    }

    public String getSearchByName() {
        return searchByName;
    }

    public String getSearchByString() {
        return searchByString;
    }
    
    public SearchUser() {
        ELContext context
                = FacesContext.getCurrentInstance().getELContext();

        app = (UserApplication) FacesContext.getCurrentInstance()
                        .getApplication()
                        .getELResolver()
                        .getValue(context, null, "userApplication");
        
        app.updateUserList();
    }

    public void searchUserById(int userId) {
       try
       {
            app.searchUserById(userId);
       }
       catch (Exception ex)
       {
           
       }
    }
    
    public void searchUserByName(String userName) {
       try
       {
            app.searchUserByName(userName);
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
    
    public void setApp(UserApplication app) {
        this.app = app;
    }
 
    public void setUser(User user){
        this.user = user;
    }
    
    
    public void setSearchByInt(int searchByInt) {
        this.searchByInt = searchByInt;
    }

    public void setSearchByName(String searchByName) {
        this.searchByName = searchByName;
    }

	public void setSearchByString(String searchByString) {
		this.searchByString = searchByString;
	}

}

