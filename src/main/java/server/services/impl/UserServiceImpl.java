package server.services.impl;



import server.dao.UserDao;
import server.dao.impl.UserDaoImpl;
import server.dto.UserDto;
import server.services.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    @Override
    public List<UserDto> getAll() {
        return userDao.getAll().stream().map(u-> new UserDto(u.getName(), u.getLastName(), u.getLogin())).toList();
    }

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }
}
