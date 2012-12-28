<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<meta http-equiv="Cache-Control" content="no-store"/>
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<meta name="baidu_union_verify" content="160d95d45a9005d7d764e2b3a5a5143e">
<link rel="stylesheet" rev="stylesheet" href="${ctx }/style/banner.css" type="text/css" media="all" />
<link rel="stylesheet" rev="stylesheet" href="${ctx }/style/default.css" type="text/css" media="all" />
<link rel="stylesheet" rev="stylesheet" href="${ctx }/style/pagination.css" type="text/css" media="all"/>

<script type="text/javascript" src="${ctx}/script/jquery-1.6.4.js"></script>
<script type="text/javascript" src="${ctx}/script/jquery-1.6.4.min.js"></script>
<script type="text/javascript" src="${ctx}/script/jquery.easing.js"></script>
<script type="text/javascript" src="${ctx}/script/jquery.slider.js"></script>
<script type="text/javascript" src="${ctx}/script/jquery.pagination.js"></script>


<!-- 解决JQuery与其他JS冲突 -->
<script type="text/javascript">
if ($ != jQuery) {
    $ = jQuery.noConflict();
}
</script>