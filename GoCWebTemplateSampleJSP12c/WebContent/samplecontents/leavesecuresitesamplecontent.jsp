<h1>GoC Web Template Samples - Leaving Secure Site</h1>
<p>In certain scenarios (ex: secure sites) we want to notify the user that the link or action they have just performed will exit the current secured site/session and it is possible that data could be lost. The message allows the user to cancel the redirect or continue with the redirect.</p>
<h2>Pre-requisite</h2>
<p>To override the Default GoC Web Template look &amp; feel, you will have to create a custom bean class that extends the <code class="wb-prettify">goc.webtemplate.component.jsp.BaseCoreBean</code> class, and then override the various methods made available to alter the look &amp; feel of the web page.</p>
<p>For this particular sample page, we are using the <code class="wb-prettify">goc.webtemplate.jsp.samplebeans.LeaveSecureSiteSampleBean</code> bean class.</p>
<p>The bean must be included and initialized in a jsp page as part of the <strong>beaninit</strong> attribute that is defined by the master template tiles definition outline in the tiles.xml configuration file:</p>
<p>The custom bean name must be <strong>goctemplateclientbean</strong> and the <strong>request</strong> param must be also be present as it is.</p>
<div class="wb-prettify all-pre lang-vb linenums">
	<pre>
&lt;s:bean name="goc.webtemplate.jsp.samplebeans.LeaveSecureSiteSampleBean" var="goctemplateclientbean"&gt;
	&lt;s:param name="request" value="#request.servletrequest" /&gt;
&lt;/s:bean&gt;
	</pre>
</div>
<h2>Leaving Secure Site Warning</h2>
<p>This feature can be enabled in the Web Template and will:</p>
<ul>
    <li>display the message to the user in the form of a modal window</li>
    <li>display the message your application provides</li>
    <li>allow your application to execute any clean up code (ex: close session, gracefully logout user etc...)</li>
    <li>allow your application to exlude any domains from raising the warning</li>
</ul>
<h2>How it works</h2>
<ul>
    <li>If <code class="wb-prettify">DisplayModalWindow</code> is set to true (default):
       	<ul>
            <li>When the user clicks an external link, the modal window will be displayed to the user.</li>
            <li>A "Cancel" button appears on the window to allow the user to return to their page.</li>
            <li>A "Yes" button appears on the window to allow the user to continue with the redirection to the selected link.</li>
        </ul>
   	</li>
	<li>if the "Yes" button is clicked:
        <ul>
            <li>the user will first be redirect to the url set in <code class="wb-prettify">"leavingSecureSiteRedirectUrl"</code> via either the cdn.properties file or programmatically</li>
            <li>the info of the linked that was clicked is part of the querystring to that url</li>
            <li>in the redirect url provided earlier, attach the preRenderView event to the page and execute a custom bean method to perform the redirect</li>
            <li>execute any clean up code your application requires</li>
            <li>once executed the custom bean class will redirect the user to the url of the clicked link</li>
            <li>the leave secure site feature is already provided by default as part of the GoC Web Template package, by default it will use the templates/leavesecuresiteredirect.xhtml page</li>
            <li>by default the leave secure site redirect page will invoke the <code class="wb-prettify">leavesecuresiteredirect.action</code> Struts Action already pre-registered in struts.xml</li>
        </ul>
    </li>
</ul>
<p>Here is a local link that will not display the warning: <a href="BaseSettingsSample.xhtml">Link to Local Page</a></p>
<p>Here is an external link that will display the warning:<a href="http://www.google.ca">Link to External Page</a></p>
<h2>Steps to implement:</h2>
<h3>Enable the leaving secure site feature</h3>
<ul>
    <li>Set, via the cdn.properties file or programmatically in your custom bean class, <code class="wb-prettify">"Enabled"</code> to <strong>"true"</strong></li>
    <li>Provide the message to be displayed by setting the <code class="wb-prettify">"Message"</code> programmatically via the <code class="wb-prettify">setLeavingSecureSiteWarning</code> method in your custom bean class.</li>
    <li>Set, via the cdn.properties file or programmatically in your custom bean class, <code class="wb-prettify">"RedirectUrl"</code> to your action class which will execute your clean up code and then redirect to the selected url.</li>
    <li>Set, via the cdn.properties or programmatically in your custom bean class, <code class="wb-prettify">"ExcludedDomain"</code> the list of domains you do not want to raise the warning</li>
</ul>
<div class="wb-prettify all-pre lang-vb linenums">
   	<pre>
@Override
public void onWebTemplateInitialize() {
//...
    LeavingSecureSiteWarning lssw = new LeavingSecureSiteWarning();

    lssw.setEnabled(true);
    lssw.setMessage("You are about to leave a secure site, do you wish to continue?");
    lssw.setRedirectUrl("leavesecuresiteredirect.action");
    lssw.setExcludedDomains("www.esdc.gc.ca,www.jobbank.gc.ca,www.readseal.ca");
    lssw.setDisplayModalWindow(true);
    
    this.setLeavingSecureSiteWarning(lssw);
//...
}
   	</pre>
</div>
<h3>Created your custom "redirect" class</h3>
<ul>
    <li>Create a class and a public method will be invoked by the preRenderView event of the redirect url</li>
    <li>enter your clean up code if required</li>
    <li>redirect to the <code class="wb-prettify">"targetURL"</code> parameter value in the querystring</li>
</ul>
<div class="wb-prettify all-pre lang-vb linenums">
    <h3>Code Sample for your Redirect action class</h3>
    <pre>
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

public class LeaveSecureSiteAction {
	public void execute() throws Exception {
		HttpServletRequest currentReq = ServletActionContext.getRequest();
		String redirectUrl = URLDecoder.decode(currentReq.getParameter("targetUrl"), "UTF-8");
		ServletActionContext.getResponse().sendRedirect(redirectUrl);
	}
}
   </pre>
</div>
<%@ include file="_sampleslist.jsp" %>
