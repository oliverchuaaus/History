<?xml version="1.0"?>

<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="/">
		<html>
			<body>
				<h2>My Elements</h2>
				<table border="1">
					<tr bgcolor="#9acd32">
						<th>Element</th>
						<th>Attribute1</th>
						<th>Attribute2</th>
					</tr>
					<xsl:for-each select="rootElement/element1">
						<tr>
							<td>
								<xsl:value-of select="." />
							</td>
							<td>
								<xsl:value-of select="@attribute1" />
							</td>
							<td>
								<xsl:value-of select="@attribute2" />
							</td>
						</tr>
					</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>

</xsl:stylesheet>
