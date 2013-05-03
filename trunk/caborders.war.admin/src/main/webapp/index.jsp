<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/meta.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网站后台管理系统-深圳市凯博德企业咨询有限公司</title>
<script type="text/javascript">
    Ext.require(['*']);

    Ext.onReady(function() {

        Ext.QuickTips.init();

        Ext.state.Manager.setProvider(Ext.create('Ext.state.CookieProvider'));

		var store = Ext.create('Ext.data.TreeStore', {
		  root: {
		   expanded: true,
		   children: [
            { text: "资讯管理", iconCls: 'menu-info', expanded: true, children: [
                { id:'info_create',text: "资讯发布", iconCls: 'menu-addInfo', leaf: true},
                { id:'info_search',text: "资讯查询", iconCls: 'menu-listInfo', leaf: true}
            ] },
            { text: "职位管理", iconCls: 'menu-position', expanded: true, children: [
                { id:'position_create',text: "职位发布", iconCls: 'menu-addPosition', leaf: true },
                { id:'position_search',text: "职位查询", iconCls: 'menu-listPosition', leaf: true}
            ] },
            { text:"系统管理", iconCls: 'menu-admin', expanded: true , children:[
                { id:'logout',text:'登出系统', iconCls: 'menu-logout', leaf: true}]}]
		  }
		});

		var infoPanel = Ext.create('Ext.form.Panel', {
		      id:'info_create',
		      title:'资讯发布',
			  closable:true,
			  frame:false,
			  region:'center',
			  bodyStyle:'padding:5px 5px 5px 5px',
			  labelAlign: 'top',
			  border:0, 
			  items: [{
			      xtype:'textfield',
				  fieldLabel: '标题',
				  name: 'first',
				  anchor:'100%'
			  },{
			      xtype: 'htmleditor',
				  fieldLabel: '内容',
				  name: 'bio',
				  autoScroll:true,
				  height:480,
				  anchor:'100%'
			  }],
			  buttons: [{text:'预览'},{text: '发布'},{text: '重置'},{text:'关闭'}]
		});

		var positionPanel = Ext.create('Ext.form.Panel', {
		      id:'position_create',
		      title:'职位发布',
			  closable:true,
			  frame:false,
			  region:'center',
			  bodyStyle:'padding:5px 5px 5px 5px',
			  labelAlign: 'top',
			  border:0, 
			  items: [{
			      xtype:'textfield',
				  fieldLabel: '标题',
				  name: 'first',
				  anchor:'80%'
			  },{
			      xtype: 'htmleditor',
				  fieldLabel: '内容',
				  name: 'bio',
				  autoScroll:true,
				  height: 420,
				  anchor: '100%'
			  }]
		});

		var top = Ext.create('Ext.Component', {
                region: 'north',
                height: 50, 
				contentEl : 'top'
        });

		var footer = Ext.create('Ext.Component', {
                region: 'south',
				height: 25 
        });

		var main = Ext.create('Ext.tab.Panel', {
		   region:'center',
		   margins:'5,5,5,0',
		   items: [{ title:'主页',
			         iconCls:'menu-index',
				     closable:false,
				     frame:false,
				     region:'center',
				     bodyStyle:'padding:5px 5px 5px 5px'}]
		});

		var tree = Ext.create('Ext.tree.Panel', {
				                                     width: 200,
													 border:0,
													 rootVisible: false,
													 root: {
														   expanded: true,
														   children: [
												            { text: "资讯管理", iconCls: 'menu-info', expanded: true, children: [
												                { id:'info_create',text: "资讯发布", iconCls: 'menu-addInfo', leaf: true},
												                { id:'info_search',text: "资讯查询", iconCls: 'menu-listInfo', leaf: true}
												            ] },
												            { text: "职位管理", iconCls: 'menu-position', expanded: true, children: [
												                { id:'position_create',text: "职位发布", iconCls: 'menu-addPosition', leaf: true },
												                { id:'position_search',text: "职位查询", iconCls: 'menu-listPosition', leaf: true}
												            ] },
												            { text:"系统管理", iconCls: 'menu-admin', expanded: true , children:[
												                { id:'logout',text:'登出系统', iconCls: 'menu-logout', leaf: true}]}]
														  }
		});

		tree.on('itemclick',function(view,record,item,index,e){   
		    if(record.raw.leaf){
			   var tab = main.getComponent(record.raw.id);      

			   if(record.raw.id == 'info_create'){
			      tab = main.add(infoPanel);
			   }

			   if(record.raw.id == 'position_create'){
			      tab = main.add(positionPanel);
			   }
               main.setActiveTab(tab);  
			}
		});

		var menu = Ext.create('Ext.panel.Panel',{
		        region: 'west',
                id: 'west-panel', 
                title: '控制台',
                split: true,
                width: 200,
                margins: '5 0 5 5',
				items:[tree]
		});

		

        Ext.create('Ext.Viewport', {
            id: 'border-example',
            layout: 'border',
            items: [top,footer,menu,main]
        });
        
        Ext.Ajax.request({
            url: '${ctx}/test.do',
            success: function(response){
                var text = response.responseText;
               alert(text);
            }
        });
    });
    </script>
</head>
<body>
<body>
    <div id="top">
	  <img src="${ctx}/images/caborders/logo.png"/>
	  <img src="${ctx}/images/caborders/title.png" />
	</div>
    <div id="west" class="x-hide-display"></div>
    <div id="center1" class="x-hide-display"></div>

	<div id="footer" class="footer">Copyright &copy; 2010 深圳市凯博德企业咨询有限公司</div>
</body>
</body>
</html>