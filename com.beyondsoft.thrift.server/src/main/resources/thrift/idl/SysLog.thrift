namespace java com.beyondsoft.thrift.rpc.syslog
/*日志表*/
struct SysLog {
        1: string id;
        2: string createTime;
        3: string sysuserId;
        4: i32 loglevel;
        5: string message;
        6: string note;
}



/*日志服务*/
service SysLogService {
	list<SysLog> findLogList(1:map<string,string> pageMap);
	i64 findLogCnt();
	i32 insertLog(1:SysLog log);
	string getUserId(1:string userName);
	
}
