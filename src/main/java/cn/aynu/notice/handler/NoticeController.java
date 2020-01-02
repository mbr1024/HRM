package cn.aynu.notice.handler;

import cn.aynu.commons.beans.Notice;
import cn.aynu.commons.beans.User;
import cn.aynu.notice.service.INoticeService;
import cn.aynu.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/notice")
@Controller
public class NoticeController {
    @Autowired
    private INoticeService noticeService;

    @RequestMapping("/findNotice")
    public String findNotice(Model model, Integer user_id, @RequestParam(defaultValue = "1") Integer pageIndex, Notice notice) {
        // System.out.println("接收到"+notice);
        User user=new User();
        user.setId(user_id);
        notice.setUser(user);
        PageModel pageModel = new PageModel();
        int count = noticeService.findNoticeCount(notice);
        pageModel.setRecordCount(count);
        pageModel.setPageIndex(pageIndex);
        List<Notice> notices = noticeService.findNotice(notice, pageModel);
        model.addAttribute("notice",notice);
        model.addAttribute("notices",notices);
        model.addAttribute("pageModel",pageModel);
        // for (Notice notic:notices
        //      ) {
        //     System.out.println("查询出的"+notic);
        // }
        // System.out.println("返回之前"+notice);
        return "/jsp/notice/notice.jsp";
    }
    @RequestMapping("/previewNotice.do")
    public String previewNotice(Integer id,Model model){
        Notice notice=noticeService.findNoticeById(id);
        model.addAttribute("notice",notice);
        return "/jsp/notice/previewNotice.jsp";
    }
    @RequestMapping("/updateNotice.do")
    public String updateNotice(Integer flag,Notice notice,Model model,Integer id){
        if (flag==1){
            notice=noticeService.findNoticeById(id);
            model.addAttribute("notice",notice);
            return "/jsp/notice/showUpdateNotice.jsp";

        }
        int rows=noticeService.updateNotice(notice);
        if (rows>0)
        return "redirect:/notice/findNotice.do";
        else {
            model.addAttribute("message","修改失败");
            return "/error.jsp";
        }
    }
    @RequestMapping("/addNotice")
    public String addNotice(Notice notice ,Model model,HttpSession session){
        User user= (User) session.getAttribute("user_session");


        notice.setUser(user);
        int rows=noticeService.addNotice(notice);
        if (rows>0)
            return "redirect:/notice/findNotice.do";
        else {
            model.addAttribute("message","添加失败");
            return "/error.jsp";
        }
    }
    @RequestMapping("/removeNotice.do")
    public String removeNotice(Integer[] ids,Model model){
        int rows = noticeService.renoveNotice(ids);
        if (rows == ids.length) {
            model.addAttribute("message", "删除成功！我心甚悦。");
            return "redirect:/notice/findNotice.do";
            //return "redirect:/notice/findNotice.do";

        }else {

            model.addAttribute("message", "删除失败！请重试");
            return "/error.jsp";
            // return "/error.jsp";
        }
    }

}


