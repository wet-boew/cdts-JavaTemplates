package goc.webtemplate.spring.samplebeans;

import org.springframework.stereotype.Component;

import goc.webtemplate.component.spring.DefaultTemplateCoreBean;

@Component(value = "addjsandcssfilessamplebean")
public class AddJsAndCssFilesSampleBean extends DefaultTemplateCoreBean {

    public void onWebTemplateInitialize() {
        // Add a CSS to the header
    	this.getHtmlHeaderElements().add("<link rel='stylesheet' type='text/css' href='css/mystyle.css'>");
        
        // Add a JS to the body (bottom of page)
        this.getHtmlBodyElements().add("<script src='js/myJS.js'></script>");
    }
}