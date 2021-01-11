package com.example.samplebatch.batch.base;

import com.example.samplebatch.enums.BatchResult;

public interface BatchBase {

    BatchResult execute(Object entity);
}