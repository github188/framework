<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<div align="center">
	<form id="admin_zyglAdd_addForm" method="post">
	
	<input type="hidden" name="id" />
	
		<table id="daxiao">
			<tr>
				<td>上级组织名称</td>
				<td><input name="orgParentName" class="easyui-validatebox" data-options="required:true" readonly="readonly" />
				</td>
			</tr>
			
			<tr>
				<td>组织名称</td>
				<td colspan="3"><input name="name" class="easyui-validatebox" data-options="required:true" />
				</td>
			</tr>
		</table>
	</form>
</div>