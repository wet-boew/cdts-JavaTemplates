<h1>GoC Web Template Samples - Breadcrumbs</h1>
<p>The breadcrumbs are a list of links displayed near to the top of the content to help the user determine where they are on the site and to help them navigate.</p>
<h2>Pre-requisite</h2>
<p>To override the Default GoC Web Template look &amp; feel, you will have to create a custom bean class that extends the <code class="wb-prettify">goc.webtemplate.component.jsp.BaseCoreBean</code> class, and then override the various methods made available to alter the look &amp; feel of the web page.</p>
<p>For this particular sample page, we are using the <code class="wb-prettify">goc.webtemplate.jsp.samplebeans.BreadcrumbsSampleBean</code> bean class.</p>
<p>The bean must be included and initialized in a jsp page as part of the <strong>beaninit</strong> attribute that is defined by the master template tiles definition outline in the tiles.xml configuration file:</p>
<p>The custom bean name must be <strong>goctemplateclientbean</strong> and the <strong>request</strong> param must be also be present as it is.</p>
<div class="wb-prettify all-pre lang-vb linenums">
	<pre>
&lt;s:bean name="goc.webtemplate.jsp.samplebeans.BreadcrumbsSampleBean" var="goctemplateclientbean"&gt;
	&lt;s:param name="request" value="#request.servletrequest" /&gt;
&lt;/s:bean&gt;
	</pre>
</div>
<h2>Bread crumb</h2>
<p>They are set programmatically by populating the <code class="wb-prettify">"breadCrumbsList"</code> collection of the Web Template Master Page, the collection is of type <code class="wb-prettify">goc.webtemplate.Breadcrumb</code>.</p>
<p>The collection can be populated by calling the <code class="wb-prettify">setBreadcrumbsList</code> method in your custom bean class.</p>
<p>The <code class="wb-prettify">"Breadcrumb"</code> class has 3 properties</p>
<ul>
    <li><code class="wb-prettify">"href"</code>: the url of the link. If left empty, the item will be displayed in text and not as a hyperlink.</li>
    <li><code class="wb-prettify">"title"</code>: the text of the link that is displayed</li>
    <li><code class="wb-prettify">"acronym"</code>: if your title has an acronym, you can use this property to provide the full text of the title.  it will be displayed when the user hovers over the link.</li>
</ul>       
<div class="wb-prettify all-pre lang-vb linenums">
    <pre>
@Override
public void onWebTemplateInitialize() {
//...
    ArrayList&lt;Breadcrumb&gt;   bcs = new ArrayList&lt;Breadcrumb&gt;();
    
    bcs.add(new Breadcrumb("http://www.canada.ca/en/index.html", "Home", ""));
    bcs.add(new Breadcrumb("http://www.esdc.gc.ca/en/jobs/opportunities/index.page", "Jobs", ""));
    bcs.add(new Breadcrumb("http://www.esdc.gc.ca/en/jobs/opportunities/youth_students.page", "Opportunities", ""));
    bcs.add(new Breadcrumb("", "FSWEP", "Federal Student Work Experience Program"));
    
    this.setBreadcrumbs(bcs);
//...    
}
   	</pre>
</div>
<%@ include file="_sampleslist.jsp" %>
