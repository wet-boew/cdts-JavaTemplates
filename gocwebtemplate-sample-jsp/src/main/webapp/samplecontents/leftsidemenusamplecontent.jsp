<h1 id="wb-cont">GoC Web Template Samples - Left Side Menu</h1>
<p>This sample uses the <code class="wb-prettify">"/templates/leftmenu-mastertemplate.jsp"</code> master page to demonstrate how the left menu is displayed and configured.</p>
<h2>Pre-requisite</h2>
<p>To override the Default GoC Web Template look &amp; feel, you will have to create a custom bean class that extends the <code class="wb-prettify">goc.webtemplate.component.jsp.BaseCoreBean</code> class, and then override the various methods made available to alter the look &amp; feel of the web page.</p>
<p>For this particular sample page, we are using the <code class="wb-prettify">goc.webtemplate.jsp.samplebeans.LeftSideMenuSampleBean</code> bean class.</p>
<p>The bean must be included and initialized in a jsp page as part of the <strong>beaninit</strong> attribute that is defined by the master template tiles definition outline in the tiles.xml configuration file:</p>
<p>The custom bean name must be <strong>goctemplateclientbean</strong> and the <strong>request</strong> param must be also be present as it is.</p>
<div class="wb-prettify all-pre lang-vb linenums">
	<pre>
&lt;s:bean name="goc.webtemplate.jsp.samplebeans.LeftSideMenuSampleBean" var="goctemplateclientbean"&gt;
	&lt;s:param name="request" value="#request.servletrequest" /&gt;
&lt;/s:bean&gt;
	</pre>
</div>
<h2>Left Side Menu</h2>
<p>The menu is set programmatically by populating the <code class="wb-prettify">"leftMenuSections"</code> collection of the Web Template Master Page via the <code class="wb-prettify">setLeftMenuSections</code> method in your custom bean class.</p>
<p>The collection expects an object of type <code class="wb-prettify">"goc.webtemplate.MenuSection"</code>, which has the following methods:</p>
<ul>
    <li><code class="wb-prettify">setName</code>: name of the menu section.  This is the header of the menu</li>
    <li><code class="wb-prettify">setLink</code>: underlying url of the menu section.  This is used to set a navigation url to the header of the menu</li>
    <li><code class="wb-prettify">setOpenInNewWindow</code>: default is false, but if set to true the link will open in a new window.</li>
    <li><code class="wb-prettify">getItems</code>: return the list of links or submenu for this section of the side menu of type <code class="wb-prettify">goc.webtemplate.Link</code> or <code class="wb-prettify">goc.webtemplate.MenuItem</code>. This collection has the following methods</li>
    <ul>
        <li><code class="wb-prettify">"setHref"</code>: to set the url of the link</li>
        <li><code class="wb-prettify">"setText"</code>: to set the text of the link that is displayed</li>
        <li><code class="wb-prettify">"setOpenInNewWindow"</code>: default is false, but if set to true the link will open in a new window.</li>
        <li><code class="wb-prettify">"setSubItems"</code>: to set the child menu, this is only applicable to type goc.webtemplate.MenuItem, and the Java template will only render 1 layer deep</li>
    </ul>
</ul>
<p>When <code class="wb-prettify">setOpenInNewWindow</code> is set to true, a span tag will be included for accessibility and to notify the user that the link will open a new window.</p>
	    
<div class="wb-prettify all-pre lang-vb linenums">
    <pre>
@Override
public void onWebTemplateInitialize() {
//...
    ArrayList&lt;MenuSection&gt;  sections = new ArrayList&lt;MenuSection&gt;();
    
    MenuSection leftMenu = new MenuSection();
    leftMenu.setName("Section A");
    leftMenu.setLink("http://www.google.ca");
    leftMenu.setOpenInNewWindow(true);
    leftMenu.getItems().add(new Link("http://www.tsn.ca", "TSN"));
    
    MenuItem item = new MenuItem("", "Sports");
    item.getSubItems().add(new MenuItem("http://www.nhl.com", "NHL", null, true));
    item.getSubItems().add(new MenuItem("http://www.mlb.com", "MLB"));    
    leftMenu.getItems().add(item);
    
    sections.add(leftMenu);
    
    this.setLeftMenuSections(sections);
//...    
}
    </pre>
</div>
<%@ include file="_sampleslist.jsp" %>
