package ru.digitalspirit.asaka.bpm.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("local")
public class LocalDirectoryService implements DirectoryService {

    public String getUserEmail(String userId) {
        return "test@digital-spirit.ru";
    }

}
