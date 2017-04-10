package goc.webtemplate.component.jsonentities;

import java.io.Serializable;

import java.util.List;

import goc.webtemplate.FooterLink;
import goc.webtemplate.Link;

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
    private boolean             globalNav;
    private List<FooterLink>    footerSections;
    private List<Link>          contactLinks;
    private String              termsLink;
    private String              privacyLink;
    private boolean             showFeatures;
    
    public AppFooter()
    {
    }

    public AppFooter(String cdnEnv, String subTheme, String localPath, boolean globalNav,
            List<FooterLink> footerSections, List<Link> contactLinks, String termsLink, String privacyLink,
            boolean showFeatures) {
        this.cdnEnv = cdnEnv;
        this.subTheme = subTheme;
        this.localPath = localPath;
        this.globalNav = globalNav;
        this.footerSections = footerSections;
        this.contactLinks = contactLinks;
        this.termsLink = termsLink;
        this.privacyLink = privacyLink;
        this.showFeatures = showFeatures;
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

    public boolean isGlobalNav() {
        return globalNav;
    }

    public void setGlobalNav(boolean globalNav) {
        this.globalNav = globalNav;
    }

    public List<FooterLink> getFooterSections() {
        return footerSections;
    }

    public void setFooterSections(List<FooterLink> footerSections) {
        this.footerSections = footerSections;
    }

    public List<Link> getContactLinks() {
        return contactLinks;
    }

    public void setContactLinks(List<Link> contactLinks) {
        this.contactLinks = contactLinks;
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

    public void setShowFeatures(boolean showFeatures) {
        this.showFeatures = showFeatures;
    }
}
