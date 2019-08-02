<h1>GoC Web Template Samples - Standard Application Settings Sample</h1>
<section class="alert alert-danger">
    <h2>Notice for Implementers</h2>
    <p>Please ensure you have permission from your department, TBS or Principal Publisher before proceeding. The changes below DO NOT currently follow the <a rel="external" class="alert-link" href="http://www.gcpedia.gc.ca/wiki/Canada.ca_Content_and_Information_Architecture_Specification">C&amp;IA specifications document</a> </p>
</section>

<p><a rel="external" href="http://www.gcpedia.gc.ca/wiki/Content_Delivery_Network/GoC_.NET_template_guide">Web Template Documentation (GCPedia)</a></p>

<p>This sample uses the <code class="wb-prettify">"/templates/application-mastertemplate.jsp"</code> master page to demonstrate how pages usign the application template are displayed and configured.</p>

<div class="alert alert-warning">
    <h2>Left Menu Variant</h2>
    <p>There is also a left menu version of this template it's available by using the <code class="wb-prettify">/templates/application-leftmenu-mastertemplate.jsp</code> layout for your page.</p>
    <p>The Left Menu is implemented in the same way as the Left Menu for the other templates. See <a href="leftsidemenusample.action">Left Side Menu Sample</a> for details on how to implement.</p>
</div>

<h2>Pre-requisite</h2>
<p>To override the Default GoC Web Template look &amp; feel, you will have to create a custom bean class that extends the <code class="wb-prettify">goc.webtemplate.component.jsp.BaseCoreBean</code> class, and then override the various methods made available to alter the look &amp; feel of the web page.</p>
<p>For this particular sample page, we are using the <code class="wb-prettify">goc.webtemplate.jsp.samplebeans.ApplicationSampleBean</code> bean class.</p>
<p>The bean must be included and initialized in a jsp page as part of the <strong>beaninit</strong> attribute that is defined by the master template tiles definition outline in the tiles.xml configuration file:</p>
<p>The custom bean name must be <strong>goctemplateclientbean</strong> and a <strong>request</strong> parameter must be also be present as follow:</p>
<div class="wb-prettify all-pre lang-vb linenums">
    <pre>
&lt;s:bean name="goc.webtemplate.jsp.samplebeans.ApplicationSampleBean" var="goctemplateclientbean"&gt;
    &lt;s:param name="request" value="#request.servletrequest" /&gt;
&lt;/s:bean&gt;
    </pre>
</div>

<h2>Application Name</h2>
<p>
    This setting determines the application title for your site. Set programmatically by calling the <code class="wb-prettify">setApplicationTitle</code> method in your custom bean class.
</p>
<div class="wb-prettify all-pre lang-c# linenums">
    <pre>
@Override
public void onWebTemplateInitialize() {
//...
    //NOTE: Application Title's URL will only take effect for ESDC's GCIntranet theme
    this.setApplicationTitle(new Link("#myapplicationurl", "My Application"));
//...        
}
    </pre>
</div>
<h2>Custom Footer Links</h2>
<p>
    <strong>This property is only available while using the GCweb theme. For the GCIntranet theme, see Custom Footer Sections below.</strong> A list of <code class="wb-prettify">customFooterLinks</code> is available for you to provide your own links in the application footer.
</p>
<p>
    <code class="wb-prettify">customFooterLinks</code> expects a list of <code class="wb-prettify">FooterLink</code> objects that have the following properties.
</p>
<ul>
    <li><code class="wb-prettify">Href</code> the HREF for the footer link</li>
    <li><code class="wb-prettify">Text</code> the text to display on the link</li>
    <li><code class="wb-prettify">NewWindow</code> a flag to specify if this link opens in a new tab, defaults to false</li>
</ul>
<div class="wb-prettify all-pre lang-c# linenums">
    <pre>
@Override
public void onWebTemplateInitialize() {
//...
    this.setCustomFooterLinks(new ArrayList&lt;FooterLink&gt;(Arrays.asList( 
            new FooterLink[] {
                    new FooterLink("#", "Footer Link 1", false),
                    new FooterLink("#", "Footer Link 2", true)
            })));
//...
}
   </pre>
</div>
<h2>Custom Footer Sections</h2>
<p>
    <strong>This property is only available while using the GCIntranet theme and has a maximum of 3 sections. For the GCweb theme, see Custom Footer Links above.</strong> A list of <code class="wb-prettify">customFooterSections</code> is available for you to provide your own links in the application footer, grouped in sections each with its title.
</p>
<p>
    <code class="wb-prettify">customFooterSections</code> expects a list of <code class="wb-prettify">FooterSection</code> objects that have the following properties.
</p>
<ul>
    <li><code class="wb-prettify">Name</code> the title of the section</li>
    <li><code class="wb-prettify">CustomFooterLinks</code> the list of links for the section.</li>
</ul>
<div class="wb-prettify all-pre lang-c# linenums">
    <pre>
@Override
public void onWebTemplateInitialize() {
//...
    FooterSection footerSection1 = new FooterSection("Section One", 
            new ArrayList&lt;FooterLink>(Arrays.asList( 
                    new FooterLink[] {
                            new FooterLink("#", "Footer Section 1 Link 1", false),
                            new FooterLink("#", "Footer Section 1 Link 2", true)
                    })));
    FooterSection footerSection2 = new FooterSection("Section Two", 
            new ArrayList&lt;FooterLink>(Arrays.asList( 
                    new FooterLink[] {
                            new FooterLink("#", "Footer Section 2 Link 1", false),
                            new FooterLink("#", "Footer Section 2 Link 2", true)
                    })));
    this.setCustomFooterSections(new ArrayList&lt;FooterSection>(Arrays.asList(new FooterSection[] {
            footerSection1, footerSection2})));
//...
}
   </pre>
</div>
<h2>Custom Search</h2>
<p>This setting allows you to override the default search behaviour.</p>
<p>Get more details in the code hints and from <a href="http://www.gcpedia.gc.ca/wiki/Centrally_Deployed_Templates_Solution_(CDTS)/Intranet#Section_top_references">CDTS documentation</a></p>
<div class="wb-prettify all-pre lang-c# linenums">
    <pre>
@Override
public void onWebTemplateInitialize() {
//...
    CustomSearch customSearch = new CustomSearch("mysearchaction.action", "Search MyApp", null, "POST");    
    java.util.HashMap<String, String> hiddenInputs = new java.util.HashMap<>();
    
    hiddenInputs.put("name1", "value1");
    customSearch.setHiddenInput(hiddenInputs);
    
    this.setCustomSearch(customSearch);
//...
}
    </pre>
</div>
<h2>Site Menu</h2>
<p>The site menu can be hidden or overridden using the following settings.</p>
<p>Although not required, it is suggested that the CDTS team be contacted before enabling this option.</p>
<ul>
    <li>
        <code class="wb-prettify">setCustomSiteMenuUrl</code> is used to specify a custom menu to be displayed instead of the standard one.   

        <p>You can copy the format in the sample custom menu file provided below in our sample.</p>
        <a href="https://ssl-templates.services.gc.ca/app/cls/wet/gcweb/v4_0_25/cdts/custommenu-en.html">https://ssl-templates.services.gc.ca/app/cls/wet/gcweb/v4_0_25/cdts/custommenu-en.html</a>
    </li>
    <li>
        <code class="wb-prettify">setMenuLinks</code> can be used insead to specify a custom menu based on MenuItem objects.
    </li>   
</ul>
<div class="wb-prettify all-pre lang-c# linenums">
    <pre>
@Override
public void onWebTemplateInitialize() {
//...
    //NOTE: This can also be set at the application level by setting the property goc.webtemplate.customsitemenuurl in cdn.properties
    //      (in this sample, default in cdn.properties is blank, which means the default menu will be used)
    //NOTE: The menu can also be set by calling setMenuLinks(ArrayList&lt;MenuItem&gt;) instead. 
    this.setCustomSiteMenuUrl( this.getRequest().getContextPath() + "/samplecontents/mycustommenu.html" );
//...
}
    </pre>
</div>
<h2>Application Settings URL</h2>
<p>
    Call the <code class="wb-prettify">setAppSettingsUrl</code> method in your custom bean class to show a "application settings" link.
</p>
<div class="wb-prettify all-pre lang-c# linenums">
    <pre>
@Override
public void onWebTemplateInitialize() {
//...
    this.setAppSettingsUrl("#myappsettingsurl");
//...
}
    </pre>
</div>
<h2>Sign In &amp; Out</h2>
<p>You are able to control the visbility and the url for sign in and sign out buttons on the application menu.</p>
<div class="alert alert-warning">
    <p>
        <code class="wb-prettify">showSignInLink</code> and <code class="wb-prettify">showSignOutLink</code> cannot both be set to true at the same time.
    </p>
</div>
<h3>Sign In</h3>
<ul>
    <li> <code class="wb-prettify">setShowSignInLink</code> to set whether or not to show the Sign In Button </li>
    <li>
        <code class="wb-prettify">setSignInLinkUrl</code> is the location of your sign in page for this web application, this can also be set in the cdn.properties.
    </li>
</ul>
<div class="wb-prettify all-pre lang-c# linenums">
    <pre>
@Override
public void onWebTemplateInitialize() {
//...
    this.setShowSignInLink(true);
    this.setSignInLinkUrl("#");
//...
}
    </pre>
</div>
<h3>Sign Out</h3>
<ul>
    <li> <code class="wb-prettify">setShowSignOutLink</code> to set whether or not to show the Sign Out Button </li>
    <li>
        <code class="wb-prettify">setSignOutLinkUrl</code> is the location of your sign out page for this web application, this can also be set in the cdn.properties.
    </li>
</ul>
<div class="wb-prettify all-pre lang-c# linenums">
   <pre> 
@Override
public void onWebTemplateInitialize() {
//...
    this.setShowSignOutLink(true);
    this.setSignOutLinkUrl("#"); 
//...
}
   </pre>
</div>
<h2>Show/Hide Search</h2>
<p>This setting allows you to hide or show the search bar on the page.</p>
<p>The setting <code class="wb-prettify">showSearch</code> can be set programmatically or in the cdn.properties.</p>
<h2>Pre-Content</h2>
<p>This will be used by Principal Publisher to insert content into the pre content space of your page. By default this is ALWAYS shown on all pages. You will need authorization from the Principal Publisher to not include this content in your web asset.</p>
<p>If you recieve such authorization then you can turn off the pre-content programmatically by calling the <code class="wb-prettify">setShowPreContent</code> method in your custom bean class or in the cdn.properties.</p>

<%@ include file="_sampleslist.jsp" %>
