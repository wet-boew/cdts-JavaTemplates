package goc.webtemplate.jsp.samplebeans;

import goc.webtemplate.IntranetTitle;

import goc.webtemplate.component.jsp.DefaultTemplateCoreBean;

public class IntranetSampleBean extends DefaultTemplateCoreBean {
    
    @Override
    public void onWebTemplateInitialize() {
        this.setHeaderTitle("GCIntranet Sample Page"); 
        
        this.setCDNEnvironment("ESDC_Prod");
        //Setting the environment will load proper theme/subtheme values, but we can override them if needed 
        this.setTheme("gcintranet");
        this.setSubTheme("esdc");

        this.setIntranetTitle(new IntranetTitle("http://www.google.ca", "GCIntranet Site", "ACRONYM"));
    }
}
