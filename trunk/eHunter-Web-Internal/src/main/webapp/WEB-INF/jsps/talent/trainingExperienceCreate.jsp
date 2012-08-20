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
	$('#organization').val('');
	$('#address').val('');
	$('#course').val('');
	$('#cert').val('');
	$('#description').val('');
}

function complete(actionFlagStr){
	var actionFlag = document.getElementById('actionFlag');
	if(actionFlag != 'undefined' && actionFlagStr != ""){
		actionFlag.value = actionFlagStr;
	}
	var form = document.getElementById('trnExpForm');
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
			var form = document.getElementById('trnExpsForm');
	        form.submit();
		}
	}else{
		alert('请至少选择一项进行删除！');
	}
}
</script>
</head>
<body>
	<form:form id="trnExpForm" commandName="trnExpDto" action="${ctx}/talent/addTrainingExperienceActions.do" method="post">
	    <div style="display: none">
	       <input type="hidden" id="actionFlag" name="actionFlag"/>
	    </div>
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">培训经历填写</td>
			</tr>
			<tr>
				<td><common:errorTable path="trnExpDto"></common:errorTable></td>
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
						<td class="labelColumn">培训机构：<span class="mandatoryField">*</span></td>
						<td colspan="2">
						   <form:input path="organization" cssClass="standardInputText"  /> 
					       <common:errorSign id="organization" path="organization"></common:errorSign>
						</td>
						<td>&nbsp;</td>
					</tr>
					<tr >
						<td class="labelColumn">培训地点：</td>
						<td colspan="2">
						  <form:input path="address" cssClass="standardInputText"  /> 
						  <common:errorSign id="address" path="address"></common:errorSign>
						</td>
						<td>&nbsp;</td>
					</tr>
					<tr >
						<td class="labelColumn">培训课程：<span class="mandatoryField">*</span></td>
						<td colspan="2">
						  <form:input path="course" cssClass="standardInputText"  /> 
						  <common:errorSign id="course" path="course"></common:errorSign>
						</td>
						<td>&nbsp;</td>
					</tr>
					<tr >
						<td class="labelColumn">获得证书：</td>
						<td colspan="2">
						  <form:input path="cert" cssClass="standardInputText"  /> 
						  <common:errorSign id="cert" path="cert"></common:errorSign>
						</td>
						<td>&nbsp;</td>
					</tr>
					<tr >
						<td class="labelColumn">详细描述：</td>
						<td colspan="3">
						   <form:textarea path="description" htmlEscape="true" rows="3" cols="60"  cssStyle="word-wrap:break-word;height:80px" onkeyup="javascript:if (this.value.length>300){this.value = this.value.substring(0, 100)}" cssClass="standardInputText"/>
						   <common:errorSign id="description" path="description"></common:errorSign>
						</td>
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
							<input class="standardButton" type="button" value="添加" onclick="complete('9');" />&nbsp;
							<input class="standardButton" type="button" value="清除" onclick="clearInputFields();" />&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form:form>
	<div class="emptyBlock"></div>
	<form:form id="trnExpsForm" commandName="resumeDto" action="${ctx }/talent/deleteTrainingExperience.do">
		<table class="contentTableBody2" cellspacing="1" width="100%">
		   <tr class="contentTableTitle">
		      <td width="10%" align="center">全选</td>
		      <td width="20%">时间</td>
		      <td width="30%">培训机构</td>
		      <td width="30%">培训课程</td>
		      <td width="10%" align="center">操作</td>
		   </tr>
		   <c:if test="${not empty resumeDto.trnExpDtos }">
		      <c:forEach items="${resumeDto.trnExpDtos }" var="training" varStatus="status">
		         <tr class="contentTableRow">
		            <td><input type="checkbox" name="expsList" value="${status.index }"/>&nbsp;${status.index+1 }</td>
		            <td>
		            <c:out value="${training.fromDateDto.year }" escapeXml="true"/>/
		            <c:out value="${training.fromDateDto.month }" escapeXml="true"/>/
		            <c:out value="${training.fromDateDto.day }" escapeXml="true"/>&nbsp;-&nbsp;
		            <c:out value="${training.toDateDto.year }" escapeXml="true"/>/
		            <c:out value="${training.toDateDto.month }" escapeXml="true"/>/
		            <c:out value="${training.toDateDto.day }" escapeXml="true"/>
		            </td>
		            <td><c:out value="${training.organization }" escapeXml="true"></c:out></td>
		            <td><c:out value="${training.course }" escapeXml="true"></c:out></td>
		            <td align="center"> 
		            <hdiv-c:url value="/talent/preEditTrainingExperience.do?_id=${status.index }" var="editTrnExpUrl"></hdiv-c:url>
		            <input class="standardButton" type="button" value="编辑" onclick="location.href='${editTrnExpUrl }'" />&nbsp;
		            </td>
		         </tr>
		      </c:forEach>
		   </c:if>
		</table>
		<div class="emptyBlock"></div>
		<table id="bg2" border="0" width="100%">
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