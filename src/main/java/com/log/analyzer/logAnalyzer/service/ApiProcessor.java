package com.log.analyzer.logAnalyzer.service;

import com.log.analyzer.logAnalyzer.result.LogResultHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class ApiProcessor implements LogProcessor{
    @Autowired
    LogResultHolder logResultHolder;
    @Override
    public void processLog(String record) {
        if (record.contains("API")) {
            LogResultHolder.setApiCount();
        }
    }
}
