package com.beyondsoft.thrift.web.util;

import java.security.MessageDigest;
/**
 * MD5加密工具集合，用于16位和32位加密字符串
 */
public class MD5Utils {

	protected MD5Utils(){}
	/**
	 * md5加密16位方法
	 * @param plainText  加密字符串
	 * @return String 返回16位md5加密字符串
	 */
	public static String getMd5For16(String plainText) {
			return md5(16,plainText);
	}
	/**
	 * md5加密自定义位数方法
	 * @param plainText  加密字符串
	 * @return String 返回自定义位数md5加密字符串
	 */
	public static String getMd5ForCustom(int customLength,String plainText) {
			return md5(customLength,plainText);
	}
	
	/**
	 * md5加密32位方法
	 * @param plainText  加密字符串
	 * @return String 返回32位md5加密字符串
	 */
	public static String getMd5For32(String plainText) {
			return md5(32,plainText);
	}
	
	/**
	 * md5加密方法
	 * @param plainText 加密字符串
	 * @param length 加密位数
	 * @return String 返回md5加密字符串
	 */
	private static String md5(int length,String plainText) {

	   // 返回字符串
	   String md5Str = null;
	   try {
			    // 操作字符串
			    StringBuffer buf = new StringBuffer();
	
			   /**
				 * MessageDigest 类为应用程序提供信息摘要算法的功能，如 MD5 或 SHA 算法。
				 * 信息摘要是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值。
				 * 
				 * MessageDigest 对象开始被初始化。 该对象通过使用 update()方法处理数据。 任何时候都可以调用
				 * reset()方法重置摘要。 一旦所有需要更新的数据都已经被更新了，应该调用digest()方法之一完成哈希计算。
				 * 
				 * 对于给定数量的更新数据，digest 方法只能被调用一次。 在调用 digest 之后，MessageDigest
				 * 对象被重新设置成其初始状态。
				 */
			    MessageDigest md = MessageDigest.getInstance("MD5");
			   
			    // 添加要进行计算摘要的信息,使用 plainText 的 byte 数组更新摘要。
			    md.update(plainText.getBytes());
		
			    // 计算出摘要,完成哈希计算。
			    byte b[] = md.digest();
			    int i;
		
			    for (int offset = 0; offset < b.length; offset++) {
		
					     i = b[offset];
				
					     if (i < 0) {
					    	 
					    	 i += 256;
					    	 
					     }
				
					     if (i < 16) {
					    	 
					    	 buf.append("0");
					    	 
					     }
				
					     // 将整型 十进制 i 转换为16位，用十六进制参数表示的无符号整数值的字符串表示形式。
					     buf.append(Integer.toHexString(i));
				
			    }
		
			    if(length==32){
				    // 32位的加密
				    md5Str = buf.toString();
			    }else if(length==16){
				    // 16位的加密
				    md5Str = buf.toString().substring(8,24);
			    }else{
				    // 自定义位数的加密
			    	int l = (32 - length)/2;
				    md5Str = buf.toString().substring(l,l+length);
			    }
		   } catch (Exception e) {
			   ExceptionUtils.getErrorInfo(e);
		   }
		   return md5Str;
	}
}
