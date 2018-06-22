package goc.webtemplate.component.jsonentities;

import java.io.Serializable;

import java.util.List;

import goc.webtemplate.Link;

/**
 * Objects of this class are meant to be serialized to a JSON object to be passed
 * as parameter to the 'wet.builder.footer' JavaScript function in the template
 * pages. 
 */
public class Footer implements Serializable {
    private static final long serialVersionUID = 1L;

    private String              cdnEnv;
    private String              subTheme;
    private boolean             showFooter;
    private boolean             showFeatures = false; //NOTE: No longer exposed to user/always false (meant to be removed in future)
    private List<Link>          contactLinks;
    private String              privacyLink;
    private String              termsLink;
    private String              localPath;

    public Footer() {
    }

    public Footer(String cdnEnv, String subTheme, boolean showFooter, List<Link> contactLinks,
            String privacyLink, String termsLink, String localPath) {
        this.cdnEnv = cdnEnv;
        this.subTheme = subTheme;
        this.showFooter = showFooter;
        this.contactLinks = contactLinks;
        this.privacyLink = privacyLink;
        this.termsLink = termsLink;
        this.localPath = localPath;
    }

    public String getCdnEnv() {
        return cdnEnv;
    }

    public void setCdnEnv(String cdnEnv) {
        this.cdnEnv = cdnEnv;
    }

    public String getSubTheme() {
        return subTheme;
    }

    public void setSubTheme(String subTheme) {
        this.subTheme = subTheme;
    }

    public boolean isShowFooter() {
        return showFooter;
    }

    public void setShowFooter(boolean showFooter) {
        this.showFooter = showFooter;
    }

    public boolean isShowFeatures() {
        return showFeatures;
    }

    public List<Link> getContactLinks() {
        return contactLinks;
    }

    public void setContactLinks(List<Link> contactLinks) {
        this.contactLinks = contactLinks;
    }

    public String getPrivacyLink() {
        return privacyLink;
    }

    public void setPrivacyLink(String privacyLink) {
        this.privacyLink = privacyLink;
    }

    public String getTermsLink() {
        return termsLink;
    }

    public void setTermsLink(String termsLink) {
        this.termsLink = termsLink;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }
}
