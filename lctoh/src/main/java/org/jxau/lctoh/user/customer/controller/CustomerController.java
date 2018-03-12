package org.jxau.lctoh.user.customer.controller;


import java.io.File;


import javax.servlet.http.HttpSession;

import org.jxau.lctoh.tool.base.controller.BaseController;
import org.jxau.lctoh.tool.config.charEncoding.EncodingConfig;
import org.jxau.lctoh.tool.config.conversation.ConversationConfig;
import org.jxau.lctoh.tool.config.error.ErrorMSG;
import org.jxau.lctoh.tool.config.imageurl.ImagesUrl;
import org.jxau.lctoh.tool.config.successMSG.SuccessMSG;
import org.jxau.lctoh.user.basis.domain.User;
import org.jxau.lctoh.user.basis.exception.UserException;
import org.jxau.lctoh.user.customer.domain.Customer;
import org.jxau.lctoh.user.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
	 * 		Object responseInfo;	//成功：为  Customer类型对象具体属性参考 Customer实体类
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
		}catch (Exception e) {
			e.printStackTrace();
			responseData.failInfo(ErrorMSG.notKnow);
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
	 * 		Object responseInfo;	//成功：为成功的信息 String 字符串
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
				responseData.successInfo(SuccessMSG.updateSuccessMSG);
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
	 * 更新头像
	 * @param file 文件
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：为成功的信息 String 字符串
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 * @see org.jxau.lctoh.user.customer.domain.Customer
	 */
	@ResponseBody
	@RequestMapping(value="/updateCustomerPortrait",produces=EncodingConfig.produces)
	public String updateCustomerPortrait(@RequestParam("file") MultipartFile file, HttpSession session){
		String xpath = session.getServletContext().getRealPath(File.separator).concat(ImagesUrl.customerPortraitUrl);
		try {
			Customer customer=getCustomer(session);
			String fileName = file.getOriginalFilename();
	        //后缀判断
	        if (!fileName.endsWith(".jpg") &&! fileName.endsWith(".jpeg")    
	              &&! fileName.endsWith(".bmp")    
	                &&! fileName.endsWith(".gif")    
	                &&! fileName.endsWith(".png")
	                &&!fileName.endsWith(".JPG") 
	                &&! fileName.endsWith(".JPEG")    
	                &&! fileName.endsWith(".BMP")    
	                &&! fileName.endsWith(".GIF")    
	                &&! fileName.endsWith(".PNG")){
	        	responseData.failInfo(ErrorMSG.fileFormatError);
	        } else{
	        	//获取后缀
		        String prefix=fileName.substring(fileName.lastIndexOf(".")+1);    
		        //新文件名
		        String imagename=session.getId()+ System.currentTimeMillis()+"."+prefix;
		        //数据库路径
		        String sqlurl=ImagesUrl.customerPortraitSqlUrl.concat(imagename);
		        File targetFile = new File(xpath, imagename);
		        if (!targetFile.exists()) {
		            targetFile.mkdirs();
		        }
		        // 保存
		        try {
		            file.transferTo(targetFile);
		            customer.setCustomerPortrait(sqlurl);
		            customerService.updateCustomer(customer);
		            session.setAttribute(ConversationConfig.customerSession, customer);
		            responseData.successInfo(SuccessMSG.updateSuccessMSG);
		        }catch (UserException e) {
		        	e.printStackTrace();
		        	responseData.failInfo(e.getMessage());
		        }catch (Exception e) {
		        	e.printStackTrace();
		        	responseData.failInfo(ErrorMSG.updateFail);
		        }
	        }
		} catch (UserException e) {
			e.printStackTrace();
			responseData.failInfo(e.getMessage());
		}
		catch (Exception e) {
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
