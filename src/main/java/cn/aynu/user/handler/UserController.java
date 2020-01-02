package cn.aynu.user.handler;

import cn.aynu.commons.beans.User;
import cn.aynu.user.service.IUserService;
import cn.aynu.utils.PageModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/user")
@Controller
public class UserController {
    // private IUserDao userDao;
    @Resource
    private IUserService userService;
    @RequestMapping("/login.do")
    public String login(User user, Model model, HttpSession session) {
        //System.out.println(user);
        User user_login = userService.findUserByLoginUser(user);
        if (user_login != null) {
            //System.out.println(user_login);
            session.setAttribute("user_session", user_login);
            return "/jsp/main.jsp";
        } else {
            model.addAttribute("message", "用户名或密码错误，请重新输入");
            return "/index.jsp";
        }
    }

    @RequestMapping("/logout.do")
    public String logout(HttpSession session) {
        session.invalidate();
        return "/index.jsp";
    }

    @RequestMapping("/findUser.do")
    public String findUser(Model model, User user, @RequestParam(defaultValue = "1") Integer pageIndex) {
        int count = userService.findUserCount(user);
        PageModel pageModel = new PageModel();
        pageModel.setPageIndex(pageIndex);
        pageModel.setRecordCount(count);
        List<User> users = userService.findUser(user, pageModel);
        model.addAttribute("users", users);
        model.addAttribute("user", user);
        model.addAttribute("pageModel", pageModel);
        return "/jsp/user/user.jsp";
    }

    @RequestMapping("/updateUser.do")
    public String updateUser(Integer id, User user, Model model, Integer flag) {


        if (flag == 1) {
            user = userService.findUSerById(id);
            model.addAttribute("user", user);

            return "/jsp/user/showUpdateUser.jsp";
        }
        int rows = userService.updateUser(user);
        if (rows > 0) {
            return "redirect:/user/findUser.do";
        } else
            model.addAttribute("message", "修改出错，请重试！");
        return "/404.html";

    }

    @RequestMapping("addUser.do")
    public String addUser(User user) {
        int rows = userService.addUser(user);
        return "redirect:/user/findUser.do";
    }

    @RequestMapping("/removeUser.do")
    public String removeUser(int[] ids, HttpSession session, Model model) {
        //加验证是否删除成功，加错误页面
        //循环删除 会导致频繁访问数据库 影响性能 尽量只访问一次数据库
        //加对所要删除id的验证 禁止删除当前登录用户
        User user = (User) session.getAttribute("user_session");
        //System.out.println("当前登录user"+user);
        for (int id : ids
        ) {
            if (id == user.getId()) {
                model.addAttribute("message", "不可以删除当前登录的用户！");
                return "/error.jsp";
            }
        }
        int rows = userService.removeUser(ids);
        if (rows == ids.length) {
            return "redirect:/user/findUser.do";
        } else {
            model.addAttribute("message", "删除失败！请重试");
            return "/error.jsp";
        }
    }
}


