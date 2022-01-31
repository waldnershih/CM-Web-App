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

import fit5042.mbeans.CustomerManagedBean;

/**
 * @author user
 *
 */
@RequestScoped
@Named("addCustomer")
public class AddCustomer {
	@ManagedProperty(value="#{customerManagedBean}") 
    CustomerManagedBean customerManagedBean;
	
	private boolean showForm = true;
	
	private Customer customer;
	
	CustomerApplication app;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public boolean isShowForm() {
		return showForm;
	}

	public void setShowForm(boolean showForm) {
		this.showForm = showForm;
	}
	
	public AddCustomer() 
    {
        ELContext context
                = FacesContext.getCurrentInstance().getELContext();

        app  = (CustomerApplication) FacesContext.getCurrentInstance()
                        .getApplication()
                        .getELResolver()
                        .getValue(context, null, "customerApplication");
        
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        customerManagedBean = (CustomerManagedBean) FacesContext.getCurrentInstance().getApplication()
        .getELResolver().getValue(elContext, null, "customerManagedBean");
    }
	
	public void addCustomer(Customer localCustomer) {
       try
       {
            customerManagedBean.addCustomer(localCustomer);

            app.searchAll();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Customer has been added succesfully"));
       }
       catch (Exception ex)
       {
           
       }
        showForm = true;
    }
}
