package com.example.xmlloader.controller;

import com.example.xmlloader.service.JobExecutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/load-xml")
@RequiredArgsConstructor
public class BatchController {

    private final Job job;
    private final JobExecutorService jobExecutorService;

    @PostMapping
    public ResponseEntity run() throws Exception {
        jobExecutorService.executeJob(job);
        return ResponseEntity.ok("Batch Job started");
    }

}
