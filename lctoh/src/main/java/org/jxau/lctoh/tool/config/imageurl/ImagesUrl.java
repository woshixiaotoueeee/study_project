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
	public final static String customerPortraitUrl="image".concat(File.separator);//File.separator;
	public final static String customerPortraitSqlUrl="image/";
	/**菜肴图片路径*/
	public final static String dishImageUrl=File.separator;
	/**店家图片路径*/
	public final static String restaurantImageUrl=File.separator;
	
	
}
