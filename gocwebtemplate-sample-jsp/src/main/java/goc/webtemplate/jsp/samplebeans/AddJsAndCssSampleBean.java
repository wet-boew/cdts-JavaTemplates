package goc.webtemplate.jsp.samplebeans;

import goc.webtemplate.component.jsp.DefaultTemplateCoreBean;

public class AddJsAndCssSampleBean extends DefaultTemplateCoreBean {

    @Override
    public void onWebTemplateInitialize() {
        // Add a CSS to the header
        this.getHtmlHeaderElements().add("<link rel='stylesheet' type='text/css' href='samplecontents/mystyle.css'>");
        
        // Add a JS to the body (bottom of page)
        this.getHtmlBodyElements().add("<script src='samplecontents/myJS.js'></script>");
    }
}
