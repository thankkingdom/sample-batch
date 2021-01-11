package com.example.samplebatch.batch;

import com.example.samplebatch.batch.base.*;
import com.example.samplebatch.enums.BatchResult;
import com.example.samplebatch.service.FtpService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("FirstBatch")
public class FirstBatch implements BatchBase {

    private final Logger logger = LoggerFactory.getLogger(FirstBatch.class);

    @Autowired
    private FtpService ftpService;

    public BatchResult execute(Object entity) {

        ftpService.put(null);
        logger.info(FirstBatch.class.getSimpleName() + " executed.");
        return BatchResult.SUCCESS;
    }
}
