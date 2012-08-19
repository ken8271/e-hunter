<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>e-Hunter System/[EH-TLNT-0001]</title>
<script type="text/javascript">
$().ready(function(){
	if('${clearField}' == 'Y'){
		clearInputFields();
	}
});

function clearInputFields(){
	document.getElementById('fromDateDto.year').value='';
	document.getElementById('fromDateDto.month').value='';
	document.getElementById('fromDateDto.day').value='';
	document.getElementById('toDateDto.year').value='';
	document.getElementById('toDateDto.month').value='';
	document.getElementById('toDateDto.day').value='';
	$('#name').val('');
	$('#duty').val('');
	$('#description').val('');
}

function complete(actionFlagStr){
	var actionFlag = document.getElementById('actionFlag');
	if(actionFlag != 'undefined' && actionFlagStr != ""){
		actionFlag.value = actionFlagStr;
	}
	var form = document.getElementById('prjExpForm');
	form.submit();
}

function submitDelete(listName){
	var isSubmit = false;
	
	for( var i = 0; i < document.getElementsByName(listName).length ; i++){ 		
		if(document.getElementsByName(listName).item(i).checked == true){
			isSubmit = true;
			break;
		} 				
	}	

	if(isSubmit == true){
		if(confirm("确认删除被选择的记录？") == true){
			var form = document.getElementById('prjExpsForm');
	        form.submit();
		}
	}else{
		alert('请至少选择一项进行删除！');
	}
}
</script>
</head>
<body>
	<form:form id="prjExpForm" commandName="prjExpDto" action="${ctx}/talent/addProjectExperienceActions.do" method="post">
	    <div style="display: none">
	       <input type="hidden" id="actionFlag" name="actionFlag"/>
	    </div>
		<table border="0" width="99%">
			<tr>
				<td class="pageTitle">人才项目经验填写</td>
			</tr>
			<tr>
				<td><common:errorTable path="prjExpDto"></common:errorTable></td>
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
						<td class="labelColumn">项目名称：<span class="mandatoryField">*</span></td>
						<td>
						   <form:input path="name" cssClass="standardInputText"  /> 
					       <common:errorSign id="name" path="name"></common:errorSign>
						</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr >
						<td class="labelColumn">职责描述：<span class="mandatoryField">*</span></td>
						<td colspan="3">
						  <form:textarea path="duty" htmlEscape="true" rows="3" cols="60"  cssStyle="word-wrap:break-word;height:80px" onkeyup="javascript:if (this.value.length>300){this.value = this.value.substring(0, 300)}" cssClass="standardInputText"/>
						  <common:errorSign id="duty" path="duty"></common:errorSign>
						</td>
					</tr>
					<tr >
						<td class="labelColumn">项目描述：<span class="mandatoryField">*</span></td>
						<td colspan="3">
						   <form:textarea path="description" htmlEscape="true" rows="3" cols="60"  cssStyle="word-wrap:break-word;height:80px" onkeyup="javascript:if (this.value.length>300){this.value = this.value.substring(0, 300)}" cssClass="standardInputText"/>
						   <common:errorSign id="description" path="description"></common:errorSign>
						</td>
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
							<input class="standardButton" type="button" value="添加" onclick="complete('10');" />&nbsp;
							<input class="standardButton" type="button" value="清除" onclick="clearInputFields();" />&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form:form>
	<div class="emptyBlock"></div>
	<form:form id="prjExpsForm" commandName="resumeDto" action="${ctx }/talent/deleteProjectExperience.do">
		<table class="contentTableBody2" cellspacing="1" width="99%">
		   <tr class="contentTableTitle">
		      <td width="10%" align="center">全选</td>
		      <td width="30%">时间</td>
		      <td width="50%">项目名称</td>
		      <td width="10%" align="center">操作</td>
		   </tr>
		   <c:if test="${not empty resumeDto.prjExpDtos }">
		      <c:forEach items="${resumeDto.prjExpDtos }" var="project" varStatus="status">
		         <tr class="contentTableRow">
		            <td><input type="checkbox" name="expsList" value="${status.index }"/>&nbsp;${status.index+1 }</td>
		            <td>
		            <c:out value="${project.fromDateDto.year }" escapeXml="true"/>/
		            <c:out value="${project.fromDateDto.month }" escapeXml="true"/>/
		            <c:out value="${project.fromDateDto.day }" escapeXml="true"/>&nbsp;-&nbsp;
		            <c:out value="${project.toDateDto.year }" escapeXml="true"/>/
		            <c:out value="${project.toDateDto.month }" escapeXml="true"/>/
		            <c:out value="${project.toDateDto.day }" escapeXml="true"/>
		            </td>
		            <td><c:out value="${project.name }" escapeXml="true"></c:out></td>
		            <td align="center"> 
		            <hdiv-c:url value="/talent/preEditProjectExperience.do?_id=${status.index }" var="editPrjExpUrl"></hdiv-c:url>
		            <input class="standardButton" type="button" value="编辑" onclick="location.href='${editPrjExpUrl }'" />&nbsp;
		            </td>
		         </tr>
		      </c:forEach>
		   </c:if>
		</table>
		<div class="emptyBlock"></div>
		<table id="bg2" border="0" width="99%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							 <input class="standardButton" type="button" value="删除" onclick="submitDelete('expsList');" />&nbsp;
							 <input class="standardButton" type="button" value="保存" onclick="complete('6');" />&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>