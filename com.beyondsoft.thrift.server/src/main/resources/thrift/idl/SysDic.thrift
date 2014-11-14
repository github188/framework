namespace java com.beyondsoft.thrift.rpc.sysdic
include "CommonStruct.thrift"
/*字典表*/
struct SysDic {
        1: string id;
        2: string dicCode;
        3: string dicName;
        5: string orderCode;
        4: string pid;
        6: string status;
        7: string dicParentName;
        
}

/*字典服务*/
service SysDicService {

	list<CommonStruct.TreeDTORPC> treegrid(1:string id);
	i32 save(1:SysDic dic);
	i32 edit(1:SysDic dic);
	i32 deleteDic(1:string id);
	i32 checkCode(1:string dicCode);

}
