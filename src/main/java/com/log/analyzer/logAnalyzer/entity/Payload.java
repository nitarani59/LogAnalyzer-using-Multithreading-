package com.log.analyzer.logAnalyzer.entity;

import org.springframework.stereotype.Component;

@Component
public class Payload {
    private String fileName;

    public Payload() {
    }

    public Payload(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
