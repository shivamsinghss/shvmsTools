package com.example.shvmstools.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AIEssayDTO {

    @JsonProperty("topic")
    String topic;

    @JsonProperty("type")
    String type;

    @JsonProperty("word_count")
    String wordCount;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWordCount() {
        return wordCount;
    }

    public void setWordCount(String wordCount) {
        this.wordCount = wordCount;
    }

    @Override
    public String toString() {
        return "AIEssayDTO{" +
                "topic='" + topic + '\'' +
                ", type='" + type + '\'' +
                ", wordCount='" + wordCount + '\'' +
                '}';
    }
}
