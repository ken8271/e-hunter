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
	$('#languageCategory').val('');
	$('#ablitityOfRW').val('');
	$('#ablitityOfLS').val('');
}

function complete(actionFlagStr){
	var actionFlag = document.getElementById('actionFlag');
	if(actionFlag != 'undefined' && actionFlagStr != ""){
		actionFlag.value = actionFlagStr;
	}
	var form = document.getElementById('langForm');
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
			var form = document.getElementById('langsForm');
	        form.submit();
		}
	}else{
		alert('请至少选择一项进行删除！');
	}
}
</script>
</head>
<body>
	<form:form id="langForm" commandName="languageDto" action="${ctx}/talent/addLanguageAbilityActions.do" method="post">
	    <div style="display: none">
	       <input type="hidden" id="actionFlag" name="actionFlag"/>
	    </div>
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">人才项目经验填写</td>
			</tr>
			<tr>
				<td><common:errorTable path="languageDto"></common:errorTable></td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<div class="contentTableBody">
			<div style="height:5px"></div>
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%" >
				<tbody>
				    <common:standardTableRow />
					<tr>
						<td class="labelColumn">外语语种：<span class="mandatoryField">*</span></td>
						<td>
						<form:select path="languageCategory" cssClass="standardSelect" >
						      <form:option value="" label="--- 请选择  ---"></form:option>
						      <c:forEach items="${listOfLanguageCategory }" var="languageCategory">
						         <form:option value="${languageCategory.code }" label="${languageCategory.displayName }"></form:option>
						      </c:forEach>
						</form:select>
						<common:errorSign id="languageCategory" path="languageCategory"></common:errorSign>
						</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				    <tr >
						<td class="labelColumn">读写能力：<span class="mandatoryField">*</span></td>
						<td>
						   <form:select path="ablitityOfRW" cssClass="standardSelect" >
						      <form:option value="" label="--- 请选择  ---"></form:option>
						      <c:forEach items="${listOfSkillLevel }" var="level">
						         <form:option value="${level.code }" label="${level.displayName }"></form:option>
						      </c:forEach>
						   </form:select>
					       <common:errorSign id="ablitityOfRW" path="ablitityOfRW"></common:errorSign>
						</td>
						<td class="labelColumn">听说能力：<span class="mandatoryField">*</span></td>
						<td>
						   <form:select path="ablitityOfLS" cssClass="standardSelect" >
						      <form:option value="" label="--- 请选择  ---"></form:option>
						      <c:forEach items="${listOfSkillLevel }" var="level">
						         <form:option value="${level.code }" label="${level.displayName }"></form:option>
						      </c:forEach>
						   </form:select>
					       <common:errorSign id="ablitityOfLS" path="ablitityOfLS"></common:errorSign>
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
							<input class="standardButton" type="button" value="添加" onclick="complete('12');" />&nbsp;
							<input class="standardButton" type="button" value="清除" onclick="clearInputFields();" />&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form:form>
	<div class="emptyBlock"></div>
	<form:form id="langsForm" commandName="resumeDto" action="${ctx }/talent/deleteLanguageAbility.do">
		<table class="contentTableBody2" cellspacing="1" width="100%">
		   <tr class="contentTableTitle">
		      <td width="10%" align="center">全选</td>
		      <td width="40%">外语语种</td>
		      <td width="20%">读写能力</td>
		      <td width="20%">听说能力</td>
		      <td width="10%" align="center">操作</td>
		   </tr>
		   <c:if test="${not empty resumeDto.languageDtos }">
		      <c:forEach items="${resumeDto.languageDtos }" var="language" varStatus="status">
		         <tr class="contentTableRow">
		            <td><input type="checkbox" name="expsList" value="${status.index }"/>&nbsp;${status.index+1 }</td>
		            <td><c:out value="${language.languageCategoryDto.displayName }" escapeXml="true"></c:out> </td>
		            <td><c:out value="${language.ablitityOfRWDto.displayName }" escapeXml="true"></c:out></td>
		            <td><c:out value="${language.ablitityOfLSDto.displayName }" escapeXml="true"></c:out></td>
		            <td align="center"> 
		            <hdiv-c:url value="/talent/preEditLanguageAbility.do?_id=${status.index }" var="editLangAbilityUrl"></hdiv-c:url>
		            <input class="standardButton" type="button" value="编辑" onclick="location.href='${editLangAbilityUrl }'" />&nbsp;
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