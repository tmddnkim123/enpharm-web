package com.enpharm.web.service;

import com.enpharm.web.dao.CategoryDAO;
import com.enpharm.web.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryDAO categoryDAO;

    public List<CategoryDTO> selectCategoryList() throws Exception {
        List<CategoryDTO> categoryList = categoryDAO.selectCategoryList();
        return categoryList;
    }

    public CategoryDTO selectCategoryDetail(Long idx) throws Exception {
        CategoryDTO categoryDTO = categoryDAO.selectCategoryDetail(idx);
        return categoryDTO;
    }

}
