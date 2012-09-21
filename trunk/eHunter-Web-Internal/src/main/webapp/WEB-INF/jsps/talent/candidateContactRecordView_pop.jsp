<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>

<div id="view_fade" class="black_overlay"></div>
<div id="view_light" class="white_content_without_padding">
	<table cellspacing="0" cellpadding="0" width="100%">
		<tr class="popUpTitle">
			<td width="70%"><span>&nbsp;&nbsp;候选人联系记录&nbsp;-&nbsp;详细</span>
			</td>
			<td width="30%" align="right">
			   <span style="cursor: pointer; font: bold 14px Arial" onclick="document.getElementById('view_light').style.display = 'none';document.getElementById('view_fade').style.display = 'none';showAllObject();">[关闭]&nbsp;&nbsp;</span>
			</td>
		</tr>
	</table>
	<div class="emptyBlock"></div>
	<table cellspacing="0" cellpadding="0" width="100%">
		<tr height="25px">
			<td>
			   <span style="font: 12px Arial">&nbsp;&nbsp;中文名/英文名：</span>&nbsp;&nbsp;&nbsp;&nbsp;
			   <span id="candidateName" style="font: 12px Arial"></span>
			</td>
		</tr>
		<tr height="25px">
			<td>
			   <span style="font: 12px Arial">&nbsp;&nbsp;项目编号/名称：</span>&nbsp;&nbsp;&nbsp;&nbsp;
			   <span id="systemProjectRefNum" style="font: 12px Arial"></span>
			</td>
		</tr>
		<tr height="25px">
			<td>
			   <span style="font: 12px Arial">&nbsp;&nbsp;联系顾问：</span>&nbsp;&nbsp;&nbsp;&nbsp;
			   <span id="adviser" style="font: 12px Arial"></span>
			</td>
		</tr>
		<tr height="25px">
			<td>
			   <span style="font: 12px Arial">&nbsp;&nbsp;联系时间：</span>&nbsp;&nbsp;&nbsp;&nbsp;
			   <span id="contactDate" style="font: 12px Arial"></span>
			</td>
		</tr>
		<tr>
			<td class="seprator" colspan="6"></td>
		</tr>
	</table>
	<div class="emptyBlock"></div>
	<div style="margin: 5px">
	<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
	   <common:standardTableRow />
	   <tr>
	      <td class="labelColumn">联系类型：</td>
		  <td colspan="3"><span id="view_contactCategory" style="font: 12px Arial"></span></td>
	   </tr>
	   <tr height="50px">
	      <td class="labelColumn">详细记录：</td>
		  <td colspan="3"><span id="view_record" style="font: 12px Arial"></span></td>
	   </tr>
	   <tr>
	      <td class="labelColumn">备注：</td>
		  <td colspan="3"><span id="view_remark" style="font: 12px Arial"></span></td>
	   </tr>
	</table>
	</div>
</div>