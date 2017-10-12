package com.song.redismq;

/**
 * Created by song on 2017/10/12.
 */
public interface MessageCallback<T> {

	void callback(T message);

}
