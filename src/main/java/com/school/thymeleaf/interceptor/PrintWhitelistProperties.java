package com.school.thymeleaf.interceptor;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class PrintWhitelistProperties implements ApplicationRunner {

    private final Whitelist whitelist;

    public PrintWhitelistProperties(Whitelist whitelist) {
        this.whitelist = whitelist;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Whitelist URLs: " + whitelist.getUrls());
    }
}
