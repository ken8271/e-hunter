<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>

<div id="create_fade" class="black_overlay"></div>
<div id="create_light" class="white_content_without_padding">
<form:form id="newTalentSourceForm" commandName="talentSourceDto" action="${ctx }/system/codetable/submitNewTalentSource.do">
	<table cellspacing="0" cellpadding="0" width="100%">
		<tr class="popUpTitle">
			<td width="70%"><span>&nbsp;&nbsp;人才来源&nbsp;-&nbsp;创建</span>
			</td>
			<td width="15%" align="right">
			   <span style="cursor: pointer; font: bold 14px Arial" onclick="submitNewTalentSource();">[提交]</span>
			</td>
			<td width="15%" align="center">
			   <span style="cursor: pointer; font: bold 14px Arial" onclick="document.getElementById('create_light').style.display = 'none';document.getElementById('create_fade').style.display = 'none';showAllObject();">[取消]</span>
			</td>
		</tr>
	</table>
	<div class="emptyBlock"></div>
	<table class="standardTableForm" border="0" cellspacing="0" cellpadding="0" width="100%">
	   <common:standardTableRow />
	   <tr>
	      <td class="labelColumn">&nbsp;&nbsp;名称(显示名称)：<span class="mandatoryField">*</span></td>
		  <td colspan="3">
		    <form:input id="displayName_create" path="displayName" cssClass="standardInputText" ></form:input>
		  </td>
	   </tr>
	   <tr>
	      <td class="labelColumn">&nbsp;&nbsp;官方网站：</td>
		  <td colspan="3">
		     <form:input id="officialSite_create" path="officialSite" cssClass="standardInputText" ></form:input>
		  </td>
	   </tr>
	   <tr>
	      <td class="labelColumn">&nbsp;&nbsp;是否启用：<span class="mandatoryField">*</span></td>
		  <td colspan="3">
		     <form:radiobutton id="activeIndicator_create_Y" path="activeIndicator" value="Y" /> 是&nbsp;&nbsp;
			 <form:radiobutton id="activeIndicator_create_N" path="activeIndicator" value="N" /> 否&nbsp;&nbsp;
		  </td>
	   </tr>
	</table>
</form:form>
</div>