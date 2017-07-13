package goc.webtemplate.jsf.samplebeans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import goc.webtemplate.ApplicationTitle;

import goc.webtemplate.component.jsf.DefaultTemplateCoreBean;

@Named("gcintranetsamplebean")
@RequestScoped
public class IntranetSample extends DefaultTemplateCoreBean {
    
    @Override
    public void onWebTemplateInitialize() {
        this.setHeaderTitle("GCIntranet Sample Page"); 
        
        this.setCDNEnvironment("ESDCProd");
        this.setTheme("gcintranet");
        this.setSubTheme("esdc");

        this.setApplicationTitle(new ApplicationTitle("GCIntranet Site", "http://www.google.ca"));
    }
}
