package com.uncodigo.vwallet.services;

import com.uncodigo.vwallet.dto.UserLoginDto;
import com.uncodigo.vwallet.models.users.User;

public interface ILoginService {
    User login(UserLoginDto userLoginDto);
}
