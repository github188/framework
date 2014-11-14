<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common.jsp"%>
<script type="text/javascript">
	function admin_dic_appendFun() {
		
		var node = $('#admin_dic_treegrid').treegrid('getSelected');
		var id=node.id;
		var text=node.text;
		
		$('<div/>').dialog({
			href : '${ctx}/dicController/toAddPage.action',
			width : 300,
			height : 250,
			modal : true,
			title : '添加数据字典',
			buttons : [ {
				text : '增加',
				iconCls : 'icon-add',
				handler : function() {
					var d = $(this).closest('.window-body');
					$('#admin_dicAdd_addForm').form('submit', {
						url : '${ctx}/dicController/add.action',
						success : function(result) {
							try {
								var r = $.parseJSON(result);
								
								if (r.success) {
									
									$('#admin_dic_treegrid').treegrid('reload');
									
									d.dialog('destroy');
									$.messager.show({
										title : '提示',
										msg : r.msg
									});
								}else
									{
									d.dialog('destroy');
									$.messager.alert('提示框', '后台错误请与管理员联系','warning',function(){});
									}
								
							} catch (e) {
								$.messager.alert('提示', result);
							}
						}
					});
				}
			} ],
			onClose : function() {
				$(this).dialog('destroy');
			},
			onLoad : function() {
				$('#admin_dicAdd_addForm').form('load', {dicParentName:node.text,id:node.id});
			}
		});
	}
	function admin_dic_editFun(id) {
		if (id != undefined) {
			$('#admin_dic_treegrid').treegrid('select', id);
		}
		var node = $('#admin_dic_treegrid').treegrid('getSelected');
		$('<div/>').dialog({
			href : '${ctx}/dicController/toEditPage.action',
			width : 300,
			height : 250,
			modal : true,
			title : '编辑数据字典',
			buttons : [ {
				text : '编辑',
				iconCls : 'icon-edit',
				handler : function() {
					var d = $(this).closest('.window-body');
					$('#admin_dicEdit_editForm').form('submit', {
						url : '${ctx}/dicController/edit.action',
						success : function(result) {
							try {
								var r = $.parseJSON(result);
								if (r.success) {
									$('#admin_dic_treegrid').treegrid('reload');
									d.dialog('destroy');
									$.messager.show({
										title : '提示',
										msg : r.msg
									});
								}else
									{
									d.dialog('destroy');
									$.messager.alert('提示框', '后台错误请与管理员联系','warning',function(){});
									}
								
							} catch (e) {
								$.messager.alert('提示', result);
							}
						}
					});
				}
			} ],
			onClose : function() {
				$(this).dialog('destroy');
			},
			onLoad : function() {
				$('#admin_dicEdit_editForm').form('load', {dicParentName:node.attributes.parentDicName,id:node.id,dicName:node.text,dicCode:node.text2,status:node.text3,orderCode:node.text4});
			}
		});
	}
	function admin_dic_deleteFun(id) {
		if (id != undefined) {
			$('#admin_dic_treegrid').treegrid('select', id);
		}
		var node = $('#admin_dic_treegrid').treegrid('getSelected');
		if (node) {
			$.messager.confirm('询问', '您确定要删除【' + node.text + '】？', function(b) {
				if (b) {
					$.ajax({
						url : '${ctx}/dicController/delete.action',
						data : {
							id : node.id
						},
						cache : false,
						dataType : 'JSON',
						success : function(r) {
							if (r.success) {
								$('#admin_dic_treegrid').treegrid('remove', r.obj.id);
								$.messager.show({
									msg : r.msg,
									title : '提示'
								});
							}else
							{
								$.messager.alert('提示框', '请先删除关联信息','warning',function(){});
								}
							
						}
					});
				}
			});
		}
	}
	//展开
	function admin_dic_expandFun(){
	  var node = $('#admin_dic_treegrid').treegrid('getSelected');
					if (node) {
						$('#admin_dic_treegrid').treegrid('expandAll', node.cid);
					} else {
						$('#admin_dic_treegrid').treegrid('expandAll');
					}
	
	}
	//折叠
	function admin_dic_collapseAll(){
	  var node = $('#admin_dic_treegrid').treegrid('getSelected');
					if (node) {
						$('#admin_dic_treegrid').treegrid('collapseAll', node.cid);
					} else {
						$('#admin_dic_treegrid').treegrid('collapseAll');
					}
	}
	//刷新
	function admin_dic_reload(){
	   	$('#admin_dic_treegrid').treegrid('reload');
	   	
	   	$('#dicId').combobox('clear');
	}
	
	//查询
	function clearFun(){
		
		$('#admin_dic_treegrid').treegrid({
			url : '${ctx}/dicController/treegrid.action',
			queryParams:{id:$('#dicId').combobox('getValue')}
		});
	
	}
	
</script>

<body class="easyui-layout">

		<div data-options="region:'center'" style="padding:5px;background:#eee;">
      <table id="admin_dic_treegrid" class="easyui-treegrid" style="width:700px;height:250px"
            data-options="
                rownumbers: true,
                url: '${ctx}/dicController/treegrid.action',
                idField: 'id',
                treeField: 'text',
                parentField : 'pid',
                fit:true,
                border : false,
                toolbar :'#tb-dicListtb',
			
			onContextMenu : function(e, row) {
				e.preventDefault();
				$(this).treegrid('unselectAll');
				$(this).treegrid('select', row.id);
	 			if(row.id!=1)
					{
			      if(row.pid!=1)
					{
						$('#admin_dic_menu_two').menu('show', {
							left : e.pageX,
							top : e.pageY
						});
					}else{
					    $('#admin_dic_menu').menu('show', {
						    left : e.pageX,
						    top : e.pageY
					});
					}
					}else{
					$('#admin_dic_menu_root').menu('show', {
						left : e.pageX,
						top : e.pageY
					});
					}
				
			}
            ">
        <thead>
            <tr>
			    <th data-options="field:'id',hidden:true" width="220"></th>
                <th data-options="field:'text'" width="220">字典名称</th>
                <th data-options="field:'text2'" width="100" align="right">字典编号</th>
                <th data-options="field:'text3'" width="150">状态</th>
                <th data-options="field:'text4'" width="150">字典排序</th>
            </tr>
        </thead>
    </table>
    
    </div>
<div id="tb-dicListtb">
			<div style="margin-bottom: 5px">
			<a href="#" class="easyui-linkbutton" iconCls="icon-redo" plain="true" onclick="admin_dic_expandFun()">展开</a> |
			<a href="#" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="admin_dic_collapseAll()">折叠</a> |
			<a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="admin_dic_reload()">刷新</a>
			</div>
  	
  	
  	<!-- 
			<div>
			<span>数据字典:</span>
			
           <input id="dicId" class="easyui-combobox" data-options="url:'${ctx}/dicController/getDicbyBack.action',editable:false,valueField:'id', textField:'text',panelHeight:'auto',multiple:false" style="width: 80px"/>
            <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="clearFun();">查询</a>
			</div>
		 -->	
			
           </div>
          

<!-- 根节点的 menu -->
<div id="admin_dic_menu_root" class="easyui-menu" style="width:120px;display: none;">
	<div onclick="admin_dic_appendFun();" data-options="iconCls:'icon-add'">增加</div>
</div>

<!-- 一级点的 menu -->
<div id="admin_dic_menu" class="easyui-menu" style="width:120px;display: none;">
	<div onclick="admin_dic_appendFun();" data-options="iconCls:'icon-add'">添加</div>
	<div onclick="admin_dic_editFun();" data-options="iconCls:'icon-edit'">修改</div>
	<!-- 后期会屏蔽这个菜单 -->
	 <div onclick="admin_dic_deleteFun();" data-options="iconCls:'icon-remove'">删除</div>
	
</div>

<!-- 二级点的 menu -->
<div id="admin_dic_menu_two" class="easyui-menu" style="width:120px;display: none;">
<div onclick="admin_dic_appendFun();" data-options="iconCls:'icon-add'">添加</div>
	<div onclick="admin_dic_editFun();" data-options="iconCls:'icon-edit'">修改</div>
	
	
	<div onclick="admin_dic_deleteFun();" data-options="iconCls:'icon-remove'">删除</div> 
</div>

			
</body>

