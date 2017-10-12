package com.song.redismq;

/**
 * Created by song on 2017/10/12.
 */
public class RedisMQException extends RuntimeException {

	public RedisMQException() {
	}

	public RedisMQException(String message) {
		super(message);
	}

	public RedisMQException(String message, Throwable cause) {
		super(message, cause);
	}

	public RedisMQException(Throwable cause) {
		super(cause);
	}

	public RedisMQException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
