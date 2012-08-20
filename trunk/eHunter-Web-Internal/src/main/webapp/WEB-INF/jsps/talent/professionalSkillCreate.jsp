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
	showOrHideInput();
});

function showOrHideInput(){
	var skillCategory = document.getElementById('categoryCode');
	if(skillCategory != null && skillCategory.selectedIndex != 0){
		$('#skillName').show();
	}else {
		$('#skillName').hide();
	}
}

function clearInputFields(){
	$('#categoryCode').val('');
	$('#skillName').val('');
	$('#duration').val('');
	$('#levelCode').val('');
}

function complete(actionFlagStr){
	var actionFlag = document.getElementById('actionFlag');
	if(actionFlag != 'undefined' && actionFlagStr != ""){
		actionFlag.value = actionFlagStr;
	}
	var form = document.getElementById('proSklForm');
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
			var form = document.getElementById('proSklsForm');
	        form.submit();
		}
	}else{
		alert('请至少选择一项进行删除！');
	}
}
</script>
</head>
<body>
	<form:form id="proSklForm" commandName="skillDto" action="${ctx}/talent/addProfessionalSkillActions.do" method="post">
	    <div style="display: none">
	       <input type="hidden" id="actionFlag" name="actionFlag"/>
	    </div>
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">专业技能填写</td>
			</tr>
			<tr>
				<td><common:errorTable path="skillDto"></common:errorTable></td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<div class="contentTableBody">
			<div style="height:5px"></div>
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%" >
				<tbody>
				    <common:standardTableRow />
					<tr>
						<td class="labelColumn">技能类型/名称：<span class="mandatoryField">*</span></td>
						<td>
						<form:select path="categoryCode" cssClass="standardSelect" onchange="showOrHideInput();">
						      <form:option value="" label="--- 请选择  ---"></form:option>
						      <c:forEach items="${listOfSkillCategory }" var="skillCategory">
						         <form:option value="${skillCategory.code }" label="${skillCategory.displayName }"></form:option>
						      </c:forEach>
						</form:select>
						<common:errorSign id="categoryCode" path="categoryCode"></common:errorSign>
						</td>
						<td>
						   <form:input path="skillName" cssClass="standardInputText"  cssStyle="display:none"/> 
						   <common:errorSign id="skillName" path="skillName"></common:errorSign>
					    </td>
					    <td>&nbsp;</td>
					</tr>
				    <tr >
						<td class="labelColumn">使用时间：<span class="mandatoryField">*</span></td>
						<td>
						   <form:input path="duration" cssClass="standardInputTextNoWidth" maxlength="3" size="3"/>&nbsp;月
					       <common:errorSign id="duration" path="duration"></common:errorSign>
						</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr >
						<td class="labelColumn">掌握程度：<span class="mandatoryField">*</span></td>
						<td>
						  <form:select path="levelCode" cssClass="standardSelect">
						      <form:option value="" label="--- 请选择  ---"></form:option>
						      <c:forEach items="${listOfSkillLevel }" var="skillLevel">
						         <form:option value="${skillLevel.code }" label="${skillLevel.displayName }"></form:option>
						      </c:forEach>
						  </form:select> 
						  <common:errorSign id="levelCode" path="levelCode"></common:errorSign>
						</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
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
							<input class="standardButton" type="button" value="添加" onclick="complete('11');" />&nbsp;
							<input class="standardButton" type="button" value="清除" onclick="clearInputFields();" />&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form:form>
	<div class="emptyBlock"></div>
	<form:form id="proSklsForm" commandName="resumeDto" action="${ctx }/talent/deleteProfessionalSkill.do">
		<table class="contentTableBody2" cellspacing="1" width="100%">
		   <tr class="contentTableTitle">
		      <td width="10%" align="center">全选</td>
		      <td width="40%">技能名称</td>
		      <td width="20%">使用时间(月)</td>
		      <td width="20%">掌握程度</td>
		      <td width="10%" align="center">操作</td>
		   </tr>
		   <c:if test="${not empty resumeDto.skillDtos }">
		      <c:forEach items="${resumeDto.skillDtos }" var="skill" varStatus="status">
		         <tr class="contentTableRow">
		            <td><input type="checkbox" name="expsList" value="${status.index }"/>&nbsp;${status.index+1 }</td>
		            <td>
		            <c:out value="${skill.skillName }" escapeXml="true"/>
		            (<c:out value="${skill.categoryDto.displayName }" escapeXml="true"/>)
		            </td>
		            <td><c:out value="${skill.duration }" escapeXml="true"></c:out></td>
		            <td><c:out value="${skill.levelDto.displayName }" escapeXml="true"></c:out></td>
		            <td align="center"> 
		            <hdiv-c:url value="/talent/preEditProfessionalSkill.do?_id=${status.index }" var="editProSklUrl"></hdiv-c:url>
		            <input class="standardButton" type="button" value="编辑" onclick="location.href='${editProSklUrl }'" />&nbsp;
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