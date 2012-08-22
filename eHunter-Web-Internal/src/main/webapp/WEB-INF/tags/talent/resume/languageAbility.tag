<%@ include file="/commons/taglibs.jsp"%>
<%@ tag language="java" pageEncoding="UTF-8"%>

<c:if test="${not empty resumeDto.languageDtos }">
	<div class="resumesLookMain">
		<div class="resumesLookTitle">语言能力</div>
	</div>
	<c:forEach items="${resumeDto.languageDtos }" var="language">
		<div class="resumesLookMain">
			<div class="lookLeftList notStrong">
				<ul>
					<li class="lookBordernone"><c:out value="${language.languageCategoryDto.displayName }" escapeXml="true"></c:out>：</li>
					<li>读写能力<c:out value="${language.ablitityOfRWDto.displayName }" escapeXml="true"></c:out></li>
					<li class="lookBordernone">听说能力<c:out value="${language.ablitityOfLSDto.displayName }" escapeXml="true"></c:out></li>
				</ul>
			</div>
		</div>
	</c:forEach>
</c:if>