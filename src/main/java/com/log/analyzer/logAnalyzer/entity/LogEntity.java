package com.log.analyzer.logAnalyzer.entity;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Entity
public class LogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int logId;
    private int apiCount;
    private int errorCount;
    private int infoCount;

    public LogEntity() {
    }

    public LogEntity(int apiCount, int errorCount, int infoCount) {
        this.apiCount = apiCount;
        this.errorCount = errorCount;
        this.infoCount = infoCount;
    }

    public int getLogId() {
        return logId;
    }

    public int getApiCount() {
        return apiCount;
    }

    public void setApiCount(int apiCount) {
        this.apiCount = apiCount;
    }

    public int getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(int errorCount) {
        this.errorCount = errorCount;
    }

    public int getInfoCount() {
        return infoCount;
    }

    public void setInfoCount(int infoCount) {
        this.infoCount = infoCount;
    }
}
