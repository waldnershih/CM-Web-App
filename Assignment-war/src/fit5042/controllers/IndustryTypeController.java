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
@Named(value = "industryTypeController")
@RequestScoped
public class IndustryTypeController {
	private int industryTypeId;
	private fit5042.repository.entities.IndustryType industryType;
	
	public IndustryTypeController() {
			industryTypeId = Integer.valueOf(FacesContext.getCurrentInstance()
	                .getExternalContext()
	                .getRequestParameterMap()
	                .get("industryTypeID"));
	        industryType = getIndustryType();
	}
	
	public int getIndustryTypeId() {
        return industryTypeId;
    }

    public void setIndustryTypeId(int industryTypeId) {
        this.industryTypeId = industryTypeId;
    }

	public fit5042.repository.entities.IndustryType getIndustryType() {
        if (industryType == null) {
            ELContext context = FacesContext.getCurrentInstance().getELContext();
            IndustryTypeApplication app = (IndustryTypeApplication) FacesContext.getCurrentInstance()
                            .getApplication()
                            .getELResolver()
                            .getValue(context, null, "industryTypeApplication");
            return app.getIndustryTypes().get(--industryTypeId);
        }
        return industryType;
    }
}
