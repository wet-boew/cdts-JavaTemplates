package goc.webtemplate.jsf.samplebeans;

import goc.webtemplate.ApplicationTitle;

import goc.webtemplate.component.jsf.DefaultTemplateCoreBean;

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
