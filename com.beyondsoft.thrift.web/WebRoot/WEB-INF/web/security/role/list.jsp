<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
	<%@ include file="/common.jsp"%>
<!-- 说明：此处是在tab子页面里，默认对Body不识别所以不能用 <body class="easyui-layout">方式渲染  
要用<div id="userDiv" class="easyui-layout" data-options="fit:true" >这个方式 -->
<script type="text/javascript">
	//刷新用户和角色
	function admin_freshUserDiv_freshModule_Fun(id) {
		var loading = false;//防止树勾选初始化时，激活onCheck事件
		/*刷新已分配用户列表  begin*/
		$('#admin_assignedUser_datagrid').datagrid({
			url : '${ctx}/roleController/assignedUserDatagrid.action',
			queryParams : {
				id : id
			}
		});
		/*刷新已分配用户列表  end*/

		/*刷新未分配用户列表  begin*/
		$('#admin_noAssignedUser_datagrid').datagrid({
			url : '${ctx}/roleController/noAssignedUserDatagrid.action',
			queryParams : {
				id : id
			}
		});
		/*刷新未分配用户列表  end*/

		/*刷新角色分配的模块菜单  begin*/
		$('#module')
				.tree(
						{
							url : '${ctx}/roleController/menuListSync.action',

							onLoadSuccess : function(node, data) {

								//勾选角色对应的菜单项
								$
										.ajax({
											url : '${ctx}/roleController/selectTreeNode.action',//请求选 择的角色对应的菜单，返回树的id
											data : {
												id : id
											},
											dataType : 'json',
											success : function(result) {

												if (result.success) {
													var flag;//判断是否有角色下分配的模块，如果没有则为false
													var moduleIds = new Array();//js创建数组对象
													var ids = result.obj;
													if (ids != '') {
														moduleIds = ids
																.split(',');
														for ( var i = 0; i < moduleIds.length; i++) {
															var selNode = $(
																	'#module')
																	.tree(
																			'find',
																			moduleIds[i]);
															$('#module')
																	.tree(
																			'check',
																			selNode.target);
														}
													}
													loading = true;

												}

											}
										});
							},
							onCheck : function(node, checked) {

								if (loading) {
									var moduleIds = new Array();//将选中的模块节点加入数组中
									var nodes = $('#module').tree('getChecked',
											[ 'checked', 'indeterminate' ]);
									for ( var i = 0; i < nodes.length; i++) {
										moduleIds.push(nodes[i].id);
									}

									$
											.ajax({
												url : '${ctx}/roleController/insertDeleteRoleModule.action',//请求选 择的角色对应的菜单，返回树的id
												data : {
													id : id,
													ids : moduleIds.join(',')
												},
												dataType : 'json',
												success : function(result) {

												}
											});
								}

							}

						});
		/*刷新角色分配的模块菜单  end*/

	}

	$(function() {

		var editing; //判断用户是否处于编辑状态 
		var flag; //判断新增和修改方法
		$('#admin_role_datagrid')
				.datagrid(
						{
							singleSelect : true,
							fit : true,
							idField : 'id',
							fitColumns : true,
							url : '${ctx}/roleController/datagrid.action',
							rownumbers : true,
							frozenColumns : [ [ {
								field : 'ck',
								checkbox : true
							} ] ],
							columns : [ [
									{
										field : 'name',
										title : '角色名称',
										align : 'center',
										editor : {
											type : 'validatebox',
											options : {
												required : true,
												validType : 'length[1,25]',
												missingMessage : '角色名称必填!'
											}
										}
									},
									/*
									{
										field:'userName' ,
										title:'已分配用户名称' ,
										align:'center' 
									
									},*/
									{
										field : 'action',
										title : '管理用户',
										width : 100,
										formatter : function(value, row, index) {

											if (row.id == '0') {
												return '系统用户';
											} else {
												return formatString(
														'<img src="{0}"/>&nbsp;',
														'${ctx}/style/images/extjs_icons/group/group_add.png');
											}
										}
									},
									{
										field : 'action1',
										title : '分配权限',
										width : 100,
										formatter : function(value, row, index) {

											if (row.id == '0') {
												return '系统用户';
											} else {
												return formatString(
														'<img src="{0}"/>&nbsp;',
														'${ctx}/style/images/extjs_icons/medal_bronze_add.png');
											}
										}
									} ] ],
							pagination : true,
							pageSize : 10,
							pageList : [ 10, 20, 30, 40, 50 ],
							toolbar : [
									{
										text : '新增',
										iconCls : 'icon-add',
										handler : function() {
											if (editing == undefined) {

												flag = 'add';
												//1 先取消所有的选中状态
												$('#admin_role_datagrid')
														.datagrid('unselectAll');
												//2追加一行
												$('#admin_role_datagrid')
														.datagrid(
																'appendRow',
																{
																	description : ''
																});
												//3获取当前页的行号
												editing = $(
														'#admin_role_datagrid')
														.datagrid('getRows').length - 1;
												//4开启编辑状态
												$('#admin_role_datagrid')
														.datagrid('beginEdit',
																editing);
												$('#admin_role_datagrid')
														.datagrid(
																'highlightRow',
																0);//高亮当前行
											}
										}
									},
									{
										text : '修改',
										iconCls : 'icon-edit',
										handler : function() {
											var arr = $('#admin_role_datagrid')
													.datagrid('getSelections');
											if (arr.length != 1) {
												$.messager.show({
													title : '提示信息',
													msg : '只能选择一条记录进行修改!'
												});
											} else {
												if (editing == undefined) {
													flag = 'edit';

													//根据行记录对象获取该行的索引位置
													editing = $(
															'#admin_role_datagrid')
															.datagrid(
																	'getRowIndex',
																	arr[0]);
													var row = $(
															'#admin_role_datagrid')
															.datagrid('getRows');
													//开启编辑状态(超级管理员默认不让编辑)
													if (row[editing].id != '1') {
														$(
																'#admin_role_datagrid')
																.datagrid(
																		'beginEdit',
																		editing);

													} else {
														$.messager
																.show({
																	title : '提示框',
																	msg : '超级管理员默认不允许编辑',
																	showType : 'slide'
																});
														editing = undefined;
														$(
																'#admin_role_datagrid')
																.datagrid(
																		'uncheckAll')
																.datagrid(
																		'unselectAll')
																.datagrid(
																		'clearSelections');
													}

												} else {
													$.messager.show({
														title : '提示框',
														msg : '有记录未完成编辑!',
														showType : 'slide'
													});
												}
											}

										}
									},
									{
										text : '保存',
										iconCls : 'icon-save',
										handler : function() {
											//保存之前进行数据的校验 , 然后结束编辑
											if ($('#admin_role_datagrid')
													.datagrid('validateRow',
															editing)) {
												$('#admin_role_datagrid')
														.datagrid('endEdit',
																editing);
												editing = undefined;

											}
										}
									},
									{
										text : '删除',
										iconCls : 'icon-remove',
										handler : function() {
											var arr = $('#admin_role_datagrid')
													.datagrid('getSelections');
											if (arr.length <= 0) {
												$.messager.show({
													title : '提示信息',
													msg : '请选择进行删除操作!'
												});
											} else {
												$.messager
														.confirm(
																'提示信息',
																'确认删除?',
																function(r) {
																	if (r) {
																		var ids = '';
																		var name = '';//不能删除promotionCode为1的数据
																		for ( var i = 0; i < arr.length; i++) {
																			ids += arr[i].id
																					+ ',';
																			name += arr[i].name
																					+ ',';
																		}
																		ids= ids
																				.substring(
																						0,
																						ids.length - 1);
																		name = name
																				.substring(
																						0,
																						name.length - 1);
																		$.post(
																						'${ctx}/roleController/ifDelete.action',
																						{
																							id : ids
																						},
																						function(
																								resultOne) {

																							
																							
																							if (resultOne.success) {
																								if(resultOne.data.not=='not')
																									{
																									$.messager
																									.show({
																										title : '提示信息',
																										msg : '请选择取消或进行保存操作！'
																									});
																									}
																								else
																									{
																									$
																									.post(
																											'${ctx}/roleController/delete.action',
																											{
																												id : ids
																											},
																											function(
																													result) {
																												if (result.success) {
																													$(
																															'#admin_role_datagrid')
																															.datagrid(
																																	'reload');
																													$(
																															'#admin_role_datagrid')
																															.datagrid(
																																	'uncheckAll')
																															.datagrid(
																																	'unselectAll')
																															.datagrid(
																																	'clearSelections');
																													$.messager
																															.show({
																																title : '提示信息',
																																msg : result.msg
																															});
																													$(
																															'#admin_noAssignedUser_datagrid')
																															.datagrid({url : '${ctx}/roleController/noAssignedUserDatagrid.action',
																																queryParams : {
																																	id : 999
																																}});
																													
																													$("#aa").panel('refresh');
																													
																												} else {
																													$.messager
																															.show({
																																title : '提示信息',
																																msg : result.msg
																															});
																												}

																											},
																											'json');
																									}

																								

																							} else {
																								$.messager
																										.show({
																											title : '提示信息',
																											msg : '超级管理员不允许删除。'
																										});
																								$(
																										'#admin_role_datagrid')
																										.datagrid(
																												'uncheckAll')
																										.datagrid(
																												'unselectAll')
																										.datagrid(
																												'clearSelections');
																							}
																						},
																						'json');

																	} else {
																		$(
																				'#admin_role_datagrid')
																				.datagrid(
																						'uncheckAll')
																				.datagrid(
																						'unselectAll')
																				.datagrid(
																						'clearSelections');
																		return;
																	}
																});
											}
										}
									}

									/*,{
										text:'刷新',
										iconCls:'icon-reload' , 
										handler:function(){
											$('#admin_role_datagrid').datagrid('reload');
											$('#admin_role_datagrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
										}
									}
									 */
									,
									{
										text : '取消',
										iconCls : 'icon-cancel',
										handler : function() {
											//回滚数据 
											$('#admin_role_datagrid').datagrid(
													'rejectChanges');
											editing = undefined;

											$('#admin_role_datagrid').datagrid(
													'uncheckAll').datagrid(
													'unselectAll').datagrid(
													'clearSelections');
										}
									} ],
							onAfterEdit : function(index, record) {
								$
										.post(
												flag == 'add' ? '${ctx}/roleController/save.action'
														: '${ctx}/roleController/edit.action',
												record,
												function(result) {

													if (result.success) {

														$.messager.show({
															title : '提示信息',
															msg : '操作成功。'
														});

													}
													editing = undefined;
													$('#admin_role_datagrid')
															.datagrid(
																	'uncheckAll')
															.datagrid(
																	'unselectAll')
															.datagrid(
																	'clearSelections');
													$('#admin_role_datagrid')
															.datagrid('reload');
													$('#admin_noAssignedUser_datagrid').datagrid({
														url : '${ctx}/roleController/noAssignedUserDatagrid.action',
														queryParams : {
															id : 999
														}});
													$('#admin_assignedUser_datagrid').datagrid({
														url : '${ctx}/roleController/assignedUserDatagrid.action',
														queryParams : {
															id : 999
														}
													});
											
													$("#aa").panel('refresh');

												}, 'json');
							},
							onSelect : function(rowIndex, rowData) {
								admin_freshUserDiv_freshModule_Fun(rowData.id);
							}

						});

		//已分配用户列表
		$('#admin_assignedUser_datagrid')
				.datagrid(
						{
							singleSelect : false,
							fit : true,
							idField : 'id',
							fitColumns : true,
							rownumbers : true,
							frozenColumns : [ [ {
								field : 'ck',
								checkbox : true
							} ] ],
							columns : [ [ {
								field : 'username',
								title : '账号',
								align : 'center'
							}, {
								field : 'realname',
								title : '姓名',
								align : 'center'
							}, {
								field : 'orgName',
								title : '组织名称',
								align : 'center'
							}, {
								field : 'status',
								title : '状态',
								align : 'center',
								formatter : formatAccountState
							} ] ],
							pagination : true,
							pageSize : 10,
							pageList : [ 10, 20, 30, 40, 50 ],
							toolbar : [ {
								text : '移除',
								iconCls : 'icon-remove',
								handler : function() {

									var roleRow = $('#admin_role_datagrid')
											.datagrid('getSelected');

									var rows = $('#admin_assignedUser_datagrid')
											.datagrid('getChecked');
									var ids = [];
									if (rows.length > 0) {
										$.messager
												.confirm(
														'确认',
														'您是否要从角色中移除用户？',
														function(r) {

															if (r) {
																for ( var i = 0; i < rows.length; i++) {
																	ids
																			.push(rows[i].id);
																}
																$
																		.ajax({
																			url : '${ctx}/roleController/removeUser.action',
																			data : {
																				ids : ids
																						.join(','),
																				id : roleRow.id
																			},
																			dataType : 'json',
																			success : function(
																					result) {
																				if (result.success) {
																					/*
																					 var rows = $('#admin_assignedUser_datagrid').datagrid("getSelections"); 
																					 var copyRows = [];
																					    for ( var j= 0; j < rows.length; j++) {
																					    copyRows.push(rows[j]);
																					    						}
																					 for(var i =0;i<copyRows.length;i++){   
																						  //1.前台移除行
																					        var index = $('#admin_assignedUser_datagrid').datagrid('getRowIndex',copyRows[i]);
																					        $('#admin_assignedUser_datagrid').datagrid('deleteRow',index); 
																					        
																					      //2.前台添加行
																					    	$('#admin_noAssignedUser_datagrid').datagrid('appendRow',
																									{
																										description : ''
																									});
																					    }
																					 
																					$('#admin_assignedUser_datagrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
																					
																					 */

																					//页面刷新已分配的用户
																					$(
																							'#admin_assignedUser_datagrid')
																							.datagrid(
																									'load');
																					$(
																							'#admin_assignedUser_datagrid')
																							.datagrid(
																									'uncheckAll')
																							.datagrid(
																									'unselectAll')
																							.datagrid(
																									'clearSelections');

																					//页面刷新未分配的用户
																					$(
																							'#admin_noAssignedUser_datagrid')
																							.datagrid(
																									'load');
																					$(
																							'#admin_noAssignedUser_datagrid')
																							.datagrid(
																									'uncheckAll')
																							.datagrid(
																									'unselectAll')
																							.datagrid(
																									'clearSelections');

																				}

																				$.messager
																						.show({
																							title : '提示',
																							msg : result.msg
																						});
																			}
																		});
															}

														});
									} else {
										$.messager.show({
											title : '提示',
											msg : '请勾选要移除的用户！'
										});
									}

								}
							} ]

						});

		//未分配用户列表
		$('#admin_noAssignedUser_datagrid')
				.datagrid(
						{
							singleSelect : false,
							fit : true,
							idField : 'id',
							fitColumns : true,
							rownumbers : true,
							frozenColumns : [ [ {
								field : 'ck',
								checkbox : true
							} ] ],
							columns : [ [ {
								field : 'username',
								title : '账号',
								align : 'center'
							}, {
								field : 'realname',
								title : '姓名',
								align : 'center'
							}, {
								field : 'orgName',
								title : '组织名称',
								align : 'center'
							}, {
								field : 'status',
								title : '状态',
								align : 'center',
								formatter : formatAccountState
							} ] ],
							pagination : true,
							pageSize : 10,
							pageList : [ 10, 20, 30, 40, 50 ],
							toolbar : [ {
								text : '添加',
								iconCls : 'icon-add',
								handler : function() {

									var roleRow = $('#admin_role_datagrid')
											.datagrid('getSelected');

									var rows = $(
											'#admin_noAssignedUser_datagrid')
											.datagrid('getChecked');
									var ids = [];
									if (rows.length > 0) {
										$.messager
												.confirm(
														'确认',
														'您是否要添加用户到该角色？',
														function(r) {
															if (r) {
																for ( var i = 0; i < rows.length; i++) {
																	ids
																			.push(rows[i].id);
																}
																$
																		.ajax({
																			url : '${ctx}/roleController/addUser.action',
																			data : {
																				ids : ids
																						.join(','),
																				id : roleRow.id
																			},
																			dataType : 'json',
																			success : function(
																					result) {
																				if (result.success) {
																					//页面刷新已分配的用户
																					$(
																							'#admin_assignedUser_datagrid')
																							.datagrid(
																									'load');
																					$(
																							'#admin_assignedUser_datagrid')
																							.datagrid(
																									'uncheckAll')
																							.datagrid(
																									'unselectAll')
																							.datagrid(
																									'clearSelections');

																					//页面刷新未分配的用户
																					$(
																							'#admin_noAssignedUser_datagrid')
																							.datagrid(
																									'load');
																					$(
																							'#admin_noAssignedUser_datagrid')
																							.datagrid(
																									'uncheckAll')
																							.datagrid(
																									'unselectAll')
																							.datagrid(
																									'clearSelections');
																				}
																				$.messager
																						.show({
																							title : '提示',
																							msg : result.msg
																						});
																			}
																		});
															}
														});
									} else {
										$.messager.show({
											title : '提示',
											msg : '请勾选要添加的用户！'
										});
									}

								}
							} ]

						});

	});
</script>

<div id="bodyDiv" class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'west',title:'角色列表',collapsible:false,border:false"style="width: 500px;">
		<div id="roleDiv" class="easyui-layout" data-options="fit:true,border:false">
			<div data-options="region:'north',collapsible:false,border:false">
				<!-- 跟据浏览器大小自动适应  角色列表的北部面板的大小  begin-->
				<div id="roleNorthDiv" class="easyui-layout" data-options="fit:true,border:false">
					<div data-options="region:'center',border:false"style="height: 280px;">
						<table id="admin_role_datagrid"></table>
					</div>
				</div>
				<!-- 跟据浏览器大小自动适应  角色列表的北部面板的大小  end-->
			</div>

			<div data-options="region:'center',collapsible:false,border:false,title:'权限树'">
				<!-- 跟据浏览器大小自动适应  权限树的中部面板的大小  begin-->
				<div id="roleCenterDiv" class="easyui-layout"data-options="fit:true,border:false">
					<div id="aa"data-options="region:'center',href:'${ctx}/roleController/toModule.action',border:false">
					</div>
				</div>
				<!-- 跟据浏览器大小自动适应  权限树的中部面板的大小  end-->
			</div>
		</div>
	</div>
	<div data-options="region:'center',title:'已分配用户列表',border:false">
		<div id="userDiv" class="easyui-layout" data-options="fit:true,border:false">
			<div data-options="region:'north',collapsible:false,border:false" >
				<!-- 跟据浏览器大小自动适应  已分配用户列表的北部面板的大小  begin-->
				<div id="userNorthDiv" class="easyui-layout" data-options="fit:true,border:false">
					<div data-options="region:'center',border:false" style="height: 280px;">
						<table id="admin_assignedUser_datagrid"></table>
					</div>
				</div>
				<!-- 跟据浏览器大小自动适应  已分配用户列表的北部面板的大小  end-->
			</div>
			<div data-options="region:'center',collapsible:false,border:false,title:'未分配用户列表'">
				<!-- 跟据浏览器大小自动适应  未分配用户列表的中部面板的大小  begin-->
				<div id="userCenterDiv" class="easyui-layout"data-options="fit:true,border:false">
					<div data-options="region:'center',border:false">
						<table id="admin_noAssignedUser_datagrid"></table>
					</div>
				</div>
				<!-- 跟据浏览器大小自动适应  未分配用户列表的中部面板的大小  end-->
			</div>
		</div>
	</div>