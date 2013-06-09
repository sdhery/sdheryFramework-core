package com.sdhery.module.core.web.jacson;

import com.sdhery.module.core.util.JsonBinder;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * Created with IntelliJ IDEA.
 * User: sdhery
 * Date: 13-6-9
 * Time: 上午10:53
 * To change this template use File | Settings | File Templates.
 */
public class MappingJacksonHttpMessageConverterExt extends MappingJacksonHttpMessageConverter {
    private ObjectMapper mapper;

    public MappingJacksonHttpMessageConverterExt() {
        this.mapper = JsonBinder.buildNormalBinder().getMapper();
        setObjectMapper(this.mapper);
    }

    protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException {
        MediaType contentType = inputMessage.getHeaders().getContentType();
        Charset charset = contentType.getCharSet() != null ? contentType.getCharSet() : DEFAULT_CHARSET;

        String content = FileCopyUtils.copyToString(new InputStreamReader(inputMessage.getBody(), charset));

        String value = content.substring(content.indexOf("json=") + 5, content.length());
        if ((value.startsWith("{")) || (value.startsWith("["))) {
            JavaType javaType = getJavaType(clazz,null);
            return this.mapper.readValue(value, javaType);
        }
        return null;
    }
}
