<%@ include file="/commons/taglibs.jsp"%>
<%@ tag language="java" pageEncoding="UTF-8"%>

<c:if test="${resumeDto.selfEvaluationDto != null && resumeDto.selfEvaluationDto.content != ''}">
	<div class="resumesLookMain">
		<div class="resumesLookTitle">自我评价</div>
		<div class="resumesLookMain mainIntro">
			<c:out value="${resumeDto.selfEvaluationDto.content }" escapeXml="true"></c:out>
		</div>
	</div>
</c:if>