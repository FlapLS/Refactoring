package com.example.sprint2.repositories;

import com.example.sprint2.model.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepo extends JpaRepository<ArticleEntity, Integer> {
    ArticleEntity findFirstByArticlename(String name);
}
