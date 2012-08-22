<%@ include file="/commons/taglibs.jsp"%>
<%@ tag language="java" pageEncoding="UTF-8"%>

<c:if test="${not empty resumeDto.trnExpDtos }">
	<div class="resumesLookMain">
		<div class="resumesLookTitle">培训经历</div>
	</div>
	<c:forEach items="${resumeDto.trnExpDtos }" var="trnExp">
		<div class="resumesLookMain">
			<div class="resumesLookTips">
				<c:out value="${trnExp.fromDateDto.year }" escapeXml="true" />/<c:out value="${trnExp.fromDateDto.month }" escapeXml="true" />--
				<c:out value="${trnExp.toDateDto.year }" escapeXml="true" />/<c:out value="${trnExp.toDateDto.month }" escapeXml="true" />
			</div>
			<div class="lookLeftList lookLeftListStrong">
				<ul>
					<li class="lookBordernone"><c:out value="${trnExp.organization }" escapeXml="true"></c:out></li>
				</ul>
			</div>
			<div class="resumesLookDetails">
				<div class="resumesLookWord">培训课程：</div>
				<div class="detailsMain"><c:out value="${trnExp.course }" escapeXml="true"></c:out></div>
			</div>
			<div class="resumesLookDetails">
				<div class="resumesLookWord">所获证书：</div>
				<div class="detailsMain"><c:out value="${trnExp.cert }" escapeXml="true"></c:out></div>
			</div>
			<div class="resumesLookDetails">
				<div class="resumesLookWord">培训地点：</div>
				<div class="detailsMain"><c:out value="${trnExp.address }" escapeXml="true"></c:out></div>
			</div>
			<div class="resumesLookDetails">
				<div class="resumesLookWord">详细描述：</div>
				<div class="detailsMain"><c:out value="${trnExp.description }" escapeXml="true"></c:out></div>
			</div>
		</div>
	</c:forEach>
</c:if>
