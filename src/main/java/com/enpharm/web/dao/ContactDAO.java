package com.enpharm.web.dao;

import com.enpharm.web.dto.ContactDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ContactDAO {
    List<ContactDTO> selectContactList(String field, String order) throws Exception;
    int insertContact(ContactDTO params) throws Exception;
    ContactDTO selectContactDetail(Long idx) throws Exception;
}
