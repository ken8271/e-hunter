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
			    items_per_page:3,
				num_display_entries:10,
				num_edge_entries:3,
				current_page:parseInt('${currPageOfInfo}')-1,
				prev_text:'上一页',
				next_text:'下一页',
				link_to:'others.html?page=__id__'
				}
	$("#pagination").pagination(parseInt('${totalCountOfInfo}'), opts);
});
</script>
</head>
<body>
<c:forEach items="${listOfInfo }" var="info">
   <ul class="list">
      <li>
         <span style="float:right;">[<fmt:formatDate type="both" value="${info.createDateTime }" pattern="yyyy-MM-dd"/>]</span>
         <a href="${ctx }/infocenter/id_${info.systemRefInfo}.html">${info.title }</a>
      </li>				
   </ul>
</c:forEach>
<div id="pagination" class="pagination" style="margin: 15px 20px 20px 20px!important;"></div>
</body>
</html>
