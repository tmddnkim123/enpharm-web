package com.enpharm.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.enpharm.web.dao.ContactDAO;
import com.enpharm.web.dto.ContactDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {
    @Autowired
    ContactDAO contactDAO;

    public List<ContactDTO> selectContactList(String field, String order) throws Exception {
        List<ContactDTO> contactList = contactDAO.selectContactList(field, order);
        return contactList;
    }

    public int insertContact(String name, String email, String contents) throws Exception {
        ContactDTO params = new ContactDTO();
        params.setName(name);
        params.setEmail(email);
        params.setContents(contents);
        return contactDAO.insertContact(params);
    }

    public ContactDTO selectContactDetail(Long idx) throws Exception {
        ContactDTO contactDTO = contactDAO.selectContactDetail(idx);
        return contactDTO;
    }
}
