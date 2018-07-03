package goc.webtemplate.jsf.samplebeans;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import goc.webtemplate.component.jsf.DefaultTemplateCoreBean;

@Named("extendedbasepagesamplebean")
@RequestScoped
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
