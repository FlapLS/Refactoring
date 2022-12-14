package com.example.sprint2.service;

import com.example.sprint2.model.SectionEntity;

public interface SectionService {
    Integer getIdByArticleIdAndIndex(Integer articleId, Integer index);
    String getSectionCodeByArticleIdAndIndex(Integer articleId, Integer index);
    SectionEntity getSectionByArticleIdAndIndex(Integer articleId, Integer index);
}
