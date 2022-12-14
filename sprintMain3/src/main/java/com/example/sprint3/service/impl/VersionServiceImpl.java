package com.example.sprint3.service.impl;

import com.example.sprint3.model.SectionEntity;
import com.example.sprint3.model.VersionEntity;
import com.example.sprint3.repositories.SectionRepo;
import com.example.sprint3.repositories.VersionRepo;
import com.example.sprint3.service.VersionService;
import com.example.sprint3.utils.VersionJmsProducerImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class VersionServiceImpl implements VersionService {
    private final VersionRepo repo;
    private final SectionRepo sectionRepo;

    private final VersionJmsProducerImpl jmsProducer;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveChangesByAuthorizedUser(String newText, String username, SectionEntity section) {
        VersionEntity entity = new VersionEntity();
        entity.setSectionId(section.getId());
        entity.setPersonedited(username);
        entity.setSectiontext(newText);
        entity.setStatus("Ожидает проверки");
        jmsProducer.sendVersion(versionEntityToMap(entity));
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

    private Map<String, Object> versionEntityToMap(VersionEntity entity) {
        Map<String, Object> map = new HashMap<>();
        map.put("sectionId", entity.getSectionId());
        if (entity.getPersonedited() !=null)
            map.put("peronEdited", entity.getPersonedited());
        map.put("sectionText", entity.getSectiontext());
        map.put("status", entity.getStatus());
        return map;
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
        sectionRepo.updateText(text, section.getId());
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

    @Override
    public void deleteDeclinedVersions() {
        repo.deleteAllByStatus("Отклонено");
    }

}
