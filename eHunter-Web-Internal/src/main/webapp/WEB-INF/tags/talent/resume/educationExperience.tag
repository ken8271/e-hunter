<%@ include file="/commons/taglibs.jsp"%>
<%@ tag language="java" pageEncoding="UTF-8"%>

<c:if test="${not empty resumeDto.eduExpDtos }">
	<div class="resumesLookMain">
		<div class="resumesLookTitle">教育经历</div>
	</div>
	<c:forEach items="${resumeDto.eduExpDtos }" var="eduExp">
		<div class="resumesLookMain">
			<div class="resumesLookTips">
				<c:out value="${eduExp.fromDateDto.year }" escapeXml="true" />/<c:out value="${eduExp.fromDateDto.month }" escapeXml="true" />--
				<c:out value="${eduExp.toDateDto.year }" escapeXml="true" />/<c:out value="${eduExp.toDateDto.month }" escapeXml="true" />
			</div>
			<div class="lookLeftList lookLeftListStrong">
				<ul>
					<li><c:out value="${eduExp.school }" escapeXml="true"></c:out></li>
					<li><c:out value="${eduExp.majorDto.displayName }"></c:out></li>
					<li class="lookBordernone"><c:out value="${eduExp.degreeDto.displayName }"></c:out></li>
				</ul>
			</div>
		</div>
	</c:forEach>
</c:if>