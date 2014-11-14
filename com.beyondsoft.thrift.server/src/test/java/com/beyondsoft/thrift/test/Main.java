package com.beyondsoft.thrift.test;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException {
		Class<?> classs =Thread.currentThread().getContextClassLoader().loadClass("com.beyondsoft.thrift.server.service.CommonServiceImpl");
		System.out.println(classs.getName());;
	}

}
