<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>

<div id="view_fade" class="black_overlay"></div>
<div id="view_light" class="white_content_without_padding">
	<table cellspacing="0" cellpadding="0" width="100%">
		<tr class="popUpTitle">
			<td width="70%"><span>&nbsp;&nbsp;用户管理&nbsp;-&nbsp;用户详细资料</span>
			</td>
			<td width="30%" align="right">
			   <span style="cursor: pointer; font: bold 14px Arial" onclick="document.getElementById('view_light').style.display = 'none';document.getElementById('view_fade').style.display = 'none';showAllObject();">[关闭]&nbsp;&nbsp;</span>
			</td>
		</tr>
	</table>
	<div class="emptyBlock"></div>
	<div style="margin-left:5px;margin-right: 5px">
	<table class="contentTableBody1" cellspacing="0" cellpadding="0" width="100%">
		<common:standardTableRow />
		<tr class="contentTableRow1">
		   <td width="20%"><span style="color: #00688B">用户编号：</span></td>
		   <td><span id="view_userRecId" style="font: 12px Arial;" ></span></td>
		   <td colspan="2">&nbsp;</td>
		</tr>
		<tr>
			<td class="seprator" colspan="4"></td>
		</tr>
	</table>
	</div>
	<div class="emptyBlock"></div>
	<div style="margin-left:5px;margin-right: 5px">
	<table class="contentTableBody1" cellspacing="0" cellpadding="0" width="100%">
		<common:standardTableRow />
		<tr class="contentTableRow1">
		   <td><span style="color: #00688B">登录名：</span></td>
		   <td colspan="3"><span id="view_loginId" style="font: 12px Arial"></span></td>
		</tr>
		<tr class="contentTableRow1">
		   <td><span style="color: #00688B">员工号：</span></td>
		   <td colspan="3"><span id="view_staffId" style="font: 12px Arial"></span></td>
		</tr>
		<tr class="contentTableRow1">
		   <td><span style="color: #00688B">中文名/英文名：</span></td>
		   <td colspan="3"><span id="view_name" style="font: 12px Arial"></span></td>
		</tr>
		<tr class="contentTableRow1">
		   <td><span style="color: #00688B">邮箱：</span></td>
		   <td><span id="view_email" style="font: 12px Arial"></span></td>
		   <td><span style="color: #00688B">手机：</span></td>
		   <td><span id="view_mobile" style="font: 12px Arial"></span></td>
		</tr>
		<tr class="contentTableRow1">
		   <td><span style="color: #00688B">用户角色：</span></td>
		   <td><span id="view_role" style="font: 12px Arial"></span></td>
		   <td><span style="color: #00688B">帐号状态：</span></td>
		   <td><span id="view_accountStatus" style="font: 12px Arial"></span></td>
		</tr>
	</table>
	</div>
	</div>
</div>