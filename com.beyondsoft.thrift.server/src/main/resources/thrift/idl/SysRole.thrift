namespace java com.beyondsoft.thrift.rpc.sysrole
include "CommonStruct.thrift"


/*角色表*/
struct SysRole {
        1: string id;
        2: string name;
        3: string createTime;
        4: string ids;        //页面传过来的中间变量(用户id)
}


/*角色模块关联表*/
struct SysRolePermission {
        1: string id;
        2: string role_id;
        3: string permission;
}

/*角色用户关联表*/
struct SysUserRole {
        1: string user_id;
        2: string role_id;
}



/*角色服务*/
service SysRoleService {
	CommonStruct.DataGridRPC findRole(1:CommonStruct.PageFormRPC pageForm);
	i32  addRole(1:SysRole role);
	i32  delRole(1:SysRole role);
	i32  editRole(1:SysRole role);
	
	i32 ifDelete(1:SysRole role);                      														    //功能: 超级管理员不允许删除
	CommonStruct.DataGridRPC assignedUserDatagrid(1:SysRole role,2:CommonStruct.PageFormRPC pageForm);          //功能: 角色下已分配用户列表
	CommonStruct.DataGridRPC noAssignedUserDatagrid(1:SysRole role,2:CommonStruct.PageFormRPC pageForm);        //功能: 角色下未分配用户列表
	
	bool removeUser(1:SysRole role);                  																					//功能:移除该角色下的用户
	bool addUser(1:SysRole role);                 						    //功能:添加用户到该角色下
	list<CommonStruct.TreeDTORPC> menuListSync();            										            //功能: 同步树角色面版菜单功能树展示(所有可用菜单全部显示)
	
	string selectTreeNode(1:SysRole role);            														    //功能: 回显勾选的叶子节点
	bool insertDeleteRoleModule(1:SysRole role);         														//功能: 插入角色对应的菜单模块， 思路：先删除角色下的模块，再添加
	
}
