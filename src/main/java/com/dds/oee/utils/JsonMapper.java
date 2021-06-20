package com.dds.oee.utils;

import java.io.InputStream;
import java.io.Reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Author : duybv Aug 28, 2019
 */

public class JsonMapper {

	private static final Logger LOGGER = LoggerFactory.getLogger(JsonMapper.class);

	private static final ObjectMapper MAPPER;

	static {
		MAPPER = new ObjectMapper();
		MAPPER.setSerializationInclusion(Include.NON_NULL);
		MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	}

	public static <O> String write(O o) {
		try {
			return MAPPER.writeValueAsString(o);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		}
	}

	public static <O> O read(String string, Class<O> clazz) {
		try {
			return MAPPER.readValue(string, clazz);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		}
	}

	public static <O> O read(String string, TypeReference<O> typeReference) {
		try {
			return MAPPER.readValue(string, typeReference);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		}
	}

	public static <O> O read(Reader reader, Class<O> clazz) {
		try {
			return MAPPER.readValue(reader, clazz);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		}
	}

	public static <O> O read(InputStream inputStream, Class<O> clazz) {
		try {
			return MAPPER.readValue(inputStream, clazz);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		}
	}
}
