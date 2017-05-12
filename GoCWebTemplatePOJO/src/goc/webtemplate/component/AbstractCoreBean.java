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
import goc.webtemplate.LeavingSecureSiteWarning;
import goc.webtemplate.Link;
import goc.webtemplate.MenuItem;
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
//TODO: Review... are unindented ones still needed?    
    private String cdnEnvironment = this.getResourceBundleString("cdn", "cdn_environment");
    private String templateVersion = this.getResourceBundleString("cdn", "wettemplate_version");
    private String theme = this.getResourceBundleString("cdn", "wettemplate_theme");
    private String subTheme = this.getResourceBundleString("cdn", "wettemplate_subtheme");
    private boolean useHttps = Boolean.parseBoolean(this.getResourceBundleString("cdn", "webtemplate_usehttps"));
    private boolean loadjQueryFromGoogle = Boolean.parseBoolean(this.getResourceBundleString("cdn", "wettemplate_loadjqueryfromgoogle"));
private String cdnLocalPathRender = null; //see getRenderLocalPath
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
    private boolean showLanguageLink = Boolean.parseBoolean(this.getResourceBundleString("cdn", "goc.webtemplate.showlanguagelink"));
    private boolean showPostContent = Boolean.parseBoolean(this.getResourceBundleString("cdn", "goc.webtemplate.showpostcontent"));
    private boolean showFeedbackLink = Boolean.parseBoolean(this.getResourceBundleString("cdn", "goc.webtemplate.showfeedbacklink"));
    private boolean showSharePageLink = Boolean.parseBoolean(this.getResourceBundleString("cdn", "goc.webtemplate.showsharepagelink"));
    private boolean showFeature = Boolean.parseBoolean(this.getResourceBundleString("cdn", "goc.webtemplate.showfeatures"));
    private ArrayList<Constants.SocialMediaSites> sharePageMediaSites = new ArrayList<Constants.SocialMediaSites>();
    private ArrayList<Breadcrumb> breadcrumbs = new ArrayList<Breadcrumb>();
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
    //---[ Caching of calculated values
    //-------------------------------------------------------
    private String partialCDNPath = null;
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
    
    
//TODO: HERE ENDS THE MAIN PROPERTIES    
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
