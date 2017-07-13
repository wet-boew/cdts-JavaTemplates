package goc.webtemplate.jsf.samplebeans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import goc.webtemplate.LeavingSecureSiteWarning;

import goc.webtemplate.component.jsf.DefaultTemplateCoreBean;

@Named("leavingsecuresitesamplebean")
@RequestScoped
public class LeavingSecureSiteSample extends DefaultTemplateCoreBean {

    @Override
    public void onWebTemplateInitialize() {
        LeavingSecureSiteWarning lssw = new LeavingSecureSiteWarning();

        lssw.setEnabled(true);
        lssw.setMessage("You are about to leave a secure site, do you wish to continue?");
        lssw.setRedirectUrl("leavesecuresiteredirect.action");
        lssw.setExcludedDomains("www.esdc.gc.ca,www.jobbank.gc.ca,www.readseal.ca");
        lssw.setDisplayModalWindow(true);
        
        this.setLeavingSecureSiteWarning(lssw);
    }
}
