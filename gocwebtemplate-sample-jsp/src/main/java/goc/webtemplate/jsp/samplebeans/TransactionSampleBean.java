package goc.webtemplate.jsp.samplebeans;

import goc.webtemplate.component.jsp.DefaultTemplateCoreBean;

public class TransactionSampleBean extends DefaultTemplateCoreBean {

    @Override
    public void onWebTemplateInitialize() {
        this.setPrivacyLinkUrl("http://www.tsn.ca");
        this.setTermsConditionsLinkUrl("http://www.lapresse.ca");
    }
}
