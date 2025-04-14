package goc.webtemplate.spring.samplebeans;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import goc.webtemplate.Breadcrumb;
import goc.webtemplate.SessionTimeout;
import goc.webtemplate.SessionTimeoutTextOverrides;
import goc.webtemplate.component.spring.DefaultTemplateCoreBean;

@Component(value = "sessiontimeoutsamplebean")
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
        SessionTimeoutTextOverrides textOverrides = new SessionTimeoutTextOverrides();
        textOverrides.setButtonContinue("Keep Going");
        
        sconfig.setEnabled(true);
        sconfig.setInactivity(30000);
        sconfig.setReactionTime(10000);
        sconfig.setSessionAlive(30000);
        sconfig.setLogoutUrl("SessionTimeoutLogout");            
        sconfig.setRefreshCallbackUrl("SessionTimeoutValidity");
        sconfig.setRefreshOnClick(false);
        sconfig.setRefreshLimit(3);
        sconfig.setMethod("");
        sconfig.setAdditionalData("");
        sconfig.setSignInUrl("");
        sconfig.setTextOverrides(textOverrides);
        
        this.setSessionTimeoutConfiguration(sconfig);
    }
	
    //This is invoked from SessionTimeoutValidity.html
	public String getSessionValidity() {
		String sessionValid = "true";
		try {
			HttpServletRequest currentReq = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			HttpSession session = currentReq.getSession(false);
			sessionValid = (session == null ? "false" : "true");
		}
		catch (Exception ex) {}
		return sessionValid;
	}
}
