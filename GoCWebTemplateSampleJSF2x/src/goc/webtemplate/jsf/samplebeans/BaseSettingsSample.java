package goc.webtemplate.jsf.samplebeans;

import goc.webtemplate.component.jsf.DefaultTemplateCoreBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("basesettingssamplebean")
@RequestScoped
public class BaseSettingsSample extends DefaultTemplateCoreBean {

    @Override
    public void onWebTemplateInitialize() {
        this.setHeaderTitle("My Title");
        this.setDateModified(new java.util.Date());
        this.setVersionIdentifier("ver01");
        this.setScreenIdentifier("PAGE001"); 
        
        this.getHtmlHeaderElements().add("<meta charset='UTF-8' />");
        this.getHtmlHeaderElements().add("<meta name='singer' content='Elvis' />");
        this.getHtmlHeaderElements().add("<meta http-equiv='default-style' content='sample' />");
    }
}
