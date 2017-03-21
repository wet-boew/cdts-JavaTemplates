package goc.webtemplate.jsp.samplebeans;

import goc.webtemplate.component.jsp.DefaultTemplateBean;

public class TransactionSampleBean extends DefaultTemplateBean {

	@Override
	public void setPrivacyLinkUrl() {
		this.privacyLinkUrl = "http://www.tsn.ca";
	}

	@Override
	public void setTermsConditionsLinkUrl() {
		this.termsConditionsLinkUrl = "http://www.lapresse.ca";
	}
}
