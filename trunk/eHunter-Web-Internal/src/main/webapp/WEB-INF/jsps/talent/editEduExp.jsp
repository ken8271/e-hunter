<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>e-Hunter System/[EH-TLNT-0001]</title>
<hdiv-c:url value="/talent/loadSubjects.do" var="loadSubjectsUrl"></hdiv-c:url>
<script type="text/javascript">
$().ready(function(){
	loadSubjects();
});

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
				
				$('#major').val('${eduExpDto.major}');
			},
			error:function(){
				$().progressDialog.hideDialog("");
				alert('系统错误');
			}
		});
	}else{
		var major = document.getElementById("major");
		clearSelector(major);
		major.options[major.length] = new Option("--- 请选择 ---", "");
		$('#major').val('');
	}
}

function clearSelector(selector){
	while(selector.childNodes.length>0){
		selector.removeChild(selector.childNodes[0]);
	}
}

function submitForm(){
	var form = document.getElementById('eduExpForm');
	form.submit();
}
</script>
</head>
<body>
	<form:form id="eduExpForm" commandName="eduExpDto" action="${ctx}/talent/completeEditEduExp.do" method="post">
	     <hdiv-c:url value="/talent/backToFillEduExp.do" var="backUrl"></hdiv-c:url>
		<table border="0" width="99%">
			<tr>
				<td class="pageTitle">人才教育经历填写</td>
			</tr>
			<tr>
				<td><common:errorTable path="eduExpDto"></common:errorTable></td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<table id="bg2" border="0" width="99%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							<div id="buttonArea">
							   <div class="buttonmenubox_R">
							      <a class="button" href="#" style="white-space:nowrap;" onclick="submitForm();">确认</a>
							      <a class="button" href="${backUrl }" style="white-space:nowrap;">返回</a>
							   </div>
							</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<div class="contentTableBody">
			<div style="height:5px"></div>
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="99%" >
				<tbody>
				    <common:standardTableRow />
					<tr>
						<td class="labelColumn">时间：<span class="mandatoryField">*</span></td>
						<td>
						<common:inputDate dateYY="fromDateDto.year" dateMON="fromDateDto.month" dateDD="fromDateDto.day" ></common:inputDate>
						<common:errorSign id="fromDateDto.day" path="fromDateDto.day"></common:errorSign>
						</td>
						<td class="labelColumn">至：<span class="mandatoryField">*</span></td>
						<td>
						<common:inputDate dateYY="toDateDto.year" dateMON="toDateDto.month" dateDD="toDateDto.day" ></common:inputDate>
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
						   <form:select id="majorSelector" path="majorType" cssClass="standardSelect" onchange="loadSubjects();">
						      <form:option value="" label="--- 请选择 ---"></form:option>
						      <c:forEach items="${listOfSubjectType}" var="subjectType">
						          <form:option value="${subjectType.typeCode }" label="${subjectType.displayName }"></form:option>
						      </c:forEach>
						   </form:select>
						</td>
						<td>
						   <form:select path="major" cssClass="standardSelect">
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
		<table id="bg2" border="0" width="99%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							<div id="buttonArea">
							   <div class="buttonmenubox_R">
							      <a class="button" href="#" style="white-space:nowrap;" onclick="submitForm();">确认</a>
							      <a class="button" href="${backUrl }" style="white-space:nowrap;" >返回</a>
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
</body>
</html>