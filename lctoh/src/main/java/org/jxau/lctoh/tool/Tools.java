package org.jxau.lctoh.tool;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.jxau.lctoh.position.location.domain.Location;
import org.jxau.lctoh.user.restaurant.domain.Restaurant;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 小小工具类
 * 内有一些实用小方法
 * @author qdt_PC
 */
@Component("Tools")
public class Tools {
	/**用于实体类对象与json字符串之间的转换*/
	public static Gson gson;
	/**得到一个单例gson对象*/
	static{
		/*显示为空属性*/
		gson=new GsonBuilder().serializeNulls().create();
		/*不显示为空属性*/
		//gson=new Gson();
	}
	/**
	 * 将Object转换成Gson类型的字符串
	 * @return
	 */
	public static String ObjectToGsonString(Object obj){
		return gson.toJson(obj);
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
     * @param length  长度 
     * @return String
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
     * 获取随机字符串数字0-9 
     *  
     * @param length 长度 
     * @return 
     */  
    public static String getRandomNumberString(int length) {  
        String str = "0123456789";  
        Random random = new Random();  
        StringBuffer sb = new StringBuffer();  
  
        for (int i = 0; i < length; ++i) { 
        	
            int number = random.nextInt(10);// [0,10)  
            if(!(i==0&&number==0)){
            	sb.append(str.charAt(number));
            }
        }  
        return sb.toString();  
    }
    /**
     * 得到传入时间与当前时间的时间差
     * @param date
     * @return Long
     */
    public static Long getTimeDifferenceFromNowDate(Date date) {
    	Date nowDate=new Date();
    	return nowDate.getTime()-date.getTime();
    }
    
    /**
     * 计算两日期的时间差
     * @param date
     * @return Long
     */
    public static Long getTimeDifferenceFromDate(Date stm,Date etm) {
    	return stm.getTime()-etm.getTime();
    }
    
    /**辅助计算两点距离*/
    private static double EARTH_RADIUS = 6378.137;//地球半径
	private static double rad(double d)
	{
	   return d * Math.PI / 180.0;
	}
  	/**
  	 * 计算两个点的距离（米）
  	 * @param y1
  	 * @param x1
  	 * @param y2
  	 * @param x2
  	 * @return
  	 */
  	public static double getDistance(double x1,double y1, double x2, double y2)
  	{
  	   double radLat1 = rad(y1);
  	   double radLat2 = rad(y2);
  	   double a = radLat1 - radLat2;
  	   double b = rad(x1) - rad(x2);
  	   double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) + 
  	   Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
  	   s = s * EARTH_RADIUS;
  	   //s=Math.round(s);//去除小数？
  	   return s;
  	}
  	
  	
  	/**
  	 * 对店家信息按距离远近进行冒泡排序
  	 * @param list
  	 * @return List<Restaurant> 
  	 */
  	public static List<Restaurant> maoPaoSort(List<Restaurant> list){
  		Restaurant restaurant;
  		for(int i=1;i<list.size();i++){
  			for(int j=0;j<list.size()-i;j++){
  	  			if(list.get(j).getRestaurantDistance().doubleValue()>
  	  					list.get(j+1).getRestaurantDistance().doubleValue()){
  	  				restaurant=list.get(j);
  	  				list.set(j, list.get(j+1));
  	  				list.set(j+1, restaurant);
  	  			}
  	  		}
  		}
  		return list;
  	}
  	
  	
  	
  	/**
  	 * 根据定位信息补全与店家的距离信息并按距离大小进行排序
  	 * @param restaurantList
  	 * @param location
  	 * @return
  	 */
  	public static List<Restaurant> completionDistance(List<Restaurant> restaurantList,Location location){
  		Restaurant restaurant;
		for(int i=0;i<restaurantList.size();i++){
			restaurant=restaurantList.get(i);
			Double distance= Tools.getDistance(restaurant.getRestaurantLongitude().doubleValue(),
					restaurant.getRestaurantLatitude().doubleValue(),
					location.getLongitude().doubleValue(),
					location.getLatitude().doubleValue());
			restaurant.setRestaurantDistance(BigDecimal.valueOf(distance));
			restaurantList.set(i, restaurant);
		}
  		return maoPaoSort(restaurantList);
  	}
}
