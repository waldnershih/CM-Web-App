package fit5042.mbeans;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import fit5042.repository.UserRepository;
import fit5042.repository.entities.Contact;
import fit5042.repository.entities.Customer;
import fit5042.repository.entities.User;

@ManagedBean(name = "userManagedBean")
@SessionScoped
public class UserManagedBean implements Serializable{
	
	@EJB
    UserRepository userRepository;
	
	private User user;
	
	public User getUser() {
		this.initializeUser();
        return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
		
	public UserManagedBean() {
	}
	
	public void addUser(User user) {
        try {
        	user.setUserPassword(getSHA256StrJava(user.getUserPassword()));
        	userRepository.addUser(user);
       } catch (Exception ex) {
           Logger.getLogger(UserManagedBean.class.getName()).log(Level.SEVERE, null, ex);
       }
	}
	   
	public void editUser(User user) {
		 try {           
			 	user.setUserPassword(getSHA256StrJava(user.getUserPassword()));
	            userRepository.editUser(user);
	            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User has been updated succesfully"));
	        } catch (Exception ex) {
	            Logger.getLogger(UserManagedBean.class.getName()).log(Level.SEVERE, null, ex);
	        }
	}
	   
	public List<User> getAllUsers() {
		try {
            return userRepository.getAllUsers();
        } catch (Exception ex) {
            Logger.getLogger(UserManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
	}

	public void removeUser(int userId) {
		try {
            userRepository.removeUser(userId);
        } catch (Exception ex) {
            Logger.getLogger(UserManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	  
	public User searchUserById(int userId) {
		 try {
	            return userRepository.searchUserById(userId);
	        } catch (Exception ex) {
	            Logger.getLogger(UserManagedBean.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        
	        return null;
	}

	public User searchUserByName(String userName) {
		try {
			return userRepository.searchUserByName(userName);
	    } catch (Exception ex) {
	        Logger.getLogger(UserManagedBean.class.getName()).log(Level.SEVERE, null, ex);
	    }
	        
	     return null;
	}
	
	public void addUser(fit5042.controllers.User localUser) {
        User user = convertUserToEntity(localUser);

        try {
        	user.setUserPassword(getSHA256StrJava(user.getUserPassword()));
            userRepository.addUser(user);
        } catch (Exception ex) {
            Logger.getLogger(UserManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	
	public void changedPassword(String firstPassword, String secondPassword) {
		 try {           
	            if (firstPassword.equals(secondPassword)) {
	            	user.setUserPassword(getSHA256StrJava(firstPassword));
		            userRepository.editUser(user);
	            	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User has been updated succesfully"));
	            }else {
	            	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User updated has failed. Please enter same passwords."));
	            }
	        } catch (Exception ex) {
	            Logger.getLogger(UserManagedBean.class.getName()).log(Level.SEVERE, null, ex);
	        }
	}
////////////////////////////////////////////////////////////////////////////	
	private User convertUserToEntity(fit5042.controllers.User localUser){
        User user = new User();
        user.setUserId(localUser.getUserId());
        user.setUserName(localUser.getUserName());
        user.setUserPassword(localUser.getUserPassword());
        user.setUserType(localUser.getUserType());
        return user;
    }
	
	public static String getSHA256StrJava(String str){
		MessageDigest messageDigest;
		String encodeStr = "";
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(str.getBytes("UTF-8"));
			encodeStr = byte2Hex(messageDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encodeStr;
	}

	private static String byte2Hex(byte[] bytes){
		StringBuffer stringBuffer = new StringBuffer();
		String temp = null;
		for (int i=0;i<bytes.length;i++  ){
			temp = Integer.toHexString(bytes[i] & 0xFF);
		if (temp.length()==1){
			stringBuffer.append("0");
		}
			stringBuffer.append(temp);
		}
		return stringBuffer.toString();
	}
	
	private void initializeUser() {
    	Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        try {
        	if (principal != null) {
        		user = userRepository.searchUserByName(principal.getName());
	        }
        } catch (Exception ex) {
            Logger.getLogger(CustomerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
	
}
