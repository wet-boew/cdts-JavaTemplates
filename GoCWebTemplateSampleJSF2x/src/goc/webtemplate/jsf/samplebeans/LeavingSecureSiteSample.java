package goc.webtemplate.jsf.samplebeans;

import goc.webtemplate.component.jsf.DefaultTemplateBean;

import javax.servlet.http.HttpServletRequest;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named("leavingsecuresitesamplebean")
@RequestScoped
public class LeavingSecureSiteSample extends DefaultTemplateBean {

	@Override
	public void setLeavingSecureSiteRedirectUrl() { 
		this.leavingSecureSiteRedirectUrl = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getContextPath() + "/faces/templates/leavesecuresiteredirect.xhtml";
	}

	@Override
	public void setLeavingSecureSiteWarningEnabled() { this.leavingSecureSiteWarningEnabled = true; }

	@Override
	public void setLeavingSecureSiteWarningMessage() {
		this.leavingSecureSiteWarningMessage = "You are about to leave a secure site, do you wish to continue?";
	}

	@Override
	public void setLeavingSecureSiteExcludedDomains() {
		this.leavingSecureSiteExcludedDomain = "www.esdc.gc.ca,www.jobbank.gc.ca,www.readseal.ca";
	}
	
	@Override
	public void setLeavingSecureSiteDisplayModalWindow() {
		// this.leavingSecureSiteDisplayModalWindow = false;
	}
}
