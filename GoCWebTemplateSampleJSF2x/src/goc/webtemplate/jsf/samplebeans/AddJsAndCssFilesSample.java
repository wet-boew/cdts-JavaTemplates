package goc.webtemplate.jsf.samplebeans;

import goc.webtemplate.component.jsf.DefaultTemplateCoreBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("addjsandcssfilessamplebean")
@RequestScoped
public class AddJsAndCssFilesSample extends DefaultTemplateCoreBean {

    @Override
    public void onWebTemplateInitialize() {
        // Add a CSS to the header
        this.getHtmlHeaderElements().add("<link rel='stylesheet' type='text/css' href='samplecontents/mystyle.css'>");
        
        // Add a JS to the body (bottom of page)
        this.getHtmlBodyElements().add("<script src='samplecontents/myJS.js'></script>");
    }
}
