package com.example.sprint1.service.impl;

import com.example.sprint1.model.SectionEntity;
import com.example.sprint1.model.VersionEntity;
import com.example.sprint1.repositories.SectionRepo;
import com.example.sprint1.repositories.VersionRepo;
import com.example.sprint1.service.VersionService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class VersionServiceImpl implements VersionService {
    private final VersionRepo repo;
    private final SectionRepo sectionRepo;

    public VersionServiceImpl(VersionRepo repo, SectionRepo sectionRepo) {
        this.repo = repo;
        this.sectionRepo = sectionRepo;
    }

    //todo change method signature

    @Override
    public void saveChangesByAuthorizedUser(String newText, String username, SectionEntity section) {
        VersionEntity entity = new VersionEntity();
        entity.setSectionId(section.getId());
        entity.setPersonedited(username);
        entity.setSectiontext(newText);
        entity.setStatus("Ожидает проверки");
        repo.save(entity);
    }

    @Override
    public void saveChangesByUnauthorizedUser(String newText, SectionEntity section) {
        VersionEntity entity = new VersionEntity();
        entity.setSectionId(section.getId());
        //entity.setIpedited(ip);
        entity.setSectiontext(newText);
        entity.setStatus("Ожидает проверки");
        repo.save(entity);
    }

    @Override
    public List<VersionEntity> getListOfWaitingUpdates() {
        return repo.findAllByStatus("Ожидает проверки");
    }

    @Override
    public String getTextOfLastUpdateBySection(SectionEntity section) {
        List<VersionEntity> versionEntities = repo.findAllBySectionId(section.getId());
        return Collections.max(versionEntities).getSectiontext();
    }

    @Override
    public String getTextOfLastApprovedVersion(SectionEntity section) {
        List<VersionEntity> versionEntities = repo.findAllBySectionIdAndStatus(section.getId(), "Принято");
        String text = Collections.max(versionEntities).getSectiontext();
        //sectionRepo.updateText(text, section.getId());
        return text;
    }

    @Override
    public void approve(Integer id) {
        VersionEntity entity = repo.findById(id).get();
        entity.setStatus("Принято");
        repo.save(entity);
    }

    @Override
    public void decline(Integer id) {
        VersionEntity entity = repo.findById(id).get();
        entity.setStatus("Отклонено");
        repo.save(entity);
    }

}
