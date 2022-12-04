package com.example.sprint1.service;

import com.example.sprint1.model.SectionEntity;

public interface SectionService {
    Integer getIdByArticleIdAndIndex(Integer articleId, Integer index);
    String getSectionCodeByArticleIdAndIndex(Integer articleId, Integer index);
    SectionEntity getSectionByArticleIdAndIndex(Integer articleId, Integer index);
}
