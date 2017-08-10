package cn.yx.controller.backend;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.yx.controller.api.AbstractController;
import cn.yx.holder.AdminInfoHolder;
import cn.yx.model.Admin;

/**
 * @author yuxuanjiao
 * @date 2017年7月30日 下午4:45:50
 * @version 1.0
 */

@Controller
@RequestMapping(value = "/backend")
public class IndexController extends AbstractController {

    @RequestMapping({ "", "/", "/index" })
    public String backendIndex() {
        return "redirect:/backend/news/";
    }

    @RequestMapping("/loginAction")
    public String login(Model model, Admin admin, HttpSession session) {
        Admin res = adminService.checkUser(admin);
        if (res == null) {
            model.addAttribute("error", "* 用户名不存在");
            return "/index/login";
        } else if (!admin.getPasswd().equals(res.getPasswd())) {
            model.addAttribute("error", "* 密码错误");
            return "/index/login";
        } else {
            session.setAttribute("admin", res);
            return "redirect:/backend/";
        }
    }

    @RequestMapping("/login")
    public String loginIndex(HttpSession session) {
        if (session.getAttribute("admin") != null) {
            return "redirect:/backend/";
        }
        return "/index/login";
    }
    
    @RequestMapping("/test")
    public String upload() {
        return "/index/upload";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        AdminInfoHolder.clear();
        return "/index/login";
    }

    // @RequestMapping("/getImage")
    // @ResponseBody
    // public void getPic(String imgKey, HttpServletResponse response) throws
    // IOException {
    // if (imgKey.equals("")) {
    // return;
    // }
    // OutputStream outputStream = response.getOutputStream();
    // ScsResult result = scsService.getPriImage(imgKey);
    // response.setContentType(result.getContentType());
    // if (result.getFileData() != null) {
    // outputStream.write(ScsOperationUtil.inputStream2ByteArray(result.getFileData()));
    // }
    // outputStream.flush();
    // outputStream.close();
    // }

    // @RequestMapping("/addUser")
    // @ResponseBody
    // public JSONObject register(@RequestParam String account, @RequestParam String
    // password, @RequestParam String name) {
    // JSONObject resp = new JSONObject();
    // try {
    // password = MD5Util.toMD5(password);
    // adminService.addUser(account, password, name);
    // resp.put("code", 200);
    // resp.put("msg", "添加成功");
    // } catch (Exception e) {
    // e.printStackTrace();
    // resp.put("code", -1);
    // resp.put("msg", "添加失败");
    // }
    // return resp;
    // }
}
