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
        
        Ext.form.Field.prototype.msgTarget='side';

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
		
		var categories = Ext.create('Ext.data.Store', {
		    fields: ['value', 'label'],
		    data : [
		        {"value":"I", "label":"行业动态"},
		        {"value":"D", "label":"管理文摘"},
		        {"value":"O", "label":"其他资讯"}
		    ]
		});

		var infoPanel = Ext.create('Ext.form.Panel', {
		      id:'info_create',
		      title:'资讯发布',
		      iconCls:'menu-addInfo',
			  closable:true,
			  frame:false,
			  region:'center',
			  bodyStyle:'padding:5px 5px 5px 5px',
			  labelAlign: 'top',
			  border:0, 
			  url:'${ctx}/test.do',
			  items: [{
				  xtype:'combobox',
				  fieldLabel: '资讯类型',
				  name:'category',
				  editable:false,
				  autoSelect:true,
				  store: categories,
				  queryMode: 'local',
				  displayField: 'label',
				  valueField: 'value',
				  emptyText:'--- 请选择 ---',
				  allowBlank:false, 
	              blankText:"类型不能为空!"
			  },{
			      xtype:'textfield',
				  fieldLabel: '标题',
				  name: 'title',
				  anchor:'100%',
				  allowBlank:false, 
	              blankText:"标题不能为空!"
			  },{
			      xtype: 'htmleditor',
				  fieldLabel: '内容',
				  name: 'content',
				  autoScroll:true,
				  height:440,
				  anchor:'100%',
				  allowBlank:false, 
	              blankText:"内容不能为空!"
			  }],
			  buttons: [{text:'预览',handler:function(){
				  
			  }},
			            {text: '发布',
				         handler:function(){
				        	 if(Ext.getCmp('info_create').getForm().isValid()){
				        		 var mask = new Ext.LoadMask(Ext.getBody(), {
				                        msg: '正在保存，请稍后！',
				                        removeMask: true //完成后移除
				                 });
				                 mask.show();
					        	 Ext.Ajax.request({ 
									  url : '${ctx}/information/release.do', 
									  method: 'POST', 
									  headers: { 'Content-Type': 'application/json' },                        
									  jsonData: Ext.JSON.encode(infoPanel.getValues()), 
									  success: function (response) { 
									                      mask.hide();
									              }, 
									  failure: function (response) { 
									                  var jsonResp = Ext.JSON.decode(response.responseText); 
									                  Ext.Msg.alert("Error",jsonResp.error); 
									                  mask.hide();
									  } 
								});  
				        	 }
			            }},
			            {text: '重置',handler:function(){Ext.getCmp('info_create').getForm().reset();}},
			            {text:'关闭',handler:function(){Ext.getCmp('info_create').close();}}]
		});

		var positionPanel = Ext.create('Ext.form.Panel',{ 
        	title: "form Layout", 
        	height: 150, 
        	width: 230, 
        	plain: true, 
        	items:  
        	{ 
        	xtype: "form", 
        	labelWidth: 30, 
        	defaultType: "textfield", 
        	frame:true, 
        	items:  
        	[ { 
        	fieldLabel:"姓名", 
        	name:"username", 
        	allowBlank:false 
        	}, { 
        	fieldLabel:"呢称", 
        	name:"nickname" 
        	}, { 
        	fieldLabel: "生日", 
        	xtype:'datefield', 
        	name: "birthday", 
        	} 
        	] 
        	} 
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
			      main.setActiveTab(tab);  
			   }
			   
			   if(record.raw.id == 'info_search'){
				   Ext.define('Information', {  
		                extend : 'Ext.data.Model',  
		                fields : [{  
		                            name : 'systemRefInfo',  
		                            type : 'string',  
		                        }, {  
		                            name : 'title',  
		                            type : 'string'  
		                        }, {  
		                            name : 'category',  
		                            type : 'string'  
		                        }, {  
		                            name : 'createBy',  
		                            type : 'string'  
		                        }]
		            }); 
				   
				   var infoStore = new Ext.data.Store({  
		                model : 'Information',  
		                pageSize : 15,  
		                proxy : {  
		                    type : 'ajax',  
		                    url : '${ctx}/information/list.do',  
		                    reader : {  
		                        type : 'json',  
		                        root : 'list',  
		                        totalProperty : 'totalCount'  
		                    }  
		                },  
		                autoLoad : false  
		            });  
				   
				   var grid = Ext.create('Ext.grid.Panel', {   
				        store : infoStore,  
				        columnLines : true,   
				        stripeRows:true,
				        loadMask:true,
				        columns : [{  
				                    header : '资讯编号',  
				                    dataIndex : 'systemRefInfo' 
				                }, {  
				                    header : '标题',  
				                    dataIndex : 'title'
				                }, {  
				                    header : '分类',  
				                    dataIndex : 'category'
				                }, {  
				                    header : '创建人',  
				                    dataIndex : 'createBy',  
				                }],  
				        forceFit : true,  
				        dockedItems : [{  
				                    xtype : 'pagingtoolbar',  
				                    store : infoStore, 
				                    dock : 'bottom',  
				                    displayInfo : true ,
				                    displayMsg: '显示 {0} - {1} 条，共计 {2} 条',
				                    emptyMsg: '没有数据'
				                }],  
				        });
				   var cf = Ext.create('Ext.form.Panel', {
				        frame:true,
				        border:0,
				        bodyStyle:'padding:5px 5px 0',
				        items: [{
				            xtype:'fieldset',
				            title: '查询条件',
				            collapsible: false,
				            layout: 'anchor',
				            labelAlign:'right',
				            items :[{
				            	 xtype:'textfield',
				            	 fieldLabel: '资讯编号',
				            	 name: 'id',
				            	 anchor:'30%'
			                }, {
			                	xtype:'container',
			                    layout:'column',
			                    anchor:'100%',
			                    items: [{
			                       xtype:'container',
		                           layout:'anchor',
		                           columnWidth:0.6,
		                           items:[{
		                                 xtype     : 'textfield',
			                             name      : 'title',
			                             fieldLabel: '资讯标题',
			                             anchor:'90%'
		                           }]
			                    },{
			      				  xtype:'combobox',
			    				  fieldLabel: '资讯类型',
			    				  name:'category',
			    				  editable:false,
			    				  autoSelect:true,
			    				  store: categories,
			    				  queryMode: 'local',
			    				  displayField: 'label',
			    				  valueField: 'value',
			    				  emptyText:'--- 请选择 ---',
			    				  columnWidth:0.3,
			    			  }]
			                },{
			                	 xtype:'container',
			                     layout: 'column',
			                     anchor:'60%',
			                     items: [
			                         {
			                             xtype:'container',
			                             layout:'anchor',
			                             columnWidth:0.45,
			                             items:[{
			                            	 xtype     : 'datefield',
				                             name      : 'fromDate',
				                             fieldLabel: '发布日期',
				                             anchor:'100%',
			                             }]
			                         },{
			                        	 xtype:'container',
			                             layout:'anchor',
			                             columnWidth:0.45,
			                             items:[{
			                            	 xtype     : 'datefield',
				                             name      : 'toDate',
				                             fieldLabel: '至',
				                             labelAlign:'right',
				                             anchor:'100%'
			                             }]
			                         }
			                     ]
			                }]
				        }],

				        buttons: [{
				            text: '查询',
				            handler: function(){
				            	 Ext.Ajax.request({ 
									  url : '${ctx}/information/search.do', 
									  method: 'POST', 
									  params: { start: 0, limit: 15},
									  headers: { 'Content-Type': 'application/json' },                        
									  jsonData: Ext.JSON.encode(cf.getValues()),
									  success: function(response){
										  var result = Ext.JSON.decode(response.responseText);
										  alert(response.responseText);
										  infoStore.loadData(result);
									  }
								});  
				            }
				        },{
				            text: '关闭'
				        }]
				    });

				   
				   var gridPanel = Ext.create('Ext.Panel',{
					   title : '资讯查询',
				       iconCls:'menu-listInfo',
				       closable:true,
				       items:[cf,grid]
				   });

				   tab = main.add(gridPanel);
				   main.setActiveTab(tab);  
				   infoStore.load({ params: { start: 0, limit: 15} });  
			   }

			   if(record.raw.id == 'position_create'){
			      tab = main.add(positionPanel);
			      main.setActiveTab(tab);  
			   }
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