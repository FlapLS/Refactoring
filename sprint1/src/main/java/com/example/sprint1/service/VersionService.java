package com.example.sprint1.service;

import com.example.sprint1.model.SectionEntity;
import com.example.sprint1.model.VersionEntity;

import java.util.List;

public interface VersionService {
    void saveChangesByAuthorizedUser(String newText, String username, SectionEntity section);
    void saveChangesByUnauthorizedUser(String newText, SectionEntity section);
    List<VersionEntity> getListOfWaitingUpdates();
    String getTextOfLastUpdateBySection(SectionEntity section);
    String getTextOfLastApprovedVersion(SectionEntity section);
    void approve(Integer id);
    void decline(Integer id);
}
