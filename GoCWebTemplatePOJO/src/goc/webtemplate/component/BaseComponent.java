package goc.webtemplate.component;

import java.util.ArrayList;
import java.util.Date;

import goc.webtemplate.ApplicationTitle;
import goc.webtemplate.Breadcrumb;
import goc.webtemplate.Constants;
import goc.webtemplate.FooterLink;
import goc.webtemplate.LeavingSecureSiteWarning;
import goc.webtemplate.Link;
import goc.webtemplate.MenuSection;
import goc.webtemplate.SessionTimeout;

/**
 * This is the base class that will be shared with either the JSF 
 * or JSP version of the BaseBean class, this class defines the complete 
 * list of abstract SET method that needs to be implemented in either the 
 * JSF or JSP version of the implementation.  The corresponding GET methods 
 * will be used directly in the various master template JSP (.xhtml) or JSP (.jsp) pages.
 *  
 * @author casey.cheung
 * @deprecated THIS CLASS WILL BE DELETED IN AN UPCOMING RELEASE, IT HAS BEEN REPLACED BY AbstractCoreBean
 * @see AbstractCoreBean
 */
@Deprecated
public abstract class BaseComponent extends AbstractCoreBean {
    // ==============================================================
	// List of Public Abstracted methods that needs to be implemented
	// ==============================================================
	public abstract void setCDNEnvironment();
	public abstract void setTemplateVersion();
	public abstract void setTheme();
	public abstract void setSubTheme();
	public abstract void setApplicationTitleText();
	public abstract void setApplicationTitleUrl();
	public abstract void setUseHttps();
	public abstract void setLoadjQueryFromGoogle();
	public abstract void setHeaderTitle();
    public abstract void setShowSearch();
    public abstract void setLanguageLinkUrl();
    public abstract void setShowPreContent();
    public abstract void setBreadcrumbsList();
    public abstract void setLeavingSecureSiteWarningEnabled();
    public abstract void setLeavingSecureSiteWarningMessage();
    public abstract void setLeavingSecureSiteRedirectUrl();
    public abstract void setLeavingSecureSiteExcludedDomains();
    public abstract void setLeavingSecureSiteDisplayModalWindow();
    public abstract void setDateModified();
    public abstract void setScreenIdentifier();
    public abstract void setVersionIdentifier();
    public abstract void setShowPostContent();
    public abstract void setShowFeedbackLink();
    public abstract void setFeedbackUrl();
    public abstract void setShowSharePageLink();
    public abstract void setSharePageMediaSites();
    public abstract void setShowFeature();
    public abstract void setContactLinkUrl();
    /**
     * @deprecated contactLinks should not be overriden. It has been replaced by contactLinkUrl.  WILL BE REMOVED IN A FUTURE RELEASE.
     * @see #setContactLinkUrl()
     */
    @Deprecated
    public abstract void setContactLinks();
    /**
     * @deprecated newLinks should not be overriden as it is no longer used anywhere.  WILL BE REMOVED IN A FUTURE RELEASE.
     */
    @Deprecated
    public abstract void setNewsLinks();
    /**
     * @deprecated aboutLinks should not be overriden as it is no longer used anywhere.  WILL BE REMOVED IN A FUTURE RELEASE.
     */
    @Deprecated
    public abstract void setAboutLinks();
    public abstract void setHtmlHeaderElements();
    public abstract void setHtmlBodyElements();
    public abstract void setStaticFallbackFilePath();
    public abstract void setSessionTimeoutEnabled();
    public abstract void setSessionTimeoutConfigurations();
    public abstract void setShowLanguageLink();
    public abstract void setLeftMenuSections();
    public abstract void setPrivacyLinkUrl();
    public abstract void setTermsConditionsLinkUrl();
    public abstract void setShowGlobalNav();
    public abstract void setShowSiteMenu();
    public abstract void setCustomSiteMenuUrl();
    public abstract void setSignInLinkUrl();
    public abstract void setSignOutLinkUrl();
    public abstract void setShowSecureIcon();
    public abstract void setShowSignInLink();
    public abstract void setShowSignOutLink();
    public abstract void setCustomFooterLinks();
    public abstract void setCustomSearch();

	// ==============================================================
	// List of Protected Abstract instance variables available to either 
	// the JSF or JSP implmementation
	// ==============================================================
	protected String cdnEnvironment = this.getResourceBundleString("cdn", "cdn_environment");
	protected String templateVersion = this.getResourceBundleString("cdn", "wettemplate_version");
	protected String mainTheme = this.getResourceBundleString("cdn", "wettemplate_theme");
	protected String subTheme = this.getResourceBundleString("cdn", "wettemplate_subtheme");
	protected boolean useHttps = Boolean.parseBoolean(this.getResourceBundleString("cdn", "webtemplate_usehttps"));
	protected boolean loadjQueryFromGoogle = Boolean.parseBoolean(this.getResourceBundleString("cdn", "wettemplate_loadjqueryfromgoogle"));
	protected String cdnLocalPathRender = null; //see getRenderLocalPath
	protected String cdnLocalPath = null;
	protected String headerTitle = "";
	protected String applicationTitleText = "";
	protected String applicationTitleUrl = "";
	protected boolean showSearch = Boolean.parseBoolean(this.getResourceBundleString("cdn", "goc.webtemplate.showsearch"));
    protected String langLinkUrl = "";
    protected boolean showPreContent = Boolean.parseBoolean(this.getResourceBundleString("cdn", "goc.webtemplate.showprecontent"));
    protected boolean leavingSecureSiteWarningEnabled = Boolean.parseBoolean(this.getResourceBundleString("cdn", "leavingsecuresitewarning.enabled"));
    protected String leavingSecureSiteWarningMessage = "";
    protected String leavingSecureSiteRedirectUrl = this.getResourceBundleString("cdn", "leavingsecuresitewarning.redirecturl");
    protected String leavingSecureSiteExcludedDomain = this.getResourceBundleString("cdn", "leavingsecuresitewarning.excludeddomains");
    protected boolean leavingSecureSiteDisplayModalWindow = Boolean.parseBoolean(this.getResourceBundleString("cdn", "leavingsecuresitewarning.displaymodalwindow"));
    protected Date dateModified = null;
    protected String screenIdentifier = "";
    protected String versionIdentifier = "";
    protected boolean showLanguageLink = Boolean.parseBoolean(this.getResourceBundleString("cdn", "goc.webtemplate.showlanguagelink"));
    protected boolean showPostContent = Boolean.parseBoolean(this.getResourceBundleString("cdn", "goc.webtemplate.showpostcontent"));
    protected boolean showFeedbackLink = Boolean.parseBoolean(this.getResourceBundleString("cdn", "goc.webtemplate.showfeedbacklink"));
    protected String feedbackUrl = this.getResourceBundleString("cdn", "goc.webtemplate.feedbackurl");
    protected boolean showSharePageLink = Boolean.parseBoolean(this.getResourceBundleString("cdn", "goc.webtemplate.showsharepagelink"));
    protected ArrayList<Constants.SocialMediaSites> sharePageMediaSites = new ArrayList<Constants.SocialMediaSites>();
    protected boolean showFeature = Boolean.parseBoolean(this.getResourceBundleString("cdn", "goc.webtemplate.showfeatures"));
    protected ArrayList<Breadcrumb> breadCrumbsList = new ArrayList<Breadcrumb>();
    protected String contactLinkUrl = null;
    /**
     * @deprecated contactLinks should not be overriden. It has been replaced by contactLinkUrl. WILL BE REMOVED IN A FUTURE RELEASE.
     * @see #contactLinkUrl()
     */
    @Deprecated
    protected ArrayList<Link> contactLinks = new ArrayList<Link>();
    /**
     * @deprecated newLinks should not be overriden as it is no longer used anywhere.  WILL BE REMOVED IN A FUTURE RELEASE.
     */
    @Deprecated
    protected ArrayList<Link> newsLinks = new ArrayList<Link>();
    /**
     * @deprecated aboutLinks should not be overriden as it is no longer used anywhere.  WILL BE REMOVED IN A FUTURE RELEASE.
     */
    @Deprecated
    protected ArrayList<Link> aboutLinks = new ArrayList<Link>();
    protected ArrayList<String> htmlHeaderElements = new ArrayList<String>();
    protected ArrayList<String> htmlBodyElements = new ArrayList<String>();
    protected String staticFilePath = this.getResourceBundleString("cdn", "goc.webtemplate.staticfileslocation");    
    protected String contentCreatorTitle = "";
    protected boolean showGlobalNav = Boolean.parseBoolean(this.getResourceBundleString("cdn", "goc.webtemplate.showglobalnav"));
    protected boolean showSiteMenu = Boolean.parseBoolean(this.getResourceBundleString("cdn", "goc.webtemplate.showsitemenu"));
    protected String  customSiteMenuUrl = this.getResourceBundleString("cdn", "goc.webtemplate.customsitemenuurl");
    protected String  signInLinkUrl = this.getResourceBundleString("cdn", "goc.webtemplate.signinlinkurl");
    protected String  signOutLinkUrl = this.getResourceBundleString("cdn", "goc.webtemplate.signoutlinkurl");
    protected boolean showSecureIcon = false;
    protected boolean showSignInLink = false;
    protected boolean showSignOutLink = false;
    protected ArrayList<FooterLink> customFooterLinks = new ArrayList<FooterLink>();
    /**
     * Allows for a custom search to be used in the application, you must contact CDTS to have one created.
     */
    protected String customSearch = this.getResourceBundleString("cdn", "goc.webtemplate.customsearch");;
    
    protected boolean sessionTimeoutEnabled = Boolean.parseBoolean(this.getResourceBundleString("cdn", "session.timeout.enabled"));
    protected SessionTimeout sessionTimeoutConfigurations = null;
    
    protected ArrayList<MenuSection> leftMenuSections = new ArrayList<MenuSection>();
    protected String privacyLinkUrl = "";
    protected String termsConditionsLinkUrl = "";
 
    
    /**
     * This method "converts" the new way of overriding values into the old way, 
     * as a stop-gad measure to support old code.
     */
    @Override
    public void onWebTemplateInitialize() {
        //---[ First, call ALL (non preivously deprecated) override methods
        this.setCDNEnvironment();
        this.setTemplateVersion();
        this.setTheme();
        this.setSubTheme();
        this.setApplicationTitleText();
        this.setApplicationTitleUrl();
        this.setUseHttps();
        this.setLoadjQueryFromGoogle();
        this.setHeaderTitle();
        this.setShowSearch();
        this.setLanguageLinkUrl();
        this.setShowPreContent();
        this.setBreadcrumbsList();
        this.setLeavingSecureSiteWarningEnabled();
        this.setLeavingSecureSiteWarningMessage();
        this.setLeavingSecureSiteRedirectUrl();
        this.setLeavingSecureSiteExcludedDomains();
        this.setLeavingSecureSiteDisplayModalWindow();
        this.setDateModified();
        this.setScreenIdentifier();
        this.setVersionIdentifier();
        this.setShowPostContent();
        this.setShowFeedbackLink();
        this.setFeedbackUrl();
        this.setShowSharePageLink();
        this.setSharePageMediaSites();
        this.setShowFeature();
        this.setContactLinkUrl();
        this.setHtmlHeaderElements();
        this.setHtmlBodyElements();
        this.setStaticFallbackFilePath();
        this.setSessionTimeoutEnabled();
        this.setSessionTimeoutConfigurations();
        this.setShowLanguageLink();
        this.setLeftMenuSections();
        this.setPrivacyLinkUrl();
        this.setTermsConditionsLinkUrl();
        this.setShowGlobalNav();
        this.setShowSiteMenu();
        this.setCustomSiteMenuUrl();
        this.setSignInLinkUrl();
        this.setSignOutLinkUrl();
        this.setShowSecureIcon();
        this.setShowSignInLink();
        this.setShowSignOutLink();
        this.setCustomFooterLinks();
        this.setCustomSearch();
        
        //---[ Set set all values from our variables that were (potentially overriden)
        this.setCDNEnvironment(this.cdnEnvironment);
        this.setTemplateVersion(this.templateVersion);
        this.setTheme(this.mainTheme);
        this.setSubTheme(this.subTheme);
        this.setUseHttps(this.useHttps);
        this.setLoadJQueryFromGoogle(this.loadjQueryFromGoogle);
        this.setHeaderTitle(this.headerTitle);
        this.setApplicationTitle(new ApplicationTitle(this.applicationTitleText, this.applicationTitleUrl));
        this.setShowSearch(this.showSearch);
        this.setLanguageLinkUrl(this.langLinkUrl);
        this.setShowPreContent(this.showPreContent);
        this.setLeavingSecureSiteWarning(new LeavingSecureSiteWarning(this.leavingSecureSiteWarningEnabled, 
                                                                      this.leavingSecureSiteDisplayModalWindow, 
                                                                      this.leavingSecureSiteWarningMessage, 
                                                                      this.leavingSecureSiteRedirectUrl, 
                                                                      this.leavingSecureSiteExcludedDomain));
        this.setDateModified(this.dateModified);
        this.setScreenIdentifier(this.screenIdentifier);
        this.setVersionIdentifier(this.versionIdentifier);
        this.setShowLanguageLink(this.showLanguageLink);
        this.setShowPostContent(this.showPostContent);
        this.setShowFeedbackLink(this.showFeedbackLink);
        this.setFeedbackUrl(this.feedbackUrl);
        this.setShowSharePageLink(this.showSharePageLink);
        this.setSharePageMediaSites(this.sharePageMediaSites);
        this.setShowFeature(this.showFeature);
        this.setBreadcrumbs(this.breadCrumbsList);
        this.setContactLinkUrl(this.contactLinkUrl);
        this.setHtmlHeaderElements(this.htmlHeaderElements);
        this.setHtmlBodyElements(this.htmlBodyElements);
        this.setStaticFallbackFilePath(this.staticFilePath);
        this.setShowGlobalNav(this.showGlobalNav);
        this.setShowSiteMenu(this.showSiteMenu);
        this.setCustomSiteMenuUrl(this.customSiteMenuUrl);
        this.setSignInLinkUrl(this.signInLinkUrl);
        this.setSignOutLinkUrl(this.signOutLinkUrl);
        this.setShowSecureIcon(this.showSecureIcon);
        this.setShowSignInLink(this.showSignInLink);
        this.setShowSignOutLink(this.showSignOutLink);
        this.setCustomFooterLinks(this.customFooterLinks);
        this.setCustomSearch(this.customSearch);
        if (this.sessionTimeoutConfigurations == null) {
            if (this.sessionTimeoutEnabled) this.getSessionTimeoutConfiguration().setEnabled(true); //this will load all defaults and set enabled
        } else {
            this.sessionTimeoutConfigurations.setEnabled(this.sessionTimeoutEnabled);
            this.setSessionTimeoutConfiguration(this.sessionTimeoutConfigurations);
        }
        this.setLeftMenuSections(this.leftMenuSections);
        this.setPrivacyLinkUrl(this.privacyLinkUrl);
        this.setTermsConditionsLinkUrl(this.termsConditionsLinkUrl);
    }
}
