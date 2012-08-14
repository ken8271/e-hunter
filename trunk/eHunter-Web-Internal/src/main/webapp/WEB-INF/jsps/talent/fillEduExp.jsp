<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>e-Hunter System/[EH-TLNT-0001]</title>
<hdiv-c:url value="/talent/loadSubjects.do" var="loadSubjectsUrl"></hdiv-c:url>
<script type="text/javascript">
function loadSubjects(){
	var majorSelector = document.getElementById("majorSelector");
	if(majorSelector != null && majorSelector.selectedIndex != 0){
		$().progressDialog.showDialog("");
		var _id = majorSelector.options[majorSelector.selectedIndex].value;
		$.ajax({
			type:'post',
			url:'${loadSubjectsUrl}',
			dataType:'xml',
			data:{'_id':_id},
			success:function(xml){	
				$().progressDialog.hideDialog("");
				var major = document.getElementById("major");
				clearSelector(major);
				major.options[major.length] = new Option("--- 请选择 ---", "");
				$(xml).find('subject').each(function(i , element){
					var label = $(this).find("label").text();
					var val = ''+$(this).children("value").text()+'';
					major.options[major.length] = new Option(label, val);
				});
				$('#major').attr('disabled',false);
			},
			error:function(){
				$().progressDialog.hideDialog("");
				$('#major').attr('disabled',true);
				alert('系统错误');
			}
		});
	}else{
		var major = document.getElementById("major");
		clearSelector(major);
		major.options[major.length] = new Option("--- 请选择 ---", "");
		$('#major').attr('disabled',true);
	}
}

function clearSelector(selector){
	while(selector.childNodes.length>0){
		selector.removeChild(selector.childNodes[0]);
	}
}

function complete(actionFlagStr){
	var actionFlag = document.getElementById('actionFlag');
	if(actionFlag != 'undefined' && actionFlagStr != ""){
		actionFlag.value = actionFlagStr;
	}
	var form = document.getElementById('eduExpForm');
	form.submit();
}
</script>
</head>
<body>
	<form:form id="eduExpForm" commandName="eduExpDto" action="${ctx}/talent/addEducationExperience.do" method="post">
	    <div style="display: none">
	       <input type="hidden" id="actionFlag" name="actionFlag"/>
	    </div>
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">人才教育经历填写</td>
			</tr>
			<tr>
				<td><common:errorTable path="eduExpDto"></common:errorTable></td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<div class="contentTableBody">
			<div style="height:5px"></div>
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%" >
				<tbody>
				    <common:standardTableRow />
					<tr>
						<td class="labelColumn">时间：<span class="mandatoryField">*</span></td>
						<td>
						<form:input path="fromDateDto.year" cssClass="standardInputTextNoWidth" maxlength="4" size="4"/>&nbsp;-&nbsp; 
						<form:input path="fromDateDto.month" cssClass="standardInputTextNoWidth" maxlength="2" size="2"/>&nbsp;-&nbsp; 
						<form:input path="fromDateDto.day" cssClass="standardInputTextNoWidth" maxlength="2" size="2"/>
						<common:errorSign id="fromDateDto.day" path="fromDateDto.day"></common:errorSign>
						</td>
						<td class="labelColumn">至：<span class="mandatoryField">*</span></td>
						<td>
						<form:input path="toDateDto.year" cssClass="standardInputTextNoWidth" maxlength="4" size="4"/>&nbsp;-&nbsp; 
						<form:input path="toDateDto.month" cssClass="standardInputTextNoWidth" maxlength="2" size="2"/>&nbsp;-&nbsp; 
						<form:input path="toDateDto.day" cssClass="standardInputTextNoWidth" maxlength="2" size="2"/>
						<common:errorSign id="toDateDto.day" path="toDateDto.day"></common:errorSign>
					    </td>
					</tr>
				    <tr >
						<td class="labelColumn">学校名称：<span class="mandatoryField">*</span></td>
						<td>
						   <form:input path="school" cssClass="standardInputText"  /> 
					       <common:errorSign id="school" path="school"></common:errorSign>
						</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr >
						<td class="labelColumn">专业名称：<span class="mandatoryField">*</span></td>
						<td>
						   <select  class="standardSelect" id="majorSelector" onchange="loadSubjects();">
						   <option value="">--- 请选择 ---</option>
						   <c:forEach items="${listOfSubjectType}" var="subjectType">
						      <option value="${subjectType.typeCode }">${subjectType.displayName }</option>
						   </c:forEach>
						   </select>
						</td>
						<td>
						   <form:select path="major" cssClass="standardSelect" disabled="true">
						      <form:option value="" label="--- 请选择 ---"></form:option>
						   </form:select>
						   <common:errorSign id="major" path="major"></common:errorSign>
						</td>
						<td >&nbsp;</td>
					</tr>
					<tr >
						<td class="labelColumn">学历/学位：<span class="mandatoryField">*</span></td>
						<td>
						   <form:select path="degree" cssClass="standardSelect">
						      <form:option value="" label="--- 请选择 ---"></form:option>
						      <c:forEach items="${listOfDegree}" var="degree">
						         <form:option value="${degree.degreeCode }" label="${degree.displayName }"></form:option>
						      </c:forEach>
						   </form:select>
						   <common:errorSign id="degree" path="degree"></common:errorSign>
						</td>
						<td >&nbsp;</td>
						<td >&nbsp;</td>
					</tr>
				</tbody>
			</table>			
		</div>
		<div class="emptyBlock"></div>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							<div id="buttonArea">
							   <div class="buttonmenubox_R">
							      <a class="button" href="#" style="white-space:nowrap;" onclick="complete('6')">添加</a>
							      <a class="button" href="#" style="white-space:nowrap;">清除</a>
							   </div>
							</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form:form>
	<div class="emptyBlock"></div>
	<form:form id="eduExpsForm" commandName="talentDto" action="/talent/deleteEducationExperience.do">
		<table class="contentTableBody2" cellspacing="1">
		   <tr class="contentTableTitle">
		      <td width="10%" align="center">全选</td>
		      <td width="20%">时间</td>
		      <td width="10%">学校</td>
		      <td width="30%">专业</td>
		      <td width="20%">学位</td>
		      <td width="10%" align="center">操作</td>
		   </tr>
		   <c:if test="${not empty talentDto.eduExpDtos }">
		      <c:forEach items="${talentDto.eduExpDtos }" var="eduExp">
		         <tr class="contentTableRow">
		            <td><input type="checkbox" /></td>
		            <td>
		            <c:out value="${eduExp.fromDateDto.year }" escapeXml="true"/>年
		            <c:out value="${eduExp.fromDateDto.month }" escapeXml="true"/>月
		            <c:out value="${eduExp.fromDateDto.day }" escapeXml="true"/>日 &nbsp;-&nbsp;
		            <c:out value="${eduExp.toDateDto.year }" escapeXml="true"/>年
		            <c:out value="${eduExp.toDateDto.month }" escapeXml="true"/>月
		            <c:out value="${eduExp.toDateDto.day }" escapeXml="true"/>日
		            </td>
		            <td><c:out value="${eduExp.school }" escapeXml="true"></c:out></td>
		            <td><c:out value="${eduExp.major }" escapeXml="true"></c:out></td>
		            <td><c:out value="${eduExp.degree }" escapeXml="true"></c:out></td>
		            <td> <a class="button" href="#" style="white-space:nowrap;">编辑</a></td>
		         </tr>
		      </c:forEach>
		   </c:if>
		</table>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							<div id="buttonArea">
							   <div class="buttonmenubox_R">
							      <a class="button" href="#" style="white-space:nowrap;">删除</a>
							      <a class="button" href="#" style="white-space:nowrap;" onclick="completeInput();">保存</a>
							      <a class="button" href="#" style="white-space:nowrap;">返回</a>
							   </div>
							</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>