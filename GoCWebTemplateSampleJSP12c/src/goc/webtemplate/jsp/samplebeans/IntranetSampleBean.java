package goc.webtemplate.jsp.samplebeans;

import goc.webtemplate.ApplicationTitle;

import goc.webtemplate.component.jsp.DefaultTemplateCoreBean;

public class IntranetSampleBean extends DefaultTemplateCoreBean {
    
    @Override
    public void onWebTemplateInitialize() {
        this.setHeaderTitle("GCIntranet Sample Page"); 
        
        this.setCDNEnvironment("ESDCProd");
        this.setTheme("gcintranet");
        this.setSubTheme("esdc");

        this.setApplicationTitle(new ApplicationTitle("GCIntranet Site", "http://www.google.ca"));
    }
}
