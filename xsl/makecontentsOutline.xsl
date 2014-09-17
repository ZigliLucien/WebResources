<?xml version="1.0" encoding="UTF-8"?>
 <xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

   <xsl:output method="html"/>
 
  <xsl:template match="outline">
<div class='menu'>
       <xsl:apply-templates/>
</div>
   </xsl:template>

<xsl:template match="chapter">
<div id='chapter'><xsl:value-of select="(.)"/></div>
   </xsl:template>

<xsl:template match="section">
<p/>
<xsl:copy>
<span style="font-size: 120%"><xsl:number format="I"/>. &#xa0; </span> <xsl:apply-templates select="node()"/> 
</xsl:copy>
 </xsl:template>

<xsl:template match="subsection">
<p/>
<xsl:copy>
   <xsl:number format="A"/>.  &#xa0; <xsl:apply-templates select="node()"/> 
</xsl:copy>
 </xsl:template>

<xsl:template match="subsubsection">
<xsl:copy>
<xsl:number format="1"/>. &#xa0;  <xsl:apply-templates select="node()"/> 
</xsl:copy>
 </xsl:template>

<xsl:template match="paragraph">
<xsl:copy>
  <xsl:number format="a"/>. &#8201;  <xsl:apply-templates select="node()"/> 
</xsl:copy>
 </xsl:template>

<xsl:template match="subparagraph">
<xsl:copy>
<xsl:number format="i"/>. &#8201;   <xsl:apply-templates select="node()"/> 
</xsl:copy>
 </xsl:template>

<xsl:template match="subsubparagraph">
<xsl:copy>
(<xsl:number format="1"/>) &#xa0;  <xsl:apply-templates select="node()"/> 
</xsl:copy>
 </xsl:template>

<xsl:template match="*">
<xsl:param name="id"><xsl:value-of select="../@id"/></xsl:param>
<xsl:param name="rf"><xsl:value-of select="translate($id,' ','_')"/>.txml</xsl:param>
<a onclick="getText('{$rf}','main')"><xsl:value-of select="(.)"/></a>
<br/>
   </xsl:template>

</xsl:stylesheet>


















