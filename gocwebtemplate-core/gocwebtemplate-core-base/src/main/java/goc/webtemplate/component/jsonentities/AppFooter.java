package goc.webtemplate.component.jsonentities;

import java.io.Serializable;

import java.util.List;

import goc.webtemplate.FooterLink;
import goc.webtemplate.IFooterSection;
import goc.webtemplate.Link;

/**
 * Objects of this class are meant to be serialized to a JSON object to be passed
 * as parameter to the 'wet.builder.appFooter' JavaScript function in the template
 * pages. 
 */
public class AppFooter implements Serializable {
    private static final long serialVersionUID = 1L;

    private String              	cdnEnv; 
    private String              	subTheme;
    private String              	localPath;
    private List<IFooterSection>    footerSections;
    private List<Link>          	contactLink; //NOTE: Name not being plural is intentional/what CDTS requires
    private List<FooterLink>        termsLink;
    private List<FooterLink>        privacyLink;
    private boolean             	showFeatures;
    
    public AppFooter()
    {
    }

    public AppFooter(String cdnEnv, String subTheme, String localPath,
            List<IFooterSection> footerSections, List<Link> contactLink, 
            List<FooterLink> termsLink, List<FooterLink> privacyLink, boolean showFeatures) {
        this.cdnEnv = cdnEnv;
        this.subTheme = subTheme;
        this.localPath = localPath;
        this.footerSections = footerSections;
        this.contactLink = contactLink;
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

    public List<IFooterSection> getFooterSections() {
        return footerSections;
    }

    public void setFooterSections(List<IFooterSection> footerSections) {
        this.footerSections = footerSections;
    }

    public List<Link> getContactLinks() {
        return contactLink;
    }

    public void setContactLinks(List<Link> value) {
        this.contactLink = value;
    }

    public List<FooterLink> getTermsLink() {
        return termsLink;
    }

    public void setTermsLink(List<FooterLink> termsLink) {
        this.termsLink = termsLink;
    }

    public List<FooterLink> getPrivacyLink() {
        return privacyLink;
    }

    public void setPrivacyLink(List<FooterLink> privacyLink) {
        this.privacyLink = privacyLink;
    }

    public boolean isShowFeatures() {
        return showFeatures;
    }
    
    public void setShowFeatures(boolean showFeatures) {
        this.showFeatures = showFeatures;
    }
}
