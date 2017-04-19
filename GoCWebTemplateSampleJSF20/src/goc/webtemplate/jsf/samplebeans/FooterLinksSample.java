package goc.webtemplate.jsf.samplebeans;

import goc.webtemplate.component.jsf.DefaultTemplateBean;

public class FooterLinksSample extends DefaultTemplateBean {
    
    @Override
    public void setContactLinkUrl()
    {
        this.contactLinkUrl = "http://travel.gc.ca/";
    }
}
