package goc.webtemplate.component;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.MissingResourceException;

import org.apache.commons.lang3.StringEscapeUtils;

import com.google.gson.Gson;

import goc.webtemplate.Breadcrumb;
import goc.webtemplate.Constants;
import goc.webtemplate.FooterLink;
import goc.webtemplate.LanguageLink;
import goc.webtemplate.Link;
import goc.webtemplate.MenuItem;
import goc.webtemplate.MenuSection;
import goc.webtemplate.SessionTimeout;
import goc.webtemplate.Utility;

import goc.webtemplate.component.jsonentities.AppFooter;
import goc.webtemplate.component.jsonentities.AppTop;

/**
 * This is the base class that will be shared with either the JSF 
 * or JSP version of the BaseBean class, this class defines the complete 
 * list of abstract SET method that needs to be implemented in either the 
 * JSF or JSP version of the implementation.  The corresponding GET methods 
 * will be used directly in the various master template JSP (.xhtml) or JSP (.jsp) pages.
 *  
 * @author casey.cheung
 *
 */
public abstract class BaseComponent {
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
    public abstract void setContactLinks();
    public abstract void setNewsLinks();
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

    public abstract java.util.ResourceBundle getResourceBundle();
	public abstract String getResourceBundleString(String resourceBundleName, String resourceBundleKey) throws MissingResourceException;
	public abstract String getTwoLetterCultureLanguage();	
	
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
    protected ArrayList<Link> contactLinks = new ArrayList<Link>();
    protected ArrayList<Link> newsLinks = new ArrayList<Link>();
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
    
    protected boolean sessionTimeoutEnabled = Boolean.parseBoolean(this.getResourceBundleString("cdn", "session.timeout.enabled"));
    protected SessionTimeout sessionTimeoutConfigurations = null;
    
    protected ArrayList<MenuSection> leftMenuSections = new ArrayList<MenuSection>();
    protected String privacyLinkUrl = "";
    protected String termsConditionsLinkUrl = "";
    
    protected abstract String getDefaultLangLinkUrl();
	protected abstract String getDefaultLeaveSecureSiteRedirectUrl();
    
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
	
	public String getSoyUtilPath() {
		return this.getPartialCDNPath() + "soyutils.js";
	}
	
	public String getWetJsPath() {
		return String.format("%swet-%s.js", this.getPartialCDNPath(), this.getTwoLetterCultureLanguage()); 
	}
	
	public String getPluginJsPath() {
		return String.format("%splugins-%s.js",  this.getPartialCDNPath(), this.getTwoLetterCultureLanguage());
	}
	
	/**
	 * outputs the WET template CDTS environment used by the application
	 * 
	 * @return the WET template CDTS environment either from cdn.properties
	 */
	public String getCDNEnvironment() 
	{
		this.setCDNEnvironment();
		return (Utility.isNullOrEmpty(this.cdnEnvironment) ? "Akamai" : StringEscapeUtils.escapeHtml4(this.cdnEnvironment));
	}
	
	/**
	 * outputs the WET template version used by the application
	 * 
	 * @return the WET template version either from cdn.properties or set programmatically
	 */
	public String getTemplateVersion() 
	{
		this.setTemplateVersion();
		return StringEscapeUtils.escapeHtml4(this.templateVersion);
	}
	/**
	 * outputs the WET Theme used by the application
	 * 
	 * @return the sub theme value or an empty string value
	 */
	public String getTheme() 
	{
		this.setTheme();
		return (!Utility.isNullOrEmpty(this.mainTheme) ? StringEscapeUtils.escapeHtml4(this.mainTheme) : "");
	}
	
		
	/**
	 * outputs the WET Sub Theme used by the application
	 * 
	 * @return the sub theme value or an empty string value
	 */
	public String getSubTheme() 
	{
		this.setSubTheme();
		return (!Utility.isNullOrEmpty(this.subTheme) ? StringEscapeUtils.escapeHtml4(this.subTheme) : "");
	}
	
	
	/**
	 * outputs the value to indicate whether HTTPS protocol should be used or not
	 * 
	 * @return the use HTTPS protocol boolean flag value
	 */
	public boolean getUseHttps() 
	{
		this.setUseHttps();
		return this.useHttps;
	}
	
	/**
	 * Builds a string with the format required by the closure templates, to represent the breadcrumb links 
	 * The list of breadcrumbs is provided by the application
	 * 
	 * sample breadcrumbs: [{ title: 'Home', href: 'http://www.canada.gc.ca/en/index.htm' }, 
	 * 						{ title: 'CDN Sample', acronym: 'Content Delivery Network Sample'}]
	 * 
	 * @return string in the format expected by the Closure Templates to generate the breadcrumb content
	 */
	public String getBreadcrumbsList()
    {
    	this.setBreadcrumbsList();
	
        StringBuilder sb = new StringBuilder();

        if (this.breadCrumbsList != null && this.breadCrumbsList.size() > 0)
        {
            sb.append("breadcrumbs: [");
            for (Breadcrumb lk : this.breadCrumbsList)
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
	 * Outputs the date modified value in the yyyy/MM/dd string format if a value has been supplied
	 * 
	 * @return the date modified value in a formatted string or an empty string value
	 */
	public String getDateModified() {
		this.setDateModified();
		
		if (this.dateModified != null) {
		    return (new SimpleDateFormat("yyyy-MM-dd")).format(this.dateModified);
		} else {
		    return "";
		}
    }
	
	/**
	 * The WET Template allows the date modified section of the page to either display 
	 * the date modified value or a version identifier value, this method will output 
	 * the appropriate portion of the final JSON string so that the html is rendered 
	 * appropriately.
	 * 
	 * @return the portion of the JSON value for either date modified or version identifier
	 */
	public String getDateModifiedOrVersionIdentifierValue() {
    	String dateModifiedValue = this.getDateModified();
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
	
	/**
	 * outputs the title of the page set by application programmatically
	 * 
	 * @return the page title value or an empty string value
	 */
	public String getHeaderTitle() {
		this.setHeaderTitle();
		if (this.headerTitle == null) this.headerTitle = "";
		
		if (this.getTheme().toLowerCase().equals("gcweb") &&  //NOTE: Hardcoding, should this be a new "titleSuffix" property?
            !this.headerTitle.endsWith(" - Canada.ca") )		    
		{
		    return StringEscapeUtils.escapeHtml4(this.headerTitle + " - Canada.ca");
		}
		
		return StringEscapeUtils.escapeHtml4(this.headerTitle);
	}
	
	/**
	 * Adds a string (html tag) to be included in the page
	 * This is our way of letting the developer to add metatags, css and js to their pages.
	 * 
	 * We are accepting a string with no validation, therefore it is up to the developer to provide 
	 * a valid string/html tag
	 * 
	 * @return outputs the list of html tag that were added programmatically
	 */
	public String getHtmlBodyElements() {
    	this.setHtmlBodyElements();
    	
    	StringBuilder sb = new StringBuilder();
        if (this.htmlBodyElements != null && this.htmlBodyElements.size() > 0)
        {
        	int idx = 0;
            for (String elem : this.htmlBodyElements) 
            {
            	sb.append((idx == 0 ? "\r\n" : "") + elem + (idx == (this.htmlBodyElements.size() - 1) ? "" : "\r\n"));
            	idx++;
            }
        }
	        
		return sb.toString();
    }
    
	/**
	 * Adds a string (html tag) to be included in the page
	 * This is our way of letting the developer to add metatags, css and js to their pages.
	 * 
	 * We are accepting a string with no validation, therefore it is up to the developer to provide 
	 * a valid string/html tag
	 * 
	 * @return outputs the list of html tag that were added programmatically
	 */
    public String getHtmlHeaderElements() {
    	this.setHtmlHeaderElements();
    	
    	StringBuilder sb = new StringBuilder();
        if (this.htmlHeaderElements != null && this.htmlHeaderElements.size() > 0)
        {
        	int idx = 0;
            for (String elem : this.htmlHeaderElements)
            {
            	sb.append((idx == 0 ? "\r\n" : "") + elem + (idx == (this.htmlHeaderElements.size() - 1) ? "" : "\r\n"));
            	idx++;
            }
        }
	        
		return sb.toString();
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
	 * @return either 'Fran�ais' or 'English'
	 */
	public String getLanguageLinkText() {
		if (this.getTwoLetterCultureLanguage().toUpperCase().equals(Constants.ENGLISH_ACCRONYM.toUpperCase())) {
		    return Constants.LANGUAGE_LINK_FRENCH_TEXT;
		} else {
		    return Constants.LANGUAGE_LINK_ENGLISH_TEXT;
		}
    }
	
	/**
	 * Outputs the url to be used for the language toggle, there is a default url already in place for either the 
	 * JSF or JSP implementation of the Template, but can be set by application programmatically.
	 * 
	 * @return the default language toggle url or the value set programmatically
	 */
	public String getLanguageLinkUrl() {
		this.setLanguageLinkUrl();
		
		if (Utility.isNullOrEmpty(this.langLinkUrl))
			return this.getDefaultLangLinkUrl();
		else 
			return this.langLinkUrl;
    }
	
	/**
	 * A comma delimited list of domains that would be excluded from raising the leave secure site warning.
	 * This can be set in either cdn.properties or by application programmatically.
	 * 
	 * @return the comma delimited list of domains
	 */
	public String getLeavingSecureSiteExcludedDomains() {
    	this.setLeavingSecureSiteExcludedDomains();
    	return StringEscapeUtils.escapeHtml4(this.leavingSecureSiteExcludedDomain);
    }
	
	/**
	 * Outputs the url to redirect to when leave secure site warning is enabled and user clicked a link that 
	 * leaves the secure session.  There is a default url already in place for either the JSF or JSP 
	 * implementation of the Template, but can be set by application programmatically.
	 * 
	 * @return the default leave secure site redirect url or the value set programmatically
	 */
	public String getLeavingSecureSiteRedirectUrl() {
		this.setLeavingSecureSiteRedirectUrl();
		
		if (this.leavingSecureSiteRedirectUrl.trim().equals("")) {
			return this.getDefaultLeaveSecureSiteRedirectUrl();
		} else {
			return BaseUtil.encodeUrl(StringEscapeUtils.escapeHtml4(this.leavingSecureSiteRedirectUrl));
		}
    }
	
	/**
	 * Determines if the leave secure site warning should be displayed if the user navigates outside the 
	 * secure session, this can be set in either cdn.properties or by application programmatically.
	 * 
	 * @return the leave secure site warning enabled flag value
	 */
    public boolean getLeavingSecureSiteWarningEnabled() {
		this.setLeavingSecureSiteWarningEnabled();
		return this.leavingSecureSiteWarningEnabled;
    }

    /**
     * The leave secure site warning message to be displayed to the user when clicking a link that 
     * leaves the secure session, this can be set in either cdn.properties or by application programmatically.
     * 
     * @return the leave secure site warning message text
     */
    public String getLeavingSecureSiteWarningMessage() {
		this.setLeavingSecureSiteWarningMessage();
		return StringEscapeUtils.escapeHtml4(this.leavingSecureSiteWarningMessage);
    }
    
    /**
     * Control whether the leave secure site warning message modal window is to be displayed to the user when clicking a 
     * link that leaves the secure session, this can be set in either cdn.properties or by application programmatically.
     * 
     * @return the control flag to determine if showing the leave secure site warning message modal window
     */
    public boolean getLeavingSecureSiteDisplayModalWindow() {
    	this.setLeavingSecureSiteDisplayModalWindow();
    	return this.leavingSecureSiteDisplayModalWindow;
    }
    
    /**
     * Builds a string with the format required by the closure templates, to represent the left side menu.
     * 
     * @return string in the format expected by the Closure Templates to generate the left side menu
     */
    public String getLeftMenuSections() {
    	this.setLeftMenuSections();
    	
		StringBuilder sb = new StringBuilder();
		if (this.leftMenuSections != null && this.leftMenuSections.size() > 0){
			sb.append("sections: [");
			for (MenuSection menuSection : this.leftMenuSections)
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
     * Builds a string with the format required by the closure templates, to manage the privacy notice link in 
     * transaction mode only.
     * 
     * @return string in the format expected by the Closure Templates to manage the privacy notice link in a transaction mode only
     */
    public String getPrivacyLinkUrl() {
    	this.setPrivacyLinkUrl();
    	return BaseUtil.encodeUrl(StringEscapeUtils.escapeHtml4(this.privacyLinkUrl));
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
        
        //TODO: The "setXXX" calls are to allow implementaters to set/override the value. This should be revised in favor of a single "initialize" method.
        this.setTermsConditionsLinkUrl();
        this.setPrivacyLinkUrl();
        this.setContactLinks();
        this.setCustomFooterLinks();
        
        if ((this.customFooterLinks != null) && (this.customFooterLinks.size() > 0))
        {
            tmpFooterLinks = new ArrayList<FooterLink>();
            for (FooterLink fl: this.customFooterLinks)
                tmpFooterLinks.add(new FooterLink(BaseUtil.encodeUrl(fl.getHref()), 
                                   JsonValueUtils.GetNonEmptyString(fl.getText()), 
                                   fl.getNewWindow()));
        }
        
        appFooter = new AppFooter(
                        this.getCdnEnvironmentParsed(),
                        JsonValueUtils.GetNonEmptyString(this.getSubTheme()),
                        JsonValueUtils.GetNonEmptyString(this.getLocalPath()),
                        this.getShowGlobalNav(),
                        tmpFooterLinks,
                        JsonValueUtils.GetNonEmptyLinkList(this.contactLinks),
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
        ArrayList<Breadcrumb>   tmpBreadcrumbs = null;
        
        //TODO: The "setXXX" calls are to allow implementaters to set/override the value. This should be revised in favor of a single "initialize" method. (will also impact the checkIfBothSignInOutareSet call below)
        this.setApplicationTitleText();
        this.setCustomSiteMenuUrl();
        this.setShowSearch();
        this.setBreadcrumbsList();
        this.setShowPreContent();

        if ((this.breadCrumbsList != null) && (this.breadCrumbsList.size() > 0))
        {
            tmpBreadcrumbs = new ArrayList<Breadcrumb>();
            for (Breadcrumb bc: this.breadCrumbsList)
            {
                tmpBreadcrumbs.add(new Breadcrumb(
                                        BaseUtil.encodeUrl(bc.getHref()), 
                                        JsonValueUtils.GetNonEmptyString(bc.getTitle()), 
                                        JsonValueUtils.GetNonEmptyString(bc.getAcronym())) );                
            }
        }
        
        appTop = new AppTop(
                    this.getCdnEnvironmentParsed(),
                    JsonValueUtils.GetNonEmptyString(this.getSubTheme()),
                    JsonValueUtils.GetNonEmptyString(this.getLocalPath()),
                    JsonValueUtils.GetNonEmptyString(this.applicationTitleText),
                    JsonValueUtils.GetNonEmptyURLEscapedString(this.customSiteMenuUrl),
                    this.buildLanguageLinkList(),
                    this.getShowSiteMenu(),
                    this.getShowSecureIcon(),
                    this.buildHideableHrefOnlyLink(this.getSignInLinkUrl(), this.getShowSignInLink()),
                    this.buildHideableHrefOnlyLink(this.getSignOutLinkUrl(), this.getShowSignOutLink()),
                    this.showSearch,
                    tmpBreadcrumbs,
                    this.showPreContent
                );

        //NOTE: We do this here because variables are not initialize until after the call to getShowSignInLink/getShowSignOutLink (because it calls its corresponding setXXX method)
        this.checkIfBothShowSignInAndOutAreSet();
        
        return gson.toJson(appTop);
    }
    
    /**
     * Builds a string with the format required by the closure templates, to represent the Application Title 
     * information (only applicable to the GCIntranet theme).
     * 
     * 
     * @return string in the format expected by the Closure Templates to generate the application title
     */
    public String getRenderApplicationTitle() {
    	this.setApplicationTitleText();
    	this.setApplicationTitleUrl();
    	
    	if (!Utility.isNullOrEmpty(this.applicationTitleText))
        {
            //intranetTitle: [{href: "http://hrsdc.prv/eng/iit/index.shtml", text: "IITB"}],
            return "intranetTitle: [{" + 
            	    	(Utility.isNullOrEmpty(this.applicationTitleUrl) ? 
            	    			"" : 
            	    			"href: \"" + BaseUtil.encodeUrl(StringEscapeUtils.escapeHtml4(this.applicationTitleUrl)) + "\", ") + 
            	    	"text: \"" + StringEscapeUtils.escapeHtml4(this.applicationTitleText) + "\"" + 
            	    	"}],";
        }
    	
        return "";
    }
    
    /**
     * Builds a string with the format required by the closure templates, to represent the features/activities.
     * 
     * sample output: showFeatures: true
     * 
     * @return string in the format expected by the Closure Templates to generate the features
     */
    public String getRenderFeatures() {
		if (this.getShowFeature()) {
		    return "showFeatures: true,";
		} else {
		    return "showFeatures: false,";
		}
    }
    
    /**
     * Builds a string with the format required by the closure templates, to represent the feedback link.
     * 
     * 
     * sample output: showFeatures: true
     * 
     * @return string in the format expected by the Closure Templates to generate the features
     */
    public String getRenderFeedbackLink() {
		if (this.getShowFeedbackLink()) {
			this.setFeedbackUrl();
			return (!Utility.isNullOrEmpty(this.feedbackUrl) ? "showFeedback: '" + BaseUtil.encodeUrl(StringEscapeUtils.escapeHtml4(this.feedbackUrl)) + "'," : "");
		} else {
		    return "showFeedback: false,";
		}
    }
    
    /**
     * Builds a string with the format required by the closure templates, to represent the Language toggle link.
     * 
     * @return string in the format expected by the Closure Templates to generate the language toggle link
     */
    public String getRenderLanguageLink() {
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
     * Builds a string with the format required by the closure templates, to manage the leave secure site warning 
     * feature offered by the WET Template.
     * 
     * sample output: exitScript: true, 
     * 				  exitURL: "", 
     * 				  exitMsg: "", 
     * 				  exitDomains: ""
     * 
     * @return string in the format expected by the Closure Templates to manage the leave secure site warning feature
     */
    public String getRenderLeavingSecureSiteWarning() {
    	StringBuilder sb = new StringBuilder();
        if (this.getLeavingSecureSiteWarningEnabled() && !Utility.isNullOrEmpty(this.getLeavingSecureSiteRedirectUrl())) {
            sb.append("exitScript: true,");
            sb.append(this.getLeavingSecureSiteDisplayModalWindow() ? "" : " displayModal: false,");
            sb.append("exitURL: \"");
            sb.append(BaseUtil.encodeUrl(StringEscapeUtils.escapeHtml4(this.getLeavingSecureSiteRedirectUrl())));
            sb.append("\",");
            sb.append("exitMsg: \"");
            sb.append(StringEscapeUtils.escapeHtml4(this.getLeavingSecureSiteWarningMessage()));
            sb.append("\",");
            
            if (!Utility.isNullOrEmpty(this.getLeavingSecureSiteExcludedDomains())) {
            	sb.append("exitDomains: \"");
            	sb.append(StringEscapeUtils.escapeHtml4(this.getLeavingSecureSiteExcludedDomains()));
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
     * Builds a string with the format required by the closure templates, to represent a list of links for :
     * 	- Contact Us
     * 	- About Us
     * 	- News
     * The list of links is provided by the application
     * 
     * @return string in the format expected by the Closure Templates te generate the list of links
     */
    public String getRenderLinksList() {
		this.setContactLinks();
		this.setNewsLinks();
		this.setAboutLinks();
		
		StringBuilder sb = new StringBuilder();

        this.buildLinksJSONString(sb, "contactLinks", this.contactLinks);
        this.buildLinksJSONString(sb, "newsLinks", this.newsLinks);
        this.buildLinksJSONString(sb, "aboutLinks", this.aboutLinks);
        
        return sb.toString();
    }
    
    /**
     * Builds a string with the format required by the Closure Templates, to represent the Screen Identifier 
     * value.  If the application did not supply a Screen Identifier value, nothing will be rendered.
     * 
     * @return string in the format expected by the Closure Template to generate the Screen Identifier execution script
     */
    public String getRenderScreenIdentifier() {
    	this.setScreenIdentifier();
    	return (!Utility.isNullOrEmpty(this.screenIdentifier) ? 
    				"screenIdentifier: \"" + StringEscapeUtils.escapeHtml4(this.screenIdentifier) + "\", " : 
    				"");
    }
    
    /**
     * Builds a string with the format required by the closure templates, to represent the Share Page Links.
     * If the application did not supply the Share Page items, the Share Page link will not be displayed.
     * 
     * sample output: showShare: ["email", "facebook", "linkedin", "twitter"]
     * 
     * @return string in the format expected by the Closure Templates to generate the Share Page Link
     */
    public String getRenderSharePageMediaSites() {
		StringBuilder sb = new StringBuilder();
		
		this.setSharePageMediaSites();
		
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
     * Builds a string with the format required by the closure template, to represent if the jQuery 
     * file should be loaded from Google or CDTS locally.
     *   
     * @return either "external" or "", this is used strictly by the various master template page
     */
    public String getRenderjQuery() {
    	this.setLoadjQueryFromGoogle();
    	return this.loadjQueryFromGoogle ? "jqueryEnv: \"external\"," : "";
    }
    
    /**
     * Builds a string with the format required by the closure template, to represent the "localPath" argument,
     * which should be specified when the project is hosting teh CDTS files locally (usually for development or load testing purposes).
     * 
     * @return Either "localPath: "...value/..."" or "", this is used strictly by the various master template page.
     */
    public String getRenderLocalPath() {
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
    	    
    		this.cdnLocalPathRender = this.getResourceBundleString("cdn", "cdn_" + this.getCDNEnvironment().toLowerCase() + "_localpath");
    		if (!Utility.isNullOrEmpty(this.cdnLocalPathRender))
    		{
    			String templateVersion = this.getTemplateVersion();
    			if (templateVersion == null) templateVersion = "";
    			
				this.cdnLocalPathRender = "localPath: \"" + 
										BaseUtil.encodeUrl(StringEscapeUtils.escapeHtml4(String.format(this.cdnLocalPathRender, this.getTheme(), templateVersion))) + 
									"\",";
    		}
    		else
    		{
    			this.cdnLocalPathRender = "";
    		}
    	}
    	
    	return this.cdnLocalPathRender;    			
    }
    
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
    
    /**
     * Outputs the portion of the SoyUtils and WET url in each of the master template page, the value 
     * outputted is determined by the current WET Template Version identified in cdn.properties, if 
     * no value is specified the "run" version of the WET Template will be utilized.
     *   
     * @return either "rn" or "app", this is used strictly by the various master template page
     */
    public String getRunOrVersionValue() {
    	if (Utility.isNullOrEmpty(this.getTemplateVersion())) { 
    		return "rn";
    	} else {
    		return "app";
    	}
    }
    
    /**
     * Determines the configuration object containing the various session timeout settings, 
     * these can be set in either cdn.properties or by application programmatically
     * 
     * @return the session timeout configuration settings in an entity
     */
    public SessionTimeout getSessionTimeoutConfigurations() {
		if (this.getSessionTimeoutEnabled()) {
			this.setSessionTimeoutConfigurations();
			if (this.sessionTimeoutConfigurations != null)
				return this.sessionTimeoutConfigurations;
			else {
				java.util.ResourceBundle bundle = this.getResourceBundle();
				this.sessionTimeoutConfigurations = this.buildDefaultSessionTimeoutConfigurations(bundle);
				return this.sessionTimeoutConfigurations;
			}
		} else {
			return null;
		}		
	}
    
    /**
     * Builds the html of the WET Session Timeout Control that provided session timeout and inactivity functionality.
     * For more documentation: https://wet-boew.github.io/v4.0-ci/demos/session-timeout/session-timeout-en.html
     * 
     * @return the html of the WET session timeout control
     */
    public String getSessionTimeoutControl() {
    	StringBuilder sb = new StringBuilder();
        
        if (this.getSessionTimeoutEnabled()) { 
        	SessionTimeout configs = this.getSessionTimeoutConfigurations();
        	
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
     * Determines if the session timeout functionality are enabled, this can be set in 
     * either cdn.properties or by application programmatically
     * 
     * @return the session timeout functionality enabled flag value
     */
	public boolean getSessionTimeoutEnabled() { 
		this.setSessionTimeoutEnabled();
		return this.sessionTimeoutEnabled;
	}
	
	/**
	 * Determines if the features of the footer are to be displayed, this can be set in 
	 * either cdn.properties or by application programmatically.
	 * 
	 * @return the show feature flag value
	 */
	public boolean getShowFeature() {
		this.setShowFeature();
		return this.showFeature;
    }
	
	/**
	 * Determines if the FeedBack link of the footer is to be displayed, this can be set in 
	 * either cdn.properties or by application programmatically.
	 * 
	 * @return the show feedback link flag value
	 */
	public boolean getShowFeedbackLink() {
		this.setShowFeedbackLink();
		return this.showFeedbackLink;
    }
	
	/**
	 * Determine if the Language Toggle Link is to be displayed, this can be set in 
	 * either cdn.properties or by application programmatically.
	 * 
	 * @return the show language toggle link flag value
	 */
	public boolean getShowLanguageLink() {
		this.setShowLanguageLink();
		return this.showLanguageLink;
	}
	
	/**
	 * Determines if the Post Content of the footer are to be displayed, this can be 
	 * set in either cdn.properties or by application programmatically.
	 * 
	 * @return the show post content flag value
	 */
	public String getShowPostContent() {
		this.setShowPostContent();
		return (this.showPostContent ? "true" : "false");
    }
	
	/**
	 * Determines if the Pre Content of the header is to be displayed, this can be 
	 * set in either cdn.properties or by application programmatically.
	 * 
	 * @return the show pre content flag value
	 */
	public String getShowPreContent() {
		this.setShowPreContent();
		return (this.showPreContent ? "true" : "false");
    }
	
	/**
	 * Determines if the Search control of the header are to be displayed, this can 
	 * be set in either cdn.properties or by application programmatically.
	 * 
	 * @return the show search flag value
	 */
	public String getShowSearch() {
		this.setShowSearch();
		return (this.showSearch ? "search: true," : "search: false,");
    }

	/**
	 * Determines if the Share Page Link of the footer are to be displayed, this can be 
	 * set in either cdn.properties or by application programmatically.
	 * 
	 * @return the show share page link flag value
	 */
	public boolean getShowSharePageLink() {
		this.setShowSharePageLink();
		return this.showSharePageLink;
    }
	
	/**
	 * Determines the path to the location of the static backup files, this can be set 
	 * in either cdn.properties or by application programmatically. The default value point 
	 * to the "StaticFallbackFiles" directory under "WebContent" in the eclipse project.
	 * 
	 * @return the static backup files directory location
	 */
	public String getStaticFallbackFilePath() {
    	this.setStaticFallbackFilePath();
    	return StringEscapeUtils.escapeHtml4(this.staticFilePath);
    }

	/**
	 *  Determines if the Global Nav bar in the footer is to be displayed.
     *  Set by application programmatically or in the cdn.properties
     *  Only available in the Application Template
	 */
	public boolean getShowGlobalNav() {
	    this.setShowGlobalNav();
	    return this.showGlobalNav;
	}
	
	/**
     *  Determines if the Site Menu is to appear at the top of the page. 
     *  If set to false only a blue band will be seen.
     *  Set by application programmatically or in the cdn.properties
     *  Only available in the Application Template
     */
	public boolean getShowSiteMenu() {
	    this.setShowSiteMenu();
	    return this.showSiteMenu;
	}
	
	/**
     * A custom site menu to be used in place of the standard canada.ca site menu
     * This defaults to null (use standard menu)
     * Set by application programmatically or in the cdn.properties
     * Only available in the Application Template
	 */
	public String getCustomSiteMenuUrl() {
	    this.setCustomSiteMenuUrl();
	    return BaseUtil.encodeUrl(StringEscapeUtils.escapeHtml4(this.customSiteMenuUrl));
	}

	/**
     * The link to use for the sign in button, will only appear if getShowSignInLink() is set to true
     * Set by application programmatically or in the cdn.properties
     * Only available in the Application Template
     * 
     * @see getShowSignInLink()
	 */
	public String getSignInLinkUrl() {
	    this.setSignInLinkUrl();
	    return BaseUtil.encodeUrl(StringEscapeUtils.escapeHtml4(this.signInLinkUrl));
	}
	
	/**
     * The link to use for the sign out button, will only appear if getSignOutLinkUrl() is set to true
     * Set by application programmatically or in the cdn.properties
     * Only available in the Application Template
     * 
     * @see getShowSignOutLink()
	 */
	public String getSignOutLinkUrl() {
	    this.setSignOutLinkUrl();
        return BaseUtil.encodeUrl(StringEscapeUtils.escapeHtml4(this.signOutLinkUrl));	    
	}
	
	/**
     * Displays the secure icon next to the applicaiton name in the header.
     * Set by application programmatically
     * Only available in the Application Template
	 */
	public boolean getShowSecureIcon() {
	    this.setShowSecureIcon();
	    return this.showSecureIcon;
	}
	
	/**
     * Displays the sign in link set.
     * signInLinkUrl must not be null or whitespace
     * showSignOutLink must not be set at the same time.
     * Set by application programmatically
     * Only available in the Application Template
	 */
	public boolean getShowSignInLink() {
	    this.setShowSignInLink();
	    return this.showSignInLink;
	}
	
	/**
     * Displays the signout link set.
     * signOutLinkUrl must not be null or whitespace
     * showSignInLink must not be set at the same time.
     * Set by application programmatically
     * Only available in the Application Template
	 */
	public boolean getShowSignOutLink() {
	    this.setShowSignOutLink();
	    return this.showSignOutLink;
	}
	
	/**
     * Custom links if null uses standard links if not null overrides the existing footer links
     * Set by application programmatically
     * Only available in the Application Template
	 */
	public ArrayList<FooterLink> getCustomFooterLinks() {
	    this.setCustomFooterLinks();
	    return this.customFooterLinks;
	}
	
	/**
	 * Determines if the master template pages should use the "Run" or a specific version of the 
	 * static backup files.  This is determined by the wet template version specified in cdn.properties.
	 * 
	 * @return either the "Run" or a specific version directory under the static backup files directory
	 */
	/*public String getStaticRunOrVersionValue() {
    	String templateVersion = this.getResourceBundleString("cdn", "wettemplate_version");
    	return Utility.isNullOrEmpty(templateVersion) ? "Run" : templateVersion;
    }*/
	
	/** 
	 * The url to be used for the Terms & Conditions link in transactional mode set by the application 
	 * programmatically.
	 * 
	 * @return the terms and conditions link
	 */
	public String getTermsConditionsLinkUrl() {
    	this.setTermsConditionsLinkUrl();
    	return BaseUtil.encodeUrl(StringEscapeUtils.escapeHtml4(this.termsConditionsLinkUrl));
    }
	
	/**
	 * Outputs the cdn environment value that the closure template is expecting, the value 
	 * is dependent on the CDN environment set by the application either from cdn.properties or 
	 * programmatically
	 * 
	 * @return the cdn environment value expected by the closure template
	 */
	public String getCdnEnvironmentParsed() {
		String envValue = this.getResourceBundleString("cdn", "cdn_" + this.getCDNEnvironment().toLowerCase() + "_env");
		return (Utility.isNullOrEmpty(envValue) ? "prod" : envValue);
	}
	
	/**
     * Outputs the portion of the SoyUtils and WET url in each of the master template page, the value 
     * outputted is determined by the current WET Template Version identified in cdn.properties, if 
     * no value is specified the "run" version of the WET Template will be utilized.
     *   
     * @return either "" if in "Run" mode or the wet template version value, this is used strictly by the various master template page
     */
	public String getThemeVersionValue() {
    	if (this.getRunOrVersionValue().equals("rn")) {
    		return "";
    	} else {
    		return this.getTemplateVersion() + "/";
    	}
    }
	
	/**
	 * Version of the application to be displayed instead of the date modified, this is 
	 * set by application programmatically only.
	 * 
	 * @return the version identifier or an empty string
	 */
    public String getVersionIdentifier() {
    	this.setVersionIdentifier();
    	return StringEscapeUtils.escapeHtml4(this.versionIdentifier);
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
     * helper method to build the Contact Us, About Us and News links
     * 
     * @param sb
     * @param linkTitle
     * @param linksList
     */
    private void buildLinksJSONString(StringBuilder sb, String linkTitle, ArrayList<Link> linksList) {
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
     * helper method to build the default SessionTimeout configuration object using default value 
     * specified in the resource bundle
     * 
     * @param bundle
     * @return
     */
    private SessionTimeout buildDefaultSessionTimeoutConfigurations(java.util.ResourceBundle bundle) {
    	SessionTimeout configs = new SessionTimeout();
    	
    	if (bundle != null) {
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
}
