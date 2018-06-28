package goc.webtemplate.component.jsp;

import org.apache.struts2.ServletActionContext;

import goc.webtemplate.component.BaseUtil;

public class SwitchLocaleAction {
    
    public void setGoCTemplateQueryString(String value)
    {
        //This method only exists because struts' ParametersInterceptor, which is part
        //of the default interceptor stack will be looking for it trying to set the parameter
        //value from the querystring and issue an warning message in the log if not present. 
        //
        //We don't need its value here since BaseUtil.doLocaleSwitch
        //will look for it in the request object directly, which is why this method
        //does nothing.
        //
        //The actual way to do this (to avoid this extra method) would be to create 
        //an interceptor stack in struts.xml and refer to it for this action, but 
        //for the template teh goal is to keep struts.xml with as little template-specific 
        //stuff as possible.
    }
    
	public void execute() throws Exception {
		BaseUtil.doLocaleSwitch(ServletActionContext.getRequest(), ServletActionContext.getResponse());
	}
}