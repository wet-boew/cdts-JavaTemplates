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
        <script type="text/javascript" src="<s:property value="#goctemplateclientbean.pluginJsPath"/>"></script>
        <noscript>
            <!-- Write closure fall-back static file -->
            <s:property escape="false" value="%{#applicationscopebean.getStaticFile(#goctemplateclientbean.staticFallbackFilePath, #request.wettheme, 'refTop.html')}" />
        </noscript>
        <!-- Write closure template -->
        <script type="text/javascript">
            document.write(wet.builder.refTop({
                cdnEnv: "<s:property value="#goctemplateclientbean.cdnEnvironmentParsed"/>", 
                subTheme: "<s:property value="#goctemplateclientbean.subTheme"/>",
                <s:property escape="false" value="#goctemplateclientbean.renderjQuery" />
                <s:property escape="false" value="#goctemplateclientbean.renderLocalPath" />
            }));
        </script>
        <s:property escape="false" value="#goctemplateclientbean.htmlHeaderElements" />
        <!--  GoC Web Template Build Version <s:property value="#goctemplateclientbean.webTemplateDistributionVersion" /> -->
	</head>
	<body vocab="http://schema.org/" typeof="WebPage">		
		<s:property escape="false" value="#goctemplateclientbean.sessionTimeoutControl" />
        <div id="def-top">
            <!-- Write closure fall-back static file -->
            <s:property escape="false" value="%{#applicationscopebean.getStaticFile(#goctemplateclientbean.staticFallbackFilePath, #request.wettheme, 'top-'.concat(#goctemplateclientbean.twoLetterCultureLanguage).concat('.html'))}" />
        </div>
        <!-- Write closure template -->
        <script type="text/javascript">
            var defTop = document.getElementById("def-top");
            defTop.outerHTML = wet.builder.top({
                cdnEnv: "<s:property value="#goctemplateclientbean.cdnEnvironmentParsed"/>",
                subTheme: "<s:property value="#goctemplateclientbean.subTheme"/>",
                <s:property value="#goctemplateclientbean.renderApplicationTitle" escape="false" />
                <s:property value="#goctemplateclientbean.showSearch" />
                <s:property value="#goctemplateclientbean.renderTopSecMenu" />
                <s:property value="#goctemplateclientbean.renderLanguageLink" escape="false" />
                showPreContent: <s:property value="#goctemplateclientbean.showPreContent" />,
                <s:property value="#goctemplateclientbean.breadcrumbsList" />
                <s:property escape="false" value="#goctemplateclientbean.renderLocalPath" />
            });
        </script>
        <main role="main" property="mainContentOfPage" class="container">
            <!-- the main content -->
            <tiles:insertAttribute name="body" />
            <!-- end main content -->
            <div id="def-preFooter">
                <!-- Write closure fall-back static file -->
                <s:property escape="false" value="%{#applicationscopebean.getStaticFile(#goctemplateclientbean.staticFallbackFilePath, #request.wettheme, 'preFooter-'.concat(#goctemplateclientbean.twoLetterCultureLanguage).concat('.html'))}" />
        	</div>
            <!-- Write closure template -->
            <script type="text/javascript">
                var defPreFooter = document.getElementById("def-preFooter");
                defPreFooter.outerHTML = wet.builder.preFooter({
                    cdnEnv: "<s:property value="#goctemplateclientbean.cdnEnvironmentParsed"/>",
                    <s:property escape="false" value="#goctemplateclientbean.renderScreenIdentifier" />
                    <s:property escape="false" value="#goctemplateclientbean.dateModifiedOrVersionIdentifierValue" />
                    showPostContent: <s:property value="#goctemplateclientbean.showPostContent" />,
                    <s:property value="#goctemplateclientbean.renderFeedbackLink" />
                    <s:property value="#goctemplateclientbean.renderSharePageMediaSites" />
                });
            </script>
        </main>	
        <div id="def-footer">
            <!-- Write closure fall-back static file -->
            <s:property escape="false" value="%{#applicationscopebean.getStaticFile(#goctemplateclientbean.staticFallbackFilePath, #request.wettheme, 'footer-'.concat(#goctemplateclientbean.twoLetterCultureLanguage).concat('.html'))}" />
        </div>
        <!-- Write closure template -->
        <script type="text/javascript">
            var defFooter = document.getElementById("def-footer");
            defFooter.outerHTML = wet.builder.footer({
                cdnEnv: "<s:property value="#goctemplateclientbean.cdnEnvironmentParsed"/>",
                subTheme: "<s:property value="#goctemplateclientbean.subTheme"/>",
                <s:property value="#goctemplateclientbean.renderFeatures" />
                <s:property value="#goctemplateclientbean.renderLinksList" />
                <s:property escape="false" value="#goctemplateclientbean.renderLocalPath" />
            });
        </script>
        <!-- Write closure template -->
		<script type="text/javascript">
			document.write(wet.builder.refFooter({
			    cdnEnv: "<s:property value="#goctemplateclientbean.cdnEnvironmentParsed"/>",
			    <s:property escape="false" value="#goctemplateclientbean.renderLeavingSecureSiteWarning" />
			    <s:property escape="false" value="#goctemplateclientbean.renderjQuery" />
                <s:property escape="false" value="#goctemplateclientbean.renderLocalPath" />
			}));
		</script>
        <s:property escape="false" value="#goctemplateclientbean.htmlBodyElements" />	
	</body>
</html>