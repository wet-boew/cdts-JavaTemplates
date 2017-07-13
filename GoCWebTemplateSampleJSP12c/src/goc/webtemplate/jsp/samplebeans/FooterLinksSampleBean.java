package goc.webtemplate.jsp.samplebeans;

import goc.webtemplate.component.jsp.DefaultTemplateCoreBean;

public class FooterLinksSampleBean extends DefaultTemplateCoreBean {

    @Override
    public void onWebTemplateInitialize() {
        this.setContactLinkUrl("http://travel.gc.ca/");        
    }
}
