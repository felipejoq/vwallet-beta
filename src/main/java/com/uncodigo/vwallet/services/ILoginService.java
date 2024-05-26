package com.uncodigo.vwallet.services;

import com.uncodigo.vwallet.dto.UserLoginDto;
import com.uncodigo.vwallet.models.users.User;

public interface ILoginService {
    /**
     * Login user with username and password
     * @param userLoginDto user login dto
     * @return user
     */
    User login(UserLoginDto userLoginDto);
}
