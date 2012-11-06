<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EHT-0-0001</title>
</head>
<body>
    <hdiv-c:url value="/system/searchCodetables.do" var="backUrl"></hdiv-c:url>
    <hdiv-c:url value="/system/preEditCodetable.do?_id=${codetableDto.id }" var="editUrl"></hdiv-c:url>
	<table border="0" width="100%">
		<tr>
			<td class="pageTitle">代码表维护</td>
		</tr>
	</table>
	<table border="0" width="100%">
		<tr>
			<td class="functionMenuBar">
				<table align="right" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
						    <input class="standardButton" type="button" value="打印" />&nbsp;
						    <input class="standardButton" type="button" value="修改" onclick="location.href='${editUrl}'"/>&nbsp;
						    <input class="standardButton" type="button" value="返回" onclick="location.href='${backUrl}'"/>&nbsp;
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<div class="emptyBlock"></div>
	<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
		<tbody>
			<common:standardTableRow />
			<tr>
				<td class="labelColumn">代码表编号：</td>
				<td colspan="3"><c:out value="${codetableDto.id }" escapeXml="true"></c:out></td>
			</tr>
			<tr>
				<td class="labelColumn">代码表名称：</td>
				<td colspan="3"><c:out value="${codetableDto.name }" escapeXml="true"></c:out></td>
			</tr>
			<tr>
				<td class="labelColumn">数据库表名称：</td>
				<td colspan="3"><c:out value="${codetableDto.reference }" escapeXml="true"></c:out></td>
			</tr>
			<tr>
				<td class="labelColumn">代码表描述：</td>
				<td colspan="3"><c:out value="${codetableDto.description }" escapeXml="true"></c:out></td>
			</tr>
			<tr>
				<td class="labelColumn">是否启用：</td>
				<td colspan="3"><c:out value="${codetableDto.activeIndicator }" escapeXml="true"></c:out></td>
			</tr>
		</tbody>
	</table>
	<div class="emptyBlock"></div>
	<table id="bg2" border="0" width="100%">
		<tr>
			<td class="functionMenuBar">
				<table align="right" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
						   <input class="standardButton" type="button" value="打印" />&nbsp;
						    <input class="standardButton" type="button" value="修改" onclick="location.href='${editUrl}'"/>&nbsp;
						    <input class="standardButton" type="button" value="返回" onclick="location.href='${backUrl}'"/>&nbsp;
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>