package org.jxau.lctoh.user.customer.controller;

import javax.servlet.http.HttpSession;

import org.jxau.lctoh.tool.base.controller.BaseController;
import org.jxau.lctoh.tool.config.charEncoding.EncodingConfig;
import org.jxau.lctoh.tool.config.conversation.ConversationConfig;
import org.jxau.lctoh.tool.config.error.ErrorMSG;
import org.jxau.lctoh.user.basis.domain.User;
import org.jxau.lctoh.user.basis.exception.UserException;
import org.jxau.lctoh.user.customer.domain.Customer;
import org.jxau.lctoh.user.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author qdt_PC
 */
@Controller
@RequestMapping("/CustomerController")
public class CustomerController  extends BaseController{
	@Autowired
	private CustomerService customerService;
	
	/**
	 * 获取登陆信息
	 * @return
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：为  Customer类型对象具体属性参考 Dish实体类
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 * @see org.jxau.lctoh.user.customer.domain.Customer
	 */
	@ResponseBody
	@RequestMapping(value="/getLoginInfo",produces=EncodingConfig.produces)
	public String getLoginInfo(HttpSession session){
		try {
			responseData.successInfo(getCustomer(session));
		} catch (UserException e) {
			e.printStackTrace();
			responseData.failInfo(e.getMessage());
		}
		return toGsonString();
	}
	
	/**
	 * 更新用户信息
	 * @param _user
	 * <pre>
	 * _user说明{
	 * 		userEmail String 字符串  邮箱
	 * 		userPhone String 字符串 联系方式
	 * 		userSex String 字符串 性别
	 * }
	 * </pre>
	 * @param nickName String 字符串 昵称
	 * @return
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：为  Customer类型对象具体属性参考 Dish实体类
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 * @see org.jxau.lctoh.user.customer.domain.Customer
	 */
	@ResponseBody
	@RequestMapping(value="/updateCustomer",produces=EncodingConfig.produces)
	public String updateCustomer(User _user,String nickName,HttpSession session){
		try {
			Customer customer=getCustomer(session);
			if(_user==null){
				responseData.failInfo(ErrorMSG.notKnow);
			}else if(_user.getUserEmail()==null){
				responseData.failInfo(ErrorMSG.emailIsNull);
			}else if(!_user.getUserEmail().matches("\\w+@\\w+\\.\\w+")){
				responseData.failInfo(ErrorMSG.emailFormatError);
			}else if(_user.getUserPhone()==null){
				responseData.failInfo(ErrorMSG.phoneIsNull);
			}else if(_user.getUserSex()==null){
				responseData.failInfo(ErrorMSG.sexIsNull);
			}else if(nickName==null){
				responseData.failInfo(ErrorMSG.nicknameIsNull);
			}else{
				User user =customer.getCustomerUser();
				user.setUserEmail(_user.getUserEmail());
				user.setUserPhone(_user.getUserPhone());
				user.setUserSex(_user.getUserSex());
				customer.setCustomerUser(user);
				customer.setCustomerNickname(nickName);
				customerService.updateCustomer(customer);
			}
			
		} catch (UserException e) {
			e.printStackTrace();
			responseData.failInfo(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			responseData.failInfo(ErrorMSG.notKnow);
		}
		return toGsonString();
	}
	
	
	
	
	
	
	/**
	 * 从session中获取客户登陆信息
	 * @param session
	 * @return
	 * @throws UserException
	 */
	private Customer getCustomer(HttpSession session) throws UserException{
		Customer customer=(Customer) session.getAttribute(ConversationConfig.customerSession);
		if(customer==null)throw new UserException(ErrorMSG.loginTimerOut);
		return customer;
	}
	
}
