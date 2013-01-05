<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>e-Hunter System/[EH-TLNT-0001]</title>
</head>
<body>
    <hdiv-c:url value="/talent/viewTalentDetail.do?_id=${talentDto.talentID }" var="backUrl"></hdiv-c:url>
	<form:form commandName="talentDto" action="${ctx}/talent/updateTalentInfo.do" method="post">
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">人才资料编辑</td>
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
							   <input class="standardButton" type="submit" value="更新">&nbsp;
							   <input class="standardButton" type="button" value="返回" onclick="location.href='${backUrl}'" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<table class="standardTableForm" border="1" cellspacing="0"
				cellpadding="0" width="100%">
				<tbody>
				    <common:standardTableRow />
					<tr>
						<td class="labelColumn">人才编号：</td>
				        <td><c:out value="${talentDto.talentID }" escapeXml="true"></c:out></td>
				        <td colspan="2"></td>
					</tr>
				</tbody>
			</table>
		<div class="emptyBlock"></div>
		<table width="100%">
			<tr>
				<td width="10%"><font face="Arial" size="2"><b>第一部分：</b></font></td>
				<td width="90%"><font face="Arial" size="2"><b>人才基本信息</b></font></td>
			</tr>
		</table>
		<div class="contentTableBody">
			<div style="height:5px"></div>
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%" >
				<tbody>
				    <common:standardTableRow />
					<tr>
						<td class="labelColumn">中文名：<span class="mandatoryField">*</span></td>
						<td>
						<form:input path="cnName" cssClass="standardInputText" onblur="isChanged('${talentDto.cnName }','cnName')"></form:input>
						<common:errorSign id="cnName" path="cnName"></common:errorSign>
						</td>
						<td class="labelColumn">英文名：</td>
						<td>
						   <form:input path="enName" cssClass="standardInputText" onblur="isChanged('${talentDto.enName }','enName')"/> 
						   <common:errorSign id="enName" path="enName"></common:errorSign>
					    </td>
					</tr>
					<tr>
						<td class="labelColumn">性别：<span class="mandatoryField">*</span></td>
						<td>
						<form:radiobutton path="gender" value="M" /> 男&nbsp;/
						<form:radiobutton path="gender" value="F" /> 女&nbsp;
						<common:errorSign id="gender" path="gender"></common:errorSign>
						</td>
						<td class="labelColumn">婚姻状况：</td>
						<td>
						<form:radiobutton path="maritalStatus" value="01" /> 未婚&nbsp;/
						<form:radiobutton path="maritalStatus" value="02" /> 已婚&nbsp;/
						<form:radiobutton path="maritalStatus" value="03" /> 保密&nbsp;
						<common:errorSign id="maritalStatus" path="maritalStatus"></common:errorSign>
					    </td>
					</tr>
					<tr>
						<td class="labelColumn">出生日期：</td>
						<td>
						<common:inputDate dateYY="birthDateDto.year" dateMON="birthDateDto.month" dateDD="birthDateDto.day" ></common:inputDate>
						<common:errorSign id="birthDateDto.day" path="birthDateDto.day"></common:errorSign>
						</td>
						<td class="labelColumn">籍贯：</td>
						<td>
						<form:input path="nativePlace" cssClass="standardInputText" onblur="isChanged('${talentDto.nativePlace }','nativePlace')"/> 
						<common:errorSign id="nativePlace" path="nativePlace"></common:errorSign>
					    </td>
					</tr>
				    <tr >
						<td class="labelColumn">曾居地：</td>
						<td>
						   <form:input path="onceLivePlace" cssClass="standardInputText"  onblur="isChanged('${talentDto.onceLivePlace }','onceLivePlace')"/> 
					       <common:errorSign id="onceLivePlace" path="onceLivePlace"></common:errorSign>
						</td>
						<td class="labelColumn">现居地：</td>
						<td>
						   <form:input path="nowLivePlace" cssClass="standardInputText" onblur="isChanged('${talentDto.nowLivePlace }','nowLivePlace')"/> 
					       <common:errorSign id="nowLivePlace" path="nowLivePlace"></common:errorSign>
						</td>
					</tr>
					<tr>
						<td class="labelColumn">最高学历：</td>
						<td>
						   <form:select path="highestDegree"  cssClass="standardSelect" onblur="isChanged('${talentDto.highestDegree }','highestDegree')">
						      <form:option value="" label="--- 请选择  ---"></form:option>
						      <c:forEach items="${listOfDegree }" var="dgre">
						         <form:option value="${dgre.degreeCode }" label="${dgre.displayName }"></form:option>
						      </c:forEach>
						   </form:select>
						   <common:errorSign path="highestDegree"></common:errorSign>
						</td>
						<td class="labelColumn">人才来源：</td>
						<td>
						<form:select path="talentSrc"  cssClass="standardSelect">
						      <form:option value="" label="--- 请选择  ---"></form:option>
						      <c:forEach items="${listOfTalentSource }" var="src">
						         <form:option value="${src.sourceId }" label="${src.displayName }"></form:option>
						      </c:forEach>
						</form:select>
						<common:errorSign path="talentSrc"></common:errorSign>
						</td>
					</tr>
				</tbody>
			</table>			
		</div>
		<div class="emptyBlock"></div>
		<table width="100%">
			<tr>
				<td width="10%"><font face="Arial" size="2"><b>第二部分：</b></font></td>
				<td width="90%"><font face="Arial" size="2"><b>人才联系方式</b></font></td>
			</tr>
		</table>
		<div class="contentTableBody">
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
				<tbody>
				    <common:standardTableRow />
					<tr >
						<td class="labelColumn">家庭电话：</td>
						<td>
						   <form:input path="homeNumberDto.regionCode" cssClass="standardInputTextNoWidth" maxlength="4" size="4" onblur="isChanged('${talentDto.homeNumberDto.regionCode }','homeNumberDto.regionCode')"/> - 
					       <form:input path="homeNumberDto.phoneNumber" cssClass="standardInputTextNoWidth" maxlength="8" size="8" onblur="isChanged('${talentDto.homeNumberDto.phoneNumber }','homeNumberDto.phoneNumber')"/>
					       <common:errorSign id="homeNumberDto.phoneNumber" path="homeNumberDto.phoneNumber"></common:errorSign>
						</td>
						<td class="labelColumn">公司电话：</td>
						<td>
						   <form:input path="companyNumberDto.regionCode" cssClass="standardInputTextNoWidth" maxlength="4" size="4" onblur="isChanged('${talentDto.companyNumberDto.regionCode }','companyNumberDto.regionCode')"/> - 
					       <form:input path="companyNumberDto.phoneNumber" cssClass="standardInputTextNoWidth" maxlength="8" size="8" onblur="isChanged('${talentDto.companyNumberDto.phoneNumber }','companyNumberDto.phoneNumber')"/>
					       <common:errorSign id="companyNumberDto.phoneNumber" path="companyNumberDto.phoneNumber"></common:errorSign>
					    </td>
					</tr>
					<tr >
						<td class="labelColumn">手机：<span class="mandatoryField">*</span></td>
					    <td>
							<form:input path="mobilePhoneDto1.phoneNumber" cssClass="standardInputText" maxlength="11" onblur="isChanged('${talentDto.mobilePhoneDto1.phoneNumber }','mobilePhoneDto1.phoneNumber')"/>
							<common:errorSign id="mobilePhoneDto1.phoneNumber" path="mobilePhoneDto1.phoneNumber"></common:errorSign><br>
							<form:input path="mobilePhoneDto2.phoneNumber" cssClass="standardInputText" maxlength="11" onblur="isChanged('${talentDto.mobilePhoneDto2.phoneNumber }','mobilePhoneDto2.phoneNumber')"/>
							<common:errorSign id="mobilePhoneDto2.phoneNumber" path="mobilePhoneDto2.phoneNumber"></common:errorSign><br>
					    </td>
						<td class="labelColumn">邮箱：</td>
						<td>
						   <form:input path="email" cssClass="standardInputText" maxlength="50" onblur="isChanged('${talentDto.email }','email')"/> 
					       <common:errorSign id="email" path="email"></common:errorSign>
						</td>
					</tr>
					<tr>
						<td class="labelColumn">QQ：</td>
					    <td>
							<form:input path="QQ" cssClass="standardInputText" maxlength="11"/>
							<common:errorSign id="QQ" path="QQ"></common:errorSign>
					    </td>
						<td class="labelColumn">MSN：</td>
						<td>
						   <form:input path="msn" cssClass="standardInputText" maxlength="50" /> 
					       <common:errorSign id="msn" path="msn"></common:errorSign>
						</td>
					</tr>
					<tr >
						<td class="labelColumn">家庭地址：</td>
						<td colspan="3">
						   <form:input path="homeAddress" cssClass="standardInputText" onblur="isChanged('${talentDto.homeAddress }','homeAddress')"/> 
					       <common:errorSign id="homeAddress" path="homeAddress"></common:errorSign>
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
							   <input class="standardButton" type="submit" value="更新">&nbsp;
							   <input class="standardButton" type="button" value="返回" onclick="location.href='${backUrl}'" />
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