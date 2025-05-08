package com.log.analyzer.logAnalyzer.service;

import com.log.analyzer.logAnalyzer.result.LogResultHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("error")
public class ErrorProcessor implements LogProcessor{
    @Autowired
    LogResultHolder logResultHolder;
    @Override
    public void processLog(String record) {
        if (record.contains("ERROR")) {
            LogResultHolder.setErrorCount();
        }
    }
}
