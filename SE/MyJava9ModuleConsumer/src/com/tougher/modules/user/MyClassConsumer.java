package com.tougher.modules.user;

import com.tougher.modules.*;

public class MyClassConsumer {
	public static void main(String[] args) {
		MyClassProvider m = new MyClassProvider();
		System.out.println(m.myMethod());
	}
}
