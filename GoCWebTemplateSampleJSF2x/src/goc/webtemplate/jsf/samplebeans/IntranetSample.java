package goc.webtemplate.jsf.samplebeans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import goc.webtemplate.Link;

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

        this.setIntranetTitle(new Link("http://www.google.ca", "GCIntranet Site"));
    }
}
