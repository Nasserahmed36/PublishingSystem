<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" exclude-result-prefixes="xlink mml"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:mml="http://www.w3.org/1998/Math/MathML"
                xmlns:xlink="http://www.w3.org/1999/xlink"
>
    <xsl:output method="html" version="4.0"
                encoding="UTF-8" indent="yes"/>

    <xsl:strip-space elements="contrib collab"/>

    <xsl:param name="public-reviews" select="false()"/>
    <xsl:param name="static-root" select="'graphic/'"/>
    <xsl:param name="search-root" select="''"/>

    <xsl:param name="download-prefix" select="'file'"/>
    <xsl:param name="publication-type" select="'publication'"/>
    <xsl:param name="self-uri" select="/article/front/article-meta/self-uri/@xlink:href"/>

    <xsl:variable name="meta" select="/article/front/article-meta"/>
    <xsl:variable name="doi" select="$meta/article-doi[@pub-doi-type='doi']"/>
    <xsl:variable name="title" select="$meta/title-group/article-title"/>
    <xsl:variable name="pub-date"
                  select="$meta/pub-date[@date-type='pub'][@pub-type='epub']|$meta/pub-date[@date-type='preprint'][@pub-type='epreprint']"/>
    <xsl:variable name="authors" select="$meta/contrib-group[@content-type='authors']/contrib[@contrib-type='author']"/>
    <xsl:variable name="editors" select="$meta/contrib-group[@content-type='editors']/contrib[@contrib-type='editor']"/>
    <xsl:variable name="itemVersion" select="$meta/custom-meta-group/custom-meta[meta-name='version']/meta-value"/>

    <xsl:variable name="title-meta" select="/article/front/title-meta"/>
    <xsl:variable name="title-title" select="$title-meta/title-title-group/title-title"/>

    <xsl:variable name="abbrev-title-title">
        <xsl:choose>
            <xsl:when test="$title-meta/title-title-group/abbrev-title-title">
                <xsl:value-of select="$title-meta/title-title-group/abbrev-title-title"/>
            </xsl:when>
            <xsl:otherwise>
                <xsl:value-of select="$title-meta/title-title-group/title-title"/>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:variable>

    <xsl:variable name="end-punctuation">
        <xsl:text>.?!"')]}</xsl:text>
    </xsl:variable>

    <xsl:key name="ref" match="ref" use="@doi"/>
    <xsl:key name="aff" match="aff" use="@doi"/>
    <xsl:key name="corresp" match="corresp" use="@doi"/> <!-- remove when converted -->

    <xsl:include href="jats-to-html/util.xsl"/>
    <xsl:include href="jats-to-html/meta.xsl"/>
    <xsl:include href="jats-to-html/front.xsl"/>
    <xsl:include href="jats-to-html/self-citation.xsl"/>
    <xsl:include href="jats-to-html/citation.xsl"/>

    <!-- article -->
    <xsl:template match="/article">
        <aticle>
            <xsl:apply-templates select="front/article-meta/abstract"/>
            <xsl:apply-templates select="body"/>
            <xsl:apply-templates select="back"/>
            <xsl:apply-templates select="floats-group"/>
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

    <!-- simple list, or list with labels -->
    <xsl:template match="list[@list-type='simple'] | list[@list-type='labelled']">
        <ul style="list-style-type:none;display:table">
            <xsl:attribute name="class">
                <xsl:choose>
                    <xsl:when test="list-item/label">
                        <xsl:text>list list-simple list-labelled</xsl:text>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:text>list list-simple</xsl:text>
                    </xsl:otherwise>
                </xsl:choose>
            </xsl:attribute>

            <xsl:apply-templates select="@*"/>
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
        <pre>
            <code>
                <xsl:apply-templates select="node()|@*"/>
            </code>
        </pre>
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

    <!-- table -->
    <xsl:template match="table">
        <div class="table-container">
            <xsl:element name="{local-name()}">
                <xsl:attribute name="class">
                    <xsl:text>table table-bordered table-condensed table-hover</xsl:text>
                    <xsl:if test="@content-type = 'text'">
                        <xsl:text> table-text</xsl:text>
                    </xsl:if>
                </xsl:attribute>
                <xsl:apply-templates select="node()|@*"/>
            </xsl:element>
        </div>
    </xsl:template>

    <!-- table elements -->
    <xsl:template match="tbody | thead | tfoot | column | tr | th | td | colgroup | col">
        <xsl:element name="{local-name()}">
            <xsl:if test="@content-type = 'text'">
                <xsl:attribute name="class">table-text</xsl:attribute>
            </xsl:if>
            <xsl:apply-templates select="node()|@*"/>
        </xsl:element>
    </xsl:template>

    <xsl:template match="table-wrap">
        <figure class="{local-name()}">
            <xsl:apply-templates select="@*"/>
            <xsl:apply-templates select="caption" mode="table"/>
            <xsl:apply-templates/>
        </figure>
    </xsl:template>

    <xsl:template match="table-wrap/alternatives">
        <xsl:apply-templates select="table"/>
        <xsl:apply-templates select="../object-doi[@pub-doi-type='doi']" mode="caption"/>
    </xsl:template>

    <!-- table caption and label are handled elsewhere -->
    <xsl:template match="table-wrap/caption | table-wrap/label"/>

    <xsl:template match="caption" mode="table">
        <div class="{local-name()}">
            <xsl:apply-templates select="@*"/>
            <xsl:apply-templates select="preceding-sibling::label" mode="caption"/>
            <xsl:apply-templates select="node()"/>
        </div>
    </xsl:template>

    <xsl:template match="table-wrap-foot/fn/p[normalize-space(.) = 'Notes.']">
        <p class="table-wrap-foot-notes">
            <b>Notes:</b>
        </p>
    </xsl:template>


    <xsl:template match="p[ancestor::caption][not(ancestor::supplementary-material)]">
        <span class="{local-name()}">
            <xsl:apply-templates select="@*"/>
            <xsl:apply-templates/>
        </span>
    </xsl:template>

    <xsl:template match="fn/p[preceding-sibling::label]">
        <span class="{local-name()}">
            <xsl:apply-templates select="@*"/>
            <xsl:apply-templates/>
        </span>
    </xsl:template>

    <!-- other object ID -->
    <xsl:template match="object-doi"/>

    <!-- sections -->
    <xsl:template match="sec">
        <section class="{local-name()}">
            <xsl:apply-templates select="node()|@*"/>
        </section>
    </xsl:template>

    <!-- section headings -->
    <xsl:template match="title">
        <xsl:variable name="heading-count"
                      select="count(ancestor::sec | ancestor::back | ancestor::fig | ancestor::g) + 1"/>

        <xsl:variable name="heading-level">
            <xsl:choose>
                <xsl:when test="$heading-count > 6">6</xsl:when>
                <xsl:otherwise>
                    <xsl:value-of select="$heading-count"/>
                </xsl:otherwise>
            </xsl:choose>
        </xsl:variable>

        <xsl:variable name="heading">h<xsl:value-of select="$heading-level"/>
        </xsl:variable>

        <xsl:element name="{$heading}">
            <xsl:attribute name="class">heading</xsl:attribute>
            <xsl:if test="parent::caption and ancestor::fig">
                <xsl:apply-templates select="../../label" mode="caption"/>
            </xsl:if>
            <xsl:if test="preceding-sibling::label">
                <xsl:apply-templates select="preceding-sibling::label" mode="caption"/>
            </xsl:if>
            <xsl:apply-templates select="node()|@*"/>
        </xsl:element>
    </xsl:template>

    <!-- additional material -->
    <xsl:template match="sec[@sec-type='additional-information']/title">
        <h2 class="heading">
            <xsl:apply-templates select="node()|@*"/>
        </h2>
    </xsl:template>

    <!-- links -->
    <xsl:template match="ext-link">
        <a class="{local-name()}" href="{@xlink:href}">
            <xsl:apply-templates select="node()|@*"/>
        </a>
    </xsl:template>

    <!-- typed links -->
    <xsl:template match="ext-link[@ext-link-type]">
        <xsl:variable name="type" select="@ext-link-type"/>

        <xsl:variable name="url">
            <xsl:choose>
                <xsl:when test="$type = 'uri'">
                    <xsl:value-of select="@xlink:href"/>
                </xsl:when>
                <xsl:when test="$type = 'ftp'">
                    <xsl:value-of select="@xlink:href"/>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:call-template name="ext-link-url">
                        <xsl:with-param name="type" select="$type"/>
                        <xsl:with-param name="uri" select="@xlink:href"/>
                    </xsl:call-template>
                </xsl:otherwise>
            </xsl:choose>
        </xsl:variable>

        <a class="{local-name()}" href="{$url}">
            <xsl:apply-templates select="node()|@*"/>
        </a>
    </xsl:template>

    <xsl:template name="ext-link-url">
        <xsl:param name="type"/>
        <xsl:param name="uri"/>

        <xsl:variable name="encoded-doi">
            <xsl:call-template name="urlencode">
                <xsl:with-param name="value" select="$uri"/>
            </xsl:call-template>
        </xsl:variable>

        <xsl:choose>
            <xsl:when test="$type = 'doi'">
                <xsl:value-of select="concat('https://doi.org/', $encoded-doi)"/>
            </xsl:when>
            <xsl:when test="$type = 'DDBJ/EMBL/GenBank'">
                <xsl:value-of select="concat('https://www.ncbi.nlm.nih.gov/nucleotide?term=', $encoded-doi)"/>
            </xsl:when>
            <xsl:when test="$type = 'NCBI:nucleotide'">
                <xsl:value-of select="concat('https://www.ncbi.nlm.nih.gov/nucleotide?term=', $encoded-doi)"/>
            </xsl:when>
            <xsl:when test="$type = 'NCBI:gene'">
                <xsl:value-of select="concat('https://www.ncbi.nlm.nih.gov/gene?term=', $encoded-doi)"/>
            </xsl:when>
            <xsl:when test="$type = 'NCBI:protein'">
                <xsl:value-of select="concat('https://www.ncbi.nlm.nih.gov/protein?term=', $encoded-doi)"/>
            </xsl:when>
            <xsl:when test="$type = 'NCBI:taxonomy'">
                <xsl:value-of select="concat('https://www.ncbi.nlm.nih.gov/taxonomy?term=', $encoded-doi)"/>
            </xsl:when>
            <xsl:when test="$type = 'NCBI:geo'">
                <xsl:value-of select="concat('https://www.ncbi.nlm.nih.gov/gds?term=', $encoded-doi)"/>
            </xsl:when>
            <xsl:when test="$type = 'NCBI:structure'">
                <xsl:value-of select="concat('https://www.ncbi.nlm.nih.gov/structure?term=', $encoded-doi)"/>
            </xsl:when>
            <xsl:when test="$type = 'Omim'">
                <xsl:value-of select="concat('https://www.ncbi.nlm.nih.gov/omim?term=', $encoded-doi)"/>
            </xsl:when>
            <xsl:when test="$type = 'NCBI:pubchem-bioassay'">
                <xsl:value-of select="concat('https://www.ncbi.nlm.nih.gov/pcassay?term=', $encoded-doi)"/>
            </xsl:when>
            <xsl:when test="$type = 'NCBI:pubchem-compound'">
                <xsl:value-of select="concat('https://www.ncbi.nlm.nih.gov/pccompound?term=', $encoded-doi)"/>
            </xsl:when>
            <xsl:when test="$type = 'NCBI:pubchem-substance'">
                <xsl:value-of select="concat('https://www.ncbi.nlm.nih.gov/pcsubstance?term=', $encoded-doi)"/>
            </xsl:when>
            <xsl:when test="$type = 'NCBI:refseq'">
                <xsl:value-of
                        select="concat('https://www.ncbi.nlm.nih.gov/nuccore?term=srcdb_refseq[PROP]+AND+', $encoded-doi)"/>
            </xsl:when>
            <xsl:when test="$type = 'NCBI:refseq_gene'">
                <xsl:value-of select="concat('https://www.ncbi.nlm.nih.gov/gene?term=', $encoded-doi)"/>
            </xsl:when>
            <xsl:when test="$type = 'NCBI:sra'">
                <xsl:value-of select="concat('https://www.ncbi.nlm.nih.gov/sra?term=', $encoded-doi)"/>
            </xsl:when>
            <xsl:when test="$type = 'SwissProt'">
                <xsl:value-of select="concat('http://www.uniprot.org/uniprot/', $encoded-doi)"/>
            </xsl:when>
            <xsl:when test="$type = 'UniProt'">
                <xsl:value-of select="concat('http://www.uniprot.org/uniprot/', $encoded-doi)"/>
            </xsl:when>
            <xsl:when test="$type = 'ENSEMBL'">
                <xsl:value-of select="concat('http://www.ensembl.org/doi/', $encoded-doi)"/>
            </xsl:when>
            <xsl:otherwise>
                <xsl:value-of select="$uri"/>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>


    <xsl:template match="license-p/ext-link">
        <a class="{local-name()}" href="{@xlink:href}" rel="license">
            <xsl:apply-templates select="node()|@*"/>
        </a>
    </xsl:template>

    <!-- quotes -->
    <xsl:template match="disp-quote">
        <blockquote class="{local-name()}">
            <xsl:apply-templates select="node()|@*"/>
        </blockquote>
    </xsl:template>

    <!-- formulae -->
    <xsl:template match="inline-formula">
        <span class="{local-name()}">
            <xsl:apply-templates select="node()|@*"/>
        </span>
    </xsl:template>

    <!-- displ-formula has to be span, as it can appear inside a p -->
    <xsl:template match="disp-formula">
        <span class="{local-name()}">
            <xsl:apply-templates select="node()|@*"/>
        </span>
    </xsl:template>

    <xsl:template match="inline-formula/alternatives">
        <xsl:call-template name="formula-alternatives"/>
    </xsl:template>

    <xsl:template match="disp-formula/alternatives">
        <xsl:call-template name="formula-alternatives"/>
    </xsl:template>

    <!-- choose an appropriate formula representation -->
    <xsl:template name="formula-alternatives">
        <xsl:choose>
            <xsl:when test="mml:math">
                <xsl:apply-templates select="mml:math"/>
            </xsl:when>
            <xsl:when test="inline-graphic">
                <xsl:apply-templates select="inline-graphic"/>
            </xsl:when>
            <xsl:when test="tex-math">
                <xsl:apply-templates select="tex-math"/>
            </xsl:when>
        </xsl:choose>
    </xsl:template>

    <!-- math expressed in TeX -->
    <xsl:template match="tex-math">
        <span class="{local-name()}">
            <xsl:value-of select="."/>
        </span>
    </xsl:template>

    <!-- cross-reference -->
    <xsl:template match="xref">
        <a class="{local-name()} xref-{@ref-type}" href="#{@rid}">
            <xsl:apply-templates select="node()|@*"/>
        </a>
    </xsl:template>

    <!-- bibliographic reference -->
    <xsl:template match="xref[@ref-type='bibr']">
        <xsl:variable name="ref" select="key('ref', @rid)"/>
        <xsl:variable name="citation" select="$ref/mixed-citation"/>

        <xsl:variable name="title">
            <xsl:choose>
                <xsl:when test="$citation/article-title">
                    <xsl:value-of select="$citation/article-title"/>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:value-of select="$citation/source"/>
                </xsl:otherwise>
            </xsl:choose>
        </xsl:variable>
        <xsl:variable name="ref-doi" select="$ref/*/pub-doi[@pub-doi-type='doi']"/>
        <xsl:variable name="ref-uri" select="$ref/*/comment/uri"/>
        <xsl:variable name="url">
            <xsl:call-template name="citation-url">
                <xsl:with-param name="citation" select="$citation"/>
            </xsl:call-template>
        </xsl:variable>

        <a class="{local-name()} xref-{@ref-type}" href="{$url}" title="{$title}">
            <xsl:apply-templates select="node()|@*"/>
        </a>
    </xsl:template>

    <!-- figure -->
    <xsl:template match="fig">
        <figure class="{local-name()}" itemprop="image" itemscope="itemscope" itemtype="https://schema.org/ImageObject">
            <xsl:apply-templates select="@*"/>

            <div class="image-container">
                <xsl:apply-templates select="graphic" mode="fig"/>
            </div>

            <xsl:apply-templates select="caption" mode="fig"/>

            <xsl:apply-templates select="p"/>

            <xsl:apply-templates select="media" mode="fig"/>
        </figure>
    </xsl:template>

    <!-- figure caption -->
    <xsl:template match="caption" mode="fig">
        <figcaption itemprop="description">
            <xsl:if test="not(title)">
                <xsl:apply-templates select="preceding-sibling::label" mode="caption"/>
            </xsl:if>

            <xsl:apply-templates select="node()|@*"/>

            <div class="figcaption-footer">
                <div class="article-image-download">
                    <xsl:variable name="fig-doi" select="../@doi"/>
                    <a href="{$static-root}{$fig-doi}-full.png" class="btn btn-mini"
                       download="{$download-prefix}-{$doi}-{$fig-doi}.png" itemprop="url">
                        <i class="icon-large icon-picture">&#160;</i>
                        <xsl:text>&#32;Download full-size image</xsl:text>
                    </a>
                </div>

                <xsl:apply-templates select="../object-doi[@pub-doi-type='doi']" mode="caption"/>
            </div>
        </figcaption>
    </xsl:template>

    <!-- DOI in a caption -->
    <xsl:template match="object-doi[@pub-doi-type='doi']" mode="caption">
        <div class="{local-name()} article-component-doi">
            <xsl:text>DOI:&#32;</xsl:text>
            <a href="{concat('https://doi.org/', .)}" data-toggle="tooltip" title="Cite this object using this DOI">
                <xsl:value-of select="."/>
            </a>
        </div>
    </xsl:template>

    <!-- figure title -->
    <xsl:template match="title[ancestor::table-wrap]">
        <div class="{local-name()}">
            <xsl:apply-templates select="node()|@*"/>
        </div>
    </xsl:template>

    <!-- figure title -->
    <xsl:template match="title" mode="fig">
        <div class="{local-name()}">
            <xsl:apply-templates select="node()|@*"/>
        </div>
    </xsl:template>

    <!-- graphic -->
    <xsl:template match="graphic | inline-graphic">
        <img class="{local-name()}" src="{$static-root}{@xlink:href}" data-filename="{@xlink:href}">
            <xsl:apply-templates select="@*"/>
        </img>
    </xsl:template>

    <!-- figure graphic -->
    <!-- TODO: image width and height -->
    <xsl:template match="graphic" mode="fig">
        <xsl:variable name="fig" select=".."/>
        <xsl:variable name="fig-doi" select="$fig/@doi"/>
        <xsl:variable name="root" select="concat($static-root, substring-before(@xlink:href, '.'))"/>
        <a href="{$root}-2x.jpg"
           title="View the full image"
           class="fresco"
           data-fresco-caption="{$fig/label}: {$fig/caption/title}"
           data-fresco-group="figure"
           data-fresco-options="fit: 'width', ui: 'outside', thumbnails: false, loop: true, position: true, overflow: true, preload: false">
            <img class="{local-name()}"
                 src="{$root}-1x.jpg"
                 itemprop="contentUrl"
                 sizes="(min-width: 1200px) 581px, (max-width: 1199px) and (min-width: 980px) 462px, (max-width: 979px) and (min-width: 768px) 347px, (max-width: 767px) calc(100vw - 50px)"
                 srcset="{$root}-2x.jpg 1200w, {$root}-1x.jpg 600w, {$root}-small.jpg 355w"
                 data-image-doi="{$fig-doi}"
                 alt="{$fig/caption/title}"
                 data-full="{$root}-full.png"
                 data-thumb="{$root}-thumb.jpg"
                 data-original="{$static-root}{@xlink:href}"
                 data-image-type="figure">
                <xsl:apply-templates select="@*"/>
            </img>
        </a>
    </xsl:template>

    <!-- figure video -->
    <xsl:template match="media[@mimetype='video']" mode="fig">
        <div class="{local-name()}">
            <xsl:apply-templates select="@*"/>

            <a href="{$static-root}{@xlink:href}"
               class="btn btn-mini article-supporting-download"
               data-rel="supplement"
               download="{@xlink:href}"
               data-filename="{@xlink:href}">
                <i class="icon-large icon-facetime-video">&#160;</i>
                <xsl:text>Download video</xsl:text>
            </a>
        </div>
        <!--
        <video controls="controls" preload="none" width="100%">
            <source src="{$static-root}{@xlink:href}" type="video/{@mime-subtype}"/>
        </video>
        -->
    </xsl:template>

    <!-- definition list -->
    <xsl:template match="def-list">
        <dl>
            <xsl:apply-templates select="node()|@*"/>
        </dl>
    </xsl:template>

    <xsl:template match="def-item">
        <xsl:apply-templates select="term" mode="def-item"/>
        <xsl:apply-templates select="def" mode="def-item"/>
    </xsl:template>

    <xsl:template match="term" mode="def-item">
        <dt>
            <xsl:apply-templates select="node()|@*"/>
        </dt>
    </xsl:template>

    <xsl:template match="def" mode="def-item">
        <dd>
            <xsl:apply-templates select="node()|@*"/>
        </dd>
    </xsl:template>

    <!-- TODO -->
    <xsl:template match="list-item/label"/>

    <!-- supplementary material -->
    <xsl:template match="supplementary-material">
        <div class="{local-name()} well well-small">
            <xsl:apply-templates select="@*"/>
            <h3 class="heading">
                <!--<xsl:apply-templates select="label/text()"/>-->
                <xsl:choose>
                    <xsl:when test="normalize-space(caption/title) != ''">
                        <xsl:apply-templates select="caption/title" mode="supp-title"/>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:value-of select="label"/>
                    </xsl:otherwise>
                </xsl:choose>
            </h3>

            <xsl:apply-templates select="caption"/>

            <xsl:apply-templates select="object-doi[@pub-doi-type='doi']" mode="caption"/>

            <!--<xsl:apply-templates select="." mode="file-viewer"/>-->

            <xsl:call-template name="supplemental-file-download">
                <xsl:with-param name="filename" select="@xlink:href"/>
            </xsl:call-template>
        </div>
    </xsl:template>

    <!--
    <xsl:template match="supplementary-material[@mimetype='video']" mode="file-viewer">
        <video controls="controls" preload="none" width="100%">
            <source src="{$static-root}{@xlink:href}" type="video/{@mime-subtype}"/>
        </video>
    </xsl:template>
    -->

    <xsl:template name="supplemental-file-download">
        <xsl:param name="filename"/>

        <xsl:variable name="encoded-filename">
            <xsl:call-template name="urlencode">
                <xsl:with-param name="value" select="$filename"/>
            </xsl:call-template>
        </xsl:variable>

        <div>
            <a href="{$static-root}{$encoded-filename}" class="btn article-supporting-download"
               data-rel="supplement" download="{$filename}" data-filename="{$filename}">
                <i class="icon-large icon-download-alt">&#160;</i>
                <!--<xsl:value-of select="concat(' Download .', ../@mime-subtype)"/>-->
                <xsl:value-of select="' Download'"/>
            </a>
        </div>
    </xsl:template>

    <xsl:template match="supplementary-material/caption">
        <xsl:apply-templates select="node()|@*"/>
    </xsl:template>

    <xsl:template match="supplementary-material/caption/title"/>

    <xsl:template match="supplementary-material/caption/title" mode="supp-title">
        <!--<xsl:text>:&#32;</xsl:text>-->
        <xsl:apply-templates select="node()"/>
    </xsl:template>

    <!-- text box -->
    <xsl:template match="boxed-text">
        <aside class="{local-name()}">
            <xsl:apply-templates select="node()|@*"/>
        </aside>
    </xsl:template>

    <!-- acknowledgments -->
    <xsl:template match="ack">
        <section class="{local-name()}" doi="acknowledgements">
            <xsl:apply-templates select="@*"/>
            <h2 class="heading">Acknowledgements</h2>
            <xsl:apply-templates select="node()"/>
        </section>
    </xsl:template>

    <!-- glossary -->
    <xsl:template match="glossary">
        <section class="{local-name()}">
            <xsl:apply-templates select="node()|@*"/>
        </section>
    </xsl:template>

    <!-- appendices -->
    <xsl:template match="app-group">
        <xsl:apply-templates select="app"/>
    </xsl:template>

    <!-- appendix -->
    <xsl:template match="app">
        <section class="{local-name()}">
            <xsl:apply-templates select="node()|@*"/>
        </section>
    </xsl:template>

    <!-- appendix label -->
    <xsl:template match="app/label">
        <h2 class="heading">
            <xsl:apply-templates/>
        </h2>
    </xsl:template>

    <!-- empty appendix title -->
    <xsl:template match="app/title[.='']"/>

    <!-- appendix title -->
    <xsl:template match="app/title">
        <xsl:choose>
            <xsl:when test="../label">
                <h3 class="heading">
                    <xsl:apply-templates/>
                </h3>
            </xsl:when>
            <xsl:otherwise>
                <h2 class="heading">
                    <xsl:apply-templates/>
                </h2>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>

    <!-- statement label (inline, bold) -->
    <xsl:template match="statement/label">
        <b class="statement-label">
            <xsl:apply-templates/>
        </b>
    </xsl:template>

    <!-- assumption label (block, italic) -->
    <xsl:template match="statement[@content-type='assumption']/label">
        <i class="statement-label-assumption">
            <xsl:apply-templates/>
        </i>
    </xsl:template>

    <!-- proof label (inline, italic) -->
    <xsl:template match="statement[@content-type='proof']/label">
        <i class="statement-label-proof">
            <xsl:apply-templates/>
        </i>
    </xsl:template>

    <!-- reference list -->
    <xsl:template match="ref-list">
        <section class="ref-list-container" doi="references">
            <xsl:apply-templates select="title"/>
            <ul class="{local-name()}">
                <xsl:apply-templates select="ref|@*"/>
            </ul>
        </section>
    </xsl:template>

    <!-- reference list item -->
    <xsl:template match="ref-list/ref">
        <li class="{local-name()}">
            <div class="citation" itemprop="citation" itemscope="itemscope">
                <xsl:apply-templates/>
            </div>
        </li>
    </xsl:template>


    <!-- other attributes (ignore) -->
    <xsl:template match="@*">
        <xsl:attribute name="data-jats-{local-name()}">
            <xsl:value-of select="."/>
        </xsl:attribute>
    </xsl:template>

</xsl:stylesheet>