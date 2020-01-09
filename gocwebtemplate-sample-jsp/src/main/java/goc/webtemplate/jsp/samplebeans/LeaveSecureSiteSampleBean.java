package goc.webtemplate.jsp.samplebeans;

import goc.webtemplate.LeavingSecureSiteWarning;

import goc.webtemplate.component.jsp.DefaultTemplateCoreBean;

public class LeaveSecureSiteSampleBean extends DefaultTemplateCoreBean {

    @Override
    public void onWebTemplateInitialize() {
        LeavingSecureSiteWarning lssw = new LeavingSecureSiteWarning();

        lssw.setEnabled(true);
        lssw.setMessage("You are about to leave a secure site, do you wish to continue?");
        lssw.setRedirectUrl("leavesecuresiteredirect.action");
        lssw.setExcludedDomains("www.esdc.gc.ca,www.jobbank.gc.ca,www.readseal.ca");
        lssw.setCancelMessage("Don't leave");
        lssw.setYesMessage("Yes, leave this site");
        lssw.setDisplayModalWindow(true);
        
        this.setLeavingSecureSiteWarning(lssw);
    }
}
