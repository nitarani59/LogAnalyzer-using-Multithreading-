package com.log.analyzer.logAnalyzer.service;

import com.log.analyzer.logAnalyzer.result.LogResultHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("info")
public class InfoProcessor implements LogProcessor{
    @Autowired
    LogResultHolder logResultHolder;
    @Override
    public void processLog(String record) {
        if (record.contains("INFO")) {
            LogResultHolder.setInfoCount();
        }
    }
}
