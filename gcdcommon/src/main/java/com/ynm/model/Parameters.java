package com.ynm.model;

import java.io.Serializable;

/**
 * @author Yogesh.Manware
 *
 */
public class Parameters implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1146857771499945947L;
	private int param1;
	private int param2;

	public Parameters() {

	}

	public Parameters(int p1, int p2) {
		this.param1 = p1;
		this.param2 = p2;
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

}
