package goc.webtemplate.jsf.samplebeans;

import java.util.ArrayList;
import java.util.Arrays;

import goc.webtemplate.ApplicationTitle;
import goc.webtemplate.FooterLink;
import goc.webtemplate.Link;

import goc.webtemplate.component.jsf.DefaultTemplateCoreBean;

public class ApplicationSample extends DefaultTemplateCoreBean {

    @Override
    public void onWebTemplateInitialize() {
        this.setHeaderTitle("My Application Title");
        
        this.setShowSecureIcon(true);
        
        //NOTE: Application Title's URL will only take effect for ESDC's GCIntranet theme
        this.setApplicationTitle(new ApplicationTitle("My Application", "#myapplicationurl"));
        
        //(this will only take effect for ESDC's GCIntranet theme)
        this.setIntranetTitle(new Link("#intranetlink", "My Intranet"));

        //NOTE: This can also be set at the application level by setting the property goc.webtemplate.showglobalnav in cdn.properties
        //      (in this sample, default in cdn.properties is false)
        this.setShowGlobalNav(false);

        //NOTE: showGlobalNav must be false for the footer links to appear.
        this.setCustomFooterLinks(new ArrayList<FooterLink>(Arrays.asList( 
                new FooterLink[] {
                        new FooterLink("#", "Footer Link 1", false),
                        new FooterLink("#", "Footer Link 2", true)
                })));
        
        //NOTE: This can also be set at the application level by setting the property goc.webtemplate.showsitemenu in cdn.properties
        //      (in this sample, default in cdn.properties is true)
        this.setShowSiteMenu(true);
        
        //NOTE: This can also be set at the application level by setting the property goc.webtemplate.customsitemenuurl in cdn.properties
        //      (in this sample, default in cdn.properties is blank, which means the default menu will be used)
        this.setCustomSiteMenuUrl( this.getRequest().getContextPath() + "/samplecontents/mycustommenu.html" );
        
        //NOTE: This can also be set at the application level by setting the property goc.webtemplate.signinlinkurl in cdn.properties
        //      (in this sample, default in cdn.properties is blank)
        this.setSignInLinkUrl("#"); //we don't really have a sign-in page/facility for the sample project, just link to self
        //NOTE: This can also be set at the application level by setting the property goc.webtemplate.signoutlinkurl in cdn.properties
        //      (in this sample, default in cdn.properties is blank)
        this.setSignOutLinkUrl("#"); //we don't really have a sign-out page/facility for the sample project, just link to self 
        
        //NOTE: The following two overrides are set programatically only, would be based on the state of user's login.  Only one can be true at any given time
        this.setShowSignInLink(true);
        this.setShowSignOutLink(false);
    }
}
