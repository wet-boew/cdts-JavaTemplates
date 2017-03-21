package goc.webtemplate.jsf.samplebeans;

import goc.webtemplate.component.jsf.DefaultTemplateBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("basesettingssamplebean")
@RequestScoped
public class BaseSettingsSample extends DefaultTemplateBean {

	@Override
	public void setHeaderTitle() {
		this.headerTitle = "My Title";
	}

	@Override
	public void setDateModified() {
		this.dateModified = new java.util.Date();
	}

	@Override
	public void setHtmlHeaderElements() {
		this.htmlHeaderElements.add("<meta charset='UTF-8' />");
		this.htmlHeaderElements.add("<meta name='singer' content='Elvis' />");
		this.htmlHeaderElements.add("<meta http-equiv='default-style' content='sample' />");
	}
	
	@Override
	public void setScreenIdentifier() { this.screenIdentifier = "PAGE001"; }
}
