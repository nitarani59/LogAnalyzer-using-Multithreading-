package com.log.analyzer.logAnalyzer.service;

import com.log.analyzer.logAnalyzer.entity.LogEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;

@Service
public class INFOConsumer implements Runnable {

    @Autowired
    BlockingQueue<String> queue;

    @Override
    public void run() {
        while (true) {
            String data = null;
            try {
                data = queue.take();
                if (data.equals("EOF")) break;
                LogEntity logEntity = new LogEntity();
                if (data.contains("INFO")) {
                    logEntity.setInfoCount(logEntity.getInfoCount() + 1);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
