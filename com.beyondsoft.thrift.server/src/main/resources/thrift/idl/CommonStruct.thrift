namespace java com.beyondsoft.thrift.rpc

/*通用模块*/

struct PageFormRPC {
        1: string id;
        2: string name;
        3: string uuid;
        4: string value1;
        5: string value2;
        6: string value3;
        7: string value4; 
        8: string value5; 
		9: string value6; 
		10:string value7; 
		11:string value8;
		12:string value9;
		13:string value10;
		14:string value11;
		15:string value12;
		16:string value13;
		17:string value14;
		18:string value15;
		19:string value16;
		20:string value17;
		21:string value18;
		22:string value19;
		23:string value20;
		24:i32 page;
		25:i32 rows;
}

struct TreeDTORPC{
		1:string id ;
		2:string text ;
		3:string text2;
		4:string text3 ;
		5:string text4 ;
		6:string text5 ;
		7:string text6 ;
		8:string iconCls ;
		9:string pid ;
		10:string state ;
		11:i32 checked ;
		12:map<string, string> attributes;
		13:string url;
}

struct DataGridRPC
{
	1:i64 total;
	2:list<PageFormRPC> rows;
}
