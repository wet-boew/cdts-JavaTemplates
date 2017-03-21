<h1>GoC Web Template Samples - Breadcrumbs</h1>
<p>The breadcrumbs are a list of links displayed near to the top of the content to help the user determine where they are on the site and to help them navigate.</p>
<h2>Pre-requisite</h2>
<p>To override the Default GoC Web Template look &amp; feel, you will have to create a custom bean class that extends the <code class="wb-prettify">goc.webtemplate.component.jsp.BaseBean</code> class, and then override the various methods made available to alter the look &amp; feel of the web page.</p>
<p>For this particular sample page, we are using the <code class="wb-prettify">goc.webtemplate.jsp.samplebeans.BreadcrumbsSampleBean</code> bean class.</p>
<p>The bean must be included and initialized in a jsp page as part of the <strong>beaninit</strong> attribute that is defined by the master template tiles definition outline in the tiles.xml configuration file:</p>
<p>The custom bean name must be <strong>clientbean</strong> and the <strong>request</strong> param must be also be present as it is.</p>
<div class="wb-prettify all-pre lang-vb linenums">
	<pre>
&lt;s:bean name="goc.webtemplate.jsp.samplebeans.BreadcrumbsSampleBean" var="clientbean"&gt;
	&lt;s:param name="request" value="#request.servletrequest" /&gt;
&lt;/s:bean&gt;
	</pre>
</div>
<h2>Bread crumb</h2>
<p>They are set programmatically by populating the <code class="wb-prettify">"breadCrumbsList"</code> collection of the Web Template Master Page, the collection is of type <code class="wb-prettify">goc.webtemplate.Breadcrumb</code>.</p>
<p>The collection can be populated by overriding the <code class="wb-prettify">setBreadcrumbsList</code> method in your custom bean class.</p>
<p>The <code class="wb-prettify">"Breadcrumb"</code> class has 3 properties</p>
<ul>
    <li><code class="wb-prettify">"href"</code>: the url of the link. If left empty, the item will be displayed in text and not as a hyperlink.</li>
    <li><code class="wb-prettify">"title"</code>: the text of the link that is displayed</li>
    <li><code class="wb-prettify">"acronym"</code>: if your title has an acronym, you can use this property to provide the full text of the title.  it will be displayed when the user hovers over the link.</li>
</ul>       
<div class="wb-prettify all-pre lang-vb linenums">
    <pre>
 @Override
public void setBreadcrumbsList() {
	this.breadCrumbsList.add(new Breadcrumb("http://www.canada.ca/en/index.html", "Home", ""));
	this.breadCrumbsList.add(new Breadcrumb("http://www.esdc.gc.ca/en/jobs/opportunities/index.page", "Jobs", ""));
	this.breadCrumbsList.add(new Breadcrumb("http://www.esdc.gc.ca/en/jobs/opportunities/youth_students.page", "Opportunities", ""));
	this.breadCrumbsList.add(new Breadcrumb("", "FSWEP", "Federal Student Work Experience Program"));
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