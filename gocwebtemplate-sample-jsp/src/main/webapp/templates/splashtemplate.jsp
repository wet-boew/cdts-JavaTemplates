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
        <link rel="stylesheet" <s:property escapeHtml="false" value="#goctemplateclientbean.splashCssPathAttributes"/>>
        <script type="text/javascript" <s:property escapeHtml="false" value="#goctemplateclientbean.wetJsPathAttributes"/> data-cdts-setup='<s:property escapeHtml="false" value="#goctemplateclientbean.renderSplashSetup"/>'></script>
        <noscript>
            <!-- Write closure fall-back static file -->
            <s:property escapeHtml="false" value="%{#applicationscopebean.getStaticFile(#goctemplateclientbean.staticFallbackFilePath, #request.wettheme, 'splashTop.html')}" />
        </noscript>
        <s:property escapeHtml="false" value="#goctemplateclientbean.renderHtmlHeaderElements" />
        <!--  GoC Web Template Build Version <s:property value="#goctemplateclientbean.webTemplateDistributionVersion" /> -->
	</head>
	<body vocab="http://schema.org/" class="splash" typeof="WebPage">
		<div id="cdts-splash-content">
			<noscript>
				<!-- Write closure fall-back static file -->
	            <s:property escapeHtml="false" value="%{#applicationscopebean.getStaticFile(#goctemplateclientbean.staticFallbackFilePath, #request.wettheme, 'splash.html')}" />
			</noscript>
		</div>
	</body>
</html>