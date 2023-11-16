package goc.webtemplate.component;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.MissingResourceException;

import org.apache.commons.text.StringEscapeUtils;

import goc.webtemplate.Breadcrumb;
import goc.webtemplate.Constants;
import goc.webtemplate.ContextualFooter;
import goc.webtemplate.CustomSearch;
import goc.webtemplate.FooterLink;
import goc.webtemplate.FooterSection;
import goc.webtemplate.HeaderMenu;
import goc.webtemplate.IFooterSection;
import goc.webtemplate.InfoBanner;
import goc.webtemplate.IntranetTitle;
import goc.webtemplate.LanguageLink;
import goc.webtemplate.LeavingSecureSiteWarning;
import goc.webtemplate.Link;
import goc.webtemplate.MenuItem;
import goc.webtemplate.MenuSection;
import goc.webtemplate.SessionTimeout;
import goc.webtemplate.SplashPageInfo;
import goc.webtemplate.Utility;
import goc.webtemplate.WebAnalyticsInfo;

import static goc.webtemplate.component.JsonRenderer.gson;

import goc.webtemplate.component.jsonentities.AppFooter;
import goc.webtemplate.component.jsonentities.AppTop;
import goc.webtemplate.component.jsonentities.CDTSEnvironment;
import goc.webtemplate.component.jsonentities.CDTSEnvironmentList;
import goc.webtemplate.component.jsonentities.FeedbackLink;
import goc.webtemplate.component.jsonentities.Footer;
import goc.webtemplate.component.jsonentities.IPreFooter;
import goc.webtemplate.component.jsonentities.PreFooter;
import goc.webtemplate.component.jsonentities.SecMenu;
import goc.webtemplate.component.jsonentities.SecMenuItem;
import goc.webtemplate.component.jsonentities.Setup;
import goc.webtemplate.component.jsonentities.Setup.Mode;
import goc.webtemplate.component.jsonentities.SetupBase;
import goc.webtemplate.component.jsonentities.ShareList;
import goc.webtemplate.component.jsonentities.Splash;
import goc.webtemplate.component.jsonentities.Top;
import goc.webtemplate.component.jsonentities.UnilingualErrorPreFooter;

/**
 * This is the base class from all template configuration beans, inherited by both
 * the specific version of the BaseCoreBean class.
 *
 * This class defines the complete list of configuration/customization items
 * that can be overriden in either the specific version of the implementation.
 *
 * @author pierre.lupien
 *
 */
public abstract class AbstractCoreBean {
    /**
     * Holds the table of CDTS environment configuration objects (loaded the first time it is accessed).
     */
    private static HashMap<String, CDTSEnvironment> cdtsEnvironments = null;
    /**
     * Holds the table of theme/version -> fileName -> SRIHash
     */
    private static HashMap<String, HashMap<String, String>> cdtsSRIHashes = null;

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

    private enum Themes { GCWEB, GCINTRANET }

    //-------------------------------------------------------
    //---[ Main template instance variables
    //-------------------------------------------------------
    private String cdnEnvironment = this.getResourceBundleString("cdn", "cdn_environment");
    private String cdtsCdnEnv = null; // initialized in getCdtsCdnEnv
    private String templateVersion = null; // initialized in getTemplateVersion
    private String theme = null; // initialized in getTheme
    private String subTheme = null; // initialized in getSubTheme
    private boolean useHttps = !"false".equals(this.getResourceBundleString("cdn", "webtemplate_usehttps")); //defaults to true if not specified/is not exactly "false"
    private boolean useSRI = !"false".equals(this.getResourceBundleString("cdn", "webtemplate_usesri")); //defaults to true if not specified/is not exactly "false"
    private boolean loadjQueryFromGoogle = Boolean.parseBoolean(this.getResourceBundleString("cdn", "wettemplate_loadjqueryfromgoogle"));
    private String cdnLocalPath = null; //initialized in getLocalPath
    private String headerTitle = "";
    private Link applicationTitle = new Link();
    private IntranetTitle intranetTitle = null;
    private String languageLinkUrl = "";
    private String feedbackUrl = this.getResourceBundleString("cdn", "goc.webtemplate.feedbackurl");
    private String feedbackUrlFr = this.getResourceBundleString("cdn", "goc.webtemplate.feedbackurl_fr");
    private List<Link> contactLinks = null;
    private LeavingSecureSiteWarning leavingSecureSiteWarning = new LeavingSecureSiteWarning(
                                            Boolean.parseBoolean(this.getResourceBundleString("cdn", "leavingsecuresitewarning.enabled")),
                                            Boolean.parseBoolean(this.getResourceBundleString("cdn", "leavingsecuresitewarning.displaymodalwindow")),
                                            "",
                                            this.getResourceBundleString("cdn", "leavingsecuresitewarning.redirecturl"),
                                            this.getResourceBundleString("cdn", "leavingsecuresitewarning.excludeddomains"), null, null, null, JsonValueUtils.getBooleanValue(this.getResourceBundleString("cdn", "leavingsecuresitewarning.displaymodalfornewwindow"), true), null);
    private Date dateModified = null;
    private String screenIdentifier = "";
    private String versionIdentifier = "";
    private boolean showSearch = Boolean.parseBoolean(this.getResourceBundleString("cdn", "goc.webtemplate.showsearch"));
    private boolean showPreContent = Boolean.parseBoolean(this.getResourceBundleString("cdn", "goc.webtemplate.showprecontent"));
    private boolean showPostContent = Boolean.parseBoolean(this.getResourceBundleString("cdn", "goc.webtemplate.showpostcontent"));
    private boolean showLanguageLink = Boolean.parseBoolean(this.getResourceBundleString("cdn", "goc.webtemplate.showlanguagelink"));
    private boolean showFeedbackLink = Boolean.parseBoolean(this.getResourceBundleString("cdn", "goc.webtemplate.showfeedbacklink"));
    private boolean showSharePageLink = Boolean.parseBoolean(this.getResourceBundleString("cdn", "goc.webtemplate.showsharepagelink"));
    private boolean showFeatures = Boolean.parseBoolean(this.getResourceBundleString("cdn", "goc.webtemplate.showfeatures"));
    private List<Constants.SocialMediaSites> sharePageMediaSites = new ArrayList<Constants.SocialMediaSites>();
    private List<Breadcrumb> breadcrumbs = new ArrayList<Breadcrumb>();
    private List<String> htmlHeaderElements = new ArrayList<String>();
    private List<String> htmlBodyElements = new ArrayList<String>();
    private String staticFallbackFilePath = this.getResourceBundleString("cdn", "goc.webtemplate.staticfileslocation");
    private String  customSiteMenuUrl = this.getResourceBundleString("cdn", "goc.webtemplate.customsitemenuurl");
    private List<MenuItem> menuLinks = null;
    private String  signInLinkUrl = this.getResourceBundleString("cdn", "goc.webtemplate.signinlinkurl");
    private String  signOutLinkUrl = this.getResourceBundleString("cdn", "goc.webtemplate.signoutlinkurl");
    private String  appSettingsUrl = this.getResourceBundleString("cdn", "goc.webtemplate.appsettingsurl");
    private boolean showSignInLink = false;
    private boolean showSignOutLink = false;
    private List<FooterLink> customFooterLinks = new ArrayList<FooterLink>();
    private List<FooterSection> customFooterSections = new ArrayList<FooterSection>();
    private CustomSearch customSearch = null;
    private SessionTimeout sessionTimeoutConfiguration = null; //initialization in get method
    private SplashPageInfo splashPageInfo = null; //initialization in get method
    private List<MenuSection> leftMenuSections = new ArrayList<MenuSection>();
    private FooterLink privacyLink = new FooterLink();
    private FooterLink termsConditionsLink = new FooterLink();
    private WebAnalyticsInfo webAnalytics = new WebAnalyticsInfo(Boolean.parseBoolean(this.getResourceBundleString("cdn", "goc.webtemplate.usewebanalytics"))); //will be false if not specified in config
    private boolean gcToolsModal = false;
    private String footerPath = null;
    private boolean hidePlaceholderMenu = false;
    private InfoBanner infoBanner = null;
    private ContextualFooter contextualFooter = null;
    private boolean hideMainFooter = false;
    private boolean hideCorporateFooter = false;
    private HeaderMenu headerMenu = null;
    //-------------------------------------------------------

    //-------------------------------------------------------
    //---[ Caching of calculated values
    //-------------------------------------------------------
    private String          partialCDNPath = null;
    private CDTSEnvironment currentCDTSEnvironment = null;
    private HashMap<String, String> currentSRIHashes = null;
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


    /**
     * Returns the CDTSEnvironment object with the specified name, otherwise returns null.
     * @param name The name of the environment to look for, must be lowercase.
     */
    private static CDTSEnvironment getCDTSEnvironmentByName(String name) {
        if (cdtsEnvironments == null) loadCDTSEnvironments();
        return cdtsEnvironments.get(name);
    }

    /**
     * Returns the table of fileName -> SRIHash for the specified theme and version.
     * @param theme Name of the theme, must be lowercase
     * @param version Version "name" (e.g. v9_9_9)
     */
    private static HashMap<String, String> getCDTSSRIHashes(String theme, String version) {
        if (cdtsSRIHashes == null) loadCDTSEnvironments();
        return cdtsSRIHashes.get(theme + "/" + version);
    }

    /**
     * Load environment json file (converts all environment names to lowercase)
     */
    private static synchronized void loadCDTSEnvironments() { //note the "static synchronized"... i.e. synchronized on AbstractCoreBean.class
        HashMap<String, CDTSEnvironment>            map = new HashMap<String, CDTSEnvironment>();
        HashMap<String, HashMap<String, String>>    hashes = new HashMap<String, HashMap<String, String>>();
        CDTSEnvironmentList                         tmpList;
        java.io.InputStream                         is = null;

        try {
            is = AbstractCoreBean.class.getResourceAsStream("/goc/webtemplate/global/config/CDTSEnvironments.json");
            if (is == null) {
                System.err.println("ERROR: CDTSEnvironment.json could not be read. Environment list will be empty. (/goc/webtemplate/global/config/CDTSEnvironments.json not found in jar)");
                cdtsEnvironments = map;
                cdtsSRIHashes = hashes;
                return;
            }

            tmpList = gson.fromJson(new java.io.InputStreamReader(is, "UTF-8"), CDTSEnvironmentList.class);
            for (CDTSEnvironment cdtsenv: tmpList.getEnvironments()) {
                //(convert name to all lower-case)
                map.put(cdtsenv.getName().toLowerCase(), cdtsenv);
            }
            hashes = tmpList.getThemeSRIHashes();
        }
        catch (Exception ex) {
            System.err.println("ERROR: CDTSEnvironment.json could not be read. Environment list will be empty. Exception:  " + ex);
        }
        finally {
            try {is.close();} catch (Throwable e) {}
        }

        cdtsEnvironments = map;
        cdtsSRIHashes = hashes;
    }

    private CDTSEnvironment getCurrentCDTSEnvironment() {
       if (this.currentCDTSEnvironment == null) this.currentCDTSEnvironment = getCDTSEnvironmentByName(this.getCDNEnvironment().toLowerCase());
       return this.currentCDTSEnvironment;
    }

    private HashMap<String, String> getCurrentCDTSSRIHashes() {
        if (this.currentSRIHashes == null) {
            HashMap<String, String> sriMap = getCDTSSRIHashes(this.getTheme(), this.getTemplateVersion());
            this.currentSRIHashes = sriMap != null? sriMap: new HashMap<String, String>(); // if not found, initialize to empty map
        }
        return this.currentSRIHashes;
    }

    private void initializeOnce() {
        if (!this.initialized) {
            this.initialized = true; //do it BEFORE calling onWebTemplateInitialize to avoid endless loops
            this.onWebTemplateInitialize();
        }
    }

    /**
     * Returns the value of cdn_XXXX_localpath (where XXXX is the cdn environment), or blank if not specified.
     */
    public String getLocalPath()
    {
        CDTSEnvironment currentEnvironment;
        String          templateVersion;

        if (this.cdnLocalPath == null)
        {
            //cdnLocalPath is not set, is it overriden in properties?
            this.cdnLocalPath = this.getResourceBundleString("cdn", "cdn_" + this.getCDNEnvironment().toLowerCase() + "_localpath");

            if (Utility.isNullOrEmpty(this.cdnLocalPath))
            {
                //...no value from properties, load from current environment
                currentEnvironment = this.getCurrentCDTSEnvironment();
                if (currentEnvironment != null) this.cdnLocalPath = currentEnvironment.getLocalPath();
            }

            if (!Utility.isNullOrEmpty(this.cdnLocalPath))
            {
                //we finally have a value, format it correctly
                templateVersion = this.getTemplateVersion();
                if (templateVersion == null) templateVersion = "";

                this.cdnLocalPath = MessageFormat.format(this.cdnLocalPath, this.getTheme(), templateVersion);
            }
            else
            {
                this.cdnLocalPath = ""; //(make sure it is blank, not null)
            }
        }

        return this.cdnLocalPath;
    }

    /**
     * Returns the path of the custom footer
     */
    public String getFooterPath() {
        this.initializeOnce();
        return this.footerPath;
    }

    /**
     * Sets the path of the custom footer
     */
    public void setFooterPath(String footerPath) {
        this.footerPath = footerPath;
    }

    //NOTE: Settign this method's visibility to "package" so it is accessible to BaseComponent.  Once BaseComponent is gone, this can go back to being private
    /*package*/ String getPartialCDNPath() {
        CDTSEnvironment environment;
        String          envName;
        String          envUrl = "";
        String          envTheme = "";
        String          https = null;
        String          envRun;
        String          templateVersion;
        String          tmpS;

        if (this.partialCDNPath != null) return this.partialCDNPath; //return cached value if found

        envName = this.getCDNEnvironment().toLowerCase();

        //---[ Look for environment in our static list...
        environment = getCDTSEnvironmentByName(envName);
        this.currentCDTSEnvironment = environment; //might as well set our cached environment value
        if (environment != null)
        {
            //...environment was found, setup accordingly
            envUrl = environment.getPath();
            https = environment.isEncryptionModifiable() && this.getUseHttps()?  "s": "";
        }
        envTheme = this.getTheme(); //getTheme() will load from current environment or use override from properties file, so all is good

        //---[ whether or not an envirionment was found in previous step, settings are overridable if found in properties file
        tmpS = this.getResourceBundleString("cdn", "cdn_" + envName + "_url");
        if (!Utility.isNullOrEmpty(tmpS)) envUrl = tmpS;
        if (https == null) https = this.getUseHttps() ? "s" : "";

        templateVersion = this.getTemplateVersion();
        envRun = "";
        if (Utility.isNullOrEmpty(templateVersion) || templateVersion.equals("rn")) {
            if ((environment != null) && environment.isVersionRnCombined()) {
                templateVersion = "rn/";
                //(envRun stays blank)
            } else {
                envRun = "rn";
                templateVersion = "";
            }
        } else {
            templateVersion = templateVersion + "/";
            envRun = "app";
        }

        this.partialCDNPath = MessageFormat.format(envUrl, https, envRun, envTheme, templateVersion);

        return this.partialCDNPath;
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

        //---[ The environment name changed, we have a few cached values to clear so they are re-loaded from the proper environment
        this.currentCDTSEnvironment = null;
        this.currentSRIHashes = null;
        this.cdtsCdnEnv = null;
        this.partialCDNPath = null;
        this.cdnLocalPath = null;
        this.theme = null;
        this.subTheme = null;
    }

    /**
     * Returns the "cdnEnv" value that the closure template is expecting. This value
     * is different than the "CDN Evironment" used to select which CDTS is targetted.
     *
     * Set at application level via "cdn_????_env" in cdn.properties,
     * can be overriden programmatically.
     */
    public String getCdtsCdnEnv() {
        CDTSEnvironment currentEnvironment;

        this.initializeOnce();

        if (this.cdtsCdnEnv == null)
        {
            //cdtsCdnEnv is not set, is it overriden in properties?
            this.cdtsCdnEnv = this.getResourceBundleString("cdn", "cdn_" + this.getCDNEnvironment().toLowerCase() + "_env");

            if (Utility.isNullOrEmpty(this.cdtsCdnEnv))
            {
                //...no value from properties, load from current environment
                currentEnvironment = this.getCurrentCDTSEnvironment();
                if (currentEnvironment != null) this.cdtsCdnEnv = currentEnvironment.getCDN();
            }

            if (Utility.isNullOrEmpty(this.cdtsCdnEnv)) this.cdtsCdnEnv = "prod"; //default to "prod"
        }

        return this.cdtsCdnEnv;
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
        return StringEscapeUtils.escapeHtml4(this.staticFallbackFilePath);
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
        this.staticFallbackFilePath = value;
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
     * Returns whether or not to include Sub Resource Integrity when inserting CDTS scripts and css.
     *
     * Set at application level via "webtemplate_usesri" property in cdn.properties,
     * can be overriden programatically.
     */
    public boolean getUseSRI() {
        this.initializeOnce();
        return this.useSRI;
    }

    /**
     * Sets whether or not to include Sub Resource Integrity when inserting CDTS scripts and css.
     *
     * Set at application level via "webtemplate_usesri" property in cdn.properties,
     * can be overriden programatically.
     */
    public void setUseSRI(boolean value) {
        this.useSRI = value;
    }

    /**
     * Returns the version of the CDN files to use to build the page. (e.g v5_0_0)
     *
     * Set at application level via "wettemplate_version" property in cdn.properties,
     * can be overriden programatically.
     */
    public String getTemplateVersion() {
        this.initializeOnce();

        if (this.templateVersion == null) {

        	this.templateVersion = this.getResourceBundleString("cdn", "wettemplate_version");

        	if (Utility.isNullOrEmpty(this.templateVersion)) {
        		this.templateVersion = Constants.CDTS_DEFAULT_VERSION;
        	}
        }

        return StringEscapeUtils.escapeHtml4(this.templateVersion);
    }

    /**
     * Sets the version of the CDN files to use to build the page. (e.g v5_0_0)
     *
     * Set at application level via "wettemplate_version" property in cdn.properties,
     * can be overriden programatically.
     */
    public void setTemplateVersion(String value) {
        this.templateVersion = value;
        this.currentSRIHashes = null;
    }

    /**
     * Returns the theme to use to build the page. (e.g. gcweb)
     *
     * Set at application level via "wettemplate_theme" property in cdn.properties,
     * can be overriden programatically.
     */
    public String getTheme() {
        CDTSEnvironment currentEnvironment;

        this.initializeOnce();

        if (this.theme == null)
        {
            //theme is not set, is it overriden in properties?
            this.theme = this.getResourceBundleString("cdn", "wettemplate_theme");

            if (Utility.isNullOrEmpty(this.theme))
            {
                //...no value from properties, load from current environment
                currentEnvironment = this.getCurrentCDTSEnvironment();
                if (currentEnvironment != null) this.theme = currentEnvironment.getTheme();
            }
        }

        return StringEscapeUtils.escapeHtml4(this.theme);
    }

    /**
     * Sets the theme to use to build the page. (e.g. gcweb)
     *
     * Set at application level via "wettemplate_theme" property in cdn.properties,
     * can be overriden programatically.
     */
    public void setTheme(String value) {
        this.theme = value;
        this.currentSRIHashes = null;
    }

    /**
     * Returns the sub-theme to use to build the page. (e.g. esdc)
     *
     * Set at application level via "wettemplate_subtheme" property in cdn.properties,
     * can be overriden programatically.
     */
    public String getSubTheme() {
        CDTSEnvironment currentEnvironment;

        this.initializeOnce();

        if (this.subTheme == null)
        {
            //subTheme is not set, is it overriden in properties?
            this.subTheme = this.getResourceBundleString("cdn", "wettemplate_subtheme");

            if (Utility.isNullOrEmpty(this.subTheme))
            {
                //...no value from properties, load from current environment
                currentEnvironment = this.getCurrentCDTSEnvironment();
                if (currentEnvironment != null) this.subTheme = currentEnvironment.getSubTheme();
            }
        }

        return StringEscapeUtils.escapeHtml4(this.subTheme);
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
     * Returns the title that will be displayed in the header above the top menu.
     */
    public Link getApplicationTitle() {
        this.initializeOnce();
        return this.applicationTitle;
    }

    /**
     * Sets the title that will be displayed in the header above the top menu.
     */
    public void setApplicationTitle(Link value) {
        this.applicationTitle = value;
    }

    /**
     * Returns the "intranet title" (displayed at very top) for ESDC intranet theme.
     */
    public IntranetTitle getIntranetTitle() {
        return this.intranetTitle;
    }

    /**
     * Sets the "intranet title" (displayed at very top) for ESDC intranet theme.
     */
    public void setIntranetTitle(IntranetTitle value) {
        this.intranetTitle = value;
    }

    /**
     * Returns the list of breadcrumb links.
     */
    public List<Breadcrumb> getBreadcrumbs() {
        this.initializeOnce();
        return this.breadcrumbs;
    }

    /**
     * Sets the list of breadcrumb links.
     */
    public void setBreadcrumbs(List<Breadcrumb> value) {
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
    public List<String> getHtmlHeaderElements() {
        this.initializeOnce();
        return this.htmlHeaderElements;
    }

    /**
     * Sets the list of html elements to add to the header tag.
     *
     * This list is used to add metatags, css, js etc. to web pages.
     */
    public void setHtmlHeaderElements(List<String> value) {
        this.htmlHeaderElements = value;
    }

    /**
     * Returns the list of html elements to add to the body tag.
     *
     * This list is used to add metatags, css, js etc. to web pages.
     */
    public List<String> getHtmlBodyElements() {
        this.initializeOnce();
        return this.htmlBodyElements;
    }

    /**
     * Sets the list of html elements to add to the body tag.
     *
     * This list is used to add metatags, css, js etc. to web pages.
     */
    public void setHtmlBodyElements(List<String> value) {
        this.htmlBodyElements = value;
    }

    /**
     * Returns the title of page.
     * Note that '- Canada.ca'  will be automatically added to all pages served under the 'gcweb' theme.
     */
    public String getHeaderTitle() {
        CDTSEnvironment currentEnvironment;

        this.initializeOnce();

        if (this.headerTitle == null) this.headerTitle = "";

        currentEnvironment = this.getCurrentCDTSEnvironment();
        if ( (currentEnvironment != null) && !Utility.isNullOrEmpty(currentEnvironment.getAppendToTitle()) &&
              (!this.headerTitle.endsWith(currentEnvironment.getAppendToTitle())) ) {
            return StringEscapeUtils.escapeHtml4(this.headerTitle + " " + currentEnvironment.getAppendToTitle()); //NOTE: Adding a space... questionable... maybe JSON file should be changed instead?
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
     * Specific implementation of the Template, but can be set by application programmatically.
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
     * Specific implementation of the Template, but can be set by application programmatically.
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
    public List<MenuSection> getLeftMenuSections() {
        this.initializeOnce();
        return this.leftMenuSections;
    }

    /**
     * Sets the list of menu items for the left menu.
     */
    public void setLeftMenuSections(List<MenuSection> value) {
        this.leftMenuSections = value;
    }

    /**
     * Returns the Link to be used for the Privacy link in transactional mode.
     *
     *  @since 1.31.0
     */
    public FooterLink getPrivacyLink() {
        this.initializeOnce();
        return this.privacyLink;
    }

    /**
     * Sets the link to be used for the Privacy link in transactional mode.
     *
     *  @since 1.31.0
     */
    public void setPrivacyLink(FooterLink value) {
        this.privacyLink = value;
    }

    /**
     * Returns whether the features of the footer are to be displayed.
     *
     * Set at application level via "goc.webtemplate.showfeatures" property in cdn.properties,
     * can be overriden programatically.
     */
    public boolean getShowFeatures() {
        this.initializeOnce();
        return this.showFeatures;
    }

    /**
     * Sets whether the features of the footer are to be displayed.
     *
     * Set at application level via "goc.webtemplate.showfeatures" property in cdn.properties,
     * can be overriden programatically.
     */
    public void setShowFeatures(boolean value) {
        this.showFeatures = value;
    }

    /**
     * Returns the list of contact links, null if no contact list is currently specified.
     */
    public List<Link> getContactLinks() {
        this.initializeOnce();
        return this.contactLinks;
    }

    /**
     * Sets the list of contact links to the specified value.
     */
    public void setContactLinks(List<Link> value) {
    	this.contactLinks = value;
    }

    /**
     * Convenience method to specify a single contact link.
     */
    public void setContactLink(Link value) {
    	if (this.contactLinks == null) this.contactLinks = new ArrayList<Link>();
    	this.contactLinks.clear();
        this.contactLinks.add(value);
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
    public CustomSearch getCustomSearch() {
        this.initializeOnce();
        return this.customSearch;
    }

    /**
     * Allows for a custom search to be used in the application, you must contact CDTS to have one created.
     */
    public void setCustomSearch(CustomSearch value) {
        this.customSearch = value;
    }

    /**
     * Returns the list of items to be displayed in the Share Page window.
     * Set by application programmatically
     */
    public List<Constants.SocialMediaSites> getSharePageMediaSites() {
        this.initializeOnce();
        return this.sharePageMediaSites;
    }

    /**
     * Sets the list of items to be displayed in the Share Page window.
     * Set by application programmatically
     */
    public void setSharePageMediaSites(List<Constants.SocialMediaSites> value) {
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
     * Returns the french FeedBack link URL.
     *
     * Set at application level via "goc.webtemplate.feedbackurl_fr" property in cdn.properties,
     * can be overriden programatically.
     */
    public String getFeedbackUrlFr() {
        this.initializeOnce();
        return this.feedbackUrlFr;
    }

    /**
     * Sets the french FeedBack link URL.
     *
     * Set at application level via "goc.webtemplate.feedbackurl_fr" property in cdn.properties,
     * can be overriden programatically.
     */
    public void setFeedbackUrlFr(String value) {
        this.feedbackUrlFr = value;
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
    public SessionTimeout getSessionTimeoutConfiguration() {
        this.initializeOnce();

        //if not overriden, set from config
        if (this.sessionTimeoutConfiguration == null) {
            java.util.ResourceBundle bundle = this.getResourceBundle();
            this.sessionTimeoutConfiguration = this.buildDefaultSessionTimeoutConfigurations(bundle);
        }

        return this.sessionTimeoutConfiguration;
    }

    /**
     * Sets the configuration object containing the various session timeout settings.
     *
     * Can be set in either cdn.properties or by application programmatically
     */
    public void setSessionTimeoutConfiguration(SessionTimeout value) {
        this.sessionTimeoutConfiguration = value;
    }

    /**
     * Returns the configuration object containing the various splash page settings.
     *
     * Set by application programmatically.
     */
    public SplashPageInfo getSplashPageInfo() {
        this.initializeOnce();

        //if not override, create a default/empty object
        if (this.splashPageInfo == null) this.splashPageInfo = new SplashPageInfo();

        return this.splashPageInfo;
    }

    /**
     * Returns the configuration object containing the various splash page settings.
     *
     * Sets by application programmatically.
     */
    public void setSplashPageInfo(SplashPageInfo value) {
        this.splashPageInfo = value;
    }

    /**
     * Returns the link to be used for the Terms & Conditions link in transactional mode.
     *
     * Set by application programmatically.
     */
    public FooterLink getTermsConditionsLink() {
        this.initializeOnce();
        return this.termsConditionsLink;
    }

    /**
     * Sets the link to be used for the Terms & Conditions link in transactional mode.
     *
     * Set by application programmatically.
     */
    public void setTermsConditionsLink(FooterLink value) {
        this.termsConditionsLink = value;
    }

    /**
     * Returns the configuration info for the Adobe Analytics (AA).
     *
     * @since 1.32.0
     */
    public WebAnalyticsInfo getWebAnalytics() {
        this.initializeOnce();
        return this.webAnalytics;
    }

    /**
     * Sets the configuration info for the Adobe Analytics (AA).
     *
     * @since 1.32.0
     */
    public void setWebAnalytics(WebAnalyticsInfo value) {
        this.webAnalytics = (value != null? value: new WebAnalyticsInfo(false));
    }

    /**
     * Returns whether the GcToolsModal will be displayed
     *
     */
    public boolean getGcToolsModal() {
        return gcToolsModal;
    }

    /**
     * Sets the configuration for the GcTools Modal
     *
     */
    public void setGcToolsModal(boolean value) {
        this.gcToolsModal = value;
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
     * Returns the custom site menu item to be used in place of the standard canada.ca site menu
     * This defaults to null (use standard menu).  For another option for specifying a custom menu,
     * see the getCustomSiteMenuUrl property.
     *
     * Set programatically.
     *
     * Only available in the Application Template
     *
     * @see getCustomSiteMenuUrl()
     */
    public List<MenuItem> getMenuLinks() {
        this.initializeOnce();
        return this.menuLinks;
    }

    /**
     * Sets the custom site menu item to be used in place of the standard canada.ca site menu
     * This defaults to null (use standard menu).  For another option for specifying a custom menu,
     * see the getCustomSiteMenuUrl property.
     *
     * Set programatically.
     *
     * Only available in the Application Template
     *
     * @see setCustomSiteMenuUrl()
     */
    public void setMenuLinks(List<MenuItem> value) {
        this.menuLinks = value;
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
     * Returns the "app settings" URL of the application template.
     *
     * Set at application level via "goc.webtemplate.appsettingsurl" property in cdn.properties,
     * can be overriden programatically.
     *
     * Only available in the Application Template
     */
    public String getAppSettingsUrl() {
        this.initializeOnce();
        return this.appSettingsUrl;
    }

    /**
     * Sets the "app settings" URL of the application template.
     *
     * Set at application level via "goc.webtemplate.appsettingsurl" property in cdn.properties,
     * can be overriden programatically.
     *
     * Only available in the Application Template
     */
    public void setAppSettingsUrl(String value) {
        this.appSettingsUrl = value;
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
     * Returns the custom footer links.
     * If null, uses standard links. If not null, overrides the existing footer links.
     *
     * Set by application programmatically
     * Only available in the Application Template when using the GCweb theme.
     */
    public List<FooterLink> getCustomFooterLinks() {
        this.initializeOnce();
        return this.customFooterLinks;
    }

    /**
     * Sets the custom footer links.
     * If null, uses standard links. If not null, overrides the existing footer links.
     *
     * Set by application programmatically
     * Only available in the Application Template when using the GCweb theme.
     */
    public void setCustomFooterLinks(List<FooterLink> value) {
        this.customFooterLinks = value;
    }

    /**
     * Returns the custom footer sections (ie groups of footer links with a name).
     * If null, uses standard links. If not null, overrides the existing footer links.
     *
     * Set by application programmatically
     * Only available in the Application Template when using a theme other than GCweb.
     */
    public List<FooterSection> getCustomFooterSections() {
        this.initializeOnce();
        return this.customFooterSections;
    }

    /**
     * Sets the custom footer sections (ie groups of footer links with a name).
     * If null, uses standard links. If not null, overrides the existing footer links.
     *
     * Set by application programmatically
     * Only available in the Application Template when using a theme other than GCweb.
     */
    public void setCustomFooterSections(List<FooterSection> value) {
        this.customFooterSections = value;
    }

    /**
     * Returns whether the placeholder menu should be hidden while a custom menu is being loaded
     *
     * can be overriden programatically.
     */
    public boolean getHidePlaceholderMenu() {
        this.initializeOnce();
        return this.hidePlaceholderMenu;
    }

    /**
     * Sets whether the placeholder menu should be hidden while a custom menu is being loaded
     *
     * can be overriden programatically.
     */
    public void setHidePlaceholderMenu(boolean value) {
        this.hidePlaceholderMenu = value;
    }

    /**
     * Returns whether to display an information banner on top of the page
     *
     * can be overriden programatically.
     */
    public InfoBanner getInfoBanner() {
        this.initializeOnce();
        return this.infoBanner;
    }

    /**
     * Sets whether to display an information banner on top of the page
     *
     * can be overriden programatically.
     */
    public void setInfoBanner(InfoBanner value) {
        this.infoBanner = value;
    }

    /**
     * Returns whether to display a contextual footer band that can display up to 3 links
     *
     * can be overriden programatically.
     */
    public ContextualFooter getContextualFooter() {
        this.initializeOnce();
        return this.contextualFooter;
    }

    /**
     * Sets whether to display a contextual footer band that can display up to 3 links
     *
     * can be overriden programatically.
     */
    public void setContextualFooter(ContextualFooter value) {
        this.contextualFooter = value;
    }

    /**
     * Returns whether to hide the main footer
     *
     * can be overriden programatically.
     */
    public boolean getHideMainFooter() {
        this.initializeOnce();
        return this.hideMainFooter;
    }

    /**
     * Sets whether to hide the main footer
     *
     * can be overriden programatically.
     */
    public void setHideMainFooter(boolean value) {
        this.hideMainFooter = value;
    }

    /**
     * Returns whether to hide corporate footer links
     *
     * can be overriden programatically.
     */
    public boolean getHideCorporateFooter() {
        this.initializeOnce();
        return this.hideCorporateFooter;
    }

    /**
     * Sets whether to hide corporate footer links
     *
     * can be overriden programatically.
     */
    public void setHideCorporateFooter(boolean value) {
        this.hideCorporateFooter = value;
    }

    /**
     * Returns whether to display a header menu on top of the page
     *
     * can be overriden programatically.
     */
    public HeaderMenu getHeaderMenu() {
        this.initializeOnce();
        return this.headerMenu;
    }

    /**
     * Sets whether to display a header menu on top of the page
     *
     * can be overriden programatically.
     */
    public void setHeaderMenu(HeaderMenu value) {
        this.headerMenu = value;
    }

    /**
     * Returns a copy of the breadcrumb list, ready for JSON serialization
     */
    private List<Breadcrumb> getEncodedBreadcrumbs() {
        List<Breadcrumb>   sourceList = this.getBreadcrumbs();
        List<Breadcrumb>   tmpBreadcrumbs = null;

        if ((sourceList != null) && (sourceList.size() > 0)) {
            tmpBreadcrumbs = new ArrayList<Breadcrumb>();
            for (Breadcrumb bc: sourceList) {
                tmpBreadcrumbs.add(new Breadcrumb(
                                        JsonValueUtils.getNonEmptyURLEscapedString(bc.getHref()),
                                        JsonValueUtils.getNonEmptyString(bc.getTitle()),
                                        JsonValueUtils.getNonEmptyString(bc.getAcronym())) );
            }
        }

        return tmpBreadcrumbs;
    }

    /**
     * helper method to build the default SessionTimeout configuration object using default value
     * specified in the resource bundle
     */
    private SessionTimeout buildDefaultSessionTimeoutConfigurations(java.util.ResourceBundle bundle) {
        SessionTimeout configs = new SessionTimeout();

        if (bundle != null) {
            configs.setEnabled(Boolean.parseBoolean(bundle.getString("session.timeout.enabled")));
            configs.setInactivity(Integer.parseInt(bundle.getString("session.inactivity.value")));
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

    private String getPathAttributes(String pathAttributeName, String fileName) {
        StringBuilder buf = new StringBuilder(384);

        buf.append(pathAttributeName);
        buf.append("=\"");
        buf.append(this.getPartialCDNPath());
        buf.append(fileName);
        buf.append("\"");
        if (this.getUseSRI()) {
            HashMap<String, String> sriHashes = this.getCurrentCDTSSRIHashes();

            String hash = sriHashes.get(fileName);
            if (hash != null) { //if not found, simply don't issue SRI attributes
                buf.append(" integrity=\"");
                buf.append(hash);
                buf.append("\" crossorigin=\"anonymous\"");
            }
        }

        return buf.toString();
    }

    /**
     * Return the href/integrity/crossorigin attribute to the cdts-[subtheme]-styles.css file.
     *
     * (Used by template files when rendering)
     */
    public String getCssPathAttributes() {
        //---[ Figure out CSS file name
        String fileName = "cdts-styles.css";

        if (!this.isThemeGcWeb()) {
            //NOT gcweb...
            String subTheme = this.getSubTheme();
            if (!Utility.isNullOrEmpty(subTheme)) {
                subTheme = subTheme.toLowerCase();
                //...limit to supported subthemes
                if (subTheme.equals("esdc") || subTheme.equals("eccc")) {
                    fileName = String.format("cdts-%s-styles.css", subTheme);
                }
            }
        } // (else we're gcweb or gcintranet with no subtheme value)

        return this.getPathAttributes("href", fileName);
    }

    /**
     * Return the href/integrity/crossorigin attribute to the cdts-[app-]styles.css file.
     *
     * (Used by template files when rendering)
     */
    public String getAppCssPathAttributes() {
        if (this.isThemeGcWeb()) {
            return this.getPathAttributes("href", "cdts-app-styles.css");
        } else {
            return this.getCssPathAttributes();
        }
    }

    /**
     * Return the href/integrity/crossorigin attribute to the cdts-splash-styles.css file.
     *
     * (Used by template files when rendering)
     */
    public String getSplashCssPathAttributes() {
        return this.getPathAttributes("href", "cdts-splash-styles.css");
    }

    /**
     * Returns the CDN path to the wet javascript file.
     *
     * (Used by template files when rendering)
     */
    public String getWetJsPathAttributes() {
        return this.getPathAttributes("src", String.format("compiled/wet-%s.js", this.getTwoLetterCultureLanguage()));
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

    private List<String> buildHtmlBodyElements() {
        if (this.htmlBodyElements == null) return null;
        if (this.htmlBodyElements.isEmpty()) return null;
        return this.htmlBodyElements;
    }

    private String getRenderHtmlElements(List<String> elems)
    {
        StringBuilder sb = new StringBuilder();

        if (elems != null && elems.size() > 0)
        {
            sb.append("\r\n");
            for (String elem : elems)
            {
                sb.append(elem);
                sb.append("\r\n");
            }
        }

        return sb.toString();
    }

    private String buildDateModified() {
        Date    sourceDate = this.getDateModified();

        if (sourceDate == null) return null;

        //If we have a date set, return it
        return dateModifiedFormat.get().format(sourceDate);
    }

    private List<LanguageLink> buildLanguageLinkList()
    {
        List<LanguageLink> vtr;

        if (!this.getShowLanguageLink()) return null;

        vtr = new ArrayList<LanguageLink>();
        vtr.add(new LanguageLink(BaseUtil.encodeUrl(this.getLanguageLinkUrl()),
                                this.getLanguageLinkLang(),
                                this.getLanguageLinkText()));

        return vtr;
    }

    private List<IntranetTitle> buildIntranetTitleList() {
        List<IntranetTitle> vtr;

        if (this.intranetTitle == null) return null;

        vtr = new ArrayList<IntranetTitle>(1);
        vtr.add(new IntranetTitle(BaseUtil.encodeUrl(this.intranetTitle.getHref()),
                         this.intranetTitle.getText(),
                         JsonValueUtils.getNonEmptyString(this.intranetTitle.getAcronym()),
                         JsonValueUtils.getNonEmptyString(this.intranetTitle.getBoldText())) );

        return vtr;
    }

    private boolean getHasLeftMenuSections() {
        return (this.leftMenuSections != null && this.leftMenuSections.size() > 0);
    }

    private List<SecMenuItem> buildMenuLinksList() {
        this.initializeOnce();
        if ((this.menuLinks == null) || (this.menuLinks.size() <= 0)) return null;

        List<SecMenuItem>  vtr = new ArrayList<SecMenuItem>();
        for (MenuItem mi: this.menuLinks) vtr.add(new SecMenuItem(mi));
        return vtr;
    }

    private List<Link> buildHideableHrefOnlyLink(String href, boolean showLink) {
        List<Link> vtr;

        if ((!showLink) || Utility.isNullOrEmpty(href)) return null;

        vtr = new ArrayList<Link>();
        vtr.add(new Link(BaseUtil.encodeUrl(href), null));

        return vtr;
    }

    /**
     * Builds the footer link list from custom links or sections depending on selected environment/theme.
     */
    private List<IFooterSection> buildCustomFooterSections() {
        List<IFooterSection>   footerSections = null;
        CDTSEnvironment        env = this.getCurrentCDTSEnvironment();

        //---[ Build list
        if (env.getFooterSectionLimit() > 0) {
            //Use customFooterSection
            if ((this.customFooterSections != null) && !this.customFooterSections.isEmpty()) {
                if (this.customFooterSections.size() > env.getFooterSectionLimit()) throw new IllegalArgumentException("Too many sections found in customFooterSections, maximum for environment [" + env.getName() + "] is [" + env.getFooterSectionLimit() + "].");

                footerSections = new ArrayList<IFooterSection>();
                for (FooterSection fs: this.customFooterSections) {
                    footerSections.add(new FooterSection(fs.getSectionName(),
                                            JsonValueUtils.getNonEmptyFooterLinkList(fs.getCustomFooterLinks()))
                                      );
                }
            }
            else if ((this.customFooterLinks != null) && !this.customFooterLinks.isEmpty()) {
                throw new IllegalArgumentException("customFooterLinks cannot be used for this environment/theme [\" + env.getName() + \"], please use customFooterSections.");
            }
        }
        else {
            //Use customFooterLinks
            if ((this.customFooterLinks != null) && !this.customFooterLinks.isEmpty()) {
                footerSections = new ArrayList<IFooterSection>();
                for (FooterLink fl: this.customFooterLinks) {
                    footerSections.add(new FooterLink(BaseUtil.encodeUrl(fl.getHref()),
                                       JsonValueUtils.getNonEmptyString(fl.getText()),
                                       fl.getNewWindow()));
                }
            }
            else if ((this.customFooterSections != null) && !this.customFooterSections.isEmpty()) {
                throw new IllegalArgumentException("customFooterSections cannot be used for this environment/theme [" + env.getName() + "], please use customFooterLinks.");
            }
        }

        return footerSections;
    }

    public List<Link> buildContactLinks() {
        List<Link> links = this.getContactLinks();

        if (!this.getCurrentCDTSEnvironment().getCanHaveMultipleContactLinks()) {
            if ((links != null) && links.size() > 1) throw new IllegalArgumentException("Having multiple contact links is not allowed for environment [" + this.getCurrentCDTSEnvironment().getName() + "]");
        }

        return JsonValueUtils.getNonEmptyLinkList(links);
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
     * Builds the html of the WET Session Timeout Control that provided session timeout and inactivity functionality.
     * For more documentation: https://wet-boew.github.io/v4.0-ci/demos/session-timeout/session-timeout-en.html
     *
     * @return the html of the WET session timeout control
     */
    public String getRenderSessionTimeoutControl() {

        StringBuilder   sb;
        SessionTimeout  configs = this.getSessionTimeoutConfiguration();

        if (!configs.isEnabled()) return "";

        sb = new StringBuilder();

        sb.append("<span class='wb-sessto' data-wb-sessto='");
        sb.append(gson.toJson(new goc.webtemplate.component.jsonentities.SessionTimeout(configs)));
        sb.append("'></span>");

        return sb.toString();
    }

    /** Creates the `SetupBase` object needed in rendering the CDTS setup JSON
     *  (assumes initializeOnce() was called)
     */
    private SetupBase buildSetupBase() {
        if (this.getWebAnalytics().isActive() && !this.getCurrentCDTSEnvironment().getCanUseWebAnalytics()) {
            throw new IllegalArgumentException("The WebAnalytics feature is not supported in environment [" + this.getCurrentCDTSEnvironment().getName() + "]");
        }

        return new SetupBase(JsonValueUtils.getNonEmptyString(this.getSubTheme()),
                this.getLoadJQueryFromGoogle() ? "external" : null, // jqueryEnv
                this.getLeavingSecureSiteWarning(),
                this.getWebAnalytics().isActive() ? Arrays.asList(this.getWebAnalytics()) : null,
                this.getUseSRI()? null: false); //if SRi is true, set to null to let CDTS's default be used
    }

    /**
     * Builds the "Top" object needed in rendering the CDTS setup JSON
     * (assumes initializeOnce() was called)
     */
    private Top buildTop(boolean isTransactional) {
        if (this.isThemeGcWeb() && this.gcToolsModal){
            throw new UnsupportedOperationException(String.format("The gcToolsModal is not available in %s", this.getTheme().toLowerCase()));
        }

        return new Top(
                    null, //no need for cdnEnv now that we're using CDTS setup function
                    JsonValueUtils.getNonEmptyString(this.getSubTheme()),
                    this.buildIntranetTitleList(),
                    this.showSearch,
                    this.buildLanguageLinkList(),
                    isTransactional? false: this.showPreContent, //preContent
                    this.getEncodedBreadcrumbs(),
                    JsonValueUtils.getNonEmptyString(this.getLocalPath()),
                    !isTransactional, //siteMenu
                    this.getHasLeftMenuSections(), //topSecMenu, true if there is at least one left menu section defined
                    this.customSearch != null? Arrays.asList(this.customSearch): null,
                    this.gcToolsModal,
                    this.hidePlaceholderMenu
                );
    }

    /**
     * Builds the "AppTop" object needed in rendering the CDTS setup JSON
     * (assumes initializeOnce() was called)
     */
    private AppTop buildAppTop() {
        AppTop  appTop;

        this.checkIfBothShowSignInAndOutAreSet();

        List<Link> appName = new ArrayList<Link>();
        appName.add(this.applicationTitle);

        //For v4.0.26.x we have to render this section differently depending on the theme,
        //GCIntranet theme renders AppName and AppUrl seperately in GCWeb we render it as a List of Links.
        if (isThemeGcWeb()) {
            appTop = new AppTop(
                    null, //no need for cdnEnv now that we're using CDTS setup function
                    JsonValueUtils.getNonEmptyString(this.getSubTheme()),
                    JsonValueUtils.getNonEmptyString(this.getLocalPath()),
                    appName,
                    JsonValueUtils.getNonEmptyURLEscapedString(this.getCustomSiteMenuUrl()),
                    this.buildMenuLinksList(),
                    this.buildLanguageLinkList(),
                    this.buildHideableHrefOnlyLink(this.getSignInLinkUrl(), this.getShowSignInLink()),
                    this.buildHideableHrefOnlyLink(this.getSignOutLinkUrl(), this.getShowSignOutLink()),
                    this.buildHideableHrefOnlyLink(this.getAppSettingsUrl(), true),
                    this.showSearch,
                    this.getEncodedBreadcrumbs(),
                    this.showPreContent,
                    this.customSearch != null? Arrays.asList(this.customSearch): null,
                    this.getHasLeftMenuSections(), //topSecMenu, true if there is at least one left menu section defined
                    this.infoBanner,
                    this.headerMenu
                    );
        } else {
            appTop = new AppTop.AppTopGCIntranet(
                    null, //no need for cdnEnv now that we're using CDTS setup function
                    JsonValueUtils.getNonEmptyString(this.getSubTheme()),
                    JsonValueUtils.getNonEmptyString(this.getLocalPath()),
                    appName,
                    JsonValueUtils.getNonEmptyURLEscapedString(this.getCustomSiteMenuUrl()),
                    this.buildMenuLinksList(),
                    this.buildLanguageLinkList(),
                    this.buildHideableHrefOnlyLink(this.getSignInLinkUrl(), this.getShowSignInLink()),
                    this.buildHideableHrefOnlyLink(this.getSignOutLinkUrl(), this.getShowSignOutLink()),
                    this.buildHideableHrefOnlyLink(this.getAppSettingsUrl(), true),
                    this.showSearch,
                    this.getEncodedBreadcrumbs(),
                    this.showPreContent,
                    this.customSearch != null? Arrays.asList(this.customSearch): null,
                    this.getHasLeftMenuSections(), //topSecMenu, true if there is at least one left menu section defined
                    this.buildIntranetTitleList(),
                    this.gcToolsModal
                    );
        }

        return appTop;
    }

    /**
     * Builds the "PreFooter" object needed in rendering the CDTS setup JSON
     * (assumes initializeOnce() was called)
     */
    private IPreFooter buildPreFooter(boolean isTransactional, boolean isUnilingualError) {
        if (!isTransactional) {
            if (!isUnilingualError) {
                return new PreFooter(
                        null, //no need for cdnEnv now that we're using CDTS setup function
                        JsonValueUtils.getNonEmptyString(this.getVersionIdentifier()),
                        this.buildDateModified(),
                        this.showPostContent,
                        new FeedbackLink(this.showFeedbackLink,
                                (this.getTwoLetterCultureLanguage().toUpperCase().equals(Constants.ENGLISH_ACCRONYM.toUpperCase()) || Utility.isNullOrEmpty(this.getFeedbackUrlFr()) ?
                                        this.feedbackUrl:
                                        this.feedbackUrlFr) ),
                        new ShareList(this.showSharePageLink, this.sharePageMediaSites),
                        JsonValueUtils.getNonEmptyString(this.getScreenIdentifier())
                      );
            } else { //(isUnilingualError)
                return new UnilingualErrorPreFooter(null, false);
            }
        }
        else { //(isTransactional)
            return new PreFooter(
                    this.getCdtsCdnEnv(),
                    JsonValueUtils.getNonEmptyString(this.getVersionIdentifier()),
                    this.buildDateModified(),
                    false, //showPostContent,
                    new FeedbackLink(false, null),
                    new ShareList(false, null),
                    JsonValueUtils.getNonEmptyString(this.getScreenIdentifier())
                  );
        }
    }

    /**
     * Builds the "Footer" object needed in rendering the CDTS setup JSON
     * (assumes initializeOnce() was called)
     */
    private Footer buildFooter(boolean isTransactional) {
        return new Footer(
                null, //no need for cdnEnv now that we're using CDTS setup function
                JsonValueUtils.getNonEmptyString(this.getSubTheme()),
                !isTransactional, //showFooter,
                this.getShowFeatures(),
                this.buildContactLinks(),
                JsonValueUtils.getFooterLinkContext(this.getPrivacyLink(), !isTransactional), //privacyLink
                JsonValueUtils.getFooterLinkContext(this.getTermsConditionsLink(), !isTransactional), //termsLink
                JsonValueUtils.getNonEmptyString(this.getLocalPath()),
                isTransactional? null: this.contextualFooter, //contextualFooter
                isTransactional? false: this.hideMainFooter, //hideMainFooter
                isTransactional? false: this.hideCorporateFooter //hideCorporateFooter
            );
    }

    /**
     * Builds the "AppFooter" object needed in rendering the CDTS setup JSON
     * (assumes initializeOnce() was called)
     */
    private AppFooter buildAppFooter() {
        if (!this.getCurrentCDTSEnvironment().getCanHaveContactLinkInAppTemplate()) {
            if ((this.getContactLinks() != null) && (this.getContactLinks().size() > 0)) {
                throw new IllegalArgumentException("Custom Footer must be used to provide a contact link in environment [" + this.getCurrentCDTSEnvironment().getName() + "]");
            }
        }

        return new AppFooter(
                    null, //no need for cdnEnv now that we're using CDTS setup function
                    JsonValueUtils.getNonEmptyString(this.getSubTheme()),
                    JsonValueUtils.getNonEmptyString(this.getLocalPath()),
                    JsonValueUtils.getNonEmptyString(this.getFooterPath()),
                    this.buildCustomFooterSections(),
                    this.buildContactLinks(),
                    JsonValueUtils.getNonEmptySingleItemLinkList(this.termsConditionsLink),
                    JsonValueUtils.getNonEmptySingleItemLinkList(this.privacyLink),
                    this.getShowFeatures()
               );
    }

    /**
     *  Builds the "common" CDTS setup object based on parameteres (used by the render functions)
     */
    private Setup buildCommonSetup(boolean isTransactional, boolean isUnilingualError) {
        this.initializeOnce();

        return new Setup(this.getCdtsCdnEnv(),
                   null, // passing null for mode since "common" is CDTS default
                   this.buildSetupBase(),
                   this.buildTop(isTransactional),
                   this.buildPreFooter(isTransactional, isUnilingualError),
                   this.buildFooter(isTransactional),
                   this.getHasLeftMenuSections()? new SecMenu(this.getLeftMenuSections()): null,
                   null, //splash
                   this.buildHtmlBodyElements());
    }

    /**
     * Builds a string with the format required by the closure template to represent the JSON object used
     * as parameter for the CDTS "setup" function for a basic web page
     */
    public String getRenderSetup() {
        return gson.toJson(this.buildCommonSetup(false, false));
    }

    /**
     * Builds a string with the format required by the closure template to represent the JSON object used
     * as parameter for the CDTS "setup" function for a transactional web page
     */
    public String getRenderTransactionalSetup() {
        return gson.toJson(this.buildCommonSetup(true, false));
    }

    /**
     * Builds a string with the format required by the closure template to represent the JSON object used
     * as parameter for the CDTS "setup" function for an unilingual error web page
     */
    public String getRenderUnilingualErrorSetup() {
        return gson.toJson(this.buildCommonSetup(false, true));
    }

    /**
     * Builds a string with the format required by the closure template to represent the JSON object used
     * as parameter for the CDTS "setup" function for a "application" web page
     */
    public String getRenderAppSetup() {
        this.initializeOnce();

        return gson.toJson(new Setup(this.getCdtsCdnEnv(),
                Mode.APP,
                this.buildSetupBase(),
                this.buildAppTop(),
                this.buildPreFooter(false, false),
                this.buildAppFooter(),
                this.getHasLeftMenuSections()? new SecMenu(this.getLeftMenuSections()): null,
                null, //splash
                this.buildHtmlBodyElements()));
    }

    /**
     * Builds a string with the format required by the closure template to represent the JSON object used
     * as parameter for the CDTS "setup" function for a "server" web page (ie used for error pages)
     */
    public String getRenderServerSetup() {
        this.initializeOnce();

        return gson.toJson(new Setup(this.getCdtsCdnEnv(),
                Mode.SERVER,
                this.getUseSRI()? null: new SetupBase(null, null, (LeavingSecureSiteWarning)null, null, false), //base left null for defaults is SRI is true
                null, //top
                null, //preFooter
                null, //footer
                null, //secmenu
                null, //splash
                this.buildHtmlBodyElements()));
    }

    /**
     * Builds a string with the format required by the closure template to represent the JSON object used
     * as parameter for the CDTS "setup" function for a "splash" web page
     */
    public String getRenderSplashSetup() {
        this.initializeOnce();

        SplashPageInfo  spi = this.getSplashPageInfo();

        return gson.toJson(new Setup(this.getCdtsCdnEnv(),
                Mode.SPLASH,
                this.buildSetupBase(), //base
                null, //top
                null, //preFooter
                null, //footer
                null, //secmenu
                new Splash(
                    null, //no need for cdnEnv now that we're using CDTS setup function
                    spi.getEnglishHomeUrl(),
                    spi.getFrenchHomeUrl(),
                    JsonValueUtils.getNonEmptyString(spi.getEnglishTermsUrl()),
                    JsonValueUtils.getNonEmptyString(spi.getFrenchTermsUrl()),
                    spi.getEnglishName(),
                    spi.getFrenchName()
                ),
                this.buildHtmlBodyElements()));
    }

    /**
     * Checks the Theme
     * @return true if theme is gcweb
     */
    public boolean isThemeGcWeb() {
        return this.getTheme().equalsIgnoreCase(Themes.GCWEB.toString());
    }
}
