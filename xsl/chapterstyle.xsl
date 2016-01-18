<?xml version="1.0" encoding="UTF-8"?>
 <xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

   <xsl:output method="xml"/>


<xsl:template match="outline">
<outline>
<xsl:apply-templates/>
</outline>
   </xsl:template>

 
<xsl:template match="@*">
<xsl:copy>
   <xsl:apply-templates select="@*"/> 
</xsl:copy>
 </xsl:template>

<xsl:template match="booktitle">
<chapter>
<xsl:apply-templates/>
</chapter>
   </xsl:template>

<xsl:template match="chapter">
<section id="{./@id}">
<xsl:apply-templates/>
</section>
 </xsl:template>

 <xsl:template match="section">
<subsection id="{./@id}">
<xsl:apply-templates/>
</subsection>
 </xsl:template>

 <xsl:template match="subsection">
<subsubsection id="{./@id}">
<xsl:apply-templates/>
</subsubsection>
 </xsl:template>

<xsl:template match="subsubsection">
<paragraph id="{./@id}">
<xsl:apply-templates/>
</paragraph>
 </xsl:template>

<xsl:template match="paragraph">
<subparagraph id="{./@id}">
<xsl:apply-templates/>
</subparagraph>
 </xsl:template>

<xsl:template match="subparagraph">
 </xsl:template>

<xsl:template match='title'>
<title><xsl:value-of select="(.)"/></title>
</xsl:template>

</xsl:stylesheet>


















