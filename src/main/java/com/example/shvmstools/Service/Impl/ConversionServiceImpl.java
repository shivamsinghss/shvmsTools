package com.example.shvmstools.Service.Impl;

import com.example.shvmstools.Service.ConversionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

@Service
public class ConversionServiceImpl implements ConversionService {
    private final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    @Override
    public String jsonToXml(String json) throws Exception {
        Map<String, Object> map = objectMapper.readValue(json, LinkedHashMap.class);
        String xml = mapToXml(map);
        return formatXml(xml);
    }

    @Override
    public String xmlToJson(String xml) throws Exception {
        JSONObject jsonObject = XML.toJSONObject(xml);
        return jsonObject.toString(4);
    }

    @Override
    public String mapToJson(Map<String, Object> map) throws Exception {
        return objectMapper.writeValueAsString(map);
    }

    private String mapToXml(Map<String, Object> map) {
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<root>");
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Map) {
                xmlBuilder.append("<").append(key).append(">")
                        .append(mapToXml((Map<String, Object>) value))
                        .append("</").append(key).append(">");
            } else if (value instanceof Iterable) {
                for (Object item : (Iterable<?>) value) {
                    xmlBuilder.append("<").append(key).append(">")
                            .append(item.toString())
                            .append("</").append(key).append(">");
                }
            } else {
                xmlBuilder.append("<").append(key).append(">")
                        .append(value.toString())
                        .append("</").append(key).append(">");
            }
        }
        xmlBuilder.append("</root>");
        return xmlBuilder.toString();
    }

    private String formatXml(String xml) {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                    .parse(new InputSource(new StringReader(xml)));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            StringWriter stringWriter = new StringWriter();
            transformer.transform(new DOMSource(document), new StreamResult(stringWriter));
            return stringWriter.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error formatting XML", e);
        }
    }
}
