<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags/common" prefix="common"%>

<div id="create_form" title="创建人才来源" class="pop_div">
   <form:form id="newTalentSourceForm" commandName="talentSourceDto" action="${ctx }/system/codetable/submitNewTalentSource.do">
      <fieldset class="pop_fieldset">
        <label class="pop_label" for="name">名称(显示名称)：<span class="mandatoryField">*</span></label>
        <form:input id="displayName_create" path="displayName" cssClass="text ui-widget-content ui-corner-all" ></form:input>
        <label class="pop_label" for="email">官方网站：</label>
        <form:input id="officialSite_create" path="officialSite" cssClass="text ui-widget-content ui-corner-all" ></form:input>
      </fieldset>
   </form:form>
</div>

