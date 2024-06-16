package com.example.shvmstools.Dto;

import java.util.List;

public class AIEssayURI {

    private List<Content> contents;

    public List<Content> getContents() {
        return contents;
    }

    public void setContents(List<Content> contents) {
        this.contents = contents;
    }

    @Override
    public String toString() {
        return "AIEssayURI{" +
                "contents=" + contents +
                '}';
    }
}
