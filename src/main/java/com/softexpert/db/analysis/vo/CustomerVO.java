package com.softexpert.db.analysis.vo;

public class CustomerVO {

	private int id;
	private String name;
	private String text;
	private byte[] randomData;
	
	public CustomerVO() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public byte[] getRandomData() {
		return randomData;
	}

	public void setRandomData(byte[] randomData) {
		this.randomData = randomData;
	}
	
}
