package cn.aynu.dept.handler;

import cn.aynu.commons.beans.Dept;
import cn.aynu.dept.service.IDeptService;
import cn.aynu.utils.PageModel;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/dept")
@Controller
public class DeptController {
    @Resource
    private IDeptService deptService;

    @RequestMapping("/findDept.do")
    public String findUser(Model model, String name, @RequestParam(defaultValue = "1") Integer pageIndex) {
        // System.out.println(name);
        int count = deptService.findDeptCount(name);
        //System.out.println("count是"+count);
        PageModel pageModel = new PageModel();
        pageModel.setPageIndex(pageIndex);
        pageModel.setRecordCount(count);
        List<Dept> depts = deptService.findDept(name, pageModel);
        // for (Dept dept:
        //      depts) {
        //     System.out.println(dept);
        //
        // }
        model.addAttribute("depts", depts);
        model.addAttribute("name", name);
        model.addAttribute("pageModel", pageModel);
        return "/jsp/dept/dept.jsp";
    }

    //@ResponseBody
    @RequestMapping("/updateDept.do")
    public String updateDept(Model model, Dept dept, int flag, Integer id) {
        if (flag == 1) {
            dept = deptService.findDeptById(id);
            //System.out.println(dept);
            model.addAttribute("dept", dept);
            return "/jsp/dept/showUpdateDept.jsp";
        }
        int rows = deptService.updateDept(dept);
        if (rows > 0) {
            return "redirect:/dept/findDept.do";
        } else
            return "/jsp/dept/showUpdateDept.jsp";

    }

    @RequestMapping("/removeDept.do")
    public String removeDept(int[] ids, Model model) {
        try {

            int rows = deptService.removeDept(ids);
            if (rows == ids.length) {
                return "redirect:/dept/findDept.do";

            } else {

                model.addAttribute("message", "删除失败！请重试");
                return "/error.jsp";
            }
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("message", "包含外键！禁止删除！");
            return "/error.jsp";
        }
    }

    @RequestMapping("/addDept.do")
    public String addDept(Dept dept, Model model) {
        int rows = deptService.addDept(dept);
        if (rows > 0) {
            return "redirect:/dept/findDept.do";
        } else {

            model.addAttribute("message", "包含外键！禁止删除！");
            return "/error.jsp";
        }
    }
}


