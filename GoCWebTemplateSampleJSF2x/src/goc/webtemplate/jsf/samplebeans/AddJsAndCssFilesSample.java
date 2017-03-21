package goc.webtemplate.jsf.samplebeans;

import goc.webtemplate.component.jsf.DefaultTemplateBean;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("addjsandcssfilessamplebean")
@RequestScoped
public class AddJsAndCssFilesSample extends DefaultTemplateBean {

	@Override
	public void setHtmlBodyElements() {
		// Add a JS to the body (bottom of page)
		this.htmlBodyElements.add("<script src='myJS.js'></script>");
	}

	@Override
	public void setHtmlHeaderElements() {
		// Add a CSS to the header
		this.htmlHeaderElements.add("<link rel='stylesheet' type='text/css' href='mystyle.css'>");
	}
}
