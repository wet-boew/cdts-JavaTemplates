<h1>GoC Web Template Samples - Adding CSS or JS</h1>
<p>This sample page demonstrates how your application can add your own CSS files or JS files via the GoC Web Template.</p>
<p>The GoC Web Template provides 2 string collections to include HTML Elements to the head section and/or body section of the master page site. The string collections expects the complete html tag to be supplied by the application.</p>
<p>For this sample we have a css "mystyle.css" that is added to add some distinct colors to label tags.  We also have add a javascript file "myJS.js" that has a function that will be called at the click of the button.</p>
<p><h6>NOTE:</h6>  Before adding your own CSS, please make sure that the style doesn't already exist in your theme and/or will not interfere with the styles of the theme.  We also recommend talking with the Portfolio Web or the Principal Publisher since they may consider adding your style to the theme for others to use.</p>
<div>
   	<label class="mySpecialLabel">This is my own style for labels</label>
</div>
<div>
   	<button type="button" onclick="javascript:myAlertFunction()">Click me to call my JS function.</button>    
</div>
<h2>Pre-requisite</h2>
<p>To override the Default GoC Web Template look &amp; feel, you will have to create a custom bean class that extends the <code class="wb-prettify">goc.webtemplate.component.jsp.BaseBean</code> class, and then override the various methods made available to alter the look &amp; feel of the web page.</p>
<p>For this particular sample page, we are using the <code class="wb-prettify">goc.webtemplate.jsp.samplebeans.AddJsAndCssSampleBean</code> bean class.</p>
<p>The bean must be included and initialized in a jsp page as part of the <strong>beaninit</strong> attribute that is defined by the master template tiles definition outline in the tiles.xml configuration file:</p>
<p>The custom bean name must be <strong>clientbean</strong> and the <strong>request</strong> param must be also be present as it is.</p>
<div class="wb-prettify all-pre lang-vb linenums">
	<pre>
&lt;s:bean name="goc.webtemplate.jsp.samplebeans.AddJsAndCssSampleBean" var="clientbean"&gt;
	&lt;s:param name="request" value="#request.servletrequest" /&gt;
&lt;/s:bean&gt;
	</pre>
</div>
<h2>Header Tags</h2>
<p>Set programmatically by populating the <code class="wb-prettify">"htmlHeaderElements"</code> collection of the Web Template Master Page.</p>
<p>The collection is of type <code class="wb-prettify">"String"</code>, and the complete html tag must be supplied.  The collection can be populated by overriding the <code class="wb-prettify">setHtmlHeaderElements</code> method in your custom bean class.</p>
<p>The header tag should be used for MetaTags, CSS and possible for JS files.</p>
<div class="wb-prettify all-pre lang-vb linenums">
	<pre>
@Override
public void setHtmlHeaderElements() {
	// Add a CSS to the header
	this.htmlHeaderElements.add("&lt;link rel='stylesheet' type='text/css' href='samplecontents/mystyle.css'&gt;");
}
	</pre>
</div>
<h2>Body Tags</h2>
<p>Set programmatically by populating the <code class="wb-prettify">"htmlBodyElements"</code> collection of the Web Template Master Page.</p>
<p>The collection is of type <code class="wb-prettify">"String"</code>, and the complete html tag must be supplied.  The collection can be populated by overriding the <code class="wb-prettify">setHtmlBodyElements</code> method in your custom bean class.</p>
<p>The body tag should be used for JS files.</p>
<div class="wb-prettify all-pre lang-vb linenums">
	<pre>
@Override
public void setHtmlBodyElements() {
	// Add a JS to the body (bottom of page)
	this.htmlBodyElements.add("&lt;script src='samplecontents/myJS.js'&gt;&lt;/script&gt;");
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
    </ul>
</div>