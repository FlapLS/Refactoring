package com.example.sprint3.config;

import com.example.sprint3.repositories.SectionRepo;
import com.example.sprint3.service.VersionService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulerConfig {
    private final VersionService versionService;
    private final SectionRepo sectionRepo;

    private final int delayTime = 30000;

    public SchedulerConfig(VersionService service, SectionRepo sectionRepo) {
        this.versionService = service;
        this.sectionRepo = sectionRepo;
    }

    @Scheduled(cron = "30 * * * * *", zone = "Europe/Moscow")
    public void updateSectionWithLastVersion() {
        versionService.deleteDeclinedVersions();
    }
}
