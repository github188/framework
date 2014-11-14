<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common.jsp"%>
  <script type="text/javascript">
				
			var flag ; //判断执行保存还是修改的方法
			$(function(){
				
				$('#t1').tree({
						//发送异步ajax请求, 还会携带一个id的参数 
						url:'${ctx}/moduleController/menuList.action' ,
						dnd:false ,
						animate:true,
						checkbox:false,
						cascadeCheck:false,
						lines:true,
						onDrop:function(target , source , point){
								var tar = $('#t1').tree('getNode' , target);
								$.ajax({
									type:'post',
									url:'${ctx}/moduleController/changeLevel.action' , 
									data:{
										targetId:tar.id , 
										sourceId:source.id , 
										point:point
									} , 
									dataType:'json' , 
									cache:false , 
									success:function(result){
										$.messager.show({
											title:'提示信息' ,
											msg:result.msg
										});
									}
								});
						} , 
						onContextMenu: function(e,node){
							
							//禁止浏览器的菜单打开
							e.preventDefault();
							$(this).tree('select',node.target);
							//node.pid为null时，表示根节点，不让修改根
							if(node.id!=1)
								{
								$('#mm').menu('show', {
									left: e.pageX,
									top: e.pageY
								});
								}else
								{
									$('#admin_menu_root').menu('show', {
										left : e.pageX,
										top : e.pageY
									});
									
									}
								
						},
						onSelect:function(node)//easyui-1.3.5不能用？
						{
							 if (node) {
				                    $('#t1').tree('expandAll', node.target);
				                }
				                else {
				                	$('#t1').tree('expandAll');
				                }
						}
				});
				
				//保存和修改按钮
				$('#savebtn').click(function(){
							if(flag == 'add'){
										//1 做前台更新   
										//	(1)获取所选中的节点,也就是父节点
										var node = $('#t1').tree('getSelected');
										//  (2)调用追加节点的方法
										$('#t1').tree('append' , {
											parent:node.target ,
											data:[{
												text: $('#myform').find('input[name=name]').val() ,
												attributes:{
													url:$('#myform').find('input[name=url]').val()
												}
											,iconCls:'icon-tree',
											sn:$('#myform').find('input[name=sn]').val()
											}]
										});
										
										//2 后台同步更新 
										$.ajax({
											type:'post' ,
											url:'${ctx}/moduleController/add.action' ,
											cache:false , 
											data:{
												parentId:node.id ,
												name:$('#myform').find('input[name=name]').val() ,
												url:$('#myform').find('input[name=url]').val(),
												priority:$('#myform').find('input[name=displayorder]').val(),
												sn:$('#myform').find('input[name=sn]').val()
											} ,
											dataType:'json' ,
											success:function(result){
												//刷新节点 
												var parent = $('#t1').tree('getParent' , node.target);
												if(parent!=null)//刷新除根节点之外的子节点
													{
													$('#t1').tree('reload',parent.target);
													}
												else            //刷新根节点
													{
													$('#t1').tree('reload',node.target);
													}
												
												
												$.messager.show({
													title:'提示信息',
													msg:result.msg
												});
											}
										});
										//3 关闭dialog
										$('#mydiv').dialog('close'); 
							} else {
										$.ajax({
											type:'post' ,
											url:'${ctx}/moduleController/edit.action' ,
											cache:false , 
											data:{
												id:$('#myform').find('input[name=id]').val() ,
												name:$('#myform').find('input[name=name]').val() ,
												url:$('#myform').find('input[name=url]').val(),
												priority:$('#myform').find('input[name=displayorder]').val(),
												iconCls:$('#myform').find('input[name=iconCls]').val(),
												sn:$('#myform').find('input[name=sn]').val()
											} ,
											dataType:'json' ,
											success:function(result){
												//刷新节点 (一定是选中节点的父级节点)
												var node = $('#t1').tree('getSelected');
												var parent = $('#t1').tree('getParent' ,node.target);
												
												if(parent!=null)//刷新除根节点之外的子节点
												{
												$('#t1').tree('reload',parent.target);
												}
											else            //刷新根节点
												{
												$('#t1').tree('reload',node.target);
												}
												
												//给出提示信息 
												$.messager.show({
													title:'提示信息',
													msg:result.msg
												});
											}
										});
										//3 关闭dialog
										$('#mydiv').dialog('close'); 
										
							}
				});
				
				//取消按钮
				$('#cancelbtn').click(function(){
						$('#mydiv').dialog('close'); 
				});
			});
			function append(){
					flag = 'add';
					$('#myform').form('clear');
					$('#mydiv').dialog('open');
			}
			
			function editor(){
					flag = 'edit';
					//清空表单数据 ,重新填充选中的节点里的id ,name , url属性
					$('#myform').form('clear');
					var node = $('#t1').tree('getSelected');
					$('#myform').form('load',{
						id:node.id ,
						name:node.text,
						url:node.attributes.url,
						displayorder:node.attributes.displayorder,
						iconCls:node.iconCls,
						sn:node.attributes.sn
					});
					//打开dialog
					$('#mydiv').dialog('open');
			}
			
			function removes(){
				$.messager.confirm('提示框', '确认删除？', function(r){
					if (r){
						//前台删除
						var node = $('#t1').tree('getSelected');
						
						
						//后台删除
						$.post('${ctx}/moduleController/delete.action' , {id:node.id} , function(result){
									//给出提示信息 
// 									var result =eval('(' + result + ')');
									
									if(result.success)
										{
										$('#t1').tree('remove' , node.target);
										}
										//
									$.messager.show({
										title:'提示信息',
										msg:result.msg
									});
						});
					}
				});
				
				
			}

	</script>
	
	
  		<ul id="t1" style="width:300px;"></ul>
  		<div id="mydiv" title="设置菜单树" class="easyui-dialog" style="width:300px;" closed=true >
  				<form id="myform" method="post">
  							<input type="hidden" name="id" value="" />
  							<table id="daxiao"> 
  								<tr>
  									<td>菜单名称:</td>
  									<td> <input class="easyui-validatebox" type="text" name="name"  data-options="required:true,validType:'length[1,50]'"></input></td>
  								</tr>
  								<tr>
  									<td>url:</td>
  									<td>
  									 <input class="easyui-validatebox" type="text" name="url"  data-options="required:true,validType:'length[1,50]'"></input>
  									</td>
  								</tr>
  								
  								<tr>
  									<td>排序号:</td>
  									<td>
  									 <input class="easyui-numberbox" type="text" name="displayorder"  data-options="required:true,validType:'length[1,50]'"></input>
  								</td>
  								</tr>
  								
  								<tr>
  									<td>模块标识名称:</td>
  									<td>
  									 <input class="easyui-validatebox" type="text" name="sn"  data-options="required:true,validType:'length[1,50]'"></input>
  								</td>
  								</tr>
  									 <input class="easyui-validatebox" type="hidden" name=iconCls  data-options="required:true,validType:'length[1,50]'"></input>
  								
  								
  								<tr align="center"  >
  									<td colspan="2"  >
  										<a id="savebtn" class="easyui-linkbutton">确定</a>
  										<a id="cancelbtn"  class="easyui-linkbutton">取消</a>
  									</td>
  								</tr>  								  								
  							</table>
  				</form>	
  		</div>
  		
  		
		<div id="mm" class="easyui-menu" style="width:150px;">
			<div onclick="append()" data-options="iconCls:'icon-add'">添加</div>
			<div onclick="editor()" data-options="iconCls:'icon-edit'">修改</div>
			<div onclick="removes()" data-options="iconCls:'icon-remove'">删除</div>
		</div>  		
		
		<!-- 根节点的 menu -->
<div id="admin_menu_root" class="easyui-menu" style="width:120px;display: none;">
	<div onclick="append();" data-options="iconCls:'icon-add'">增加</div>
</div>
