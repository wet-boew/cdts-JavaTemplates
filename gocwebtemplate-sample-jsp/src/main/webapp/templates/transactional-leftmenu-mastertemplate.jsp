<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<tiles:insertAttribute name="beaninit" />
<c:if test="${ cdn == null }">
   	<fmt:setBundle basename="goc.webtemplate.global.config.cdn" var="cdn" scope="application"/>
</c:if>
<c:set var="wettheme" scope="request">
	<s:property value="#goctemplateclientbean.theme" />
</c:set>
<c:set var="wetversion" scope="request">
	<fmt:message key="wettemplate_version" bundle="${cdn}"/>
</c:set>
<s:bean name="goc.webtemplate.component.jsp.MasterApplicationBean" var="applicationscopebean">
	<s:param name="request" value="#request.servletrequest" />
</s:bean>	
<!DOCTYPE html>
<!--[if lt IE 9]><html class="no-js lt-ie9" lang="<s:property value="#goctemplateclientbean.twoLetterCultureLanguage"/>" dir="ltr"><![endif]-->
<!--[if gt IE 8]><!-->
<html xmlns="http://www.w3.org/1999/xhtml" class="no-js" lang="<s:property value="#goctemplateclientbean.twoLetterCultureLanguage"/>" dir="ltr">
<!--<![endif]-->
	<head>
		<meta charset="utf-8" />
        <!-- Web Experience Toolkit (WET) / Boîte à outils de l'expérience Web (BOEW)
            wet-boew.github.io/wet-boew/License-en.html / wet-boew.github.io/wet-boew/Licence-fr.html -->
        <title><tiles:insertAttribute name="title" /><s:property value="#goctemplateclientbean.headerTitle"/></title>
        <meta content="width=device-width,initial-scale=1" name="viewport" />
        <!-- Load closure template scripts -->
       	<script type="text/javascript" src="<s:property value="#goctemplateclientbean.soyUtilPath"/>"></script>
        <script type="text/javascript" src="<s:property value="#goctemplateclientbean.wetJsPath"/>"></script>
        <noscript>
            <!-- Write closure fall-back static file -->
            <s:property escapeHtml="false" value="%{#applicationscopebean.getStaticFile(#goctemplateclientbean.staticFallbackFilePath, #request.wettheme, 'refTop.html')}" />
        </noscript>
        <!-- Write closure template -->
        <script type="text/javascript">
            document.write(wet.builder.refTop(<s:property escapeHtml="false" value="#goctemplateclientbean.renderRefTop" />));
        </script>
        <s:property escapeHtml="false" value="#goctemplateclientbean.renderHtmlHeaderElements" />
        <!--  GoC Web Template Build Version <s:property value="#goctemplateclientbean.webTemplateDistributionVersion" /> -->
	</head>
	<body vocab="http://schema.org/" typeof="WebPage">		
		<s:property escapeHtml="false" value="#goctemplateclientbean.renderSessionTimeoutControl" />
        <div id="def-top">
            <!-- Write closure fall-back static file -->
            <s:property escapeHtml="false" value="%{#applicationscopebean.getStaticFile(#goctemplateclientbean.staticFallbackFilePath, #request.wettheme, 'transactTop-'.concat(#goctemplateclientbean.twoLetterCultureLanguage).concat('.html'))}" />
        	<!-- Google Tag Manager DO NOT REMOVE OR MODIFY - NE PAS SUPPRIMER OU MODIFIER -->
            <noscript><iframe title="Google Tag Manager" src="//www.googletagmanager.com/ns.html?id=GTM-TLGQ9K" height="0" width="0" style="display:none;visibility:hidden"></iframe></noscript>
            <!-- End Google Tag Manager -->
        </div>
        <!-- Write closure template -->
        <script type="text/javascript">
            var defTop = document.getElementById("def-top");
            defTop.outerHTML = wet.builder.top(<s:property escapeHtml="false" value="#goctemplateclientbean.renderTransactionalTop" />);
        </script>
        <div class="container">
        	<div class="row">
        		<main role="main" property="mainContentOfPage" class="col-md-9 col-md-push-3" typeof="WebPageElement">
		            <!-- the main content -->
		            <tiles:insertAttribute name="body" />
		            <!-- end main content -->
		            <div id="def-preFooter">
		                <!-- Write closure fall-back static file -->
		                <s:property escapeHtml="false" value="%{#applicationscopebean.getStaticFile(#goctemplateclientbean.staticFallbackFilePath, #request.wettheme, 'preFooter-'.concat(#goctemplateclientbean.twoLetterCultureLanguage).concat('.html'))}" />
		        	</div>
		            <!-- Write closure template -->
		            <script type="text/javascript">
		                var defPreFooter = document.getElementById("def-preFooter");
		                defPreFooter.outerHTML = wet.builder.preFooter(<s:property escapeHtml="false" value="#goctemplateclientbean.renderTransactionalPreFooter" />);
		            </script>
		        </main>
		        <nav class="wb-sec col-md-3 col-md-pull-9" typeof="SiteNavigationElement" id="wb-sec" role="navigation">
                   <!-- Write closure fall-back static file -->
	                <s:property escapeHtml="false" value="%{#applicationscopebean.getStaticFile(#goctemplateclientbean.staticFallbackFilePath, #request.wettheme, 'secMenu-'.concat(#goctemplateclientbean.twoLetterCultureLanguage).concat('.html'))}" />
	        	</nav>
	        	<!-- Write closure template -->
	            <script type="text/javascript">
	                var secondarymenu = document.getElementById("wb-sec");
	                secondarymenu.innerHTML = wet.builder.secmenu(<s:property escapeHtml="false" value="#goctemplateclientbean.renderLeftMenuSections" />);
	            </script>	
        	</div>
        </div>        	
        <div id="def-footer">
            <!-- Write closure fall-back static file -->
            <s:property escapeHtml="false" value="%{#applicationscopebean.getStaticFile(#goctemplateclientbean.staticFallbackFilePath, #request.wettheme, 'transactFooter-'.concat(#goctemplateclientbean.twoLetterCultureLanguage).concat('.html'))}" />
        </div>
        <!-- Write closure template -->
        <script type="text/javascript">
            var defFooter = document.getElementById("def-footer");
            defFooter.outerHTML = wet.builder.footer(<s:property escapeHtml="false" value="#goctemplateclientbean.renderTransactionalFooter" />);
        </script>
        <!-- Write closure template -->
        <script type="text/javascript">
            document.write(wet.builder.refFooter(<s:property escapeHtml="false" value="#goctemplateclientbean.renderRefFooter" />));
        </script>
        <s:property escapeHtml="false" value="#goctemplateclientbean.renderHtmlBodyElements" />	
	</body>
</html>