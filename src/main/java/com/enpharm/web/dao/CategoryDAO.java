package com.enpharm.web.dao;

import com.enpharm.web.dto.CategoryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryDAO {
    List<CategoryDTO> selectCategoryList() throws Exception;
    CategoryDTO selectCategoryDetail(Long idx) throws Exception;;
}
