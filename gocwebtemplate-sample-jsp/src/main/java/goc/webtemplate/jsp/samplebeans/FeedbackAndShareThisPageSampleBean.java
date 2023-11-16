package goc.webtemplate.jsp.samplebeans;

import java.util.ArrayList;
import java.util.List;

import goc.webtemplate.Constants;

import goc.webtemplate.component.jsp.DefaultTemplateCoreBean;

public class FeedbackAndShareThisPageSampleBean extends DefaultTemplateCoreBean {

    @Override
    public void onWebTemplateInitialize() {
        List<String> elements = new ArrayList<String>();
        elements.add("<meta content=\"width = device - width, initial - scale = 1\" name=\"viewport\">");
        elements.add("<meta name=\"dcterms.creator\" content=\"[Department name / Nom du dÃ©partement]\">");
        this.setHtmlHeaderElements(elements);

        this.setShowFeedbackLink(true);
        this.setFeedbackText("Contact");
        this.setFeedbackTextFr("Contactez-nous");
        this.setFeedbackUrl("http://www.google.ca"); 
        this.setFeedbackUrlFr("http://www.google.ca/?hl=fr");
        this.setFeedbackTheme("Theme");
        this.setFeedbackSection("Section");

        this.setShowSharePageLink(true);
        this.getSharePageMediaSites().add(Constants.SocialMediaSites.blogger);
        this.getSharePageMediaSites().add(Constants.SocialMediaSites.facebook);
        this.getSharePageMediaSites().add(Constants.SocialMediaSites.twitter);
    }
}
