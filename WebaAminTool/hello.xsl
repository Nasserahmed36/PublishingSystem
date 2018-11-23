<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/catalog">
        <xsl:apply-templates select="label"/>
    </xsl:template>

    <xsl:template match="label">
        <xsl:value-of select="self::"/>
    </xsl:template>
</xsl:stylesheet>

