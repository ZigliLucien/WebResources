<?xml version="1.0" encoding="UTF-8"?>
 <xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

   <xsl:output method="xml"/>


<xsl:template match="html">
<outline>
<xsl:apply-templates/>
</outline>
   </xsl:template>

<xsl:template match="chapter">
<chapter>
<xsl:value-of select="./@id"/>
</chapter>
   </xsl:template>

<xsl:template match="section">
<section id="{./@id}">
<xsl:call-template name="runit"/>
<xsl:apply-templates/>
</section>
 </xsl:template>

 <xsl:template match="subsection">
<subsection id="{./@id}">
<xsl:call-template name="runit"/>
<xsl:apply-templates/>
</subsection>
 </xsl:template>

 <xsl:template match="subsubsection">
<subsubsection id="{./@id}">
<xsl:call-template name="runit"/>
<xsl:apply-templates/>
</subsubsection>
 </xsl:template>

<xsl:template match="paragraph">
<paragraph id="{./@id}">
<xsl:call-template name="runit"/>
</paragraph>
 </xsl:template>

<xsl:template match="subparagraph">
<subparagraph id="{./@id}">
<xsl:call-template name="runit"/>
<xsl:apply-templates/>
</subparagraph>
   </xsl:template>

<xsl:template match="subsubparagraph">
<subsubparagraph id="{./@id}">
<xsl:call-template name="runit"/>
<xsl:apply-templates/>
</subsubparagraph>
 </xsl:template>

<xsl:template name="runit">
<xsl:text>
</xsl:text>
<title><xsl:value-of select="./@id"/></title>
<xsl:text>
</xsl:text>
</xsl:template>


</xsl:stylesheet>


















