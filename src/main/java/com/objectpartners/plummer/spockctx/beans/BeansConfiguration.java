package com.objectpartners.plummer.spockctx.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {
    @Bean
    public HeavyBean heavyBean() {
        return new HeavyBean();
    }

    @Bean
    public LightBean lightBean() {
        return new LightBean();
    }
}
