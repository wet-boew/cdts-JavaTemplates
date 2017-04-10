package goc.webtemplate.jsp.samplebeans;

import goc.webtemplate.FooterLink;

import goc.webtemplate.component.jsp.DefaultTemplateBean;

public class ApplicationSampleBean extends DefaultTemplateBean {

	@Override
	public void setHeaderTitle() {
		this.headerTitle = "My Application Title";
	}

	@Override
	public void setApplicationTitleText()
	{
	    this.applicationTitleText = "My Application";
	}
    
    @Override
    public void setShowSecureIcon()
    {
        this.showSecureIcon = true;
    }
    
    //NOTE: This can also be set at the application level by setting the property goc.webtemplate.showglobalnav in cdn.properties
    //      (in this sample, default in cdn.properties is false)
    @Override
    public void setShowGlobalNav()
    {
        this.showGlobalNav = false;
    }

    //NOTE: showGlobalNav must be false for the footer links to appear.
    @Override
    public void setCustomFooterLinks()
    {
        this.customFooterLinks.add(new FooterLink("#", "Footer Link 1", false));
        this.customFooterLinks.add(new FooterLink("#", "Footer Link 2", true));
    }

    //NOTE: This can also be set at the application level by setting the property goc.webtemplate.showsitemenu in cdn.properties
    //      (in this sample, default in cdn.properties is true)
    @Override
    public void setShowSiteMenu()
    {
        this.showSiteMenu = true;
    }

    //NOTE: This can also be set at the application level by setting the property goc.webtemplate.customsitemenuurl in cdn.properties
    //      (in this sample, default in cdn.properties is blank, which means the default menu will be used)
	@Override
	public void setCustomSiteMenuUrl()
	{
	    this.customSiteMenuUrl = this.getRequest().getContextPath() + "/samplecontents/mycustommenu.html";
	}
    
    //NOTE: This can also be set at the application level by setting the property goc.webtemplate.signinlinkurl in cdn.properties
    //      (in this sample, default in cdn.properties is blank)
	@Override
	public void setSignInLinkUrl()
	{
	    this.signInLinkUrl = "#"; //we don't really have a sign-in page/facility for the sample project, just link to self 
	}
    
    //NOTE: This can also be set at the application level by setting the property goc.webtemplate.signoutlinkurl in cdn.properties
    //      (in this sample, default in cdn.properties is blank)
    @Override
    public void setSignOutLinkUrl()
    {
        this.signOutLinkUrl = "#"; //we don't really have a sign-out page/facility for the sample project, just link to self 
    }
    
    //NOTE: The following two overrides are set programatically only, would be based on the state of user's login.  Only one can be true at any given time
    @Override
    public void setShowSignInLink()
    {
        this.showSignInLink = true;
    }
    @Override
    public void setShowSignOutLink()
    {
        this.showSignOutLink = false;
    }
}
