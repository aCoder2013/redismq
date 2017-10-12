package com.song.redismq;

/**
 * Created by song on 2017/10/12.
 */
public interface Producer {

	void sendOneway(String topic, Object message) throws RedisMQException;

}
