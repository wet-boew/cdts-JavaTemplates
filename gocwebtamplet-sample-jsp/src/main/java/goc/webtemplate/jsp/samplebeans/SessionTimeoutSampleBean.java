package goc.webtemplate.jsp.samplebeans;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import goc.webtemplate.Breadcrumb;
import goc.webtemplate.SessionTimeout;

import goc.webtemplate.component.jsp.DefaultTemplateCoreBean;

public class SessionTimeoutSampleBean extends DefaultTemplateCoreBean {

    @Override
    public void onWebTemplateInitialize() {
        ArrayList<Breadcrumb>   bcs = new ArrayList<Breadcrumb>();
        
        bcs.add(new Breadcrumb("http://www.canada.ca/en/index.html", "Home", ""));
        bcs.add(new Breadcrumb("http://www.esdc.gc.ca/en/jobs/opportunities/index.page", "Jobs", ""));
        bcs.add(new Breadcrumb("http://www.esdc.gc.ca/en/jobs/opportunities/youth_students.page", "Opportunities", ""));
        bcs.add(new Breadcrumb("", "FSWEP", "Federal Student Work Experience Program"));
        
        this.setBreadcrumbs(bcs);

        SessionTimeout sconfig = new SessionTimeout();
        
        sconfig.setEnabled(true);
        sconfig.setInactivity(30000);
        sconfig.setReactionTime(10000);
        sconfig.setSessionAlive(30000);
        sconfig.setLogoutUrl("samplecontents/sessiontimeoutlogout.jsp");            
        sconfig.setRefreshCallbackUrl("samplecontents/sessiontimeoutvalidity.jsp");
        sconfig.setRefreshOnClick(false);
        sconfig.setRefreshLimit(3);
        sconfig.setMethod("");
        sconfig.setAdditionalData("");
        
        this.setSessionTimeoutConfiguration(sconfig);
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
