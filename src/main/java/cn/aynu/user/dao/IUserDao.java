package cn.aynu.user.dao;

import cn.aynu.commons.beans.User;

import java.util.List;
import java.util.Map;

/**
 * company: www.abc.com
 * Author: 苏依林
 * Create Data: 2019/3/1
 */
public interface IUserDao {
    User selectUserByLoginUser(User user);

    List<User> selectUser(Map map);

    int selectUserCount(User user);

    User selectUserById(Integer id);

    int updateUser(User user);

    int insertUser(User user);

    int deleteUser(int[] ids);
}
