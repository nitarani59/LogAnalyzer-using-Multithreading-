package com.log.analyzer.logAnalyzer.service;

import com.log.analyzer.logAnalyzer.result.LogResultHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.BlockingQueue;

@Service
public class LogConsumer implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(LogConsumer.class);
    BlockingQueue<String> queue;
    List<LogProcessor> logProcessor;

    public LogConsumer(BlockingQueue<String> queue, List<LogProcessor> logProcessor) {
        this.queue = queue;
        this.logProcessor = logProcessor;
    }

    @Override
    public void run() {
        while (true) {
            String data = null;
            try {
                data = queue.take();
                if (data.equals("EOF")) break;
                for (LogProcessor logProcessor1 : logProcessor) {
                    logProcessor1.processLog(data);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
