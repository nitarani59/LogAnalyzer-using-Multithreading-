package com.log.analyzer.logAnalyzer.service;

import com.log.analyzer.logAnalyzer.entity.Payload;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.concurrent.BlockingQueue;

public class LogProducer implements Runnable{
    private final BlockingQueue<String> queue;
    private final Payload payload;

    public LogProducer(BlockingQueue<String> queue, Payload payload) {
        this.queue = queue;
        this.payload = payload;
    }

    @Override
    public void run() {
        File file = new File(payload.getFileName());
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file));) {
            String record;
            while((record = bufferedReader.readLine()) != null) {
                queue.put(record);
            }
            queue.put("EOF");
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
