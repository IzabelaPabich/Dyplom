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
						<fo:block font-size="14pt" space-after="5mm" text-align="left" font-family="arial">
							<xsl:value-of select="n:nameAndSurname"/>
						</fo:block>
					</xsl:if>
					<xsl:if test="n:date">
						<fo:block font-size="14pt" space-after="5mm" text-align="left">
							<xsl:value-of select="n:date"/>
						</fo:block>
					</xsl:if>
					<fo:block font-size="20pt" space-before="20mm" space-after="20mm" text-align="center" font-family="arial">
						<xsl:value-of select="n:addInfo"/>
					</fo:block>
					<fo:block font-size="16pt">
						<xsl:apply-templates select="n:mathTasks"/>
					</fo:block>
				</fo:flow>
			</fo:page-sequence>

		</fo:root>
	</xsl:template>
	
	<xsl:template match="n:mathTasks">
		<xsl:for-each select="n:equation">
			<fo:block font-size="16pt" space-after="5mm" text-align="left">
				<xsl:number/>.  <xsl:value-of select="concat(n:firstComp, ' ', n:operation, ' ', n:secondComp, ' ', n:equationMark, ' ', n:result)"/>
			</fo:block>
		</xsl:for-each>
		<xsl:for-each select="n:equationM">
			<fo:block font-size="16pt" space-after="5mm" text-align="left">
				<xsl:number/>.  <xsl:value-of select="concat(n:firstComp1, ' ', n:firstOperation, ' ', n:firstComp2, ' ', n:equationMark, ' ', 
										' ', n:secondComp1, ' ', n:secondOperation, ' ', n:secondComp2)"/>
			</fo:block>
		</xsl:for-each>
		<xsl:for-each select="n:graph">
		<fo:block  page-break-inside="avoid">
			<fo:table>
			<fo:table-column column-width="10mm"/>
            <fo:table-column column-width="10mm"/>
            <fo:table-column column-width="25mm"/>
            <fo:table-column column-width="10mm"/>
            <fo:table-column column-width="10mm"/>
			
			<fo:table-body>
				<fo:table-row>
					<fo:table-cell padding="1mm">
                        <fo:block>
							<xsl:text> </xsl:text>
                        </fo:block>
                    </fo:table-cell>
					<fo:table-cell padding="1mm">
                        <fo:block>
							<xsl:text> </xsl:text>
                        </fo:block>
                    </fo:table-cell>
					<fo:table-cell padding="1mm" text-align="center">
                        <fo:block>
							<xsl:for-each select="n:operation12">
								<xsl:value-of select="concat(n:operation, n:value)"/>
							</xsl:for-each>
                        </fo:block>
                    </fo:table-cell>
					<fo:table-cell padding="1mm">
                        <fo:block>
							<xsl:text> </xsl:text>
                        </fo:block>
                    </fo:table-cell>
					<fo:table-cell padding="1mm">
                        <fo:block>
							<xsl:text> </xsl:text>
                        </fo:block>
                    </fo:table-cell>
				</fo:table-row>
				
				<fo:table-row>
					<fo:table-cell padding="1mm">
                        <fo:block>
							<xsl:text> </xsl:text>
                        </fo:block>
                    </fo:table-cell>
					<fo:table-cell padding="1mm" text-align="center">
                        <fo:block>
							<xsl:value-of select="n:firstComp"/>
                        </fo:block>
                    </fo:table-cell>
					<fo:table-cell padding="1mm" text-align="center">
                        <fo:block>
                            <fo:external-graphic src="url('src/main/resources/xslt/operation12.png')" width="100%" content-height="100%" content-width="scale-to-fit"
													scaling="uniform"/>
                        </fo:block>
                    </fo:table-cell>
					<fo:table-cell padding="1mm" text-align="center">
                        <fo:block>
                            <xsl:value-of select="n:secondComp"/>
                        </fo:block>
                    </fo:table-cell>
					<fo:table-cell padding="1mm">
                        <fo:block>
							<xsl:text> </xsl:text>
                        </fo:block>
                    </fo:table-cell>
				</fo:table-row>
				
				<fo:table-row>
					<fo:table-cell padding="1mm" text-align="center">
                        <fo:block>
							<xsl:for-each select="n:operation31">
								<xsl:value-of select="concat(n:operation, n:value)"/>
							</xsl:for-each>
						</fo:block>
                    </fo:table-cell>
					<fo:table-cell padding="1mm" text-align="center">
                        <fo:block>
							<fo:external-graphic src="url('src/main/resources/xslt/operation31.png')" width="100%" content-height="100%" content-width="scale-down-to-fit"
												scaling="uniform"/>
                        </fo:block>
                    </fo:table-cell>
					<fo:table-cell padding="1mm">
                        <fo:block>
							<xsl:text> </xsl:text>
                        </fo:block>
                    </fo:table-cell>
					<fo:table-cell padding="1mm" text-align="center">
                        <fo:block>
                            <fo:external-graphic src="url('src/main/resources/xslt/operation23.png')" width="100%" content-height="100%" content-width="scale-down-to-fit"
												scaling="uniform"/>
                        </fo:block>
                    </fo:table-cell>
					<fo:table-cell padding="1mm" text-align="center">
                        <fo:block>
							<xsl:for-each select="n:operation23">
								<xsl:value-of select="concat(n:operation, n:value)"/>
							</xsl:for-each>
                        </fo:block>
                    </fo:table-cell>
				</fo:table-row>
				
				<fo:table-row>
					<fo:table-cell padding="1mm">
                        <fo:block>
							<xsl:text> </xsl:text>
                        </fo:block>
                    </fo:table-cell>
					<fo:table-cell padding="1mm">
                        <fo:block>
							<xsl:text> </xsl:text>
                        </fo:block>
                    </fo:table-cell>
					<fo:table-cell padding="1mm" text-align="center">
                        <fo:block>
                            <xsl:value-of select="n:thirdComp"/>
                        </fo:block>
                    </fo:table-cell>
					<fo:table-cell padding="1mm">
                        <fo:block>
							<xsl:text> </xsl:text>
                        </fo:block>
                    </fo:table-cell>
					<fo:table-cell padding="1mm">
                        <fo:block>
							<xsl:text> </xsl:text>
                        </fo:block>
                    </fo:table-cell>
				</fo:table-row>
				
				<fo:table-row>
					<fo:table-cell padding="1mm">
                        <fo:block>
							<xsl:text> </xsl:text>
                        </fo:block>
                    </fo:table-cell>
					<fo:table-cell padding="1mm">
                        <fo:block>
							<xsl:text> </xsl:text>
                        </fo:block>
                    </fo:table-cell>
					<fo:table-cell padding="1mm" text-align="center">
                        <fo:block>
                            <xsl:text> </xsl:text>
                        </fo:block>
                    </fo:table-cell>
					<fo:table-cell padding="1mm">
                        <fo:block>
							<xsl:text> </xsl:text>
                        </fo:block>
                    </fo:table-cell>
					<fo:table-cell padding="1mm">
                        <fo:block>
							<xsl:text> </xsl:text>
                        </fo:block>
                    </fo:table-cell>
				</fo:table-row>
			</fo:table-body>
			
			</fo:table>
			<xsl:text> </xsl:text>
			</fo:block>
		</xsl:for-each>
	</xsl:template>



</xsl:stylesheet>