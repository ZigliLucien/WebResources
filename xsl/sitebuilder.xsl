<?xml version="1.0" encoding="UTF-8"?>
 <xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

   <xsl:output method="text"/>
 
  <xsl:template match="outline">
       <xsl:apply-templates select="chapter"/>
       <xsl:apply-templates select="section"/>
       <xsl:apply-templates select="*//subsection"/>
       <xsl:apply-templates select="*//subsubsection"/>
       <xsl:apply-templates select="*//paragraph"/>
       <xsl:apply-templates select="*//subparagraph"/>
       <xsl:apply-templates select="*//subsubparagraph"/>
   </xsl:template>

<xsl:template match="chapter">
chapter: <xsl:value-of select="(.)"/>
   </xsl:template>

<xsl:template match="section">
<xsl:text></xsl:text>
<xsl:if test ="./title">
title: <xsl:value-of select="./title"/>
</xsl:if>
filename: <xsl:value-of select="@id"/> 
	  <xsl:for-each select="*[not(name()='title')]">
next: <xsl:value-of select="@id"/>
          </xsl:for-each>
last: c@nt@nts
 </xsl:template>

  <xsl:template match="subsection">
<xsl:call-template name="runit"/>
 </xsl:template>

<xsl:template match="subsubsection">
<xsl:call-template name="runit"/>
 </xsl:template>

<xsl:template match="paragraph">
<xsl:call-template name="runit"/>
 </xsl:template>

<xsl:template match="subparagraph">
<xsl:call-template name="runit"/>
 </xsl:template>

<xsl:template match="subsubparagraph">
<xsl:call-template name="runit"/>
 </xsl:template>

<!-- xsl:template match="*|@*">
<xsl:copy>
   <xsl:apply-templates select="node()|@*"/> 
</xsl:copy>
 </xsl:template -->

<xsl:template name="runit">
<xsl:text>
</xsl:text>
<xsl:if test ="./title">
title: <xsl:value-of select="./title"/>
</xsl:if> 
filename: <xsl:value-of select="@id"/> 
	  <xsl:for-each select="*[not(name()='title')]">
next: <xsl:value-of select="@id"/>
          </xsl:for-each>
last: <xsl:value-of select="../@id"/> 
</xsl:template>

</xsl:stylesheet>


















