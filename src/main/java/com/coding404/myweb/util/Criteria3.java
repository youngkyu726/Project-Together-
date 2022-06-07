package com.coding404.myweb.util;

import lombok.Data;

@Data //getter, setter
public class Criteria3 {
	
	//sql문에 전달될 값을 가지고 다니는 클래스 
	private int page;
	private int amount;
	
	//검색키워드 추가 
	private String searchType;
	private String search;
	
	public Criteria3() {
		this(1, 10);
	}
	
	public Criteria3(int page, int amount) {
		super();
		this.page = page;
		this.amount = amount;
	}

}