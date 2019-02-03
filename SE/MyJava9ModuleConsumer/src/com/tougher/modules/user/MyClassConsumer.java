package com.tougher.modules.user;

import com.tougher.modules.*;


public class MyClassConsumer {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		MyClassProvider m = new MyClassProvider();
		System.out.println(m.myMethod());
		
		//Can get class instance
		Class clazz = Class.forName("com.tougher.priv.MyHiddenClass");
		//Cannot create new instance
		//clazz.newInstance();
		
		//Cannot have same package names as required module
	}
}
