package org.jxau.lctoh.tool;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
/**
 * 小小工具类
 * 内有一些使用小方法
 * */
@Component("Tools")
public class Tools {
	
	public static Gson gson;
	/**得到一个单例gson对象*/
	static{
		/*显示为空属性*/
		gson=new GsonBuilder().serializeNulls().create();
		/*不显示为空属性*/
		//gson=new Gson();
	}
	/**
	 * 反射获取set方法
	 * 类需要序列化并且序列化ID必须为第一个属性
	 * 除去第一个属性序列化ID
	 * */
	public static Method getSetMethod(Class objectClass, String fieldName) {  
        try {  
            Class[] parameterTypes = new Class[1];  
            Field field = objectClass.getDeclaredField(fieldName);  
            parameterTypes[0] = field.getType();  
            StringBuffer sb = new StringBuffer();  
            sb.append("set");  
            sb.append(fieldName.substring(0, 1).toUpperCase());  
            sb.append(fieldName.substring(1));  
            Method method = objectClass.getMethod(sb.toString(), parameterTypes);  
            return method;  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }
	
	/**
	 * 反射获取get方法（需所有属性名小写）
	 * */
	public static Method getGetMethod(Class objectClass, String fieldName) {  
	       StringBuffer sb = new StringBuffer();  
	       sb.append("get");  
	       sb.append(fieldName.substring(0, 1).toUpperCase());  
	       sb.append(fieldName.substring(1));  
	       try{  
	           return objectClass.getMethod(sb.toString());  
	       } catch (Exception e){  
	       }  
	       return null;  
	}
	
	
	
	
	/** 
     * 获取随机字符串 a-z,A-Z,0-9 
     *  
     * @param length 
     *            长度 
     * @return 
     */  
    public static String getRandomString(int length) {  
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";  
        Random random = new Random();  
        StringBuffer sb = new StringBuffer();  
  
        for (int i = 0; i < length; ++i) {  
            int number = random.nextInt(62);// [0,62)  
            sb.append(str.charAt(number));  
        }  
        return sb.toString();  
    }
	
    /**
     * 得到传入时间与当前时间的时间差
     * @param date
     * @return
     */
    public static Long getTimeDifferenceFromNowDate(Date date) {
    	Date nowDate=new Date();
    	return nowDate.getTime()-date.getTime();
    }
    
    
    
}
