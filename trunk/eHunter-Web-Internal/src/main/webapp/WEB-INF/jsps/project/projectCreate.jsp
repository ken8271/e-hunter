<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>e-Hunter System/[EH-PRJ-0001]</title>
<script type="text/javascript">
function setOverlayDimension (){
	 var overlay = document.getElementById('fade');
	 var _11 = AJS.getWindowSize();
		if (AJS.isMozilla() || AJS.isOpera()) {
			AJS.setWidth(overlay, "100%");
		} else {
			AJS.setWidth(overlay, _11.w);
		}
		var _12 = Math.max(AJS.getScrollTop() + _11.h, AJS.getScrollTop()
				+ this.height);
		if (_12 < AJS.getScrollTop()) {
			AJS.setHeight(overlay, _12);
		} else {
			AJS.setHeight(overlay, AJS.getScrollTop() + _11.h);
		}
}

function showAllObject(){
	var e = document.getElementsByTagName('select'); 
	if(e == null){
		return;
	}
	for(var i=0;i<e.length;i++){
		e[i].style.display = "block";
	}
}

function popUpFrame(lightDivId, fadeDivId) {
	document.getElementById(lightDivId).style.display = 'block';
	document.getElementById(fadeDivId).style.display = 'block'
}

function popUpSelector(){
	setOverlayDimension('fade');	
	popUpFrame('light','fade');
}
</script>
</head>
<body>
	<form:form commandName="projectDto" action="${ctx}/project/saveProjectInfo.do" method="post">
	    <div style="display: none">
	       <input type="hidden" id="actionFlag" name="actionFlag" />
	    </div>
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">创建猎头项目</td>
			</tr>
			<tr>
				<td><common:errorTable path="projectDto"></common:errorTable></td>
			</tr>
		</table>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>							   
							   <input class="standardButton" type="button" value="下一步" onclick="popUpSelector();" />&nbsp;
							   <input class="standardButton" type="reset" value="重置">&nbsp;
							   <input class="standardButton" type="button" value="结束">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<table width="100%">
			<tr>
				<td width="10%"><font face="Arial" size="2"><b>第一部分：</b></font></td>
				<td width="90%"><font face="Arial" size="2"><b>猎头项目信息</b></font></td>
			</tr>
		</table>
		<div class="contentTableBody">
			<div style="height:5px"></div>
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%" >
				<tbody>
				    <common:standardTableRow />
					<tr>
						<td class="labelColumn">项目名称：<span class="mandatoryField">*</span></td>
						<td>
						   <form:input path="projectName" cssClass="standardInputText" /> 
						</td>
						<td class="labelColumn">项目负责人：<span class="mandatoryField">*</span></td>
						<td>
						  <c:out value="${projectDto.adviserDto.cnName }" escapeXml="true"></c:out>
					    </td>
					</tr>
				</tbody>
			</table>			
		</div>
		<div class="emptyBlock"></div>
		<table width="100%">
			<tr>
				<td width="10%"><font face="Arial" size="2"><b>第二部分：</b></font></td>
				<td width="90%"><font face="Arial" size="2"><b>客户公司信息</b></font></td>
			</tr>
		</table>
		<table class="standardTableForm" border="1" cellspacing="0"
				cellpadding="0" width="100%">
				<tbody>
				    <common:standardTableRow />
					<tr>
						<td class="labelColumn">客户编号：<span class="mandatoryField">*</span></td>
						<td>
						   <div class="search">
						      <form:input path="customerDto.systemCustRefNum" cssStyle="width:173px;height: 25px"/>
						      <a class="searchBtn" onclick="popUpSelector();" title="搜索按鈕">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
						   </div>
						   <common:errorSign path="customerDto.systemCustRefNum" id="customerDto.systemCustRefNum"></common:errorSign>
						</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</tbody>
		</table>
		<jsp:include page="customerSelector_pop.jsp"></jsp:include>
		<div class="emptyBlock"></div>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							   <input class="standardButton" type="button" value="下一步" onclick="popUpSelector();" />&nbsp;
							   <input class="standardButton" type="reset" value="重置">&nbsp;
							   <input class="standardButton" type="button" value="结束">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>