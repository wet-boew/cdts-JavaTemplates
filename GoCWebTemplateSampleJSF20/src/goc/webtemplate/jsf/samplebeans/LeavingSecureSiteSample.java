package goc.webtemplate.jsf.samplebeans;

import goc.webtemplate.component.jsf.DefaultTemplateBean;

public class LeavingSecureSiteSample extends DefaultTemplateBean {

	@Override
	public void setLeavingSecureSiteRedirectUrl() { 
		this.leavingSecureSiteRedirectUrl = "customredirect.xhtml";
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
