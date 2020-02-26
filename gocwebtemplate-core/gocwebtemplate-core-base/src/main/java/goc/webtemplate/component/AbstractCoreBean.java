package goc.webtemplate.component;

import java.text.SimpleDateFormat;
import java.text.MessageFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.MissingResourceException;

import org.apache.commons.text.StringEscapeUtils;

import com.google.gson.Gson;

import goc.webtemplate.Breadcrumb;
import goc.webtemplate.Constants;
import goc.webtemplate.CustomSearch;
import goc.webtemplate.FooterLink;
import goc.webtemplate.FooterSection;
import goc.webtemplate.IFooterSection;
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

import goc.webtemplate.component.jsonentities.AppFooter;
import goc.webtemplate.component.jsonentities.AppTop;
import goc.webtemplate.component.jsonentities.CDTSEnvironment;
import goc.webtemplate.component.jsonentities.CdnEnvironment;
import goc.webtemplate.component.jsonentities.FeedbackLink;
import goc.webtemplate.component.jsonentities.Footer;
import goc.webtemplate.component.jsonentities.PreFooter;
import goc.webtemplate.component.jsonentities.RefFooter;
import goc.webtemplate.component.jsonentities.RefTop;
import goc.webtemplate.component.jsonentities.SecMenu;
import goc.webtemplate.component.jsonentities.SecMenuItem;
import goc.webtemplate.component.jsonentities.ShareList;
import goc.webtemplate.component.jsonentities.Splash;
import goc.webtemplate.component.jsonentities.SplashTop;
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
     * Hold the table of CDTS environment configuration objects (loaded the first time it is accessed).
     */
    private static HashMap<String, CDTSEnvironment> cdtsEnvironments = null;
    
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
    private String templateVersion = this.getResourceBundleString("cdn", "wettemplate_version");
    private String theme = null; // initialized in getTheme 
    private String subTheme = null; // initialized in getSubTheme
    private boolean useHttps = Boolean.parseBoolean(this.getResourceBundleString("cdn", "webtemplate_usehttps"));
    private boolean loadjQueryFromGoogle = Boolean.parseBoolean(this.getResourceBundleString("cdn", "wettemplate_loadjqueryfromgoogle"));
    private String cdnLocalPath = null; //initialized in getLocalPath
    private String headerTitle = "";
    private Link applicationTitle = new Link();
    private IntranetTitle intranetTitle = null;    
    private String languageLinkUrl = "";
    private String feedbackUrl = this.getResourceBundleString("cdn", "goc.webtemplate.feedbackurl");
    private String feedbackUrlFr = this.getResourceBundleString("cdn", "goc.webtemplate.feedbackurl_fr");
    private ArrayList<Link> contactLinks = null;
    private LeavingSecureSiteWarning leavingSecureSiteWarning = new LeavingSecureSiteWarning(
                                            Boolean.parseBoolean(this.getResourceBundleString("cdn", "leavingsecuresitewarning.enabled")),
                                            Boolean.parseBoolean(this.getResourceBundleString("cdn", "leavingsecuresitewarning.displaymodalwindow")),
                                            "",
                                            this.getResourceBundleString("cdn", "leavingsecuresitewarning.redirecturl"),
                                            this.getResourceBundleString("cdn", "leavingsecuresitewarning.excludeddomains"), null, null);
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
    private ArrayList<Constants.SocialMediaSites> sharePageMediaSites = new ArrayList<Constants.SocialMediaSites>();
    private ArrayList<Breadcrumb> breadcrumbs = new ArrayList<Breadcrumb>();
    private ArrayList<String> htmlHeaderElements = new ArrayList<String>();
    private ArrayList<String> htmlBodyElements = new ArrayList<String>();
    private String staticFallbackFilePath = this.getResourceBundleString("cdn", "goc.webtemplate.staticfileslocation");    
    private String  customSiteMenuUrl = this.getResourceBundleString("cdn", "goc.webtemplate.customsitemenuurl");
    private ArrayList<MenuItem> menuLinks = null;
    private String  signInLinkUrl = this.getResourceBundleString("cdn", "goc.webtemplate.signinlinkurl");
    private String  signOutLinkUrl = this.getResourceBundleString("cdn", "goc.webtemplate.signoutlinkurl");
    private String  appSettingsUrl = this.getResourceBundleString("cdn", "goc.webtemplate.appsettingsurl");
    private boolean showSignInLink = false;
    private boolean showSignOutLink = false;
    private ArrayList<FooterLink> customFooterLinks = new ArrayList<FooterLink>();
    private ArrayList<FooterSection> customFooterSections = new ArrayList<FooterSection>();
    private CustomSearch customSearch = null;
    private SessionTimeout sessionTimeoutConfiguration = null; //initialization in get method
    private SplashPageInfo splashPageInfo = null; //initialization in get method
    private ArrayList<MenuSection> leftMenuSections = new ArrayList<MenuSection>();
    private FooterLink privacyLink = new FooterLink();
    private FooterLink termsConditionsLink = new FooterLink();
    private WebAnalyticsInfo webAnalytics = new WebAnalyticsInfo(Boolean.parseBoolean(this.getResourceBundleString("cdn", "goc.webtemplate.usewebanalytics"))); //will be false if not specified in config
    private boolean gcToolsModal = false;
    //-------------------------------------------------------

    //-------------------------------------------------------
    //---[ Caching of calculated values
    //-------------------------------------------------------
    private String          partialCDNPath = null;
    private CDTSEnvironment currentCDTSEnvironment = null;
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
     * Load environment json file (converts all environment names to lowercase)
     */
    private static synchronized void loadCDTSEnvironments() { //note the "static synchronized"... i.e. synchronized on AbstractCoreBean.class 
        HashMap<String, CDTSEnvironment>    map = new HashMap<String, CDTSEnvironment>();
        CDTSEnvironment.CDTSEnvironmentList tmpList;
        java.io.InputStream                 is = null;
        
        try {
            is = AbstractCoreBean.class.getResourceAsStream("/goc/webtemplate/global/config/CDTSEnvironments.json");
            if (is == null) {
                System.err.println("ERROR: CDTSEnvironment.json could not be read. Environment list will be empty. (/goc/webtemplate/global/config/CDTSEnvironments.json not found in jar)");
                cdtsEnvironments = map;
                return;
            }
            
            tmpList = gson.fromJson(new java.io.InputStreamReader(is, "UTF-8"), CDTSEnvironment.CDTSEnvironmentList.class);
            for (CDTSEnvironment cdtsenv: tmpList.getEnvironments()) {
                //(convert name to all lower-case)
                map.put(cdtsenv.getName().toLowerCase(), cdtsenv);                    
            }
        }
        catch (Exception ex) {
            System.err.println("ERROR: CDTSEnvironment.json could not be read. Environment list will be empty. Exception:  " + ex);
            cdtsEnvironments = map; 
        }
        finally {
            try {is.close();} catch (Throwable e) {}
        }
        
        cdtsEnvironments = map;
    }
    
    private CDTSEnvironment getCurrentCDTSEnvironment() {
       if (this.currentCDTSEnvironment == null) this.currentCDTSEnvironment = getCDTSEnvironmentByName(this.getCDNEnvironment().toLowerCase());
       return this.currentCDTSEnvironment;
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
        if (Utility.isNullOrEmpty(templateVersion)) {
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
        
        //---[ The environment anme changed, we have a few cached values to clear so they are re-loaded from the proper environment
        this.currentCDTSEnvironment = null;
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
     * 
     *  @deprecated {@link #getPrivacyLink()} should be used instead.
     */
    @Deprecated
    public String getPrivacyLinkUrl() {
        this.initializeOnce();
        return this.privacyLink.getHref();
    }
    
    /**
     * Sets the URL to be used for the Privacy link in transactional mode.
     *  
     * @deprecated {@link #setPrivacyLink()} should be used instead.
     */
    @Deprecated
    public void setPrivacyLinkUrl(String value) {
        this.privacyLink.setHref(value);
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
    public ArrayList<Link> getContactLinks() {
        this.initializeOnce();
        return this.contactLinks;
    }
    
    /**
     * Sets the list of contact links to the specified value.
     */
    public void setContactLinks(ArrayList<Link> value) {
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
     * Returns the url to be used for the Terms & Conditions link in transactional mode.
     * 
     * Set by application programmatically.
     * 
     * @deprecated {@link #getTermsConditionsLink()} should be used instead.
     */
    @Deprecated
    public String getTermsConditionsLinkUrl() {
        this.initializeOnce();
        return this.termsConditionsLink.getHref();
    }
    
    /**
     * Sets the url to be used for the Terms & Conditions link in transactional mode.
     * 
     * Set by application programmatically.
     * 
     * @deprecated {@link #setTermsConditionsLink(FooterLink)} should be used instead.
     */
    @Deprecated
    public void setTermsConditionsLinkUrl(String value) {
        this.termsConditionsLink.setHref(value);
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
    public ArrayList<MenuItem> getMenuLinks() {
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
    public void setMenuLinks(ArrayList<MenuItem> value) {
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
    public ArrayList<FooterLink> getCustomFooterLinks() {
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
    public void setCustomFooterLinks(ArrayList<FooterLink> value) {
        this.customFooterLinks = value;
    }
    
    /**
     * Returns the custom footer sections (ie groups of footer links with a name).
     * If null, uses standard links. If not null, overrides the existing footer links.
     * 
     * Set by application programmatically
     * Only available in the Application Template when using a theme other than GCweb.
     */
    public ArrayList<FooterSection> getCustomFooterSections() {
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
    public void setCustomFooterSections(ArrayList<FooterSection> value) {
        this.customFooterSections = value;
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
    
    private ArrayList<IntranetTitle> buildIntranetTitleList() {
        ArrayList<IntranetTitle> vtr;
        
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
    
    private ArrayList<SecMenuItem> buildMenuLinksList() {
        this.initializeOnce();
        if ((this.menuLinks == null) || (this.menuLinks.size() <= 0)) return null;
        
        ArrayList<SecMenuItem>  vtr = new ArrayList<SecMenuItem>();
        for (MenuItem mi: this.menuLinks) vtr.add(new SecMenuItem(mi));
        return vtr;
    }
    
    private ArrayList<Link> buildHideableHrefOnlyLink(String href, boolean showLink) {
        ArrayList<Link> vtr;
        
        if ((!showLink) || Utility.isNullOrEmpty(href)) return null;
        
        vtr = new ArrayList<Link>();
        vtr.add(new Link(BaseUtil.encodeUrl(href), null));
        
        return vtr;
    }
    
    /**
     * Builds the footer link list from custom links or sections depending on selected environment/theme.
     */
    private ArrayList<IFooterSection> buildCustomFooterSections() {
        ArrayList<IFooterSection>   footerSections = null;
        CDTSEnvironment             env = this.getCurrentCDTSEnvironment();
        
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
    
    public ArrayList<Link> buildContactLinks() {
        ArrayList<Link> links = this.getContactLinks();
        
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
    
    /**
     * Builds a string with the format required by the closure template to represent the JSON object used 
     * as parameter for the "secmenu"
     */
    public String getRenderLeftMenuSections() {
        return gson.toJson(new SecMenu(this.getLeftMenuSections()));
    }
    
    /**
     * Builds a string with the format required by the closure template to represent the JSON object used 
     * as parameter for the "refTop"
     * 
     * Applications will actually use property "renderRefTop" or "renderRefTopForApp" depending
     * on the case, since passing argument when calling properties is not supported in JSF2.0 (not supported anymore), having two
     * differently named get methods seemed the next simplest solution.
     */
    private String buildRefTopValue(boolean isApplication) {
        return gson.toJson(new RefTop(
                this.getCdtsCdnEnv(),
                JsonValueUtils.getNonEmptyString(this.getSubTheme()),
                this.getLoadJQueryFromGoogle() ? "external" : null, //jqueryEnv
                JsonValueUtils.getNonEmptyString(this.getLocalPath()),
                isApplication,
                this.getWebAnalytics().isActive()? Arrays.asList(this.getWebAnalytics()): null
            ));        
    }
    public String getRenderRefTop() { 
        return this.buildRefTopValue(false);
    }    
    public String getRenderRefTopForApp() {
        return this.buildRefTopValue(true);
    }
    
    /**
     * Builds a string with the format required by the closure template to represent the JSON object used 
     * as parameter for the "top"
     */
    public String getRenderTop() {
        if (this.isThemeGcWeb() && this.gcToolsModal){
            throw new UnsupportedOperationException(String.format("The gcToolsModal is not available in %s", this.getTheme().toLowerCase()));
        }
        
        return gson.toJson(new Top(
                    this.getCdtsCdnEnv(),
                    JsonValueUtils.getNonEmptyString(this.getSubTheme()),
                    this.buildIntranetTitleList(),
                    this.showSearch,
                    this.buildLanguageLinkList(),
                    this.showPreContent,
                    this.getEncodedBreadcrumbs(),
                    JsonValueUtils.getNonEmptyString(this.getLocalPath()),
                    true, //siteMenu
                    this.getHasLeftMenuSections(), //topSecMenu, true if there is at least one left menu section defined
                    this.customSearch != null? Arrays.asList(this.customSearch): null,      
                    this.gcToolsModal
                ));        
    }
    
    /**
     * Builds a string with the format required by the closure template to represent the JSON object used 
     * as parameter for the "top" for transactional pages.
     */
    public String getRenderTransactionalTop() {
        if (this.isThemeGcWeb() && this.gcToolsModal){
            throw new UnsupportedOperationException(String.format("The gcToolsModal is not available in %s", this.getTheme().toLowerCase()));
        }
        
        return gson.toJson(new Top(
                    this.getCdtsCdnEnv(),
                    JsonValueUtils.getNonEmptyString(this.getSubTheme()),
                    this.buildIntranetTitleList(),
                    this.showSearch,
                    this.buildLanguageLinkList(),
                    false, //preContent
                    this.getEncodedBreadcrumbs(),
                    JsonValueUtils.getNonEmptyString(this.getLocalPath()),
                    false, //siteMenu
                    this.getHasLeftMenuSections(), //topSecMenu, true if there is at least one left menu section defined
                    this.customSearch != null? Arrays.asList(this.customSearch): null,
                    this.gcToolsModal        
                ));        
    }
    
    /**
     * Builds a string with the format required by the closure template to represent the JSON object used 
     * as parameter for the "appTop"
     */
    public String getRenderAppTop() {
        AppTop  appTop;

        this.initializeOnce();
        this.checkIfBothShowSignInAndOutAreSet();

        ArrayList<Link> appName = new ArrayList<Link>();
        appName.add(this.applicationTitle);
        
        //For v4.0.26.x we have to render this section differently depending on the theme, 
        //GCIntranet theme renders AppName and AppUrl seperately in GCWeb we render it as a List of Links. 
        if (isThemeGcWeb()) {
            appTop = new AppTop(
                    this.getCdtsCdnEnv(),
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
                    this.getHasLeftMenuSections() //topSecMenu, true if there is at least one left menu section defined
                    );
        } else {
            appTop = new AppTop.AppTopGCIntranet(
                    this.getCdtsCdnEnv(),
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

        return gson.toJson(appTop);
    }
    
    /**
     * Builds a string with the format required by the closure template to represent the JSON object used 
     * as parameter for the "splashTop"
     */
    public String getRenderSplashTop() {
        return gson.toJson(new SplashTop(
                this.getCdtsCdnEnv(),
                JsonValueUtils.getNonEmptyString(this.getLocalPath())
              ));
    }
    
    /**
     * Builds a string with the format required by the closure template to represent the JSON object used 
     * as parameter for the "splash"
     */
    public String getRenderSplash() {
        SplashPageInfo  spi = this.getSplashPageInfo();
        
        return gson.toJson(new Splash(
                    this.getCdtsCdnEnv(),
                    spi.getEnglishHomeUrl(), 
                    spi.getFrenchHomeUrl(),
                    JsonValueUtils.getNonEmptyString(spi.getEnglishTermsUrl()),
                    JsonValueUtils.getNonEmptyString(spi.getFrenchTermsUrl()),
                    spi.getEnglishName(),
                    spi.getFrenchName()
                ));
    }
    
    /**
     * Builds a string with the format required by the closure template to represent the JSON object used 
     * as parameter for the "preFooter"
     */
    public String getRenderPreFooter() {
        return gson.toJson(new PreFooter(
                this.getCdtsCdnEnv(),
                JsonValueUtils.getNonEmptyString(this.getVersionIdentifier()),
                this.buildDateModified(),
                this.showPostContent,
                new FeedbackLink(this.showFeedbackLink, 
                        (this.getTwoLetterCultureLanguage().toUpperCase().equals(Constants.ENGLISH_ACCRONYM.toUpperCase()) || Utility.isNullOrEmpty(this.getFeedbackUrlFr()) ?
                                this.feedbackUrl:
                                this.feedbackUrlFr) ),
                new ShareList(this.showSharePageLink, this.sharePageMediaSites),
                JsonValueUtils.getNonEmptyString(this.getScreenIdentifier())
              )); 
    }
    
    /**
     * Builds a string with the format required by the closure template to represent the JSON object used 
     * as parameter for the "preFooter" for transactional template.
     */
    public String getRenderTransactionalPreFooter() {
        return gson.toJson(new PreFooter(
                this.getCdtsCdnEnv(),
                JsonValueUtils.getNonEmptyString(this.getVersionIdentifier()),
                this.buildDateModified(),
                false, //showPostContent,
                new FeedbackLink(false, null),
                new ShareList(false, null),
                JsonValueUtils.getNonEmptyString(this.getScreenIdentifier())
              ));
    }
    
    public String getRenderUnilingualErrorPreFooter() {
        return gson.toJson(new UnilingualErrorPreFooter(this.getCdtsCdnEnv(),
                false
                ));
    }
    
    /**
     * Builds a string with the format required by the closure template to represent the JSON object used 
     * as parameter for the "footer"
     */
    public String getRenderFooter() {
        return gson.toJson(new Footer(
                this.getCdtsCdnEnv(),
                JsonValueUtils.getNonEmptyString(this.getSubTheme()),
                true, //showFooter,
                this.getShowFeatures(),
                this.buildContactLinks(),
                null, //privacyLink
                null, //termsLink
                JsonValueUtils.getNonEmptyString(this.getLocalPath())
            ));        
    }
    
    /**
     * Builds a string with the format required by the closure template to represent the JSON object used 
     * as parameter for the "footer" for transactional template
     */
    public String getRenderTransactionalFooter() {
        return gson.toJson(new Footer(
                this.getCdtsCdnEnv(),
                JsonValueUtils.getNonEmptyString(this.getSubTheme()),
                false, //showFooter
                this.getShowFeatures(),
                this.buildContactLinks(),
                JsonValueUtils.getNonEmptySingleItemLinkList(this.getPrivacyLink()),
                JsonValueUtils.getNonEmptySingleItemLinkList(this.getTermsConditionsLink()),
                JsonValueUtils.getNonEmptyString(this.getLocalPath())
            ));        
    }
    
    /**
     * Builds a string with the format required by the closure template to represent the JSON object used 
     * as parameter for the "appFooter"
     */
    public String getRenderAppFooter() {
        AppFooter                   appFooter;
        
        this.initializeOnce();
        
        if (!this.getCurrentCDTSEnvironment().getCanHaveContactLinkInAppTemplate()) {
            if ((this.getContactLinks() != null) && (this.getContactLinks().size() > 0)) {
                throw new IllegalArgumentException("Custom Footer must be used to provide a contact link in environment [" + this.getCurrentCDTSEnvironment().getName() + "]");
            }
        }       
        
        appFooter = new AppFooter(
                        this.getCdtsCdnEnv(),
                        JsonValueUtils.getNonEmptyString(this.getSubTheme()),
                        JsonValueUtils.getNonEmptyString(this.getLocalPath()),
                        this.buildCustomFooterSections(),
                        this.buildContactLinks(),
                        JsonValueUtils.getNonEmptySingleItemLinkList(this.termsConditionsLink),
                        JsonValueUtils.getNonEmptySingleItemLinkList(this.privacyLink),
                        this.getShowFeatures()
                );
        
        return gson.toJson(appFooter);
    }
    
    /**
     * Builds a string with the format required by the closure template to represent the JSON object used 
     * as parameter for the "refFooter"
     */
    public String getRenderRefFooter() {
        if (this.getWebAnalytics().isActive() && !this.getCurrentCDTSEnvironment().getCanUseWebAnalytics()) {
            throw new IllegalArgumentException("The WebAnalytics feature is not supported in environment [" + this.getCurrentCDTSEnvironment().getName() + "]");
        }
        
        return gson.toJson(new RefFooter(
                this.getCdtsCdnEnv(),
                this.getLeavingSecureSiteWarning(),
                this.getLoadJQueryFromGoogle() ? "external" : null, //jqueryEnv
                JsonValueUtils.getNonEmptyString(this.getLocalPath()),
                this.getWebAnalytics()
            ));        
    }
    
    public String getRenderServerTop() {
        return this.getRenderCdnEnvironment();
    }
    public String getRenderServerBottom() {
        return this.getRenderCdnEnvironment();
    }
    public String getRenderServerRefTop() {
        return this.getRenderCdnEnvironment();
    }
    public String getRenderServerRefFooter() {
        return this.getRenderCdnEnvironment();
    }
    
    private String getRenderCdnEnvironment() {
        return gson.toJson(new CdnEnvironment(this.getCdtsCdnEnv()));
    }
    
    /**
     * Checks the Theme
     * @return true if theme is gcweb
     */
    public boolean isThemeGcWeb() {
        return this.getTheme().equalsIgnoreCase(Themes.GCWEB.toString());
    }
}
