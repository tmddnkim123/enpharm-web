package com.enpharm.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class ContactDTO {
    private Long idx;
    private String name;
    private String email;
    private String contents;
    private Date createdTime;
}
