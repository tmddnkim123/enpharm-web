package com.enpharm.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class ArticleDTO {
    private Long idx;
    private Long categoryIdx;
    private Long writerIdx;
    private String categoryName;
    private String title;
    private String writerName;
    private String contents;
    private String filePath;
    private String originalFileName;
    private boolean isImage;
    private Date createdTime;
}
