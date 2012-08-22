<%@ include file="/commons/taglibs.jsp"%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="path" type="java.lang.String" required="true"%>

<div class="resumesLookTop">
	<div class="resLookTopleft">
		<div class="lookLeftname">
			<c:out value="${talentDto.cnName }"></c:out>
		</div>
		<div class="lookLeftList">
			<ul>
				<li>
				   <c:if test="${talentDto.gender == 'M'}"><c:out value="男" escapeXml="true"></c:out></c:if> 
				   <c:if test="${talentDto.gender == 'F'}"><c:out value="女" escapeXml="true"></c:out></c:if>
				</li>
				<li>
				   <c:if test="${talentDto.maritalStatus == '01'}"><c:out value="未婚" escapeXml="true"></c:out></c:if> 
				   <c:if test="${talentDto.maritalStatus == '02'}"><c:out value="已婚" escapeXml="true"></c:out></c:if>
				</li>
				<li>
				   <c:out value="${talentDto.birthDateDto.year }"escapeXml="true" />年
				   <c:out value="${talentDto.birthDateDto.month }" escapeXml="true" />月生</li>
				<li><c:out value="${talentDto.highestDegree }" escapeXml="true"></c:out></li>
			</ul>
		</div>
		<div class="lookLeftList">
			<ul>
				<li>户口：<c:out value="${talentDto.nativePlace }" escapeXml="true"></c:out></li>
				<li class="lookBordernone">现居住于：<c:out value="${talentDto.nowLivePlace }" escapeXml="true"></c:out></li>
			</ul>
		</div>
		<div class="lookLeftMain">
			<c:out value="${talentDto.homeAddress }" escapeXml="true"></c:out>
		</div>
		<div class="lookLeftMain">
			<c:out value="${talentDto.mobilePhoneDto1.phoneNumber }"></c:out>(手机)
		</div>
		<div class="lookLeftMain">
			E-mail:<c:out value="${talentDto.email }"></c:out>
		</div>
		<div class="lookLeftMain"></div>
	</div>
</div>