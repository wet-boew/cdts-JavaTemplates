package goc.webtemplate.jsf.samplebeans;

import goc.webtemplate.Constants;
import goc.webtemplate.component.jsf.DefaultTemplateBean;

public class FeedbackAndShareThisPageSample extends DefaultTemplateBean {

	@Override
	public void setSharePageMediaSites() {
		this.sharePageMediaSites.add(Constants.SocialMediaSites.bitly);
        this.sharePageMediaSites.add(Constants.SocialMediaSites.facebook);
        this.sharePageMediaSites.add(Constants.SocialMediaSites.twitter);
	}

	@Override
	public void setShowFeedbackLink() { this.showFeedbackLink = true; }

	@Override
	public void setFeedbackUrl() { this.feedbackUrl = "http://www.google.ca"; }

	@Override
	public void setShowSharePageLink() { this.showSharePageLink = true; }
}
