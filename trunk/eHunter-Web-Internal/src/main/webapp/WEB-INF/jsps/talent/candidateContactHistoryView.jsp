<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>e-Hunter System/[EH-PRJ-0001]</title>
<hdiv-c:url value="/talent/loadContactHistories.do" var="loadContactHistoriesUrl"></hdiv-c:url>
<script type="text/javascript">
$().ready(function(){
	$("#projectSelector").val('${projectID}');
	loadHistories();
	if('${projectID}' != null && '${projectID}' != ''){
		$("#projectSelector").attr('disabled' , true);
	}else {
		$("#projectSelector").attr('disabled' , false);
	}
});

function clearHistories(){
	$('#historyTable tr:gt(0)').remove();
}

function loadHistories(){
	clearHistories();
	var projectSelector = document.getElementById("projectSelector");
	if(projectSelector != null && projectSelector.selectedIndex != 0){
		$().progressDialog.showDialog("");
		var _tid = '${talentDto.talentID}';
		var _pid = projectSelector.options[projectSelector.selectedIndex].value;
		$.ajax({
			type:'post',
			url:'${loadContactHistoriesUrl}',
			dataType:'xml',
			data:{'_tid':_tid,'_pid':_pid},
			success:function(xml){	
				
				var str = '';
				var count = 0;
				$(xml).find('history').each(function(i , element){
					count++;
					var systemContactRefNum = $(this).find("systemContactRefNum").text();
					var contactCategory = $(this).find("contactCategory").text();
					var adviser = $(this).find("adviser").text();
					var contactDate = $(this).find("contactDate").text();
					
					str = str + "<tr height='30px'>" 
					          + "<td align='center'>" + count + "</td>"
					          + "<td>" + contactCategory + "</td>"
					          + "<td>" + adviser + "</td>"
					          + "<td>" + contactDate + "</td>"
					          + "<td align='center'><img src='${imagePath}/icon/tips.gif' title='查看联系记录' style='vertical-align: middle; cursor: pointer;'/></td>"
					          + "</tr>";
				});
				
				$(str).appendTo("#historyTable");
				$().progressDialog.hideDialog("");
			},
			error:function(){
				$().progressDialog.hideDialog("");
				alert('系统错误');
			}
		});
	}else{
		clearHistories();
	}
}
</script>
</head>
<body>
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">候选人联系记录</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
				<tbody>
					<common:standardTableRow />
					<tr>
					    <td class="labelColumn">人才编号：</td>
						<td colspan="3">
						     <c:out value="${talentDto.talentID }" escapeXml="true"></c:out>&nbsp;&nbsp;&nbsp;&nbsp; 
						     <img src="${imagePath }/icon/tips.gif" title="查看人才资料" style="vertical-align: middle; cursor: pointer;" onclick="var customerInfoWindow = window.open('${viewProjectUrl}','customerInfoWindow', 'directories=no,height=550,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680,top=100,left=200');" />
						</td>
					</tr>
					<tr>
					    <td class="labelColumn">中文名/英文名：</td>
						<td colspan="3">
						     <c:out value="${talentDto.cnName }" escapeXml="true"></c:out>/ 
						     <c:out value="${talentDto.enName }" escapeXml="true"></c:out>
						</td>
					</tr>
				</tbody>
		</table>
		<div class="emptyBlock"></div>
		<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
				<tbody>
					<common:standardTableRow />
					<tr>
						<td class="labelColumn">项目编号/名称：</td>
						<td>
						   <select id="projectSelector" class="standardSelect" onchange="loadHistories();">
						      <option value="">--- 请选择  ---</option>
						      <c:forEach items="${listOfParticipatedProject }" var="project">
						         <option value="${project.systemProjectRefNum }">${project.systemProjectRefNum }/${project.projectName }</option>
						      </c:forEach>
						   </select>
						</td>
						<td colspan="2"></td>
					</tr>
				</tbody>
		</table>
		<div class="emptyBlock"></div>
		<table id="historyTable" width="100%"  border="1" cellspacing="0" cellpadding="1" class="standardTableForm">
		   <tr>
		      <td align="center" width="5%" class="labelColumn">序号</td>
		      <td width="30%" class="labelColumn">联系类型</td>
		      <td width="10%" class="labelColumn">联系顾问</td>
		      <td width="20%" class="labelColumn">联系时间</td>
		      <td align="center" width="5%" class="labelColumn">查看</td>
		   </tr>
		</table>
		<div class="emptyBlock"></div>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							    <input class="standardButton" type="button" value="新增" onclick="location.href='${addCandiateUrl}'"/>&nbsp;
								<input class="standardButton" type="button" value="移除">
								<input class="standardButton" type="button" value="返回" onclick="location.href='${backUrl}'">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
</body>
</html>