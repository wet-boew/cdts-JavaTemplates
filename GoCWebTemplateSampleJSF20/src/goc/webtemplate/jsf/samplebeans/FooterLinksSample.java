package goc.webtemplate.jsf.samplebeans;

import goc.webtemplate.Link;

import goc.webtemplate.component.jsf.DefaultTemplateCoreBean;

public class FooterLinksSample extends DefaultTemplateCoreBean {

    @Override
    public void onWebTemplateInitialize() {
        this.setContactLink(new Link("http://travel.gc.ca/"));        
    }
}
