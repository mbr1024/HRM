package cn.aynu.dept.dao;

import cn.aynu.commons.beans.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * company: www.abc.com
 * Author: 苏依林
 * Create Data: 2019/3/1
 */
public interface IDeptDao {
    @SelectProvider(type = DeptSqlProvider.class, method = "selectDeptCount")
    int selectDeptCount(String name);

    @SelectProvider(type = DeptSqlProvider.class, method = "selectDept")
    List<Dept> selectDept(Map map);

    @Select("select * from dept_inf where id=#{id}")
    Dept selectDeptById(Integer id);

    @Update("update dept_inf set name=#{name},remark=#{remark}  where id=#{id}")
    int updateDept(Dept dept);

    @DeleteProvider(type = DeptSqlProvider.class, method = "deleteDept")
    int deleteDept(@Param("ids") int[] ids);

    @Insert("insert into dept_inf (name,remark) values(#{name},#{remark})")
    int insertDept(Dept dept);
}
