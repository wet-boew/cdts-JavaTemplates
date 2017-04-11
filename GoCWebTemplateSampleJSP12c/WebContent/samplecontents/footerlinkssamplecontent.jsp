<h1>GoC Web Template Samples - Footer Links</h1>
<p>The footer links consist of the Contact, News and About links located at the bottom of the page.  The theme you specify for your site determines which category and links are displayed.  The theme also determines which of the categories can be modified by the application.  Example in the "GCWeb" theme, only the Contact links can be customized.</p>
<h2>Pre-requisite</h2>
<p>To override the Default GoC Web Template look &amp; feel, you will have to create a custom bean class that extends the <code class="wb-prettify">goc.webtemplate.component.jsp.BaseBean</code> class, and then override the various methods made available to alter the look &amp; feel of the web page.</p>
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
<p>All categories are based on the same logic which is a collection of <code class="wb-prettify">"goc.webtemplate.Link"</code> and are set programmatically by populating the <code class="wb-prettify">"contactLinks, newsLinks and/or aboutLinks"</code> collections of the Web Template Master Page.</p>
<p>The collections can be populated via overriding the following corresponding method in your custom bean class:</p>
<ul>
	<li><code class="wb-prettify">setContactLinks</code></li>
	<li><code class="wb-prettify">setAboutLinks</code></li>
	<li><code class="wb-prettify">setNewsLinks</code></li>
</ul>
<p>The <code class="wb-prettify">"Link"</code> class has 2 properties</p>
<ul>
    <li><code class="wb-prettify">"href"</code>: the url of the link.</li>
    <li><code class="wb-prettify">"text"</code>: the text of the link that is displayed</li>
</ul>
<div class="wb-prettify all-pre lang-vb linenums">
    <pre>
@Override
public void setAboutLinks() { 
	// About Links
	this.aboutLinks.add(new Link("https://www.facebook.com", "Facebook"));
	this.aboutLinks.add(new Link("http://www.lapresse.ca/", "LaPresse"));
}

@Override
public void setContactLinks() { 
	// Contact Links
	this.contactLinks.add(new Link("http://travel.gc.ca/", "Travel"));
	this.contactLinks.add(new Link("http://healthycanadians.gc.ca/index-eng.php", "Health"));
	this.contactLinks.add(new Link("http://jobs-emplois.gc.ca/index-eng.htm", "Jobs"));
}

@Override
public void setNewsLinks() { 
	// News Links
	this.newsLinks.add(new Link("http://www.cbc.ca/news/canada", "CBC"));
	this.newsLinks.add(new Link("http://www.cnn.com/", "CNN"));
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