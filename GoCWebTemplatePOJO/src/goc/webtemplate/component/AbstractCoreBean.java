package goc.webtemplate.component;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.MissingResourceException;

import org.apache.commons.lang3.StringEscapeUtils;

import com.google.gson.Gson;

import goc.webtemplate.ApplicationTitle;
import goc.webtemplate.Breadcrumb;
import goc.webtemplate.Constants;
import goc.webtemplate.FooterLink;
import goc.webtemplate.LanguageLink;
import goc.webtemplate.LeavingSecureSiteWarning;
import goc.webtemplate.Link;
import goc.webtemplate.MenuItem;
import goc.webtemplate.MenuSection;
import goc.webtemplate.SessionTimeout;
import goc.webtemplate.Utility;

import goc.webtemplate.component.jsonentities.AppFooter;
import goc.webtemplate.component.jsonentities.AppTop;

/**
 * This is the base class from all template configuration beans, inherited by both
 * the JSF or JSP version of the BaseCoreBean class.
 * 
 * This class defines the complete list of configuration/customization items 
 * that can be overriden in either the JSF or JSP version of the implementation.
 * 
 * @author pierre.lupien
 *
 */
public abstract class AbstractCoreBean {
    /**
     * Object used for JSON serialization.  (https://github.com/google/gson)
     * 
     * According to documentation (http://www.javadoc.io/doc/com.google.code.gson/gson/2.8.0) 
     * and source code, Gson objects are thread-safe.
     */
    //NOTE: Doesn't render null values by default, which is what we want
    //NOTE: Escapes HTML by default, which is what we want (though URLs still need to be encoded)
    //NOTE: Indented output can be obtained by chaining a call to setPrettyPrinting()
    private static Gson gson = new com.google.gson.GsonBuilder()
                                        .setFieldNamingPolicy(com.google.gson.FieldNamingPolicy.IDENTITY)
                                        .create();

    /**
     * SimpleDateFormat object used to format dateModified-type dates.
     */
    private static ThreadLocal<SimpleDateFormat>    dateModifiedFormat = new ThreadLocal<SimpleDateFormat>() {
                                                        @Override
                                                        protected SimpleDateFormat initialValue() {
                                                          return new SimpleDateFormat("yyyy-MM-dd");
                                                        }
                                                    };
    
    /**
     * Flag indicating whether the onWebTemplateInitialize has already been called. 
     */
    private boolean initialized = false;    
    
    //-------------------------------------------------------
    //---[ Main template instance variables
    //-------------------------------------------------------
    private String cdnEnvironment = this.getResourceBundleString("cdn", "cdn_environment");
    private String cdtsCdnEnv = null; // initialized in getCdtsCdnEnv
    private String templateVersion = this.getResourceBundleString("cdn", "wettemplate_version");
    private String theme = this.getResourceBundleString("cdn", "wettemplate_theme");
    private String subTheme = this.getResourceBundleString("cdn", "wettemplate_subtheme");
    private boolean useHttps = Boolean.parseBoolean(this.getResourceBundleString("cdn", "webtemplate_usehttps"));
    private boolean loadjQueryFromGoogle = Boolean.parseBoolean(this.getResourceBundleString("cdn", "wettemplate_loadjqueryfromgoogle"));
    private String cdnLocalPath = null;
    private String headerTitle = "";
    private ApplicationTitle applicationTitle = new ApplicationTitle();
    private String languageLinkUrl = "";
    private String feedbackUrl = this.getResourceBundleString("cdn", "goc.webtemplate.feedbackurl");
    private String contactLinkUrl = null;
    private LeavingSecureSiteWarning leavingSecureSiteWarning = new LeavingSecureSiteWarning(
                                            Boolean.parseBoolean(this.getResourceBundleString("cdn", "leavingsecuresitewarning.enabled")),
                                            Boolean.parseBoolean(this.getResourceBundleString("cdn", "leavingsecuresitewarning.displaymodalwindow")),
                                            "",
                                            this.getResourceBundleString("cdn", "leavingsecuresitewarning.redirecturl"),
                                            this.getResourceBundleString("cdn", "leavingsecuresitewarning.excludeddomains"));
    private Date dateModified = null;
    private String screenIdentifier = "";
    private String versionIdentifier = "";
    private boolean showSearch = Boolean.parseBoolean(this.getResourceBundleString("cdn", "goc.webtemplate.showsearch"));
    private boolean showPreContent = Boolean.parseBoolean(this.getResourceBundleString("cdn", "goc.webtemplate.showprecontent"));
    private boolean showPostContent = Boolean.parseBoolean(this.getResourceBundleString("cdn", "goc.webtemplate.showpostcontent"));
    private boolean showLanguageLink = Boolean.parseBoolean(this.getResourceBundleString("cdn", "goc.webtemplate.showlanguagelink"));
    private boolean showFeedbackLink = Boolean.parseBoolean(this.getResourceBundleString("cdn", "goc.webtemplate.showfeedbacklink"));
    private boolean showSharePageLink = Boolean.parseBoolean(this.getResourceBundleString("cdn", "goc.webtemplate.showsharepagelink"));
    private boolean showFeature = Boolean.parseBoolean(this.getResourceBundleString("cdn", "goc.webtemplate.showfeatures"));
    private ArrayList<Constants.SocialMediaSites> sharePageMediaSites = new ArrayList<Constants.SocialMediaSites>();
    private ArrayList<Breadcrumb> breadcrumbs = new ArrayList<Breadcrumb>();
    private ArrayList<String> htmlHeaderElements = new ArrayList<String>();
    private ArrayList<String> htmlBodyElements = new ArrayList<String>();
    private String staticFilePath = this.getResourceBundleString("cdn", "goc.webtemplate.staticfileslocation");    
    private boolean showGlobalNav = Boolean.parseBoolean(this.getResourceBundleString("cdn", "goc.webtemplate.showglobalnav"));
    private boolean showSiteMenu = Boolean.parseBoolean(this.getResourceBundleString("cdn", "goc.webtemplate.showsitemenu"));
    private String  customSiteMenuUrl = this.getResourceBundleString("cdn", "goc.webtemplate.customsitemenuurl");
    private String  signInLinkUrl = this.getResourceBundleString("cdn", "goc.webtemplate.signinlinkurl");
    private String  signOutLinkUrl = this.getResourceBundleString("cdn", "goc.webtemplate.signoutlinkurl");
    private boolean showSecureIcon = false;
    private boolean showSignInLink = false;
    private boolean showSignOutLink = false;
    private ArrayList<FooterLink> customFooterLinks = new ArrayList<FooterLink>();
    private String customSearch = this.getResourceBundleString("cdn", "goc.webtemplate.customsearch");;
    private SessionTimeout sessionTimeoutConfigurations = null; //initialization in get method 
    private ArrayList<MenuSection> leftMenuSections = new ArrayList<MenuSection>();
    private String privacyLinkUrl = "";
    private String termsConditionsLinkUrl = "";
    //-------------------------------------------------------

    //-------------------------------------------------------
    //---[ Caching of calculated values
    //-------------------------------------------------------
    private String partialCDNPath = null;
    private String cdnLocalPathRender = null; //see getRenderLocalPath
    //-------------------------------------------------------
    
    //-------------------------------------------------------
    //---[ Methods with framework-dependant implementation
    //-------------------------------------------------------
    public abstract java.util.ResourceBundle getResourceBundle();
    public abstract String getResourceBundleString(String resourceBundleName, String resourceBundleKey) throws MissingResourceException;
    public abstract String getTwoLetterCultureLanguage();   
    protected abstract String getDefaultLanguageLinkUrl();
    protected abstract String getDefaultLeaveSecureSiteRedirectUrl();
    //-------------------------------------------------------
    
    /**
     * This method will be called the first time a web template-related configuration
     * property is read. Child classes implement this method to override web template
     * configuration defined in cdn.properties on a bean basis
     */
    public abstract void onWebTemplateInitialize();
    
    private void initializeOnce() {
        if (!this.initialized) {
            this.initialized = true; //do it BEFORE calling onWebTemplateInitialize to avoid endless loops
            this.onWebTemplateInitialize();
        }
    }
    
    /**
     * Returns the environment used (akamai, ESDCPRod, ESDCNonProd, etc)
     * 
     * The environment provided will determine the CDTS that will be used (url and cdnenv)
     * 
     * Set at application level via "cdn_environment" property in cdn.properties, 
     * can be overriden programatically.  
     */
    public String getCDNEnvironment() {
        this.initializeOnce();
        return (Utility.isNullOrEmpty(this.cdnEnvironment) ? "Akamai" : StringEscapeUtils.escapeHtml4(this.cdnEnvironment));
    }
    
    /**
     * Sets the environment used (akamai, ESDCPRod, ESDCNonProd, etc)
     * 
     * The environment provided will determine the CDTS that will be used (url and cdnenv)
     * 
     * Set at application level via "cdn_environment" property in cdn.properties, 
     * can be overriden programatically.  
     */
    public void setCDNEnvironment(String value) {
        this.cdnEnvironment = value;
    }

    /**
     * Returns the "cdnEnv" value that the closure template is expecting. This value 
     * is different than the "CDN Evironment" used to select which CDTS is targetted.
     * 
     * Set at application level via "cdn_????_env" in cdn.properties,
     * can be overriden programmatically.
     */
    public String getCdtsCdnEnv() {
        this.initializeOnce();
        if (this.cdtsCdnEnv == null)this.cdtsCdnEnv = this.getResourceBundleString("cdn", "cdn_" + this.getCDNEnvironment().toLowerCase() + "_env");
        return (Utility.isNullOrEmpty(this.cdtsCdnEnv) ? "prod" : this.cdtsCdnEnv);
    }
    
    /**
     * Sets the "cdnEnv" value that the closure template is expecting. This value 
     * is different than the "CDN Evironment" used to select which CDTS is targetted.
     * 
     * Set at application level via "cdn_????_env" in cdn.properties,
     * can be overriden programmatically.
     */
    public void setCdtsCdnEnv(String value) {
        this.cdtsCdnEnv = value;
    }
    
    /**
     * Returns the path to the location of the static backup files.
     * 
     * Set at application level via "goc.webtemplate.staticfileslocation" property in cdn.properties, 
     * can be overriden programatically.  
     * 
     * The default value point to the "StaticFallbackFiles" directory under 
     * "WebContent" in the eclipse project.
     */
    public String getStaticFallbackFilePath() {
        this.initializeOnce();
        return StringEscapeUtils.escapeHtml4(this.staticFilePath); //TODO: Escaping will no longer be needed once/if value is used only with JSON serialization
    }

    /**
     * Sets the path to the location of the static backup files.
     * 
     * Set at application level via "goc.webtemplate.staticfileslocation" property in cdn.properties, 
     * can be overriden programatically.  
     * 
     * The default value point to the "StaticFallbackFiles" directory under 
     * "WebContent" in the eclipse project.
     */
    public void setStaticFallbackFilePath(String value) {
        this.staticFilePath = value;
    }        
    
    /**
     * Returns whether or not the communication between the browser and the CDTS should be encrypted.
     * 
     * Set at application level via "webtemplate_usehttps" property in cdn.properties, 
     * can be overriden programatically.  
     */
    public boolean getUseHttps() {
        this.initializeOnce();
        return this.useHttps;
    }
    
    /**
     * Sets whether or not the communication between the browser and the CDTS should be encrypted.
     * 
     * Set at application level via "webtemplate_usehttps" property in cdn.properties, 
     * can be overriden programatically.  
     */
    public void setUseHttps(boolean value) {
        this.useHttps = value;
    }
    
    /**
     * Returns the version of the CDN files to use to build the page. (e.g 4.0.17)
     * 
     * Set at application level via "wettemplate_version" property in cdn.properties, 
     * can be overriden programatically.  
     */
    public String getTemplateVersion() {
        this.initializeOnce();
        return StringEscapeUtils.escapeHtml4(this.templateVersion);
    }

    /**
     * Sets the version of the CDN files to use to build the page. (e.g 4.0.17)
     * 
     * Set at application level via "wettemplate_version" property in cdn.properties, 
     * can be overriden programatically.  
     */
    public void setTemplateVersion(String value) {
        this.templateVersion = value;
    }

    /**
     * Returns the theme to use to build the page. (e.g. gcweb)
     * 
     * Set at application level via "wettemplate_theme" property in cdn.properties, 
     * can be overriden programatically.  
     */
    public String getTheme() {
        this.initializeOnce();
        return (!Utility.isNullOrEmpty(this.theme) ? StringEscapeUtils.escapeHtml4(this.theme) : "");
    }

    /**
     * Sets the theme to use to build the page. (e.g. gcweb)
     * 
     * Set at application level via "wettemplate_theme" property in cdn.properties, 
     * can be overriden programatically.  
     */
    public void setTheme(String value) {
        this.theme = value; 
    }
        
    /**
     * Returns the sub-theme to use to build the page. (e.g. esdc)
     * 
     * Set at application level via "wettemplate_subtheme" property in cdn.properties, 
     * can be overriden programatically.  
     */
    public String getSubTheme() {
        this.initializeOnce();
        return (!Utility.isNullOrEmpty(this.subTheme) ? StringEscapeUtils.escapeHtml4(this.subTheme) : "");
    }
    
    /**
     * Sets the sub-theme to use to build the page. (e.g. esdc)
     * 
     * Set at application level via "wettemplate_subtheme" property in cdn.properties, 
     * can be overriden programatically.  
     */
    public void setSubTheme(String value) {
        this.subTheme = value;
    }

    /**
     * Returns whether the jQuery files should be loaded from google or from the CDN.
     * 
     * Set at application level via "wettemplate_loadjqueryfromgoogle" property in cdn.properties, 
     * can be overriden programatically.  
     */
    public boolean getLoadJQueryFromGoogle() {
        this.initializeOnce();
        return this.loadjQueryFromGoogle;
    }
    
    /**
     * Sets whether the jQuery files should be loaded from google or from the CDN.
     * 
     * Set at application level via "wettemplate_loadjqueryfromgoogle" property in cdn.properties, 
     * can be overriden programatically.  
     */
    public void setLoadJQueryFromGoogle(boolean value) {
        this.loadjQueryFromGoogle = value;
    }
    
    /**
     * Returns the list of breadcrumb links.
     */
    public ArrayList<Breadcrumb> getBreadcrumbs() {
        this.initializeOnce();
        return this.breadcrumbs;
    }
    
    /**
     * Sets the list of breadcrumb links.
     */
    public void setBreadcrumbs(ArrayList<Breadcrumb> value) {
        this.breadcrumbs = value;
    }
    
    /**
     * Returns the date modified displayed just above the footer.
     */
    public Date getDateModified() {
        this.initializeOnce();
        return this.dateModified;
    }
    
    /**
     * Returns the date modified displayed just above the footer.
     */
    public void setDateModified(Date value) {
        this.dateModified = value;
    }
    
    /**
     * Returns the version of the application, to be displayed instead of the date modified.
     */
    public String getVersionIdentifier() {
        this.initializeOnce();
        return this.versionIdentifier;
    }
    
    /**
     * Sets the version of the application, to be displayed instead of the date modified.
     */
    public void setVersionIdentifier(String value) {
        this.versionIdentifier = value;
    }
  
    /**
     * Returns the list of html elements to add to the header tag.
     * 
     * This list is used to add metatags, css, js etc. to web pages.
     */
    public ArrayList<String> getHtmlHeaderElements() {
        this.initializeOnce();
        return this.htmlHeaderElements;
    }
    
    /**
     * Sets the list of html elements to add to the header tag.
     * 
     * This list is used to add metatags, css, js etc. to web pages.
     */
    public void setHtmlHeaderElements(ArrayList<String> value) {
        this.htmlHeaderElements = value;
    }
    
    /**
     * Returns the list of html elements to add to the body tag.
     * 
     * This list is used to add metatags, css, js etc. to web pages.
     */
    public ArrayList<String> getHtmlBodyElements() {
        this.initializeOnce();
        return this.htmlBodyElements;
    }
    
    /**
     * Sets the list of html elements to add to the body tag.
     * 
     * This list is used to add metatags, css, js etc. to web pages.
     */
    public void setHtmlBodyElements(ArrayList<String> value) {
        this.htmlBodyElements = value;
    }
    
    /**
     * Returns the title of page. 
     * Note that '- Canada.ca'  will be automatically added to all pages served under the 'gcweb' theme. 
     */
    public String getHeaderTitle() {
        this.initializeOnce();
        
        if (this.headerTitle == null) this.headerTitle = "";
        
        if (this.getTheme().toLowerCase().equals("gcweb") &&  //NOTE: Hardcoding, should this be a new "titleSuffix" variable?
            !this.headerTitle.endsWith(" - Canada.ca") )            
        {
            return StringEscapeUtils.escapeHtml4(this.headerTitle + " - Canada.ca");
        }
        
        return StringEscapeUtils.escapeHtml4(this.headerTitle);
    }
    
    /**
     * Sets the title of page. 
     * Note that '- Canada.ca'  will be automatically added to all pages served under the 'gcweb' theme. 
     */
    public void setHeaderTitle(String value)
    {
        this.headerTitle = value;
    }
    
    /**
     * Returns whether the Language Toggle Link is to be displayed.
     * 
     * Set at application level via "goc.webtemplate.showlanguagelink" property in cdn.properties, 
     * can be overriden programatically.  
     */
    public boolean getShowLanguageLink() {
        this.initializeOnce();
        return this.showLanguageLink;
    }

    /**
     * Sets whether the Language Toggle Link is to be displayed.
     * 
     * Set at application level via "goc.webtemplate.showlanguagelink" property in cdn.properties, 
     * can be overriden programatically.  
     */
    public void setShowLanguageLink(boolean value) {
        this.showLanguageLink = value;
    }
    
    /**
     * Returns the url to be used for the language toggle, there is a default url already in place for either the 
     * JSF or JSP implementation of the Template, but can be set by application programmatically.
     * 
     * @return the default language toggle url or the value set programmatically
     */
    public String getLanguageLinkUrl() {
        this.initializeOnce();
        
        if (Utility.isNullOrEmpty(this.languageLinkUrl))
            return this.getDefaultLanguageLinkUrl();
        else 
            return this.languageLinkUrl;
    }
    
    /**
     * Sets the url to be used for the language toggle, there is a default url already in place for either the 
     * JSF or JSP implementation of the Template, but can be set by application programmatically.
     */
    public void setLanguageLinkUrl(String value) {
        this.languageLinkUrl = value;
    }
    
    /**
     * Returns the LeavingSecuritySiteWarning object used as configuration for the
     * secure ste warning feature.
     * 
     * Configuration can be set at application level in cdn.properties, or set programattically at runtime.
     */
    public LeavingSecureSiteWarning getLeavingSecureSiteWarning() {
        this.initializeOnce();
        
        //(Redirect URL gets default if not specified)
        if (Utility.isNullOrEmpty(this.leavingSecureSiteWarning.getRedirectUrl()))
        {
            this.leavingSecureSiteWarning.setRedirectUrl(this.getDefaultLeaveSecureSiteRedirectUrl());
        }
        
        return this.leavingSecureSiteWarning; 
    }
    
    /**
     * Sets the LeavingSecuritySiteWarning object used as configuration for the
     * secure ste warning feature.
     * 
     * Configuration can be set at application level in cdn.properties, or set programattically at runtime.
     */
    public void setLeavingSecureSiteWarning(LeavingSecureSiteWarning value) {
        this.leavingSecureSiteWarning = value;
    }

    /**
     * Returns the list of menu items for the left menu.
     */
    public ArrayList<MenuSection> getLeftMenuSections() {
        this.initializeOnce();
        return this.leftMenuSections;
    }
    
    /**
     * Sets the list of menu items for the left menu.
     */
    public void setLeftMenuSections(ArrayList<MenuSection> value) {
        this.leftMenuSections = value;
    }
    
    /**
     * Returns the URL to be used for the Privacy link in transactional mode. 
     */
    public String getPrivacyLinkUrl() {
        this.initializeOnce();
        return BaseUtil.encodeUrl(StringEscapeUtils.escapeHtml4(this.privacyLinkUrl));
    }
    
    /**
     * Sets the URL to be used for the Privacy link in transactional mode. 
     */
    public void setPrivacyLinkUrl(String value) {
        this.privacyLinkUrl = value;
    }
    
    /**
     * Returns the URL to be used to the contact link.
     */
    public String getContactLinkUrl() {
        this.initializeOnce();
        return this.contactLinkUrl;
    }
    
    /**
     * Returns the URL to be used to the contact link.
     */
    public void setContactLinkUrl(String value) {
        this.contactLinkUrl = value;
    }
    
    /**
     * Returns whether the features of the footer are to be displayed.
     * 
     * Set at application level via "goc.webtemplate.showfeatures" property in cdn.properties, 
     * can be overriden programatically.  
     */
    public boolean getShowFeature() {
        this.initializeOnce();
        return this.showFeature;
    }

    /**
     * Sets whether the features of the footer are to be displayed.
     * 
     * Set at application level via "goc.webtemplate.showfeatures" property in cdn.properties, 
     * can be overriden programatically.  
     */
    public void setShowFeature(boolean value) {
        this.showFeature = value;
    }

    /**
     * Returns whether the Share Page Link of the footer are to be displayed.
     * 
     * Set at application level via "goc.webtemplate.showsharepagelink" property in cdn.properties, 
     * can be overriden programatically.  
     */
    public boolean getShowSharePageLink() {
        this.initializeOnce();
        return this.showSharePageLink;
    }
    
    /**
     * Sets whether the Share Page Link of the footer are to be displayed.
     * 
     * Set at application level via "goc.webtemplate.showsharepagelink" property in cdn.properties, 
     * can be overriden programatically.  
     */
    public void setShowSharePageLink(boolean value) {
        this.showSharePageLink = value;
    }

    /**
     * Returns whether the Pre Content of the header are to be displayed.
     * 
     * Set at application level via "goc.webtemplate.showprecontent" property in cdn.properties, 
     * can be overriden programatically.  
     */
    public boolean getShowPreContent() {
        this.initializeOnce();
        return this.showPreContent;
    }
    
    /**
     * Sets whether the Pre Content of the header are to be displayed.
     * 
     * Set at application level via "goc.webtemplate.showprecontent" property in cdn.properties, 
     * can be overriden programatically.  
     */
    public void setShowPreContent(boolean value) {
        this.showPreContent = value;
    }

    /**
     * Returns whether the Post Content of the header are to be displayed.
     * 
     * Set at application level via "goc.webtemplate.showpostcontent" property in cdn.properties, 
     * can be overriden programatically.  
     */
    public boolean getShowPostContent() {
        this.initializeOnce();
        return this.showPostContent;
    }
    
    /**
     * Sets whether the Post Content of the header are to be displayed.
     * 
     * Set at application level via "goc.webtemplate.showpostcontent" property in cdn.properties, 
     * can be overriden programatically.  
     */
    public void setShowPostContent(boolean value) {
        this.showPostContent = value;
    }

    /**
     * Returns whether the Search control of the header are to be displayed.
     * 
     * Set at application level via "goc.webtemplate.showsearch" property in cdn.properties, 
     * can be overriden programatically.  
     */
    public boolean getShowSearch() {
        this.initializeOnce();
        return this.showSearch;
    }
    
    /**
     * Sets whether the Search control of the header are to be displayed.
     * 
     * Set at application level via "goc.webtemplate.showsearch" property in cdn.properties, 
     * can be overriden programatically.  
     */
    public void setShowSearch(boolean value) {
        this.showSearch = value;
    }
    
    /**
     * Allows for a custom search to be used in the application, you must contact CDTS to have one created.
     */
    public String getCustomSearch() {
        this.initializeOnce();
        return this.customSearch;
    }
    
    /**
     * Allows for a custom search to be used in the application, you must contact CDTS to have one created.
     */
    public void setCustomSearch(String value) {
        this.customSearch = value;
    }
    
    /**
     * Returns the list of items to be displayed in the Share Page window.
     * Set by application programmatically
     */
    public ArrayList<Constants.SocialMediaSites> getSharePageMediaSites() {
        this.initializeOnce();
        return this.sharePageMediaSites;
    }
    
    /**
     * Sets the list of items to be displayed in the Share Page window.
     * Set by application programmatically
     */
    public void setSharePageMediaSites(ArrayList<Constants.SocialMediaSites> value) {
        this.sharePageMediaSites = value;
    }
    
    /**
     * Returns whether the FeedBack link of the footer is to be displayed.
     * 
     * Set at application level via "goc.webtemplate.showfeedbacklink" property in cdn.properties, 
     * can be overriden programatically.  
     */
    public boolean getShowFeedbackLink() {
        this.initializeOnce();
        return this.showFeedbackLink;
    }
    
    /**
     * Sets whether the FeedBack link of the footer is to be displayed.
     * 
     * Set at application level via "goc.webtemplate.showfeedbacklink" property in cdn.properties, 
     * can be overriden programatically.  
     */
    public void setShowFeedbackLink(boolean value) {
        this.showFeedbackLink = value;
    }
    
    /**
     * Returns the FeedBack link URL.
     * 
     * Set at application level via "goc.webtemplate.feedbackurl" property in cdn.properties, 
     * can be overriden programatically.  
     */
    public String getFeedbackUrl() {
        this.initializeOnce();
        return this.feedbackUrl;
    }
    
    /**
     * Sets the FeedBack link URL.
     * 
     * Set at application level via "goc.webtemplate.feedbackurl" property in cdn.properties, 
     * can be overriden programatically.  
     */
    public void setFeedbackUrl(String value) {
        this.feedbackUrl = value;
    }
    
    /**
     * Returns a unique string to identify a web page. Used by user to identify the screen where an issue occured.
     */
    public String getScreenIdentifier() {
        this.initializeOnce();
        return this.screenIdentifier;
    }
    
    /**
     * Sets a unique string to identify a web page. Used by user to identify the screen where an issue occured.
     */
    public void setScreenIdentifier(String value) {
        this.screenIdentifier = value;
    }
    
    /**
     * Returns the configuration object containing the various session timeout settings.
     *  
     * Can be set in either cdn.properties or by application programmatically
     */
    public SessionTimeout getSessionTimeoutConfigurations() {
        this.initializeOnce();

        //if not overriden, set from config
        if (this.sessionTimeoutConfigurations == null) {
            java.util.ResourceBundle bundle = this.getResourceBundle();
            this.sessionTimeoutConfigurations = this.buildDefaultSessionTimeoutConfigurations(bundle);
        }

        return this.sessionTimeoutConfigurations;
    }

    /**
     * Sets the configuration object containing the various session timeout settings.
     *  
     * Can be set in either cdn.properties or by application programmatically
     */
    public void setSessionTimeoutConfigurations(SessionTimeout value) {
        this.sessionTimeoutConfigurations = value;
    }

    /**
     * Returns the url to be used for the Terms & Conditions link in transactional mode.
     * 
     * Set by application programmatically.
     */
    public String getTermsConditionsLinkUrl() {
        this.initializeOnce();
        return BaseUtil.encodeUrl(StringEscapeUtils.escapeHtml4(this.termsConditionsLinkUrl));//TODO: Escaping will no longer be needed once/if value is used only with JSON serialization
    }
    
    /**
     * Sets the url to be used for the Terms & Conditions link in transactional mode.
     * 
     * Set by application programmatically.
     */
    public void setTermsConditionsLinkUrl(String value) {
        this.termsConditionsLinkUrl = value;
    }
    
    /**
     *  Returns whether the Global Nav bar in the footer is to be displayed.
     *  
     * Set at application level via "goc.webtemplate.showglobalnav" property in cdn.properties, 
     * can be overriden programatically.  
     *  
     *  Only available in the Application Template
     */
    public boolean getShowGlobalNav() {
        this.initializeOnce();
        return this.showGlobalNav;
    }
    
    /**
     * Sets whether the Global Nav bar in the footer is to be displayed.
     *  
     * Set at application level via "goc.webtemplate.showglobalnav" property in cdn.properties, 
     * can be overriden programatically.  
     *  
     *  Only available in the Application Template
     */
    public void setShowGlobalNav(boolean value)
    {
        this.showGlobalNav = value;
    }
    
    /**
     * Returns the  custom site menu to be used in place of the standard canada.ca site menu
     * This defaults to null (use standard menu)
     * 
     * Set at application level via "goc.webtemplate.customsitemenuurl" property in cdn.properties, 
     * can be overriden programatically.  
     * 
     * Only available in the Application Template
     */
    public String getCustomSiteMenuUrl() {
        this.initializeOnce();
        return this.customSiteMenuUrl;
    }
    
    /**
     * Sets the custom site menu to be used in place of the standard canada.ca site menu
     * This defaults to null (use standard menu)
     * 
     * Set at application level via "goc.webtemplate.customsitemenuurl" property in cdn.properties, 
     * can be overriden programatically.  
     * 
     * Only available in the Application Template
     */
    public void setCustomSiteMenuUrl(String value) {
        this.customSiteMenuUrl = value;
    }
    
    /**
     *  Returns whether the Site Menu is to appear at the top of the page. 
     *  If set to false only a band will still be seen.
     *  
     * Set at application level via "goc.webtemplate.showsitemenu" property in cdn.properties, 
     * can be overriden programatically.  
     *  
     *  Only available in the Application Template
     */
    public boolean getShowSiteMenu() {
        this.initializeOnce();
        return this.showSiteMenu;
    }
    
    /**
     *  Sets whether the Site Menu is to appear at the top of the page. 
     *  If set to false only a band will still be seen.
     *  
     * Set at application level via "goc.webtemplate.showsitemenu" property in cdn.properties, 
     * can be overriden programatically.  
     *  
     *  Only available in the Application Template
     */
    public void setShowSiteMenu(boolean value) {
        this.showSiteMenu = value;
    }

    /**
     * Returns the link to use for the sign in button, will only appear if getShowSignInLink() is set to true
     * 
     * Set at application level via "goc.webtemplate.signinlinkurl" property in cdn.properties, 
     * can be overriden programatically.  
     * 
     * Only available in the Application Template
     * 
     * @see getShowSignInLink()
     */
    public String getSignInLinkUrl() {
        this.initializeOnce();
        return this.signInLinkUrl;
    }
    
    /**
     * Sets the link to use for the sign in button, will only appear if getShowSignInLink() is set to true
     * 
     * Set at application level via "goc.webtemplate.signinlinkurl" property in cdn.properties, 
     * can be overriden programatically.  
     * 
     * Only available in the Application Template
     * 
     * @see getShowSignInLink()
     */
    public void setSignInLinkUrl(String value) {
        this.signInLinkUrl = value;
    }

    /**
     * Returns the link to use for the sign out button, will only appear if getSignOutLinkUrl() is set to true
     * 
     * Set at application level via "goc.webtemplate.signoutlinkurl" property in cdn.properties, 
     * can be overriden programatically.  
     * 
     * Only available in the Application Template
     * 
     * @see getShowSignOutLink()
     */
    public String getSignOutLinkUrl() {
        this.initializeOnce();
        return this.signOutLinkUrl;      
    }
    
    /**
     * Sets the link to use for the sign out button, will only appear if getSignOutLinkUrl() is set to true
     * 
     * Set at application level via "goc.webtemplate.signoutlinkurl" property in cdn.properties, 
     * can be overriden programatically.  
     * 
     * Only available in the Application Template
     * 
     * @see getShowSignOutLink()
     */
    public void setSignOutLinkUrl(String value) {
        this.signOutLinkUrl = value;
    }

    /**
     * Returns whether the secure icon is displayed next to the applicaiton name in the header.
     * Set by application programmatically
     * Only available in the Application Template
     */
    public boolean getShowSecureIcon() {
        this.initializeOnce();
        return this.showSecureIcon;
    }
    
    /**
     * Sets whether the secure icon is displayed next to the applicaiton name in the header.
     * Set by application programmatically
     * Only available in the Application Template
     */
    public void setShowSecureIcon(boolean value) {
        this.showSecureIcon = value;
    }

    /**
     * Returns whether the sign in link is displayed.
     * signInLinkUrl must not be null or whitespace
     * showSignOutLink must not be set at the same time.
     * Set by application programmatically
     * Only available in the Application Template
     */
    public boolean getShowSignInLink() {
        this.initializeOnce();
        return this.showSignInLink;
    }

    /**
     * Sets whether the sign in link is displayed.
     * signInLinkUrl must not be null or whitespace
     * showSignOutLink must not be set at the same time.
     * Set by application programmatically
     * Only available in the Application Template
     */
    public void setShowSignInLink(boolean value) {
        this.showSignInLink = value;
    }
    
    /**
     * Returns whether the signout link is displayed.
     * signOutLinkUrl must not be null or whitespace
     * showSignInLink must not be set at the same time.
     * Set by application programmatically
     * Only available in the Application Template
     */
    public boolean getShowSignOutLink() {
        this.initializeOnce();
        return this.showSignOutLink;
    }
    
    /**
     * Sets whether the signout link is displayed.
     * signOutLinkUrl must not be null or whitespace
     * showSignInLink must not be set at the same time.
     * Set by application programmatically
     * Only available in the Application Template
     */
    public void setShowSignOutLink(boolean value) {
        this.showSignOutLink = value;
    }
    
    /**
     * Returns this custom links.
     * If null uses standard links if not null overrides the existing footer links
     * Set by application programmatically
     * Only available in the Application Template
     */
    public ArrayList<FooterLink> getCustomFooterLinks() {
        this.initializeOnce();
        return this.customFooterLinks;
    }

    /**
     * Sets this custom links.
     * If null uses standard links if not null overrides the existing footer links
     * Set by application programmatically
     * Only available in the Application Template
     */
    public void setCustomFooterLinks(ArrayList<FooterLink> value) {
        this.customFooterLinks = value;
    }
    
    
//TODO: HERE ENDS THE MAIN PROPERTIES
    /**
     * Returns the value of cdn_XXXX_localpath (where XXXX is the cdn environment), or blank if not specified.
     */
    public String getLocalPath()
    {
        String  tmpPath;
        String  templateVersion;
        
        if (this.cdnLocalPath == null)
        {
            try
            {
                tmpPath = this.getResourceBundleString("cdn", "cdn_" + this.getCDNEnvironment().toLowerCase() + "_localpath");
            }
            catch (java.util.MissingResourceException ex)
            {
                tmpPath = null;
            }
            if (!Utility.isNullOrEmpty(tmpPath))
            {
                templateVersion = this.getTemplateVersion();
                if (templateVersion == null) templateVersion = "";
                
                this.cdnLocalPath = BaseUtil.encodeUrl(StringEscapeUtils.escapeHtml4(String.format(tmpPath, this.getTheme(), templateVersion)));
            }
            else
            {
                this.cdnLocalPath = "";
            }
        }
        
        return this.cdnLocalPath;
    }
    
    private String getPartialCDNPath() {
        String https;
        String envName;
        String envUrl;
        String templateVersion;
        String cdnUrl;

        if (this.partialCDNPath != null) return this.partialCDNPath; //return cached value if found
        
        https = this.getUseHttps() ? "s" : "";
        envName = this.getCDNEnvironment().toLowerCase();
        envUrl = this.getResourceBundleString("cdn", "cdn_" + envName + "_url");
        envUrl = (Utility.isNullOrEmpty(envUrl) ? "" : envUrl);
        templateVersion = this.getTemplateVersion();
        
        if (envName.equals("akamai")) {
            // Using Akamai CDTS
            if (Utility.isNullOrEmpty(templateVersion)) {
                cdnUrl = String.format(envUrl, https, this.getTheme(), "rn");
            } else {
                cdnUrl = String.format(envUrl, https, this.getTheme(), templateVersion);
            }
        } else {
            // Using ESDC CDTS (SSL)
            if (Utility.isNullOrEmpty(templateVersion)) {
                cdnUrl = String.format(envUrl, https, "rn", this.getTheme(), "");
            } else {
                cdnUrl = String.format(envUrl, https, "app", this.getTheme(), templateVersion + "/");
            }
        }
        
        this.partialCDNPath = BaseUtil.encodeUrl(StringEscapeUtils.escapeHtml4(cdnUrl));
        
        return this.partialCDNPath;
    }
    
    /**
     * Outputs the portion of the SoyUtils and WET url in each of the master template page, the value 
     * outputted is determined by the current WET Template Version identified in cdn.properties, if 
     * no value is specified the "run" version of the WET Template will be utilized.
     *   
     * @return either "rn" or "app", this is used strictly by the various master template page
     */
    private String getRunOrVersionValue() {
        if (Utility.isNullOrEmpty(this.getTemplateVersion())) { 
            return "rn";
        } else {
            return "app";
        }
    }
    
    /**
     * Returns a copy of the breadcrumb list, ready for JSON serialization 
     */
    private ArrayList<Breadcrumb> getEncodedBreadcrumbs() {
        ArrayList<Breadcrumb>   sourceList = this.getBreadcrumbs();
        ArrayList<Breadcrumb>   tmpBreadcrumbs = null;
        
        if ((sourceList != null) && (sourceList.size() > 0)) {
            tmpBreadcrumbs = new ArrayList<Breadcrumb>();
            for (Breadcrumb bc: sourceList) {
                tmpBreadcrumbs.add(new Breadcrumb(
                                        BaseUtil.encodeUrl(bc.getHref()), 
                                        JsonValueUtils.GetNonEmptyString(bc.getTitle()), 
                                        JsonValueUtils.GetNonEmptyString(bc.getAcronym())) );                
            }
        }
        
        return tmpBreadcrumbs;
    }
    
    private String getDateModifiedJSONValue() {
        Date    sourceDate = this.getDateModified();
        
        //If we have a date set, return it
        if (sourceDate != null) return dateModifiedFormat.get().format(sourceDate);
        //If we don't have a date, but we have a versionIdentifier: no date modified at all!
        if (!Utility.isNullOrEmpty(this.getVersionIdentifier())) return null;
        //If we don't have a date nor a versionIdentifier, return epoc date
        return dateModifiedFormat.get().format(new Date(0));       
    }
    
    private String getVersionIdentifierJSONValue() {
        //If we have a date modified set: no version identifier at all!
        if (this.getDateModified() != null) return null;
        //If we don't have a date, return version identifier if we have one set
        return JsonValueUtils.GetNonEmptyString(this.getVersionIdentifier());
    }
    
    private ArrayList<Link> getContactList()
    {
        ArrayList<Link> vtr = new ArrayList<Link>();
        vtr.add(new Link(this.getContactLinkUrl(), ""));
        return vtr;
    }
    
    /**
     * helper method to build the default SessionTimeout configuration object using default value 
     * specified in the resource bundle
     */
    private SessionTimeout buildDefaultSessionTimeoutConfigurations(java.util.ResourceBundle bundle) {
        SessionTimeout configs = new SessionTimeout();
        
        if (bundle != null) {
            configs.setEnabled(Boolean.parseBoolean(bundle.getString("session.timeout.enabled")));
            configs.setInActivity(Integer.parseInt(bundle.getString("session.inactivity.value")));
            configs.setReactionTime(Integer.parseInt(bundle.getString("session.reactiontime.value")));
            configs.setSessionAlive(Integer.parseInt(bundle.getString("session.sessionalive.value")));
            configs.setLogoutUrl(bundle.getString("session.logouturl"));
            
            if (!Utility.isNullOrEmpty(bundle.getString("session.refreshcallbackurl"))) 
                configs.setRefreshCallbackUrl(bundle.getString("session.refreshcallbackurl"));
            
            if (!Utility.isNullOrEmpty(bundle.getString("session.refreshonclick"))) 
                configs.setRefreshOnClick(Boolean.parseBoolean(bundle.getString("session.refreshonclick")));
                
            if (Integer.parseInt(bundle.getString("session.refreshlimit.value")) > 0) 
                configs.setRefreshLimit(Integer.parseInt(bundle.getString("session.refreshlimit.value")));
                
            if (!Utility.isNullOrEmpty(bundle.getString("session.method"))) 
                configs.setMethod(bundle.getString("session.method"));
                
            if (!Utility.isNullOrEmpty(bundle.getString("session.additionaldata"))) 
                configs.setAdditionalData(bundle.getString("session.additionaldata"));
        }
        
        return configs;
    }    
    
    /**
     * outputs the version of the GoC Java Web Template, it will be put as a comment in the html of the 
     * master pages.  this will help us troubleshoot issues with clients using the template.
     * 
     * @return the distribution version value as per specified in the Constants class
     */
    public String getWebTemplateDistributionVersion() {
        return Constants.WEB_TEMPLATE_DISTRIBUTION_VERSION;
    }     
    
    /**
     * Returns the CDN path to the soyutils javascript file.
     * 
     * (Used by template files when rendering)
     */
    public String getSoyUtilPath() {
        return this.getPartialCDNPath() + "soyutils.js";
    }
    
    /**
     * Returns the CDN path to the wet javascript file.
     * 
     * (Used by template files when rendering)
     */
    public String getWetJsPath() {
        return String.format("%swet-%s.js", this.getPartialCDNPath(), this.getTwoLetterCultureLanguage()); 
    }
    
    /**
     * Returns the CDN path to the plugins javascript file.
     * 
     * (Used by template files when rendering)
     */
    public String getPluginJsPath() {
        return String.format("%splugins-%s.js",  this.getPartialCDNPath(), this.getTwoLetterCultureLanguage());
    }

    /**
     * Outputs the Lang attribute of the language toggle link
     *
     * @return either 'fr' or 'en'
     */
    public String getLanguageLinkLang() {
        if (this.getTwoLetterCultureLanguage().toUpperCase().equals(Constants.ENGLISH_ACCRONYM.toUpperCase())) {
            return Constants.FRENCH_ACCRONYM;
        } else {
            return Constants.ENGLISH_ACCRONYM;
        }
    }
    
    /**
     * Outputs the Text attribute of the language toggle link
     * 
     * @return either 'Français' or 'English'
     */
    public String getLanguageLinkText() {
        if (this.getTwoLetterCultureLanguage().toUpperCase().equals(Constants.ENGLISH_ACCRONYM.toUpperCase())) {
            return Constants.LANGUAGE_LINK_FRENCH_TEXT;
        } else {
            return Constants.LANGUAGE_LINK_ENGLISH_TEXT;
        }
    }

    /**
     * Returns the list of header elements rendered as a string.
     * 
     * @see #getHtmlHeaderElements()
     */
    public String getRenderHtmlHeaderElements()
    {
        return this.getRenderHtmlElements(this.getHtmlHeaderElements());
    }
    
    /**
     * Returns the list of body elements rendered as a string.
     * 
     * @see #getHtmlHeaderElements()
     */
    public String getRenderHtmlBodyElements()
    {
        return this.getRenderHtmlElements(this.getHtmlBodyElements());
    }
    
    private String getRenderHtmlElements(ArrayList<String> elems)
    {
        StringBuilder sb = new StringBuilder();
        
        if (elems != null && elems.size() > 0)
        {
            int idx = 0;
            for (String elem : elems) 
            {
                sb.append((idx == 0 ? "\r\n" : "") + elem + (idx == (elems.size() - 1) ? "" : "\r\n"));
                idx++;
            }
        }
            
        return sb.toString();
    }

    private ArrayList<LanguageLink> buildLanguageLinkList()
    {
        ArrayList<LanguageLink> vtr;
        
        if (!this.getShowLanguageLink()) return null;
        
        vtr = new ArrayList<LanguageLink>();
        vtr.add(new LanguageLink(BaseUtil.encodeUrl(this.getLanguageLinkUrl()),
                                this.getLanguageLinkLang(),
                                this.getLanguageLinkText()));
        
        return vtr;
    }    
    
    private ArrayList<Link> buildHideableHrefOnlyLink(String href, boolean showLink)
    {
        ArrayList<Link> vtr;
        
        if ((!showLink) || Utility.isNullOrEmpty(href)) return null;
        
        vtr = new ArrayList<Link>();
        vtr.add(new Link(BaseUtil.encodeUrl(href), null));
        
        return vtr;
    }
    
    /**
     * NOTE: This method assumes the instance variable values are already defined.
     */
    private void checkIfBothShowSignInAndOutAreSet()
    {
        if (this.showSignInLink && this.showSignOutLink)
        {
            System.err.println(this.getClass().getName() + ": ERROR: Both showSignInLink and showSignOutLink must not be enabled at the same time.");
            throw new java.lang.IllegalStateException("Both showSignInLink and showSignOutLink must not be enabled at the same time.");
        }
    }
    
    /**
     * Builds a string with the format required by the closure template to represent the JSON object used 
     * as parameter for the "appFooter"
     */
    public String getRenderAppFooter()
    {
        AppFooter               appFooter;
        ArrayList<FooterLink>   tmpFooterLinks = null;
        
        this.initializeOnce();
        
        if ((this.customFooterLinks != null) && (this.customFooterLinks.size() > 0))
        {
            tmpFooterLinks = new ArrayList<FooterLink>();
            for (FooterLink fl: this.customFooterLinks)
                tmpFooterLinks.add(new FooterLink(BaseUtil.encodeUrl(fl.getHref()), 
                                   JsonValueUtils.GetNonEmptyString(fl.getText()), 
                                   fl.getNewWindow()));
        }
        
        appFooter = new AppFooter(
                        this.getCdtsCdnEnv(),
                        JsonValueUtils.GetNonEmptyString(this.getSubTheme()),
                        JsonValueUtils.GetNonEmptyString(this.getLocalPath()),
                        this.getShowGlobalNav(),
                        tmpFooterLinks,
                        JsonValueUtils.GetNonEmptyLinkList(this.getContactList()),
                        JsonValueUtils.GetNonEmptyURLEscapedString(this.termsConditionsLinkUrl),
                        JsonValueUtils.GetNonEmptyURLEscapedString(this.privacyLinkUrl),
                        this.getShowFeature()                        
                );
        
        return gson.toJson(appFooter);
    }
    
    /**
     * Builds a string with the format required by the closure template to represent the JSON object used 
     * as parameter for the "appTop"
     */
    public String getRenderAppTop()
    {
        AppTop                  appTop;

        appTop = new AppTop(
                    this.getCdtsCdnEnv(),
                    JsonValueUtils.GetNonEmptyString(this.getSubTheme()),
                    JsonValueUtils.GetNonEmptyString(this.getLocalPath()),
                    JsonValueUtils.GetNonEmptyString(this.applicationTitle.getText()),
                    JsonValueUtils.GetNonEmptyURLEscapedString(this.getCustomSiteMenuUrl()),
                    this.buildLanguageLinkList(),
                    this.getShowSiteMenu(),
                    this.getShowSecureIcon(),
                    this.buildHideableHrefOnlyLink(this.getSignInLinkUrl(), this.getShowSignInLink()),
                    this.buildHideableHrefOnlyLink(this.getSignOutLinkUrl(), this.getShowSignOutLink()),
                    this.showSearch,
                    this.getEncodedBreadcrumbs(),
                    this.showPreContent,
                    JsonValueUtils.GetNonEmptyString(this.getCustomSearch())
                );

        //NOTE: We do this here because variables are not initialize until after the call to getShowSignInLink/getShowSignOutLink (because it calls its corresponding setXXX method)
        this.checkIfBothShowSignInAndOutAreSet();
        
        return gson.toJson(appTop);
    }
    
//TODO: HERE! (1332)
    /**
     * Outputs the portion of the SoyUtils and WET url in each of the master template page, the value 
     * outputted is determined by the current WET Template Version identified in cdn.properties, if 
     * no value is specified the "run" version of the WET Template will be utilized.
     *   
     * @return either "" if in "Run" mode or the wet template version value, this is used strictly by the various master template page
     */
    public String getThemeVersionValue() {//TODO: IF DOABLE, Remove this method once the templates are modified to use JSON serialization
        if (this.getRunOrVersionValue().equals("rn")) {
            return "";
        } else {
            return this.getTemplateVersion() + "/";
        }
    }
    
    /**
     * @deprecated  This method should not be used, it will be removed in a futrue version.
     */
    @Deprecated
    public String getRenderShowPostContent() {//TODO: Remove this method once the templates are modified to use JSON serialization
        return (this.getShowPostContent() ? "true" : "false");
    }
    
    /**
     * @deprecated  This method should not be used, it will be removed in a futrue version.
     */
    @Deprecated
    public String getRenderShowPreContent() {//TODO: Remove this method once the templates are modified to use JSON serialization
        return (this.getShowPreContent() ? "true" : "false");
    }
    
    /**
     * @deprecated  This method should not be used, it will be removed in a futrue version.
     */
    @Deprecated
    public String getRenderShowSearch() {//TODO: Remove this method once the templates are modified to use JSON serialization
        return (this.getShowSearch() ? "search: true," : "search: false,");
    }
    
    /**
     * Builds the html of the WET Session Timeout Control that provided session timeout and inactivity functionality.
     * For more documentation: https://wet-boew.github.io/v4.0-ci/demos/session-timeout/session-timeout-en.html
     * 
     * @return the html of the WET session timeout control
     * @deprecated  This method should not be used, it will be removed in a futrue version.
     */
    @Deprecated
    public String getSessionTimeoutControl() {//TODO: Remove this method once the templates are modified to use JSON serialization
        StringBuilder sb = new StringBuilder();
        
        SessionTimeout configs = this.getSessionTimeoutConfigurations();
        
        if (configs.isEnabled()) { 
            sb.append("<span class='wb-sessto' data-wb-sessto='{");
            sb.append("\"inactivity\": ");
            sb.append(configs.getInActivity());
            sb.append(", \"reactionTime\": ");
            sb.append(configs.getReactionTime());
            sb.append(", \"sessionalive\": ");
            sb.append(configs.getSessionAlive());
            sb.append(", \"logouturl\": \"");
            sb.append(BaseUtil.encodeUrl(StringEscapeUtils.escapeHtml4(configs.getLogoutUrl())));
            sb.append("\"");
            
            if (!Utility.isNullOrEmpty(configs.getRefreshCallbackUrl())) {
                sb.append(", \"refreshCallbackUrl\": \"");
                sb.append(BaseUtil.encodeUrl(StringEscapeUtils.escapeHtml4(configs.getRefreshCallbackUrl())));
                sb.append("\"");
            }
            
            sb.append(", \"refreshOnClick\": ");
            sb.append(configs.isRefreshOnClick());
            
            if (configs.getRefreshLimit() > 0) {
                sb.append(", \"refreshLimit\": ");
                sb.append(configs.getRefreshLimit());
            }
            
            if (!Utility.isNullOrEmpty(configs.getMethod())) {
                sb.append(", \"method\": \"");
                sb.append(StringEscapeUtils.escapeHtml4(configs.getMethod()));
                sb.append("\"");
            }
            
            if (!Utility.isNullOrEmpty(configs.getAdditionalData())) {
                sb.append(", \"additionalData\": \"");
                sb.append(StringEscapeUtils.escapeHtml4(configs.getAdditionalData()));
                sb.append("\"");
            }
            
            sb.append("}'></span>");
        }
        return sb.toString();
    }
    
    
    /**
     * Builds a string with the format required by the closure template, to represent the "localPath" argument,
     * which should be specified when the project is hosting teh CDTS files locally (usually for development or load testing purposes).
     * 
     * @return Either "localPath: "...value/..."" or "", this is used strictly by the various master template page.
     * @deprecated  This method should not be used, it will be removed in a futrue version.
     */
    @Deprecated
    public String getRenderLocalPath() {//TODO: Remove this method once the templates are modified to use JSON serialization
        if (this.cdnLocalPathRender == null)
        {
            String tmpPath = this.getLocalPath();
            if (!Utility.isNullOrEmpty(tmpPath))
            {
                this.cdnLocalPathRender = "localPath: \"" + tmpPath + "\",";
            }
            else
            {
                this.cdnLocalPathRender = "";
            }
        }
        
        return this.cdnLocalPathRender;             
    }
    
    /**
     * Builds a string with the format required by the closure template, to represent if the jQuery 
     * file should be loaded from Google or CDTS locally.
     *   
     * @return either "external" or "", this is used strictly by the various master template page
     * @deprecated  This method should not be used, it will be removed in a futrue version.
     */
    @Deprecated
    public String getRenderjQuery() {//TODO: Remove this method once the templates are modified to use JSON serialization
        return this.getLoadJQueryFromGoogle() ? "jqueryEnv: \"external\"," : "";
    }
    
    /**
     * Builds a string with the format required by the closure templates, to represent the Share Page Links.
     * If the application did not supply the Share Page items, the Share Page link will not be displayed.
     * 
     * sample output: showShare: ["email", "facebook", "linkedin", "twitter"]
     * 
     * @return string in the format expected by the Closure Templates to generate the Share Page Link
     * @deprecated  This method should not be used, it will be removed in a futrue version.
     */
    @Deprecated
    public String getRenderSharePageMediaSites() {//TODO: Remove this method once the templates are modified to use JSON serialization
        StringBuilder sb = new StringBuilder();
        
        this.initializeOnce();
        
        if (this.getShowSharePageLink() && this.sharePageMediaSites.size() > 0)
        {
            sb.append("showShare: [");

            for (Constants.SocialMediaSites site : this.sharePageMediaSites)
            {
                sb.append(" '" + site + "', ");
            }
            sb.append("],");
        }
        else // don't display the feedback link
        {
            sb.append("showShare: false,");
        }

        return sb.toString();
    }
    
    /**
     * Builds a string with the format required by the Closure Templates, to represent the Screen Identifier 
     * value.  If the application did not supply a Screen Identifier value, nothing will be rendered.
     * 
     * @return string in the format expected by the Closure Template to generate the Screen Identifier execution script
     * @deprecated  This method should not be used, it will be removed in a futrue version.
     */
    @Deprecated
    public String getRenderScreenIdentifier() {//TODO: Remove this method once the templates are modified to use JSON serialization
        return (!Utility.isNullOrEmpty(this.getScreenIdentifier()) ? 
                    "screenIdentifier: \"" + StringEscapeUtils.escapeHtml4(this.getScreenIdentifier()) + "\", " : 
                    "");
    }
    
    /**
     * Builds a string with the format required by the closure templates, to represent a list of links for :
     *  - Contact Us
     *  - About Us
     *  - News
     * The list of links is provided by the application
     * 
     * @return string in the format expected by the Closure Templates te generate the list of links
     * @deprecated  This method should not be used, it will be removed in a futrue version.
     */
    @Deprecated
    public String getRenderLinksList() {//TODO: Remove this method once the templates are modified to use JSON serialization
        StringBuilder sb = new StringBuilder();

        this.buildLinksJSONString(sb, "contactLinks", this.getContactList());
        
        return sb.toString();
    }
    /**
     * @deprecated  This method should not be used, it will be removed in a futrue version.
     */
    @Deprecated
    private void buildLinksJSONString(StringBuilder sb, String linkTitle, ArrayList<Link> linksList) {//TODO: Remove this method once the templates are modified to use JSON serialization
        if (linksList != null && linksList.size() > 0)
        {
            sb.append(linkTitle);
            sb.append(": [");
            for (Link lk : linksList)
            {
                sb.append("{href: '");
                sb.append(BaseUtil.encodeUrl(StringEscapeUtils.escapeHtml4(lk.getHref())));
                sb.append("', text: '");
                sb.append(StringEscapeUtils.escapeHtml4(lk.getText()));
                sb.append("'},");
            }
            sb.append("],");
        }
    }
    
    /**
     * Builds a string with the format required by the closure templates, to manage the leave secure site warning 
     * feature offered by the WET Template.
     * 
     * sample output: exitScript: true, 
     *                exitURL: "", 
     *                exitMsg: "", 
     *                exitDomains: ""
     * 
     * @return string in the format expected by the Closure Templates to manage the leave secure site warning feature
     * @deprecated  This method should not be used, it will be removed in a futrue version.
     */
    @Deprecated
    public String getRenderLeavingSecureSiteWarning() {//TODO: Remove this method once the templates are modified to use JSON serialization
        StringBuilder               sb = new StringBuilder();
        LeavingSecureSiteWarning    lssw = this.getLeavingSecureSiteWarning();
        
        if (lssw.isEnabled() && !Utility.isNullOrEmpty(lssw.getRedirectUrl())) {
            sb.append("exitScript: true,");
            sb.append(lssw.getDisplayModalWindow() ? "" : " displayModal: false,");
            sb.append("exitURL: \"");
            sb.append(BaseUtil.encodeUrl(StringEscapeUtils.escapeHtml4(lssw.getRedirectUrl())));
            sb.append("\",");
            sb.append("exitMsg: \"");
            sb.append(StringEscapeUtils.escapeHtml4(lssw.getMessage()));
            sb.append("\",");
            
            if (!Utility.isNullOrEmpty(lssw.getExcludedDomains())) {
                sb.append("exitDomains: \"");
                sb.append(StringEscapeUtils.escapeHtml4(lssw.getExcludedDomains()));
                sb.append("\",");
            }
        }
        else
        {
            sb.append("exitScript: false,");
        }
        
        return sb.toString();
    }
    
    /**
     * Builds a string with the format required by the closure templates, to represent the Language toggle link.
     * 
     * @return string in the format expected by the Closure Templates to generate the language toggle link
     * @deprecated  This method should not be used, it will be removed in a futrue version.
     */
    @Deprecated
    public String getRenderLanguageLink() {//TODO: Remove this method once the templates are modified to use JSON serialization
        if (this.getShowLanguageLink()) {
            return "lngLinks: [{" + 
                        "lang: '" + StringEscapeUtils.escapeHtml4(this.getLanguageLinkLang()) + "', " + 
                        "href: '" + BaseUtil.encodeUrl(StringEscapeUtils.escapeHtml4(this.getLanguageLinkUrl())) + "', " + 
                        "text: '" + StringEscapeUtils.escapeHtml4(this.getLanguageLinkText()) + "'" + 
                        "}],";
        } else {
            return "";
        }
    }
    
    /**
     * Builds a string with the format required by the closure templates, to represent the feedback link.
     * 
     * 
     * sample output: showFeatures: true
     * 
     * @return string in the format expected by the Closure Templates to generate the features
     * @deprecated  This method should not be used, it will be removed in a futrue version.
     */
    @Deprecated
    public String getRenderFeedbackLink() {//TODO: Remove this method once the templates are modified to use JSON serialization
        if (this.getShowFeedbackLink()) {
            return (!Utility.isNullOrEmpty(this.feedbackUrl) ? "showFeedback: '" + BaseUtil.encodeUrl(StringEscapeUtils.escapeHtml4(this.getFeedbackUrl())) + "'," : "");
        } else {
            return "showFeedback: false,";
        }
    }
    
    /**
     * Builds a string with the format required by the closure templates, to represent the features/activities.
     * 
     * sample output: showFeatures: true
     * 
     * @return string in the format expected by the Closure Templates to generate the features
     * @deprecated  This method should not be used, it will be removed in a futrue version.
     */
    @Deprecated
    public String getRenderFeatures() {//TODO: Remove this method once the templates are modified to use JSON serialization
        if (this.getShowFeature()) {
            return "showFeatures: true,";
        } else {
            return "showFeatures: false,";
        }
    }

    /**
     * Builds a string with the format required by the closure templates, to represent the Application Title 
     * information (only applicable to the GCIntranet theme).
     * 
     * @return string in the format expected by the Closure Templates to generate the application title
     * @deprecated  This method should not be used, it will be removed in a futrue version.
     */
    @Deprecated
    public String getRenderApplicationTitle() {//TODO: Remove this method once the templates are modified to use JSON serialization
        this.initializeOnce();
        
        if (!Utility.isNullOrEmpty(this.applicationTitle.getText()))
        {
            //intranetTitle: [{href: "http://hrsdc.prv/eng/iit/index.shtml", text: "IITB"}],
            return "intranetTitle: [{" + 
                        (Utility.isNullOrEmpty(this.applicationTitle.getUrl()) ? 
                                "" : 
                                "href: \"" + BaseUtil.encodeUrl(StringEscapeUtils.escapeHtml4(this.applicationTitle.getUrl())) + "\", ") + 
                        "text: \"" + StringEscapeUtils.escapeHtml4(this.applicationTitle.getText()) + "\"" + 
                        "}],";
        }
        
        return "";
    }
    
    /**
     * @deprecated  This method should not be used, it will be removed in a futrue version.
     */
    @Deprecated
    public String getRenderLeftMenuSections() {//TODO: Remove this method once the templates are modified to use JSON serialization
        ArrayList<MenuSection>   sourceList = this.getLeftMenuSections();
        
        StringBuilder sb = new StringBuilder();
        if (sourceList != null && sourceList.size() > 0){
            sb.append("sections: [");
            for (MenuSection menuSection : sourceList)
            {
                // add section name
                sb.append(" {sectionName: '");
                sb.append(StringEscapeUtils.escapeHtml4(menuSection.getName()));
                sb.append("',");
        
                // add section link
                if (!Utility.isNullOrEmpty(menuSection.getLink())) {
                    sb.append("sectionLink: '" + BaseUtil.encodeUrl(StringEscapeUtils.escapeHtml4(menuSection.getLink())) + "',");
                    sb.append(menuSection.isOpenInNewWindow() ? "newWindow: true," : "");
                }
                
                // add menu items
                if (menuSection.getItems() != null && menuSection.getItems().size() > 0) {
                    sb.append(" menuLinks: [");
                    for (Link lk : menuSection.getItems()) {
                        sb.append("{href: '");
                        sb.append(BaseUtil.encodeUrl(StringEscapeUtils.escapeHtml4(lk.getHref())));
                        sb.append("', text: '");
                        sb.append(StringEscapeUtils.escapeHtml4(lk.getText()));
                        sb.append("'");
                        //sb.append("'},");
                        
                        // add 3rd level sub items, NOTE: template is limiting to 3 levels deep even if the POJO class allows more
                        if (lk instanceof MenuItem) {
                            MenuItem mi = (MenuItem)lk;
                            sb.append(mi.isOpenInNewWindow() ? ", newWindow: true" : "");
                            
                            if (mi.getSubItems() != null && mi.getSubItems().size() > 0) {
                                sb.append(", subLinks: [");
                                for (MenuItem sublk : mi.getSubItems()) {
                                    sb.append("{subhref: '");
                                    sb.append(BaseUtil.encodeUrl(StringEscapeUtils.escapeHtml4(sublk.getHref())));
                                    sb.append("', subtext: '");
                                    sb.append(StringEscapeUtils.escapeHtml4(sublk.getText()));
                                    sb.append("',");
                                    sb.append(sublk.isOpenInNewWindow() ? " newWindow: true" : "");
                                    sb.append("},");
                                }
                                sb.append("]");
                            }
                        }
                        sb.append("},");
                    }
                    sb.append("]");
                }
                sb.append("},");
            }
            sb.append("]");
        }
        
        return sb.toString();
    }
    
    
    /**
     * @deprecated  This method should not be used, it will be removed in a futrue version.
     */
    @Deprecated
    public String getBreadcrumbsList() { //TODO: Remove this method once the templates are modified to use JSON serialization
        ArrayList<Breadcrumb>   sourceList = this.getBreadcrumbs();
    
        StringBuilder sb = new StringBuilder();

        if (sourceList != null && sourceList.size() > 0)
        {
            sb.append("breadcrumbs: [");
            for (Breadcrumb lk : sourceList)
            {
                sb.append("{title: '");
                sb.append(StringEscapeUtils.escapeHtml4(lk.getTitle()));
                if (!Utility.isNullOrEmpty(lk.getHref()))
                {
                    sb.append("', href: '");
                    sb.append(BaseUtil.encodeUrl(StringEscapeUtils.escapeHtml4(lk.getHref())));
                }

                if (!Utility.isNullOrEmpty(lk.getAcronym()))
                {
                    sb.append("', acronym: '");
                    sb.append(StringEscapeUtils.escapeHtml4(lk.getAcronym()));
                }
                sb.append("'},");
            }
            sb.append("],");
        }
        
        return sb.toString();
    }

    /**
     * @deprecated  This method should not be used, it will be removed in a futrue version.
     */
    @Deprecated
    public String getDateModifiedOrVersionIdentifierValue() { //TODO: Remove this method once the templates are modified to use JSON serialization
        String dateModifiedValue = this.getDateModifiedJSONValue();
        if (!Utility.isNullOrEmpty(dateModifiedValue)) {
            return "dateModified: \"" + dateModifiedValue + "\",";
        } else {
            String versionIdentifierValue = this.getVersionIdentifier();
            if (!Utility.isNullOrEmpty(versionIdentifierValue)) {
                return "versionIdentifier: \"" + versionIdentifierValue + "\",";
            } else {
                return "dateModified: \"" + (new SimpleDateFormat("yyyy-MM-dd")).format(new java.util.Date(0)) + "\",";
            }
        }
    }
}
