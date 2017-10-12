package com.song.redismq.support;

import com.song.redismq.Producer;
import com.song.redismq.RedisMQException;
import com.song.redismq.serialized.KryoSerializableUtils;

import java.nio.charset.StandardCharsets;

import redis.clients.jedis.Jedis;

/**
 * Created by song on 2017/10/12.
 */
public class DefaultProducer implements Producer {

	private final Jedis jedis;

	public DefaultProducer(Jedis jedis) {
		this.jedis = jedis;
	}

	public void sendOneway(String topic, Object message) throws RedisMQException {
		try {
			byte[] bytes = KryoSerializableUtils.serialize(message);
			this.jedis.publish(topic.getBytes(StandardCharsets.UTF_8), bytes);
		} catch (Exception e) {
			throw new RedisMQException(e);
		}
	}
}
