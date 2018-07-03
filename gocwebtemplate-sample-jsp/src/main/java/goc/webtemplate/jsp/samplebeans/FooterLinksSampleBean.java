package goc.webtemplate.jsp.samplebeans;

import goc.webtemplate.Link;

import goc.webtemplate.component.jsp.DefaultTemplateCoreBean;

public class FooterLinksSampleBean extends DefaultTemplateCoreBean {

    @Override
    public void onWebTemplateInitialize() {
        this.setContactLink(new Link("http://travel.gc.ca/"));        
    }
}
