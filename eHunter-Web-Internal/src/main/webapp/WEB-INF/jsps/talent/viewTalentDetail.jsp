<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>e-Hunter System/[EH-TLNT-0001]</title>
<hdiv-c:url value="/project/singleCandidateAsgnVerify.do" var="asgn2prjUrl"></hdiv-c:url>
<script type="text/javascript">
$().ready(function(){
	$('#systemTalentRefNum').val('${talentDto.talentID}');
	clearResult();
});

function popUpSelector(){
	clearResult();
	setPopUpFramePosition('light',600,300);
	setOverlayDimension('fade');	
	popUpFrame('light','fade');
}

function clearResult(){
	$('#resultTable tr.contentTableRow').remove();
	$('#resultTable').hide();
}

function loadProjectsByAjax(){
	clearResult();
	$('#projectSelectorForm').ajaxSubmit({
		success:function(xml){
			var str = '';
			$(xml).find('project').each(function(i , element){
				var systemProjectRefNum = $(this).find("systemProjectRefNum").text();
				var projectName = $(this).find("projectName").text();
				var customerName = $(this).find("customerName").text();
				var status = $(this).find("projectStatus").text();
				str = str + "<tr class='contentTableRow'><td align='center'><input type='radio' name='type' value='" + systemProjectRefNum + "' onclick='asgnCandidate2SelectedProject(this);'/></td>"
				        + "<td>" + systemProjectRefNum + "</td>"
				        + "<td>" + projectName + "</td>"
				        + "<td>" + customerName + "</td>"
				        + "<td align='center'>" + status + "</td>";
			});
			$(str).appendTo('#resultTable');
			$('#resultTable').show();
		},
		error:function(){
			alert('系统错误，请稍后重试！');
		}	
	});
}

function asgnCandidate2SelectedProject(c){
	if(c.checked == true){		
	  window.location.href = '${asgn2prjUrl}&_id=' + c.value;
	}
}
</script>
</head>
<body>
    <hdiv-c:url value="/talent/preEditTalentInfo.do" var="editUrl"></hdiv-c:url>
    <hdiv-c:url value="/talent/initCurriculumVitaeUpload.do?_id=${talentDto.talentID }" var="addCvUrl"></hdiv-c:url>
    <hdiv-c:url value="/talent/fillEmploymentHistory.do?module=5" var="editEmploymentHistoryUrl"></hdiv-c:url>
    <c:if test="${module == '4' }">
       <hdiv-c:url value="/talent/completeTalentRegistration.do" var="backUrl"></hdiv-c:url>
    </c:if>
    <c:if test="${module == '5' }">
       <hdiv-c:url value="/talent/talentsSearch.do" var="backUrl"></hdiv-c:url>
    </c:if>
	<table border="0" width="100%">
		<tr>
			<td class="pageTitle">人才详细资料</td>
		</tr>
	</table>
	<table id="bg2" border="0" width="100%">
		<tr>
			<td class="functionMenuBar">
				<table align="right" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
						    <input class="standardButton" type="button" value="新增简历" onclick="location.href='${addCvUrl}'" />&nbsp;
						    <input class="standardButton" type="button" value="新增/编辑工作经历" onclick="location.href='${editEmploymentHistoryUrl}'" />&nbsp;
							<input class="standardButton" type="button" value="添加到项目" onclick="popUpSelector();">&nbsp;
						    <input class="standardButton" type="button" value="编辑" onclick="location.href='${editUrl}'"/>&nbsp;
							<input class="standardButton" type="button" value="返回" onclick="location.href='${backUrl}'" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<div class="emptyBlock"></div>
	<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
		<tbody>
			<common:standardTableRow />
			<tr>
				<td class="labelColumn">人才编号：</td>
				<td><c:out value="${talentDto.talentID }" escapeXml="true"></c:out></td>
				<td colspan="2"></td>
			</tr>
		</tbody>
	</table>
	<div class="emptyBlock"></div>
	<table width="100%">
		<tr>
			<td width="10%"><font face="Arial" size="2"><b>第一部分：</b></font></td>
			<td width="90%"><font face="Arial" size="2"><b>人才基本信息</b></font></td>
		</tr>
	</table>
	<div style="height: 5px"></div>
	<div class="contentTableBody">
		<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
			<tbody>
				<common:standardTableRow />
				<tr>
					<td class="labelColumn">中文名：</td>
					<td><c:out value="${talentDto.cnName }" escapeXml="true"></c:out></td>
					<td class="labelColumn">英文名：</td>
					<td><c:out value="${talentDto.enName }" escapeXml="true"></c:out></td>
				</tr>
				<tr>
					<td class="labelColumn">性别：</td>
					<td>
					   <c:if test="${talentDto.gender == 'M' }"><c:out value="男" escapeXml="true"></c:out></c:if>
					   <c:if test="${talentDto.gender == 'F' }"><c:out value="女" escapeXml="true"></c:out></c:if>
					</td>
					<td class="labelColumn">婚姻状况：</td>
					<td>
					   <c:if test="${talentDto.maritalStatus == '01'}"><c:out value="未婚" escapeXml="true"></c:out></c:if>
					   <c:if test="${talentDto.maritalStatus == '02'}"><c:out value="已婚" escapeXml="true"></c:out></c:if>
					   <c:if test="${talentDto.maritalStatus == '03'}"><c:out value="保密" escapeXml="true"></c:out></c:if>
					</td>
				</tr>
				<tr>
					<td class="labelColumn">出生日期：
					</td>
					<td>
					   <c:out value="${talentDto.birthDateDto.year }" escapeXml="true"></c:out>/
					   <c:out value="${talentDto.birthDateDto.month }" escapeXml="true"></c:out>/
					   <c:out value="${talentDto.birthDateDto.day }" escapeXml="true"></c:out>
					</td>
					<td class="labelColumn">籍贯：</td>
					<td><c:out value="${talentDto.nativePlace }" escapeXml="true"></c:out></td>
				</tr>
				<tr>
					<td class="labelColumn">曾居地：</td>
					<td><c:out value="${talentDto.onceLivePlace }" escapeXml="true"></c:out></td>
					<td class="labelColumn">现居地：</td>
					<td><c:out value="${talentDto.nowLivePlace }" escapeXml="true"></c:out></td>
				</tr>
				<tr>
					<td class="labelColumn">最高学历：</td>
					<td><c:out value="${talentDto.degreeDto.displayName }" escapeXml="true"></c:out></td>
					<td class="labelColumn">人才来源：</td>
				    <td><c:out value="${talentDto.talentSrcDto.displayName }" escapeXml="true"></c:out></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="emptyBlock"></div>
	<table width="100%">
		<tr>
			<td width="10%"><font face="Arial" size="2"><b>第二部分：</b></font></td>
			<td width="90%"><font face="Arial" size="2"><b>人才联系方式</b></font></td>
		</tr>
	</table>
	<div class="contentTableBody">
		<table class="standardTableForm" border="1" cellspacing="0" cellpadding="0" width="100%">
			<tbody>
				<common:standardTableRow />
				<tr>
					<td class="labelColumn">家庭电话：</td>
					<td>
					  <c:if test="${talentDto.homeNumberDto != null && talentDto.homeNumberDto.phoneNumber != null && talentDto.homeNumberDto.phoneNumber != ''}">
					     <c:out value="${talentDto.homeNumberDto.regionCode }" escapeXml="true"></c:out>&nbsp;-&nbsp;
					     <c:out value="${talentDto.homeNumberDto.phoneNumber }" escapeXml="true"></c:out>
					  </c:if>
					</td>
					<td class="labelColumn">公司电话：</td>
					<td>
					  <c:if test="${talentDto.companyNumberDto != null && talentDto.companyNumberDto.phoneNumber != null && talentDto.companyNumberDto.phoneNumber != ''}">
					     <c:out value="${talentDto.companyNumberDto.regionCode }" escapeXml="true"></c:out>&nbsp;-&nbsp;
					     <c:out value="${talentDto.companyNumberDto.phoneNumber }" escapeXml="true"></c:out>
					  </c:if>
					</td>
				</tr>
				<tr>
					<td class="labelColumn">手机：</td>
					<td>
					   <c:out value="${talentDto.mobilePhoneDto1.phoneNumber }" escapeXml="true"></c:out><br/>
					   <c:out value="${talentDto.mobilePhoneDto2.phoneNumber }" escapeXml="true"></c:out>
					</td>
					<td class="labelColumn">邮箱：</td>
					<td><c:out value="${talentDto.email }" escapeXml="true"></c:out></td>
				</tr>
				<tr>
					<td class="labelColumn">QQ：</td>
					<td>
					   <c:out value="${talentDto.QQ }" escapeXml="true"></c:out><br/>
					</td>
					<td class="labelColumn">MSN：</td>
					<td><c:out value="${talentDto.msn }" escapeXml="true"></c:out></td>
				</tr>
				<tr>
					<td class="labelColumn">家庭地址：</td>
					<td colspan="3"><c:out value="${talentDto.homeAddress }"></c:out></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="emptyBlock"></div>
	<table width="100%">
		<tr>
			<td width="10%"><font face="Arial" size="2"><b>第三部分：</b></font></td>
			<td width="90%"><font face="Arial" size="2"><b>职业概况</b></font></td>
		</tr>
	</table>
	<table class="contentTableBody2" cellspacing="1" width="100%">
	   <tr class="contentTableTitle">
	      <td width="15%" align="center">时间</td>
		  <td width="30%">公司名称</td>
		  <td width="50%">曾任职位</td>
		  <td width="5%" align="center">查看</td>
	   </tr>
	   <c:forEach items="${talentDto.employmentHistoryDtos }" var="historyDto" varStatus="status">
	         <tr class="contentTableRow">
		            <td>
		            <c:out value="${historyDto.beginTimeDto.year }" escapeXml="true"/>/
		            <c:out value="${historyDto.beginTimeDto.month }" escapeXml="true"/>&nbsp;-&nbsp;
		            <c:if test="${empty historyDto.endTimeDto.day }"><c:out value="至今" escapeXml="true"/></c:if>
		            <c:if test="${not empty historyDto.endTimeDto.day }">
		               <c:out value="${historyDto.endTimeDto.year }" escapeXml="true"/>/
		               <c:out value="${historyDto.endTimeDto.month }" escapeXml="true"/>
		            </c:if>
		            </td>
		            <td>
		               <c:out value="${historyDto.companyName }" escapeXml="true"></c:out>
		            </td>
		            <td>
		               <c:forEach items="${historyDto.positionDtos }" var="positionDto">
		                 <c:out value="${positionDto.displayName }" escapeXml="true" /><br/> 
		               </c:forEach>
		            </td>
		            <td align="center">
		               <common:tips url="${viewProjectUrl }" title="查看项目资料"></common:tips>
		            </td>
		     </tr>
	   </c:forEach>
    </table>
	<div class="emptyBlock"></div>
	<table width="100%">
		<tr>
			<td width="10%"><font face="Arial" size="2"><b>第四部分：</b></font></td>
			<td width="90%"><font face="Arial" size="2"><b>人才简历</b></font></td>
		</tr>
	</table>
	<div class="emptyBlock"></div>
	<table class="contentTableBody2" cellspacing="1" width="100%">
		   <tr class="contentTableTitle">
		      <td width="10%" align="center">序号</td>
		      <td width="10%">语言版本</td>
		      <td width="55%">简历名称</td>
		      <td width="15%" align="center">操作</td>
		   </tr>
		   <c:if test="${not empty talentDto.cvDtos }">
		      <c:forEach items="${talentDto.cvDtos }" var="cvDto" varStatus="status">
		         <tr class="contentTableRow">
		            <td>${status.index+1 }</td>
		            <td>
		               <c:if test="${cvDto.language == 'cn'}">中文</c:if>
		               <c:if test="${cvDto.language == 'en' }">英文</c:if>
		            </td>
		            <td><c:out value="${cvDto.cvName }" escapeXml="true"></c:out></td>
		            <td align="center"> 
		              <hdiv-c:url value="/talent/pop/viewCurriculumVitaeOnline.do?_id=${cvDto.cvID }" var="viewUrl"></hdiv-c:url>
		              <hdiv-c:url value="/talent/downloadCurriculumVitae.do?_id=${cvDto.cvID }" var="cvDownUrl"></hdiv-c:url>
		              <hdiv-c:url value="/talent/deleteCurriculumVitae.do?_id=${cvDto.cvID }" var="deleteUrl"></hdiv-c:url>
		              <img src="${imagePath }/icon/download.gif" title="下载" style="vertical-align: middle;cursor: pointer;"  onclick="location.href='${cvDownUrl}'" />&nbsp;
		              <img src="${imagePath }/icon/preview.gif" title="查看" style="vertical-align: middle;cursor: pointer;"  onclick="window.open('${viewUrl}');"/>&nbsp;
		              <img src="${imagePath }/icon/delete.gif" title="删除" style="vertical-align: middle;cursor: pointer;"  onclick="location.href='${deleteUrl}'"/>&nbsp;
		            </td>
		         </tr>
		      </c:forEach>
		   </c:if>
	</table>
	<div class="emptyBlock"></div>
	<div><jsp:include page="projectSelector_pop.jsp"></jsp:include></div>
	<table id="bg2" border="0" width="100%">
		<tr>
			<td class="functionMenuBar">
				<table align="right" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
						    <input class="standardButton" type="button" value="新增简历" onclick="location.href='${addCvUrl}'" />&nbsp;
						    <input class="standardButton" type="button" value="新增/编辑工作经历" onclick="location.href='${editEmploymentHistoryUrl}'" />&nbsp;
							<input class="standardButton" type="button" value="添加到项目" onclick="popUpSelector();">&nbsp;
						    <input class="standardButton" type="button" value="编辑" onclick="location.href='${editUrl}'"/>&nbsp;
							<input class="standardButton" type="button" value="返回"  onclick="location.href='${backUrl}'" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<div class="emptyBlock"></div>
</body>
</html>