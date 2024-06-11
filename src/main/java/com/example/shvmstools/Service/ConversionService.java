package com.example.shvmstools.Service;

import java.util.Map;

public interface ConversionService {
    String jsonToXml(String json) throws Exception;
    String xmlToJson(String xml) throws Exception;
    String mapToJson(Map<String, Object> map) throws Exception;
}

