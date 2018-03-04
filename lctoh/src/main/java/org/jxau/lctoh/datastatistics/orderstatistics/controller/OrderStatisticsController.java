package org.jxau.lctoh.datastatistics.orderstatistics.controller;


import org.jxau.lctoh.tool.base.controller.BaseController;
import org.jxau.lctoh.tool.config.charEncoding.EncodingConfig;
import org.jxau.lctoh.tool.config.error.ErrorMSG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.jxau.lctoh.datastatistics.orderstatistics.daomain.OrderStatisticsQureyModel;
import org.jxau.lctoh.datastatistics.orderstatistics.service.OrderStatisticsService;
/**
 * @author qdt_PC
 */
@Controller
@RequestMapping("/OrderStatisticsController")
public class OrderStatisticsController extends BaseController{
	@Autowired
	private OrderStatisticsService orderStatisticsService;
	
	
	/**
	 * @param orderStatisticsQureyModel
	 * <pre>
	 * orderStatisticsQureyModel 说明
	 * orderStatisticsQureyModel{
	 * 	stm Date 统计开始时间
	 * 	etm Date 统计结束时间
	 * 	customerId String 统计对象客户
	 * 	restaurantId String 统计对象店家
	 * }
	 * </pre>
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/orderStatistics",produces=EncodingConfig.produces)
	public String orderStatistics(OrderStatisticsQureyModel orderStatisticsQureyModel){
		try{
			responseData.successInfo(orderStatisticsService.orderStatistics(orderStatisticsQureyModel));
		}catch(Exception e){
			responseData.failInfo(ErrorMSG.selectFail);
		}
		return toGsonString();
	}
	
	
	
}
