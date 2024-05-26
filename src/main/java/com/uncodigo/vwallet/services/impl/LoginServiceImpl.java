package com.uncodigo.vwallet.services.impl;

import com.uncodigo.vwallet.dao.IUserDao;
import com.uncodigo.vwallet.dto.UserLoginDto;
import com.uncodigo.vwallet.models.users.User;
import com.uncodigo.vwallet.services.ILoginService;

public class LoginServiceImpl implements ILoginService {

    private final IUserDao userDao;

    public LoginServiceImpl(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User login(UserLoginDto userLoginDto) {
        return userDao.login(userLoginDto);
    }
}
