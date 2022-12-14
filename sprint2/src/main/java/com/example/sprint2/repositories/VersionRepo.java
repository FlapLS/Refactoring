package com.example.sprint2.repositories;

import com.example.sprint2.model.VersionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VersionRepo extends JpaRepository<VersionEntity, Integer> {
    List<VersionEntity> findAllByStatus(String status);
    List<VersionEntity> findAllBySectionId(Integer sectionId);
    List<VersionEntity> findAllBySectionIdAndStatus(Integer sectionId, String status);
}
