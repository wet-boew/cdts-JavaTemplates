package goc.webtemplate.component.jsonentities;

import java.io.Serializable;

import java.util.List;

import goc.webtemplate.FooterLink;

/**
 * Objects of this class are meant to be serialized to a JSON object to be passed
 * as parameter to the 'wet.builder.appFooter' JavaScript function in the template
 * pages. 
 */
public class AppFooter implements Serializable {
    private static final long serialVersionUID = 1L;

    private String              cdnEnv; 
    private String              subTheme;
    private String              localPath;
    private List<FooterLink>    footerSections;
    private String              contactLink;
    private String              termsLink;
    private String              privacyLink;
    private boolean             showFeatures = false; //NOTE: No longer exposed to user/always false (meant to be removed in future)
    
    public AppFooter()
    {
    }

    public AppFooter(String cdnEnv, String subTheme, String localPath,
            List<FooterLink> footerSections, String contactLink, String termsLink, String privacyLink) {
        this.cdnEnv = cdnEnv;
        this.subTheme = subTheme;
        this.localPath = localPath;
        this.footerSections = footerSections;
        this.contactLink = contactLink;
        this.termsLink = termsLink;
        this.privacyLink = privacyLink;
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

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public List<FooterLink> getFooterSections() {
        return footerSections;
    }

    public void setFooterSections(List<FooterLink> footerSections) {
        this.footerSections = footerSections;
    }

    public String getContactLinks() {
        return contactLink;
    }

    public void setContactLinks(String value) {
        this.contactLink = value;
    }

    public String getTermsLink() {
        return termsLink;
    }

    public void setTermsLink(String termsLink) {
        this.termsLink = termsLink;
    }

    public String getPrivacyLink() {
        return privacyLink;
    }

    public void setPrivacyLink(String privacyLink) {
        this.privacyLink = privacyLink;
    }

    public boolean isShowFeatures() {
        return showFeatures;
    }
}
