package goc.webtemplate.jsf.samplebeans;

import goc.webtemplate.component.jsf.DefaultTemplateBean;

public class IntranetSample extends DefaultTemplateBean {
	@Override
	public void setHeaderTitle() { 
		this.headerTitle = "GCIntranet Sample Page"; 
	}
	
	@Override
	public void setCDNEnvironment() {
		this.cdnEnvironment = "ESDCProd";
	}
	
	@Override
	public void setSubTheme() {
		this.subTheme = "esdc";
	}

	@Override
	public void setTheme() {
		this.mainTheme = "gcintranet";
	}

	@Override
	public void setApplicationTitleText() {
		this.applicationTitleText = "GCIntranet Site";
	}

	@Override
	public void setApplicationTitleUrl() {
		this.applicationTitleUrl = "http://www.google.ca";
	}
}
