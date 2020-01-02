package cn.aynu.employee.service;

import cn.aynu.commons.beans.Dept;
import cn.aynu.commons.beans.Employee;
import cn.aynu.commons.beans.Job;
import cn.aynu.utils.PageModel;

import java.util.List;

/**
 * company: www.abc.com
 * Author: 苏依林
 * Create Data: 2019/3/1
 */
public interface IEmployeeService {
    List<Employee> findEmployee(Employee employee, PageModel pageModel);

    int findEmployeeCount(Employee employee);

    List<Dept> findDept();

    List<Job> findJob();

    int addEmployee(Employee employee);

    Employee findEmployeeById(Integer id);

    int updateEmployee(Employee employee);

    int removeEmployee(int[] ids);
}
