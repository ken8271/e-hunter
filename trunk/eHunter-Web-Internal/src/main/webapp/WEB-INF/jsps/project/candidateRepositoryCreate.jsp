<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${ctx }/style/jquery-ui.css" />
<title>e-Hunter System/[EH-PRJ-0001]</title>
<script type="text/javascript" src="${scriptPath }/jquery-ui.js"></script>
<script type="text/javascript">
$().ready(function(){
	$( document ).tooltip();
	if('${talentEnquireDto.queryMode}' == 'F'){
	   $("#tabs").tabs({ active: 1 });
	}else {
	   $("#tabs").tabs({ active: 0 });
	   $('#full_match').attr('checked',false);
	   $('#partial_match').attr('checked',false);
	}
});

function checkCandidateSelection(){
	var selectCount = 0;
	var selections = document.getElementsByName("jmesaDto.select");
	if(selections != 'undefined'){
		for(var i=0 ; i<selections.length ; i++){
			if(selections[i].checked){
				selectCount++;
			}
		}
	}
	if(selectCount==0){
		alert('请至少选择一名候选人才!');
		return;
	}
	
	submitForm('5');
}

function submitForm(actionFlagStr){
	var actionFlag = document.getElementById('actionFlag');
	if(actionFlag != 'undefined' && actionFlagStr != ""){
		actionFlag.value = actionFlagStr;
	}
	document.forms[0].submit();
}

function changeSearchMode(tabBtn){
	if('precise_inquiry_btn' == tabBtn){
		$('#keywords').val('');
		$('#full_match').attr('checked',false);
		$('#partial_match').attr('checked',false);
		$('#queryMode').val('P');
		return ;
	}
	
	if('fuzzy_query_btn' == tabBtn){
		$('#talentID').val('');
		$('#name').val('');
		$('#queryMode').val('F');
		return ;
	}
}
</script>
<common:jmesaScript actionFlagStr="90"></common:jmesaScript>
</head>
<body>
    <hdiv-c:url value="/project/viewCandidateRepository.do" var="backUrl"></hdiv-c:url>
	<form:form commandName="talentEnquireDto" action="${ctx}/project/appendCandidateRepositoryActions.do" method="post">
		<div style="display: none">
			<input type="hidden" id="actionFlag" name="actionFlag" />
			<form:hidden path="queryMode"/>
		</div>
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">项目人才库-添加候选人才</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<div id="tabs">
		   <ul>
		      <li><a id="precise_inquiry_btn" href="#precise_inquiry" onclick="changeSearchMode('precise_inquiry_btn');">精确查询</a></li>
		      <li><a id="fuzzy_query_btn" href="#fuzzy_query" onclick="changeSearchMode('fuzzy_query_btn')">模糊查询</a></li>
		   </ul>
		   <div id="precise_inquiry">
		      <table border="0" cellspacing="5" cellpadding="0" width="100%">
				<tr>
					<td width="20%">人才编号：</td>
					<td width="30%"><form:input path="talentID" cssClass="standardInputText"></form:input></td>
					<td width="50%">&nbsp;</td>
				</tr>
				<tr>
					<td width="20%">中文名/英文名：</td>
					<td width="30%"><form:input path="name" cssClass="standardInputText"></form:input></td>
					<td width="50%">&nbsp;</td>
				</tr>
		      </table>
		   </div>
		   <div id="fuzzy_query">
		       <table border="0" cellspacing="5" cellpadding="0" width="100%">
				<tr>
					<td width="20%" rowspan="2">简历关键词：</td>
					<td width="30%"><form:input path="keywords" cssClass="standardInputText" title="多个关键词请用空格分隔"></form:input></td>
					<td width="50%">&nbsp;</td>
				</tr>
				<tr>
					<td>
					   <form:radiobutton id="full_match" path="matchMode" value="F" />全部匹配&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					   <form:radiobutton id="partial_match" path="matchMode" value="P" />部分匹配
					</td>
				</tr>
		      </table>
		   </div>
		</div>
		<div class="emptyBlock"></div>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td><input class="standardButton" type="button" value="查询" onclick="submitForm(1);"/>&nbsp;
								<input class="standardButton" type="reset" value="重置">&nbsp;
								<c:if test="${type == '1'}">
								   <input class="standardButton" type="button" value="返回" onclick="location.href='${backUrl}'" />
								</c:if>
								<c:if test="${type == '2' }">
								   <input class="standardButton" type="button" value="结束" onclick="location.href='${ctx}/index.do'" />
								</c:if>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<c:if test="${not empty listOfTalent }">
		<div class="emptyBlock"></div>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
				   <b>Point(s) to note:</b><br>  查询结果已滤除已参加该项目的候选人才。
				</td>
			</tr>
		</table>
		</c:if>
		<div class="emptyBlock"></div>
		<div>${listOfTalent}</div>
		<div class="emptyBlock"></div>
		<c:if test="${not empty listOfTalent}">
		   <table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
								<input class="standardButton" type="button" value="添加到项目人才库" onclick="checkCandidateSelection();">&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
		   </table>
		</c:if>
	</form:form>
</body>
</html>