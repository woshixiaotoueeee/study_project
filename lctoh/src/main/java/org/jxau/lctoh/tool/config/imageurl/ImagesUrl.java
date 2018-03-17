package org.jxau.lctoh.tool.config.imageurl;

import java.io.File;
import java.math.BigDecimal;

import org.springframework.stereotype.Component;

/**
 * 一些图片信息常用的通用常量池
 * @author qdt_PC
 */
@Component("ImagesUrl")
public class ImagesUrl{
	
	/**头像路径*/
	//public final static String PortraitUrl="image".concat(File.separator).concat("portrait");//File.separator;
	
	/**头像路径*/
	public final static String CustomerPortraitUrl="custmer/images/portrait/";
	
	/**店家图片路径*/
	public final static String RestaurantPortraitUrl="restaurant/images/portrait/";
	
	/**菜肴图片路径*/
	public final static String DishPortraitUrl="restaurant/dish/images/portrait/";
	
	
	
	
	/**路径数据库*/
	//public final static String PortraitSQLUrl="image/portrait/";//File.separator;
	
	/*
	public final static String customerPortraitSqlUrl="image".concat(File.separator);
	///**菜肴图片路径
	public final static String dishImageUrl=File.separator;
	///**店家图片路径
	public final static String restaurantImageUrl=File.separator;
	
	*/
}
