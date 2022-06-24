package com.in28minutes.microservices.limitsservice.bean;

public class Limit {

	private int min;
	private int max;
	
	
	public Limit() {
		super();
	}
	
	public Limit(int min, int max) {
		super();
		this.min = min;
		this.max = max;
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	
	
}
