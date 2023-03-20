<h1 id="wb-cont">GoC Web Template Samples - Nested Master Page</h1>

<p>This sample demonstrates how a Nested Master Page could be implemented with the GoC JSP Web Template.</p>

<p>Nested master pages can be used to customize the layout of the parent master pages' content section, provide a central location to add controls needed on each page of your site and/or common functionality.</p>

<p>The sample uses a Nested Master page to:</p>
<ul>
    <li>Divide the 1 column GoC Web Template Master Page layout into 2 columns.  Note: This same approach could be used with the SideMenu Master Page of the Web template.</li>
    <li>Display a Local Weather Widget in the right column of all pages.  By using a nested master page, we could include this widget centrally in the nested master page once instead of including it on every web page of the site.</li>
    <li>The nested master page sets the <code class="wb-prettify">"dateModified"</code> value of the GoC Web Template Master Page.</li>
    <li>The web page that will be using the nested master page will set the <code class="wb-prettify">"headerTitle"</code> value of the GoC Web Template Master page.</li>
</ul>
 <h2>Steps to implement</h2>

<ul>
    <li>Download the latest "GoC JSP Web Template" eclipse project from GCPEDIA.</li>
    <li>In tiles.xml, define a new definition for the nested template
    <li>Under the <strong>"WebContent"</strong> directory, create a new .jsp page (for this sample, it would be /samplecontents/nested-mastertemplate-template.jsp)
    	<ul>
    		<li>This page will be used in the tiles definition to override the <strong>body</strong> attribute provided by the GoC JSP Web Template package</li>
    		<li>The nested master page should expose further content place holder for child page(s) that would need the nested master page feature</li>
    	</ul>
    </li>
    <li>Under the <strong>"WebContent"</strong> directory, add another .jsp page as the child content page (for this sample, it would be /samplecontents/nestedmasterpagesamplecontent.jsp)
        <ul>
            <li>This page will use the nested master page layout created earlier in a new definition in tiles.xml</li>
            <li>Modify the page to include the required content using the content place holder made available by the nested master page</li>
        </ul>
        </li>
    <li>If necessary, create custom bean class to alter the default look &amp; feel of the child content page</li>
    <li>The custom bean class should be initialized in the child content page using <strong>beaninit</strong> attribute made available by the GoC Master Template page</li>
</ul>
<div class="wb-prettify all-pre lang-vb linenums">
    <h3>HTML Code Sample of the Nested Master Page</h3>
    <pre>
&lt;%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %&gt;
&lt;div class="row"&gt;
    &lt;!-- left column--&gt;
    &lt;section class="col-md-8"&gt;
        &lt;tiles:insertAttribute name="leftcontent" /&gt;
    &lt;/section&gt;
    &lt;!-- right column--&gt;
    &lt;aside class="col-md-4 mrgn-tp-lg"&gt;
        &lt;!-- include weather widget here so it is displayed on all pages--&gt;
        &lt;div class="well"&gt;
            &lt;section class="wb-feeds limit-1"&gt;
             &lt;h3&gt;Ottawa Weather&lt;/h3&gt;
             &lt;ul class="feeds-cont"&gt;
              &lt;li&gt;
               &lt;a href="http://weather.gc.ca/rss/city/on-118_e.xml" rel="external"&gt;Ottawa Ontario&lt;/a&gt;
              &lt;/li&gt;
             &lt;/ul&gt;
            &lt;/section&gt;
            &lt;!-- include a content place holder should the page require to add content to the right column below the weather widget--&gt;
            &lt;tiles:insertAttribute name="rightcontent" /&gt;
        &lt;/div&gt;
    &lt;/aside&gt;
&lt;/div&gt;
    </pre>
</div>
<div class="wb-prettify all-pre lang-vb linenums">
    <h3>Code Sample of the Nested Bean Class</h3>
    <pre>
import goc.webtemplate.component.jsp.DefaultTemplateCoreBean;

public class NestedMasterPageTemplateBean extends DefaultTemplateCoreBean {

    @Override
    public void onWebTemplateInitialize() {
//...    
        this.setDateModified(new java.util.Date());
//...        
    }
}
    </pre>
</div>
<div class="wb-prettify all-pre lang-vb linenums">
    <h3>Tiles Definition of the Child Content Page</h3>
    <pre>
&lt;definition name="nestedMasterPageSample" extends="nestedMasterPageTemplate"&gt;
	&lt;put-attribute name="beaninit" value="/samplebeans/nestedmasterpagesamplebean.jsp" /&gt;
	&lt;put-attribute name="leftcontent" value="/samplecontents/nestedmasterpagesamplecontent.jsp" cascade="true" /&gt;
	&lt;put-attribute name="rightcontent" value="" cascade="true" /&gt;
&lt;/definition&gt;
    </pre>
</div>
<div class="wb-prettify all-pre lang-vb linenums">
    <h3>Code Sample of the Child Content Page Custom Bean</h3>
    <pre>
public class NestedMasterPageSampleBean extends NestedMasterPageTemplateBean {
    
    @Override 
    public void onWebTemplateInitialize() {
        super.onWebTemplateInitialize(); //call our parent first, we'll override the values we need

//...        
        this.setHeaderTitle("Nested Master Page Sample");
//...        
    }
}
    </pre>
</div>
<%@ include file="_sampleslist.jsp" %>
