<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" exclude-result-prefixes="xlink mml"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:mml="http://www.w3.org/1998/Math/MathML"
                xmlns:xlink="http://www.w3.org/1999/xlink">
    <!-- article -->
    <xsl:template match="/">
        <xsl:apply-templates select="/article/front/article-meta"/>
    </xsl:template>

    <xsl:template match="article-meta">
        <article>
            <xsl:variable name="doi" select="article-id[@pub-id-type='doi']"/>
            <doi>
                <xsl:value-of select="$doi"/>
            </doi>
            <issue-doi>
                <xsl:value-of select="substring-before($doi,'/')"/>
            </issue-doi>
            <subject>
                <xsl:value-of select="article-categories/subj-group/subject"></xsl:value-of>
            </subject>
            <title>
                <xsl:value-of select="title-group/article-title"/>
            </title>
            <authors>
                <xsl:apply-templates select="contrib-group"/>
            </authors>
            <first-page>
                <xsl:value-of select="fpage"/>
            </first-page>
            <last-page>
                <xsl:value-of select="lpage"/>
            </last-page>
        </article>
    </xsl:template>
    <xsl:template match="contrib-group">
        <xsl:apply-templates select="contrib"/>
    </xsl:template>
    <xsl:template match="contrib[@contrib-type='author']">
        <xsl:variable name="name" select="string-name"/>
        <author>
            <given-names>
                <xsl:value-of select="$name/given-names"/>
            </given-names>
            <surname>
                <xsl:value-of select="$name/surname"/>
            </surname>
            <degress>
                <xsl:value-of select="degrees"/>
            </degress>
        </author>
    </xsl:template>
</xsl:stylesheet>