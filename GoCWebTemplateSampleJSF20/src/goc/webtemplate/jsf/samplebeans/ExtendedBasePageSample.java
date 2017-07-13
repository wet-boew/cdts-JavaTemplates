package goc.webtemplate.jsf.samplebeans;

import goc.webtemplate.component.jsf.DefaultTemplateCoreBean;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class ExtendedBasePageSample extends DefaultTemplateCoreBean {

    @Override
    public void onWebTemplateInitialize() {
        this.setHeaderTitle("Title set for every page!");
    }
	
    public String getWeather() { 
	    return "Sunny"; 
    }
	
    public String getSessionId() {
        HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return req.getSession().getId();
    }
}
