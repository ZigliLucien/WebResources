<?xml version="1.0" encoding="UTF-8"?>
 <xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

   <xsl:output method="html"/>
 
  <xsl:template match="outline">
<div class='menu'>
       <xsl:apply-templates/>
</div>
   </xsl:template>

<xsl:template match="chapter">
<xsl:param name="idd"><xsl:value-of select="(.)"/></xsl:param>
<xsl:param name="rf"><xsl:value-of select="translate($idd,' ','_')"/>.txml</xsl:param>
<xsl:param name="reff"><xsl:value-of select="translate($idd,'_',' ')"/></xsl:param>
<div id='chapter'>
<a onclick="getText('{$rf}','main')"><xsl:value-of select="$reff"/></a>
</div>
   </xsl:template>

<xsl:template match="section">
<p/>
<xsl:copy>
&#xa0; <xsl:apply-templates select="node()"/> 
</xsl:copy>
 </xsl:template>

<xsl:template match="subsection">
<p/>
<xsl:copy>
 &#xa0; <xsl:apply-templates select="node()"/> 
</xsl:copy>
 </xsl:template>

<xsl:template match="subsubsection">
<xsl:copy>
 &#xa0;  <xsl:apply-templates select="node()"/> 
</xsl:copy>
 </xsl:template>

<xsl:template match="paragraph">
<xsl:copy>
 &#8201;  <xsl:apply-templates select="node()"/> 
</xsl:copy>
 </xsl:template>

<xsl:template match="subparagraph">
<xsl:copy>
 &#8201;   <xsl:apply-templates select="node()"/> 
</xsl:copy>
 </xsl:template>

<xsl:template match="subsubparagraph">
<xsl:copy>
 &#xa0;  <xsl:apply-templates select="node()"/> 
</xsl:copy>
 </xsl:template>

<xsl:template match="*">
<xsl:param name="id"><xsl:value-of select="../@id"/></xsl:param>
<xsl:param name="rf"><xsl:value-of select="translate($id,' ','_')"/>.txml</xsl:param>
<xsl:param name="idd"><xsl:value-of select="(.)"/></xsl:param>
<xsl:param name="reff"><xsl:value-of select="translate($idd,'_',' ')"/></xsl:param>
<a onclick="getText('{$rf}','main')"><xsl:value-of select="$reff"/></a>
<br/>
   </xsl:template>

</xsl:stylesheet>


















