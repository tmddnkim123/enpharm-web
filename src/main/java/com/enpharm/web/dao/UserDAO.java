package com.enpharm.web.dao;

import com.enpharm.web.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDAO {
    List<UserDTO> selectUserList();
    UserDTO selectUserDetailByIdx(Long idx);
    UserDTO selectUserDetailByUserId(String userId);
    UserDTO doLogin(String userId, String password);
}
