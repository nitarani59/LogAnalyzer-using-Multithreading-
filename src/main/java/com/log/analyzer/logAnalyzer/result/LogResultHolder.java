package com.log.analyzer.logAnalyzer.result;

import com.log.analyzer.logAnalyzer.entity.LogEntity;
import org.springframework.stereotype.Component;

@Component
public class LogResultHolder {

    private static int apiCount = 0;
    private static int infoCount = 0;
    private static int errorCount = 0;

    public static void setApiCount() {
        apiCount++;
    }

    public static void setInfoCount() {
        infoCount++;
    }

    public static void setErrorCount() {
        errorCount++;
    }

    public static LogEntity toLogEntity() {
        return new LogEntity(apiCount, errorCount, infoCount);
    }
}
