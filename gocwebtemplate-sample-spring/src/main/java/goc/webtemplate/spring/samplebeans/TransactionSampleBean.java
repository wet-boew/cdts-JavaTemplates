package goc.webtemplate.spring.samplebeans;

import org.springframework.stereotype.Component;

import goc.webtemplate.FooterLink;
import goc.webtemplate.component.spring.DefaultTemplateCoreBean;

@Component(value = "transactionsamplebean")
public class TransactionSampleBean extends DefaultTemplateCoreBean {

    @Override
    public void onWebTemplateInitialize() {
        this.setPrivacyLink(new FooterLink("https://www.google.com/search?q=privacy"));
        this.setTermsConditionsLink(new FooterLink("https://www.google.com/search?q=terms", true));
    }
}
