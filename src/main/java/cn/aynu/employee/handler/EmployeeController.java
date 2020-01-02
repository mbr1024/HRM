package cn.aynu.employee.handler;

import cn.aynu.commons.beans.Dept;
import cn.aynu.commons.beans.Employee;
import cn.aynu.commons.beans.Job;
import cn.aynu.employee.service.IEmployeeService;
import cn.aynu.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/employee")
@Controller
public class EmployeeController {

    @Autowired
    IEmployeeService employeeService;

    @RequestMapping("/findEmployee.do")
    public String findEmployee(Model model, Employee employee, Integer dept_id, Integer job_id, @RequestParam(defaultValue = "1") Integer pageIndex) {
        //将接收到的dept_id，job_id分别存入dept,job,并将这两个对象放入employee对象中
        Dept dept = new Dept();
        Job job = new Job();
        dept.setId(dept_id);
        job.setId(job_id);
        employee.setDept(dept);
        employee.setJob(job);
        //创建pagemodel对象
        PageModel pageModel = new PageModel();
        //设置pagemodel的起始页码索引
        pageModel.setPageIndex(pageIndex);
        //将封装好的employee作为参数传入方法，查找当前条件下的数据条目数
        int count = employeeService.findEmployeeCount(employee);
        //将查询出的数据条目存入pagemodel对象,供分页使用
        pageModel.setRecordCount(count);
        //将查询条件employee和页码信息传入查询方法
        List<Employee> employees = employeeService.findEmployee(employee, pageModel);
        //分别查询关联表的信息
        List<Dept> depts = employeeService.findDept();
        List<Job> jobs = employeeService.findJob();
        //将相关数据存入model中,供前台页面调用显示
        model.addAttribute("jobs", jobs);
        model.addAttribute("depts", depts);
        model.addAttribute("employee", employee);
        model.addAttribute("employees", employees);
        model.addAttribute("pageModel", pageModel);
       // System.out.println(employee);
        //System.out.println(pageModel);
        return "/jsp/employee/employee.jsp";
    }

    @RequestMapping("/addEmployee.do")
    public String addEmployee(Employee employee, Integer dept_id, Integer job_id, Integer flag, Model model) {
        if (flag == 1) {
            List<Dept> depts = employeeService.findDept();
            for (Dept dept :
                    depts) {
                System.out.println(dept);
            }
            List<Job> jobs = employeeService.findJob();
            for (Job job : jobs
            ) {
                System.out.println(job);
            }
            model.addAttribute("jobs", jobs);
            model.addAttribute("depts", depts);
            return "/jsp/employee/showAddEmployee.jsp";
        }
        Dept dept = new Dept();
        Job job = new Job();
        dept.setId(dept_id);
        job.setId(job_id);
        employee.setDept(dept);
        employee.setJob(job);
        int rows = employeeService.addEmployee(employee);
        if (rows > 0) {
            return "redirect:/employee/findEmployee.do";
        } else {
            model.addAttribute("message", "添加失败");
            return "/error.jsp";
        }
    }

    @RequestMapping("/updateEmployee.do")
    public String updateEmployee(Model model, Integer dept_id, Integer job_id, Employee employee, Integer id, Integer flag) {
        if (flag == 1) {
            employee = employeeService.findEmployeeById(id);
            List<Dept> depts = employeeService.findDept();
            List<Job> jobs = employeeService.findJob();
            model.addAttribute("jobs", jobs);
            model.addAttribute("depts", depts);
            model.addAttribute("employee", employee);
            return "/jsp/employee/showUpdateEmployee.jsp";
        }
        Dept dept = new Dept();
        Job job = new Job();
        dept.setId(dept_id);
        job.setId(job_id);
        employee.setDept(dept);
        employee.setJob(job);
        int rows = employeeService.updateEmployee(employee);
        if (rows > 0) {
            return "redirect:/employee/findEmployee.do";
        } else {
            model.addAttribute("message", "修改失败");
            return "/error.jsp";
        }

    }

    @RequestMapping("/removeEmployee.do")
    public String removeEmployee(int[] ids, Model model) {
        int rows = employeeService.removeEmployee(ids);
        if (rows == ids.length) {
            return "redirect:/employee/findEmployee.do";
        } else{
            model.addAttribute("message", "删除失败");
            return "/error.jsp";
        }
    }
}


