package com.objectpartners.plummer.spockctx.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class DataListService {
    public DataListService() {
        log.info("Beginning construction...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {}
        log.info("Construction complete");
    }

    public List<String> get() {
        return Arrays.asList("A", "B", "C");
    }
}
