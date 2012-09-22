<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>e-Hunter System/[EH-PRJ-0001]</title>
<hdiv-c:url value="/customer/loadContactHistories.do" var="loadContactHistoriesUrl"></hdiv-c:url>
<hdiv-c:url value="/customer/viewContactHistoryDetail.do" var="viewContactHistoryDetailUrl"></hdiv-c:url>
<script type="text/javascript">
$().ready(function(){
	loadContactHistories();
});

function clearHistories(){
	$('#historyTable tr:gt(0)').remove();
}

function loadContactHistories(){
	$().progressDialog.showDialog("");
	clearHistories();
	var _id = '${customerDto.systemCustRefNum}';
	$.ajax({
		type:'post',
		url:'${loadContactHistoriesUrl}',
		dataType:'xml',
		data:{'_id':_id},
		success:function(xml){	
			var str = '';
			var count = 0;
			$(xml).find('history').each(function(i , element){
				count++;
				var systemContactRefNum = $(this).find("systemContactRefNum").text();
				var responsePersonName = $(this).find("responsePersonName").text();
				var adviser = $(this).find("adviser").text();
				var contactDate = $(this).find("contactDate").text();
				
				str = str + "<tr height='30px'>" 
				          + "<td align='center'>" + count + "</td>"
				          + "<td>" + responsePersonName + "</td>"
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
				document.getElementById('systemCustomerRefNum').innerHTML = $(this).find("systemCustomerRefNum").text();
				document.getElementById('customerName').innerHTML = $(this).find("customerName").text();
				document.getElementById('adviser').innerHTML = $(this).find("adviser").text();
				document.getElementById('contactDate').innerHTML = $(this).find("contactDate").text();
				document.getElementById('view_rp').innerHTML = $(this).find("responsePersonName").text();
				document.getElementById('view_record').innerHTML = $(this).find("content").text();
				document.getElementById('view_remark').innerHTML = $(this).find("remark").text();
			});	
		},
		error:function(){
			alert('系统错误');
		}
	});
	
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
			loadContactHistories();
		}
	});
}

function initialize(){	
	document.getElementById('customerDto.systemCustRefNum').value = '${customerDto.systemCustRefNum }';
	document.getElementById('responsePersonDto.systemRespRefNum').value = '';
	$("#content").val('');
	$("#remark").val('');
}

function popUpSelector(){
	initialize();
	setPopUpFramePosition('create_light',600,270);
	setOverlayDimension('create_fade');	
	popUpFrame('create_light','create_fade');
}
</script>
</head>
<body>
        <hdiv-c:url value="/customer/customersSearch.do?module=3" var="backUrl"></hdiv-c:url>
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">客户公司联系记录</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
				<tbody>
					<common:standardTableRow />
					<tr>
					    <td class="labelColumn">客户编号：</td>
						<td colspan="3">
						     <c:out value="${customerDto.systemCustRefNum }" escapeXml="true"></c:out>&nbsp;&nbsp;&nbsp;&nbsp; 
						     <img src="${imagePath }/icon/tips.gif" title="查看客户资料" style="vertical-align: middle; cursor: pointer;" onclick="var customerInfoWindow = window.open('${viewCustomerUrl}','customerInfoWindow', 'directories=no,height=550,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680,top=100,left=200');" />
						</td>
					</tr>
					<tr>
					    <td class="labelColumn">公司全称/简称：</td>
						<td colspan="3">
						     <c:out value="${customerDto.fullName }" escapeXml="true"></c:out>/ 
						     <c:out value="${customerDto.shortName }" escapeXml="true"></c:out>
						</td>
					</tr>
				</tbody>
		</table>
		<div class="emptyBlock"></div>
		<table id="historyTable" width="100%"  border="1" cellspacing="0" cellpadding="1" class="standardTableForm">
		   <tr>
		      <td align="center" width="5%" class="labelColumn">序号</td>
		      <td width="30%" class="labelColumn">联系人(客户方)</td>
		      <td width="10%" class="labelColumn">联系顾问</td>
		      <td width="20%" class="labelColumn">联系时间</td>
		      <td align="center" width="5%" class="labelColumn">查看</td>
		   </tr>
		</table>
		<div class="emptyBlock"></div>
		<jsp:include page="customerContactRecordCreate_pop.jsp"></jsp:include>
		<jsp:include page="customerContactRecordView_pop.jsp"></jsp:include>
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