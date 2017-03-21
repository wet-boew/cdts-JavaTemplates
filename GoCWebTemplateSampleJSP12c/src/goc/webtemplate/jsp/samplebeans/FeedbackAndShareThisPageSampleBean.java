package goc.webtemplate.jsp.samplebeans;

import goc.webtemplate.Constants;
import goc.webtemplate.component.jsp.DefaultTemplateBean;

public class FeedbackAndShareThisPageSampleBean extends DefaultTemplateBean {

	@Override
	public void setShowFeedbackLink() {
		this.showFeedbackLink = true;
	}

	@Override
	public void setFeedbackUrl() { this.feedbackUrl = "http://www.google.ca"; }
	
	@Override
	public void setShowSharePageLink() {
		this.showSharePageLink = true;
	}

	@Override
	public void setSharePageMediaSites() {
		this.sharePageMediaSites.add(Constants.SocialMediaSites.bitly);
        this.sharePageMediaSites.add(Constants.SocialMediaSites.facebook);
        this.sharePageMediaSites.add(Constants.SocialMediaSites.twitter);
	}
}
