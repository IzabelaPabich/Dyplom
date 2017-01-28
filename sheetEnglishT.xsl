<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" 
				xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
				xmlns:fo="http://www.w3.org/1999/XSL/Format"
				xmlns:n="http://example.org/moja">

	<xsl:template match="n:sheet">
		<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
			<fo:layout-master-set>
				<fo:simple-page-master master-name="page" page-width="21cm" page-height="29.7cm"
                                   margin-top="1.5cm" margin-bottom="1.5cm" margin-left="1.5cm" margin-right="1.5cm">
					<fo:region-body margin-top="1cm" margin-bottom="1cm" region-name="body"/>
					<fo:region-after extent="1cm" region-name="page-footer"/>
				</fo:simple-page-master>
				<fo:simple-page-master master-name="last-page" page-width="21cm" page-height="29.7cm"
                                   margin-top="1.5cm" margin-bottom="1.5cm" margin-left="1.5cm" margin-right="1.5cm">
					<fo:region-body margin-top="1cm" margin-bottom="1cm" region-name="body"/>
					<fo:region-after extent="1cm" region-name="last-page-footer"/>
				</fo:simple-page-master>
				<fo:page-sequence-master master-name="pages">
					<fo:repeatable-page-master-alternatives>
						<fo:conditional-page-master-reference page-position="last" master-reference="last-page"/>
						<fo:conditional-page-master-reference master-reference="page"/>
					</fo:repeatable-page-master-alternatives>
				</fo:page-sequence-master>
			</fo:layout-master-set>

			<fo:page-sequence master-reference="pages">
				<fo:static-content flow-name="last-page-footer">
					<xsl:if test="n:grade">
						<fo:block font-size="16pt" space-after="5mm" text-align="right">
							<xsl:value-of select="n:grade"/>
						</fo:block>
					</xsl:if>
					<fo:block text-align="center">
						<fo:page-number/>
					</fo:block>
				</fo:static-content>
				<fo:flow flow-name="body">
					<fo:block font-size="24pt" font-weight="bold" space-after="20mm" text-align="center">
						<xsl:value-of select="n:title"/>
					</fo:block>
					<xsl:if test="n:nameAndSurname">
						<fo:block font-size="14pt" space-after="5mm" text-align="left">
							<xsl:value-of select="n:nameAndSurname"/>
						</fo:block>
					</xsl:if>
					<xsl:if test="n:date">
						<fo:block font-size="14pt" space-after="5mm" text-align="left">
							<xsl:value-of select="n:date"/>
						</fo:block>
					</xsl:if>
					<fo:block font-size="20pt" space-before="20mm" space-after="20mm" text-align="center">
						<xsl:value-of select="n:addInfo"/>
					</fo:block>
					<fo:block font-size="16pt">
						<xsl:apply-templates select="n:engpolWords"/>
					</fo:block>
				</fo:flow>
			</fo:page-sequence>

		</fo:root>
	</xsl:template>

	<xsl:template match="n:engpolWords">
		<xsl:for-each select="n:engpolWord">
			<fo:block font-size="16pt" space-after="5mm" text-align="left">
				<xsl:choose>
				<xsl:when test="@ifToPolish">
					<xsl:value-of select="concat(n:engWord, ' - ', n:polWord)"/>
				</xsl:when>
				<xsl:when test="@ifToEnglish">
					<xsl:value-of select="concat(n:polWord, ' - ', n:engWord)"/>
				</xsl:when>
				</xsl:choose>
			</fo:block>
		</xsl:for-each>
	</xsl:template>


</xsl:stylesheet>