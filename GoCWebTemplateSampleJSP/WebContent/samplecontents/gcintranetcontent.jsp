<h1>GoC Web Template Samples - GCIntranet Theme</h1>
<p>This sample page provides the items to configure when required to use the GCIntranet theme.</p>
<h2>Pre-requisite</h2>
<p>To override the Default GoC Web Template look &amp; feel, you will have to create a custom bean class that extends the <code class="wb-prettify">goc.webtemplate.component.jsp.BaseCoreBean</code> class, and then override the various methods made available to alter the look &amp; feel of the web page.</p>
<p>For this particular sample page, we are using the <code class="wb-prettify">goc.webtemplate.jsp.samplebeans.IntranetSampleBean</code> bean class, and the bean name is <code class="wb-prettify">"gcintranetsamplebean"</code>.</p>
<p>The bean must be included and initialized in a jsp page as part of the <strong>beaninit</strong> attribute that is defined by the master template tiles definition outline in the tiles.xml configuration file:</p>
<p>The custom bean name must be <strong>goctemplateclientbean</strong> and the <strong>request</strong> param must be also be present as it is.</p>
<div class="wb-prettify all-pre lang-vb linenums">
	<pre>
&lt;s:bean name="goc.webtemplate.jsp.samplebeans.IntranetSampleBean" var="goctemplateclientbean"&gt;
	&lt;s:param name="request" value="#request.servletrequest" /&gt;
&lt;/s:bean&gt;
    </pre>
</div>
<h2>CDNEnvironment Value</h2>
<p>The "GCIntranet" theme is not available on the "Akamai" CDTS and therefore you must set the CDN Environment to something other than "Akamai".</p>
<p>Set the cdn.properties key <code class="wb-prettify">"cdn_environment"</code> to the environment of choice (other than Akamai) example "ESDC_Prod". It can also be set programmatically via the <code class="wb-prettify">"setCDNEnvironment"</code> method in your custom bean class.</p>
<div class="wb-prettify all-pre lang-vb linenums">
    <pre>
@Override
public void onWebTemplateInitialize() {
//...
    this.setCDNEnvironment("ESDC_Prod");
//...    
}
    </pre>
</div>
<h2>Intranet Title</h2>
<p>When using the "GCIntranet" theme in your application, you can optionally set the Intranet Title text and its url. The intranet title is displayed at the top of the page, above the menu.</p>
<p>Set programmatically via the <code class="wb-prettify">"setIntranetTitle"</code> method in your custom bean class.</p>
<p>The URL for the Intranet Title is optional.  If left blank the subTheme will determine the URL to use.  You can override the default URL value of the subTheme by setting programmatically via 
the <code class="wb-prettify">"setIntranetTitle"</code> in your custom bean class.</p>
<div class="wb-prettify all-pre lang-vb linenums">
  	<pre>
@Override
public void onWebTemplateInitialize() {
//...
    this.setIntranetTitle(new Link("http://www.google.ca", "GCIntranet Site"));
//...    
}
	</pre>
</div>
<%@ include file="_sampleslist.jsp" %>
