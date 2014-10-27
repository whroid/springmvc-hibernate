package com.anialy.webproj.common;

public class ResponseEntity<T> {
	private int state;
	private T payload;
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public T getPayload() {
		return payload;
	}
	public void setPayload(T payload) {
		this.payload = payload;
	}
}
