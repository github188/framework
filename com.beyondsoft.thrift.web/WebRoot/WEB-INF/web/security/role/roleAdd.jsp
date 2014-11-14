<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<div class="easyui-panel" style="width:700px;" data-options="border:false">
	    <form id="ff-yhglAdd_addForm" method="post">
	    	<table id="daxiao">
	    	
	    	<tr>
	    			<td width="100px">登录名称:</td>
	    			<td width="200px"><input class="easyui-validatebox" type="text" name="username"   data-options="precision:2,required:true,validType:'length[1,25]'"></input></td>
	    			<td width="100px">真实名称:</td>
	    			<td width="200px"><input class="easyui-validatebox" type="text" name="realname"   data-options="precision:2,required:true,validType:'length[1,25]'"></input></td>
	    		</tr>
	    		
	    		<tr>
	    			<td width="100px">邮箱地址:</td>
	    			<td width="200px"><input class="easyui-validatebox" type="text" name="email" data-options="required:true,validType:'length[1,25]'"></input></td>
	    			<td width="100px">电话:</td>
	    			<td width="200px"><input class="easyui-validatebox" type="text" name="phone" data-options="required:true,validType:'length[1,25]'"></input></td>
	    		</tr>
	    		
	    		<tr>
	    			<td width="100px">密码:</td>
	    			<td width="200px"><input class="easyui-validatebox" type="password" name="password" data-options="required:true,validType:'length[1,25]'"></input></td>
	    			<td width="100px">用户状态:</td>
	    			<td width="200px">
	    			<input id="status" name="status"
								class="easyui-combobox" style="width: 50px"
								data-options="panelHeight:'auto',editable:false,required:false,
									valueField: 'id',
									textField: 'text',
									data: [{
										id: 'enabled',
										text: '启用',
										selected:true
									},{
										id: 'disabled',
										text: '禁用'
									}
									]" />	
	    			</td>
	    		</tr>
	    	
	    	    <tr>
	    			<td width="100px"></td>
	    			<td width="200px"></td>
	    			<td width="100px">组织:</td>
	    			
	    			<td width="200px"><input id="admin_zyglAdd_pid" name="orgId" class="easyui-combotree" data-options="url:'${ctx}/orgController/treegrid.action',parentField : 'pid',lines : true" style="width: 150px;" /><img src="${ctx}/style/images/extjs_icons/cut_red.png" onclick="$('#admin_zyglAdd_pid').combotree('clear');" />
				</td>
				
	    		</tr>
	    	
	    	
	    	</table>
	    </form>
	</div>