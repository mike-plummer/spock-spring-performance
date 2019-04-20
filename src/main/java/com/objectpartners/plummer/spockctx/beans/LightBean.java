package com.objectpartners.plummer.spockctx.beans;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class LightBean {
    public LightBean() {
        log.info("Beginning construction...");
        try {
            Thread.sleep(100);
        } catch (InterruptedException ignored) {}
        log.info("Construction complete");
    }
}
