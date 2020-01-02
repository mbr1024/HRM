package cn.aynu.user.service;

import cn.aynu.commons.beans.User;
import cn.aynu.utils.PageModel;

import java.util.List;

/**
 * company: www.abc.com
 * Author: 苏依林
 * Create Data: 2019/3/1
 */
public interface IUserService {
    User findUserByLoginUser(User user);

    List<User> findUser(User user, PageModel pageModel);

    int findUserCount(User user);

    User findUSerById(Integer id);

    int updateUser(User user);

    int addUser(User user);

    int removeUser(int[] ids);
}
