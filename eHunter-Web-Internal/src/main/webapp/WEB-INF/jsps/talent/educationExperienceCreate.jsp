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
	if('${clearField}' == 'Y'){
		clearInputFields();
	}else if('${clearField}' == 'N'){
		loadSubjects();
	}
});

function clearInputFields(){
	document.getElementById('fromDateDto.year').value='';
	document.getElementById('fromDateDto.month').value='';
	document.getElementById('fromDateDto.day').value='';
	document.getElementById('toDateDto.year').value='';
	document.getElementById('toDateDto.month').value='';
	document.getElementById('toDateDto.day').value='';
	$("#school").val('');
	$("#majorSelector").val('');
	$("#major").val('');
	$("#degree").val('');
}

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
				
				if('${clearField}' == 'N'){
					$("#major").val('${eduExpDto.major}');
				}
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
			var form = document.getElementById('eduExpsForm');
	        form.submit();
		}
	}else{
		alert('请至少选择一项进行删除！');
	}
}
</script>
</head>
<body>
	<form:form id="eduExpForm" commandName="eduExpDto" action="${ctx}/talent/addEducationExperienceActions.do" method="post">
	    <div style="display: none">
	       <input type="hidden" id="actionFlag" name="actionFlag"/>
	    </div>
		<table border="0" width="99%">
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
						   <form:select path="major" cssClass="standardSelect" >
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
							<input class="standardButton" type="button" value="添加" onclick="complete('7');" />&nbsp;
							<input class="standardButton" type="button" value="清除" onclick="clearInputFields();" />&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form:form>
	<div class="emptyBlock"></div>
	<form:form id="eduExpsForm" commandName="resumeDto" action="${ctx }/talent/deleteEducationExperience.do">
		<table class="contentTableBody2" cellspacing="1" width="99%">
		   <tr class="contentTableTitle">
		      <td width="5%" align="center">全选</td>
		      <td width="25%">时间</td>
		      <td width="10%">学校</td>
		      <td width="30%">专业</td>
		      <td width="20%">学位</td>
		      <td width="10%" align="center">操作</td>
		   </tr>
		   <c:if test="${not empty resumeDto.eduExpDtos }">
		      <c:forEach items="${resumeDto.eduExpDtos }" var="eduExp" varStatus="status">
		         <tr class="contentTableRow">
		            <td><input type="checkbox" name="expsList" value="${status.index }"/>&nbsp;${status.index+1 }</td>
		            <td>
		            <c:out value="${eduExp.fromDateDto.year }" escapeXml="true"/>年
		            <c:out value="${eduExp.fromDateDto.month }" escapeXml="true"/>月
		            <c:out value="${eduExp.fromDateDto.day }" escapeXml="true"/>日 &nbsp;-&nbsp;
		            <c:out value="${eduExp.toDateDto.year }" escapeXml="true"/>年
		            <c:out value="${eduExp.toDateDto.month }" escapeXml="true"/>月
		            <c:out value="${eduExp.toDateDto.day }" escapeXml="true"/>日
		            </td>
		            <td><c:out value="${eduExp.school }" escapeXml="true"></c:out></td>
		            <td>
		            <c:if test="${eduExp.majorDto != null }">
		               <c:out value="${eduExp.majorDto.displayName }" escapeXml="true"></c:out>
		            </c:if>
		            </td>
		            <td>
		            <c:if test="${eduExp.degreeDto != null }">
		               <c:out value="${eduExp.degreeDto.displayName }" escapeXml="true"></c:out>
		            </c:if>
		            </td>
		            <td align="center"> 
		            <hdiv-c:url value="/talent/preEditEducationExperience.do?_id=${status.index }" var="editEduExpUrl"></hdiv-c:url>
		            <input class="standardButton" type="button" value="编辑" onclick="location.href='${editEduExpUrl }'" />&nbsp;
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