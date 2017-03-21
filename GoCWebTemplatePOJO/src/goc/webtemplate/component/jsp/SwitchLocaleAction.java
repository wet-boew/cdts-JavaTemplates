package goc.webtemplate.component.jsp;

import org.apache.struts2.ServletActionContext;

import goc.webtemplate.component.BaseUtil;

public class SwitchLocaleAction {
	public void execute() throws Exception {
		BaseUtil.doLocaleSwitch(ServletActionContext.getRequest(), ServletActionContext.getResponse());
	}
}