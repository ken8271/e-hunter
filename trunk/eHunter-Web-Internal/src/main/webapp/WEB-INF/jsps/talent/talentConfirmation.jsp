<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
<head>
<link rel="stylesheet" href="${ctx}/style/verify.css" type="text/css" />
</head>
<body>
        <hdiv-c:url value="/talent/viewTalentDetail.do?_id=${talentDto.talentID }&type=2" var="viewTalentDetailUrl"></hdiv-c:url>
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">人才资料预览</td>
			</tr>
		</table>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td><input class="standardButton" type="submit" value="添加到项目" onclick="location.href='${ctx}/project/initAddProject.do'"/>&nbsp;
								<input class="standardButton" type="button" value="关闭" onclick="location.href='${ctx}/index.do'">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<table class="contentTableBody1" cellspacing="0">
				<tr>
					<td class="note">
						<common:message code="ehunter.talent.create.confirm" needSuccSign="true"></common:message>
					</td>
				</tr>
		</table>
		<div class="emptyBlock"></div>
		<table class="verifyTable" style="border-collapse: collapse; margin-right: 5px" border="2" cellSpacing="0" width="100%" cellpadding="5px">
			<tr>
				<td align="center" width="20%"><span class="textCh8">人才编号</span><br /> <br /></td>
				<td align="center" width="10%"><span class="textCh8">人才來源</span><br /> <br /></td>
				<td align="center" width="20%"><span class="textCh8">姓名</span><br /><br /></td>
				<td align="center" width="35%"><span class="textCh8">现居地</span><br /><br /></td>
				<td align="center" width="10%"><span class="textCh8">最高学历</span><br /><br /></td>
				<td align="center" width="5%"><span class="textCh8">操作</span><br /><br /></td>
			</tr>
			<tr>
				<td align="center"><span class="textCn8"><c:out value="${talentDto.talentID }" escapeXml="true"></c:out></span></td>
				<td align="center"><span class="textCn8"><c:out value="${talentDto.talentSrcDto.displayName }" escapeXml="true"></c:out></span>
				<td align="center">
				   <span class="textCn8">
				      <c:out value="${talentDto.cnName }"></c:out>
				      <c:if test="${talentDto.enName != null && talentDto.enName != '' }">
				         (<c:out value="${talentDto.enName }" escapeXml="true"></c:out>)
				      </c:if>
				   </span>
				</td>
				<td align="center"><span class="textCn8"><c:out value="${talentDto.nowLivePlace }" escapeXml="true"></c:out></span>
				<td align="center"><span class="textCn8"><c:out value="${talentDto.degreeDto.displayName }" escapeXml="true"></c:out></span>
				<td>
                    <input class="standardButton" type="button" value="查看" onclick="location.href='${viewTalentDetailUrl}'">
				</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td><input class="standardButton" type="submit" value="添加到项目" onclick="location.href='${ctx}/project/initAddProject.do'"/>&nbsp;
								<input class="standardButton" type="button" value="关闭" onclick="location.href='${ctx}/index.do'">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
</body>
</html>
