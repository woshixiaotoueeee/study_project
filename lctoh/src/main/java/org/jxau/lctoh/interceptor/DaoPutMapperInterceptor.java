package org.jxau.lctoh.interceptor;

import java.lang.reflect.Field;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.jxau.lctoh.tool.Tools;
import org.jxau.lctoh.tool.base.dao.BaseDao;
import org.springframework.stereotype.Component;

/** 
 * 配合工具包中的方法给dao层中的mapper接口动态赋值 
 * @author qdt_PC
 */  
@Aspect 
@Component("DaoPutMapperInterceptor")
public class DaoPutMapperInterceptor {  
	
	/**为除了putMapper()的所有方法配置AOP*/
    @Pointcut("!(execution(* org.jxau.lctoh.*.dao.*.puttMapper(..)))&&execution(* org.jxau.lctoh.*.dao.*.*(..))")  
    private void notPutMapper(){}//定义一个切入点  
    /**为除了putMapper()的所有方法配置AOP*/
    @Pointcut("!(execution(* org.jxau.lctoh.*.*.dao.*.puttMapper(..)))&&execution(* org.jxau.lctoh.*.*.dao.*.*(..))")  
    private void notPutMapper_2(){}//定义一个切入点  
    /**
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("notPutMapper_2()")  
    public Object doBasicProfiling_2(ProceedingJoinPoint pjp) throws Throwable{
    	/*
    	 * 1.先判断调用dao层方法的对象的属性是否存在为空
    	 * 2.都不为空则执行dao层方法
    	 * 3.存在为空则调用该层对象puttMapper()方法给其赋值
    	 * */
    	boolean flag=false;
        System.out.println("进入环绕通知"+pjp.getSignature().getName()); 
        Object obj=pjp.getTarget();
        
        Field[] fields=obj.getClass().getDeclaredFields();
        for(int i=0;i<fields.length;i++){
        	if(Tools.getGetMethod(obj.getClass(), fields[i].getName()).invoke(obj, null)==null){
        		flag=true;
        	}
        }
        if(flag)((BaseDao)pjp.getThis()).puttMapper();
        Object object = pjp.proceed();//执行该方法 
        System.out.println("完成环绕通知"+pjp.getSignature().getName()); 
        return object;  
    }  
    /**
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("notPutMapper()")  
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable{
    	/*
    	 * 1.先判断调用dao层方法的对象的属性是否存在为空
    	 * 2.都不为空则执行dao层方法
    	 * 3.存在为空则调用该层对象puttMapper()方法给其赋值
    	 * */
    	boolean flag=false;
        System.out.println("进入环绕通知"+pjp.getSignature().getName()); 
        Object obj=pjp.getTarget();
        
        Field[] fields=obj.getClass().getDeclaredFields();
        for(int i=0;i<fields.length;i++){
        	if(Tools.getGetMethod(obj.getClass(), fields[i].getName()).invoke(obj, null)==null){
        		flag=true;
        	}
        }
        
        if(flag)((BaseDao)pjp.getThis()).puttMapper();
        Object object = pjp.proceed();//执行该方法 
        System.out.println("完成环绕通知"+pjp.getSignature().getName()); 
        return object;  
    }  
}  