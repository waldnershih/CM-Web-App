package fit5042.controllers;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Hsuan-Yu Shih
 */
@Named(value = "titleController")
@RequestScoped
public class TitleController {

    private String pageTitle;

    public TitleController() {
        pageTitle = "Management System";
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }
}
