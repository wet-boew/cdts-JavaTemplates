package goc.webtemplate.jsf.samplebeans;

import goc.webtemplate.component.jsf.DefaultTemplateCoreBean;

public class TransactionSample extends DefaultTemplateCoreBean {

    @Override
    public void onWebTemplateInitialize() {
        this.setPrivacyLinkUrl("http://www.tsn.ca");
        this.setTermsConditionsLinkUrl("http://www.lapresse.ca");
    }
}
