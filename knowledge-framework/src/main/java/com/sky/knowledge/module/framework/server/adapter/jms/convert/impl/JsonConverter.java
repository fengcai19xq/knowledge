package com.sky.knowledge.module.framework.server.adapter.jms.convert.impl;

import java.io.StringWriter;

import org.codehaus.jackson.map.ObjectMapper;

import com.sky.knowledge.module.framework.server.adapter.jms.convert.ConvertException;
import com.sky.knowledge.module.framework.server.adapter.jms.convert.IConverter;


public class JsonConverter implements IConverter {

	private static ObjectMapper mapper = new ObjectMapper();

	@Override
	public String fromObject(Object to) throws ConvertException {
		try {
			StringWriter writer = new StringWriter();
			mapper.writeValue(writer, to);
			return writer.getBuffer().toString();
		} catch (Throwable e) {
			throw new ConvertException(e);
		}
	}

	@Override
	public <T> T toObject(String xml, Class<T> clazz) throws ConvertException {
		try {
			return mapper.readValue(xml, clazz);
		} catch (Throwable e) {
			throw new ConvertException(e);
		}
	}

}