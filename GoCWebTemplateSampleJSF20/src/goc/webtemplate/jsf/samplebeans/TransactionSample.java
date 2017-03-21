package goc.webtemplate.jsf.samplebeans;

import goc.webtemplate.component.jsf.DefaultTemplateBean;

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
