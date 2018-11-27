package goc.webtemplate.jsf.samplebeans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import goc.webtemplate.Constants;
import goc.webtemplate.component.jsf.DefaultTemplateCoreBean;

@Named("feedbackandsharethispagesamplebean")
@RequestScoped
public class FeedbackAndShareThisPageSample extends DefaultTemplateCoreBean {

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