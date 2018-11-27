package goc.webtemplate.jsp.samplebeans;

import goc.webtemplate.Constants;

import goc.webtemplate.component.jsp.DefaultTemplateCoreBean;

public class FeedbackAndShareThisPageSampleBean extends DefaultTemplateCoreBean {

    @Override
    public void onWebTemplateInitialize() {
        this.setShowFeedbackLink(true);
        this.setFeedbackUrl("http://www.google.ca"); 

        this.setShowSharePageLink(true);
        this.getSharePageMediaSites().add(Constants.SocialMediaSites.bitly);
        this.getSharePageMediaSites().add(Constants.SocialMediaSites.facebook);
        this.getSharePageMediaSites().add(Constants.SocialMediaSites.twitter);
    }
}