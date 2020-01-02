package cn.aynu.employee.service;

import cn.aynu.commons.beans.Dept;
import cn.aynu.commons.beans.Employee;
import cn.aynu.commons.beans.Job;
import cn.aynu.employee.dao.IEmployeeDao;
import cn.aynu.utils.PageModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Resource
    private IEmployeeDao employeeDao;

    @Override
    public List<Employee> findEmployee(Employee employee, PageModel pageModel) {
        Map map = new HashMap();
        map.put("employee", employee);
        map.put("pageModel", pageModel);
        List<Employee> employeess = employeeDao.selectEmployee(map);
        return employeess;
    }

    @Override
    public int findEmployeeCount(Employee employee) {
        int count = employeeDao.selectEmployeeCount(employee);
        return count;
    }

    @Override
    public List<Dept> findDept() {
        return employeeDao.selectDept();
    }

    @Override
    public List<Job> findJob() {
        return employeeDao.selectJob();
    }

    @Override
    public int addEmployee(Employee employee) {
        return employeeDao.insertEmployee(employee);
    }

    @Override
    public Employee findEmployeeById(Integer id) {
        return employeeDao.selectEmployeeById(id);
    }

    @Override
    public int updateEmployee(Employee employee) {
        return employeeDao.updateEmployee(employee);
    }

    @Override
    public int removeEmployee(int[] ids) {

        return employeeDao.deleteEmployee(ids);
    }
}
