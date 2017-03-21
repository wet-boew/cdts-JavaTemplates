package goc.webtemplate.jsp.samplebeans;

import goc.webtemplate.component.jsp.DefaultTemplateBean;

public class NestedMasterPageTemplateBean extends DefaultTemplateBean {

	@Override
	public void setDateModified() {
		this.dateModified = new java.util.Date();
	}
}
