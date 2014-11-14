<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common.jsp"%>
<script type="text/javascript">



	$(function() {

		$('#admin_yhgl_datagrid').datagrid({
			url : '${ctx}/userController/datagrid.action',
			fit : true,
			fitColumns : true,
			rownumbers:true ,
			border : false,
			idField : 'id',
			pagination : true,
			pageSize : 20,
			pageList : [  20, 30, 40, 50 ],
			//sortName : 'name',
			//sortOrder : 'asc',
			nowrap : false,
			frozenColumns : [ [ {
				field : 'id',
				title : '编号',
				width : 150,
				checkbox : true
			}, {
				field : 'username',
				title : '登录名称',
				width : 80
			} ] ],
			columns : [ [ {
				field : 'realname',
				title : '真实名字',
				width : 80
			}, {
				field : 'email',
				title : '邮箱地址',
				width : 150
			}, {
				field : 'phone',
				title : '电话',
				width : 150
			}, {
				field : 'orgName',
				title : '所在组织',
				width : 150
			}, {
				field : 'orgId',
				title : '所在组织id',
				width : 150,
				hidden:true
			}, {
				field : 'roleName',
				title : '角色',
				width : 150
			}, {
				field : 'roleIds',
				title : '角色id',
				width : 150,
				hidden:true
			}, {
				field : 'status',
				title : '账户状态',
				width : 150,
				formatter:formatAccountState
			}, {
				field : 'createTime',
				title : '创建时间',
				width : 150
			}, {
				field : 'action', 
				title : '编辑与锁定用户',
				width : 100,
				formatter : function(value, row, index) {
					
					if (row.id == '1') {
						return '<font color="red">系统用户</font>';
					} else {
						return formatString('<img onclick="admin_yhgl_editFun(\'{0}\');" title="编辑用户" src="{1}"/>&nbsp;<img onclick="admin_yhgl_modifyZT(\'{2}\');" title="锁定用户" src="{3}"/>', row.id, '${ctx}/style/images/extjs_icons/pencil.png', row.id, '${ctx}/style/images/extjs_icons/lock/lock_edit.png');
					}
				}
			} ] ],
				toolbar:'#tb-queryUser'
		});

	});

	function admin_yhgl_editFun(id) {
		$('#admin_yhgl_datagrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
		$('<div/>').dialog({
			href : '${ctx}/userController/toEditPage.action',
			width : 750,
			height : 300,
			modal : true,
			title : '编辑用户',
			buttons : [ {
				text : '编辑',
				iconCls : 'icon-edit',
				handler : function() {
					var d = $(this).closest('.window-body');
					$('#ff-yhglEdit_editForm').form('submit', {
						url : '${ctx}/userController/edit.action',
						success : function(result) {
							try {
								var r = $.parseJSON(result);
								
								if (r.success) {
									/*
									$('#admin_yhgl_datagrid').datagrid('updateRow', {
										index : $('#admin_yhgl_datagrid').datagrid('getRowIndex', id),
										row : r.obj
									});
									*/
									$('#admin_yhgl_datagrid').datagrid('load');
									$('#admin_yhgl_datagrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
									d.dialog('destroy');
								}
								$.messager.show({
									title : '提示',
									msg : r.msg
								});
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
				var index = $('#admin_yhgl_datagrid').datagrid('getRowIndex', id);
				var rows = $('#admin_yhgl_datagrid').datagrid('getRows');
				var o = rows[index];
				o.roleIds = stringToList(rows[index].roleIds);
				
				
				$('#ff-yhglEdit_editForm').form('load', o);
			}
		});
	}
	
	//增加
	function admin_yhgl_appendFun() {
		$('#admin_yhgl_datagrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
		$('<div/>').dialog({
			href : '${ctx}/userController/toAddPage.action',
			width : 750,
			height : 300,
			modal : true,
			title : '添加用户',
			buttons : [ {
				text : '增加',
				iconCls : 'icon-add',
				handler : function() {
					var d = $(this).closest('.window-body');
					$('#ff-yhglAdd_addForm').form('submit', {
						url : '${ctx}/userController/save.action',
						success : function(result) {
							try {
								var r = $.parseJSON(result);
								if (r.success) {
									/*
									$('#admin_yhgl_datagrid').datagrid('insertRow', {
										index : 0,
										row : r.obj
									});
									*/
									$('#admin_yhgl_datagrid').datagrid('load');
									$('#admin_yhgl_datagrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
									d.dialog('destroy');
									
								}
								$.messager.show({
									title : '提示',
									msg : r.msg
								});
							} catch (e) {
								$.messager.alert('提示', result);
							}
						}
					});
				}
			} ],
			onClose : function() {
				$(this).dialog('destroy');
			}
		});
	}
	//批量删除
	function admin_yhgl_removeFun() {
		var rows = $('#admin_yhgl_datagrid').datagrid('getChecked');
		var ids = [];
		if (rows.length > 0) {
			$.messager.confirm('确认', '您是否要删除当前选中的项目？', function(r) {
				if (r) {
					//var currentUserId = '${sessionInfo.userId}';/*当前登录用户的ID*/
				    var flag = false;
					for ( var i = 0; i < rows.length; i++) {
						
						ids.push(rows[i].id);
						
					}
					$.ajax({
						url : '${ctx}/userController/delete.action',
						data : {
							ids : ids.join(',')
						},
						dataType : 'json',
						success : function(result) {
							if (result.success) {
								$('#admin_yhgl_datagrid').datagrid('load');
								$('#admin_yhgl_datagrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
							}
							if (flag) {
								$.messager.show({
									title : '提示',
									msg : '不可以删除自己！'
								});
							} else {
								$.messager.show({
									title : '提示',
									msg : result.msg
								});
							}
						}
					});
				}
			});
		} else {
			$.messager.show({
				title : '提示',
				msg : '请勾选要删除的记录！'
			});
		}
	}
	function admin_yhgl_deleteFun(id) {
		$('#admin_yhgl_datagrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
		$('#admin_yhgl_datagrid').datagrid('checkRow', $('#admin_yhgl_datagrid').datagrid('getRowIndex', id));
		admin_yhgl_removeFun();
	}
	function admin_yhgl_modifyZT(id) {
		
		$.ajax({
			url : '${ctx}/userController/editState.action',
			data : {
				id : id
			},
			dataType : 'json',
			success : function(result) {
				if (result.success) {
					$('#admin_yhgl_datagrid').datagrid('load');
					$('#admin_yhgl_datagrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
					$.messager.show({
						title : '提示',
						msg : result.msg
					});
				}
				
			}
		});
		
		
	}
	
	//批量设置角色
	function admin_yhgl_modifyRoleFun() {
		var rows = $('#admin_yhgl_datagrid').datagrid('getChecked');
		var ids = [];
		if (rows.length > 0) {
			for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].id);
			}
			$('<div/>').dialog({
				href : '${ctx}/userController/toModifyRolePage.action',
				width : 500,
				height : 200,
				modal : true,
				title : '批量编辑用户角色',
				buttons : [ {
					text : '编辑',
					iconCls : 'icon-edit',
					handler : function() {
						var d = $(this).closest('.window-body');
						$('#admin_yhglEditRole_editForm').form('submit', {
							url : '${ctx}/userController/modifyRole.action',
							success : function(result) {
								try {
									var r = $.parseJSON(result);
									if (r.success) {
										$('#admin_yhgl_datagrid').datagrid('load');
										$('#admin_yhgl_datagrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
										d.dialog('destroy');
									}
									$.messager.show({
										title : '提示',
										msg : r.msg
									});
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
					$('#admin_yhglEditRole_editForm').form('load', {
						ids : ids
					});
				}
			});
		} else {
			$.messager.show({
				title : '提示',
				msg : '请勾选要编辑的记录！'
			});
		}
	}
	
	//重置密码
	function admin_yhgl_resetPasswordFun() {
	  var rows = $('#admin_yhgl_datagrid').datagrid('getChecked');
	  var ids = [];
	  if(rows.length>0){
	  
	     $.messager.confirm('确认','您是否要重置当前选中用户的密码？',function(r){
	    	 
	    	 if (r){
	    		 for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].id);
						
					}
	        $.ajax({
	           url:'${ctx}/userController/resetPassword.action',
	           data:{
	               ids:ids.join(','),
	           },
	           dataType:'json',
	           success:function(result){
	               if(result.success){
	                 $('#admin_yhgl_datagrid').datagrid('load');
	                 $('#admin_yhgl_datagrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
	                   $.messager.show({
	                    title : '提示',
	                    msg : result.msg
	                 });
	               }else{
	                 $.messager.show({
	                    title : '提示',
	                    msg : result.msg
	                 });
	              }
	           } 
	        });
			    }
	    	 
	    
	     });
	  }else{
	     $.messager.show({
	             title : '提示',
	             msg : '请勾选要重置密码的用户！'
	        });
	    }
	}
	
	function doSearch_expert(){
		$('#admin_yhgl_datagrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
		$('#admin_yhgl_datagrid').datagrid('load',{name:$("#name").val()});
	}
	function doSearch_all_expert() {
		$('#name').val('');
		$('#admin_yhgl_datagrid').datagrid('load', {});
		$('#admin_yhgl_datagrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
	}
	
</script>
<div class="easyui-layout" data-options="fit : true,border : false">
	<div data-options="region:'center',border:false">
		<table id="admin_yhgl_datagrid"></table>
	</div>
	<div id="tb-queryUser" style="padding: 5px; height: auto">
			<div style="margin-bottom: 5px">
				<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="admin_yhgl_appendFun();">添加</a>
<!-- 				<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="admin_yhgl_modifyRoleFun()">批量设置角色</a> -->
				<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="admin_yhgl_removeFun();">删除</a>
<!-- 				<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="admin_yhgl_resetPasswordFun()">重置密码</a> -->
			</div>
			<div>
				<table id="daxiao">
					<tr>
						<td>
							真实名称：
						</td>
						<td>
							<input class="easyui-validatebox" id="name" style="width: 120px">&nbsp;&nbsp;
						</td>
<!-- 					<td>创建时间</td> -->
<!-- 					<td><input name="createdatetimeStart" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />至<input name="createdatetimeEnd" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" /></td> -->
						<td>
							<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch_expert();">搜索</a>
						</td>
						<td>
							<a href="#" class="easyui-linkbutton" onclick="doSearch_all_expert();">显示全部</a>
						</td>
					</tr>
				</table>
			</div>
		</div>
</div>