package goc.webtemplate.jsf.samplebeans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import goc.webtemplate.FooterLink;
import goc.webtemplate.component.jsf.DefaultTemplateCoreBean;

@Named("transactionsamplebean")
@RequestScoped
public class TransactionSample extends DefaultTemplateCoreBean {

    @Override
    public void onWebTemplateInitialize() {
        this.setPrivacyLink(new FooterLink("https://www.google.com/search?q=privacy"));
        this.setTermsConditionsLink(new FooterLink("https://www.google.com/search?q=terms", true));
    }
}
