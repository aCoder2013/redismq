package com.song.redismq;

/**
 * Created by song on 2017/10/12.
 */
public interface Consumer<T> {

	void startConsume(String topic, Class<T> type, MessageCallback<T> callback);

}
