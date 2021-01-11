package com.example.samplebatch.batch.base;

import java.util.Map;

import com.example.samplebatch.enums.BatchResult;

public interface BatchBase {

    BatchResult execute(Map<String, Object> params) throws Exception;
}