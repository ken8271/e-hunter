<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<div class="arrowlistmenu">
			<h3 class="menuheader expandable">客户管理</h3>
			<ul class="categoryitems">
			<li><a href="${ctx}/customer/initAddCustomer.do">新增客户</a></li>
			<li><a href="${ctx}/customer/initCustomersSearch.do">客户查询</a></li>
			<li><a href="${ctx}/customer/initCustomerContactHistorySearch.do">客户联系记录</a></li>
			</ul>
			
			<h3 class="menuheader expandable">人才库管理</h3>
			<ul class="categoryitems">
			<li><a href="${ctx}/talent/initAddTalent.do">新增人才</a></li>
			<li><a href="${ctx}/talent/initTalentsSearch.do">人才查询</a></li>
			<li><a href="${ctx}/talent/initCandidateContact.do">人才联系记录</a></li>
			</ul>
			
			<h3 class="menuheader expandable">项目管理</h3>
			<ul class="categoryitems">
			<li><a href="${ctx}/project/initNewProject.do">新建项目</a></li>
			<li><a href="${ctx}/project/initProjectsSearch.do">项目查询</a></li>
			</ul>
			
			<!--  			
			<h3 class="menuheader expandable">邮件管理</h3>
			<ul class="categoryitems">
			<li><a href="#">新建邮件</a></li>
			<li><a href="#">收件箱</a></li>
			<li><a href="#">发件箱</a></li>
			<li><a href="#">垃圾箱</a></li>
			</ul>
			-->
			
			<h3 class="menuheader expandable">报表管理</h3>
			<ul class="categoryitems">
			<li><a href="#">报表查询</a></li>
			</ul>
			
			<security:authorize ifAnyGranted="ROLE_ADMIN">
			<h3 class="menuheader expandable">系统管理</h3>
			<ul class="categoryitems">
			<li><a href="${ctx }/usrMgmt/initUsersSearch.do">用户管理</a></li>
			<li><a href="${ctx }/system/initCodetablesSearch.do">代码维护</a></li>
			<li><a href="${ctx }/system/initTransactionlogSearch.do">日志管理</a></li>
			<li><a href="${ctx }/system/initSystemParametersSearch.do">参数管理</a></li>
			<li><a href="#">数据备份</a></li>
			</ul>
			</security:authorize>
			
			<h3 class="menuheader expandable">其他</h3>
			<ul class="categoryitems">
			<li><a href="${ctx }/initChangePassword.do">密码修改</a></li>
			<li><a href="${ctx}/j_spring_security_logout">退出系统</a></li>
			</ul>
</div>