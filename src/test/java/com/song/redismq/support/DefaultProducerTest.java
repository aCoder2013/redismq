package com.song.redismq.support;

import com.song.redismq.Producer;

import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;

import redis.clients.jedis.Jedis;

/**
 * Created by song on 2017/10/12.
 */
public class DefaultProducerTest {

	private Producer producer;

	@Before
	public void setUp() {
		Jedis jedis = new Jedis("localhost", 6379);
		producer = new DefaultProducer(jedis);
	}

	@Test
	public void sendOneway() {
		producer.sendOneway("test", new Message("Hello World"));
	}

	static class Message implements Serializable {
		private String data;

		public Message() {
		}

		Message(String data) {
			this.data = data;
		}

		public String getData() {
			return data;
		}

		public Message setData(String data) {
			this.data = data;
			return this;
		}

		@Override
		public String toString() {
			return "Message{" +
					"data='" + data + '\'' +
					'}';
		}
	}
}