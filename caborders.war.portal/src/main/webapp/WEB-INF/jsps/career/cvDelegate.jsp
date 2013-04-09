<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>深圳市铠博德企业管理咨询有限公司</title>
<script type="text/javascript">
function pageselectCallback(page){
	
}

$(document).ready(function(){
	var opts = {callback:pageselectCallback,
			    items_per_page:2,
				num_display_entries:10,
				num_edge_entries:3,
				current_page:parseInt('${positionPageInfoDto.currPage}')-1,
				prev_text:'上一页',
				next_text:'下一页',
				link_to:'index.html?page=__id__'
				}
	$("#pagination").pagination(parseInt('${positionPageInfoDto.totalCount}'), opts);
});
</script>
</head>
<body>
<div class="office_list">
   <c:forEach items="${listOfPosition }" var="postn">
      <h4 class="office_title"><a href="${ctx }/career/id_${postn.systemRefPosition}.html">${postn.title }</a></h4>
      <div class="office_other">工作地点：${postn.workCity }  &nbsp;&nbsp; | &nbsp;&nbsp; <fmt:formatDate type="both" value="${postn.createDateTime }" pattern="yyyy-MM-dd"/></div>
   </c:forEach>
</div>
<c:if test="${not empty listOfPosition }">
   <div id="pagination" class="pagination" style="margin: 15px 20px 20px 20px"></div>
</c:if>
</body>
</html>
