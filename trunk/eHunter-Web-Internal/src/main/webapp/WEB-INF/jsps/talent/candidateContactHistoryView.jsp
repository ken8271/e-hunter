<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>e-Hunter System/[EH-PRJ-0001]</title>
<hdiv-c:url value="/talent/loadContactHistories.do" var="loadContactHistoriesUrl"></hdiv-c:url>
<hdiv-c:url value="/talent/viewContactHistoryDetail.do" var="viewContactHistoryDetailUrl"></hdiv-c:url>
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
					          + "<td align='center'><img src='${imagePath}/icon/tips.gif' title='查看联系记录' style='vertical-align: middle; cursor: pointer;' onclick='popUpContactDetail(" + systemContactRefNum +");'/></td>"
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

function initialize(){
	var projectName = '';
	var projectSelector = document.getElementById("projectSelector");
	if(projectSelector != null && projectSelector.selectedIndex != 0){
		projectName = projectSelector.options[projectSelector.selectedIndex].text;
		document.getElementById('projectDto.systemProjectRefNum').value = projectSelector.options[projectSelector.selectedIndex].value;
	}
	
	document.getElementById('projectName').innerHTML = projectName;
	document.getElementById('talentDto.talentID').value = '${talentDto.talentID }';
	
	$("#contactCategory").val('');
	$("#record").val('');
	$("#remark").val('');
}

function popUpSelector(){
	var projectSelector = document.getElementById("projectSelector");
	if(projectSelector == null || projectSelector.selectedIndex == 0){
		alert('请选择已参与项目');
		return ;
	}
	
	initialize();
	setPopUpFramePosition('light',600,270);
	setOverlayDimension('fade');	
	popUpFrame('light','fade');
}

function popUpContactDetail(systemContactRefNum){
	if(systemContactRefNum == null || systemContactRefNum == '') return ;
	
	$.ajax({
		type:'post',
		url:'${viewContactHistoryDetailUrl}',
		dataType:'xml',
		data:{'_id':systemContactRefNum},
		success:function(xml){	
			$(xml).find('history').each(function(i , element){
				document.getElementById('candidateName').innerHTML = $(this).find("candidateName").text();
				document.getElementById('systemProjectRefNum').innerHTML = $(this).find("systemProjectRefNum").text() + '&nbsp;/&nbsp;' +$(this).find("projectName").text();
				document.getElementById('adviser').innerHTML = $(this).find("adviser").text();
				document.getElementById('contactDate').innerHTML = $(this).find("contactDate").text();
				document.getElementById('view_contactCategory').innerHTML = $(this).find("contactCategory").text();
				document.getElementById('view_record').innerHTML = $(this).find("record").text();
				document.getElementById('view_remark').innerHTML = $(this).find("remark").text();
			});	
		},
		error:function(){
			alert('系统错误');
		}
	});
	
	//setPopUpFramePosition(600,270);
	var leftOffset = (getClientWidth() - 600) / 2;
	var topOffset = (getClientHeight() - 270) / 2;
	$('#view_light').css( {
		"position" : "absolute",
		"height" : 270 + "px",
		"width" : 600 + "px",
		"left" : leftOffset + "px",
		"top" : topOffset + "px"
	});
	setOverlayDimension('view_fade');	
	popUpFrame('view_light','view_fade');
}

function submitContactHistory(){
	$("#newContactHistoryForm").ajaxSubmit({
		success:function(){
			loadHistories();
		}
	});
}
</script>
</head>
<body>
        <c:if test="${module == '9' }">
           <hdiv-c:url value="/project/viewCandidateRepository.do" var="backUrl"></hdiv-c:url>
        </c:if>
        <c:if test="${module == '6' }">
           <hdiv-c:url value="/talent/candidatesSearch.do" var="backUrl"></hdiv-c:url>
        </c:if>
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
		<jsp:include page="candidateContactRecordCreate_pop.jsp"></jsp:include>
		<jsp:include page="candidateContactRecordView_pop.jsp"></jsp:include>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							    <input class="standardButton" type="button" value="新增" onclick="popUpSelector();"/>&nbsp;
								<input class="standardButton" type="button" value="返回" onclick="location.href='${backUrl}'">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
</body>
</html>