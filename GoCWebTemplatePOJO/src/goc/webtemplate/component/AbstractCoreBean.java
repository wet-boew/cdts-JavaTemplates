package goc.webtemplate.component;

import java.util.ArrayList;
import java.util.Date;
import java.util.MissingResourceException;

import org.apache.commons.lang3.StringEscapeUtils;

import com.google.gson.Gson;

import goc.webtemplate.ApplicationTitle;
import goc.webtemplate.Breadcrumb;
import goc.webtemplate.Constants;
import goc.webtemplate.FooterLink;
import goc.webtemplate.LeavingSecureSiteWarning;
import goc.webtemplate.Link;
import goc.webtemplate.MenuSection;
import goc.webtemplate.SessionTimeout;
import goc.webtemplate.Utility;

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
     * Flag indicating whether the onWebTemplateInitialize has already been called. 
     */
    private boolean initialized = false;    
    
    //-------------------------------------------------------
    //---[ Main template instance variables
    //-------------------------------------------------------
//TODO: Review... are unindented ones still needed?    
    private String cdnEnvironment = this.getResourceBundleString("cdn", "cdn_environment");
    private String templateVersion = this.getResourceBundleString("cdn", "wettemplate_version");
    private String mainTheme = this.getResourceBundleString("cdn", "wettemplate_theme");
    private String subTheme = this.getResourceBundleString("cdn", "wettemplate_subtheme");
    private boolean useHttps = Boolean.parseBoolean(this.getResourceBundleString("cdn", "webtemplate_usehttps"));
    private boolean loadjQueryFromGoogle = Boolean.parseBoolean(this.getResourceBundleString("cdn", "wettemplate_loadjqueryfromgoogle"));
private String cdnLocalPathRender = null; //see getRenderLocalPath
private String cdnLocalPath = null;
    private String headerTitle = "";
    private ApplicationTitle applicationTitle = new ApplicationTitle();
    private String langLinkUrl = "";
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
    private boolean showLanguageLink = Boolean.parseBoolean(this.getResourceBundleString("cdn", "goc.webtemplate.showlanguagelink"));
    private boolean showPostContent = Boolean.parseBoolean(this.getResourceBundleString("cdn", "goc.webtemplate.showpostcontent"));
    private boolean showFeedbackLink = Boolean.parseBoolean(this.getResourceBundleString("cdn", "goc.webtemplate.showfeedbacklink"));
    private boolean showSharePageLink = Boolean.parseBoolean(this.getResourceBundleString("cdn", "goc.webtemplate.showsharepagelink"));
    private boolean showFeature = Boolean.parseBoolean(this.getResourceBundleString("cdn", "goc.webtemplate.showfeatures"));
    private ArrayList<Constants.SocialMediaSites> sharePageMediaSites = new ArrayList<Constants.SocialMediaSites>();
    private ArrayList<Breadcrumb> breadCrumbsList = new ArrayList<Breadcrumb>();
    private ArrayList<String> htmlHeaderElements = new ArrayList<String>();
    private ArrayList<String> htmlBodyElements = new ArrayList<String>();
    private String staticFilePath = this.getResourceBundleString("cdn", "goc.webtemplate.staticfileslocation");    
    private String contentCreatorTitle = "";
    private boolean showGlobalNav = Boolean.parseBoolean(this.getResourceBundleString("cdn", "goc.webtemplate.showglobalnav"));
    private boolean showSiteMenu = Boolean.parseBoolean(this.getResourceBundleString("cdn", "goc.webtemplate.showsitemenu"));
    private String  customSiteMenuUrl = this.getResourceBundleString("cdn", "goc.webtemplate.customsitemenuurl");
    private String  signInLinkUrl = this.getResourceBundleString("cdn", "goc.webtemplate.signinlinkurl");
    private String  signOutLinkUrl = this.getResourceBundleString("cdn", "goc.webtemplate.signoutlinkurl");
    private boolean showSecureIcon = false;
    private boolean showSignInLink = false;
    private boolean showSignOutLink = false;
    private ArrayList<FooterLink> customFooterLinks = new ArrayList<FooterLink>();
/**
 * Allows for a custom search to be used in the application, you must contact CDTS to have one created.
 */
    private String customSearch = this.getResourceBundleString("cdn", "goc.webtemplate.customsearch");;
    private SessionTimeout sessionTimeoutConfigurations = null; //sessionTimeoutEnabled = Boolean.parseBoolean(this.getResourceBundleString("cdn", "session.timeout.enabled"));
    private ArrayList<MenuSection> leftMenuSections = new ArrayList<MenuSection>();
    private String privacyLinkUrl = "";
    private String termsConditionsLinkUrl = "";
    //-------------------------------------------------------

    //-------------------------------------------------------
    //---[ Methods with framework-dependant implementation
    //-------------------------------------------------------
    public abstract java.util.ResourceBundle getResourceBundle();
    public abstract String getResourceBundleString(String resourceBundleName, String resourceBundleKey) throws MissingResourceException;
    public abstract String getTwoLetterCultureLanguage();   
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
    
//TODO: Javadoc for these    
    public String getCDNEnvironment() {
        this.initializeOnce();
        return (Utility.isNullOrEmpty(this.cdnEnvironment) ? "Akamai" : StringEscapeUtils.escapeHtml4(this.cdnEnvironment));
    }
    
    public void setCDNEnvironment(String value) {
        this.cdnEnvironment = value;
    }
    
    public boolean getUseHttps() {
        this.initializeOnce();
        return this.useHttps;
    }
    
    public void setUseHttps(boolean value) {
        this.useHttps = value;
    }
    
    public String getTemplateVersion() {
        this.initializeOnce();
        return StringEscapeUtils.escapeHtml4(this.templateVersion);
    }

    public void setTemplateVersion(String value) {
        this.templateVersion = value;
    }

    public String getTheme() 
    {
        this.initializeOnce();
        return (!Utility.isNullOrEmpty(this.mainTheme) ? StringEscapeUtils.escapeHtml4(this.mainTheme) : "");
    }

    public void setTheme(String value)
    {
        this.mainTheme = value; //TODO: variable name!!
    }
        
    public String getSubTheme() 
    {
        this.initializeOnce();
        return (!Utility.isNullOrEmpty(this.subTheme) ? StringEscapeUtils.escapeHtml4(this.subTheme) : "");
    }
    
    public void setSubTheme(String value)
    {
        this.subTheme = value;
    }

    
    private String getPartialCDNPath() 
    {
        String https = this.getUseHttps() ? "s" : "";
        String envUrl = this.getResourceBundleString("cdn", "cdn_" + this.getCDNEnvironment().toLowerCase() + "_url");
        envUrl = (Utility.isNullOrEmpty(envUrl) ? "" : envUrl);
        String templateVersion = this.getTemplateVersion();
        String cdnUrl;
        
        if (this.getCDNEnvironment().toLowerCase().equals("akamai")) {
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
        
        return BaseUtil.encodeUrl(StringEscapeUtils.escapeHtml4(cdnUrl));
    }
    
}
