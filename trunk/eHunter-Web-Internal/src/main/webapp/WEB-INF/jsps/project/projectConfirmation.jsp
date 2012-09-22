<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
<head>
<link rel="stylesheet" href="${ctx}/style/verify.css" type="text/css" />
</head>
<body>
        <hdiv-c:url value="/project/viewProjectDetail.do?_id=${projectDto.systemProjectRefNum }&module=7" var="viewProjectDetailUrl"></hdiv-c:url>
        <hdiv-c:url value="/project/initProjectCandidateRepository.do?_id=${projectDto.systemProjectRefNum }&type=2" var="tlntAsgnUrl"></hdiv-c:url>
		<table border="0" width="100%">
			<tr>
				<td class="pageTitle">成功创建猎头项目</td>
			</tr>
		</table>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td><input class="standardButton" type="button" value="添加项目人才库" onclick="location.href='${tlntAsgnUrl}'"/>&nbsp;
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
						<common:message code="ehunter.project.create.confirm" needSuccSign="true"></common:message>
					</td>
				</tr>
		</table>
		<div class="emptyBlock"></div>
		<table class="verifyTable" style="border-collapse: collapse; margin-right: 5px" border="2" cellSpacing="0" width="100%" cellpadding="5px">
			<tr>
				<td align="center" width="15%"><span class="textCh8">项目编号</span><br /> <br /></td>
				<td align="center" width="30%"><span class="textCh8">项目名称</span><br /> <br /></td>
				<td align="center" width="30%"><span class="textCh8">客户公司</span><br /><br /></td>
				<td align="center" width="10%"><span class="textCh8">需求人数</span><br /><br /></td>
				<td align="center" width="10%"><span class="textCh8">项目负责人</span><br /><br /></td>
				<td align="center" width="5%"><span class="textCh8">操作</span><br /><br /></td>
			</tr>
			<tr>
				<td align="center"><span class="textCn8"><c:out value="${projectDto.systemProjectRefNum }" escapeXml="true"></c:out></span></td>
				<td align="center"><span class="textCn8"><c:out value="${projectDto.projectName }" escapeXml="true"></c:out></span>
				<td align="center">
				   <span class="textCn8">
				      <c:out value="${projectDto.customerDto.fullName }"></c:out>
				   </span>
				</td>
				<td align="center"><span class="textCn8"><c:out value="${projectDto.postDescDto.expectNumberStr}" escapeXml="true"></c:out></span>
				<td align="center"><span class="textCn8"><c:out value="${projectDto.adviserDto.cnName}" escapeXml="true"></c:out></span>
				<td>
                    <input class="standardButton" type="button" value="查看" onclick="location.href='${viewProjectDetailUrl}'">
				</td>
			</tr>
		</table>
		<div class="emptyBlock"></div>
		<table id="bg2" border="0" width="100%">
			<tr>
				<td class="functionMenuBar">
					<table align="right" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							    <input class="standardButton" type="button" value="添加项目人才库" onclick="location.href='${tlntAsgnUrl}'"/>&nbsp;
								<input class="standardButton" type="button" value="关闭" onclick="location.href='${ctx}/index.do'">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
</body>
</html>
