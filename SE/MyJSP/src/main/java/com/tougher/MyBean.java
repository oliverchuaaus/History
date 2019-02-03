package com.tougher;

public class MyBean {
	private String id;
	private int anInt;
	private MyNestedBean nestedBean;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAnInt() {
		return anInt;
	}

	public void setAnInt(int anInt) {
		this.anInt = anInt;
	}

	public MyNestedBean getNestedBean() {
		return nestedBean;
	}

	public void setNestedBean(MyNestedBean nestedBean) {
		this.nestedBean = nestedBean;
	}
}
