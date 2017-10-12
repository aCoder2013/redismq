package com.song.redismq.concurrent;

/**
 * Created by song on 2017/10/12.
 */
public class SendResult {

	private SendStatus sendStatus;

	private String message;

	enum SendStatus {
		OK, FAILED
	}

}
