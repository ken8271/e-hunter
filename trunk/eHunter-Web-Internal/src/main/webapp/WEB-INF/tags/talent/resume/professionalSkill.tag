<%@ include file="/commons/taglibs.jsp"%>
<%@ tag language="java" pageEncoding="UTF-8"%>

<c:if test="${not empty resumeDto.skillDtos }">
	<div class="resumesLookMain">
		<div class="resumesLookTitle">专业技能</div>
	</div>
	<c:forEach items="${resumeDto.skillDtos }" var="skill">
		<div class="resumesLookMain">
			<div class="lookLeftList notStrong">
				<ul>
					<li class="lookBordernone"><c:out value="${skill.skillName }" escapeXml="true"></c:out></li>
					<li><c:out value="${skill.levelDto.displayName }" escapeXml="true"></c:out></li>
					<li class="lookBordernone"><c:out value="${skill.duration }" escapeXml="true"></c:out></li>
				</ul>
			</div>
		</div>
	</c:forEach>
</c:if>