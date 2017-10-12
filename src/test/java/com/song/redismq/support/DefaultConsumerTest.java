package com.song.redismq.support;

import com.song.redismq.Consumer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;

/**
 * Created by song on 2017/10/12.
 */
public class DefaultConsumerTest {

	private Consumer<DefaultProducerTest.Message> consumer;

	@Before
	public void setUp() {
		Jedis jedis = new Jedis("localhost", 6379);
		consumer = new DefaultConsumer<>(jedis);
	}

	@Test
	public void startConsume() throws Exception {
		consumer.startConsume("test", DefaultProducerTest.Message.class, message -> {
			System.out.println(message);
			Assert.assertEquals("Hello World", message.getData());
		});
	}

}