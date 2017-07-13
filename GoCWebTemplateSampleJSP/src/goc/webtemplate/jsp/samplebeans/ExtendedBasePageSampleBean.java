package goc.webtemplate.jsp.samplebeans;

import goc.webtemplate.component.jsp.DefaultTemplateCoreBean;

public class ExtendedBasePageSampleBean extends DefaultTemplateCoreBean {

    @Override
    public void onWebTemplateInitialize() {
        this.setHeaderTitle("Title set for every page!");
    }

	public String getWeather() { 
	    return "Sunny"; 
    }
	
	public String getSessionId() {
		return this.getRequest().getSession().getId();
	}
}
