package com.objectpartners.plummer.spockctx.beans;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class HeavyBean {
    public HeavyBean() {
        log.info("Beginning construction...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignored) {}
        log.info("Construction complete");
    }
}
