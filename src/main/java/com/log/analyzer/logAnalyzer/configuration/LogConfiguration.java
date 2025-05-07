package com.log.analyzer.logAnalyzer.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Configuration
public class LogConfiguration {

    @Bean
    public BlockingQueue<String> stringBlockingQueue() {
        return new LinkedBlockingQueue<>();
    }
}
