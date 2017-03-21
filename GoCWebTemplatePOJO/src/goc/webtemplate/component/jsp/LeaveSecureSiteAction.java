package goc.webtemplate.component.jsp;

import org.apache.struts2.ServletActionContext;

import goc.webtemplate.component.BaseUtil;

public class LeaveSecureSiteAction {
	public void execute() throws Exception {
		BaseUtil.doLeaveSecureSite(ServletActionContext.getRequest(), ServletActionContext.getResponse());
	}
}
