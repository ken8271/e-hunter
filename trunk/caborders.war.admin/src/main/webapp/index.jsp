<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/meta.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网站后台管理系统[深圳市铠博德企业管理咨询有限公司]</title>
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
                { id:'logout',text:'登出系统(未开通)', iconCls: 'menu-logout', leaf: true}]}]
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
		   id : 'main',
		   region:'center',
		   margins:'5,5,5,0',
		   autoScroll:true ,
		   items: [{ title:'主页',
			         iconCls:'menu-index',
				     closable:false,
				     frame:false,
				     region:'center',
				     bodyStyle:'padding:5px 5px 5px 5px'
				    }]
		});

		var tree = Ext.create('Ext.tree.Panel', {
			width: 200,
			border:0,
			rootVisible: false,
			store : store,
		});

		tree.on('itemclick',function(view,record,item,index,e){   
		    if(record.raw.leaf){
			   var tab = main.getComponent(record.raw.id);   
			   
			   if(tab){
				   main.setActiveTab(tab);  
			   }else {
				   if(record.raw.id == 'info_create'){
					   tab = main.add(initInformationCreatePanel());
					   main.setActiveTab(tab);  
				   }
					   
				   if(record.raw.id == 'info_search'){
					   tab = main.add(initInformationSearchPanel());
					   main.setActiveTab(tab);  
				   }

				   if(record.raw.id == 'position_create'){
					   tab = main.add(initPositionCreatePanel());
					   main.setActiveTab(tab);  
				   }
					   
				   if(record.raw.id == 'position_search'){
					   tab = main.add(initPositionSearchPanel());
					   main.setActiveTab(tab);
				   }
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
            id: 'caborders',
            layout: 'border',
            items: [top,footer,menu,main]
        });
        
    });
    
    function initCatgoriesStore(){
    	var categories = Ext.create('Ext.data.Store', {
		    fields: ['value', 'label'],
		    data : [
		        {"value":"I", "label":"行业动态"},
		        {"value":"D", "label":"管理文摘"},
		        {"value":"O", "label":"其他资讯"}
		    ]
		});
    	
    	return categories;
    }
    
    function initInformationCreatePanel(){
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
			  items: [{
				  xtype:'combobox',
				  fieldLabel: '资讯类型',
				  name:'category',
				  editable:false,
				  autoSelect:true,
				  store: initCatgoriesStore(),
				  queryMode: 'local',
				  displayField: 'label',
				  valueField: 'value',
				  emptyText:'--- 请选择 ---',
			  },{
				  id:'title',
			      xtype:'textfield',
				  fieldLabel: '标题',
				  name: 'title',
				  anchor:'100%',
			  },{
				  id:'content',
			      xtype: 'htmleditor',
				  fieldLabel: '内容',
				  name: 'content',
				  autoScroll:true,
				  height:400,
				  anchor:'100%',
			  }],
			  buttons: [{text:'预览',
				         handler:function(){
				        	 if(Ext.getCmp('info_create').getForm().isValid()){
				        		 var title = Ext.getCmp('info_create').getForm().getFields().get('title').getValue();
				        		 var content = Ext.getCmp('info_create').getForm().getFields().get('content').getValue();
				        		 popUp4Preview(title,content);
				        	 }
				         }
				        },{text: '发布',
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
									  jsonData: Ext.JSON.encode(Ext.getCmp('info_create').getValues()), 
									  success: function (response) { 
										  mask.hide();
										  
										  var result = Ext.JSON.decode(response.responseText);    
										  if(result.success) {
											  Ext.Msg.alert('提示','发布成功');
											  Ext.getCmp('info_create').getForm().reset();
							              } else {  
							            	  genErrorContainer(result.messages);
							              } 
									  }, 
									  failure: function (response) { 
										  mask.hide();
										  Ext.Msg.alert("错误",'服务器错误，请联系管理员！'); 
									  } 
								});  
				        	 }
			            }},
			            {text: '重置',handler:function(){Ext.getCmp('info_create').getForm().reset();}},
			            {text:'关闭',handler:function(){Ext.getCmp('info_create').close();}}]
		});
    	
    	return infoPanel;
    }
    
    function initInformationSearchPanel(){
    	Ext.define('Information', {  
            extend : 'Ext.data.Model',  
            fields : [{  
                        name : 'systemRefInfo',  
                        type : 'string',  
                    }, {  
                        name : 'title',  
                        type : 'string'  
                    }, {  
                        name : 'categoryDesc',  
                        type : 'string'  
                    }, {  
                        name : 'createBy',  
                        type : 'string'  
                    },{
                    	name : 'createDateStr',
                    	type : 'string'
                    }]
        }); 
    	
    	var infoStore = new Ext.data.Store({  
    		storeId: 'infoStore',
            model : 'Information',  
            pageSize : 10,  
            proxy : {  
                type : 'ajax',  
                url : '${ctx}/information/list.do',  
                reader : {  
                    type : 'json',  
                    root : 'list',  
                    totalProperty : 'totalCount'  
                }  
            },  
            listeners:{
            	beforeload:{
            		fn : function(){
            			var cf = Ext.getCmp('info_enquiry_form');
            			Ext.apply(infoStore.proxy.extraParams, cf.getValues());
            		}
            	}
            },
            autoLoad : false  
       });  
	   
	   var grid = Ext.create('Ext.grid.Panel', {
		    id:'info_result_grid',
	        store : infoStore,  
	        columnLines : true,   
	        stripeRows:true,
	        loadMask:true,
	        columns : [{  
	                    header : '资讯编号', 
	                    dataIndex : 'systemRefInfo',
	                }, {  
	                    header : '标题',  
	                    dataIndex : 'title',
	                }, {  
	                    header : '类型',  
	                    dataIndex : 'categoryDesc',
	                }, {  
	                    header : '创建人',  
	                    dataIndex : 'createBy',  
	                },{
	                	header : '发布时间',
	                	dataIndex : 'createDateStr',
	                },{
	                    xtype: 'actioncolumn',
	                    align : 'center',
	                    header: '操作',
	                    width: 80,
	                    items: [{
	                        icon   : '${imagePath}/caborders/edit.gif',  
	                        tooltip: '编辑',
	                        getClass: function(v, meta, rec){return 'action-img';},
	                        handler: function(grid, row, col) {
	                        	var main = Ext.getCmp('main');
	                        	var tab = main.getComponent('info_maintenance_tab');   
	                        	
	                        	if(!tab){
	                        		tab = main.add(initInfoMaintenancePanel());
	                        	}
	                        	
	                        	var record = grid.getStore().getAt(row);
	                        	loadInfoByID(record.get('systemRefInfo'));
	                        	
	                        	main.setActiveTab(tab); 
	                        }
	                    },{
	                    	icon   : '${imagePath}/caborders/blank.gif'
	                    },{
	                        icon   : '${imagePath}/caborders/delete.gif',  
	                        tooltip: '删除',
	                        getClass: function(v, meta, rec){return 'action-img';},
	                        handler: function (grid, row, col) {
	                        	Ext.Msg.confirm("请确认", "是否确认删除该数据？", function(button, text) {  
	                                if (button == "yes") {
	                                	var store = grid.getStore();
	                                	var record = store.getAt(row);

	                        			Ext.Ajax.request({ 
	                        				  url : '${ctx}/information/delete.do', 
	                        				  method: 'POST', 
	                        				  params : {id : record.get('systemRefInfo')}, 
	                        				  success: function (response) { 
	                        					  var result = Ext.JSON.decode(response.responseText);    
	                        					  if(result.success) {
	                        						  store.load({ params: { start: 0, limit: 10} });
	                        		              } else {  
	                        		            	  genErrorContainer(result.messages);
	                        		              } 
	                        				  }, 
	                        				  failure: function (response) { 
	                        					  Ext.Msg.alert("错误",'服务器错误，请联系管理员！'); 
	                        				  } 
	                        			});
	                                }
	                        	});
	                        }
	                    },{
	                    	icon   : '${imagePath}/caborders/blank.gif'
	                    },{
	                        icon   : '${imagePath}/caborders/tips.gif',  
	                        tooltip: '查看',
	                        getClass: function(v, meta, rec){return 'action-img';},
	                        handler: function(grid, row, col) {
	                        	var store = grid.getStore();
	                        	var record = store.getAt(row);

	                			Ext.Ajax.request({ 
	                				  url : '${ctx}/information/view.do', 
	                				  method: 'POST', 
	                				  params : {id : record.get('systemRefInfo')}, 
	                				  success: function (response) { 
	                					  var result = Ext.JSON.decode(response.responseText);    
	                					  if(result.success) {
	                						  var displayStr = '<div class="contents">'
	                						                 + '<h1>' + result.data.title + '</h1>'
	                						                 + '<div class="article">'
	                						                 + result.data.content
	                						                 + '</div>'
	                						                 + '</div>';
	                						  Ext.create('Ext.window.Window', {
	                							  title : '资讯查看[' + result.data.systemRefInfo + ']',
	                							  height: 500,
	                							  width: 800,
	                							  frame : false,
	                							  modal : true ,
	                							  autoScroll:true,
	                							  html : displayStr
	                						  }).show();
	                		              } else {  
	                		            	  genErrorContainer(result.messages);
	                		              } 
	                				  }, 
	                				  failure: function (response) { 
	                					  Ext.Msg.alert("错误",'服务器错误，请联系管理员！'); 
	                				  } 
	                			});
	                        }
	                    }]
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
		    id : 'info_enquiry_form',
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
    				  store: initCatgoriesStore(),
    				  queryMode: 'local',
    				  displayField: 'label',
    				  valueField: 'value',
    				  emptyText:'--- 请选择 ---',
    				  columnWidth:0.3,
    			  }]
                }]
	        }],

	        buttons: [{
	            text: '发布新资讯',
	            handler: function(){
	            	var main = Ext.getCmp('main');
	            	var tab = Ext.getCmp('info_create');   
					   
	            	if(tab){
	            		main.setActiveTab(tab);  
	            	}else {
	            		tab = main.add(initInformationCreatePanel());
	            		main.setActiveTab(tab);
	            	}
	            }
	        },{
	            text: '查询',
	            handler: function(){
	            	infoStore.load({ params: { start: 0, limit: 10} });
	            }
	        },{
	            text: '重置',
	            handler : function(){
	            	Ext.getCmp('info_enquiry_form').getForm().reset();
	            }
	        }]
	   });
	   
	   var gridPanel = Ext.create('Ext.Panel',{
		   id:'info_search',
		   title : '资讯查询',
	       iconCls:'menu-listInfo',
	       closable:true,
	       autoScroll:true ,
	       items:[cf,grid]
	   });
	   
	   infoStore.load({ params: { start: 0, limit: 10} });  
	   
	   return gridPanel;
    }
    
    function initInfoMaintenancePanel(){
    	var infoPanel = Ext.create('Ext.form.Panel', {
		      id:'info_maintenance_tab',
		      title:'资讯修改',
		      iconCls:'menu-edit',
			  closable:true,
			  frame:false,
			  region:'center',
			  bodyStyle:'padding:5px 5px 5px 5px',
			  labelAlign: 'top',
			  border:0, 
			  items: [{
				  id : 'systemRefInfo',
				  xtype: 'textfield',
			      fieldLabel: '资讯编号',
			      name: 'systemRefInfo',
			      anchor:'30%',
			      readOnly: true
			  },{
				  xtype:'combobox',
				  fieldLabel: '资讯类型',
				  name:'category',
				  editable:false,
				  autoSelect:true,
				  store: initCatgoriesStore(),
				  queryMode: 'local',
				  displayField: 'label',
				  valueField: 'value',
				  emptyText:'--- 请选择 ---',
			  },{
			      xtype:'textfield',
				  fieldLabel: '标题',
				  name: 'title',
				  anchor:'100%',
			  },{
				  id:'content',
			      xtype: 'htmleditor',
				  fieldLabel: '内容',
				  name: 'content',
				  autoScroll:true,
				  height:400,
				  anchor:'100%',
			  }],
			  buttons: [{
				  text:'预览',
				  disabled:true
			  },{
				  text: '更新',
				  handler:function(){
					  if(Ext.getCmp('info_maintenance_tab').getForm().isValid()){
						  var mask = new Ext.LoadMask(Ext.getBody(), {
							  msg: '正在保存，请稍后！',
							  removeMask: true
						  });
						  mask.show();
						  Ext.Ajax.request({ 
							  url : '${ctx}/information/update.do', 
							  method: 'POST', 
							  headers: { 'Content-Type': 'application/json' },                        
							  jsonData: Ext.JSON.encode(Ext.getCmp('info_maintenance_tab').getValues()), 
							  success: function (response) { 
								  mask.hide();
										  
								  var result = Ext.JSON.decode(response.responseText);    
								  if(result.success) {
									  Ext.Msg.alert('提示','更新成功');
									  var main = Ext.getCmp('main');
			                          var tab = main.getComponent('info_search');
			                          main.setActiveTab(tab);
			                          Ext.getCmp('info_maintenance_tab').close();
			                          
			                          tab.getComponent('info_result_grid').getStore().loadPage(1);
								  } else {  
									  genErrorContainer(result.messages);
								  } 
							  }, 
							  failure: function (response) { 
								  mask.hide();
								  Ext.Msg.alert("错误",'服务器错误，请联系管理员！'); 
							  } 
						  });  
					  }
				  }
			  },{
				  text: '重置',
				  handler:function(){
					  var id = Ext.getCmp('info_maintenance_tab').getForm().getFields().get('systemRefInfo').getValue();
					  loadInfoByID(id);
				  }
			  },{
				  text:'关闭',
				  handler:function(){Ext.getCmp('info_maintenance_tab').close();}
			  }]
		});
    	
    	return infoPanel;
    }
    
    function loadInfoByID(id){
    	Ext.getCmp('info_maintenance_tab').getForm().load({
			  url : '${ctx}/information/view.do',
			  params : {id : id},
			  failure : function(response){
				  var result = Ext.JSON.decode(response.responseText);
				  genErrorContainer(result.messages);
			  }
	    });
    }
    
    function initPositionCreatePanel(){
    	var postPanel = Ext.create('Ext.form.Panel', {
		      id:'position_create',
		      title:'职位发布',
		      iconCls:'menu-addPosition',
			  closable:true,
			  frame:false,
			  region:'center',
			  bodyStyle:'padding:5px 5px 5px 5px',
			  labelAlign: 'top',
			  border:0, 
			  items: [{
			      xtype:'textfield',
				  fieldLabel: '标题',
				  name: 'title',
				  anchor:'100%',
			  },{
			      xtype:'textfield',
				  fieldLabel: '工作城市',
				  name: 'workCity',
				  anchor:'100%',
			  },{
			      xtype: 'htmleditor',
				  fieldLabel: '职位描述',
				  name: 'content',
				  autoScroll:true,
				  height:400,
				  anchor:'100%',
			  }],
			  buttons: [{text:'预览',
				         disabled:true
				        },{text: '发布',
				         handler:function(){
				        	 if(Ext.getCmp('position_create').getForm().isValid()){
				        		 var mask = new Ext.LoadMask(Ext.getBody(), {
				                        msg: '正在保存，请稍后！',
				                        removeMask: true 
				                 });
				                 mask.show();
					        	 Ext.Ajax.request({ 
									  url : '${ctx}/career/release.do', 
									  method: 'POST', 
									  headers: { 'Content-Type': 'application/json' },                        
									  jsonData: Ext.JSON.encode(Ext.getCmp('position_create').getValues()), 
									  success: function (response) { 
										  mask.hide();
										  var result = Ext.JSON.decode(response.responseText);    
										  if(result.success) {
											  Ext.Msg.alert('提示','发布成功');
											  Ext.getCmp('position_create').getForm().reset();
							              } else {  
							            	  genErrorContainer(result.messages);
							              } 
									  }, 
									  failure: function (response) { 
										  var jsonResp = Ext.JSON.decode(response.responseText); 
										  Ext.Msg.alert("Error",jsonResp.error); 
										  mask.hide();
									  } 
								});  
				        	 }
			            }},
			            {text: '重置',handler:function(){Ext.getCmp('position_create').getForm().reset();}},
			            {text:'关闭',handler:function(){Ext.getCmp('position_create').close();}}]
		});
    	
    	return postPanel;
    }
    
    function initPositionSearchPanel(){
    	Ext.define('Position', {  
            extend : 'Ext.data.Model',  
            fields : [{  
                        name : 'systemRefPosition',  
                        type : 'string',  
                    }, {  
                        name : 'title',  
                        type : 'string'  
                    }, {  
                        name : 'workCity',  
                        type : 'string'  
                    }, {  
                        name : 'createBy',  
                        type : 'string'  
                    },{
                    	name : 'createDateStr',
                    	type : 'string'
                    }]
        }); 
	   
	   var positionStore = new Ext.data.Store({  
            model : 'Position',  
            pageSize : 10,  
            proxy : {  
                type : 'ajax',  
                url : '${ctx}/career/list.do',  
                reader : {  
                    type : 'json',  
                    root : 'list',  
                    totalProperty : 'totalCount'  
                }  
            },  
            listeners:{
            	beforeload:{
            		fn : function(){
            			var cf = Ext.getCmp('position_enquiry_form');
            			Ext.apply(positionStore.proxy.extraParams, cf.getValues());
            		}
            	}
            },
            autoLoad : false 
       });  
	   
	   var grid = Ext.create('Ext.grid.Panel', {   
		    id : 'postn_grid',
	        store : positionStore,  
	        columnLines : true,   
	        stripeRows:true,
	        loadMask:true,
	        columns : [{  
	                    header : '职位编号',  
	                    dataIndex : 'systemRefPosition' 
	                }, {  
	                    header : '标题',  
	                    dataIndex : 'title'
	                }, {  
	                    header : '工作城市',  
	                    dataIndex : 'workCity'
	                }, {  
	                    header : '创建人',  
	                    dataIndex : 'createBy',  
	                }, {  
	                    header : '发布时间',  
	                    dataIndex : 'createDateStr',  
	                }, {
	                    xtype: 'actioncolumn',
	                    align : 'center',
	                    header: '操作',
	                    width: 80,
	                    items: [{
	                        icon   : '${imagePath}/caborders/edit.gif',  
	                        tooltip: '编辑',
	                        getClass: function(v, meta, rec){return 'action-img';},
	                        handler: function(grid, row, col) {
	                        	var main = Ext.getCmp('main');
	                        	var tab = main.getComponent('postn_mainten');   
	                        	
	                        	if(!tab){
	                        		tab = main.add(initPostnMaintenancePanel());
	                        	}
	                        	
	                        	var record = grid.getStore().getAt(row);
	                        	loadPostnByID(record.get('systemRefPosition'));
	                        	
	                        	main.setActiveTab(tab); 
	                        }
	                    },{
	                    	icon   : '${imagePath}/caborders/blank.gif'
	                    },{
	                        icon   : '${imagePath}/caborders/delete.gif',  
	                        tooltip: '删除',
	                        getClass: function(v, meta, rec){return 'action-img';},
	                        handler: function (grid, row, col) {
	                        	Ext.Msg.confirm("请确认", "是否确认删除该数据？", function(button, text) {  
	                                if (button == "yes") {
	                                	var store = grid.getStore();
	                                	var record = store.getAt(row);

	                        			Ext.Ajax.request({ 
	                        				  url : '${ctx}/career/delete.do', 
	                        				  method: 'POST', 
	                        				  params : {id : record.get('systemRefPosition')}, 
	                        				  success: function (response) { 
	                        					  var result = Ext.JSON.decode(response.responseText);    
	                        					  if(result.success) {
	                        						  store.load({ params: { start: 0, limit: 10} });
	                        		              } else {  
	                        		            	  genErrorContainer(result.messages);
	                        		              } 
	                        				  }, 
	                        				  failure: function (response) { 
	                        					  Ext.Msg.alert("错误",'服务器错误，请联系管理员！'); 
	                        				  } 
	                        			});
	                                }
	                        	});
	                        }
	                    },{
	                    	icon   : '${imagePath}/caborders/blank.gif'
	                    },{
	                        icon   : '${imagePath}/caborders/tips.gif',  
	                        tooltip: '查看',
	                        getClass: function(v, meta, rec){return 'action-img';},
	                        handler: function(grid, row, col) {
	                        	var store = grid.getStore();
	                        	var record = store.getAt(row);

	                			Ext.Ajax.request({ 
	                				  url : '${ctx}/career/view.do', 
	                				  method: 'POST', 
	                				  params : {id : record.get('systemRefPosition')}, 
	                				  success: function (response) { 
	                					  var result = Ext.JSON.decode(response.responseText);    
	                					  if(result.success) {
	                						  var displayStr = '<div class="contents">'
	                						                 + '<h1>' + result.data.title + '</h1>'
	                						                 + '<div class="article">'
	                						                 + result.data.content
	                						                 + '</div>'
	                						                 + '</div>';
	                						  Ext.create('Ext.window.Window', {
	                							  title : '职位查看[' + result.data.systemRefPosition + ']',
	                							  height: 500,
	                							  width: 800,
	                							  frame : false,
	                							  modal : true ,
	                							  autoScroll:true,
	                							  html : displayStr
	                						  }).show();
	                		              } else {  
	                		            	  genErrorContainer(result.messages);
	                		              } 
	                				  }, 
	                				  failure: function (response) { 
	                					  Ext.Msg.alert("错误",'服务器错误，请联系管理员！'); 
	                				  } 
	                			});
	                        }
	                    }]
	                }],  
	        forceFit : true,  
	        dockedItems : [{  
	                    xtype : 'pagingtoolbar',  
	                    store : positionStore, 
	                    dock : 'bottom',  
	                    displayInfo : true ,
	                    displayMsg: '显示 {0} - {1} 条，共计 {2} 条',
	                    emptyMsg: '没有数据'
	                }],  
	   });
	   
	   var cf = Ext.create('Ext.form.Panel', {
		    id : 'position_enquiry_form',
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
	            	 fieldLabel: '职位编号',
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
                             fieldLabel: '标题',
                             anchor:'90%'
                       }]
                    },{
                    	xtype:'container',
                        layout:'anchor',
                        columnWidth:0.4,
                        items:[{
                              xtype     : 'textfield',
                              name      : 'workCity',
                              fieldLabel: '工作城市',
                              anchor:'90%'
                        }]
                    }]
                }]
	        }],
	        buttons: [{
	            text: '发布新职位',
	            handler: function(){
	            	var main = Ext.getCmp('main');
	            	var tab = Ext.getCmp('position_create');   
					   
	            	if(tab){
	            		main.setActiveTab(tab);  
	            	}else {
	            		tab = main.add(initPositionCreatePanel());
	            		main.setActiveTab(tab);
	            	}
	            }
	        },{
	            text: '查询',
	            handler: function(){
	            	positionStore.load({ params: { start: 0, limit: 10} });
	            }
	        },{
	            text: '重置',
	            handler: function(){
	            	Ext.getCmp('position_enquiry_form').getForm().reset();
	            }
	        }]
	   });
	   
	   var gridPanel = Ext.create('Ext.Panel',{
		   id:'position_search',
		   title : '职位查询',
	       iconCls:'menu-listPosition',
	       closable:true,
	       items:[cf,grid]
	   });
	   
	   positionStore.load({ params: { start: 0, limit: 10} });  
	   
	   return gridPanel;
    }
    
    function initPostnMaintenancePanel(){
    	var postPanel = Ext.create('Ext.form.Panel', {
		      id:'postn_mainten',
		      title:'职位修改',
		      iconCls:'menu-edit',
			  closable:true,
			  frame:false,
			  region:'center',
			  bodyStyle:'padding:5px 5px 5px 5px',
			  labelAlign: 'top',
			  border:0, 
			  items: [{
				  id : 'systemRefPosition',
			      xtype:'textfield',
				  fieldLabel: '职位编号',
				  name: 'systemRefPosition',
				  anchor:'30%',
				  readOnly : true
			  },{
			      xtype:'textfield',
				  fieldLabel: '标题',
				  name: 'title',
				  anchor:'100%',
			  },{
			      xtype:'textfield',
				  fieldLabel: '工作城市',
				  name: 'workCity',
				  anchor:'100%',
			  },{
			      xtype: 'htmleditor',
				  fieldLabel: '职位描述',
				  name: 'content',
				  autoScroll:true,
				  height:400,
				  anchor:'100%',
			  }],
			  buttons: [{text:'预览',
				         disabled:true
				        },{text: '更新',
				         handler:function(){
				        	 if(Ext.getCmp('postn_mainten').getForm().isValid()){
				        		 var mask = new Ext.LoadMask(Ext.getBody(), {
				                        msg: '正在保存，请稍后！',
				                        removeMask: true 
				                 });
				                 mask.show();
					        	 Ext.Ajax.request({ 
									  url : '${ctx}/career/update.do', 
									  method: 'POST', 
									  headers: { 'Content-Type': 'application/json' },                        
									  jsonData: Ext.JSON.encode(Ext.getCmp('postn_mainten').getValues()), 
									  success: function (response) { 
										  mask.hide();
										  var result = Ext.JSON.decode(response.responseText);    
										  if(result.success) {
											  Ext.Msg.alert('提示','更新成功');
											  var main = Ext.getCmp('main');
					                          var tab = main.getComponent('position_search');
					                          main.setActiveTab(tab);
					                          Ext.getCmp('postn_mainten').close();
					                          
					                          tab.getComponent('postn_grid').getStore().loadPage(1);
							              } else {  
							            	  genErrorContainer(result.messages);
							              } 
									  }, 
									  failure: function (response) { 
										  var jsonResp = Ext.JSON.decode(response.responseText); 
										  Ext.Msg.alert("Error",jsonResp.error); 
										  mask.hide();
									  } 
								});  
				        	 }
			            }},
			            {
			            	text: '重置',
			            	handler:function(){
			            		var id = Ext.getCmp('postn_mainten').getForm().getFields().get('systemRefPosition').getValue();
			            		loadPostnByID(id);
			            	}
			            },
			            {text:'关闭',handler:function(){Ext.getCmp('postn_mainten').close();}}]
		});
  	
    	return postPanel;
    }
    
    function loadPostnByID(id){
    	Ext.getCmp('postn_mainten').getForm().load({
			  url : '${ctx}/career/view.do',
			  params : {id : id},
			  failure : function(response){
				  var result = Ext.JSON.decode(response.responseText);
				  genErrorContainer(result.messages);
			  }
	    });
    }
    
    function genErrorContainer(msgs){
  	  var errorContainer = '<div id="errorContainer" class="errorContainer" >'
  	                     + '<ul>';
           
  	  for(var i=0 ; i<msgs.length ; i++){
  		  errorContainer = errorContainer + '<li>' + msgs[i].message+'</li>';
	  }
        
  	  errorContainer = errorContainer + '</ul></div>';

  	  Ext.Msg.show({
  		  title:'错误:',
  		  msg: errorContainer,
  		  buttons: Ext.Msg.CANCEL,
  		  width:350
  	  });
    }
    
    function popUp4Preview(title , content){
    	var displayStr = '<div class="contents">'
            + '<h1>' + title + '</h1>'
            + '<div class="article">'
            + content
            + '</div>'
            + '</div>';
        Ext.create('Ext.window.Window', {
        	title : '预览',
        	height: 500,
        	width: 800,
        	frame : false,
        	modal : true ,
        	autoScroll:true,
        	html : displayStr
        }).show();
    }
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

	<div id="footer" class="footer">Copyright &copy; 2003 - 2013   深圳市铠博德企业管理咨询有限公司</div>
</body>
</body>
</html>