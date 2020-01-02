package cn.aynu.employee.dao;

import cn.aynu.commons.beans.Dept;
import cn.aynu.commons.beans.Employee;
import cn.aynu.commons.beans.Job;

import java.util.List;
import java.util.Map;

/**
 * company: www.abc.com
 * Author: 苏依林
 * Create Data: 2019/3/1
 */
public interface IEmployeeDao  {

    List<Employee> selectEmployee(Map map);

    int selectEmployeeCount(Employee employee);

    List<Dept> selectDept();

    List<Job> selectJob();

    int insertEmployee(Employee employee);

    Employee selectEmployeeById(Integer id);

    int updateEmployee(Employee employee);

    int deleteEmployee(int[] ids);
}
