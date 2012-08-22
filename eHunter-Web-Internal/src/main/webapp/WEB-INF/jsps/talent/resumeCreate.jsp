<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<%@ taglib tagdir="/WEB-INF/tags/talent" prefix="talent"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>e-Hunter System/[EH-TLNT-0001]</title>
<script type="text/javascript">
$().ready(function(){
	checkFilled();
	
	if('${operation}' == 'E'){
		$('#confirmButton_top').show();
		$('#backButton_top_edit').show();
		$('#confirmButton').show();
		$('#backButton').show();
		$('#submitButton_top').hide();
		$('#backButton_top_add').hide();
		$('#addButton').hide();
		$('#resetButton').hide();
		$('#resumesTable').hide();
	}else {
		$('#submitButton_top').show();
		$('#backButton_top_add').show();
		$('#addButton').show();
		$('#resetButton').show();
		$('#resumesTable').show();
		$('#confirmButton_top').hide();
		$('#backButton_top_edit').hide();
		$('#confirmButton').hide();
		$('#backButton').hide();
	}
});

function submitForm(actionFlagStr){
	var actionFlag = document.getElementById('actionFlag');
	if(actionFlag != 'undefined' && actionFlagStr != ""){
		actionFlag.value = actionFlagStr;
	}
	document.forms[0].submit();
}

function checkFilled(){
	var content = document.getElementById('resumeDto.selfEvaluationDto.content').value;
	if(content != ''){
		$('#blank').hide();
		$('#ok').show();
	}else{
		$('#ok').hide();
		$('#blank').show();
	}
}
</script>
</head>
<body>
    <hdiv-c:url value="/talent/backToPreviousStep.do" var="backToFillTalentInfoUrl"></hdiv-c:url>
    <hdiv-c:url value="/talent/backToFillResume.do" var="backToFillResumeUrl"></hdiv-c:url>
    <hdiv-c:url value="/talent/clearCurrResume.do" var="clearUrl"></hdiv-c:url>
	<form:form id="resumeForm" commandName="talentDto" action="${ctx}/talent/addResumeActions.do" method="post">
	    <div style="display: none">
	       <input type="hidden" id="actionFlag" name="actionFlag"/>
	    </div>
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">人才简历填写</td>
			</tr>
			<tr>
				<td><common:errorTable path="talentDto"></common:errorTable></td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							   <input id="submitButton_top" class="standardButton" type="button" value="提交" onclick="submitForm('6')"/>&nbsp;
							   <input id="backButton_top_add" class="standardButton" type="button" value="返回" onclick="location.href='${backToFillTalentInfoUrl}'" />
							   <input id="confirmButton_top" class="standardButton" type="button" value="确定" onclick="submitForm('3')" />
							   <input id="backButton_top_edit" class="standardButton" type="button" value="返回" onclick="location.href='${backToFillResumeUrl}'"/>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<table class="standardTableForm" border="1" cellspacing="0"
				cellpadding="0" width="100%">
				<tbody>
				    <common:standardTableRow />
					<tr>
						<td class="labelColumn">简历名称：<span class="mandatoryField">*</span></td>
						<td>
						<form:input path="resumeDto.name" cssClass="standardInputText" ></form:input>
						<common:errorSign id="resumeDto.name" path="resumeDto.name"></common:errorSign>
						</td>
						<td class="labelColumn">语言版本：<span class="mandatoryField">*</span></td>
						<td>
						<form:radiobutton path="resumeDto.language" value="cn" /> 中文&nbsp;/
						<form:radiobutton path="resumeDto.language" value="en" /> 英文&nbsp;
						<common:errorSign id="resumeDto.language" path="resumeDto.language"></common:errorSign>
						</td>
					</tr>
				</tbody>
		</table>
		<div class="emptyBlock"></div>
		<table width="100%">
			<tr>
				<td width="100%"><font face="Arial" size="2"><b>&nbsp;基本信息</b></font></td>
			</tr>
		</table>
		<div class="contentTableBody">
			<div style="height:5px"></div>
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%" >
				<tbody>
				    <common:standardTableRow />
					<tr>
						<td class="labelColumn">中文名：</td>
						<td><c:out value="${talentDto.cnName }" escapeXml="true"></c:out></td>
						<td class="labelColumn">英文名：</td>
						<td><c:out value="${talentDto.enName }" escapeXml="true"></c:out> </td>
					</tr>
				</tbody>
			</table>			
		</div>
		<div class="emptyBlock"></div>
		<table width="100%">
			<tr>
			    <td width="100%">
			       <font face="Arial" size="2"><b>&nbsp;求职意向</b></font>&nbsp;
			       <c:choose> 
			       <c:when test="${talentDto.resumeDto.intentionDto != null && talentDto.resumeDto.intentionDto.expectSalary != null}"><img src="${imagePath}/icon/ok.gif"></img></c:when>
			       <c:otherwise><img src="${imagePath}/icon/blank.gif"></img></c:otherwise>
			       </c:choose>
			    </td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<div class="contentTableBody">
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
			   <tr>
			      <td width="100%" align="right">
			        <div id="buttonArea">
						<div class="buttonmenubox_R">
							<a class="button" href="#" style="white-space:nowrap;" onclick="submitForm('13')">输入求职意向</a>
						</div>
					</div>
			      </td>
			   </tr>
			</table>
		</div>
		<div class="emptyBlock"></div>
		<table width="100%">
			<tr>
			    <td width="100%">
			       <font face="Arial" size="2"><b>&nbsp;教育经历</b></font>&nbsp;
			       <talent:checkSign path="${talentDto.resumeDto.eduExpDtos }"></talent:checkSign>
			    </td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<div class="contentTableBody">
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
			   <tr>
			      <td width="100%" align="right">
			        <div id="buttonArea">
						<div class="buttonmenubox_R">
							<a class="button" href="#" style="white-space:nowrap;" onclick="submitForm('7')">输入教育经历</a>
						</div>
					</div>
			      </td>
			   </tr>
			</table>
		</div>
		<div class="emptyBlock"></div>
		<table width="100%">
			<tr valign="middle">
				<td width="100%">
				   <font face="Arial" size="2"><b>&nbsp;工作经历</b></font>&nbsp;
				   <talent:checkSign path="${talentDto.resumeDto.jobExpDtos }"></talent:checkSign>
				</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<div class="contentTableBody">
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
			   <tr>
			      <td width="100%" align="right">
			        <div id="buttonArea">
						<div class="buttonmenubox_R">
							<a class="button" href="#" style="white-space:nowrap;" onclick="submitForm('8');">输入工作经历</a>
						</div>
					</div>
			      </td>
			   </tr>
			</table>
		</div>
		<div class="emptyBlock"></div>
		<table width="100%">
			<tr>
				<td width="100%">
				   <font face="Arial" size="2"><b>&nbsp;项目经验</b></font>&nbsp;
				   <talent:checkSign path="${talentDto.resumeDto.prjExpDtos }"></talent:checkSign>
				</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<div class="contentTableBody">
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
			   <tr>
			      <td width="100%" align="right">
			        <div id="buttonArea">
						<div class="buttonmenubox_R">
							<a class="button" href="#" style="white-space:nowrap;" onclick="submitForm('10');">输入项目经验</a>
						</div>
					</div>
			      </td>
			   </tr>
			</table>
		</div>
		<div class="emptyBlock"></div>
		<table width="100%">
			<tr>
				<td width="100%"><font face="Arial" size="2"><b>&nbsp;培训经历</b></font>&nbsp;
				<talent:checkSign path="${talentDto.resumeDto.trnExpDtos }"></talent:checkSign>
			</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<div class="contentTableBody">
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
			   <tr>
			      <td width="100%" align="right">
			        <div id="buttonArea">
						<div class="buttonmenubox_R">
							<a class="button" href="#" style="white-space:nowrap;" onclick="submitForm('9');">输入培训经历</a>
						</div>
					</div>
			      </td>
			   </tr>
			</table>
		</div>
		<div class="emptyBlock"></div>
		<table width="100%">
			<tr>
				<td width="100%"><font face="Arial" size="2"><b>&nbsp;语言能力</b></font>&nbsp;
				<talent:checkSign path="${talentDto.resumeDto.languageDtos }"></talent:checkSign>
			</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<div class="contentTableBody">
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
			   <tr>
			      <td width="100%" align="right">
			        <div id="buttonArea">
						<div class="buttonmenubox_R">
							<a class="button" href="#" style="white-space:nowrap;" onclick="submitForm('12');">输入语言能力</a>
						</div>
					</div>
			      </td>
			   </tr>
			</table>
		</div>
		<div class="emptyBlock"></div>
		<table width="100%">
			<tr>
				<td width="100%">
				   <font face="Arial" size="2"><b>&nbsp;专业技能</b></font>&nbsp;
				   <talent:checkSign path="${talentDto.resumeDto.skillDtos }"></talent:checkSign>
				</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<div class="contentTableBody">
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
			   <tr>
			      <td width="100%" align="right">
			        <div id="buttonArea">
						<div class="buttonmenubox_R">
							<a class="button" href="#" style="white-space:nowrap;" onclick="submitForm('11');">输入专业技能</a>
						</div>
					</div>
			      </td>
			   </tr>
			</table>
		</div>
		<div class="emptyBlock"></div>
		<table width="100%">
			<tr>
				<td width="100%">
				   <font face="Arial" size="2"><b>&nbsp;所获证书</b></font>&nbsp;
				   <talent:checkSign path="${talentDto.resumeDto.certDtos }"></talent:checkSign>
				</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<div class="contentTableBody">
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
			   <tr>
			      <td width="100%" align="right">
			        <div id="buttonArea">
						<div class="buttonmenubox_R">
							<a class="button" href="#" style="white-space:nowrap;" onclick="submitForm('14');">输入所获证书</a>
						</div>
					</div>
			      </td>
			   </tr>
			</table>
		</div>
		<div class="emptyBlock"></div>
		<table width="100%">
			<tr valign="middle">
				<td width="100%" nowrap="nowrap">
				   <font face="Arial" size="2"><b>&nbsp;自我评价</b></font>&nbsp;
				   <span id="ok" style="display: none"><img src="${imagePath}/icon/ok.gif" ></img></span>
				   <span id="blank"><img src="${imagePath}/icon/blank.gif" ></img></span>
				</td>
			</tr>
		</table>
		<div class="contentTableBody">
			<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%" >
				<tbody>
				    <common:standardTableRow />
					<tr>
						<td class="labelColumn">自我评价：</td>
						<td colspan="3">
						<form:textarea path="resumeDto.selfEvaluationDto.content" htmlEscape="true" rows="3" cols="60"  cssStyle="word-wrap:break-word;height:80px" onkeyup="javascript:if (this.value.length>1000){this.value = this.value.substring(0, 1000)}" cssClass="standardInputText" onblur="checkFilled();"/>
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
							   <input id="addButton" class="standardButton" type="button" value="添加" onclick="submitForm('5')"/>&nbsp;
							   <input id="confirmButton" class="standardButton" type="button" value="确认" onclick="submitForm('3')"/>&nbsp;
							   <input id="resetButton" class="standardButton" type="button" value="清除" onclick="location.href='${clearUrl}'">&nbsp;
							   <input id="backButton" class="standardButton" type="button" value="返回" onclick="location.href='${backToFillResumeUrl}'">&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	    <div class="emptyBlock"></div>
	    <div id="resumesTable">
		<table class="contentTableBody2" cellspacing="1" width="100%">
		   <tr class="contentTableTitle">
		      <td width="10%" align="center">全选</td>
		      <td width="10%">语言版本</td>
		      <td width="50%">简历名称</td>
		      <td width="20%" align="center">操作</td>
		   </tr>
		   <c:if test="${not empty talentDto.resumeDtos }">
		      <c:forEach items="${talentDto.resumeDtos }" var="resumeDto" varStatus="status">
		         <tr class="contentTableRow">
		            <td>${status.index+1 }</td>
		            <td>
		               <c:if test="${resumeDto.language == 'cn'}">中文</c:if>
		               <c:if test="${resumeDto.language == 'en' }">英文</c:if>
		            </td>
		            <td><c:out value="${resumeDto.name }" escapeXml="true"></c:out></td>
		            <td align="center"> 
		              <hdiv-c:url value="/talent/preEditResume.do?_id=${status.index }" var="editUrl"></hdiv-c:url>
		              <hdiv-c:url value="/talent/deleteResume.do?_id=${status.index }" var="deleteUrl"></hdiv-c:url>
		              <hdiv-c:url value="/talent/pop/viewResume.do?_id=${status.index }" var="viewUrl"></hdiv-c:url>
		              <input class="standardButton" type="button" value="编辑" onclick="location.href='${editUrl}'"/>&nbsp;
		              <input class="standardButton" type="button" value="删除" onclick="location.href='${deleteUrl}'"/>&nbsp;
		              <input class="standardButton" type="button" value="预览" onclick="var resumeWindow = window.open('${viewUrl}','resumeWindow', 'directories=no,height=550,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680,top=100,left=200');"/>&nbsp;
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
							   <input class="standardButton" type="button" value="提交" onclick="submitForm('6')"/>&nbsp;
							   <input class="standardButton" type="button" value="返回" onclick="location.href='${backToFillTalentInfoUrl}'"/>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		</div>
	</form:form>
</body>
</html>