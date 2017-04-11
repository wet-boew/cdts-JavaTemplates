package goc.webtemplate.jsf.samplebeans;

import goc.webtemplate.component.jsf.DefaultTemplateBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("footerlinkssamplebean")
@RequestScoped
public class FooterLinksSample extends DefaultTemplateBean {
    
    @Override
    public void setContactLinkUrl()
    {
        this.contactLinkUrl = "http://travel.gc.ca/";
    }
}
