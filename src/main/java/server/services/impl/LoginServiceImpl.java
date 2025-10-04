package server.services.impl;

import server.dao.UserDao;
import server.dto.UserDto;
import server.dto.UserLoginDto;
import server.entities.User;
import server.services.LoginService;
import server.util.PasswordUtil;

public class LoginServiceImpl implements LoginService {
    private UserDao userDao;
    @Override
    public UserDto login(UserLoginDto userLoginDto) {
        String login = userLoginDto.getLogin();
        String passwordHash = PasswordUtil.encrypt(userLoginDto.getPassword());
        User user = userDao.getByLogin(login);
        if (user != null && user.getPassword().equals(passwordHash)) {
            return new UserDto(user.getName(), user.getLastName(), user.getLogin());
        }
        return null;
    }

    public LoginServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }
}
