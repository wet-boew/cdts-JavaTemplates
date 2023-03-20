<h1 id="wb-cont">GoC Web Template Samples - Basic Settings</h1>
<p>This sample page provides the basic items to configure in your pages, when using the GoC Web Template.</p>
<h2>Pre-requisite</h2>
<p>To override the Default GoC Web Template look &amp; feel, you will have to create a custom bean class that extends the <code class="wb-prettify">goc.webtemplate.component.jsp.BaseCoreBean</code> class, and then override the various methods made available to alter the look &amp; feel of the web page.</p>
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
<p>Set programmatically via calling the <code class="wb-prettify">"setHeaderTitle"</code> method in your custom bean class.</p>
<div class="wb-prettify all-pre lang-vb linenums">
   	<pre>
@Override
public void onWebTemplateInitialize() {
//...
    this.setHeaderTitle("My Title");
//...    
}
	</pre>
</div>
<h2>Metatags</h2>
<p>A string collection is available to be included as part of the HTML Elements, including Meta Tag, to the head section of the master page site.</p>
<p>Set programmatically by populating the <code class="wb-prettify">"htmlHeaderElements"</code> collection of the Web Template Master Page.</p>
<p>The collection is of type <code class="wb-prettify">"String"</code>, and the complete html tag for your MetaTag to include should be supplied.  The collection can be populated by calling the <code class="wb-prettify">getHtmlHeaderElements</code> method in your custom bean class.</p>
<p>Note: this collections can also be used to add script tags for javascript and/or link tag to include CSSs.</p>
<div class="wb-prettify all-pre lang-vb linenums">
   	<pre>
@Override
public void onWebTemplateInitialize() {
//...
    this.getHtmlHeaderElements().add("&lt;meta charset='UTF-8' /&gt;");
    this.getHtmlHeaderElements().add("&lt;meta name='singer' content='Elvis' /&gt;");
    this.getHtmlHeaderElements().add("&lt;meta http-equiv='default-style' content='sample' /&gt;");
//...
}
	</pre>
</div>
<h2>Date Modified</h2>
<p>The date modified is displayed near the bottom of the page.</p>
<p>Set programmatically by populating the <code class="wb-prettify">"dateModified"</code> value of the Web Template Master Page.  This value can be populated by calling the <code class="wb-prettify">setDateModified</code> method in your custom bean class.</p>
<div class="wb-prettify all-pre lang-vb linenums">
   	<pre>
@Override
public void onWebTemplateInitialize() {
//...
    this.setDateModified(new java.util.Date());
//...
}
	</pre>
</div>
<h2>Change Language Link</h2>
<p>You can set a custom link for changing the language of your site</p>
<p>This can be set programmatically by calling the <code class="wb-prettify">setLanguageLinkUrl</code> method in your custom bean class.</p>
<h2>Version Identifier</h2>
<p>The Version Identifier is displayed near the bottom of the page.</p>
<p>Set programmatically via the <code class="wb-prettify">"VersionIdentifier"</code> property of the Web Template.</p>
<h2>Screen Identifier</h2>
<p>The Screen Identifier is displayed near the bottom of the page. This element serves a unique identifier for your page and can be used to help communication between users and the service desk/support team to identify the location exact location of a user in your application.</p>
<p>Set programmatically via the <code class="wb-prettify">"ScreenIdentifier"</code> property of the Web Template.</p>
<div class="wb-prettify all-pre lang-vb linenums">
    <pre>
@Override
public void onWebTemplateInitialize() {
//...
    this.setScreenIdentifier("PAGE001"); 
//...
}
    </pre>
</div>
<%@ include file="_sampleslist.jsp" %>
