<h1>GoC Web Template Samples - Basic Settings</h1>
<p>This sample page provides the basic items to configure in your pages, when using the GoC Web Template.</p>
<h2>Pre-requisite</h2>
<p>To override the Default GoC Web Template look &amp; feel, you will have to create a custom bean class that extends the <code class="wb-prettify">goc.webtemplate.component.jsp.BaseBean</code> class, and then override the various methods made available to alter the look &amp; feel of the web page.</p>
<p>For this particular sample page, we are using the <code class="wb-prettify">goc.webtemplate.jsp.samplebeans.BaseSettingsSampleBean</code> bean class.</p>
<p>The bean must be included and initialized in a jsp page as part of the <strong>beaninit</strong> attribute that is defined by the master template tiles definition outline in the tiles.xml configuration file:</p>
<p>The custom bean name must be <strong>goctemplateclientbean</strong> and the <strong>request</strong> param must be also be present as it is.</p>
<div class="wb-prettify all-pre lang-vb linenums">
	<pre>
&lt;s:bean name="goc.webtemplate.jsp.samplebeans.BaseSettingsSampleBean" var="goctemplateclientbean"&gt;
	&lt;s:param name="request" value="#request.servletrequest" /&gt;
&lt;/s:bean&gt;
	</pre>
</div>
<h2>Page Title</h2>
<p>This setting determines the title for your site.</p>
<p>Set programmatically via overriding the <code class="wb-prettify">"setHeaderTitle"</code> method in your custom bean class.</p>
<div class="wb-prettify all-pre lang-vb linenums">
   	<pre>
@Override
public void setHeaderTitle() {
	this.headerTitle = "My Title";
}
	</pre>
</div>
<h2>Metatags</h2>
<p>A string collection is available to be included as part of the HTML Elements, including Meta Tag, to the head section of the master page site.</p>
<p>Set programmatically by populating the <code class="wb-prettify">"htmlHeaderElements"</code> collection of the Web Template Master Page.</p>
<p>The collection is of type <code class="wb-prettify">"String"</code>, and the complete html tag for your MetaTag to include should be supplied.  The collection can be populated by overriding the <code class="wb-prettify">setHtmlHeaderElements</code> method in your custom bean class.</p>
<p>Note: this collections can also be used to add script tags for javascript and/or link tag to include CSSs.</p>
<div class="wb-prettify all-pre lang-vb linenums">
   	<pre>
@Override
public void setHtmlHeaderElements() {
	this.htmlHeaderElements.add("&lt;meta charset='UTF-8' /&gt;");
	this.htmlHeaderElements.add("&lt;meta name='singer' content='Elvis' /&gt;");
	this.htmlHeaderElements.add("&lt;meta http-equiv='default-style' content='sample' /&gt;");
}
	</pre>
</div>
<h2>Date Modified</h2>
<p>The date modified is displayed near the bottom of the page.</p>
<p>Set programmatically by populating the <code class="wb-prettify">"dateModified"</code> value of the Web Template Master Page.  This value can be populated by overriding the <code class="wb-prettify">setDateModified</code> method in your custom bean class.</p>
<div class="wb-prettify all-pre lang-vb linenums">
   	<pre>
@Override
public void setDateModified() {
	this.dateModified = new java.util.Date();
}
	</pre>
</div>
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