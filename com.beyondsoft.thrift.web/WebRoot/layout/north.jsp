<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" charset="utf-8">

$(function(){
	
	//setInterval("getTime();",1000);
})
//取得系统当前时间
/*
function getTime(){
	var myDate = new Date();
	var date = myDate.toLocaleDateString();
	var hours = myDate.getHours();
	var minutes = myDate.getMinutes();
	var seconds = myDate.getSeconds();
	$("#showDate").html(date+" "+hours+":"+minutes+":"+seconds);
}
*/
	function logoutFun() {
		
		$.messager.confirm('友情提示','确定退出系统?',function(r){
		    if (r){
		    	window.location = 'logout.action';
		    }
		});
		
		
	}
	function userInfoFun() {
		$('<div/>')
				.dialog(
						{
							href : '${pageContext.request.contextPath}/userController/userInfo.action',
							width : 490,
							height : 285,
							modal : true,
							title : '用户信息',
							buttons : [ {
								text : '修改密码',
								iconCls : 'icon-edit',
								handler : function() {
									var d = $(this).closest('.window-body');
									$('#user_userInfo_form')
											.form(
													'submit',
													{
														url : '${pageContext.request.contextPath}/userController/modifyCurrentUserPwd.action',
														success : function(
																result) {
															try {
																var r = $.parseJSON(result);
																if (r.success) {
																	d.dialog('destroy');
																}
																$.messager.show({
																			title : '提示',
																			msg : r.msg
																		});
															} catch (e) {
																$.messager.alert('提示',result);
															}
														}
													});
								}
							} ],
							onClose : function() {
								$(this).dialog('destroy');
							},
							onLoad : function() {
							}
						});
	}
</script>


<div class="homepage-top-banner">
	<div class="homepage-top-logo"></div>
	<div style="position: absolute; right: 0px; bottom: 0px;">
<!-- 当前时间：<div id="showDate"></div> -->

[<strong> ${userName}</strong>，欢迎你！]
	<!--<a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_pfMenu',iconCls:'icon-ok'">更换皮肤</a>--> 
	
	<!-- <a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_kzmbMenu',iconCls:'icon-help'">控制面板</a> --> 
	
	<a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_zxMenu',iconCls:'icon-back'">注销</a>
</div>
<div id="layout_north_pfMenu" style="width: 120px; display: none;">
	<div onclick="changeTheme('default');">default</div>
	<div onclick="changeTheme('gray');">gray</div>
	
</div>
<div id="layout_north_kzmbMenu" style="width: 100px; display: none;">
	<div onclick="userInfoFun();">个人信息</div>
	<div class="menu-sep"></div>
	<div>
		<span>更换主题</span>
		<div style="width: 120px;">
			<div onclick="changeTheme('default');">default</div>
			<div onclick="changeTheme('gray');">gray</div>
			
		</div>
	</div>
</div>
<div id="layout_north_zxMenu" style="width: 100px; display: none;">

	<div onclick="logoutFun();">退出系统</div>
</div>
</div>
