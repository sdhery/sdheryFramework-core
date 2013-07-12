package com.sdhery.module.core.json;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: sdhery
 * Date: 13-6-9
 * Time: 上午10:54
 * To change this template use File | Settings | File Templates.
 */
public class JsonBinder {
    private static Logger logger = LoggerFactory.getLogger(JsonBinder.class);
    private ObjectMapper mapper;
    private static JsonBinder normalBinder;
    private static JsonBinder nonNullBinder;

    private JsonBinder(JsonSerialize.Inclusion inclusion) {
        this.mapper = new ObjectMapper();

        this.mapper.getSerializationConfig().withSerializationInclusion(inclusion);

        this.mapper.getDeserializationConfig().without(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public static synchronized JsonBinder buildNormalBinder() {
        if (normalBinder == null) {
            normalBinder = new JsonBinder(JsonSerialize.Inclusion.ALWAYS);
        }
        return normalBinder;
    }

    public static synchronized JsonBinder buildNonNullBinder() {
        if (nonNullBinder == null) {
            nonNullBinder = new JsonBinder(JsonSerialize.Inclusion.NON_NULL);
        }
        return nonNullBinder;
    }

    public <T> T toBean(String jsonString, Class<T> clazz) {
        if (StringUtils.isEmpty(jsonString))
            return null;
        try {
            return this.mapper.readValue(jsonString, clazz);
        } catch (IOException e) {
            logger.warn("parse json string error:" + jsonString, e);
        }
        return null;
    }

    public String toJson(Object object) {
        try {
            return this.mapper.writeValueAsString(object);
        } catch (IOException e) {
            logger.warn("write to json string error:" + object, e);
        }
        return null;
    }

    public ObjectMapper getMapper() {
        return this.mapper;
    }
}
