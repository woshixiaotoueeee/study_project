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
	 * 	stm String 统计开始时间	格式：2011-12-31 00:00:00
	 * 	etm String 统计结束时间 格式：2011-12-31 00:00:00
	 * 	customerId String 统计对象客户
	 * 	restaurantId String 统计对象店家
	 * }
	 * </pre>
	 * @return
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：为  List&lt;OrderStatistics&gt; 类型对象具体属性参考 OrderStatistics实体类
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 * @see org.jxau.lctoh.datastatistics.orderstatistics.daomain.OrderStatistics
	 */
	@ResponseBody
	@RequestMapping(value="/orderStatistics",produces=EncodingConfig.produces)
	public String orderStatistics(OrderStatisticsQureyModel orderStatisticsQureyModel){
		try{
			responseData.successInfo(orderStatisticsService.orderStatistics(orderStatisticsQureyModel));
		}catch(Exception e){
			e.printStackTrace();
			responseData.failInfo(ErrorMSG.selectFail);
		}
		return toGsonString();
	}
	/**
	 * @param orderStatisticsQureyModel
	 * <pre>
	 * orderStatisticsQureyModel 说明
	 * orderStatisticsQureyModel{
	 * 	stm String 统计开始时间	格式：2011-12-31 00:00:00
	 * 	etm String 统计结束时间 格式：2011-12-31 00:00:00
	 * 	customerId String 统计对象客户
	 * 	restaurantId String 统计对象店家
	 * }
	 * </pre>
	 * @return
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：为  List&lt;OrderTypeCount&gt; 类型对象具体属性参考 OrderTypeCount实体类
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 * @see org.jxau.lctoh.datastatistics.orderstatistics.daomain.OrderTypeCount
	 */
	@ResponseBody
	@RequestMapping(value="/orderStatisticsByState",produces=EncodingConfig.produces)
	public String orderStatisticsByState(OrderStatisticsQureyModel orderStatisticsQureyModel){
		try{
			
			responseData.successInfo(orderStatisticsService.orderStatisticsByState(orderStatisticsQureyModel));
		}catch(Exception e){
			e.printStackTrace();
			responseData.failInfo(ErrorMSG.selectFail);
		}
		return toGsonString();
	}
	
	/**
	 * @param orderStatisticsQureyModel
	 * <pre>
	 * orderStatisticsQureyModel 说明
	 * orderStatisticsQureyModel{
	 * 	stm String 统计开始时间	格式：2011-12-31 00:00:00
	 * 	etm String 统计结束时间 格式：2011-12-31 00:00:00
	 * 	riderId String 统计对象骑手
	 * }
	 * </pre>
	 * @return
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：为  List&lt;OrderTypeCount&gt; 类型对象具体属性参考 OrderTypeCount实体类
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 * @see org.jxau.lctoh.datastatistics.orderstatistics.daomain.OrderTypeCount
	 */
	@ResponseBody
	@RequestMapping(value="/dispatchingStatisticsByState",produces=EncodingConfig.produces)
	public String dispatchingStatisticsByState(OrderStatisticsQureyModel orderStatisticsQureyModel){
		try{
			
			responseData.successInfo(orderStatisticsService.dispatchingStatisticsByState(orderStatisticsQureyModel));
		}catch(Exception e){
			e.printStackTrace();
			responseData.failInfo(ErrorMSG.selectFail);
		}
		return toGsonString();
	}
	
	
	
	/**
	 * @param orderStatisticsQureyModel
	 * <pre>
	 * orderStatisticsQureyModel 说明
	 * orderStatisticsQureyModel{
	 * 	stm 字符串 统计开始时间	格式：2011-12-31 00:00:00
	 * 	etm 字符串 统计结束时间 格式：2011-12-31 00:00:00
	 * 	riderId String 统计对象骑手
	 * }
	 * </pre>
	 * @return
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：为  List&lt;OrderStatistics&gt; 类型对象具体属性参考 OrderStatistics实体类
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 * @see org.jxau.lctoh.datastatistics.orderstatistics.daomain.OrderStatistics
	 * 
	 */
	@ResponseBody
	@RequestMapping(value="/dispatchingStatistics",produces=EncodingConfig.produces)
	public String dispatchingStatistics(OrderStatisticsQureyModel orderStatisticsQureyModel){
		try{
			responseData.successInfo(orderStatisticsService.dispatchingStatistics(orderStatisticsQureyModel));
		}catch(Exception e){
			e.printStackTrace();
			responseData.failInfo(ErrorMSG.selectFail);
		}
		return toGsonString();
	}
}
