package com.example.sprint3.repositories;

import com.example.sprint3.model.VersionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface VersionRepo extends JpaRepository<VersionEntity, Integer> {
    List<VersionEntity> findAllByStatus(String status);
    List<VersionEntity> findAllBySectionId(Integer sectionId);
    List<VersionEntity> findAllBySectionIdAndStatus(Integer sectionId, String status);
    @Transactional
    void deleteAllByStatus(String status);
}
