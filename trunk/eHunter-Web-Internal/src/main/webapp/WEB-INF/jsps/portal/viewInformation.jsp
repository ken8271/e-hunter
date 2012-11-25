<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EHT-0-0001</title>
</head>
<body>
	<table border="0" width="100%">
		<tr>
			<td class="pageTitle">资讯内容</td>
		</tr>
	</table>
	<table border="0" width="100%">
		<tr>
			<td class="functionMenuBar">
				<table align="right" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
						    <input class="standardButton" type="button" value="结束" onclick="location.href='${ctx}/index.do'"/>&nbsp;
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<div class="emptyBlock"></div>
	<div class="contents"> 
	   <h1>${informationDto.title }</h1> 
	   <div class="article">${informationDto.content }</div>
	</div>
	<div class="emptyBlock"></div>
	<table id="bg2" border="0" width="100%">
		<tr>
			<td class="functionMenuBar">
				<table align="right" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
						    <input class="standardButton" type="button" value="结束" onclick="location.href='${ctx}/index.do'"/>&nbsp;
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>