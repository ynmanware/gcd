package com.ynm.repository.domain;

import com.ynm.model.Parameters;

public class GCD {
	private Long id;
	private String apiKey;
	private int param1;
	private int param2;
	private int result;

	public GCD() {
		// TODO Auto-generated constructor stub
	}
	
	public GCD(Parameters params, String key) {
		this.param1 = params.getParam1();
		this.param2 = params.getParam2();
		this.apiKey = key;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public int getParam1() {
		return param1;
	}

	public void setParam1(int param1) {
		this.param1 = param1;
	}

	public int getParam2() {
		return param2;
	}

	public void setParam2(int param2) {
		this.param2 = param2;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

}
