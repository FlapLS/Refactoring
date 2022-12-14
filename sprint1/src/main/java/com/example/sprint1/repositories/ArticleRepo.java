package com.example.sprint1.repositories;

import com.example.sprint1.model.ArticleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepo extends CrudRepository<ArticleEntity, Integer> {
    ArticleEntity findFirstByArticlename(String name);
}
