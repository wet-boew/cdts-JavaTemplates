<?xml version='1.0' ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" exclude-result-prefixes="maven" xmlns:maven="http://maven.apache.org/POM/4.0.0" xmlns="http://maven.apache.org/POM/4.0.0">

    <!-- Transfrom the POM file from sample project(s) into a version with no reference
         to ESDC-specific dependencies, as suitable for a "packaged release". -->

    <xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes" omit-xml-declaration="yes"/>
    
    <!-- Parameters to be passed in (the values below are only default values, meant to be overriden by caller) -->
    <!-- <xsl:param name="gocwebtemplate.build.version" select="'1.0-SNAPSHOT'" /> --> 
    
    <!-- Identity template (copies everything as-is) -->
    <xsl:template match="@*|node()">
        <xsl:copy><xsl:apply-templates select="@*|node()"/></xsl:copy>
    </xsl:template>
    
    <!-- Change what needs changing in the file -->
    <xsl:template match="maven:project/maven:groupId/text()">
        <xsl:text>ca.gc.changeme</xsl:text>
        <!-- <xsl:apply-templates /> -->
    </xsl:template>
    <xsl:template match="maven:project/maven:distributionManagement">
        <!-- We need remove this node, and we also need to insert a "repositories" element, so two birds one stone -->
        <xsl:comment>Declaring a repository local to the project. 
      This repository hosts the GoC WebTemplate core libraries.  
      This is not needed within the ESDC network, where the sample projects should be created from the Maven archetypes instead.</xsl:comment><xsl:text>&#xa;  </xsl:text>
        <repositories>
            <xsl:text>&#xa;    </xsl:text><repository>
                <xsl:text>&#xa;      </xsl:text><id>project-local-maven-repo</id>
                <xsl:text>&#xa;      </xsl:text><url>file://${basedir}/./local-maven-repo</url>
                <xsl:text>&#xa;      </xsl:text><releases><xsl:text> </xsl:text><enabled>true</enabled><xsl:text> </xsl:text></releases>
                <xsl:text>&#xa;      </xsl:text><snapshots><xsl:text> </xsl:text><enabled>true</enabled><xsl:text> </xsl:text></snapshots>
            <xsl:text>&#xa;    </xsl:text></repository>
        <xsl:text>&#xa;  </xsl:text></repositories>
    </xsl:template>
</xsl:stylesheet>
