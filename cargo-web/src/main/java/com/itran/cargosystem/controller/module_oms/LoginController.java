package com.itran.cargosystem.controller.module_oms;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.itran.cargosystem.function.log.Log;

@Controller
public class LoginController {

    @RequestMapping("/login")
    @Log(operationType = "login操作:", operationName = "登录用户")
    public String login(HttpServletRequest request) throws Exception {
        String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
        if (exceptionClassName != null) {
            if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
                throw new UnknownAccountException("账号不存在");
            } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
                throw new IncorrectCredentialsException("用户名或密码错误");
            } else {
                throw new Exception("未知错误，请联系系统管理员");
            }
        }
        return "login";
    }

    @RequestMapping("/home")
    public String home() throws Exception {
        return "index";
    }

}
