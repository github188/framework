<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<script type="text/javascript">
	$(function() {
		$('#admin_yhglEditRole_combogrid').combogrid({
			multiple : true,
			nowrap : false,
			required:true,
			url : '${ctx}/userController/combogrid.action',
			panelWidth : 450,
			panelHeight : 200,
			idField : 'id',
			textField : 'name',
			pagination : true,
			fitColumns : true,
			rownumbers : true,
			mode : 'remote',
			delay : 500,
			pageSize : 5,
			pageList : [ 5, 10 ],
			columns : [ [ {
				field : 'id',
				title : '编号',
				width : 150,
				hidden : true
			}, {
				field : 'name',
				title : '角色名称',
				width : 150
			} ] ]
		});
	});
</script>
<div align="center">
	<form id="admin_yhglEditRole_editForm" method="post">
		<input name="ids" readonly="readonly" type="hidden" />
		<table class="tableForm">
			<tr>
				<th>所属角色</th>
				<td><input id="admin_yhglEditRole_combogrid" name="roleIds" style="width: 365px;" /></td>
				<td><img src="${ctx}/style/images/extjs_icons/cut_red.png" onclick="$('#admin_yhglEditRole_combogrid').combogrid('clear');" />
				</td>
			</tr>
		</table>
	</form>
</div>