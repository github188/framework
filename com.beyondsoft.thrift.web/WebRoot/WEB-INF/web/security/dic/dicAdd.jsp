<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<div align="center">
	<form id="admin_dicAdd_addForm" method="post">
	
	<input type="hidden" name="id" />
	
		<table id="daxiao">
			<tr>
				<td>上级名称 : </td>
				<td><input name="dicParentName" class="easyui-validatebox" data-options="required:true" readonly="readonly" />
				</td>
			</tr>
			
			<tr>
				<td>字典编号 : </td>
				<td><input name="dicCode" class="easyui-validatebox" data-options="required:true" />
<!-- 				<td><input name="dicCode" class="easyui-validatebox" data-options="required:true,validType:'length[1,20]'" validType="remote['${ctx}/dicController/checkCode.action','dicCode']" invalidMessage = "该字典编号重复请重新输入" /> -->
			</tr>
			<tr>
				<td>字典名称 : </td>
				<td colspan="3"><input name="dicName" class="easyui-validatebox" data-options="required:true" />
				</td>
			</tr>
			<tr>
				<td>状态量 : </td>
				<td colspan="3"><input name="status" class="easyui-validatebox" data-options="required:true" />
				</td>
			</tr>
			<tr>
				<td> 排序号: </td>
				<td><input class="easyui-numberspinner" type="text" name="orderCode" value="0" data-options="required:true,min:0,max:100,editable:true" missingMessage="请输入1到100间的数字"></input>
				</td>
			</tr>
		</table>
	</form>
	
</div>
