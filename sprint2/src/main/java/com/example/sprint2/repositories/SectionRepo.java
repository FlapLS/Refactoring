package com.example.sprint2.repositories;

import com.example.sprint2.model.SectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SectionRepo extends JpaRepository<SectionEntity, Integer> {
    @Transactional
    @Query(value = "select * from SECTION where article_id = ?1 and sectionorder = ?2", nativeQuery = true)
    SectionEntity findByArticleIdAndSectionOrder(Integer articleId, Integer order);
    @Transactional
    @Modifying
    @Query(value = "update section set newesttext = ?1 where id = ?2", nativeQuery = true)
    void updateText(String text, Integer id);
}
