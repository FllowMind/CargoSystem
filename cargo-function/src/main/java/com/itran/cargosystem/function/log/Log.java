package com.itran.cargosystem.function.log;

import java.lang.annotation.*;

/**  
* 自定义注释
*  
* @author lsf  
* @date 2017年6月19日  新建  
*/

@Target({ElementType.PARAMETER, ElementType.METHOD}) //字段注解   
@Retention(RetentionPolicy.RUNTIME)  //在运行期保留注解信息 
@Documented  //在生成javac时显示该注解的信息  
@Inherited      //标明MyAnnotation1注解可以被使用它的子类继承  
public @interface Log {

    /** 要执行的操作类型比如：add操作 **/  
    public String operationType() default "";  
     
    /** 要执行的具体操作比如：添加用户 **/  
    public String operationName() default "";
}
  