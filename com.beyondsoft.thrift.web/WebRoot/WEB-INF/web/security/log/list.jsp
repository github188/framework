<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common.jsp"%>
<script type="text/javascript">



	$(function() {

		$('#admin_yhgl_datagrid_1').datagrid({
			url : '${ctx}/logController/datagrid.action',
			fit : true,
			fitColumns : true,
			rownumbers:true ,
			border : false,
			pagination : true,
			idField : 'id',
			pageSize : 20,
			pageList : [  20, 30, 40, 50 ],
			//sortName : 'name',
			//sortOrder : 'asc',
			nowrap : false,
			frozenColumns : [ [ {
				field : 'id',
				title : '编号',
				width : 150,
				checkbox : false,
				hidden:true
			}, {
				field : 'message',
				title : '日志信息',
				width : 500
			} ] ],
			columns : [ [{
				field : 'note',
				title : '登陆ip',
				width : 80
			},
			 {
				field : 'createTime',
				title : '创建日志时间',
				width : 80
			}] ],
			toolbar : '#logListtb'
		});

	});
	 
	function logListsearch(a){
		$('#admin_yhgl_datagrid_1').datagrid('load',{loglevel:a});
	}
	function clearFun() {
		$('#loglevel').combobox('setValue', ['0']);
		$('#admin_yhgl_datagrid_1').datagrid('load', {loglevel:0});
	}

</script>
<div class="easyui-layout" data-options="fit : true,border : false">
	<div data-options="region:'center',border:false">
		<table id="admin_yhgl_datagrid_1"></table>
	</div>
</div>
<!--  
<div id="logListtb" style="padding: 3px; height: 25px">
<div name="searchColums" style="float: right; padding-right: 15px;">日志类型:
 <select name="loglevel" id="loglevel"
     class="easyui-combobox" 
     style="width: 140px;"
     data-options="
     		editable:false,
     		panelHeight:'auto',
     		valueField: 'id',
			textField: 'text',
			value:'0',
     		data: [{
						id: '0',
						text: '--选择日志类型--'
					},{
						id: '1',
						text: '登陆'
					},{
						id: '2',
						text: '退出'
					},{
						id: '3',
						text: '插入'
					},{
						id: '4',
						text: '删除'
					},{
						id: '5',
						text: '更新'
					},{
						id: '6',
						text: '上传'
					},{
						id: '7',
						text: '其他'
					}],
					onSelect:function(rec){
					logListsearch(rec.id);
					}
     " 
     >
</select> 
 <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="clearFun();">查询全部</a></div>
</div>
-->