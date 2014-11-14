<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common.jsp"%>
<script type="text/javascript">
	$(function() {
		$('#admin_zygl_treegrid').treegrid({
			url : '${ctx}/orgController/treegrid.action',
			idField : 'id',
			treeField : 'text',
			parentField : 'pid',
			fit : true,
			fitColumns : true,
			border : false,
			frozenColumns : [ [ {
				title : '编号',
				field : 'id',
				width : 150,
				hidden : true
			}, {
				field : 'text',
				title : '组织名称',
				width : 200
			} ] ],
			
			toolbar : [ {
				text : '展开',
				iconCls : 'icon-redo',
				handler : function() {
					var node = $('#admin_zygl_treegrid').treegrid('getSelected');
					if (node) {
						$('#admin_zygl_treegrid').treegrid('expandAll', node.cid);
					} else {
						$('#admin_zygl_treegrid').treegrid('expandAll');
					}
				}
			}, '-', {
				text : '折叠',
				iconCls : 'icon-undo',
				handler : function() {
					var node = $('#admin_zygl_treegrid').treegrid('getSelected');
					if (node) {
						$('#admin_zygl_treegrid').treegrid('collapseAll', node.cid);
					} else {
						$('#admin_zygl_treegrid').treegrid('collapseAll');
					}
				}
			}, '-', {
				text : '刷新',
				iconCls : 'icon-reload',
				handler : function() {
					$('#admin_zygl_treegrid').treegrid('reload');
				}
			} ],
			onContextMenu : function(e, row) {
				e.preventDefault();
				$(this).treegrid('unselectAll');
				$(this).treegrid('select', row.id);
				
				if(row.id!=1)
					{
					$('#admin_zygl_menu').menu('show', {
						left : e.pageX,
						top : e.pageY
					});
					}
				else
					{
					$('#admin_zygl_menu_root').menu('show', {
						left : e.pageX,
						top : e.pageY
					});
					
					}
				
			}
		});
		
	});

	function admin_zygl_appendFun() {
		
		var node = $('#admin_zygl_treegrid').treegrid('getSelected');
		var id=node.id;
		var text=node.text;
		
		$('<div/>').dialog({
			href : '${ctx}/orgController/toAddPage.action',
			width : 300,
			height : 200,
			modal : true,
			title : '添加组织机构',
			buttons : [ {
				text : '增加',
				iconCls : 'icon-add',
				handler : function() {
					var d = $(this).closest('.window-body');
					$('#admin_zyglAdd_addForm').form('submit', {
						url : '${ctx}/orgController/add.action',
						success : function(result) {
							try {
								var r = $.parseJSON(result);
								
								if (r.success) {
									/*
									$('#admin_zygl_treegrid').treegrid('append', {
										parent : r.obj.pid,
										data : [ r.obj ]
									});
									*/
									
									$('#admin_zygl_treegrid').treegrid('reload');
									
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
				$('#admin_zyglAdd_addForm').form('load', {orgParentName:node.text,id:node.id});
			}
		});
	}
	function admin_zygl_editFun(id) {
		if (id != undefined) {
			$('#admin_zygl_treegrid').treegrid('select', id);
		}
		var node = $('#admin_zygl_treegrid').treegrid('getSelected');
		$('<div/>').dialog({
			href : '${ctx}/orgController/toEditPage.action',
			width : 300,
			height : 200,
			modal : true,
			title : '编辑组织机构',
			buttons : [ {
				text : '编辑',
				iconCls : 'icon-edit',
				handler : function() {
					var d = $(this).closest('.window-body');
					$('#admin_zyglEdit_editForm').form('submit', {
						url : '${ctx}/orgController/edit.action',
						success : function(result) {
							try {
								var r = $.parseJSON(result);
								if (r.success) {
									$('#admin_zygl_treegrid').treegrid('reload');
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
				$('#admin_zyglEdit_editForm').form('load', {orgParentName:node.attributes.parentOrgName,id:node.id,name:node.text});
			}
		});
	}
	function admin_zygl_deleteFun(id) {
		if (id != undefined) {
			$('#admin_zygl_treegrid').treegrid('select', id);
		}
		var node = $('#admin_zygl_treegrid').treegrid('getSelected');
		if (node) {
			$.messager.confirm('询问', '您确定要删除【' + node.text + '】？', function(b) {
				if (b) {
					$.ajax({
						url : '${ctx}/orgController/delete.action',
						data : {
							id : node.id
						},
						cache : false,
						dataType : 'JSON',
						success : function(r) {
							if (r.success) {
								$('#admin_zygl_treegrid').treegrid('remove', r.obj.id);
								$.messager.show({
									msg : r.msg,
									title : '提示'
								});
							}else
							{
								$.messager.alert('提示框', '请先删除用户信息','warning',function(){});
								}
							
						}
					});
				}
			});
		}
	}
</script>
<table id="admin_zygl_treegrid"></table>

<div id="admin_zygl_menu" class="easyui-menu" style="width:120px;display: none;">
	<div onclick="admin_zygl_appendFun();" data-options="iconCls:'icon-add'">添加</div>
	<div onclick="admin_zygl_editFun();" data-options="iconCls:'icon-edit'">修改</div>
	<div onclick="admin_zygl_deleteFun();" data-options="iconCls:'icon-remove'">删除</div>
</div>

<!-- 根节点的 menu -->
<div id="admin_zygl_menu_root" class="easyui-menu" style="width:120px;display: none;">
	<div onclick="admin_zygl_appendFun();" data-options="iconCls:'icon-add'">增加</div>
</div>