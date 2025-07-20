package ru.danilgordienko.synthetic_human_core_starter.service;

import org.springframework.stereotype.Service;
import ru.danilgordienko.synthetic_human_core_starter.AuditModule.WeylandWatchingYou;

@Service
public class TestService {

    @WeylandWatchingYou
    public String greet(String name) {
        return "Hello, " + name;
    }
}
