package goc.webtemplate.jsp.samplebeans;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import goc.webtemplate.Breadcrumb;
import goc.webtemplate.SessionTimeout;
import goc.webtemplate.component.jsp.DefaultTemplateBean;

public class SessionTimeoutSampleBean extends DefaultTemplateBean {

	@Override
	public void setBreadcrumbsList() {
		this.breadCrumbsList.add(new Breadcrumb("http://www.canada.ca/en/index.html", "Home", ""));
		this.breadCrumbsList.add(new Breadcrumb("http://www.esdc.gc.ca/en/jobs/opportunities/index.page", "Jobs", ""));
		this.breadCrumbsList.add(new Breadcrumb("http://www.esdc.gc.ca/en/jobs/opportunities/youth_students.page", "Opportunities", ""));
		this.breadCrumbsList.add(new Breadcrumb("", "FSWEP", "Federal Student Work Experience Program"));
	}

	@Override
	public void setSessionTimeoutConfigurations() {
		SessionTimeout configs = new SessionTimeout();
		
		configs.setInActivity(30000);
		configs.setReactionTime(10000);
		configs.setSessionAlive(30000);
		configs.setLogoutUrl("sessiontimeoutlogout.jsp");            
		configs.setRefreshCallbackUrl("sessiontimeoutvalidity.jsp");
		configs.setRefreshOnClick(false);
		configs.setRefreshLimit(3);
		configs.setMethod("");
		configs.setAdditionalData("");
		
		this.sessionTimeoutConfigurations = configs;
	}
	
	@Override
	public void setSessionTimeoutEnabled() {
		this.sessionTimeoutEnabled = true;
	}

	public String getLogoutAction() {
		try {
			HttpServletRequest currentReq = ServletActionContext.getRequest();
			HttpSession session = currentReq.getSession(false);
			if (session != null) session.invalidate();
			ServletActionContext.getResponse().sendRedirect("addjsandcssfilessample.action");
		}
		catch (Exception ex) {}
		return "";
	}
	
	public String getSessionValidity() {
		String sessionValid = "true";
		try {
			HttpServletRequest currentReq = ServletActionContext.getRequest();
			HttpSession session = currentReq.getSession(false);
			sessionValid = (session == null ? "false" : "true");
		}
		catch (Exception ex) {}
		return sessionValid;
	}
}
