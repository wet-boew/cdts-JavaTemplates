package goc.webtemplate.jsp.samplebeans;

import goc.webtemplate.Link;

import goc.webtemplate.component.jsp.DefaultTemplateCoreBean;

public class FooterLinksSampleBean extends DefaultTemplateCoreBean {

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
