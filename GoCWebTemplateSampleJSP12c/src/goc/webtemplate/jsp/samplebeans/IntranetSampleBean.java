package goc.webtemplate.jsp.samplebeans;

import goc.webtemplate.Link;

import goc.webtemplate.component.jsp.DefaultTemplateCoreBean;

public class IntranetSampleBean extends DefaultTemplateCoreBean {
    
    @Override
    public void onWebTemplateInitialize() {
        this.setHeaderTitle("GCIntranet Sample Page"); 
        
        this.setCDNEnvironment("ESDCProd");
        this.setTheme("gcintranet");
        this.setSubTheme("esdc");

        this.setIntranetTitle(new Link("http://www.google.ca", "GCIntranet Site"));
    }
}
