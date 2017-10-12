package com.song.redismq.support;

import com.song.redismq.Consumer;
import com.song.redismq.MessageCallback;
import com.song.redismq.serialized.KryoSerializableUtils;

import java.nio.charset.StandardCharsets;

import redis.clients.jedis.BinaryJedisPubSub;
import redis.clients.jedis.Jedis;

/**
 * Created by song on 2017/10/12.
 */
public class DefaultConsumer<T> implements Consumer<T> {

	private Jedis jedis;

	public DefaultConsumer(Jedis jedis) {
		this.jedis = jedis;
	}

	@Override
	public void startConsume(String topic, Class<T> type, MessageCallback<T> callback) {
		this.jedis.subscribe(new BinaryJedisPubSub() {
			@Override
			public void onMessage(byte[] channel, byte[] message) {
				if (topic.equals(new String(channel))) {
					callback.callback(KryoSerializableUtils.deserialize(type, message));
				}
			}
		}, topic.getBytes(StandardCharsets.UTF_8));
	}
}
