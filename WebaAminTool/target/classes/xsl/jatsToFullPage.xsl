<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" exclude-result-prefixes="xlink mml"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:mml="http://www.w3.org/1998/Math/MathML"
                xmlns:xlink="http://www.w3.org/1999/xlink">
    <!-- article -->
    <xsl:template match="/article">
        <aticle>
            <xsl:apply-templates select="front/article-meta/abstract"/>
            <xsl:apply-templates select="body"/>
        </aticle>
    </xsl:template>
    <xsl:template match="body">
        <main>
            <div class="{local-name()}" lang="en">
                <xsl:apply-templates/>
            </div>
        </main>
    </xsl:template>

    <xsl:template match="abstract">
        <div>
            <h2>Abstract</h2>
            <div class="{local-name()}">
                <xsl:apply-templates/>
            </div>
        </div>
    </xsl:template>

    <xsl:template match="label">
        <span class="article-label">
            <xsl:apply-templates select="node()|@*"/>
        </span>
    </xsl:template>

    <xsl:template match="label" mode="caption">
        <span class="caption-label">
            <xsl:apply-templates select="node()|@*"/>
            <xsl:text>:&#32;</xsl:text>
        </span>
    </xsl:template>

    <xsl:template match="list[@list-type='simple'] | list[@list-type='labelled']">
        <ul style="list-style-type:none;display:table">
            <xsl:apply-templates mode="list-simple"/>
        </ul>
    </xsl:template>

    <xsl:template match="list-item" mode="list-simple">
        <li class="{local-name()}" style="display:table-row">
            <xsl:apply-templates select="@*"/>
            <xsl:apply-templates select="label" mode="list-simple"/>
            <div class="list-item-content" style="display:table-cell">
                <xsl:apply-templates select="*[not(self::label)]"/>
            </div>
        </li>
    </xsl:template>

    <!-- simple list item label -->
    <xsl:template match="label" mode="list-simple">
        <div class="list-item-label" style="display:table-cell;text-align:right">
            <xsl:apply-templates select="node()|@*"/>
        </div>
    </xsl:template>

    <!-- alpha list, uppercase -->
    <xsl:template match="list[@list-type='alpha-upper']">
        <ol class="{local-name()}" style="list-style-type:upper-alpha">
            <xsl:apply-templates select="node()|@*"/>
        </ol>
    </xsl:template>

    <!-- alpha list, lowercase -->
    <xsl:template match="list[@list-type='alpha-lower']">
        <ol class="{local-name()}" style="list-style-type:lower-alpha">
            <xsl:apply-templates select="node()|@*"/>
        </ol>
    </xsl:template>

    <!-- roman list, uppercase -->
    <xsl:template match="list[@list-type='roman-upper']">
        <ol class="{local-name()}" style="list-style-type:upper-roman">
            <xsl:apply-templates select="node()|@*"/>
        </ol>
    </xsl:template>

    <!-- roman list, lowercase -->
    <xsl:template match="list[@list-type='roman-lower']">
        <ol class="{local-name()}" style="list-style-type:lower-roman">
            <xsl:apply-templates select="node()|@*"/>
        </ol>
    </xsl:template>

    <!-- ordered list -->
    <xsl:template match="list[@list-type='order']">
        <ol class="{local-name()}">
            <xsl:apply-templates select="node()|@*"/>
        </ol>
    </xsl:template>

    <!-- unordered list (bullets)  -->
    <xsl:template match="list">
        <ul class="{local-name()}">
            <xsl:apply-templates select="node()|@*"/>
        </ul>
    </xsl:template>

    <!-- list item -->
    <xsl:template match="list-item">
        <li class="{local-name()}">
            <xsl:apply-templates select="node()|@*"/>
        </li>
    </xsl:template>

    <!-- paragraph -->
    <xsl:template match="p">
        <p>
            <xsl:apply-templates select="node()|@*"/>
        </p>
    </xsl:template>


    <!-- the article title -->
    <xsl:template match="article-title">
        <h1 class="{local-name()}" itemprop="name headline">
            <xsl:apply-templates select="node()|@*"/>
        </h1>
    </xsl:template>


    <!-- the article title -->
    <xsl:template match="article-title">
        <h1 class="{local-name()}" itemprop="name headline">
            <xsl:apply-templates select="node()|@*"/>
        </h1>
    </xsl:template>

    <!-- people -->
    <xsl:template match="person-group">
        <div class="{local-name()}">
            <xsl:apply-templates select="node()|@*"/>
        </div>
    </xsl:template>

    <!-- name -->
    <xsl:template name="name">
        <xsl:apply-templates select="given-names"/>
        <xsl:if test="surname">
            <xsl:text>&#32;</xsl:text>
            <xsl:apply-templates select="surname"/>
        </xsl:if>
        <xsl:if test="suffix">
            <xsl:text>&#32;</xsl:text>
            <xsl:apply-templates select="suffix"/>
        </xsl:if>
    </xsl:template>

    <xsl:template match="given-names">
        <span class="{local-name()}" itemprop="givenName">
            <xsl:apply-templates select="node()|@*"/>
        </span>
    </xsl:template>

    <xsl:template match="surname">
        <span class="{local-name()}" itemprop="familyName">
            <xsl:apply-templates select="node()|@*"/>
        </span>
    </xsl:template>

    <!-- name -->
    <xsl:template match="name">
        <span class="{local-name()}" itemprop="name">
            <xsl:call-template name="name"/>
        </span>
        <xsl:call-template name="comma-separator"/>
    </xsl:template>

    <xsl:template name="comma-separator">
        <xsl:param name="separator" select="', '"/>
        <xsl:if test="position() != last()">
            <xsl:value-of select="$separator"/>
        </xsl:if>
    </xsl:template>

    <xsl:template match="collab">
        <span class="{local-name()} name" itemprop="name">
            <xsl:apply-templates select="node()|@*"/>
        </span>
    </xsl:template>


    <xsl:template match="italic">
        <i>
            <xsl:apply-templates select="node()|@*"/>
        </i>
    </xsl:template>

    <xsl:template match="bold">
        <b>
            <xsl:apply-templates select="node()|@*"/>
        </b>
    </xsl:template>

    <xsl:template match="sub">
        <sub>
            <xsl:apply-templates select="node()|@*"/>
        </sub>
    </xsl:template>

    <xsl:template match="sup">
        <sup>
            <xsl:apply-templates select="node()|@*"/>
        </sup>
    </xsl:template>

    <xsl:template match="underline">
        <u>
            <xsl:apply-templates select="node()|@*"/>
        </u>
    </xsl:template>

    <xsl:template match="underline[@underline-style='single']">
        <span style="border-bottom:1px solid">
            <xsl:apply-templates select="node()|@*"/>
        </span>
    </xsl:template>

    <xsl:template match="underline[@underline-style='double']">
        <span style="border-bottom:3px double">
            <xsl:apply-templates select="node()|@*"/>
        </span>
    </xsl:template>

    <xsl:template match="break">
        <br/>
    </xsl:template>

    <!-- preformatted code block -->
    <xsl:template match="preformat | code">
        <pre><code><xsl:apply-templates select="node()|@*"/></code></pre>
    </xsl:template>

    <!-- style elements -->
    <xsl:template match="sc | strike | roman | sans-serif | monospace | overline">
        <span class="{local-name()}">
            <xsl:apply-templates select="node()|@*"/>
        </span>
    </xsl:template>

    <!-- inline elements -->
    <xsl:template
            match="abbrev | suffix | email | year | month | day
        | xref | contrib | source | volume | fpage | lpage | etal | pub-doi
        | named-content | styled-content | funding-source | award-doi
        | institution | city | state | country | addr-line
        | chem-struct">
        <span class="{local-name()}">
            <xsl:apply-templates select="node()|@*"/>
        </span>
    </xsl:template>
    <xsl:template match="title">
        <h3><xsl:apply-templates/></h3>
    </xsl:template>

    <!-- links -->
    <xsl:template match="uri">
        <a class="{local-name()}">
            <xsl:attribute name="href">
                <xsl:choose>
                    <xsl:when test="@xlink:href">
                        <xsl:value-of select="@xlink:href"/>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:value-of select="."/>
                    </xsl:otherwise>
                </xsl:choose>
            </xsl:attribute>
            <xsl:apply-templates select="node()|@*"/>
        </a>
    </xsl:template>

</xsl:stylesheet>