<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
<head>
<title>e-Hunter System/[EH-PRJ-0001]</title>
<hdiv-c:url value="/system/codetable/loadSourcesOfTalent.do" var="loadUrl"></hdiv-c:url>
<hdiv-c:url value="/system/codetable/checkExistsStatus.do" var="checkUrl"></hdiv-c:url>
<hdiv-c:url value="/system/codetable/handleTalentSourceDelete.do" var="deleteUrl"></hdiv-c:url>
<script type="text/javascript">
$().ready(function(){
	loadSouces();
	
	$( "#create_form" ).dialog({
        autoOpen: false,
        height: 250,
        width: 300,
        modal: true,
        buttons: {
            "提交": function() {
            	submitNewTalentSource();
            	$( this ).dialog( "close" );
            },
            "取消": function() {
                $( this ).dialog( "close" );
            }
        },
    });

	$( "#confirmDialog" ).dialog({
		autoOpen: false,
        resizable: false,
        height:140,
        modal: true,
        buttons: {
            "确认": function() {
            	var url = '${deleteUrl}';
        		url = url + '&_id=' + addZero($('#target').val(),3);
        		window.location.href=url;
            	$( this ).dialog( "close" );
            },
            "取消": function() {
                $( this ).dialog( "close" );
            }
        }
    });
});

function clearSources(){
	$('#listOfTalentSrc tr:gt(0)').remove();
}

function addZero(str,size){
	var value = new String(str);
	if(value != null && value != ''){
		for(var i=0;value.length<size;i++){
			value='0'+value;
		}
	}
	return value;
}

function handleDelete(sourceID){
	$('#target').val(sourceID);
	$( "#confirmDialog" ).dialog( "open" );
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
				var officialSite = $(this).find("officialSite").text()=='' ? '&nbsp;' : $(this).find("officialSite").text();
				var activeIndicator = $(this).find("activeIndicator").text()=='Y' ? '是' : '否';
				str = str + "<tr height='30px'>" 
				          + "<td align='center'>" + count + "</td>"
				          + "<td>" + displayName + "</td>"
				          + "<td>" + officialSite + "</td>"
				          + "<td>" + activeIndicator + "</td>"
				          + "<td align='center'>" 
				          + "<img src='${imagePath}/icon/delete.gif' title='删除' style='vertical-align: middle; cursor: pointer;' onclick='handleDelete("+ sourceID +");'/>"
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

function checkExistStatus(officialSite){
	var isExists = false;
	$.ajax({
		type:'post',
		data:{'officialSite':officialSite},
		url:'${checkUrl}',
		dataType:'xml',
		async: false,  
		success:function(xml){	
			isExistsValue = $(xml).find('isExists').text();
			if(isExistsValue == 'Y'){
				isExists = true;
			}else {
				isExists = false;
			}
		},
		error:function(){
			alert('系统错误');
		}
	});
	return isExists;
}

function submitNewTalentSource(){
	var displayName = document.getElementById('displayName_create');
	if(displayName == null || displayName.value == ''){
		alert('必须提供显示名称！');
		return ;
	}
	
	if(validateStringlength(displayName.value , 50)){
		alert('显示名称已超过最大长度50字节，请修改！');
		return ;
	}
	
	var officialSite = document.getElementById('officialSite_create');
	if(validateStringlength(officialSite.value , 50)){
		alert('官方网址已超过最大长度50字节，请修改！');
		return ;
	}
	
	if(officialSite != null && officialSite.value != ''){
		if(checkExistStatus(officialSite.value) == true){
			alert('该人才来源已经存在，请确认！');
			return ;
		}
	}
	
	$("#newTalentSourceForm").ajaxSubmit({
		success:function(){
			loadSouces();
		}
	});
}

function initialize(){	
	$("#displayName_create").val('');
	$("#officialSite_create").val('');
}

function popUpCreator(){
	initialize();
	$( "#create_form" ).dialog( "open" );
}
</script>
</head>
<body>
    <hdiv-c:url value="/system/initCodetablesSearch.do" var="backUrl"></hdiv-c:url>
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
						   <input class="standardButton" type="button" value="新增" onclick="popUpCreator();"/>&nbsp;
						   <input class="standardButton" type="button" value="返回" onclick="location.href='${backUrl}'">
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
	<jsp:include page="talentSourceCreate_pop.jsp"></jsp:include>
	<table id="bg2" border="0" width="100%">
		<tr>
			<td class="functionMenuBar">
				<table align="right" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
						   <input class="standardButton" type="button" value="新增" onclick="popUpCreator();"/>&nbsp;
						   <input class="standardButton" type="button" value="返回" onclick="location.href='${backUrl}'">
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<div id="confirmDialog" title="Confirmation">
	   <p>
	      <span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>确认删除该人才来源？
	      <input id="target" type="hidden" />
	   </p>
	</div>
</body>
</html>
