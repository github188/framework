<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<script type="text/javascript">
	$(function() {
		/*同步树  begin*/
		$('#layout_west_tree').tree({
			url : '${ctx}/userController/allTreeNodeSync.action',
			parentField : 'pid',//不能省略如果省略就没有父子关系了，所有的树的结点刚平行
			lines : false , 
			animate:true,
			onContextMenu: function(e,node){
				//禁止浏览器的菜单打开
				e.preventDefault();
				$(this).tree('select',node.target);
		}
			,
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
								content : '<iframe src="' + url + '" frameborder="0" style="border:0;width:100%;height:99%;"></iframe>'         //加载链接
							});
							}
					
						}
					
					
					
				
				}
			}
			
			
		});
		
		/*同步树  end*/
		
		/*异步树  begin*/
		/*
		$('#layout_west_tree').tree({
						//发送异步ajax请求, 还会携带一个id的参数 
						url:'${ctx}/menuController/allTreeNodeAsync.action',
						onContextMenu: function(e,node){
							//禁止浏览器的菜单打开
							e.preventDefault();
							$(this).tree('select',node.target);
					}
					
					
					
					
					,
					onClick : function(node) {
						var url;
						if (node.attributes.url) {
							url = '${ctx}' + node.attributes.url;
						} else {
							url = '';
						}
						if (url.indexOf('druidController') > -1) {//要查看连接池监控页面
							layout_center_addTabFun({
								title : node.text,
								closable : true,
								iconCls : node.iconCls,
								content : '<iframe src="' + url + '" frameborder="0" style="border:0;width:100%;height:99%;"></iframe>'
							});
						} else {
							if(url!='')
								{
								layout_center_addTabFun({
									title : node.text,
									closable : true,
									iconCls : node.iconCls,
									href : url
								});
								}
							
						}
					}
						//animate:false,
						//lines:false
						
				});
				*/
		/*异步树  end*/
	});
</script>
<div class="easyui-accordion" data-options="fit:true,border:false">
	<div title="树形系统菜单" data-options="isonCls:'icon-save',tools : [ {
				iconCls : 'icon-reload',
				handler : function() {
					$('#layout_west_tree').tree('reload');
				}
			}, {
				iconCls : 'icon-redo',
				handler : function() {
					var node = $('#layout_west_tree').tree('getSelected');
					if (node) {
						$('#layout_west_tree').tree('expandAll', node.target);
					} else {
						$('#layout_west_tree').tree('expandAll');
					}
				}
			}, {
				iconCls : 'icon-undo',
				handler : function() {
					var node = $('#layout_west_tree').tree('getSelected');
					if (node) {
						$('#layout_west_tree').tree('collapseAll', node.target);
					} else {
						$('#layout_west_tree').tree('collapseAll');
					}
				}
			} ]">
		<ul id="layout_west_tree"></ul>
	</div>
	

</div>