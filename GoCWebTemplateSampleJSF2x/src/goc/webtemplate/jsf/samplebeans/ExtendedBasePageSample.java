package goc.webtemplate.jsf.samplebeans;

import goc.webtemplate.component.jsf.DefaultTemplateBean;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("extendedbasepagesamplebean")
@RequestScoped
public class ExtendedBasePageSample extends DefaultTemplateBean {

	@Override
	public void setHeaderTitle() {
		this.headerTitle = "Title set for everpage!";
	}
	
	public String getWeather() { return "Sunny"; }
	
	public String getSessionId() {
		HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		return req.getSession().getId();
	}
}
