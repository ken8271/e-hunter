<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>

<div id="fade" class="black_overlay"></div>
<div id="light" class="white_content">
	<table class="contentTableBody1" cellspacing="0">
	    <tr>
			<td class="note">
				提醒：请选择新增/填写简历方式
				 <div class="emptyBlock"></div>
			</td>
		</tr>
		<tr>
			<td align="left" >
				&nbsp;<input id="manual" type="radio" value="manual" name="type" checked/>手动输入人才简历.
			</td>
		</tr>
		<tr>	
			<td align="left">
				&nbsp;<input id="import" type="radio" value="import" name="type" />批量导入人才简历.
			</td>
		</tr>
	</table>
	
	<div class="emptyBlock"></div>
	<table id="bg2" border="0" width="100%">
		<tr>
			<td class="functionMenuBar">
				<table align="right" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
						   <input class="standardButton" type="button" value="确定" onclick="submit();document.getElementById('light').style.display = 'none'; document.getElementById('fade').style.display = 'none';showAllObject();"/>&nbsp;
						   <input class="standardButton" type="button" value="取消" onclick="document.getElementById('light').style.display = 'none';document.getElementById('fade').style.display = 'none';showAllObject();">
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</div>

