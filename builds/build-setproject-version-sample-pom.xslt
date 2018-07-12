<?xml version='1.0' ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" exclude-result-prefixes="maven" xmlns:maven="http://maven.apache.org/POM/4.0.0">

    <!-- Transfrom the POM file from sample project(s) into a version with no reference
         to ESDC-specific dependencies, as suitable for a "packaged release". -->

    <xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes" omit-xml-declaration="yes"/>
    
    <!-- Parameters to be passed in (the values below are only default values, meant to be overriden by caller) -->
    <xsl:param name="gocwebtemplate.build.version" select="'1.0-SNAPSHOT'" />  
    
    <!-- Identity template (copies everything as-is) -->
    <xsl:template match="@*|node()">
        <xsl:copy><xsl:apply-templates select="@*|node()"/></xsl:copy>
    </xsl:template>
    
    <!-- Change what needs changing in the file -->
    <xsl:template match="maven:project/maven:version/text()">
        <xsl:value-of select="$gocwebtemplate.build.version" />
    </xsl:template>
    <xsl:template match="maven:project/maven:properties/maven:webtemplate.version/text()">
        <xsl:value-of select="$gocwebtemplate.build.version" />
    </xsl:template>
</xsl:stylesheet>
