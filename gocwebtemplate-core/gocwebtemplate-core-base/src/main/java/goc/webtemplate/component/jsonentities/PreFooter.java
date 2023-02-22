package goc.webtemplate.component.jsonentities;

import java.io.Serializable;

import com.google.gson.annotations.JsonAdapter;

/**
 * Objects of this class are meant to be serialized to a JSON object to be passed
 * as parameter to the 'wet.builder.preFooter' JavaScript function in the template
 * pages.
 */
public class PreFooter implements Serializable, IPreFooter {
    private static final long serialVersionUID = 1L;

    private String              cdnEnv;
    private String              versionIdentifier;
    private String              dateModified;
    private boolean             showPostContent;

    //NOTE: Custom serialization/adapter because value can be both boolean and string
    @JsonAdapter(goc.webtemplate.component.jsonentities.adapters.FeedbackLinkAdapter.class)
    private FeedbackLink        showFeedback;

    //NOTE: Custom serialization/adapter because value can be both boolean and array of string
    @JsonAdapter(goc.webtemplate.component.jsonentities.adapters.ShareListAdapter.class)
    private ShareList           showShare;

    private String              screenIdentifier;


    public PreFooter() {
    }

    public PreFooter(String cdnEnv, String versionIdentifier, String dateModified, boolean showPostContent,
            FeedbackLink showFeedback, ShareList showShare, String screenIdentifier) {
        this.cdnEnv = cdnEnv;
        this.versionIdentifier = versionIdentifier;
        this.dateModified = dateModified;
        this.showPostContent = showPostContent;
        this.showFeedback = showFeedback;
        this.showShare = showShare;
        this.screenIdentifier = screenIdentifier;
    }

    public String getCdnEnv() {
        return cdnEnv;
    }

    public void setCdnEnv(String cdnEnv) {
        this.cdnEnv = cdnEnv;
    }

    public String getVersionIdentifier() {
        return versionIdentifier;
    }

    public void setVersionIdentifier(String versionIdentifier) {
        this.versionIdentifier = versionIdentifier;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    public boolean isShowPostContent() {
        return showPostContent;
    }

    public void setShowPostContent(boolean showPostContent) {
        this.showPostContent = showPostContent;
    }

    public FeedbackLink getShowFeedback() {
        return showFeedback;
    }

    public void setShowFeedback(FeedbackLink showFeedback) {
        this.showFeedback = showFeedback;
    }

    public ShareList getShowShare() {
        return showShare;
    }

    public void setShowShare(ShareList showShare) {
        this.showShare = showShare;
    }

    public String getScreenIdentifier() {
        return screenIdentifier;
    }

    public void setScreenIdentifier(String screenIdentifier) {
        this.screenIdentifier = screenIdentifier;
    }
}
