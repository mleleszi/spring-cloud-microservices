package com.example.xmlloader.service;

import org.springframework.batch.core.Job;

public interface JobExecutorService {
    void executeJob(Job job) throws Exception;
}
