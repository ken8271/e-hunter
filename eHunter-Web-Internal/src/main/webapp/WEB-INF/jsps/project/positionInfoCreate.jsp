<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>e-Hunter System/[EH-TLNT-0001]</title>
</head>
<body>
	<form:form commandName="talentDto" action="${ctx}/talent/saveTalentInfo.do" method="post">
	    <div style="display: none">
	       <input type="hidden" id="actionFlag" name="actionFlag" />
	    </div>
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">职位描述</td>
			</tr>
			<tr>
				<td><common:errorTable path="talentDto"></common:errorTable></td>
			</tr>
		</table>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>							   
							   <input class="standardButton" type="button" value="填写简历" onclick="popUpSelector();" />&nbsp;
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
				<td width="10%"><font face="Arial" size="2"><b>第三部分：</b></font></td>
				<td width="90%"><font face="Arial" size="2"><b>职位基本描述</b></font></td>
			</tr>
		</table>
		<div class="contentTableBody">
			<div style="height:5px"></div>
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%" >
				<tbody>
				    <common:standardTableRow />
					<tr>
						<td class="labelColumn">招聘企业：<span class="mandatoryField">*</span></td>
						<td>
						<form:input path="cnName" cssClass="standardInputText" ></form:input>
						<common:errorSign id="cnName" path="cnName"></common:errorSign>
						</td>
						<td colspan="2">&nbsp;</td>
					</tr>
					<tr>
						<td class="labelColumn">职位分类：<span class="mandatoryField">*</span></td>
						<td>
						<form:input path="cnName" cssClass="standardInputText" ></form:input>
						<common:errorSign id="gender" path="gender"></common:errorSign>
						</td>
						<td colspan="2">&nbsp;</td>
					</tr>
					<tr>
						<td class="labelColumn">职位名称：<span class="mandatoryField">*</span></td>
						<td>
						<form:input path="cnName" cssClass="standardInputText" ></form:input>
						<common:errorSign id="gender" path="gender"></common:errorSign>
						</td>
						<td colspan="2">&nbsp;</td>
					</tr>
					<tr>
						<td class="labelColumn">所属部门：<span class="mandatoryField">*</span></td>
						<td>
						<common:inputDate dateYY="birthDateDto.year" dateMON="birthDateDto.month" dateDD="birthDateDto.day" ></common:inputDate>
						<common:errorSign id="birthDateDto.day" path="birthDateDto.day"></common:errorSign>
						</td>
						<td colspan="2">&nbsp;</td>
					</tr>
				    <tr >
						<td class="labelColumn">汇报对象：</td>
						<td>
						   <form:input path="onceLivePlace" cssClass="standardInputText"  /> 
					       <common:errorSign id="onceLivePlace" path="onceLivePlace"></common:errorSign>
						</td>
						<td class="labelColumn">下属人数：<span class="mandatoryField">*</span></td>
						<td>
						<common:inputDate dateYY="birthDateDto.year" dateMON="birthDateDto.month" dateDD="birthDateDto.day" ></common:inputDate>
						<common:errorSign id="birthDateDto.day" path="birthDateDto.day"></common:errorSign>
						</td>
					</tr>
					<tr>
						<td class="labelColumn">工作地点：<span class="mandatoryField">*</span></td>
						<td>
						 <form:input path="onceLivePlace" cssClass="standardInputText"  /> 
						   <common:errorSign path="highestDegree"></common:errorSign>
						</td>
						<td colspan="2">&nbsp;</td>
					</tr>
					<tr>
						<td class="labelColumn">截止日期：<span class="mandatoryField">*</span></td>
						<td>
						 <form:input path="onceLivePlace" cssClass="standardInputText"  /> 
						   <common:errorSign path="highestDegree"></common:errorSign>
						</td>
						<td colspan="2">&nbsp;</td>
					</tr>
				</tbody>
			</table>			
		</div>
		<div class="emptyBlock"></div>
		<table width="100%">
			<tr>
				<td width="10%"><font face="Arial" size="2"><b>第四部分：</b></font></td>
				<td width="90%"><font face="Arial" size="2"><b>福利薪酬</b></font></td>
			</tr>
		</table>
		<div class="contentTableBody">
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
				<tbody>
				    <common:standardTableRow />
					<tr >
						<td class="labelColumn">职位年薪：</td>
						<td>
						   <form:input path="homeNumberDto.regionCode" cssClass="standardInputTextNoWidth" maxlength="4" size="4"/> - 
					       <form:input path="homeNumberDto.phoneNumber" cssClass="standardInputTextNoWidth" maxlength="8" size="8"/>
					       <common:errorSign id="homeNumberDto.phoneNumber" path="homeNumberDto.phoneNumber"></common:errorSign>
						</td>
						<td colspan="2">&nbsp;</td>
					</tr>
					<tr >
						<td class="labelColumn">薪资构成：<span class="mandatoryField">*</span></td>
					    <td colspan="3">
							<form:input path="mobilePhoneDto1.phoneNumber" cssClass="standardInputText" maxlength="11"/>
							<common:errorSign id="mobilePhoneDto1.phoneNumber" path="mobilePhoneDto1.phoneNumber"></common:errorSign><br>
							<form:input path="mobilePhoneDto2.phoneNumber" cssClass="standardInputText" maxlength="11"/>
							<common:errorSign id="mobilePhoneDto2.phoneNumber" path="mobilePhoneDto2.phoneNumber"></common:errorSign><br>
					    </td>
					</tr>
					<tr >
						<td class="labelColumn">社会福利：</td>
						<td colspan="3">
						   <form:input path="homeAddress" cssClass="standardInputText" /> 
					       <common:errorSign id="homeAddress" path="homeAddress"></common:errorSign>
						</td>
					</tr>
					<tr >
						<td class="labelColumn">居住福利：</td>
						<td colspan="3">
						   <form:input path="homeAddress" cssClass="standardInputText" /> 
					       <common:errorSign id="homeAddress" path="homeAddress"></common:errorSign>
						</td>
					</tr>
					<tr >
						<td class="labelColumn">年假福利：</td>
						<td colspan="3">
						   <form:input path="homeAddress" cssClass="standardInputText" /> 
					       <common:errorSign id="homeAddress" path="homeAddress"></common:errorSign>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<table width="100%">
			<tr>
				<td width="10%"><font face="Arial" size="2"><b>第五部分：</b></font></td>
				<td width="90%"><font face="Arial" size="2"><b>工作角色与职责</b></font></td>
			</tr>
		</table>
		<div class="contentTableBody">
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
				<tbody>
				    <common:standardTableRow />
					<tr >
						<td class="labelColumn">职责描述：</td>
						<td>
						   <form:input path="homeNumberDto.regionCode" cssClass="standardInputTextNoWidth" maxlength="4" size="4"/> - 
					       <form:input path="homeNumberDto.phoneNumber" cssClass="standardInputTextNoWidth" maxlength="8" size="8"/>
					       <common:errorSign id="homeNumberDto.phoneNumber" path="homeNumberDto.phoneNumber"></common:errorSign>
						</td>
						<td colspan="2">&nbsp;</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="emptyBlock"></div>
		<jsp:include page="resumeModeOption_pop.jsp"></jsp:include>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							   <input class="standardButton" type="button" value="填写简历" onclick="popUpSelector();" />&nbsp;
							   <input class="standardButton" type="reset" value="重置">&nbsp;
							   <input class="standardButton" type="button" value="结束">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
	</form:form>
</body>
</html>