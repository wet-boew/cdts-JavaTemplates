package goc.webtemplate.jsp.samplebeans;

import goc.webtemplate.component.jsp.DefaultTemplateBean;

public class AddJsAndCssSampleBean extends DefaultTemplateBean {

	@Override
	public void setHtmlHeaderElements() {
		// Add a CSS to the header
		this.htmlHeaderElements.add("<link rel='stylesheet' type='text/css' href='samplecontents/mystyle.css'>");
	}

	@Override
	public void setHtmlBodyElements() {
		// Add a JS to the body (bottom of page)
		this.htmlBodyElements.add("<script src='samplecontents/myJS.js'></script>");
	}
}
