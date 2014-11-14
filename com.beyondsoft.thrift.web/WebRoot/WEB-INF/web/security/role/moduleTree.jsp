<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
  <script type="text/javascript">
				
			$(function(){
				var nodes ;
				$('#module').tree({
					    parentField : 'pid',//不能省略如果省略就没有父子关系了，所有的树的结点刚平行
						animate:true,
						checkbox:true,
						cascadeCheck:false,
						onlyLeafCheck:false,//仅仅显示叶子节点
						onContextMenu: function(e,node){
							//禁止浏览器的菜单打开
							e.preventDefault();
							$(this).tree('select',node.target);
							//node.pid为null时，表示根节点，不让修改根
							
						}
						  
				});
				
			});

	</script>
	
  		<ul id="module" style="width:300px;"></ul>
