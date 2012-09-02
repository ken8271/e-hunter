<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>e-Hunter System/[EH-TLNT-0001]</title>
</head>
<body>
	<form:form commandName="postDescDto" action="${ctx}/talent/savePositionDescription.do" method="post">
	    <div style="display: none">
	       <input type="hidden" id="actionFlag" name="actionFlag" />
	    </div>
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">需求职位信息 - 职位描述</td>
			</tr>
			<tr>
				<td><common:errorTable path="postDescDto"></common:errorTable></td>
			</tr>
		</table>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>							   
							   <input class="standardButton" type="submit" value="下一步" />&nbsp;
							   <input class="standardButton" type="reset" value="重置" />&nbsp;
							   <input class="standardButton" type="button" value="返回" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<table width="100%">
			<tr>
				<td width="90%"><font face="Arial" size="2"><b>职位基本描述</b></font></td>
			</tr>
		</table>
		<div class="contentTableBody">
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%" >
				<tbody>
				    <common:standardTableRow />
					<tr>
						<td class="labelColumn">招聘企业：<span class="mandatoryField">*</span></td>
						<td><c:out value="${customerName }" escapeXml="true"></c:out></td>
						<td colspan="2">&nbsp;</td>
					</tr>
					<tr>
						<td class="labelColumn">职位分类：<span class="mandatoryField">*</span></td>
						<td>
						   <form:select id="positionSelector" path="positionCategory" cssClass="standardSelect" onchange="loadPositions();">
						      <form:option value="" label="--- 请选择  ---"></form:option>
						      <c:forEach items="${listOfPositionCategory }" var="positionCategory">
						         <form:option value="${positionCategory.typeCode }" label="${positionCategory.displayName }"></form:option>
						      </c:forEach>
						   </form:select>
						</td>
						<td >
						   <form:select path="position" cssClass="standardSelect">
						      <form:option value="" label="--- 请选择  ---"></form:option>
						   </form:select>
						    <common:errorSign id="position" path="position"></common:errorSign>
						</td>
						<td >&nbsp;</td>
					</tr>
					<tr>
						<td class="labelColumn">职位名称：<span class="mandatoryField">*</span></td>
						<td>
						<form:input path="positionName" cssClass="standardInputText" ></form:input>
						<common:errorSign id="positionName" path="positionName"></common:errorSign>
						</td>
						<td colspan="2">&nbsp;</td>
					</tr>
					<tr>
						<td class="labelColumn">所属部门：<span class="mandatoryField">*</span></td>
						<td>
						<form:input path="department" cssClass="standardInputText" ></form:input>
						<common:errorSign id="department" path="department"></common:errorSign>
						</td>
						<td colspan="2">&nbsp;</td>
					</tr>
				    <tr >
						<td class="labelColumn">汇报对象：<span class="mandatoryField">*</span></td>
						<td>
						   <form:input path="reportTarget" cssClass="standardInputText"  /> 
					       <common:errorSign id="reportTarget" path="reportTarget"></common:errorSign>
						</td>
						<td colspan="2">&nbsp;</td>
					</tr>
					<tr>
						<td class="labelColumn">工作地点：<span class="mandatoryField">*</span></td>
						<td>&nbsp;</td>
						<td colspan="2">&nbsp;</td>
					</tr>
					<tr>
						<td class="labelColumn">截止日期：<span class="mandatoryField">*</span></td>
						<td>
						   <common:inputDate dateYY="expiryDateDto.year" dateMON="expiryDateDto.month" dateDD="expiryDateDto.day" ></common:inputDate>
						   <common:errorSign id="expiryDateDto.day" path="expiryDateDto.day"></common:errorSign>
						</td>
						<td colspan="2">&nbsp;</td>
					</tr>
				</tbody>
			</table>			
		</div>
		<div class="emptyBlock"></div>
		<table width="100%">
			<tr>
				<td width="90%"><font face="Arial" size="2"><b>福利薪酬</b></font></td>
			</tr>
		</table>
		<div class="contentTableBody">
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
				<tbody>
				    <common:standardTableRow />
					<tr >
						<td class="labelColumn">职位年薪：<span class="mandatoryField">*</span></td>
						<td colspan="2">
						   <form:input path="salaryFrom" cssClass="standardInputTextNoWidth" maxlength="4" size="4"/>万元 至
					       <form:input path="salaryTo" cssClass="standardInputTextNoWidth" maxlength="4" size="4"/>万元（年薪单位：万元人民币）
					       <common:errorSign id="salaryTo" path="salaryTo"></common:errorSign>
						</td>
						<td>&nbsp;</td>
					</tr>
					<tr >
						<td class="labelColumn">薪资构成：<span class="mandatoryField">*</span></td>
					    <td colspan="3">
							<input type="checkbox" name="salaryStructure" value="BSC" />&nbsp;基本薪资&nbsp;&nbsp;&nbsp;
							<input type="checkbox" name="salaryStructure" value="BNS" />&nbsp;奖金/提成&nbsp;&nbsp;&nbsp;
							<input type="checkbox" name="salaryStructure" value="OPT" />&nbsp;期权&nbsp;&nbsp;&nbsp;
							<input type="checkbox" name="salaryStructure" value="OTH" />&nbsp;其他&nbsp;&nbsp;&nbsp;
					    </td>
					</tr>
					<tr >
						<td class="labelColumn">社会福利：</td>
						<td colspan="3">
						    <input type="checkbox" name="socityWelfare" value="STD" />&nbsp;国家标准&nbsp;&nbsp;&nbsp;
						    <input type="checkbox" name="socityWelfare" value="BIS" />&nbsp;商业保险&nbsp;&nbsp;&nbsp;
							<input type="checkbox" name="socityWelfare" value="OTH" />&nbsp;其他&nbsp;&nbsp;&nbsp;
						</td>
					</tr>
					<tr >
						<td class="labelColumn">居住福利：</td>
						<td colspan="3">
						    <input type="checkbox" name="residentialWelfare" value="AWC" />&nbsp;住房补贴&nbsp;&nbsp;&nbsp;
						    <input type="checkbox" name="residentialWelfare" value="ARG" />&nbsp;公司安排&nbsp;&nbsp;&nbsp;
							<input type="checkbox" name="residentialWelfare" value="PCF" />&nbsp;公积金&nbsp;&nbsp;&nbsp;
							<input type="checkbox" name="residentialWelfare" value="OTH" />&nbsp;其他&nbsp;&nbsp;&nbsp;
						</td>
					</tr>
					<tr >
						<td class="labelColumn">年假福利：</td>
						<td colspan="3">
						    <input type="checkbox" name="annualLeaveWelfare" value="STD" />&nbsp;国家标准&nbsp;&nbsp;&nbsp;
						    <input type="checkbox" name="annualLeaveWelfare" value="REP" />&nbsp;公司补充&nbsp;&nbsp;&nbsp;
							<input type="checkbox" name="annualLeaveWelfare" value="OTH" />&nbsp;其他&nbsp;&nbsp;&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="emptyBlock"></div>
		<table width="100%">
			<tr>
				<td width="90%"><font face="Arial" size="2"><b>工作角色与职责</b></font></td>
			</tr>
		</table>
		<div class="contentTableBody">
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
				<tbody>
				    <common:standardTableRow />
					<tr>
						<td class="labelColumn">职责描述：<span class="mandatoryField">*</span></td>
						<td colspan="3">
						   <form:textarea path="dutyDescription" htmlEscape="true" rows="3" cols="60"  cssStyle="word-wrap:break-word;height:80px" onkeyup="javascript:if (this.value.length>1000){this.value = this.value.substring(0, 1000)}" cssClass="standardInputText"/>
						   <common:errorSign id="dutyDescription" path="dutyDescription"></common:errorSign>
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
							   <input class="standardButton" type="submit" value="下一步" />&nbsp;
							   <input class="standardButton" type="reset" value="重置" />&nbsp;
							   <input class="standardButton" type="button" value="返回" />
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