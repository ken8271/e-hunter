<%@ include file="/commons/taglibs.jsp"%>
<%@ tag language="java" pageEncoding="UTF-8"%>

<c:if test="${not empty resumeDto.certDtos }">
	<div class="resumesLookMain">
		<div class="resumesLookTitle">证书</div>
	</div>
	<c:forEach items="${resumeDto.certDtos }" var="cert">
		<div class="resumesLookMain">
			<div class="resumesLookTips">
			    <c:out value="${cert.gainedDateDto.year }" escapeXml="true" />/<c:out value="${cert.gainedDateDto.month }" escapeXml="true" />
			</div>
			<div class="lookLeftList lookLeftListStrong">
				<ul>
					<li class="lookBordernone"><c:out value="${cert.certName }" escapeXml="true" /></li>
				</ul>
			</div>
			<div class="lookLeftListAdd">证书说明:<c:out value="${cert.description }" escapeXml="true" /></div>
		</div>
	</c:forEach>
</c:if>