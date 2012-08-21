<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>e-Hunter System/[EH-TLNT-0001]</title>
<script type="text/javascript">
$().ready(function(){
	if('${clearField}' == 'Y'){
		clearInputFields();
	}
});

function clearInputFields(){
	document.getElementById('gainedDateDto.year').value='';
	document.getElementById('gainedDateDto.month').value='';
	document.getElementById('gainedDateDto.day').value='';
	$('#certName').val('');
	$('#description').val('');
}

function complete(actionFlagStr){
	var actionFlag = document.getElementById('actionFlag');
	if(actionFlag != 'undefined' && actionFlagStr != ""){
		actionFlag.value = actionFlagStr;
	}
	var form = document.getElementById('certForm');
	form.submit();
}

function submitDelete(listName){
	var isSubmit = false;
	
	for( var i = 0; i < document.getElementsByName(listName).length ; i++){ 		
		if(document.getElementsByName(listName).item(i).checked == true){
			isSubmit = true;
			break;
		} 				
	}	

	if(isSubmit == true){
		if(confirm("确认删除被选择的记录？") == true){
			var form = document.getElementById('certsForm');
	        form.submit();
		}
	}else{
		alert('请至少选择一项进行删除！');
	}
}
</script>
</head>
<body>
	<form:form id="certForm" commandName="certDto" action="${ctx}/talent/addCertActions.do" method="post">
	    <div style="display: none">
	       <input type="hidden" id="actionFlag" name="actionFlag"/>
	    </div>
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">证书填写</td>
			</tr>
			<tr>
				<td><common:errorTable path="certDto"></common:errorTable></td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<div class="contentTableBody">
			<div style="height:5px"></div>
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%" >
				<tbody>
				    <common:standardTableRow />
				    <tr >
						<td class="labelColumn">证书名称：<span class="mandatoryField">*</span></td>
						<td>
						   <form:input path="certName" cssClass="standardInputText"  /> 
					       <common:errorSign id="certName" path="certName"></common:errorSign>
						</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td class="labelColumn">获得时间：<span class="mandatoryField">*</span></td>
						<td>
						<common:inputDate dateYY="gainedDateDto.year" dateMON="gainedDateDto.month" dateDD="gainedDateDto.day" ></common:inputDate>
						<common:errorSign id="gainedDateDto.day" path="gainedDateDto.day"></common:errorSign>
						</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>   
					<tr>
						<td class="labelColumn">说明：</td>
						<td colspan="3">
						  <form:textarea path="description" htmlEscape="true" rows="3" cols="60"  cssStyle="word-wrap:break-word;height:80px" onkeyup="javascript:if (this.value.length>300){this.value = this.value.substring(0, 300)}" cssClass="standardInputText"/>
						  <common:errorSign id="description" path="description"></common:errorSign>
						</td>
					</tr>
				</tbody>
			</table>			
		</div>
		<div class="emptyBlock"></div>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							<input class="standardButton" type="button" value="添加" onclick="complete('14');" />&nbsp;
							<input class="standardButton" type="button" value="清除" onclick="clearInputFields();" />&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form:form>
	<div class="emptyBlock"></div>
	<form:form id="certsForm" commandName="certDto" action="${ctx }/talent/deleteCert.do">
		<table class="contentTableBody2" cellspacing="1" width="100%">
		   <tr class="contentTableTitle">
		      <td width="10%" align="center">全选</td>
		      <td width="50%">证书名称</td>
		      <td width="30%">获得时间</td>
		      <td width="10%" align="center">操作</td>
		   </tr>
		   <c:if test="${not empty resumeDto.certDtos }">
		      <c:forEach items="${resumeDto.certDtos }" var="cert" varStatus="status">
		         <tr class="contentTableRow">
		            <td><input type="checkbox" name="expsList" value="${status.index }"/>&nbsp;${status.index+1 }</td>
		            <td><c:out value="${cert.certName }" escapeXml="true"></c:out></td>
		            <td>
		            <c:out value="${cert.gainedDateDto.year }" escapeXml="true"/>-
		            <c:out value="${cert.gainedDateDto.month }" escapeXml="true"/>-
		            <c:out value="${cert.gainedDateDto.day }" escapeXml="true"/>
		            </td>
		            <td align="center"> 
		            <hdiv-c:url value="/talent/preEditCert.do?_id=${status.index }" var="editCertUrl"></hdiv-c:url>
		            <input class="standardButton" type="button" value="编辑" onclick="location.href='${editCertUrl }'" />&nbsp;
		            </td>
		         </tr>
		      </c:forEach>
		   </c:if>
		</table>
		<div class="emptyBlock"></div>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							 <input class="standardButton" type="button" value="删除" onclick="submitDelete('expsList');" />&nbsp;
							 <input class="standardButton" type="button" value="保存" onclick="complete('6');" />&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>