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

import fit5042.mbeans.IndustryTypeManagedBean;

/**
 * @author user
 *
 */
@RequestScoped
@Named("addIndustryType")
public class AddIndustryType {
	@ManagedProperty(value="#{industryTypeManagedBean}") 
    IndustryTypeManagedBean industryTypeManagedBean;
	
	private boolean showForm = true;
	
	private IndustryType industryType;
	
	IndustryTypeApplication app;

	public IndustryType getIndustryType() {
		return industryType;
	}

	public void setIndustryType(IndustryType industryType) {
		this.industryType = industryType;
	}

	public boolean isShowForm() {
		return showForm;
	}

	public void setShowForm(boolean showForm) {
		this.showForm = showForm;
	}
	
	public AddIndustryType() 
    {
        ELContext context
                = FacesContext.getCurrentInstance().getELContext();

        app  = (IndustryTypeApplication) FacesContext.getCurrentInstance()
                        .getApplication()
                        .getELResolver()
                        .getValue(context, null, "industryTypeApplication");
        
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        industryTypeManagedBean = (IndustryTypeManagedBean) FacesContext.getCurrentInstance().getApplication()
        .getELResolver().getValue(elContext, null, "industryTypeManagedBean");
    }
	
	public void addIndustryType(IndustryType localIndustryType) {
       try
       {
            industryTypeManagedBean.addIndustryType(localIndustryType);

            app.searchAll();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("IndustryType has been added succesfully"));
       }
       catch (Exception ex)
       {
           
       }
        showForm = true;
    }
}
