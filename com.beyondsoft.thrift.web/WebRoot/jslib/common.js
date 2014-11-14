
/**
 * 包含easyui的扩展和常用的方法
 * 
 * @author zh
 */

//datagrid 清除√ 
function dataGridClearSelections(id)
 {
	 $(id).datagrid('clearSelections');
 }

var sy = $.extend({}, sy);/* 全局对象 */

$.fn.panel.defaults.onBeforeDestroy = function() {/* tab关闭时回收内存 */
	var frame = $('iframe', this);
	try {
		if (frame.length > 0) {
			frame[0].contentWindow.document.write('');
			frame[0].contentWindow.close();
			frame.remove();
			if ($.browser.msie) {
				CollectGarbage();
			}
		} else {
			$(this).find('.combo-f').each(function() {
				var panel = $(this).data().combo.panel;
				panel.panel('destroy');
			});
		}
	} catch (e) {
	}	
};

$.fn.panel.defaults.loadingMessage = '数据正在努力为您加载，请稍候....';
$.fn.datagrid.defaults.loadMsg = '数据正在努力为您加载，请稍候....';

var easyuiErrorFunction = function(XMLHttpRequest) {
	/* $.messager.progress('close'); */
	/* alert(XMLHttpRequest.responseText.split('<script')[0]); */
	//$.messager.alert('错误', XMLHttpRequest.responseText.split('<script')[0]);
	$.messager.alert('提示框', '后台错误请与管理员联系!','warning',function(){
		//document.location = "${pageContext.request.contextPath}/logout.do?method=logout";
		});
};
$.fn.datagrid.defaults.onLoadError = easyuiErrorFunction;
$.fn.treegrid.defaults.onLoadError = easyuiErrorFunction;
$.fn.combogrid.defaults.onLoadError = easyuiErrorFunction;
$.fn.combobox.defaults.onLoadError = easyuiErrorFunction;
$.fn.form.defaults.onLoadError = easyuiErrorFunction;


 $.extend($.fn.datagrid.defaults.editors, {
     datetimebox: {//datetimebox就是你要自定义editor的名称
         
         	init : function(container, options) {
			var editor = $('<input/>').appendTo(container);
			editor.datetimebox(options);
			return editor;
		},
		destroy : function(target) {
			$(target).datetimebox('destroy');
		},
		getValue : function(target) {
			return $(target).parent().find('input.combo-value').val();
		},
		setValue : function(target, value) {
			$(target).datetimebox("setValue",value);
		},
		resize : function(target, width) {
			$(target).datetimebox('resize', width);
		}
		
     }
});

$.extend($.fn.datagrid.defaults.editors, {
	combocheckboxtree : {
		init : function(container, options) {
			var editor = $('<input/>').appendTo(container);
			options.multiple = true;
			editor.combotree(options);
			return editor;
		},
		destroy : function(target) {
			$(target).combotree('destroy');
		},
		getValue : function(target) {
			return $(target).combotree('getValues').join(',');
		},
		setValue : function(target, value) {
			$(target).combotree('setValues', sy.getList(value));
		},
		resize : function(target, width) {
			$(target).combotree('resize', width);
		}
	}
});

/**
 * 获得项目根路径
 * 
 * 使用方法：sy.bp();
 */
sy.bp = function() {
	var curWwwPath = window.document.location.href;
	var pathName = window.document.location.pathname;
	var pos = curWwwPath.indexOf(pathName);
	var localhostPaht = curWwwPath.substring(0, pos);
	var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
	return (localhostPaht + projectName);
};

/**
 * 获得项目名称
 * 
 * 使用方法：sy.bp();
 */
sy.bpName = function() {
	var curWwwPath = window.document.location.href;
	var pathName = window.document.location.pathname;
	var pos = curWwwPath.indexOf(pathName);
	var localhostPaht = curWwwPath.substring(0, pos);
	var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
	return (projectName);
};

/**
 * 增加formatString功能
 * 
 * 使用方法：sy.fs('字符串{0}字符串{1}字符串','第一个变量','第二个变量');
 */
sy.fs = function(str) {
	for ( var i = 0; i < arguments.length - 1; i++) {
		str = str.replace("{" + i + "}", arguments[i + 1]);
	}
	return str;
};

/**
 * 增加命名空间功能
 * 
 * 使用方法：sy.ns('jQuery.bbb.ccc','jQuery.eee.fff');
 */
sy.ns = function() {
	var o = {}, d;
	for ( var i = 0; i < arguments.length; i++) {
		d = arguments[i].split(".");
		o = window[d[0]] = window[d[0]] || {};
		for ( var k = 0; k < d.slice(1).length; k++) {
			o = o[d[k + 1]] = o[d[k + 1]] || {};
		}
	}
	return o;
};

/**
 * 生成UUID
 */
sy.random4 = function() {
	return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
};
sy.UUID = function() {
	return (sy.random4() + sy.random4() + "-" + sy.random4() + "-" + sy.random4() + "-" + sy.random4() + "-" + sy.random4() + sy.random4() + sy.random4());
};

/**
 * 获得URL参数
 */
sy.getUrlParam = function(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
};

sy.getList = function(value) {
	if (value) {
		var values = [];
		var t = value.split(',');
		for ( var i = 0; i < t.length; i++) {
			values.push('' + t[i]);/* 避免他将ID当成数字 */
		}
		return values;
	} else {
		return [];
	}
};

sy.png = function() {
	var imgArr = document.getElementsByTagName("IMG");
	for ( var i = 0; i < imgArr.length; i++) {
		if (imgArr[i].src.toLowerCase().lastIndexOf(".png") != -1) {
			imgArr[i].style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='" + imgArr[i].src + "', sizingMethod='auto')";
			imgArr[i].src = "images/blank.gif";
		}
		if (imgArr[i].currentStyle.backgroundImage.lastIndexOf(".png") != -1) {
			var img = imgArr[i].currentStyle.backgroundImage.substring(5, imgArr[i].currentStyle.backgroundImage.length - 2);
			imgArr[i].style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='" + img + "', sizingMethod='crop')";
			imgArr[i].style.backgroundImage = "url('images/blank.gif')";
		}
	}
};
sy.bgPng = function(bgElements) {
	for ( var i = 0; i < bgElements.length; i++) {
		if (bgElements[i].currentStyle.backgroundImage.lastIndexOf(".png") != -1) {
			var img = bgElements[i].currentStyle.backgroundImage.substring(5, bgElements[i].currentStyle.backgroundImage.length - 2);
			bgElements[i].style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='" + img + "', sizingMethod='crop')";
			bgElements[i].style.backgroundImage = "url('images/blank.gif')";
		}
	}
};

sy.isLessThanIe8 = function() {/* 判断浏览器是否是IE并且版本小于8 */
	return ($.browser.msie && $.browser.version < 8);
};

$.ajaxSetup({
	type : 'POST',
	error : function(XMLHttpRequest, textStatus, errorThrown) {/* 扩展AJAX出现错误的提示 */
		$.messager.progress('close');
		$.messager.alert('错误', XMLHttpRequest.responseText.split('<script')[0]);
	}
});

/*
  
 var easyuiPanelOnMove = function(left, top) {
 	var right,bottom;
 	var bodyWidth = $('body').width();
	var bodyHeight = $('body').height();
	var dialogwidth = $(this).width();
	var dialogHeight = $(this).height();
	if (left < 0) {
		$(this).window('move', {
			left:0,top:top
		});
	}
	
	if (top < 0) {
		$(this).window('move', {
			left:left,top:0
		});
	}
};
//$.fn.panel.defaults.onMove = easyuiPanelOnMove;
//$.fn.window.defaults.onMove = easyuiPanelOnMove;
//$.fn.dialog.defaults.onMove = easyuiPanelOnMove;


//自动计算dialog不超出iframe
function caculateDialog(left,top)
{
	var right,bottom;
	//1558--479--786=1265
	//alert($('body').width()+'---'+left +'----'+ $('#dlg-addDeclareCompany').width()+'='+($('#dlg-addDeclareCompany').width()+left));
	var bodyWidth = $('body').width();
	var bodyHeight = $('body').height();
	var dialogwidth = $('#dlg-addDeclareCompany').width();
	var dialogHeight = $('#dlg-addDeclareCompany').height();
	if(left < 0){
	    $('#dlg-addDeclareCompany').dialog('move',{left:0,top:top});
	}else if((left + dialogwidth) > (bodyWidth - 50)){
	    right = bodyWidth - dialogwidth - 50;
	    $('#dlg-addDeclareCompany').dialog('move',{left:right,top:top});
	}
	if(top < 0){
	    $('#dlg-addDeclareCompany').dialog('move',{left:left,top:0});
	}else if(top > (bodyHeight - dialogHeight - 50 )){
	    bottom = bodyHeight - dialogHeight - 50;
	    $('#dlg-addDeclareCompany').dialog('move',{left:left,top:bottom});
	}
	}
*/
/* 防止超出浏览器边界 */
var easyuiPanelOnMove = function(left, top) {
	if (left < 0) {
		$(this).window('move', {
			left : 1
		});
	}
	if (top < 0) {
		$(this).window('move', {
			top : 1
		});
	}
};
$.fn.panel.defaults.onMove = easyuiPanelOnMove;
$.fn.window.defaults.onMove = easyuiPanelOnMove;
$.fn.dialog.defaults.onMove = easyuiPanelOnMove;


 
 // 获取随机数根据时间戳
 function getRandomByTimeStamp()
 {
	 var timestamp=new Date().getTime();
	 return timestamp;
 }

 
 /* 页面渲染 js 开始 */ 
 
//用户状态
 function formatAccountState(val,row){  
	    if (val == 'enabled'){  
	        return '<font color=green>启用</font>';  
	    }if (val == 'disabled'){  
	        return '<font color=red>禁用</font>';  
	    }
	}  
 
	
function formatItem(row){  
    var s = '<span style="font-weight:bold">' + row.text + '</span><br/>' +  
            '<span style="color:#888">' + row.desc + '</span>';  
    return s;  
}  

//用户审核
function formatCarUserCheck(val,row){
	if(val=='0'){
		return '<font color=gray>未提交审核</font>';
	}else if(val =='1'){
        return '<font color=blue>已提交审核</font>';		
	}else if(val =='2'){
        return '<font color=purple>开始审核</font>';		
	}else if(val =='3'){
		return '<font color=red>未通过</font>';
	}else if(val =='4'){
		return '<font color=green>车主(已通过)</font>';
	}
}
function formatTeantUserCheck(val,row){
	if(val=='0'){
		return '<font color=gray>未提交审核</font>';
	}else if(val =='1'){
        return '<font color=blue>已提交审核</font>';		
	}else if(val =='2'){
        return '<font color=purple>开始审核</font>';		
	}else if(val =='3'){
		return '<font color=red>未通过</font>';
	}else if(val =='4'){
		return '<font color=green>租客(已通过)</font>';
	}
}

//广告图片编辑
function  formatIMG(val,row){
	if (row.isStop == "1") {
		return '<font color="green">已上架</font>'
	}else if(row.isStop == "0") {
		return '<font color="red">已下架</font>'
	}else{
		return '<font color="bulle">未处理</font>'
	}
}
//性别
function formatSex(val,row){
	if(val=='0'){
		return '<font>男</font>'
	}if(val=='1'){
		return '<font>女</font>'
	}
}

//车辆状态
function formatCar(val,row){
	if(val=='0'){
		return '<font color="gray">未提交审核</font>'
	}else if (val=='1'){
		return '<font color="blue">已提交车审核</font>'
	}else if(val=='2'){
		return '<font color="purple">开始审核</font>'
	}else if(val=='3'){
		return '<font color="green">审核通过</font>'
	}else if(val=='4'){
		return '<font color="red">审核未通过</font>'
	}
}
//不可租时间
function formatNotRentTime(val,row){
	if(val.length==0){
		return '';
	}else{
			num=val.split(",");
			var str='';
			for(var i=0;i<num.length;i++){
				str = str + num[i]+" ";
//				if(i==3){
//					str = str + '</br>'
//				}
//					if(num[i]=='1'){
//						str=str+"周一"+" ";
//					}
//					if(num[i]=='2'){
//						str=str+"周二"+" ";
//					}
//					if(num[i]=='3'){
//						str=str+"周三"+" ";
//					}
//					if(num[i]=='4'){
//						str=str+"周四"+" ";
//					}
//					if(num[i]=='5'){
//						str=str+"周五"+" ";
//					}
//					if(num[i]=='6'){
//						str=str+"周六"+" ";
//					}
//					if(num[i]=='7'){
//						str=str+"周日"+" ";
//					}
			}
			return '<font color="red">'+str+'</font>'
	}
}
//车主状态
function formatTBUser(val,row){
	if(val==1){
		return '<font color="green">已注册成功用户</font>'
	}else{
		return '<font color="red">未验证用户</font>'
	}
}
//车主
function formatCarUser(val,row){
	return ''+row.commentUser.userName+''
}
//车名称
function formatCarName(val,row){
	return ''+row.tCar.carName+''
}
//车牌照
function formatPlateNum(val,row){
	return ''+row.tCar.plateNum+''
}
//车主电话
function formatPhone(val,row){
	return ""+row.commentUser.phone+""
}
//评论状态
function formatterCommentState(val,row){
	if(val=='0'){
		return '<font color="green">通过</font>'
	}else{
		return '<font color="red">未通过</font>'
	}
	
}
//订单状态
function formatterOrderState(val,row){
	if(val=='-2'){
		return '<font color="violet">续租车主未确认</font>'
	}else if(val=='-1'){
		return '<font color="violet">续租确认未付款</font>'
	}else if(val=='0'){
		return '<font color="green">已下单(未付款等待车主确认)</font>'
	}else if(val=='1'){
		return '<font color="violet">车主确认(未付款)</font>'
	}else if(val=='2'){
		return '<font color="purple">已付款(未支付押金)</font>'		
	}else if(val=='3'){
		return '<font color="purple">已支付押金(取车中)</font>'
	}else if(val=='4'){
		return '<font color="blue">已取车</font>'
	}else if(val=='5'){
		return '<font color="brown">租客确认已还车</font>'
	}else if(val=='6'){
		return '<font color="orange">车主确认已还车(已完成)</font>'
	}else if(val=='7'){
		return '<font color="gray">过期</font>'
	}else if(val=='8'){
		if(row.cancelPerson=='0'){
			return '<font color="red">取消(车主取消)</font>'
		}else if(row.cancelPerson=='1'){
			return '<font color="red">取消(租客取消)</font>'
		}else{
			return '<font color="red">取消</font>'
		}
	}else if(val=='9')
		return '<font color="yellowgreen">已结算</font>'
}

//押金（预授权）处理情况
function formatterMoneyState(val,row){
	if(val=='0'){
		return '<font color="blue">已完成</font>'
	}else if(val=='1'){
		return '<font color="red">未处理</font>'
	}else if(val=='2'){
		return '<font color="brown">已撤销</font>'
	}else if(val=='3'){
		return '<font color="purple">未收取</font>'
	}
}
function formatterOrderCarUser(val,row){
	return ''+row.carUser.userName+''
}
function formatterOrderCar(val,row){
	return ''+row.car.carName+''
}
function formatterOrderRentUser(val,row){
	return ''+row.rentCarUser.userName+''
}
//账单状态
function formatterUserBillState(val,row){
	if(val=='0'){
		 return '<font color="red">冻结中</font>'
   }else if(val=='1'){
		 return '<font color="green">已完成</font>'
   }else if(val=='2'){
		 return '<font color="blue">取消入账</font>'
   }
}
//事故信息
function accidentState(val,row){
	if(val=='0'){
		return '<font color="red">否</font>'
	}else if(val=='1'){
		return '<font color="green">是</font>'
	}else{
		return '<font color="gray">未处理</font>'
	}
}
//故障跟进状态 ：0,已提交故障信息 1,跟进中 2,已跟进
function formatffollow(val,row){
	if(val=='0'){
		return '<font color="violet">已提交故障信息</font>'
	}else if(val=='1'){
		return '<font color="green">跟进中</font>'		
	}else if(val=='2'){
		return '<font color="purple">已跟进</font>'
	}
}
//事故跟进状态：0.事故信息已录入，1.已通知租客交纳资料，2.理赔资料已收集，3.已交案，4.已赔付，5.已结案
function formatAccidentfollow(val,row){
	if(val=='0'){ 
		return '<font color="violet">事故信息已录入</font>'
	}else if(val=='1'){
		return '<font color="purple">已通知租客交纳资料</font>'		
	}else if(val=='2'){
		return '<font color="blue">理赔资料已收</font>'
	}else if(val=='3'){
		return '<font color="gray">已交案</font>'
	}else if(val=='4'){
		return '<font color="red">已赔付</font>'
	}else if(val=='5'){
		return '<font color="green">已结案</font>'
	}
}
//变速箱
function formatTransMission(val,row){
	if(val=='1'){
		return '<font color="violet">不限</font>'
	}else if(val=='2'){
		return '<font color="green">自动挡</font>'		
	}else if(val=='3'){
		return '<font color="purple">手动挡</font>'
	}
}
//车排量
function formatCarEmission(val,row){
	if(val=='1'){
		return '<font color="violet">不限</font>'
	}else if(val=='2'){
		return '<font color="green">1.6L及以下</font>'		
	}else if(val=='3'){
		return '<font color="purple">1.6L~2.0L</font>'
	}else if(val=='4'){
		return '<font color="orange">2.0L~2.5L</font>'
	}else if(val=='5'){
		return '<font color="blue">2.5L及以上</font>'
	}
}
//车状态
function carState(val,row){
	if(val=='0'){
		return '<font color="green">可租</font>'
	}else if(val=='1'){
		return '<font color="violet">在租</font>'		
	}else if(val=='2'){
		return '<font color="orange">维修</font>'
	}else if(val=='3'){
		return '<font color="red">停用</font>'
	}
}
//提现状态
function formatterApplyState(val,row){
	if(val=='0'){
		return '<font color="orange">审核中</font>'
	}else if(val=='1'){
		return '<font color="green">提现完成</font>'
	}else if(val=='2'){
		return '<font color="red">审核失败</font>'
	}
	
}
//注册渠道
function formatRegisterChannel(val,row){
	if(val=='0'){//注册渠道 0.微信 1.官网 2.第三方合作商 3.APP 4.线下
		return '<font color="violet">微信</font>'
	}else if(val=='1'){
		return '<font color="green">官网</font>'		
	}else if(val=='2'){
		return '<font color="purple">第三方合作商</font>'
	}else if(val=='3'){
		return '<font color="orange">APP</font>'
	}else if(val=='4'){
		return '<font color="blue">线下</font>'
	} 
}
//续租状态      续租状态，0：车主未确认，1未付款，2已付款，3支付过期 4车主拒绝续约 ，5已结算
function formatReletType(val,row){
	if(val=='0'){ 
		return '<font color="violet">车主未确认</font>'
	}else if(val=='1'){
		return '<font color="purple">确认未付款</font>'		
	}else if(val=='2'){
		return '<font color="blue">已付款</font>'
	}else if(val=='3'){
		return '<font color="gray">支付过期</font>'
	}else if(val=='4'){
		return '<font color="red">车主拒绝续约</font>'
	}else if(val=='5'){
		return '<font color="green">已结算</font>'
	}
}
//违章处理状态
function formatterChuliState(val,row){
	if(val=='0'){
		return '<font color="red">未处理</font>'
	}else if(val=='3'){
		return '<font color="green">已处理</font>'
	}
	
}
//出账入账审核状态
function formattercZrZ(val,row){
	if(val=='0'){
		return '<font color="red">未审核</font>'
	}else if(val=='1'){
	    return '<font color="purple">不同意</font>'
	}else if(val=='2'){
	    return '<font color="orange">同意</font>'
	}
}	
//证件类型
function formatterIDState(val,row){
	if(val=='1'){
		return '<font color="violet">身份证</font>'
	}else if(val=='2'){
		return '<font color="purple">护照</font>'
	}else if(val=='3'){
		return '<font color="blue">军官证</font>'
	}
	
}
//优惠券状态
function formatterYHQ(val,row){
	if(val=='0'){
		return '<font color="red">未激活</font>'
	}else if(val=='1'){
		return '<font color="blue">已激活/未使用</font>'
	}else if(val=='2'){
		return '<font color="purple">使用中</font>'
	}else if(val=='3'){
		return '<font color="orange">已使用</font>'
	}else if(val=='4'){
		return '<font>已过期</font>'
	}
	
}
//可载人数
function formatterUserNum(val,row){
	if(val=='1'){
		return '<font>2人</font>'
	}else if(val=='2'){
		return '<font>4人</font>'
	}else if(val=='3'){
		return '<font>5人</font>'
	}else if(val=='4'){
		return '<font>7人及以上</font>'
	}
	
}

//行驶里程
function formatterMileage(val,row){
	if(val=='1'){
		return '<font>不限</font>'
	}else if(val=='2'){
		return '<font>低于2万公里</font>'
	}else if(val=='3'){
		return '<font>2-4万公里</font>'
	}else if(val=='4'){
		return '<font>4-6万公里</font>'
	}else if(val=='5'){
		return '<font>6-9万公里</font>'
	}else if(val=='6'){
		return '<font>9-12万公里</font>'
	}else if(val=='7'){
		return '<font>12-15万公里</font>'
	}else if(val=='8'){
		return '<font>15-20万公里</font>'
	}else if(val=='9'){
		return '<font>超过20万公里</font>'
	}
}
	 /* 页面渲染 js 结束 */ 
	
	