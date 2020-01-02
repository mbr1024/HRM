package cn.aynu.job.handler;

import cn.aynu.commons.beans.Job;
import cn.aynu.job.service.IJobService;
import cn.aynu.utils.PageModel;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/job")
@Controller
public class JobController {
    @Resource
    private IJobService jobService;

    @RequestMapping("/findJob.do")
    public String findUser(Model model, String name, @RequestParam(defaultValue = "1") Integer pageIndex) {
        //System.out.println(name);
        int count = jobService.findJobCount(name);
        //System.out.println("count是"+count);
        PageModel pageModel = new PageModel();
        pageModel.setPageIndex(pageIndex);
        pageModel.setRecordCount(count);
        List<Job> jobs = jobService.findJob(name, pageModel);
        // for (Job job:
        //      jobs) {
        //     System.out.println(job);
        //
        // }
        model.addAttribute("jobs", jobs);
        model.addAttribute("name", name);
        model.addAttribute("pageModel", pageModel);
        return "/jsp/job/job.jsp";
    }
    @RequestMapping("/findJobById")
    public String findJobById(Integer id ,Model model){
        // if (flag == 1) {

            Job job = jobService.findJobById(id);
            //System.out.println(job);
            model.addAttribute("job", job);
        // }
        return "/jsp/job/showUpdateJob.jsp";
    }
    @ResponseBody
    @RequestMapping("/updateJob.do")
    public String updateJob(Model model, Job job, int flag, Integer id) {

        int rows = jobService.updateJob(job);
        if (rows > 0) {
            return "OK";
            // return "redirect:/job/findJob.do";
        } else
            // return "/jsp/job/showUpdateJob.jsp";
        return "FALSE";
    }
    @ResponseBody
    @RequestMapping("/removeJob.do")
    public String removeJob(int[] ids, Model model, HttpSession session) {

        //System.out.println(ids);
        try {

            int rows = jobService.removeJob(ids);
            if (rows == ids.length) {
                model.addAttribute("message", "删除成功！我心甚悦。");
                return "OK";
                //return "redirect:/job/findJob.do";

            }else {

                model.addAttribute("message", "删除失败！请重试");
                return "FALSE";
                // return "/error.jsp";
            }
        } catch (DataIntegrityViolationException e){
            model.addAttribute("message", "包含外键！禁止删除！");
            return "ERROR";
            // return "/error.jsp";
        }
    }

    @RequestMapping("/addJob.do")
    public String addJob(Job job, Model model) {
        int rows = jobService.addJob(job);
        if (rows > 0) {
            return "redirect:/job/findJob.do";
        } else {
            model.addAttribute("message", "添加失败！请重试");
            return "/error.jsp";
        }
    }
}


