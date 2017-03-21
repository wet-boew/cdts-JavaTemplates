package goc.webtemplate.jsf.samplebeans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import goc.webtemplate.component.jsf.DefaultTemplateBean;

@Named("transactionsamplebean")
@RequestScoped
public class TransactionSample extends DefaultTemplateBean {

	@Override
	public void setPrivacyLinkUrl() {
		this.privacyLinkUrl = "http://www.tsn.ca";
	}

	@Override
	public void setTermsConditionsLinkUrl() {
		this.termsConditionsLinkUrl = "http://www.lapresse.ca";
	}
}
