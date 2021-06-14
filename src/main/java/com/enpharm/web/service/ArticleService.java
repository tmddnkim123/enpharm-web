package com.enpharm.web.service;

import com.enpharm.web.dao.ArticleDAO;
import com.enpharm.web.dto.ArticleDTO;
import com.enpharm.web.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    ArticleDAO articleDAO;
    @Autowired
    FileService fileService;

    public List<ArticleDTO> selectArticleList(Long categoryIdx) throws Exception {
        List<ArticleDTO> articleList = articleDAO.selectArticleList(categoryIdx);
        return articleList;
    }

    public int insertArticle(Long categoryIdx, String title, String contents, MultipartFile file, UserDTO userDTO) throws Exception {
        ArticleDTO params = new ArticleDTO();
        String originalFileName = null;
        String filePath = null;
        boolean isImage = false;

        if(!file.isEmpty()) {
            String[] whiteList = {"jpeg", "jpg", "png", "gif"};
            originalFileName = fileService.filter(file.getOriginalFilename());
            filePath = fileService.fileUpload(file, originalFileName);
            System.out.println("filePath : " + filePath);
            for(String ext: whiteList) {
                if(filePath.endsWith(ext)) {
                    isImage = true;
                    break;
                }
            }

        }
        params.setOriginalFileName(originalFileName);
        params.setImage(isImage);
        params.setFilePath(filePath);
        params.setCategoryIdx(categoryIdx);
        params.setTitle(title);
        params.setContents(contents);
        params.setWriterIdx(userDTO.getIdx());
        return articleDAO.insertArticle(params);
    }

    public ArticleDTO selectArticleDetail(Long idx) throws Exception {
        ArticleDTO articleDTO = articleDAO.selectArticleDetail(idx);
        return articleDTO;
    }

    public List<ArticleDTO> selectRecentArticle() throws Exception {
        List<ArticleDTO> recentArticle = articleDAO.selectRecentArticle();
        return recentArticle;
    }
}
