<%@ taglib uri="/struts-tags" prefix="s" %>
<h1>GoC Web Template Samples - Extended BasePage</h1>
<p>This page provides an example on how to extend the <code class="wb-prettify">goc.webtemplate.component.jsp.BaseBean</code> class provided by the Web Template.  The <code class="wb-prettify">BaseBean</code> provided by the Web Template has very little functions and therefore applications may wish to extend its functionality. This will be useful should you require to add your own common logic that will be used by your applications' pages.</p>
<p>In this sample, we've created a new class <code class="wb-prettify">"ExtendedBasePageSampleBean"</code>, which extend the BaseBean class from the Web Template and it includes the following:</p>
<ul>
    <li>A getter method <code class="wb-prettify">"getWeather"</code>, which returns a string.</li>
    <li>A getter method <code class="wb-prettify">"getSessionId"</code>, which returns the ID of the user's session</li>
    <li>Override the <code class="wb-prettify">"setHeaderTitle"</code> getter method of the WebTemplate.  This would set the page title for all pages of the application.</li>
</ul>
<p>The <code class="wb-prettify">"ExtendedBasePageSampleBean"</code> class is initialized as the <strong>beaninit</strong> attribute in tiles definition that is made available by the web template tiles definition.</p>
<h2>Level of inheritance:</h2>
<ul>
    <li>Your jsp page will use this custom bean class to extend the look and feel of your application</li>
    <li>Your Extended Base Page bean class will inherit from the <code class="wb-prettify">goc.webtemplate.component.jsp.BaseBean</code> of the Web Template</li>
    <li>The <code class="wb-prettify">goc.webtemplate.component.jsp.BaseBean</code> of the Web Template inherits from <code class="wb-prettify">goc.webtemplate.component.BaseComponent</code> class</li>
</ul>
<h2>Steps to Extend the Template BasePage</h2>
<ul>
    <li>Create a new class</li>
    <li>Inherit from <code class="wb-prettify">"goc.webtemplate.component.jsp.BaseBean"</code></li>
    <li>Include the logic required for your application</li>
</ul>
<div class="wb-prettify all-pre lang-vb linenums">
   	<pre>
import goc.webtemplate.component.jsp.BaseBean;

public class ExtendedBasePageSampleBean extends BaseBean {

	@Override
	public void setHeaderTitle() {
		this.headerTitle = "Title set for everpage!";
	}
	
	...
	
	public String getWeather() { return "Sunny"; }
	
	public String getSessionId() {
		return this.getRequest().getSession().getId();
	}
	
}
   	</pre>
</div>
<h2>Steps when adding your JSP Pages</h2>
<ul>
    <li>include the custom bean class in the <strong>beaninit</strong> section that is made available the web template, <strong>always</strong> under the name <strong>clientbean</strong>.</li>
</ul>
<div class="wb-prettify all-pre lang-vb linenums">
   	<pre>
&lt;s:bean name="goc.webtemplate.jsp.samplebeans.ExtendedBasePageSampleBean" var="goctemplateclientbean"&gt;
	&lt;s:param name="request" value="#request.servletrequest" /&gt;
&lt;/s:bean&gt;
   	</pre>
</div>    	
<h2>Values from the "ExtendedBasePageSampleBean"</h2>
<div>Session ID: <strong><s:property value="#goctemplateclientbean.sessionId" /></strong></div>
<div>Today's Weather: <strong><s:property value="#goctemplateclientbean.weather" /></strong></div>
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