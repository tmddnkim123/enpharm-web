package com.enpharm.web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.enpharm.web.dao.UserDAO;
import com.enpharm.web.dto.UserDTO;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        UserDTO userDTO = userDAO.selectUserDetailByUserId(userId);
        if(userDTO == null) {
            throw new UsernameNotFoundException(userId);
        }
        return userDTO;
    }
}
