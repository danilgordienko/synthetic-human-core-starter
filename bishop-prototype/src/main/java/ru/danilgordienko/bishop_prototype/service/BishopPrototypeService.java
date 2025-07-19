package ru.danilgordienko.bishop_prototype.service;

import org.springframework.stereotype.Service;
import ru.danilgordienko.synthetic_human_core_starter.AuditModule.WeylandWatchingYou;

@Service
public class BishopPrototypeService {


    @WeylandWatchingYou
    public String test(){
        return "test";
    }
}
