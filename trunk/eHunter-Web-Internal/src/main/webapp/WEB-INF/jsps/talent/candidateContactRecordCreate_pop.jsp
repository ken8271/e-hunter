<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>

<div id="fade" class="black_overlay"></div>
<div id="light" class="white_content_without_padding">
<form:form id="newContactHistoryForm" commandName="candidateContactHistoryDto" action="${ctx }/talent/submitContactHistory.do">
	<table cellspacing="0" cellpadding="0" width="100%">
		<tr class="popUpTitle">
			<td width="70%"><span>&nbsp;&nbsp;候选人联系记录&nbsp;-&nbsp;创建</span>
			</td>
			<td width="15%" align="right">
			   <span style="cursor: pointer; font: bold 14px Arial" onclick="submitContactHistory();">[提交]</span>
			</td>
			<td width="15%" align="center">
			   <span style="cursor: pointer; font: bold 14px Arial" onclick="document.getElementById('light').style.display = 'none';document.getElementById('fade').style.display = 'none';showAllObject();">[取消]</span>
			</td>
		</tr>
	</table>
	<div class="emptyBlock"></div>
	<table cellspacing="0" cellpadding="0" width="100%">
		<tr height="25px">
			<td>
			   <span style="font: 12px Arial">&nbsp;&nbsp;中文名/英文名：<span class="mandatoryField">*</span></span>&nbsp;&nbsp;&nbsp;&nbsp;
			   <span style="font: 12px Arial">
			      <c:out value="${talentDto.cnName }" escapeXml="true"></c:out>/ 
				  <c:out value="${talentDto.enName }" escapeXml="true"></c:out>
				  <form:hidden path="talentDto.talentID" />
			   </span>
			</td>
		</tr>
		<tr height="25px">
			<td>
			   <span style="font: 12px Arial">&nbsp;&nbsp;项目编号/名称：<span class="mandatoryField">*</span><form:hidden path="projectDto.systemProjectRefNum" /></span>&nbsp;&nbsp;&nbsp;&nbsp;
			   <span id="projectName" style="font: 12px Arial" ></span>
			</td>
		</tr>
		<tr>
			<td class="seprator" colspan="6"></td>
		</tr>
	</table>
	<div class="emptyBlock"></div>
	<div style="margin: 5px">
	<table class="standardTableForm" border="0" cellspacing="0" cellpadding="0" width="100%">
	   <common:standardTableRow />
	   <tr>
	      <td class="labelColumn">联系类型：<span class="mandatoryField">*</span></td>
		  <td colspan="3">
		     <form:select path="contactCategory" cssClass="standardSelect" >
		        <form:option value="" label="--- 请选择 ---"></form:option>
		        <c:forEach items="${listOfCandidateStatus}" var="cddtStatus">
				   <form:option value="${cddtStatus.statusCode }" label="${cddtStatus.displayName }"></form:option>
				</c:forEach>
			 </form:select>
		  </td>
	   </tr>
	   <tr>
	      <td class="labelColumn">详细记录：<span class="mandatoryField">*</span></td>
		  <td colspan="3">
		     <form:textarea path="record" htmlEscape="true" rows="3" cols="60"  cssStyle="word-wrap:break-word;height:80px" onkeyup="javascript:if (this.value.length>1000){this.value = this.value.substring(0, 1000)}" cssClass="standardInputText"/>
		  </td>
	   </tr>
	   <tr>
	      <td class="labelColumn">备注：</td>
		  <td colspan="3">
		     <form:input path="remark" cssClass="standardInputText" ></form:input>
		  </td>
	   </tr>
	</table>
	</div>
</form:form>
</div>