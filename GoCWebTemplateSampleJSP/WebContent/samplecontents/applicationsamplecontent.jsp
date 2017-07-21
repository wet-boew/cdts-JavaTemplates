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
<p>To override the Default GoC Web Template look &amp; feel, you will have to create a custom bean class that extends the <code class="wb-prettify">goc.webtemplate.component.jsp.BaseBean</code> class, and then override the various methods made available to alter the look &amp; feel of the web page.</p>
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
    This setting determines the application title for your site. Set programmatically by overriding the <code class="wb-prettify">setApplicationTitleText()</code> method in your custom bean class.
</p>
<div class="wb-prettify all-pre lang-c# linenums">
    <pre>
@Override
public void setApplicationTitleText() {
    this.applicationTitleText = "My Application";
}
    </pre>
</div>
<h2>Custom Footer Links</h2>
<p>
    A list of <code class="wb-prettify">customFooterLinks</code> is available for you to provide your own links in the application footer.
    Note that <code class="wb-prettify">showGlobalNav</code> must be false for the custom footer links to appear.
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
//NOTE: showGlobalNav must be false for the footer links to appear.
@Override
public void setCustomFooterLinks() {
    this.customFooterLinks.add(new FooterLink("#", "Footer Link 1", false));
    this.customFooterLinks.add(new FooterLink("#", "Footer Link 2", true));
}
   </pre>
</div>
<h2>Custom Search</h2>
<p>This setting allows you to override the default search behaviour.</p>
<p>Before setting <code class="wb-prettify">customSearch</code> You need to contact the CDTS team to enable this option, as it needs to be added to the CDTS Template.</p>
<div class="wb-prettify all-pre lang-c# linenums">
    <pre>
@Override
public void setCustomSearch() {
    this.customSearch = "SDS";
}
    </pre>
</div>
<h2>Global Navigation</h2>
<p>This setting determines if the global navigation menu for Canada.ca is displayed in the footer.</p>
<p>This is set programmatically by the <code class="wb-prettify">showGlobalNav</code> property of the application template and can also be set in the cdn.properties </p>
<div class="alert alert-warning">
    <p>Setting this to true will override the default footer, custom footer links, and transactional footer</p>
</div>
<div class="wb-prettify all-pre lang-c# linenums">
    <pre>
 @Override
 public void setShowGlobalNav() {
     this.showGlobalNav = false;
 }
    </pre>
</div>
<h2>Site Menu</h2>
<p>The site menu can be hidden or overridden using the following settings.</p>
<ul>
    <li><code class="wb-prettify">setShowSiteMenu</code> is used to control the display of the site menu . Can also be set in cdn.properties</li>
    <li>
        <code class="wb-prettify">setCustomSiteMenuUrl</code> is used to specify a custom menu to be displayed instead of the standard one.   
        <p>Although not required, it is suggested that the CDTS team be contacted before enabling this option.</p>

        <p>You can copy the format in the sample custom menu file provided below in our sample.</p>
        <a href="https://ssl-templates.services.gc.ca/app/cls/wet/gcweb/v4_0_24/cdts/custommenu-en.html">https://ssl-templates.services.gc.ca/app/cls/wet/gcweb/v4_0_24/cdts/custommenu-en.html</a>
    </li>
</ul>
<div class="wb-prettify all-pre lang-c# linenums">
    <pre>
@Override
public void setShowSiteMenu() {
    this.showSiteMenu = true;
}

@Override
public void setCustomSiteMenuUrl() {
    this.customSiteMenuUrl = this.getRequest().getContextPath() + "/samplecontents/mycustommenu.html";
}
    </pre>
</div>
<h2>Secure Icon</h2>
<p>
    Override the <code class="wb-prettify">setShowSecureIcon</code> method in your custom bean class to show or hide the secure icon.
</p>
<div class="wb-prettify all-pre lang-c# linenums">
    <pre>
@Override
public void setShowSecureIcon() {
    this.showSecureIcon = true;
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
public void setShowSignInLink() {
    this.showSignInLink = true;
}
@Override
public void setSignInLinkUrl() {
    this.signInLinkUrl = "#";  
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
public void setShowSignOutLink() {
    this.showSignOutLink = true;
}
@Override
public void setSignOutLinkUrl() {
    this.signOutLinkUrl = "#";  
}
   </pre>
</div>
<h2>Show/Hide Search</h2>
<p>This setting allows you to hide or show the search bar on the page.</p>
<p>The setting <code class="wb-prettify">showSearch</code> can be set programmatically or in the cdn.properties.</p>
<h2>Features</h2>
<p>This will be used by the Principal Publisher to insert GoC activities and initiatives into your page. By default this is ALWAYS shown on all pages. You will need authorization from the Principal Publisher to not include this content in your web asset.</p>
<p>If you receive such authorization then you can programmatically turn this off by overriding the <code class="wb-prettify">setShowFeature</code> method in your custom bean class  or in the cdn.properties</p>
<h2>Pre-Content</h2>
<p>This will be used by Principal Publisher to insert content into the pre content space of your page. By default this is ALWAYS shown on all pages. You will need authorization from the Principal Publisher to not include this content in your web asset.</p>
<p>If you recieve such authorization then you can turn off the pre-content programmatically by overriding the <code class="wb-prettify">setShowPreContent</code> method in your custom bean class or in the cdn.properties.</p>

<div>
    <h3>Other Web Template Samples</h3>
    <ul>
    	<li><a href="splashpagesample.action">Splash Page</a></li>
        <li><a href="addjsandcssfilessample.action">Adding CSS or JS</a></li>
        <li><a href="basesettingssample.action">Basic Settings</a></li>
        <li><a href="breadcrumbsample.action">Breadcrumbs</a></li>
        <li><a href="errorsample.action">Errors</a></li>
        <li><a href="extendedbasepagesample.action">Extended Base Page</a></li>
        <li><a href="feedbackandsharethispagesample.action">Feedback and Share This Page Links</a></li>
        <li><a href="footerlinkssample.action">Footer Links</a></li>
        <li><a href="leavingsecureSitesample.action">Leaving Secure Site Warning</a></li>
        <li><a href="leftsidemenusample.action">Left Side Menu</a></li>
        <li><a href="nestedmasterpagesample.action">Nested Master Page</a></li>
        <li><a href="sessiontimeoutsample.action">Session Timeout</a></li>
        <li><a href="transactionalsample.action">Transactional Page</a></li>
        <li><a href="applicationsample.action">Application Page</a></li>
        <li><a href="gcintranetsample.action">GCIntranet Theme Page</a></li>
    </ul>
</div> 