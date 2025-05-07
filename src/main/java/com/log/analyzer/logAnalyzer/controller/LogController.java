package com.log.analyzer.logAnalyzer.controller;

import com.log.analyzer.logAnalyzer.entity.LogEntity;
import com.log.analyzer.logAnalyzer.entity.Payload;
import com.log.analyzer.logAnalyzer.result.LogResultHolder;
import com.log.analyzer.logAnalyzer.service.APIConsumer;
import com.log.analyzer.logAnalyzer.service.ERRORConsumer;
import com.log.analyzer.logAnalyzer.service.INFOConsumer;
import com.log.analyzer.logAnalyzer.service.LogProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.BlockingQueue;

@RestController
public class LogController {

    @Autowired
    BlockingQueue<String> queue;
    @Autowired
    LogResultHolder logEntity;

    @PostMapping("/log")
    public ResponseEntity<LogEntity> getLog(@RequestBody Payload payload) {
        payload.setFileName("src/main/resources/logfile.txt");
        Thread logProducer = new Thread(new LogProducer(queue, payload));
        logProducer.start();
        Thread errorConsumer = new Thread(new ERRORConsumer(queue, logEntity));
        errorConsumer.start();
        try {
            logProducer.join();   // Wait for producer to finish
            errorConsumer.join(); // Wait for consumer to finish
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    return ResponseEntity.ok(LogResultHolder.toLogEntity());
    }
}
