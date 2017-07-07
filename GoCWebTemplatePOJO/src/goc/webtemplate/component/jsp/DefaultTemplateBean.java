package goc.webtemplate.component.jsp;

public class DefaultTemplateBean extends BaseBean {

	@Override
	public void setHeaderTitle() {
		this.headerTitle = (this.getRequest() == null ? "DefaultBean" : this.getRequest().getRequestURI()); 
	}
	
	@Override
	public void setShowSearch() {}
	
	@Override
	public void setLanguageLinkUrl() {}
	
	@Override
	public void setShowPreContent() {}
	
	@Override
	public void setBreadcrumbsList() {}
	
	@Override
	public void setLeavingSecureSiteWarningEnabled() {}
	
	@Override
	public void setLeavingSecureSiteWarningMessage() {}
	
	@Override
	public void setLeavingSecureSiteRedirectUrl() {}
	
	@Override
	public void setDateModified() {}
	
	@Override
	public void setShowPostContent() {}
	
	@Override
	public void setShowFeedbackLink() {}
	
	@Override
	public void setShowSharePageLink() {}
	
	@Override
	public void setSharePageMediaSites() {}
	
	@Override
	public void setShowFeature() {}
	

    @Override
    public void setContactLinkUrl() {}
    
    /**
     * @deprecated contactLinks should not be overriden. It has been replaced by contactLinkUrl.  WILL BE REMOVED IN A FUTURE RELEASE.
     * @see #setContactLinkUrl()
     */
    @Deprecated
    @Override
	public void setContactLinks() {}
	
    /**
     * @deprecated newLinks should not be overriden as it is no longer used anywhere.  WILL BE REMOVED IN A FUTURE RELEASE.
     */
    @Deprecated
	@Override
	public void setNewsLinks() {}
	
    /**
     * @deprecated aboutLinks should not be overriden as it is no longer used anywhere.  WILL BE REMOVED IN A FUTURE RELEASE.
     */
    @Deprecated
	@Override
	public void setAboutLinks() {}
	
	@Override
	public void setHtmlHeaderElements() {}
	
	@Override
	public void setHtmlBodyElements() {}
	
	@Override
	public void setStaticFallbackFilePath() {}

	@Override
	public void setSessionTimeoutConfigurations() {}
	
	@Override
	public void setSessionTimeoutEnabled() {}

	@Override
	public void setLeftMenuSections() {}

	@Override
	public void setVersionIdentifier() {}

	@Override
	public void setLeavingSecureSiteExcludedDomains() {}

	@Override
	public void setPrivacyLinkUrl() {}

	@Override
	public void setTermsConditionsLinkUrl() {}

	@Override
	public void setFeedbackUrl() {}

	@Override
	public void setSubTheme() {}

	@Override
	public void setTheme() {}

	@Override
	public void setShowLanguageLink() {}

	@Override
	public void setScreenIdentifier() {}

	@Override
	public void setTemplateVersion() {}

	@Override
	public void setCDNEnvironment() {}

	@Override
	public void setUseHttps() {}

	@Override
	public void setApplicationTitleText() {}

	@Override
	public void setApplicationTitleUrl() {}

    @Override
    public void setIntranetTitle() {}

	@Override
	public void setLoadjQueryFromGoogle() {}

	@Override
	public void setLeavingSecureSiteDisplayModalWindow() {}
	
    @Override
    public void setShowGlobalNav() {}
    
    @Override
    public void setShowSiteMenu() {}
    
    @Override
    public void setCustomSiteMenuUrl() {}
    
    @Override
    public void setSignInLinkUrl() {}
    
    @Override
    public void setSignOutLinkUrl() {}
    
    @Override
    public void setShowSecureIcon() {}
    
    @Override
    public void setShowSignInLink() {}
    
    @Override
    public void setShowSignOutLink() {}
    
    @Override
    public void setCustomFooterLinks() {}
    
    /**
     * Allows for a custom search to be used in the application, you must contact CDTS to have one created.
     */
    @Override
    public void setCustomSearch() {}
}
