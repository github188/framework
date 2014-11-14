<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<title></title>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="she,sypro,sshe,springmvc,hibernate">
<meta http-equiv="description" content="springmvc+hibernate+easyui示例项目">

</head>
<body class="easyui-layout">
	<div data-options="region:'north',href:'${ctx}/layout/north.jsp'" style="height: 30px;overflow: hidden;" class="logo"></div>
	
    <div data-options="region:'west',title:'功能导航',href:'${ctx}/layout/west.jsp'" style="width: 200px;overflow: hidden;"></div> 
	
	<div data-options="region:'center',title:'欢迎使用快快租车系统',href:'${ctx}/layout/center.jsp'" style="overflow: hidden;"></div>
	
	<%--
	<div data-options="region:'east',title:'日历',split:true,href:'${ctx}/layout/east.jsp'" style="width: 200px;overflow: hidden;"></div>
	<div data-options="region:'south',href:'${ctx}/layout/south.jsp'" style="height: 27px;overflow: hidden;"></div>
	--%>

	<%--<jsp:include page="user/login.jsp"></jsp:include>
	<jsp:include page="user/reg.jsp"></jsp:include>
--%>

</body>
</html>