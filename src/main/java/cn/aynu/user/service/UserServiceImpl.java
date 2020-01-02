package cn.aynu.user.service;

import cn.aynu.commons.beans.User;
import cn.aynu.user.dao.IUserDao;
import cn.aynu.utils.PageModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;


@Service
public class UserServiceImpl implements IUserService {
    @Resource
    private IUserDao userDao;

    @Override
    public User findUserByLoginUser(User user) {
        // System.out.println(user);
        return userDao.selectUserByLoginUser(user);
    }

    @Override
    public List<User> findUser(User user, PageModel pageModel) {
        HashMap<Object,Object> map=new HashMap();
        map.put("user",user);
        map.put("pageModel",pageModel);
        return userDao.selectUser(map);
    }

    @Override
    public int findUserCount(User user) {
        return userDao.selectUserCount(user);
    }

    @Override
    public User findUSerById(Integer id) {
        return userDao.selectUserById(id);
    }

    @Override
    public int updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public int addUser(User user) {
        return userDao.insertUser(user);
    }

    @Override
    public int removeUser(int[] ids) {
        return userDao.deleteUser(ids);
    }
}
