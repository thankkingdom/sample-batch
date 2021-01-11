package com.example.samplebatch.batch;

import com.example.samplebatch.batch.base.BatchBase;
import com.example.samplebatch.enums.BatchResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("SecondBatch")
public class SecondBatch implements BatchBase {
    
    private final Logger logger = LoggerFactory.getLogger(SecondBatch.class);

    public BatchResult execute(Object entity) {
        
        logger.info(SecondBatch.class.getSimpleName() + " executed.");
        return BatchResult.SUCCESS;
    }
}
