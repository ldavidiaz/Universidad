<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<!--LUIS DAVID DIAZ MESA-->
<xsl:template match="/">
    <xsl:apply-templates/>
</xsl:template>

<xsl:template match="cv">
<html>
    <head>
        <title>EXPERIENCIA LABORAL</title>
    </head>
    <body>
    <!-- EJERCICIO 2.2 -->
    <h1>Experiencia Laboral</h1>
    <xsl:for-each select="experiencia"><!-- (1)Orden año -->
        <xsl:sort select="añoini"/>
        <xsl:choose><!-- (2)Filtro año -->
        <xsl:when test="2011>=añoini">
            <h1><xsl:value-of select="puesto"/></h1>
            <p><xsl:value-of select="mesini"/>
            <xsl:value-of select="añoini"/>
            <xsl:value-of select="hasta"/>
            <xsl:value-of select="mesfin"/>
            <xsl:value-of select="añofin"/></p>
            <xsl:for-each select="labor">
            <ul>
                <li>
                    <xsl:value-of select="."/>
                </li>
            </ul>
            </xsl:for-each>
        </xsl:when> 
        </xsl:choose>
        </xsl:for-each>

    </body>    
</html>
</xsl:template>

<xsl:template match="puesto">
    <h2>
        <xsl:value-of select="."/>
    </h2>
</xsl:template>

<xsl:template match="novedades">
    <xsl:value-of select="."/>
</xsl:template>

<xsl:template match="mesini">    
    <xsl:value-of select="."/>
</xsl:template>

<xsl:template match="añoini">
    <xsl:value-of select="."/>
</xsl:template>

<xsl:template match="mesfin">
    <xsl:value-of select="."/>
</xsl:template>

<xsl:template match="añofin">
    <xsl:value-of select="."/>
</xsl:template>

<xsl:template match="labor">
    <ul>
        <li>
            <xsl:value-of select="."/>
        </li>
   </ul>
</xsl:template>

</xsl:stylesheet>