package goc.webtemplate.spring.samplebeans;

import org.springframework.stereotype.Component;

import goc.webtemplate.component.spring.DefaultTemplateCoreBean;

@Component(value = "basesettingssamplebean")
public class BaseSettingsSampleBean extends DefaultTemplateCoreBean {

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
