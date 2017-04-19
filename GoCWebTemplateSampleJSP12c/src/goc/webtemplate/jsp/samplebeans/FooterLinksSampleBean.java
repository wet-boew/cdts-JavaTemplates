package goc.webtemplate.jsp.samplebeans;

import goc.webtemplate.component.jsp.DefaultTemplateBean;

public class FooterLinksSampleBean extends DefaultTemplateBean {

    @Override
    public void setContactLinkUrl()
    {
        this.contactLinkUrl = "http://travel.gc.ca/";
    }
}
