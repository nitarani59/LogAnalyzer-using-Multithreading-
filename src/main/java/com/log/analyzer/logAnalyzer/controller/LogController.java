package com.log.analyzer.logAnalyzer.controller;

import com.log.analyzer.logAnalyzer.entity.LogEntity;
import com.log.analyzer.logAnalyzer.entity.Payload;
import com.log.analyzer.logAnalyzer.result.LogResultHolder;
import com.log.analyzer.logAnalyzer.service.LogConsumer;
import com.log.analyzer.logAnalyzer.service.LogProcessor;
import com.log.analyzer.logAnalyzer.service.LogProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.BlockingQueue;

@RestController
public class LogController {

    @Autowired
    BlockingQueue<String> queue;
    @Autowired
    List<LogProcessor> logProcessor;

    @PostMapping("/log")
    public ResponseEntity<LogEntity> getLog(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        // Save the uploaded file to a temporary location
        File tempFile;
        try {
            tempFile = File.createTempFile("log_", ".txt");
            file.transferTo(tempFile);
            Payload payload = new Payload();
            payload.setFileName(tempFile.getAbsolutePath());
            Thread logProducer = new Thread(new LogProducer(queue, payload));
            logProducer.start();
            Thread errorConsumer = new Thread(new LogConsumer(queue, logProcessor));
            errorConsumer.start();
            try {
                logProducer.join();   // Wait for producer to finish
                errorConsumer.join(); // Wait for consumer to finish
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (IllegalStateException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(LogResultHolder.toLogEntity());
        }

}
