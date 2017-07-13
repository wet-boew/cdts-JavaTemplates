package goc.webtemplate.jsf.samplebeans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import goc.webtemplate.component.jsf.DefaultTemplateCoreBean;

@Named("footerlinkssamplebean")
@RequestScoped
public class FooterLinksSample extends DefaultTemplateCoreBean {

    @Override
    public void onWebTemplateInitialize() {
        this.setContactLinkUrl("http://travel.gc.ca/");        
    }
}
