package com.example.sprint3.service;

import com.example.sprint3.model.SectionEntity;

public interface SectionService {
    Integer getIdByArticleIdAndIndex(Integer articleId, Integer index);
    String getSectionCodeByArticleIdAndIndex(Integer articleId, Integer index);
    SectionEntity getSectionByArticleIdAndIndex(Integer articleId, Integer index);
}
