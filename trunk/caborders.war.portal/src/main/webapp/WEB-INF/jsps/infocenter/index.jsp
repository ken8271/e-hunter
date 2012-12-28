<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>深圳市铠博德企业管理咨询有限公司</title>
<script type="text/javascript">
function pageselectCallback(page_index, jq){
	var items_per_page = $('#items_per_page').val();
	var max_elem = Math.min((page_index+1) * items_per_page, members.length);
	return false;
}

$(document).ready(function(){
	var opts = {callback:pageselectCallback,
	            items_per_page:5,
				num_display_entries:10,
				num_edge_entries:3,
				prev_text:'上一页',
				next_text:'下一页'}
	$("#pagination").pagination(20, opts);
});
</script>
</head>
<body>
<ul class="list">
   <li>
      <span style="float:right;">时间代码：[2012-09-20]</span>
      <a href="/Article/views/item/40.shtml">新闻标题代码</a>
   </li>				
</ul>
<div id="pagination" class="pagination" style="margin: 15px 20px 20px 20px!important;"></div>
</body>
</html>
