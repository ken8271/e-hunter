<%@ include file="/commons/taglibs.jsp"%>
<%@ tag language="java" pageEncoding="UTF-8"%>

<c:if test="${not empty resumeDto.jobExpDtos }">
	<div class="resumesLookMain">
		<div class="resumesLookTitle">工作经历</div>
	</div>
	<c:forEach items="${resumeDto.jobExpDtos }" var="jobExp">
		<div class="resumesLookMain resumesLookBorder">
			<div class="resumesLookTips">
				<c:out value="${jobExp.fromDateDto.year }" escapeXml="true" />/<c:out value="${jobExp.fromDateDto.month }" escapeXml="true" />--
				<c:out value="${jobExp.toDateDto.year }" escapeXml="true" />/<c:out value="${jobExp.toDateDto.month }" escapeXml="true" />
			</div>
			<div class="lookLeftList lookLeftListStrong">
				<ul>
					<li><c:out value="${jobExp.companyName }" escapeXml="true"></c:out></li>
					<li><c:out value="${jobExp.department }" escapeXml="true"></c:out></li>
					<li class="lookBordernone"><c:out value="${jobExp.positionName }" escapeXml="true"></c:out></li>
				</ul>
			</div>
			<div class="lookLeftList lookLeftListStrong notStrong">
				<ul>
					<li>行业类别：<c:out value="${jobExp.industryDto.displayName }" escapeXml="true"></c:out></li>
					<li>企业性质：<c:out value="${jobExp.companyCategoryDto.displayName }" escapeXml="true"></c:out></li>
					<li>规模：<c:out value="${jobExp.companySizeDto.displayName }" escapeXml="true"></c:out></li>
				</ul>
			</div>
			<div class="resumesLookDetails">
				<div class="resumesLookWord">工作描述：</div>
				<div class="detailsMain">
					<c:out value="${jobExp.jobDescription }" escapeXml="true"></c:out>
				</div>
			</div>
		</div>
	</c:forEach>
</c:if>