package goc.webtemplate.jsf.samplebeans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import goc.webtemplate.component.jsf.DefaultTemplateCoreBean;

@Named("transactionsamplebean")
@RequestScoped
public class TransactionSample extends DefaultTemplateCoreBean {

    @Override
    public void onWebTemplateInitialize() {
        this.setPrivacyLinkUrl("http://www.tsn.ca");
        this.setTermsConditionsLinkUrl("http://www.lapresse.ca");
    }
}
