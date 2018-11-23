package goc.webtemplate.jsf.samplebeans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import goc.webtemplate.Link;

import goc.webtemplate.component.jsf.DefaultTemplateCoreBean;

@Named("footerlinkssamplebean")
@RequestScoped
public class FooterLinksSample extends DefaultTemplateCoreBean {

    @Override
    public void onWebTemplateInitialize() {
    	//For a single link, the convenience function can be used:
        this.setContactLink(new Link("http://travel.gc.ca/"));
        
        /*
        //Example of having multiple links and specifying text (only for non-Akamai, non-Application template)
        java.util.ArrayList<Link> contactLinks = new java.util.ArrayList<Link>();
        contactLinks.add(new Link("http://canada.ca/", "Contact Canada!"));
        contactLinks.add(new Link("http://travel.gc.ca/", "Contact Travel!"));
        this.setContactLinks(contactLinks);
        */
    }
}
