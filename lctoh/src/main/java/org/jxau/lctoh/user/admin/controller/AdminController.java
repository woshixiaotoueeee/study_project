package org.jxau.lctoh.user.admin.controller;




import javax.servlet.http.HttpSession;

import org.jxau.lctoh.tool.Tools;
import org.jxau.lctoh.tool.base.controller.BaseController;
import org.jxau.lctoh.tool.config.charEncoding.EncodingConfig;
import org.jxau.lctoh.tool.config.conversation.ConversationConfig;
import org.jxau.lctoh.tool.config.error.ErrorMSG;
import org.jxau.lctoh.tool.config.successMSG.SuccessMSG;
import org.jxau.lctoh.user.admin.domain.Admin;
import org.jxau.lctoh.user.admin.domain.Notice;
import org.jxau.lctoh.user.admin.domain.QueryWebModel;
import org.jxau.lctoh.user.admin.service.NoticeService;
import org.jxau.lctoh.user.basis.exception.UserException;
import org.jxau.lctoh.user.customer.service.CustomerService;
import org.jxau.lctoh.user.restaurant.service.RestaurantService;
import org.jxau.lctoh.user.rider.service.RiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author qdt_PC
 */
@Controller
@RequestMapping("/AdminController")
public class AdminController extends BaseController{
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private RestaurantService restaurantService;
	@Autowired
	private RiderService riderService;
	/**
	 * 查询客户信息
	 * @return
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：成功：为  List&lt;Customer&gt; 类型对象具体属性参考 Restaurant实体类
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 * @see org.jxau.lctoh.user.customer.domain.Customer
	 */
	@ResponseBody
	@RequestMapping(value="/getCustomer",produces=EncodingConfig.produces)
	public String getCustomer(QueryWebModel qwm,HttpSession session){
		Admin admin=(Admin) session.getAttribute(ConversationConfig.adminSession);
		if(admin==null){
			responseData.failInfo(ErrorMSG.loginTimerOut);
		}else{
			customerService.getCustomer(qwm);
		}
		return toGsonString();
	}
	
	
	
	/**
	 * 封号
	 * 
	 * @param String id 要更新状态的用户ID,Integer type 整数  1:客户；2 店家；3 骑手
	 * 
	 * @return
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：为操作成功的信息 String 字符串
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 */
	@ResponseBody
	@RequestMapping(value="/lockAccount",produces=EncodingConfig.produces)
	public String lockAccount(String id,Integer type,HttpSession session){
		Admin admin=(Admin) session.getAttribute(ConversationConfig.adminSession);
		if(admin==null){
			responseData.failInfo(ErrorMSG.loginTimerOut);
		}else{
			try{
				switch(type){
					case 1:
						customerService.lockAccount(id,20003);
						responseData.successInfo(SuccessMSG.updateSuccessMSG);
						break;
					case 2:
						restaurantService.lockAccount(id,70001);
						responseData.successInfo(SuccessMSG.updateSuccessMSG);
						break;
					case 3:
						restaurantService.lockAccount(id,130003);
						responseData.successInfo(SuccessMSG.updateSuccessMSG);
						break;
					default : responseData.failInfo(ErrorMSG.notKnowUser);
				}
			}catch(UserException e){
				e.printStackTrace();
				responseData.failInfo(e.getMessage());
			}catch(Exception e){
				e.printStackTrace();
				responseData.failInfo(ErrorMSG.updateFail);
			}
		}
		return toGsonString();
	}
	/**
	 * 解封
	 * 
	 * @param String id 要更新状态的用户ID,Integer type 整数  120001:客户；120002 店家；120003 骑手
	 * 
	 * @return
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：为操作成功的信息 String 字符串
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 */
	@ResponseBody
	@RequestMapping(value="/unlockAccount",produces=EncodingConfig.produces)
	public String unlockAccount(String id,Integer state,Integer type,HttpSession session){
		Admin admin=(Admin) session.getAttribute(ConversationConfig.adminSession);
		if(admin==null){
			responseData.failInfo(ErrorMSG.loginTimerOut);
		}else{
			
		}
		return toGsonString();
	}
	
}
