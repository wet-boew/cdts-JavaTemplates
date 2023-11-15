package goc.webtemplate.spring.samplebeans;

import org.springframework.stereotype.Component;

import goc.webtemplate.LeavingSecureSiteWarning;
import goc.webtemplate.component.spring.DefaultTemplateCoreBean;

@Component(value = "leavingsecuresitesamplebean")
public class LeavingSecureSiteSampleBean extends DefaultTemplateCoreBean {

    @Override
    public void onWebTemplateInitialize() {
        LeavingSecureSiteWarning lssw = new LeavingSecureSiteWarning();

        lssw.setEnabled(true);
        lssw.setMessage("You are about to leave a secure site, do you wish to continue?");
        lssw.setExcludedDomains("www.esdc.gc.ca,www.jobbank.gc.ca,www.readseal.ca");
        lssw.setCancelMessage("Don't leave");
        lssw.setYesMessage("Yes, leave this site");
        lssw.setDisplayModalWindow(true);
        
        this.setLeavingSecureSiteWarning(lssw);
    }
}
