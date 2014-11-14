package com.beyondsoft.framework.util;

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

	
	public static void main(String[] args) {
		System.out.println(getUUid());
	}

}
