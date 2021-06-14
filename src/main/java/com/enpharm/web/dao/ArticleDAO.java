package com.enpharm.web.dao;

import com.enpharm.web.dto.ArticleDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleDAO {
    List<ArticleDTO> selectArticleList(Long categoryIdx) throws Exception;
    int insertArticle(ArticleDTO params) throws Exception;
    ArticleDTO selectArticleDetail(Long idx) throws Exception;
    List<ArticleDTO> selectRecentArticle() throws Exception;
}
