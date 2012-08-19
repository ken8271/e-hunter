<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>e-Hunter System/[EH-TLNT-0001]</title>
<hdiv-c:url value="/talent/loadIndustries.do" var="loadIndustriesUrl"></hdiv-c:url>
<hdiv-c:url value="/talent/loadPositions.do" var="loadPositionsUrl"></hdiv-c:url>
<script type="text/javascript">
$().ready(function(){
	loadIndustries();
	loadPositions();
});

function loadIndustries(){
	var industrySelector = document.getElementById("industrySelector");
	if(industrySelector != null && industrySelector.selectedIndex != 0){
		$().progressDialog.showDialog("");
		var _id = industrySelector.options[industrySelector.selectedIndex].value;
		$.ajax({
			type:'post',
			url:'${loadIndustriesUrl}',
			dataType:'xml',
			data:{'_id':_id},
			success:function(xml){	
				$().progressDialog.hideDialog("");
				var industry = document.getElementById("industryDto.industryCode");
				clearSelector(industry);
				industry.options[industry.length] = new Option("--- 请选择 ---", "");
				$(xml).find('industry').each(function(i , element){
					var label = $(this).find("label").text();
					var val = ''+$(this).children("value").text()+'';
					industry.options[industry.length] = new Option(label, val);
				});
				
			    document.getElementById('industryDto.industryCode').value = '${jobExpDto.industryDto.industryCode}';
			},
			error:function(){
				$().progressDialog.hideDialog("");
				alert('系统错误');
			}
		});
	}else{
		var industry = document.getElementById("industryDto.industryCode");
		clearSelector(industry);
		industry.options[industry.length] = new Option("--- 请选择 ---", "");
	}
}

function loadPositions(){
	var positionSelector = document.getElementById("positionSelector");
	if(positionSelector != null && positionSelector.selectedIndex != 0){
		$().progressDialog.showDialog("");
		var _id = positionSelector.options[positionSelector.selectedIndex].value;
		$.ajax({
			type:'post',
			url:'${loadPositionsUrl}',
			dataType:'xml',
			data:{'_id':_id},
			success:function(xml){	
				$().progressDialog.hideDialog("");
				var position = document.getElementById("positionDto.typeCode");
				clearSelector(position);
				position.options[position.length] = new Option("--- 请选择 ---", "");
				$(xml).find('position').each(function(i , element){
					var label = $(this).find("label").text();
					var val = ''+$(this).children("value").text()+'';
					position.options[position.length] = new Option(label, val);
				});
				
			    document.getElementById('positionDto.typeCode').value = '${jobExpDto.positionDto.typeCode}';
			},
			error:function(){
				$().progressDialog.hideDialog("");
				alert('系统错误');
			}
		});
	}else{
		var position = document.getElementById("positionDto.typeCode");
		clearSelector(position);
		position.options[position.length] = new Option("--- 请选择 ---", "");
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
	var form = document.getElementById('jobExpForm');
	form.submit();
}
</script>
</head>
<body>
    <hdiv-c:url value="/talent/backToFillJobExperience.do" var="backUrl"></hdiv-c:url>
	<form:form commandName="jobExpDto" action="${ctx}/talent/completeEditJobExperience.do" method="post">
		<table border="0" width="99%">
			<tr>
				<td class="pageTitle">人才工作经历编辑</td>
			</tr>
			<tr>
				<td><common:errorTable path="jobExpDto"></common:errorTable></td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<table id="bg2" border="0" width="99%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							<input class="standardButton" type="submit" value="确认" />&nbsp;
							<input class="standardButton" type="button" value="返回" onclick="location.href='${backUrl}'"/>&nbsp;
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
						<td class="labelColumn">企业名称：<span class="mandatoryField">*</span></td>
						<td>
						   <form:input path="companyName" cssClass="standardInputText"  /> 
					       <common:errorSign id="companyName" path="companyName"></common:errorSign>
						</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr >
						<td class="labelColumn">企业性质：<span class="mandatoryField">*</span></td>
						<td>
						   <form:select path="companyCategoryDto.categoryCode" cssClass="standardSelect">
						      <form:option value="" label="--- 请选择  ---"></form:option>
						      <c:forEach items="${listOfCompanyCategory }" var="companyCategory">
						         <form:option value="${companyCategory.categoryCode }" label="${companyCategory.displayName }"></form:option>
						      </c:forEach>
						   </form:select>
						   <common:errorSign id="companyCategoryDto.categoryCode" path="companyCategoryDto.categoryCode"></common:errorSign>
						</td>
						<td class="labelColumn">企业规模：<span class="mandatoryField">*</span></td>
						<td >
						   <form:select path="companySizeDto.sizeCode" cssClass="standardSelect">
						      <form:option value="" label="--- 请选择  ---"></form:option>
						      <c:forEach items="${listOfCompanySize }" var="companySize">
						         <form:option value="${companySize.sizeCode }" label="${companySize.displayName }"></form:option>
						      </c:forEach>
						   </form:select>
						   <common:errorSign id="companySizeDto.sizeCode" path="companySizeDto.sizeCode"></common:errorSign>
						</td>
					</tr>
					<tr >
						<td class="labelColumn">行业类别：<span class="mandatoryField">*</span></td>
						<td>
						   <form:select id="industrySelector" path="industryCategoryDto.categoryCode" cssClass="standardSelect" onchange="loadIndustries();">
						      <form:option value="" label="--- 请选择  ---"></form:option>
						      <c:forEach items="${listOfIndustryCategory }" var="industryCategory">
						         <form:option value="${industryCategory.categoryCode }" label="${industryCategory.displayName }"></form:option>
						      </c:forEach>
						   </form:select>
						</td>
						<td >
						   <form:select path="industryDto.industryCode" cssClass="standardSelect">
						      <form:option value="" label="--- 请选择  ---"></form:option>
						   </form:select>
						   <common:errorSign id="industryDto.industryCode" path="industryDto.industryCode"></common:errorSign>
						</td>
						<td >&nbsp;</td>
					</tr>
					<tr >
						<td class="labelColumn">所在部门：<span class="mandatoryField">*</span></td>
						<td>
						   <form:input path="department" cssClass="standardInputText"  /> 
						   <common:errorSign id="department" path="department"></common:errorSign>
						</td>
						<td >&nbsp;</td>
						<td >&nbsp;</td>
					</tr>
					<tr >
						<td class="labelColumn">职位类别：<span class="mandatoryField">*</span></td>
						<td>
						   <form:select id="positionSelector" path="positionCategoryDto.typeCode" cssClass="standardSelect" onchange="loadPositions();">
						      <form:option value="" label="--- 请选择  ---"></form:option>
						      <c:forEach items="${listOfPositionCategory }" var="positionCategory">
						         <form:option value="${positionCategory.typeCode }" label="${positionCategory.displayName }"></form:option>
						      </c:forEach>
						   </form:select>
						</td>
						<td >
						   <form:select path="positionDto.typeCode" cssClass="standardSelect">
						      <form:option value="" label="--- 请选择  ---"></form:option>
						   </form:select>
						    <common:errorSign id="positionDto.typeCode" path="positionDto.typeCode"></common:errorSign>
						</td>
						<td >&nbsp;</td>
					</tr>
					<tr >
						<td class="labelColumn">职位名称：</td>
						<td>
						   <form:input path="positionName" cssClass="standardInputText"  /> 
						   <common:errorSign id="positionName" path="positionName"></common:errorSign>
						</td>
						<td >&nbsp;</td>
						<td >&nbsp;</td>
					</tr>
					<tr >
						<td class="labelColumn">工作描述：<span class="mandatoryField">*</span></td>
						<td colspan="3">
						   <form:textarea path="jobDescription" htmlEscape="true" rows="3" cols="60"  cssStyle="word-wrap:break-word;height:80px" onkeyup="javascript:if (this.value.length>300){this.value = this.value.substring(0, 300)}" cssClass="standardInputText"/>
						   <common:errorSign id="jobDescription" path="jobDescription"></common:errorSign>
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
							<input class="standardButton" type="submit" value="确认" />&nbsp;
							<input class="standardButton" type="button" value="返回" onclick="${backUrl}"/>&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>