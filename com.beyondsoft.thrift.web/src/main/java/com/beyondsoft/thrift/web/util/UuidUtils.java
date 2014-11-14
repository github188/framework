package com.beyondsoft.thrift.web.util;

import java.util.UUID;


public class UuidUtils {


	public static String getUUid() {
		String uuid = null;
		try {
			uuid = UUID.randomUUID().toString().replaceAll("-", "");
		} catch (Exception ex) {

		}
		return uuid;
	}

	/**
	 * 自定义规则生成自定义位数编码
	 * @param customLength 自定义长度
	 * @return string
	 */
	public static String getUUIDByCustomLength(int customLength) {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid.substring(8,24);
	}
	/**
	 * 生成16位编码
	 * @return string
	 */
	public static String getUUID16() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid.substring(8,24);
	}
	/**
	 * 生成32位编码
	 * @return string
	 */
	public static String getUUID32() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}

	/**
	 * 自定义规则生成自定义位数编码
	 * @param customLength 自定义长度
	 * @return string
	 */
	public static String getUUIDByMilliSecondAndCustomLength(int customLength) {
		String uuid = "";
		String milliSecond = DateUtils.getMilliSecond();
		uuid = MD5Utils.getMd5ForCustom(customLength,milliSecond);
		return uuid;
	}

	/**
	 * 自定义规则生成16位编码
	 * @return string
	 */
	public static String getUUIDByCustom16() {
		String uuid = "";
		String milliSecond = DateUtils.getMilliSecond();
		uuid = MD5Utils.getMd5For16(milliSecond);
		return uuid;
	}

	/**
	 * 自定义规则生成32位编码
	 * @return string
	 */
	public static String getUUIDByCustom32() {
		String uuid = "";
		String milliSecond = DateUtils.getMilliSecond();
		uuid = MD5Utils.getMd5For32(milliSecond);
		return uuid;
	}
	public static void main(String[] args) {
		System.out.println(getUUid());
	}

}
