package fit5042.controllers;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import fit5042.repository.entities.User;

/**
 *
 * @author Hsuan-Yu Shih
 */
@RequestScoped
@Named("searchCustomer")
public class SearchCustomer {
    private Customer customer;
    
    CustomerApplication app;
    
    private int searchByInt;
    private String searchByString;
    private String searchByName;
    private String searchByUserName;
    
    public CustomerApplication getApp() {
        return app;
    }
    
    public Customer getCustomer(){
        return customer;
    }
    
    public int getSearchByInt() {
        return searchByInt;
    }

    public String getSearchByName() {
        return searchByName;
    }

    public String getSearchByUserName() {
    	return searchByUserName;
    }
    
    public String getSearchByString() {
        return searchByString;
    }
    
    public SearchCustomer() {
        ELContext context
                = FacesContext.getCurrentInstance().getELContext();

        app = (CustomerApplication) FacesContext.getCurrentInstance()
                        .getApplication()
                        .getELResolver()
                        .getValue(context, null, "customerApplication");
        app.updateCustomerList();
    }

    public void searchCustomerById(int customerId) {
       try
       {
            app.searchCustomerById(customerId);
       }
       catch (Exception ex)
       {
           
       }
    }
    
    public void searchCustomerByName(String customerName) {
       try
       {
            app.searchCustomerByName(customerName);
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
    
    public void setApp(CustomerApplication app) {
        this.app = app;
    }
 
    public void setCustomer(Customer customer){
        this.customer = customer;
    }
    
    
    public void setSearchByInt(int searchByInt) {
        this.searchByInt = searchByInt;
    }

    public void setSearchByName(String searchByName) {
        this.searchByName = searchByName;
    }

    public void setSearchByUserName(String searchByUserName) {
    	this.searchByUserName = searchByUserName;
    }
    
	public void setSearchByString(String searchByString) {
		this.searchByString = searchByString;
	}
}

