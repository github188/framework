namespace java com.beyondsoft.thrift.rpc.sysmodule
include "CommonStruct.thrift"

/*模块表*/
struct SysModule {
        1: string id;
        2: string description;
        3: string name;
        4: string priority;
        5: string url;
        6: string parentId;
        7: string sn; 
        8: string iconCls; 
}



/*模块服务*/
service SysModuleService {
	i32  addModule(1:SysModule m);
	i32  delModule(1:SysModule m);
	i32  editModule(1:SysModule m);
	list<CommonStruct.TreeDTORPC>  getModule(1:string id);
}
