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
@Named("searchIndustryType")
public class SearchIndustryType {
    private IndustryType industryType;
    
    IndustryTypeApplication app;
    
    private int searchByInt;
    private String searchByString;
    private String searchByName;
    
    public IndustryTypeApplication getApp() {
        return app;
    }
    
    public IndustryType getIndustryType(){
        return industryType;
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
    
    public SearchIndustryType() {
        ELContext context
                = FacesContext.getCurrentInstance().getELContext();

        app = (IndustryTypeApplication) FacesContext.getCurrentInstance()
                        .getApplication()
                        .getELResolver()
                        .getValue(context, null, "industryTypeApplication");
        
        app.updateIndustryTypeList();
    }

    public void searchIndustryTypeById(int industryTypeId) {
       try
       {
            app.searchIndustryTypeById(industryTypeId);
       }
       catch (Exception ex)
       {
           
       }
    }
    
    public void searchIndustryTypeByName(String industryTypeName) {
       try
       {
            app.searchIndustryTypeByName(industryTypeName);
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
    
    public void setApp(IndustryTypeApplication app) {
        this.app = app;
    }
 
    public void setIndustryType(IndustryType industryType){
        this.industryType = industryType;
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

