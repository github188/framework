namespace java com.beyondsoft.thrift.rpc.sysuser
include "CommonStruct.thrift"
/*用户表*/
struct SysUser {
        1: string id;
        2: string create_time;
        3: string password;
        4: string status;
        5: string username;
        6: string email;
        7: string realname; 
        8: string phone; 
		9: string org_id; 
		10:map<string,string> msg;
}


/*组织表*/
struct SysOrganization {
        1: string id;
        2: string description;
        3: string name;
        4: string parent_id;
}

/*用户服务*/
service SysUserService {
	map<string,string> checkUserLogin(1:string username,2:string password);
	bool  addUser(1:SysUser user);
	bool  delUser(1:string ids);
	bool  editUser(1:SysUser user);
	CommonStruct.DataGridRPC findUser(1:CommonStruct.PageFormRPC pageForm,2:SysUser user);
	list<CommonStruct.TreeDTORPC> findModuleBySelf(1:SysUser user);
	SysUser findUserById(string id);
	
}

/*组织机构服务*/
service SysOrgService {
	bool  addSysOrg(1:SysOrganization sysOrg);
	bool  delSysOrg(1:SysOrganization sysorg);
	bool  editSysOrg(1:SysOrganization sysOrg);
}
