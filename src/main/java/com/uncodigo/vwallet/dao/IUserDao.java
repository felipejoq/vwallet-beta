package com.uncodigo.vwallet.dao;

import com.uncodigo.vwallet.dto.UserLoginDto;
import com.uncodigo.vwallet.models.users.User;

public interface IUserDao {
    User login(UserLoginDto userLoginDto);
    User getUserById(int id);
    User getUserByEmail(String email);
}
