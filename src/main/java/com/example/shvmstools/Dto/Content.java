package com.example.shvmstools.Dto;

import java.util.List;

public class Content {

    private List<Part> parts;

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }

    @Override
    public String toString() {
        return "Content{" +
                "parts=" + parts +
                '}';
    }
}
