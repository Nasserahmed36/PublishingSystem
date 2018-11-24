<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" exclude-result-prefixes="xlink mml"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:mml="http://www.w3.org/1998/Math/MathML"
                xmlns:xlink="http://www.w3.org/1999/xlink">
    <!-- article -->
    <xsl:template match="/">
        <xsl:apply-templates select="issue-xml"/>
    </xsl:template>

    <xsl:template match="issue-xml">
        <issue>
            <xsl:apply-templates/>
        </issue>
    </xsl:template>

    <xsl:template match="journal-meta">
        <xsl:apply-templates select="issn"/>
    </xsl:template>

    <xsl:template match="issn">
        <xsl:if test="@pub-type='ppub'">
            <journal-print-issn>
                <xsl:value-of select="."/>
            </journal-print-issn>
        </xsl:if>
    </xsl:template>

    <xsl:template match="month">
        <month>
            <xsl:apply-templates/>
        </month>
    </xsl:template>

    <xsl:template match="year">
        <year>
            <xsl:apply-templates/>
        </year>
    </xsl:template>

    <xsl:template match="volume">
        <volume>
            <xsl:apply-templates/>
        </volume>
    </xsl:template>

    <xsl:template match="issue">
        <number>
            <xsl:apply-templates/>
        </number>
    </xsl:template>

    <xsl:template match="issue-id">
        <xsl:if test="@pub-id-type='doi'">
            <doi>
                <xsl:value-of select="substring-before(.,'/')"/>
            </doi>
        </xsl:if>
    </xsl:template>

    <xsl:template match="toc">
        <subjects>
            <xsl:apply-templates select="//subject"/>
        </subjects>
    </xsl:template>

    <xsl:template match="subject">
        <subject>
            <xsl:apply-templates/>
        </subject>
    </xsl:template>

</xsl:stylesheet>