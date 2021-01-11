package com.example.samplebatch.service;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FtpService {
    
    private final Logger logger = LoggerFactory.getLogger(FtpService.class);

    public int put(File f) {
        logger.info("FtpService executed.");
        return 0;
    }
}
