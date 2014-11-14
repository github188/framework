<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title></title>
		<meta http-equiv=Content-Type content=text/html;charset=utf-8>
	</head>
	<body>
		<ul id="<%=request.getParameter("typeCode")%>"></ul>
		<script>
			$("#<%=request.getParameter("typeCode")%>").tree({
				lines : true,
				url:'${pageContext.request.contextPath}/menuController/getSonFunction.action?parentId=<%=request.getParameter("typeCode")%>',
				animate:true,
				onClick : function(node) {
					var url;
					if (node.attributes.url) {
						url = '${ctx}' + node.attributes.url;
					} else {
						url = '';
					}
					if (url.indexOf('druid') > -1) {  //要查看连接池监控页面
						layout_center_addTabFun({
							title : node.text,
							closable : true,
							iconCls : node.iconCls,
							
							content : '<iframe src="' + url + '" frameborder="0" style="border:0;width:100%;height:99%;"></iframe>'
						});
					} else {
						
						if(url!='')
							{
							if(url!=sy.bpName()+'#')
								{
								layout_center_addTabFun({
									title : node.text,    //tab的名称
									closable : true,      //是否显示关闭按钮
									iconCls : node.iconCls,//图标
									content : '<iframe src="' + url + '" frameborder="0" style="border:0;width:100%;height:99%;"></iframe>'
									//href : url              //加载链接
								});
								}
						
							}
					}
				}
			});
		</script>
	</body>
</html>