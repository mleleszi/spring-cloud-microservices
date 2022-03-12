package com.example.xmlloader.service;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class JobExecutorServiceImpl implements JobExecutorService {

    private final JobLauncher jobLauncher;

    @Async
    @Override
    public void executeJob(Job job) throws Exception {
        jobLauncher.run(job, new JobParameters());
    }
}
