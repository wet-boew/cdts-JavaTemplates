package goc.webtemplate.jsf.samplebeans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import goc.webtemplate.IntranetTitle;

import goc.webtemplate.component.jsf.DefaultTemplateCoreBean;

@Named("gcintranetsamplebean")
@RequestScoped
public class IntranetSample extends DefaultTemplateCoreBean {
    
    @Override
    public void onWebTemplateInitialize() {
        this.setHeaderTitle("GCIntranet Sample Page"); 
        
        this.setCDNEnvironment("ESDC_Prod");
        //Setting the environment will load proper theme/subtheme values, but we can override them if needed 
        this.setTheme("gcintranet");
        this.setSubTheme("esdc");

        this.setIntranetTitle(new IntranetTitle("http://www.google.ca", "GCIntranet Site", "ACRONYM", "Optional Bold Text"));
    }
}
