<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${ctx}/style/verify.css" type="text/css" />
<title>e-Hunter System/[EH-PRJ-0001]</title>
</head>
<body>
	<table border="0" width="100%">
		<tr>
			<td class="pageTitle">批量导入-资料确认</td>
		</tr>
	</table>
	<table id="bg2" border="0" width="100%">
		<tr>
			<td class="functionMenuBar">
				<table align="right" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td><input class="standardButton" type="submit" value="确认" />&nbsp;
							<input class="standardButton" type="button" value="返回"
							onclick="location.href='${backUrl}'" /></td>
					</tr>
				</table></td>
		</tr>
	</table>
	<div class="emptyBlock"></div>
	<table class="contentTableBody2" cellspacing="1" width="100%">
		<tr class="contentTableTitle">
			<td align="center" width="15%">中文名</td>
			<td align="center" width="20%">英文名</td>
			<td align="center" width="10%">手机</td>
			<td align="center" width="40%">现任职公司</td>
			<td align="center" width="10%">职位</td>
			<td align="center" width="5%">现居地</td>
		</tr>
		<c:forEach items="${listOfTalent }" var="tlnt">
			<tr class="contentTableRow">
				<td align="center"><span class="textCn8"><c:out
							value="${tlnt.cnName }" escapeXml="true"></c:out>
				</span>
				</td>
				<td align="center"><span class="textCn8"><c:out
							value="${tlnt.enName }" escapeXml="true"></c:out>
				</span></td>
				<td align="center"><span class="textCn8"><c:out
							value="${tlnt.mobilePhoneDto1.phoneNumber }"></c:out>
				</span>
				</td>
				<td align="center"></td>
				<td align="center"></td>
				<td align="center"><c:out
							value="${tlnt.nowLivePlace }"></c:out></td>
			</tr>
		</c:forEach>
	</table>
	<div class="emptyBlock"></div>
	<table id="bg2" border="0" width="100%">
		<tr>
			<td class="functionMenuBar">
				<table align="right" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td><input class="standardButton" type="submit" value="确认" />&nbsp;
							<input class="standardButton" type="button" value="返回"
							onclick="location.href='${backUrl}'" /></td>
					</tr>
				</table></td>
		</tr>
	</table>
</body>
</html>