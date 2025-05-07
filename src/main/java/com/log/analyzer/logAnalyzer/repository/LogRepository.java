package com.log.analyzer.logAnalyzer.repository;

import com.log.analyzer.logAnalyzer.entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<LogEntity, Integer> {
}
