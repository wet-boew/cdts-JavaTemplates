<h1 id="wb-cont">GoC Web Template Samples - Footer</h1>
<p>Canada.ca provides a global site footer as seen <a href="https://design.canada.ca/common-design-patterns/site-footer.html">here.</a></p>
<p>For more information, please visit the WET footer documentation <a href="https://wet-boew.github.io/GCWeb/sites/footers/footers-en.html">here.</a></p>
<h2>Pre-requisite</h2>
<p>To override the Default GoC Web Template look &amp; feel, you will have to create a custom bean class that extends the <code class="wb-prettify">goc.webtemplate.component.jsp.BaseCoreBean</code> class, and then override the various methods made available to alter the look &amp; feel of the web page.</p>
<p>For this particular sample page, we are using the <code class="wb-prettify">goc.webtemplate.jsp.samplebeans.FooterLinksSampleBean</code> bean class.</p>
<p>The bean must be included and initialized in a jsp page as part of the <strong>beaninit</strong> attribute that is defined by the master template tiles definition outline in the tiles.xml configuration file:</p>
<p>The custom bean name must be <strong>goctemplateclientbean</strong> and the <strong>request</strong> param must be also be present as it is.</p>
<div class="wb-prettify all-pre lang-vb linenums">
	<pre>
&lt;s:bean name="goc.webtemplate.jsp.samplebeans.FooterLinksSampleBean" var="goctemplateclientbean"&gt;
	&lt;s:param name="request" value="#request.servletrequest" /&gt;
&lt;/s:bean&gt;
	</pre>
</div>
<h2>Contextual Footer</h2>
<p>The contextual footer is hidden by default. Making the contextual footer visible is done via the <code class="wb-prettify">contextualFooter</code> variable.</p>
<p><code class="wb-prettify">contextualFooter</code> is composed of a <code class="wb-prettify">title</code> property as well as a list of<code class="wb-prettify">FooterLink</code> objects. Each footer link has three properties:</p>
<ul>
    <li><code class="wb-prettify">href</code>: The URL of the link</li>
    <li><code class="wb-prettify">text</code>: The text of the link to be displayed</li>
    <li><code class="wb-prettify">newWindow</code>: Open the link in a  new window</li>
</ul>
<p>Note: You can have a maximum of 3 links in your contextual footer.</p>
<h2>Main Footer</h2>
<p>The main footer is visible by default. You have the ability to hide the main footer via the <code class="wb-prettify">hideMainFooter</code> variable.</p>
<h2>Sub Footer</h2>
<p>The sub footer is visible by default. You have the option of hiding the first three links via the <code class="wb-prettify">hideFooterCorporate</code> variable.</p>
      <p>You have the option of overwriting the href attribute of the Terms and conditions link and Privacy link via the <code class="wb-prettify">termsLink</code> and <code class="wb-prettify">privacyLink</code> variables respectively, which are <code class="wb-prettify">FooterLink</code> objects.</p>
<h3>Example:</h3>
<div class="wb-prettify all-pre lang-vb linenums">
    <pre>
@Override
public void onWebTemplateInitialize() {
//...
    /*
    //Example of adding a contextual footer with 3 links
    List&lt;FooterLink&gt; links = new ArrayList&lt;FooterLink&gt;();
            
    links.add(new FooterLink("http://www.canada.ca/en/index.html", "Link 1", false));
    links.add(new FooterLink("http://www.canada.ca/en/index.html", "Link 2", false));
    links.add(new FooterLink("http://www.canada.ca/en/index.html", "Link 3", false));
    this.setContextualFooter(new ContextualFooter("Title", links));
    */
        
    //Hiding the main footer
    //this.setHideMainFooter(true);
    
    //Hiding the corporate footer links
    //this.setHideCorporateFooter(true);
        
    //Overwriting the href of the privacy link and the terms and conditions link
    //this.setPrivacyLink(new FooterLink("http://canada.ca/"));
    //this.setTermsConditionsLink(new FooterLink("http://canada.ca/"));
//...
}
    </pre>
</div>
<h1>Footer Links</h1>
<p>The footer links consist of the Contact, News and About links located at the bottom of the page.  The environment you specify for your site determines which category and links are displayed.  The environment also determines which of the categories can be modified by the application.</p>
<h2>Before Changing The Footer</h2>
<p>Will you need a custom header/footer? Keep in mind this is different than removing or adding certain elements that are allowed as per the C&amp;IA specifications document. Any element that can be implemented using the C&amp;IA specifications document does not require a custom header/footer and is available in the default version of the CDTS. Currently it is advisable to not implement a custom header/footer unless you have permission to do so from your department, TBS or Principal Publisher. If you have permission to do so then follow the instructions for the "Application templates" in the menu on the right.</p>
<h2>Setting the Contact Us link(s)</h2>
<p>The "Contact Us" link(s) at the bottom of the page can be customized by populating the <code class="wb-prettify">contactLinks</code> variable:</p>
<p><code class="wb-prettify">contactLinks</code> is a List of <code class="wb-prettify">Link</code> objects. Each link has two properties:</p>
<ul>
    <li><code class="wb-prettify">href</code>: The URL of the link</li>
    <li><code class="wb-prettify">text</code>: The text of the link to be displayed (see note below)</li>
</ul>
<p>A <code class="wb-prettify">FooterLink</code> can be used instead of a Link, allowing an additional property:</p>
<ul>
    <li><code class="wb-prettify">newWindow</code>: Whether the link should open in another window.</li>
</ul>
<h3>Special Considerations</h3>
<ul>
    <li>
        For the application template, <code class="wb-prettify">contactLinks</code> can only be used if the environment is <em>NOT</em> GCWeb (AKAMAI). If a custom link is needed with AKAMAI, <code class="wb-prettify">customFooterLinks</code> can be used instead (see the <a href="applicationsample.action">Application Template</a> sample for details).
        <ul><li>For environments other than GCWeb (AKAMAI), the application template supports a single contact link (multiple not allowed).</li></ul>
    </li> 
    <li>For all other templates, the GCWeb (AKAMAI) environment only supports ONE link (multiple links are acceptable when using other environments).</li>
    <li>The <code class="wb-prettify">text</code> property can only be used in the Intranet (ESDC_Prod) and Extranet (PROD_SSL) environments.</li>
</ul>
<h3>Example:</h3>
<div class="wb-prettify all-pre lang-vb linenums">
    <pre>
@Override
public void onWebTemplateInitialize() {
//...
    //For a single link, the convenience function can be used:
    this.setContactLink(new Link("http://travel.gc.ca/"));

    /*
    //Example of having multiple links and specifying text (only for non-Akamai, non-Application template)
    java.util.ArrayList&lt;Link&gt; contactLinks = new java.util.ArrayList&lt;Link&gt;();
    contactLinks.add(new Link("http://canada.ca/", "Contact Canada!"));
    contactLinks.add(new Link("http://travel.gc.ca/", "Contact Travel!"));
    this.setContactLinks(contactLinks);
    */
//...
}
    </pre>
</div>		
<%@ include file="_sampleslist.jsp" %>
