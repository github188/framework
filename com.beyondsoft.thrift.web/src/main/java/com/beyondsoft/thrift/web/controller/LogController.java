package com.beyondsoft.thrift.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beyondsoft.thrift.rpc.syslog.SysLog;
import com.beyondsoft.thrift.rpc.syslog.SysLogService;
import com.beyondsoft.thrift.web.pageModel.DataGrid;
import com.beyondsoft.thrift.web.pageModel.PageForm;


@Controller
@RequestMapping("/logController")
public class LogController extends BaseController{

	
    private static final String LOGLIST = "security/log/list";// 日志列表页面
	
	
    
    /**
     * 功能: 跳转到日志列表页面
     * 
     * @return
     */
    @RequestMapping(value = "/toPage")
    public String toPage() {
		return LOGLIST;
    }
    
    /**
     * 功能: 日志列表页面数据
     * 
     * @return
     */
    @RequestMapping("/datagrid")
    @ResponseBody
    public DataGrid datagrid(PageForm pageform) {
    	DataGrid dg = new DataGrid();
//    	List<TLog> tlogList = Lists.newArrayList();
    	
    	Map<String, String> map = getPageRow(pageform);
        try {
			SysLogService.Client client = (SysLogService.Client) getClient("SysLogService");
			long total =client.findLogCnt();
			List<SysLog> list = client.findLogList(map);
			
			dg.setTotal(total);
			dg.setRows(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return dg;
    }

	
}
