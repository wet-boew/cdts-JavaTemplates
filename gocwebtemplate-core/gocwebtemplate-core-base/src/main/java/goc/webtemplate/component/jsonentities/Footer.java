package goc.webtemplate.component.jsonentities;

import java.io.Serializable;

import java.util.List;

import com.google.gson.annotations.JsonAdapter;

import goc.webtemplate.ContextualFooter;
import goc.webtemplate.FooterLink;
import goc.webtemplate.FooterLinkContext;
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
    private boolean             showFeatures;
    private List<Link>          contactLinks;

    //NOTE: Custom serialization/adapter because value can be both a footer link and a list of footer links
    @JsonAdapter(goc.webtemplate.component.jsonentities.adapters.FooterLinkAdapter.class)
    private FooterLinkContext   privacyLink;

    //NOTE: Custom serialization/adapter because value can be both a footer link and a list of footer links
    @JsonAdapter(goc.webtemplate.component.jsonentities.adapters.FooterLinkAdapter.class)    
    private FooterLinkContext   termsLink;

    private String              localPath;
    private ContextualFooter    contextualFooter;
    private boolean             hideFooterMain;
    private boolean             hideFooterCorporate;

    public Footer() {
    }

    public Footer(String cdnEnv, String subTheme, boolean showFooter, boolean showFeatures, List<Link> contactLinks,
            FooterLinkContext privacyLink, FooterLinkContext termsLink, String localPath, ContextualFooter contextualFooter, boolean hideFooterMain, boolean hideFooterCorporate) {
        this.cdnEnv = cdnEnv;
        this.subTheme = subTheme;
        this.showFooter = showFooter;
        this.showFeatures = showFeatures;
        this.contactLinks = contactLinks;
        this.privacyLink = privacyLink;
        this.termsLink = termsLink;
        this.localPath = localPath;
        this.contextualFooter = contextualFooter;
        this.hideFooterMain = hideFooterMain;
        this.hideFooterCorporate = hideFooterCorporate;
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
    
    public void setShowFeatures(boolean showFeatures) {
        this.showFeatures = showFeatures;
    }

    public List<Link> getContactLinks() {
        return contactLinks;
    }

    public void setContactLinks(List<Link> contactLinks) {
        this.contactLinks = contactLinks;
    }

    public FooterLinkContext getPrivacyLink() {
        return privacyLink;
    }

    public void setPrivacyLink(FooterLinkContext privacyLink) {
        this.privacyLink = privacyLink;
    }

    public FooterLinkContext getTermsLink() {
        return termsLink;
    }

    public void setTermsLink(FooterLinkContext termsLink) {
        this.termsLink = termsLink;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public ContextualFooter getContextualFooter() {
        return contextualFooter;
    }

    public void setContextualFooter(ContextualFooter contextualFooter) {
        this.contextualFooter = contextualFooter;
    }

    public boolean isHideFooterMain() {
        return hideFooterMain;
    }

    public void setHideFooterMain(boolean hideFooterMain) {
        this.hideFooterMain = hideFooterMain;
    }

    public boolean isHideFooterCorporate() {
        return hideFooterCorporate;
    }

    public void setHideFooterCorporate(boolean hideFooterCorporate) {
        this.hideFooterCorporate = hideFooterCorporate;
    }
}
