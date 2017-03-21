package goc.webtemplate.jsp.samplebeans;

import goc.webtemplate.component.jsp.DefaultTemplateBean;

public class LeaveSecureSiteSampleBean extends DefaultTemplateBean {

	@Override
	public void setLeavingSecureSiteWarningEnabled() {
		this.leavingSecureSiteWarningEnabled = true;
	}

	@Override
	public void setLeavingSecureSiteWarningMessage() {
		this.leavingSecureSiteWarningMessage = "You are about to leave a secure site, do you wish to continue?";
	}

	@Override
	public void setLeavingSecureSiteRedirectUrl() {
		this.leavingSecureSiteRedirectUrl = "leavesecuresiteredirect.action";
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
