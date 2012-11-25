<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>e-Hunter System/[EH-TLNT-0101]</title>
<hdiv-c:url value="/system/codetable/loadSourcesOfTalent.do" var="loadUrl"></hdiv-c:url>
<script type="text/javascript">
$().ready(function(){
	loadSouces();
});

function clearSources(){
	$('#listOfTalentSrc tr:gt(0)').remove();
}

function loadSouces(){
	$().progressDialog.showDialog("");
	clearSources();
	$.ajax({
		type:'post',
		url:'${loadUrl}',
		dataType:'xml',
		success:function(xml){	
			var str = '';
			var count = 0;
			$(xml).find('source').each(function(i , element){
				count++;
				var sourceID = $(this).find("sourceID").text();
				var displayName = $(this).find("displayName").text();
				var officialSite = $(this).find("officialSite").text();
				var activeIndicator = $(this).find("activeIndicator").text()=='Y' ? '是' : '否';
				
				str = str + "<tr height='30px'>" 
				          + "<td align='center'>" + count + "</td>"
				          + "<td>" + displayName + "</td>"
				          + "<td>" + officialSite + "</td>"
				          + "<td>" + activeIndicator + "</td>"
				          + "<td align='center'>" 
				          + "<img src='${imagePath}/icon/edit.gif' title='编辑' style='vertical-align: middle; cursor: pointer;' onclick=''/>&nbsp;"
				          + "<img src='${imagePath}/icon/delete.gif' title='删除' style='vertical-align: middle; cursor: pointer;' onclick=''/>"
				          + "</td>"
				          + "</tr>";
			});
			
			$(str).appendTo("#listOfTalentSrc");
			$().progressDialog.hideDialog("");
		},
		error:function(){
			$().progressDialog.hideDialog("");
			alert('系统错误');
		}
	});
}
</script>
</head>
<body>
	<table border="0" width="100%">
		<tr>
			<td class="pageTitle">人才来源列表</td>
		</tr>
	</table>
	<table id="bg2" border="0" width="100%">
		<tr>
			<td class="functionMenuBar">
				<table align="right" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
						   <input class="standardButton" type="button" value="新增" />&nbsp;
						   <input class="standardButton" type="button" value="返回" onclick="location.href='${ctx}/index.do'">
						</td>
					</tr>
				</table>
	        </td>
		</tr>
	</table>
	<div class="emptyBlock"></div>
	<table id="listOfTalentSrc" width="100%" border="1" cellspacing="0" cellpadding="1" class="standardTableForm">
		<tr>
			<td align="center" width="5%" class="contentTableTitile2">序号</td>
			<td width="30%" class="contentTableTitile2">名称</td>
			<td width="20%" class="contentTableTitile2">官方网站</td>
			<td width="10%" class="contentTableTitile2">是否启用</td>
			<td align="center" width="5%" class="contentTableTitile2">操作</td>
		</tr>
	</table>
	<div class="emptyBlock"></div>
	<table id="bg2" border="0" width="100%">
		<tr>
			<td class="functionMenuBar">
				<table align="right" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
						   <input class="standardButton" type="button" value="新增" />&nbsp;
						   <input class="standardButton" type="button" value="返回" onclick="location.href='${ctx}/index.do'">
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>
