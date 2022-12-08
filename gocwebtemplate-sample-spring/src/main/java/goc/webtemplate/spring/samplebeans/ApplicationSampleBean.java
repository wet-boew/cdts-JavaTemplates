package goc.webtemplate.spring.samplebeans;

//import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Component;

import goc.webtemplate.FooterLink;
import goc.webtemplate.IntranetTitle;
import goc.webtemplate.Link;
import goc.webtemplate.component.spring.DefaultTemplateCoreBean;

@Component(value = "applicationsamplebean")
public class ApplicationSampleBean extends DefaultTemplateCoreBean {

    @Override
    public void onWebTemplateInitialize() {
        this.setHeaderTitle("My Application Title");
        
        //NOTE: Application Title's URL will only take effect for ESDC's GCIntranet theme
        this.setApplicationTitle(new Link("#myapplicationurl", "My Application"));
        
        //(this will only take effect for ESDC's GCIntranet theme)
        this.setIntranetTitle(new IntranetTitle("#intranetlink", "My Intranet", "ACRONYM"));

        // Custom Footer Links - GCweb theme only
        this.setCustomFooterLinks(Arrays.asList( 
                new FooterLink[] {
                        new FooterLink("#", "Footer Link 1", false),
                        new FooterLink("#", "Footer Link 2", true)
                }));

        // Custom Footer Sections - GCintranet theme only
        /*
        FooterSection footerSection1 = new FooterSection("Section One", 
                Arrays.asList( 
                        new FooterLink[] {
                                new FooterLink("#", "Footer Section 1 Link 1", false),
                                new FooterLink("#", "Footer Section 1 Link 2", true)
                        }));
        FooterSection footerSection2 = new FooterSection("Section Two", 
                Arrays.asList( 
                        new FooterLink[] {
                                new FooterLink("#", "Footer Section 2 Link 1", false),
                                new FooterLink("#", "Footer Section 2 Link 2", true)
                        }));
        this.setCustomFooterSections(Arrays.asList(new FooterSection[] {
                footerSection1, footerSection2}));
        */      

        // Setup custom search behavior
        /*
        CustomSearch customSearch = new CustomSearch("mysearchaction.action", "Search MyApp", null, "POST");
        java.util.HashMap<String, String> hiddenInputs = new java.util.HashMap<>();
        hiddenInputs.put("name1", "value1");
        customSearch.setHiddenInput(hiddenInputs);
        this.setCustomSearch(customSearch);
        */
        
        //NOTE: This can also be set at the application level by setting the property goc.webtemplate.customsitemenuurl in cdn.properties
        //      (in this sample, default in cdn.properties is blank, which means the default menu will be used)
        //NOTE: The menu can also be set by calling setMenuLinks(List<MenuItem>) instead. 
        this.setCustomSiteMenuUrl( this.getRequest().getContextPath() + "/mycustommenu.html" );
        
        this.setAppSettingsUrl("#myappsettingsurl");
        
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
