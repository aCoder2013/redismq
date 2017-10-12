package com.song.redismq.serialized;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.pool.KryoFactory;
import com.esotericsoftware.kryo.pool.KryoPool;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Created by song on 2017/10/12.
 */
public class KryoSerializableUtils {

	public static final int DEFAULT_BUFFER_SIZE = 1024;

	private static KryoPool KRYO_POOL;

	static {
		KryoFactory kryoFactory = new KryoFactory() {
			public Kryo create() {
				// TODO: 2017/10/12 custom serialized strategy
				return new Kryo();
			}
		};
		KRYO_POOL = new KryoPool.Builder(kryoFactory).build();
	}


	public static byte[] serialize(Object obj) throws Exception {
		ByteArrayOutputStream bo = new ByteArrayOutputStream(DEFAULT_BUFFER_SIZE);
		Output output = new Output(bo);
		Kryo kryo = null;
		try {
			kryo = KRYO_POOL.borrow();
			kryo.writeObject(output, obj);
			output.flush();
			bo.flush();
			return bo.toByteArray();
		} finally {
			KRYO_POOL.release(kryo);
		}
	}


	public static <T> T deserialize(Class<T> type, byte[] bytes) {
		ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
		Input input = new Input(bi);
		T obj;
		Kryo kryo = null;
		try {
			kryo = KRYO_POOL.borrow();
			obj = kryo.readObject(input, type);
		} finally {
			KRYO_POOL.release(kryo);
		}
		return obj;
	}

}
