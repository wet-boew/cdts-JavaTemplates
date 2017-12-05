<h1>GoC Web Template Samples - Footer Links</h1>
<p>The footer links consist of the Contact, News and About links located at the bottom of the page.  The theme you specify for your site determines which category and links are displayed.  The theme also determines which of the categories can be modified by the application.  Example in the "GCWeb" theme, only the Contact links can be customized.</p>
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
<h2>Setting the Contact Us link</h2>
<p>The "Contact Us" link at the bottom of the page can be customized by populating the <code class="wb-prettify">contactLink</code> variable:</p>
<div class="wb-prettify all-pre lang-vb linenums">
    <pre>
@Override
public void onWebTemplateInitialize() {
//...
    this.setContactLink(new Link("http://travel.gc.ca/"));
//...
}
    </pre>
</div>		
<%@ include file="_sampleslist.jsp" %>
