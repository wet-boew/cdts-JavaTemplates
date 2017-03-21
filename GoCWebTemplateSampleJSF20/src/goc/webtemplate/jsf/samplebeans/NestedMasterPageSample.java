package goc.webtemplate.jsf.samplebeans;

import goc.webtemplate.component.jsf.DefaultTemplateBean;

public class NestedMasterPageSample extends DefaultTemplateBean {

	@Override
	public void setDateModified() { 
		this.dateModified = new java.util.Date();
	}
}
