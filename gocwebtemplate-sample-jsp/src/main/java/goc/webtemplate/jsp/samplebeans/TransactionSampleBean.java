package goc.webtemplate.jsp.samplebeans;

import goc.webtemplate.FooterLink;

import goc.webtemplate.component.jsp.DefaultTemplateCoreBean;

public class TransactionSampleBean extends DefaultTemplateCoreBean {

    @Override
    public void onWebTemplateInitialize() {
        this.setPrivacyLink(new FooterLink("https://www.google.com/search?q=privacy"));
        this.setTermsConditionsLink(new FooterLink("https://www.google.com/search?q=terms", true));
    }
}
