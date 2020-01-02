package cn.aynu.dept.service;

import cn.aynu.commons.beans.Dept;
import cn.aynu.utils.PageModel;

import java.util.List;

/**
 * company: www.abc.com
 * Author: 苏依林
 * Create Data: 2019/3/1
 */
public interface IDeptService {
    int findDeptCount(String name);

    List<Dept> findDept(String name, PageModel pageModel);

    Dept findDeptById(Integer id);

    int updateDept(Dept dept);

    int removeDept(int[] id);

    int addDept(Dept dept);
}
