<%@ include file="/commons/taglibs.jsp"%>
<%@ tag language="java" pageEncoding="UTF-8"%>

<c:if test="${not empty resumeDto.prjExpDtos }">
	<div class="resumesLookMain">
		<div class="resumesLookTitle">项目经验</div>
	</div>
	<c:forEach items="${resumeDto.prjExpDtos }" var="prjExp">
		<div class="resumesLookMain">
			<div class="resumesLookTips">
				<c:out value="${prjExp.fromDateDto.year }" escapeXml="true" />/<c:out value="${prjExp.fromDateDto.month }" escapeXml="true" />--
				<c:out value="${prjExp.toDateDto.year }" escapeXml="true" />/<c:out value="${prjExp.toDateDto.month }" escapeXml="true" />
			</div>
			<div class="lookLeftList lookLeftListStrong">
				<ul>
					<li class="lookBordernone"><c:out value="${prjExp.name }" escapeXml="true" /></li>
				</ul>
			</div>
			<div class="resumesLookDetails">
				<div class="resumesLookWord">项目职责：</div>
				<div class="detailsMain"><c:out value="${prjExp.duty }" escapeXml="true" /></div>
			</div>
			<div class="resumesLookDetails">
				<div class="resumesLookWord">项目描述：</div>
				<div class="detailsMain"><c:out value="${prjExp.description }" escapeXml="true" /></div>
			</div>
		</div>
	</c:forEach>
</c:if>
