<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>e-Hunter System/[EH-TLNT-0001]</title>
</head>
<body>
	<form:form commandName="postRequireDto" action="${ctx}/project/savePositionRequirement.do" method="post">
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">需求职位信息 - 职位要求</td>
			</tr>
			<tr>
				<td><common:errorTable path="postRequireDto"></common:errorTable></td>
			</tr>
		</table>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>							   
							   <input class="standardButton" type="submit" value="提交" />&nbsp;
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
				<td width="90%"><font face="Arial" size="2"><b>基本素质要求</b></font></td>
			</tr>
		</table>
		<div class="contentTableBody">
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%" >
				<tbody>
				    <common:standardTableRow />
					<tr>
						<td class="labelColumn">年龄要求：<span class="mandatoryField">*</span></td>
						<td>
						<form:select path="ageFrom" cssClass="standardSelectNoWidth">
						   <form:option value="" label="不限"></form:option>
						   <c:forEach begin="20" end="50" step="5" var="age">
						      <form:option value="${age }" label="${age }"></form:option>
						   </c:forEach>
						</form:select>
						&nbsp;&nbsp;-&nbsp;&nbsp;
						<form:select path="ageTo" cssClass="standardSelectNoWidth">
						   <form:option value="" label="不限"></form:option>
						   <c:forEach begin="20" end="50" step="5" var="age">
						      <form:option value="${age }" label="${age }"></form:option>
						   </c:forEach>
						</form:select>
						<common:errorSign id="ageTo" path="ageTo"></common:errorSign>
						</td>
						<td class="labelColumn">性别要求：<span class="mandatoryField">*</span></td>
						<td>
						<form:select path="gender" cssClass="standardSelectNoWidth">
						   <form:option value="" label="不限"></form:option>
						   <form:option value="M" label="男"></form:option>
						   <form:option value="F" label="女"></form:option>
						</form:select>
						<common:errorSign id="gender" path="gender"></common:errorSign>
					</tr>
					<tr>
						<td class="labelColumn">专业要求：<span class="mandatoryField">*</span></td>
						<td>
						   <form:select path="majorCategory" cssClass="standardSelect" >
						      <form:option value="" label="不限"></form:option>
						      <c:forEach items="${listOfSubjectType}" var="subjectType">
						          <form:option value="${subjectType.typeCode }" label="${subjectType.displayName }"></form:option>
						      </c:forEach>
						   </form:select>
						<common:errorSign id="majorCategory" path="majorCategory"></common:errorSign>
						</td>
						<td class="labelColumn">总工作年限：<span class="mandatoryField">*</span></td>
						<td>
						   <form:input path="workExperience" cssClass="standardInputTextNoWidth" maxlength="2" size="2"/>&nbsp;年以上
						   <common:errorSign id="workExperience" path="workExperience"></common:errorSign>
						</td>
					</tr>
				    <tr >
						<td class="labelColumn">学历要求：</td>
						<td>
						   <form:select path="degree"  cssClass="standardSelect">
						      <form:option value="" label="不限"></form:option>
						      <c:forEach items="${listOfDegree }" var="dgre">
						         <form:option value="${dgre.degreeCode }" label="${dgre.displayName }"></form:option>
						      </c:forEach>
						   </form:select>
					       <common:errorSign id="degree" path="degree"></common:errorSign>
						</td>
						<td class="labelColumn">是否统招全日制：<span class="mandatoryField">*</span></td>
						<td>
						<form:radiobutton path="ftEduIndicator" value="Y" /> 是&nbsp;&nbsp;&nbsp;
						<form:radiobutton path="ftEduIndicator" value="N" /> 不限&nbsp;
						<common:errorSign id="ftEduIndicator" path="ftEduIndicator"></common:errorSign>
						</td>
					</tr>
					<tr>
						<td class="labelColumn">语言要求：</td>
						<td colspan="3">
						   <input type="checkbox" name="language" value="EN" />&nbsp;英语&nbsp;&nbsp;&nbsp;
						   <input type="checkbox" name="language" value="JP" />&nbsp;日语&nbsp;&nbsp;&nbsp;
						   <input type="checkbox" name="language" value="FR" />&nbsp;法语&nbsp;&nbsp;&nbsp;
						   <input type="checkbox" name="language" value="CH" />&nbsp;普通话&nbsp;&nbsp;&nbsp;
						   <input type="checkbox" name="language" value="CT" />&nbsp;粤语&nbsp;&nbsp;&nbsp;
						   <input type="checkbox" name="language" value="OT" />&nbsp;其他&nbsp;&nbsp;&nbsp;
						   <common:errorSign path="language"></common:errorSign>
						</td>
					</tr>
				</tbody>
			</table>			
		</div>
		<div class="emptyBlock"></div>
		<table width="100%">
			<tr>
				<td width="90%"><font face="Arial" size="2"><b>任职资格相关要求</b></font></td>
			</tr>
		</table>
		<div class="contentTableBody">
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
				<tbody>
				    <common:standardTableRow />
					<tr>
						<td class="labelColumn">任职资格：<span class="mandatoryField">*</span></td>
						<td colspan="3">
						   <form:textarea path="duty" htmlEscape="true" rows="3" cols="60"  cssStyle="word-wrap:break-word;height:80px" onkeyup="javascript:if (this.value.length>1000){this.value = this.value.substring(0, 1000)}" cssClass="standardInputText"/>
						   <common:errorSign id="duty" path="duty"></common:errorSign>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="emptyBlock"></div>
		<table width="100%">
			<tr>
				<td width="90%"><font face="Arial" size="2"><b>职位关键词</b></font></td>
			</tr>
		</table>
		<div class="contentTableBody">
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
				<tbody>
				    <common:standardTableRow />
					<tr >
						<td class="labelColumn">关键词：</td>
						<td colspan="3">
						   <form:input path="keyWords[0]" cssClass="standardInputTextNoWidth" maxlength="5" size="10"/>&nbsp;&nbsp;
						   <form:input path="keyWords[1]" cssClass="standardInputTextNoWidth" maxlength="5" size="10"/>&nbsp;&nbsp;
						   <form:input path="keyWords[2]" cssClass="standardInputTextNoWidth" maxlength="5" size="10"/>&nbsp;&nbsp;
						   <form:input path="keyWords[3]" cssClass="standardInputTextNoWidth" maxlength="5" size="10"/>&nbsp;&nbsp;
						   <form:input path="keyWords[4]" cssClass="standardInputTextNoWidth" maxlength="5" size="10"/>&nbsp;&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="emptyBlock"></div>
		<table width="100%">
			<tr>
				<td width="90%"><font face="Arial" size="2"><b>期望人选来源行业</b></font></td>
			</tr>
		</table>
		<div class="contentTableBody">
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
				<tbody>
				    <common:standardTableRow />
					<tr >
						<td class="labelColumn">所属行业：</td>
						<td colspan="3">
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="emptyBlock"></div>
		<table width="100%">
			<tr>
				<td width="90%"><font face="Arial" size="2"><b>其他</b></font></td>
			</tr>
		</table>
		<div class="contentTableBody">
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
				<tbody>
				    <common:standardTableRow />
					<tr>
						<td class="labelColumn">补充说明：</td>
						<td colspan="3">
						   <form:textarea path="remark" htmlEscape="true" rows="3" cols="60"  cssStyle="word-wrap:break-word;height:80px" onkeyup="javascript:if (this.value.length>1000){this.value = this.value.substring(0, 1000)}" cssClass="standardInputText"/>
						   <common:errorSign id="remark" path="remark"></common:errorSign>
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
							   <input class="standardButton" type="submit" value="提交" />&nbsp;
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