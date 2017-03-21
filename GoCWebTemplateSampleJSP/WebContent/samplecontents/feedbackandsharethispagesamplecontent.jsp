<h1>GoC Web Template Samples - Feedback Link and Share This Page Link</h1>
<p>This page will demonstrate how to enable/disable the Feedback Link, as well as how to use the feature to share the current page with some pre-defined Social Media applications.</p>
<h2>Pre-requisite</h2>
<p>To override the Default GoC Web Template look &amp; feel, you will have to create a custom bean class that extends the <code class="wb-prettify">goc.webtemplate.component.jsp.BaseBean</code> class, and then override the various methods made available to alter the look &amp; feel of the web page.</p>
<p>For this particular sample page, we are using the <code class="wb-prettify">goc.webtemplate.jsp.samplebeans.FeedbackAndShareThisPageSampleBean</code> bean class.</p>
<p>The bean must be included and initialized in a jsp page as part of the <strong>beaninit</strong> attribute that is defined by the master template tiles definition outline in the tiles.xml configuration file:</p>
<p>The custom bean name must be <strong>clientbean</strong> and the <strong>request</strong> param must be also be present as it is.</p>
<div class="wb-prettify all-pre lang-vb linenums">
	<pre>
&lt;s:bean name="goc.webtemplate.jsp.samplebeans.FeedbackAndShareThisPageSampleBean" var="clientbean"&gt;
	&lt;s:param name="request" value="#request.servletrequest" /&gt;
&lt;/s:bean&gt;
	</pre>
</div>
<h2>Feedback Link</h2>
<p>The Feedback Link is a way for the users of your site to provide feedback on their experience navigating through the site.  Displaying the link is optional and Portfolio Web or the Principal Publisher should be contacted to help you determine if it should be displayed on your site.</p>
<p>To display the Feedback link</p>
<ul>
    <li>Set the key <code class="wb-prettify">"goc.webtemplate.showfeedbacklink"</code> in the cdn.properties file to <strong>"true"</strong>.</li>
    <li>or set the property programmatically via overriding the <code class="wb-prettify">setShowFeedbackLink</code> method in your custom bean class.</li>
	<li>If you wish to redirect the user to your own pages instead of the default canada.ca page, set the key <code class="wb-prettify">"goc.webtemplate.feedbackurl"</code> in the cdn.properties file to the url of your page.</li>
    <li>or set the property programmatically via overriding the <code class="wb-prettify">setFeedbackUrl</code> method in your custom bean class.</li>
</ul>
<div class="wb-prettify all-pre lang-vb linenums">
	<pre>
@Override
public void setShowFeedbackLink() { this.showFeedbackLink = true; }

@Override
public void setFeedbackUrl() { this.feedbackUrl = "http://www.google.ca"; }
   	</pre>
</div>
<p>The URL of the link is pre-defined by the theme and cannot be modified.</p>
<h2>Share This Page Link</h2>
<p>The "Share This Page" Link is a way for the users to share the URL of the site via Social Media .  Displaying the link is optional and Portfolio Web or the Principal Publisher should be contacted to help you determine if it should be displayed on your site.</p>
<p>To display the Share This Page link</p>
<ul>
    <li>Set the key <code class="wb-prettify">"goc.webtemplate.showsharepagelink"</code> in the cdn.properties file to <strong>"true"</strong>.</li>
    <li>or set the property programmatically via overriding the <code class="wb-prettify">setShowSharePageLink</code> method in your custom bean class.</li>
</ul>
<div class="wb-prettify all-pre lang-vb linenums">
	<pre>
@Override
public void setShowSharePageLink() { this.showSharePageLink = true; }
   	</pre>
</div>
<p>If you decide to show the link, you must provide the list of Social Media sites to be displayed to the user by programatically populating the <code class="wb-prettify">"sharePageMediaSites"</code> collection of the Web Template Master Page</p>
<p>The collection is of type <code class="wb-prettify">goc.webtemplate.Constants.SocialMediaSites</code> enum, if the collection is left empty, the link will not be displayed.</p>
<div class="wb-prettify all-pre lang-vb linenums">
    <pre>
@Override
public void setSharePageMediaSites() {
	this.sharePageMediaSites.add(Constants.SocialMediaSites.bitly);
       this.sharePageMediaSites.add(Constants.SocialMediaSites.facebook);
       this.sharePageMediaSites.add(Constants.SocialMediaSites.twitter);
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
        <li><a href="gcintranetsample.action">GCIntranet Theme Page</a></li>
    </ul>
</div>