package goc.webtemplate.jsp.samplebeans;

import java.util.ArrayList;
import java.util.List;

import goc.webtemplate.Constants;
import goc.webtemplate.FeedbackLink;
import goc.webtemplate.component.jsp.DefaultTemplateCoreBean;

public class FeedbackAndShareThisPageSampleBean extends DefaultTemplateCoreBean {

    @Override
    public void onWebTemplateInitialize() {
        //Your page MUST have the following metadata defined for the feedback tool to be enabled
        List<String> elements = new ArrayList<String>();
        elements.add("<meta content=\"width = device - width, initial - scale = 1\" name=\"viewport\">");
        elements.add("<meta name=\"dcterms.creator\" content=\"[Department name / Nom du département]\">");
        this.setHtmlHeaderElements(elements);

        FeedbackLink feedbackLink = new FeedbackLink();
        feedbackLink.setEnabled(true);
        feedbackLink.setText("Contact");
        feedbackLink.setTextFr("Contactez-nous");
        feedbackLink.setUrl("http://www.google.ca"); 
        feedbackLink.setUrlFr("http://www.google.ca/?hl=fr");
        feedbackLink.setTheme("Theme");
        feedbackLink.setSection("Section");

        this.setFeedbackLink(feedbackLink);
        this.setShowSharePageLink(true);
        this.getSharePageMediaSites().add(Constants.SocialMediaSites.blogger);
        this.getSharePageMediaSites().add(Constants.SocialMediaSites.facebook);
        this.getSharePageMediaSites().add(Constants.SocialMediaSites.twitter);
    }
}
