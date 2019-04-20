package com.objectpartners.plummer.spockctx.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DataByIdService {
    public DataByIdService() {
        log.info("Beginning construction...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {}
        log.info("Construction complete");
    }

    public String get(String id) {
        return id;
    }
}
