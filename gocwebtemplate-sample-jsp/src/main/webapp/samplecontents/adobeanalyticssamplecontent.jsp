<h1>GoC Web Template Samples - Adobe Analytics</h1>
<p>This sample page demonstrates how your application can incorporate Adobe Analytics via the GoC Web Template.</p>
<h2>Pre-requisite</h2>
<p>To override the Default GoC Web Template look &amp; feel, you will have to create a custom bean class that extends the <code class="wb-prettify">goc.webtemplate.component.jsp.BaseCoreBean</code> class, and then override the various methods made available to alter the look &amp; feel of the web page.</p>
<p>For this particular sample page, we are using the <code class="wb-prettify">goc.webtemplate.jsp.samplebeans.AdobeAnalyticsSampleBean</code> bean class.</p>
<p>The bean must be included and initialized in a jsp page as part of the <strong>beaninit</strong> attribute that is defined by the master template tiles definition outline in the tiles.xml configuration file:</p>
<p>The custom bean name must be <strong>goctemplateclientbean</strong> and the <strong>request</strong> param must be also be present as it is.</p>
<div class="wb-prettify all-pre lang-vb linenums">
    <pre>
&lt;s:bean name="goc.webtemplate.jsp.samplebeans.AdobeAnalyticsSampleBean" var="goctemplateclientbean"&gt;
    &lt;s:param name="request" value="#request.servletrequest" /&gt;
&lt;/s:bean&gt;
    </pre>
</div>
<h2>Adobe Analytics</h2>
<p>Set programmatically via <code class="wb-prettify">"WebAnalyticsInfo"</code> which has four properties:</p>
<ul>
    <li><code class="wb-prettify">"active"</code>: set to true to enable analytics.</li>
    <li><code class="wb-prettify">"environment"</code>: the environment in which analytics will run (staging or production).</li>
    <li><code class="wb-prettify">"version"</code>: a choice between 1 or 2.</li>
    <li><code class="wb-prettify">"custom"</code>: if the environment/version options do not satisfy, you can provide a custom string.</li>
</ul>   
<h3>Using Adobe Analytics <em>version</em> 1 on staging <em>environment</em>.</h3>
<div class="wb-prettify all-pre lang-vb linenums">
    <pre>
@Override
public void onWebTemplateInitialize() {
//...
    this.setWebAnalytics(new WebAnalyticsInfo(true, WebAnalyticsInfo.EnvironmentOption.STAGING, 1));
//...
}      
    </pre>
</div>  
<h3>Using Adobe Analytics <em>version</em> 2 on production <em>environment</em>.</h3>
<div class="wb-prettify all-pre lang-vb linenums">
    <pre>
@Override
public void onWebTemplateInitialize() {
//...
    this.setWebAnalytics(new WebAnalyticsInfo(true, WebAnalyticsInfo.EnvironmentOption.PRODUCTION, 2));
//...
}
    </pre>
</div>  
<h3>Using Adobe Analytics <em>version</em> 3 on staging <em>environment</em>.</h3>
<div class="wb-prettify all-pre lang-vb linenums">
    <pre>
@Override
public void onWebTemplateInitialize() {
//...
    this.setWebAnalytics(new WebAnalyticsInfo(true, "launch-EN11c0261481f74c56b7656937bbd995e9-staging.min.js"));
//...
}
    </pre>
</div>  
<h3>Using Adobe Analytics <em>version</em> 3 on production <em>environment</em>.</h3>
<div class="wb-prettify all-pre lang-vb linenums">
    <pre>
@Override
public void onWebTemplateInitialize() {
//...
    this.setWebAnalytics(new WebAnalyticsInfo(true, "launch-EN0cf6c2810a2b48f8a4c36502a1b09541.min.js"));
//...
}
    </pre>
</div>
<%@ include file="_sampleslist.jsp" %>