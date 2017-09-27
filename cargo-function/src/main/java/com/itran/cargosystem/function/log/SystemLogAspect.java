package com.itran.cargosystem.function.log;

import com.itran.cargosystem.common.util.CommonUtils;
import com.itran.cargosystem.common.util.date.DateTimeZoneUtils;
import com.itran.cargosystem.common.util.ip.IPUtils;
import com.itran.cargosystem.dao.module_common.CommonDao;
import com.itran.cargosystem.entity.SystemLog;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;


/**
 * 日志面类
 * 测试after,before,around,throwing,returning Advice.
 *
 * @author Admin
 */
public class SystemLogAspect implements Ordered {

    private SystemLog systemLog;

    // 注入Service用于把日志保存数据库
    @Autowired
    private CommonDao commonDao;

    private static final Logger logger = Logger.getLogger(SystemLogAspect.class);

    /**
     * 在核心业务执行前执行，不能阻止核心业务的调用。
     *
     * @param joinPoint
     */
    @SuppressWarnings("unused")
    private void doBefore(JoinPoint joinPoint) {

    	/*此处意在执行核心业务逻辑前，做一些安全性的判断等等*/
        /*可通过joinPoint来获取所需要的内容*/
        System.out.println("========== 开始执行controller前置通知 ========== ");
        if (logger.isInfoEnabled()) {
            System.out.println(logger + "==========================____");
            logger.info("before " + joinPoint);
        }
    }

    /**
     * 手动控制调用核心业务逻辑，以及调用前和调用后的处理,
     * <p>
     * 注意：当核心业务抛异常后，立即退出，转向After Advice
     * 执行完毕After Advice，再转到Throwing Advice
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    private Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {  
        /*此处可以做类似于Before Advice的事情*/
        System.out.println("=============== 开始执行controller环绕通知 ===============");
        Object result = null;
        //执行的方法
        //String methodName = joinPoint.getSignature().getName();
        // 开始时间
        long start = System.currentTimeMillis();
        try {
            // 执行核心逻辑
            result = ((ProceedingJoinPoint) joinPoint).proceed();
            // 结束时间
            long end = System.currentTimeMillis();
            if (logger.isInfoEnabled()) {
                // 日志：环绕结束，用时xx毫秒
                System.out.println("返回结果：" + result.toString());
                logger.info("around " + joinPoint + "\tUse time : " + (end - start) + " ms!");
            }
            //此处可以做类似于After Advice的事情
            System.out.println("=============== 结束执行controller环绕通知 ===============");
        } catch (Throwable e) {
            long end = System.currentTimeMillis();
            if (logger.isInfoEnabled()) {
                // 异常日志
                System.out.println("=============== controller错误信息 ===============");
                logger.error("around " + joinPoint + "\tUse time : " + (end - start) + " ms with exception : "
                        + e.getClass().getName() + ":" + e.getMessage());
            }
        }
        return result;
    }

    /**
     * 核心业务逻辑退出后（包括正常执行结束和异常退出），执行此Advice
     *
     * @param joinPoint
     */
    private void doAfter(JoinPoint joinPoint) {   
        /*此处意在执行核心业务逻辑之后，做一些日志记录操作等等*/   
        /*可通过joinPoint来获取所需要的内容*/
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String ip = IPUtils.getRealIPAddress(request);
        //模拟用户
        String user = "aaa";
        try {

            String targetName = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            Object[] arguments = joinPoint.getArgs();
            Class targetClass = Class.forName(targetName);
            Method[] methods = targetClass.getMethods();
            String operationType = "";
            String operationName = "";
            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    Class[] clazzs = method.getParameterTypes();
                    if (clazzs.length == arguments.length) {
                        // log注释解析
                        operationType = method.getAnnotation(Log.class).operationType();
                        operationName = method.getAnnotation(Log.class).operationName();
                        break;
                    }
                }
            }
            // *========控制台输出=========*//
            System.out.println("========== controller后置通知开始 ==========");
            System.out.println("请求方法:"
                    + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()")
                    + "." + operationType);
            System.out.println("方法描述:" + operationName);
            System.out.println("请求人:" + user);
            System.out.println("请求IP:" + ip);
            // *========数据库要记录的日志信息=========*//
            systemLog = new SystemLog();
            systemLog.setId(CommonUtils.uuid());
            systemLog.setDescription(operationName);
            systemLog.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()")
                    + "." + operationType);
            systemLog.setLogType(0);
            systemLog.setRequestIp(ip);
            systemLog.setExceptionCode(null);
            systemLog.setExceptionDetail(null);
            systemLog.setParams(Arrays.toString(joinPoint.getArgs()));
            systemLog.setCreateUser(user);
            systemLog.setCreateTime(DateTimeZoneUtils.getGMTTime(new Date()));
            // 保存数据库
            //commonDao.insert(systemLog);
            System.out.println("========== controller后置通知结束 ==========");
        } catch (Exception e) {
            // 记录本地异常日志
            logger.error("==后置通知异常==");
            logger.error("异常信息记录到本地日志:" + "{" + e.getClass().getName() + ":" + e.getMessage() + "}");
        }
    }

    /**
     * 核心业务逻辑调用正常退出后，不管是否有返回值，正常退出后，均执行此Advice
     *
     * @param joinPoint
     */
    private void doReturn(JoinPoint joinPoint) {  
    	/*此处可以对返回值做进一步处理*/
    	/*可通过joinPoint来获取所需要的内容*/
        System.out.println("=====执行controller后置返回通知=====");
        if (logger.isInfoEnabled()) {
            logger.info("afterReturn " + joinPoint);
        }
    }

    /**
     * 核心业务逻辑调用异常退出后，执行此Advice，处理错误信息
     *
     * @param joinPoint
     * @param ex
     */
    private void doThrowing(JoinPoint joinPoint, Throwable ex) {
    	/*此处意在执行核心业务逻辑出错时，捕获异常，并可做一些日志记录操作等等*/
    	/*可通过joinPoint来获取所需要的内容*/
        //获取用户请求方法的参数并序列化为JSON格式字符串
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String ip = IPUtils.getRealIPAddress(request);
        String user = "eeee";
        try {

            String targetName = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            Object[] arguments = joinPoint.getArgs();
            Class targetClass = Class.forName(targetName);
            Method[] methods = targetClass.getMethods();
            String operationType = "";
            String operationName = "";
            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    Class[] clazzs = method.getParameterTypes();
                    if (clazzs.length == arguments.length) {
                        operationType = method.getAnnotation(Log.class).operationType();
                        operationName = method.getAnnotation(Log.class).operationName();
                        break;
                    }
                }
            }
            /*========控制台输出=========*/
            System.out.println("↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓出错啦↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓");
            System.out.println("异常代码:" + ex.getClass().getName());
            System.out.println("异常信息:" + ex.getMessage());
            System.out.println("异常方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()") + "." + operationType);
            System.out.println("方法描述:" + operationName);
            System.out.println("请求人:" + user);
            System.out.println("请求IP:" + ip);
            System.out.println("请求参数:" + Arrays.toString(joinPoint.getArgs()));
              /*==========数据库日志=========*/
            systemLog = new SystemLog();
            systemLog.setDescription(operationName);
            systemLog.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()")
                    + "." + operationType);
            systemLog.setLogType(0);
            systemLog.setRequestIp(ip);
            systemLog.setExceptionCode(ex.getClass().getName());
            systemLog.setExceptionDetail(ex.getMessage());
            systemLog.setParams(Arrays.toString(joinPoint.getArgs()));
            systemLog.setCreateUser(user);
            systemLog.setCreateTime(DateTimeZoneUtils.getGMTTime(new Date()));
            //保存数据库
            //commonDao.insert(systemLog);

            System.out.println("↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑出错啦↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑");
            logger.error("异常信息记录到本地日志:");
            logger.error("{" + ex.getClass().getName() + ":" + ex.getMessage() + "}");
        } catch (Exception e) {
            //记录本地异常日志
            System.out.println("↓↓↓记录异常日志出错啦↓↓↓");
            logger.error("记录异常信息日志出现异常:{}" + e.getClass().getName() + ":" + e.getMessage());
            System.out.println("↑↑↑记录异常日志出错啦↑↑↑");
        }
    }

    /**
     * 事务级别
     */
    @Override
    public int getOrder() {
        // TODO Auto-generated method stub
        return 1;
    }
}
  