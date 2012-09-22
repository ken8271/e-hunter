<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>

<div id="create_fade" class="black_overlay"></div>
<div id="create_light" class="white_content_without_padding">
<form:form id="newContactHistoryForm" commandName="customerContactHistoryDto" action="${ctx }/customer/submitContactHistory.do">
	<table cellspacing="0" cellpadding="0" width="100%">
		<tr class="popUpTitle">
			<td width="70%"><span>&nbsp;&nbsp;客户公司联系记录&nbsp;-&nbsp;创建</span>
			</td>
			<td width="15%" align="right">
			   <span style="cursor: pointer; font: bold 14px Arial" onclick="submitContactHistory();document.getElementById('create_light').style.display = 'none';document.getElementById('create_fade').style.display = 'none';showAllObject();">[提交]</span>
			</td>
			<td width="15%" align="center">
			   <span style="cursor: pointer; font: bold 14px Arial" onclick="document.getElementById('create_light').style.display = 'none';document.getElementById('create_fade').style.display = 'none';showAllObject();">[取消]</span>
			</td>
		</tr>
	</table>
	<div class="emptyBlock"></div>
	<table cellspacing="0" cellpadding="0" width="100%">
		<tr height="25px">
			<td>
			   <span style="font: 12px Arial">&nbsp;&nbsp;客户编号：</span>&nbsp;&nbsp;&nbsp;&nbsp;
			   <span style="font: 12px Arial">
			      <c:out value="${customerDto.systemCustRefNum}" escapeXml="true"></c:out>
				  <form:hidden path="customerDto.systemCustRefNum" />
			   </span>
			</td>
		</tr>
		<tr height="25px">
			<td>
			   <span style="font: 12px Arial">&nbsp;&nbsp;客户名称：</span>&nbsp;&nbsp;&nbsp;&nbsp;
			   <span style="font: 12px Arial" >
			      <c:out value="${customerDto.fullName}" escapeXml="true"></c:out>
			   </span>
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
	      <td class="labelColumn">客户公司联系人：</td>
		  <td colspan="3">
		     <form:select path="responsePersonDto.systemRespRefNum" cssClass="standardSelect" >
		        <form:option value="" label="--- 请选择 ---"></form:option>
		        <c:forEach items="${listOfResponsePerson}" var="rp">
				   <form:option value="${rp.systemRespRefNum }" label="${rp.name }"></form:option>
				</c:forEach>
			 </form:select>
		  </td>
	   </tr>
	   <tr>
	      <td class="labelColumn">详细记录：</td>
		  <td colspan="3">
		     <form:textarea path="content" htmlEscape="true" rows="3" cols="60"  cssStyle="word-wrap:break-word;height:80px" onkeyup="javascript:if (this.value.length>1000){this.value = this.value.substring(0, 1000)}" cssClass="standardInputText"/>
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