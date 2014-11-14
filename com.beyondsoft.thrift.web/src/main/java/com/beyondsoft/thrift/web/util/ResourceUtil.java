package com.beyondsoft.thrift.web.util;

import java.util.ResourceBundle;

/**
 * 项目参数工具类
 * 
 */
public class ResourceUtil {

	private static final ResourceBundle bundle = java.util.ResourceBundle.getBundle("thrift-config");

	

//    update-begin--Author:zhangguoming  Date:20140226 for：添加验证码
    /**
     * 获取随机码的长度
     *
     * @return 随机码的长度
     */
    public static String getRandCodeLength() {
        return bundle.getString("randCodeLength");
    }

    /**
     * 获取随机码的类型
     *
     * @return 随机码的类型
     */
    public static String getRandCodeType() {
        return bundle.getString("randCodeType");
    }
    
    /**
     * 获取初始化密码
     *
     * @return 初始化密码
     */
    public static String getResetPassword() {
        return bundle.getString("password");
    }
    
    /**
     * 获取上传文件路径
     *
     * @return 上传文件路径
     */
    public static String getUploadImgPath() {
        return bundle.getString("uploadImgPath");
    }
    
    
    public static String getKey(String key){
    	 return bundle.getString(key);
    }
    
    
	public static void main(String[] args) {
		System.out.println(ResourceUtil.getKey("thrift.server.ip"));
	}
}
