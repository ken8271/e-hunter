<%@ include file="/commons/taglibs.jsp"%>
<%@ tag language="java" pageEncoding="UTF-8"%>

<c:if test="${resumeDto.intentionDto != null && resumeDto.intentionDto.employmentCategory != null}">
	<div class="resumesLookMain">
		<div class="resumesLookTitle">求职意向</div>
		<div class="resumesLookDetails">
			<div class="detailsName">工作性质：</div>
			<div class="detailsMain">
				<c:if test="${resumeDto.intentionDto.employmentCategory == 'FT' }"><c:out value="全职" escapeXml="true"></c:out></c:if>
				<c:if test="${resumeDto.intentionDto.employmentCategory == 'PT' }"><c:out value="兼职" escapeXml="true"></c:out></c:if>
				<c:if test="${resumeDto.intentionDto.employmentCategory == 'IS' }"><c:out value="实习" escapeXml="true"></c:out></c:if>
			</div>
		</div>
		<div class="resumesLookDetails">
			<div class="detailsName">期望职业：</div>
			<div class="detailsMain">
				<c:out value="${resumeDto.intentionDto.expectPositionDto.displayName }" escapeXml="true" />
			</div>
		</div>
		<div class="resumesLookDetails">
			<div class="detailsName">期望行业：</div>
			<div class="detailsMain">
				<c:out value="${resumeDto.intentionDto.expectIndustryDto.displayName }" escapeXml="true" />
			</div>
		</div>
		<div class="resumesLookDetails">
			<div class="detailsName">工作地区：</div>
			<div class="detailsMain">
			    <c:forEach items="${resumeDto.intentionDto.expectCityDtos }" var="expectCityDto">
			       <c:out value="${expectCityDto.displayName }" escapeXml="true" />&nbsp;&nbsp;
			    </c:forEach>
			</div>
		</div>
		<div class="resumesLookDetails">
			<div class="detailsName">期望月薪：</div>
			<div class="detailsMain">
				<c:out value="${resumeDto.intentionDto.expectSalary }" escapeXml="true" />千元/月
			</div>
		</div>
	</div>
</c:if>