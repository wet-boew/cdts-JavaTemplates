package goc.webtemplate.spring.samplebeans;

import org.springframework.stereotype.Component;

import goc.webtemplate.Link;
import goc.webtemplate.component.spring.DefaultTemplateCoreBean;

@Component(value = "footerlinkssamplebean")
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

        /*
        //Example of adding a contextual footer with 3 links
        List<FooterLink> links = new ArrayList<FooterLink>();

        links.add(new FooterLink("http://www.canada.ca/en/index.html", "Link 1", false));
        links.add(new FooterLink("http://www.canada.ca/en/index.html", "Link 2", false));
        links.add(new FooterLink("http://www.canada.ca/en/index.html", "Link 3", false));
        this.setContextualFooter(new ContextualFooter("Title", links));
        */

        //Hiding the main footer
        //this.setHideMainFooter(true);

        //Hiding the corporate footer links
        //this.setHideCorporateFooter(true);

        //Overwriting the href of the privacy link and the terms and conditions link
        //this.setPrivacyLink(new FooterLink("http://canada.ca/"));
        //this.setTermsConditionsLink(new FooterLink("http://canada.ca/"));
    }
}
