<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EHT-0-0001</title>
<script type="text/javascript">
function deleteConirmation(url){
	if(confirm('确认删除该参数?')){
		window.location.href=url;
	}
}
</script>
</head>
<body>
   <hdiv-c:url value="/system/preCreateSystemParameter.do" var="createUrl"></hdiv-c:url>
   <form:form id="enquireForm" commandName="systemParameterPagedCriteria" action="${ctx}/system/listSystemParameters.do" method="post">
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">系统参数管理 - 参数列表</td>
			</tr>
		</table>
		<table border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							<input class="standardButton" type="button" value="打印" />&nbsp;
							<input class="standardButton" type="button" value="新增参数" onclick="location.href='${createUrl}'"/>&nbsp;
							<input class="standardButton" type="button" value="结束" onclick="location.href='${ctx}/index.do'"/>&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<div align="center">
	       <common:jmesaScript action=""></common:jmesaScript>
	    </div>
		<div>
		    ${listOfSystemParameter}
		</div>
		<div class="emptyBlock"></div>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							<input class="standardButton" type="button" value="打印" />&nbsp;
							<input class="standardButton" type="button" value="新增参数" onclick="location.href='${createUrl}'"/>&nbsp;
							<input class="standardButton" type="button" value="结束" onclick="location.href='${ctx}/index.do'"/>&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
	</form:form>
</body>
</html>