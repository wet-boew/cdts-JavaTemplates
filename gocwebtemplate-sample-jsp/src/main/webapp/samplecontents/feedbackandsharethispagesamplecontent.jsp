<h1 id="wb-cont">GoC Web Template Samples - Feedback Link and Share This Page Link</h1>
<p>This page will demonstrate how to enable/disable the Feedback Link, as well as how to use the feature to share the current page with some pre-defined Social Media applications.</p>
<h2>Pre-requisite</h2>
<p>To override the Default GoC Web Template look &amp; feel, you will have to create a custom bean class that extends the <code class="wb-prettify">goc.webtemplate.component.jsp.BaseCoreBean</code> class, and then override the various methods made available to alter the look &amp; feel of the web page.</p>
<p>For this particular sample page, we are using the <code class="wb-prettify">goc.webtemplate.jsp.samplebeans.FeedbackAndShareThisPageSampleBean</code> bean class.</p>
<p>The bean must be included and initialized in a jsp page as part of the <strong>beaninit</strong> attribute that is defined by the master template tiles definition outline in the tiles.xml configuration file:</p>
<p>The custom bean name must be <strong>goctemplateclientbean</strong> and the <strong>request</strong> param must be also be present as it is.</p>
<div class="wb-prettify all-pre lang-vb linenums">
	<pre>
&lt;s:bean name="goc.webtemplate.jsp.samplebeans.FeedbackAndShareThisPageSampleBean" var="goctemplateclientbean"&gt;
	&lt;s:param name="request" value="#request.servletrequest" /&gt;
&lt;/s:bean&gt;
	</pre>
</div>
<h2>Feedback Link</h2>
<p>The Feedback Link is a way for the users of your site to provide feedback on their experience navigating through the site.  Displaying the link is optional and Portfolio Web or the Principal Publisher should be contacted to help you determine if it should be displayed on your site.</p>
<p>To display the Feedback link</p>
<ul>
    <li>Set the key <code class="wb-prettify">"goc.webtemplate.showfeedbacklink"</code> in the cdn.properties file to <strong>"true"</strong>.</li>
    <li>or set the property programmatically by creating a FeedbackLink object and enabling it via the <code class="wb-prettify">setEnabled</code> method in your custom bean class.</li>
    <li>Your page MUST have the following metadata defined for the feedback tool to be enabled: <code>&lt;meta name="dcterms.creator" content="[Department name / Nom du département]"&gt;</code></li>
    <li>The values for Theme, Section and Department Name should be the SAME for English and French.</li>
    <li>If you wish to add contact information, set the key <code class="wb-prettify">"goc.webtemplate.feedbackurl"</code> and optionally <code class="wb-prettify">"goc.webtemplate.feedbackurl_fr"</code> in the cdn.properties file which will be the url for the contact link.</li>
    <li>or set the property programmatically via the <code class="wb-prettify">setUrl</code> method and optionally the <code class="wb-prettify">setUrlFr</code> method in your custom bean class.</li>
    <li>If you wish to add contact information, set the key <code class="wb-prettify">"goc.webtemplate.feedbacktext"</code> and optionally <code class="wb-prettify">"goc.webtemplate.feedbacktext"</code> in the cdn.properties file which will be the name of the contact.</li>
    <li>or set the property programmatically via the <code class="wb-prettify">setText</code> method and optionally the <code class="wb-prettify">setTextFr</code> method in your custom bean class.</li>
    <li>If no french URL is specified, the english one wil be used for both languages.</li>
    <li>If you wish to add the theme, set the key <code class="wb-prettify">"goc.webtemplate.feedbacktheme"</code> in the cdn.properties file.</li>
    <li>or set the property programmatically via the <code class="wb-prettify">setTheme</code> method in your custom bean class.</li>
    <li>If you wish to add the section, set the key <code class="wb-prettify">"goc.webtemplate.feedbacksection"</code> in the cdn.properties file.</li>
    <li>or set the property programmatically via the <code class="wb-prettify">setSection</code> method in your custom bean class.</li>
</ul>
<div class="wb-prettify all-pre lang-vb linenums">
	<pre>
@Override
public void onWebTemplateInitialize() {
//...
    List&lt;String> elements = new ArrayList&lt;String>();
    elements.add("&lt;meta content=\"width = device - width, initial - scale = 1\" name=\"viewport\">");
    elements.add("&lt;meta name=\"dcterms.creator\" content=\"[Department name / Nom du département]\">");
    this.setHtmlHeaderElements(elements);

    FeedbackLink feedbackLink = new FeedbackLink();
    feedbackLink.setEnabled(false);
    feedbackLink.setText("Contact");
    feedbackLink.setTextFr("Contactez-nous");
    feedbackLink.setUrl("http://www.google.ca"); 
    feedbackLink.setUrlFr("http://www.google.ca/?hl=fr");
    feedbackLink.setTheme("Theme");
    feedbackLink.setSection("Section");
    this.setFeedbackLink(feedbackLink);
//...
} 
   	</pre>
</div>
<p>The text of the link is pre-defined by the theme and cannot be modified.</p>
<h2>Share This Page Link</h2>
<p>The "Share This Page" Link is a way for the users to share the URL of the site via Social Media .  Displaying the link is optional and Portfolio Web or the Principal Publisher should be contacted to help you determine if it should be displayed on your site.</p>
<p>To display the Share This Page link</p>
<ul>
    <li>Set the key <code class="wb-prettify">"goc.webtemplate.showsharepagelink"</code> in the cdn.properties file to <strong>"true"</strong>.</li>
    <li>or set the property programmatically via the <code class="wb-prettify">setShowSharePageLink</code> method in your custom bean class.</li>
</ul>
<div class="wb-prettify all-pre lang-vb linenums">
	<pre>
@Override
public void onWebTemplateInitialize() {
//...
    this.setShowSharePageLink(true);
//...
} 
   	</pre>
</div>
<p>If you decide to show the link, you must provide the list of Social Media sites to be displayed to the user by programatically populating the <code class="wb-prettify">"sharePageMediaSites"</code> collection of the Web Template Master Page</p>
<p>The collection is of type <code class="wb-prettify">goc.webtemplate.Constants.SocialMediaSites</code> enum, if the collection is left empty, the link will not be displayed.</p>
<div class="wb-prettify all-pre lang-vb linenums">
    <pre>
@Override
public void onWebTemplateInitialize() {
//...
    this.getSharePageMediaSites().add(Constants.SocialMediaSites.bitly);
    this.getSharePageMediaSites().add(Constants.SocialMediaSites.facebook);
    this.getSharePageMediaSites().add(Constants.SocialMediaSites.twitter);
//...
} 
    </pre>
</div>
<%@ include file="_sampleslist.jsp" %>
