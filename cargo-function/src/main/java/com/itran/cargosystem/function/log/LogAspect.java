package com.itran.cargosystem.function.log;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * 增删改操作日志记录切面
 */
@Aspect
@Component
public class LogAspect {
    private Logger logger = Logger.getLogger(this.getClass().getName());
    private Properties config;

    @After(value = "execution(* com.itran.cargosystem.service.impl.*.add*(..))" +
            "||execution(* com.itran.cargosystem.service.impl.*.remove*(..))" +
            "||execution(* com.itran.cargosystem.service.impl.*.update*(..))")
    public void doAfter(JoinPoint jp) {

        System.out.println("*************************************************");
        System.out.println("Args :" + jp.getArgs());
        System.out.println("Target :" + jp.getTarget().getClass().getName());
        System.out.println("Signature :" + jp.getSignature().getName());
        System.out.println("value :" + getProperty(jp.getTarget().getClass().getName() + "." + jp.getSignature().getName()));
        System.out.println("*************************************************");

    }

    private Properties getProperties() {
        if (config == null) {
            config = new Properties();
            try {
                config.load(getClass().getResourceAsStream("/logInfo.properties"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
                logger.error(e);
            }
        }
        return config;
    }

    /**
     * 从logInfo.properties 文件中获取操作描述
     * @param key
     * @return
     */
    private String getProperty(String key) {
        Properties config = getProperties();
        config.getProperty(key);
        return config.getProperty(key);
    }
}
