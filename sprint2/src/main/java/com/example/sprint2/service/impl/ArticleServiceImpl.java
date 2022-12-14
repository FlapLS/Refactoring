package com.example.sprint2.service.impl;

import com.example.sprint2.repositories.ArticleRepo;
import com.example.sprint2.service.ArticleService;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepo repo;

    public ArticleServiceImpl(ArticleRepo repo){
        this.repo = repo;
    }

    @Override
    public Integer getIdByName(String name) {
        return repo.findFirstByArticlename(name).getId();
    }
}
