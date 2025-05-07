package com.log.analyzer.logAnalyzer.service;

import com.log.analyzer.logAnalyzer.entity.LogEntity;
import com.log.analyzer.logAnalyzer.result.LogResultHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;

@Service
public class ERRORConsumer implements Runnable {
    BlockingQueue<String> queue;
    LogResultHolder logEntity;
    public ERRORConsumer(BlockingQueue<String> queue, LogResultHolder logEntity) {
        this.queue = queue;
        this.logEntity = logEntity;
    }

    @Override
    public void run() {
        while (true) {
            String data = null;
            try {
                data = queue.take();
                if (data.equals("EOF")) break;
                if (data.contains("ERROR")) {
                    LogResultHolder.setErrorCount();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
