package com.example.sprint3.service;

import com.example.sprint3.model.SectionEntity;
import com.example.sprint3.model.VersionEntity;

import java.util.List;

public interface VersionService {
    void saveChangesByAuthorizedUser(String newText, String username, SectionEntity section);
    void saveChangesByUnauthorizedUser(String newText, SectionEntity section);
    List<VersionEntity> getListOfWaitingUpdates();
    String getTextOfLastUpdateBySection(SectionEntity section);
    String getTextOfLastApprovedVersion(SectionEntity section);
    void approve(Integer id);
    void decline(Integer id);
    void deleteDeclinedVersions();
}
