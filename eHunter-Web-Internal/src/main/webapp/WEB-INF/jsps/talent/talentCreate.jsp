<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>e-Hunter System/[EH-TLNT-0001]</title>
<script type="text/javascript">
function popUpSelector(){
	setOverlayDimension('fade');
	hideAllObject();
	setPopUpFramePosition(400,150);
	popUpFrame('light','fade');
}
</script>
</head>
<body>
	<form:form commandName="talentDto" action="${ctx}/talent/saveTalentInfo.do" method="post">
	    <div style="display: none">
	       <input type="hidden" id="actionFlag" name="actionFlag" />
	    </div>
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">人才基本资料填写</td>
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
		<table class="standardTableForm" border="1" cellspacing="0"
				cellpadding="0" width="100%">
				<tbody>
				    <common:standardTableRow />
					<tr>
						<td class="labelColumn">人才来源：<span class="mandatoryField">*</span></td>
						<td>
						   <form:select path="talentSrc"  cssClass="standardSelect">
						      <form:option value="" label="--- 请选择  ---"></form:option>
						      <c:forEach items="${listOfTalentSource }" var="src">
						         <form:option value="${src.sourceId }" label="${src.displayName }"></form:option>
						      </c:forEach>
						   </form:select>
						   <common:errorSign path="talentSrc"></common:errorSign>
						</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
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
						<form:input path="cnName" cssClass="standardInputText" ></form:input>
						<common:errorSign id="cnName" path="cnName"></common:errorSign>
						</td>
						<td class="labelColumn">英文名：</td>
						<td>
						   <form:input path="enName" cssClass="standardInputText" /> 
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
						<td class="labelColumn">婚姻状况：<span class="mandatoryField">*</span></td>
						<td>
						<form:radiobutton path="maritalStatus" value="01" /> 未婚&nbsp;/
						<form:radiobutton path="maritalStatus" value="02" /> 已婚&nbsp;/
						<form:radiobutton path="maritalStatus" value="03" /> 保密&nbsp;
						<common:errorSign id="maritalStatus" path="maritalStatus"></common:errorSign>
					    </td>
					</tr>
					<tr>
						<td class="labelColumn">出生日期：<span class="mandatoryField">*</span></td>
						<td>
						<common:inputDate dateYY="birthDateDto.year" dateMON="birthDateDto.month" dateDD="birthDateDto.day" ></common:inputDate>
						<common:errorSign id="birthDateDto.day" path="birthDateDto.day"></common:errorSign>
						</td>
						<td class="labelColumn">籍贯：<span class="mandatoryField">*</span></td>
						<td>
						<form:input path="nativePlace" cssClass="standardInputText" /> 
						<common:errorSign id="nativePlace" path="nativePlace"></common:errorSign>
					    </td>
					</tr>
				    <tr >
						<td class="labelColumn">曾居地：</td>
						<td>
						   <form:input path="onceLivePlace" cssClass="standardInputText"  /> 
					       <common:errorSign id="onceLivePlace" path="onceLivePlace"></common:errorSign>
						</td>
						<td class="labelColumn">现居地：</td>
						<td>
						   <form:input path="nowLivePlace" cssClass="standardInputText" /> 
					       <common:errorSign id="nowLivePlace" path="nowLivePlace"></common:errorSign>
						</td>
					</tr>
					<tr>
						<td class="labelColumn">最高学历：<span class="mandatoryField">*</span></td>
						<td>
						   <form:select path="highestDegree"  cssClass="standardSelect">
						      <form:option value="" label="--- 请选择  ---"></form:option>
						      <c:forEach items="${listOfDegree }" var="dgre">
						         <form:option value="${dgre.degreeCode }" label="${dgre.displayName }"></form:option>
						      </c:forEach>
						   </form:select>
						   <common:errorSign path="highestDegree"></common:errorSign>
						</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
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
						   <form:input path="homeNumberDto.regionCode" cssClass="standardInputTextNoWidth" maxlength="4" size="4"/> - 
					       <form:input path="homeNumberDto.phoneNumber" cssClass="standardInputTextNoWidth" maxlength="8" size="8"/>
					       <common:errorSign id="homeNumberDto.phoneNumber" path="homeNumberDto.phoneNumber"></common:errorSign>
						</td>
						<td class="labelColumn">公司电话：</td>
						<td>
						   <form:input path="companyNumberDto.regionCode" cssClass="standardInputTextNoWidth" maxlength="4" size="4"/> - 
					       <form:input path="companyNumberDto.phoneNumber" cssClass="standardInputTextNoWidth" maxlength="8" size="8"/>
					       <common:errorSign id="companyNumberDto.phoneNumber" path="companyNumberDto.phoneNumber"></common:errorSign>
					    </td>
					</tr>
					<tr >
						<td class="labelColumn">手机：<span class="mandatoryField">*</span></td>
					    <td>
							<form:input path="mobilePhoneDto1.phoneNumber" cssClass="standardInputText" maxlength="11"/>
							<common:errorSign id="mobilePhoneDto1.phoneNumber" path="mobilePhoneDto1.phoneNumber"></common:errorSign><br>
							<form:input path="mobilePhoneDto2.phoneNumber" cssClass="standardInputText" maxlength="11"/>
							<common:errorSign id="mobilePhoneDto2.phoneNumber" path="mobilePhoneDto2.phoneNumber"></common:errorSign><br>
					    </td>
						<td class="labelColumn">邮箱：<span class="mandatoryField">*</span></td>
						<td>
						   <form:input path="email" cssClass="standardInputText" maxlength="50" /> 
					       <common:errorSign id="email" path="email"></common:errorSign>
						</td>
					</tr>
					<tr >
						<td class="labelColumn">家庭地址：</td>
						<td colspan="3">
						   <form:input path="homeAddress" cssClass="standardInputText" /> 
					       <common:errorSign id="homeAddress" path="homeAddress"></common:errorSign>
						</td>
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