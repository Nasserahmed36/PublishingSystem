<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" exclude-result-prefixes="xlink mml"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:mml="http://www.w3.org/1998/Math/MathML"
                xmlns:xlink="http://www.w3.org/1999/xlink">
    <!-- article -->
    <xsl:template match="/">
        <xsl:apply-templates select="/article/front/journal-meta"/>
    </xsl:template>

    <xsl:template match="journal-meta">
        <kaka>
            <xsl:apply-templates select="journal-id"/>
        </kaka>
    </xsl:template>


</xsl:stylesheet>