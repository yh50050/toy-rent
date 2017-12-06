package com.toy.utils;

import org.springframework.http.HttpStatus;

/**
 * 统一消息返回
 * 
 * @author 枫茗丿love
 *
 * @param <T>
 */
public class Message<T> {

	public static final int CODE_SUCCESS = 1;
	public static final int CODE_FAIL = 0;
	public static final int CODE_ERROR = -1;

	public static final String MSG_SUCCESS = "success";
	public static final String MSG_FAIL = "fail";
	public static final String MSG_ERROR = "error";

	private HttpStatus code;
	private String message;
	private T data;

	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Message(HttpStatus code, String message, T date) {
		super();
		this.code = code;
		this.message = message;
		this.data = date;
	}

	public HttpStatus getCode() {
		return code;
	}

	public void setCode(HttpStatus code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T date) {
		this.data = date;
	}

	@Override
	public String toString() {
		return "Message{code=" + code + ",message=" + message + ",date=" + data + "}";
	}

}