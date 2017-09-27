package com.itran.cargosystem.function.resolver;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 系统统一异常处理
 */
public class SystemExceptionHandlerResolver implements HandlerExceptionResolver {

    private Logger logger = Logger.getLogger(SystemExceptionHandlerResolver.class.getName());

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        logger.error(ex);
        ex.printStackTrace();
        ModelAndView mv = new ModelAndView();
        mv.addObject(ex);
        mv.setViewName("error");
        return mv;
    }
}
