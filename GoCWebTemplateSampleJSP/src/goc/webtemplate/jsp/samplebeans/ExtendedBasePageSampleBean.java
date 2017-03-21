package goc.webtemplate.jsp.samplebeans;

import goc.webtemplate.component.jsp.DefaultTemplateBean;

public class ExtendedBasePageSampleBean extends DefaultTemplateBean {

	@Override
	public void setHeaderTitle() {
		this.headerTitle = "Title set for everpage!";
	}

	public String getWeather() { return "Sunny"; }
	
	public String getSessionId() {
		return this.getRequest().getSession().getId();
	}
}
