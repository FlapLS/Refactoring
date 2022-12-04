package com.example.sprint1.repositories;

import com.example.sprint1.model.VersionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VersionRepo extends CrudRepository<VersionEntity, Integer> {
    List<VersionEntity> findAllByStatus(String status);
    List<VersionEntity> findAllBySectionId(Integer sectionId);
    List<VersionEntity> findAllBySectionIdAndStatus(Integer sectionId, String status);
}
