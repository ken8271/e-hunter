<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>e-Hunter System/[EH-TLNT-0101]</title>
<script type="text/javascript">
function clearInput(){
	$(':text').val('');
}
</script>
<common:jmesaScript actionFlagStr="90"></common:jmesaScript>
</head>
<body>
<form:form commandName="prjEnquireDto" action="${ctx}/project/projectsSearch.do">
        <div style="display: none">
			<input type="hidden" id="actionFlag" name="actionFlag" />
		</div>
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">猎头项目查询</td>
			</tr>
			<tr>
				<td><common:errorTable path="prjEnquireDto"></common:errorTable></td>
			</tr>
		</table>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td><input class="standardButton" type="submit" value="查询" />&nbsp;
								<input class="standardButton" type="button" value="重置" onclick="clearInput();"/>&nbsp;
								<input class="standardButton" type="button" value="关闭" onclick="location.href='${ctx}/index.do'">
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
				   <td class="labelColumn">创建时间从：</td>
				   <td>
				      <common:inputDate dateYY="fromDateDto.year" dateMON="fromDateDto.month" dateDD="fromDateDto.day" ></common:inputDate>
				      <common:errorSign id="fromDateDto.day" path="fromDateDto.day"></common:errorSign>
				   </td>
				   <td class="labelColumn">至：</td>
				   <td>
				      <common:inputDate dateYY="toDateDto.year" dateMON="toDateDto.month" dateDD="toDateDto.day" ></common:inputDate>
				      <common:errorSign id="toDateDto.day" path="toDateDto.day"></common:errorSign>
				   </td>
			    </tr>
			</tbody>
		</table>
		<div class="emptyBlock"></div>	
		<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
			<tbody>
				<common:standardTableRow />
				<tr>
				   <td class="labelColumn">项目编号：</td>
				   <td><form:input path="systemProjectRefNum" cssClass="standardInputText"></form:input></td>
				   <td class="labelColumn">项目名称：</td>
				   <td><form:input path="projectName" cssClass="standardInputText"></form:input></td>
			    </tr>
			    <tr>
				   <td class="labelColumn">客户编号：</td>
				   <td><form:input path="systemCustRefNum" cssClass="standardInputText"></form:input></td>
				   <td class="labelColumn">客户名称：</td>
				   <td><form:input path="customerName" cssClass="standardInputText"></form:input></td>
			    </tr>
			</tbody>
		</table>
		<div class="emptyBlock"></div>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td><input class="standardButton" type="submit" value="查询" />&nbsp;
							    <input class="standardButton" type="button" value="重置" onclick="clearInput();"/>&nbsp;
								<input class="standardButton" type="button" value="关闭" onclick="location.href='${ctx}/index.do'">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<div>${listOfProject }</div>
</form:form>
</body>
</html>
