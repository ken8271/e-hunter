<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>e-Hunter System/[EH-PRJ-0001]</title>
<script type="text/javascript">
$().ready(function(){
	if('${enquireDto.searchIndicator}' == 'Y'){
		$('#searchIndicator').attr('checked',true);
	}
	setExtraCriteria(document.getElementById('searchIndicator'));
});

function setExtraCriteria(c){
	if(c.checked == true){
		$('#ageFrom').val('${projectDto.postRequireDto.ageFromStr}');
		$('#ageTo').val('${projectDto.postRequireDto.ageToStr}');
		$('#gender').val('${projectDto.postRequireDto.gender}');
		$('#majorCategory').val('${projectDto.postRequireDto.majorCategory}');
		$('#workExperience').val('${projectDto.postRequireDto.workExperienceStr}');
		$('#degree').val('${projectDto.postRequireDto.degree}');
		if('${projectDto.postRequireDto.ftEduIndicator}' == 'Y'){
			$('#ftEduIndicator_Y').attr('checked' , true);
		}else {
			$('#ftEduIndicator_N').attr('checked' , true);
		}
		$('#ageFrom').attr('disabled' , true);
		$('#ageTo').attr('disabled' , true);
		$('#gender').attr('disabled' , true);
		$('#majorCategory').attr('disabled' , true);
		$('#workExperience').attr('disabled' , true);
		$('#degree').attr('disabled' , true);
		$('#majorCategory').attr('disabled' , true);
		$('#ftEduIndicator_Y').attr('disabled' , true);
		$('#ftEduIndicator_N').attr('disabled' , true);
	}else {
		$('#ageFrom').val('');
		$('#ageTo').val('');
		$('#gender').val('');
		$('#majorCategory').val('');
		$('#workExperience').val('');
		$('#degree').val('');
		$('#ftEduIndicator_N').attr('checked' , true);
		
		$('#ageFrom').attr('disabled' , false);
		$('#ageTo').attr('disabled' , false);
		$('#gender').attr('disabled' , false);
		$('#majorCategory').attr('disabled' , false);
		$('#workExperience').attr('disabled' , false);
		$('#degree').attr('disabled' , false);
		$('#majorCategory').attr('disabled' , false);
		$('#ftEduIndicator_Y').attr('disabled' , false);
		$('#ftEduIndicator_N').attr('disabled' , false);
	}
}

function checkCandidateSelection(){
	var selectCount = 0;
	var selections = document.getElementsByName("jmesaDto.select");
	if(selections != 'undefined'){
		for(var i=0 ; i<selections.length ; i++){
			if(selections[i].checked){
				selectCount++;
			}
		}
	}
	if(selectCount==0){
		alert('请至少选择一名候选人才!');
		return;
	}
	
	submitForm('5');
}

function submitForm(actionFlagStr){
	var actionFlag = document.getElementById('actionFlag');
	if(actionFlag != 'undefined' && actionFlagStr != ""){
		actionFlag.value = actionFlagStr;
	}
	document.forms[0].submit();
}
</script>
<common:jmesaScript actionFlagStr="90"></common:jmesaScript>
</head>
<body>
    <hdiv-c:url value="/project/viewCandidateRepository.do" var="backUrl"></hdiv-c:url>
    <hdiv-c:url value="/customer/pop/viewCustomerDetail.do?_id=${projectDto.customerDto.systemCustRefNum }" var="viewCustomerUrl"></hdiv-c:url>
    <hdiv-c:url value="/project/pop/viewProjectDetail.do?_id=${projectDto.systemProjectRefNum }" var="viewProjectUrl"></hdiv-c:url>
	<form:form commandName="talentEnquireDto" action="${ctx}/project/appendCandidateRepositoryActions.do" method="post">
		<div style="display: none">
			<input type="hidden" id="actionFlag" name="actionFlag" />
		</div>
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">项目人才库-添加候选人才</td>
			</tr>
		</table>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							    <input class="standardButton" type="button" value="查询" onclick="submitForm('1')"/>&nbsp;
								<input class="standardButton" type="reset" value="重置">&nbsp;
								<c:if test="${type == '1'}">
								   <input class="standardButton" type="button" value="返回" onclick="location.href='${backUrl}'" />
								</c:if>
								<c:if test="${type == '2' }">
								   <input class="standardButton" type="button" value="结束" onclick="location.href='${ctx}/index.do'" />
								</c:if>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<div class="contentTableBody">
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
				<tbody>
					<common:standardTableRow />
					<tr>
						<td class="labelColumn">项目编号：</td>
						<td colspan="3">
						   <c:out value="${projectDto.systemProjectRefNum }" escapeXml="true"></c:out>&nbsp;&nbsp;&nbsp;&nbsp; 
						   <common:tips url="${viewProjectUrl }" title="查看项目资料"></common:tips>
						</td>
					</tr>
					<tr>
					    <td class="labelColumn">客户公司：</td>
						<td colspan="3">
						     <c:out value="${projectDto.customerDto.fullName }" escapeXml="true"></c:out>&nbsp;&nbsp;&nbsp;&nbsp; 
						     <common:tips url="${viewCustomerUrl }" title="查看客户公司资料"></common:tips>
						</td>
					</tr>
					<tr>
						<td class="labelColumn">需求职位：</td>
						<td colspan="3">
						     <c:out value="${projectDto.postDescDto.positionName}" escapeXml="true"></c:out>&nbsp;&nbsp;&nbsp;&nbsp; 
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="emptyBlock"></div>
		<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
			<tbody>
				<common:standardTableRow />
				<tr>
					<td class="labelColumn">人才编号：</td>
					<td><form:input path="talentID" cssClass="standardInputText"></form:input></td>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td class="labelColumn">中文名/英文名：</td>
					<td><form:input path="name" cssClass="standardInputText"></form:input></td>
					<td colspan="2"></td>
				</tr>
			</tbody>
		</table>
		<div class="emptyBlock"></div>
		&nbsp;<input id="searchIndicator" type="checkbox" name="searchIndicator" value='<form:cipher value="Y" parameter="searchIndicator"/>' onclick="setExtraCriteria(this)"/>&nbsp;只接受符合该项目职位基本素质要求的人才
		<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
			<tbody>
				<common:standardTableRow />
				<tr>
					<td class="labelColumn">年龄：</td>
					<td>
					    <form:select path="ageFrom" cssClass="standardSelectNoWidth">
							<form:option value="" label="不限"></form:option>
							<c:forEach begin="20" end="50" step="5" var="age">
								<form:option value="${age }" label="${age }"></form:option>
							</c:forEach>
						</form:select> &nbsp;&nbsp;-&nbsp;&nbsp; 
						<form:select path="ageTo" cssClass="standardSelectNoWidth">
							<form:option value="" label="不限"></form:option>
							<c:forEach begin="20" end="50" step="5" var="age">
								<form:option value="${age }" label="${age }"></form:option>
							</c:forEach>
						</form:select>
					</td>
					<td class="labelColumn">性别：</td>
					<td>
					    <form:select path="gender" cssClass="standardSelectNoWidth">
							<form:option value="" label="不限"></form:option>
							<form:option value="M" label="男"></form:option>
							<form:option value="F" label="女"></form:option>
						</form:select>
					</td>
				</tr>
				<tr>
					<td class="labelColumn">专业：</td>
					<td>
					    <form:select path="majorCategory" cssClass="standardSelect">
							<form:option value="" label="不限"></form:option>
							<c:forEach items="${listOfSubjectType}" var="subjectType">
								<form:option value="${subjectType.typeCode }" label="${subjectType.displayName }"></form:option>
							</c:forEach>
						</form:select>
					</td>
					<td class="labelColumn">总工作年限：</td>
					<td>
					    <form:input path="workExperience" cssClass="standardInputTextNoWidth" maxlength="2" size="2" />&nbsp;年以上
						<common:errorSign id="workExperience" path="workExperience"></common:errorSign>
					</td>
				</tr>
				<tr>
					<td class="labelColumn">学历：</td>
					<td>
					    <form:select path="degree" cssClass="standardSelect">
							<form:option value="" label="不限"></form:option>
							<c:forEach items="${listOfDegree }" var="dgre">
								<form:option value="${dgre.degreeCode }" label="${dgre.displayName }"></form:option>
							</c:forEach>
						</form:select> <common:errorSign id="degree" path="degree"></common:errorSign>
					</td>
					<td colspan="2"></td>
				</tr>
			</tbody>
		</table>
		<div class="emptyBlock"></div>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td><input class="standardButton" type="button" value="查询" onclick="submitForm(1);"/>&nbsp;
								<input class="standardButton" type="reset" value="重置">&nbsp;
								<c:if test="${type == '1'}">
								   <input class="standardButton" type="button" value="返回" onclick="location.href='${backUrl}'" />
								</c:if>
								<c:if test="${type == '2' }">
								   <input class="standardButton" type="button" value="结束" onclick="location.href='${ctx}/index.do'" />
								</c:if>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<div>${listOfTalent}</div>
		<div class="emptyBlock"></div>
		<c:if test="${not empty listOfTalent}">
		   <table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
								<input class="standardButton" type="button" value="添加到项目人才库" onclick="checkCandidateSelection();">&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
		   </table>
		</c:if>
	</form:form>
</body>
</html>