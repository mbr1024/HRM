package cn.aynu.dept.service;

import cn.aynu.commons.beans.Dept;
import cn.aynu.dept.dao.IDeptDao;
import cn.aynu.utils.PageModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class DeptServiceImpl implements IDeptService {
    @Resource
    private IDeptDao deptDao;

    @Override
    public int findDeptCount(String name) {
        int rows=deptDao.selectDeptCount(name);
        //System.out.println(rows);
        return rows;
    }

    @Override
    public List<Dept> findDept(String name, PageModel pageModel) {
        Map map=new HashMap();
        map.put("name",name);
        map.put("pageModel",pageModel);
        return deptDao.selectDept(map);
    }

    @Override
    public Dept findDeptById(Integer id) {
        return deptDao.selectDeptById(id);
    }

    @Override
    public int updateDept(Dept dept) {
        return deptDao.updateDept(dept);
    }

    @Override
    public int removeDept(int[] ids) {
        return deptDao.deleteDept(ids);
    }

    @Override
    public int addDept(Dept dept) {
        return deptDao.insertDept(dept);
    }
}
