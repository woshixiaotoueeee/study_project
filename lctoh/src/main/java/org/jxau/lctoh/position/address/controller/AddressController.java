package org.jxau.lctoh.position.address.controller;


import javax.servlet.http.HttpSession;

import org.jxau.lctoh.position.address.domain.Address;
import org.jxau.lctoh.position.address.service.AddressService;
import org.jxau.lctoh.tool.Tools;
import org.jxau.lctoh.tool.base.controller.BaseController;
import org.jxau.lctoh.tool.config.charEncoding.EncodingConfig;
import org.jxau.lctoh.tool.config.conversation.ConversationConfig;
import org.jxau.lctoh.tool.config.error.ErrorMSG;
import org.jxau.lctoh.tool.config.successMSG.SuccessMSG;
import org.jxau.lctoh.user.customer.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author qdt_PC
 */
@Controller
@RequestMapping("/AddressController") 
public class AddressController extends BaseController{
	@Autowired
	private AddressService addressService;
	
	/**
	 * 根据地址识别码查询地址信息
	 * @param addressId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/findAddressByAddressId",produces=EncodingConfig.produces)
	public String findAddressByAddressId(String addressId){
		if(addressId==null){			//验证前台信息
			responseData.failInfo(ErrorMSG.notKnow);
		}else{							//进行业务查询
			try{
				responseData.successInfo(addressService.findAddressByAddressId(addressId));
			}catch(Exception e){
				responseData.failInfo(ErrorMSG.selectFail);
			}
		}
		return toGsonString();
	}
	
	/**
	 * 根据客户识别码查询地址信息
	 * @param customerId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/findAddressByCustomerId",produces=EncodingConfig.produces)
	public String findAddressByCustomerId(String customerId){
		if(customerId==null){			//验证前台信息
			responseData.failInfo(ErrorMSG.notKnow);
		}else{							//进行业务查询
			try{
				responseData.successInfo(addressService.findAddressByCustomerId(customerId));
			}catch(Exception e){
				responseData.failInfo(ErrorMSG.selectFail);
			}
		}
		return toGsonString();
	}
	
	/**
	 * 更新地址信息
	 * @param adrdess
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateAddress",produces=EncodingConfig.produces)
	public String updateAddress(Address address,HttpSession session){
		//验证前台信息
		if(address==null||address.getAddressId()==null||
				address.getAddressLatitude()==null||address.getAddressLongitude()==null
				){
			responseData.failInfo(ErrorMSG.notKnow);
		}else if(address.getAddressCity()==null||address.getAddressProvince()==null){
			responseData.failInfo(ErrorMSG.provinceAndCityIsNull);
		}else if(address.getAddressName()==null||address.getAddressPhone()==null){
			responseData.failInfo(ErrorMSG.addressNameOrPhoneIsNull);
		}else{
			//验证部分后台信息
			Customer customer=(Customer) session.getAttribute(ConversationConfig.customerSession);
			if(customer==null){
				responseData.failInfo(ErrorMSG.loginTimerOut);
			}else{
				//更新
				address.setAddressCustomer(customer);
				try{
					addressService.updateAddress(address);
					responseData.successInfo(SuccessMSG.updateSuccessMSG);
				}catch(Exception e){
					responseData.failInfo(ErrorMSG.updateFail);
				}
			}
		}
		return toGsonString();
	}
	
	/**
	 * 更新默认地址地址状态信息
	 * @param adrdess
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateAddressState",produces=EncodingConfig.produces)
	public String updateAddressState(String addressId,HttpSession session){
		if(addressId==null){//验证前台信息
			responseData.failInfo(ErrorMSG.notKnow);
		}else{//验证后台台信息
			Customer customer=(Customer) session.getAttribute(ConversationConfig.customerSession);
			if(customer==null){
				responseData.failInfo(ErrorMSG.loginTimerOut);
			}else{//业务操作
				try{
					addressService.updateAddressState(customer,addressId);
					responseData.successInfo(SuccessMSG.successMSG);
				}catch(Exception e){
					responseData.failInfo(ErrorMSG.updateFail);
				}
			}
		}
		return toGsonString();
	}
	
	/**
	 * 添加一个地址
	 * @param adrdess
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addAddress",produces=EncodingConfig.produces)
	public String addAddress(Address address,HttpSession session){
		//验证前台信息
		if(address==null||address.getAddressId()==null||
				address.getAddressLatitude()==null||address.getAddressLongitude()==null
				){
			responseData.failInfo(ErrorMSG.notKnow);
		}else if(address.getAddressCity()==null||address.getAddressProvince()==null){
			responseData.failInfo(ErrorMSG.provinceAndCityIsNull);
		}else if(address.getAddressName()==null||address.getAddressPhone()==null){
			responseData.failInfo(ErrorMSG.addressNameOrPhoneIsNull);
		}
		else{
			//验证部分后台信息
			Customer customer=(Customer) session.getAttribute(ConversationConfig.customerSession);
			if(customer==null){
				responseData.failInfo(ErrorMSG.loginTimerOut);
			}else{
				//添加
				address.setAddressCustomer(customer);
				address.setAddressId(Tools.getRandomString(32));
				try{
					addressService.addAddress(address);
					responseData.successInfo(SuccessMSG.addSuccessMSG);
				}catch(Exception e){
					responseData.failInfo(ErrorMSG.addFail);
				}
			}
		}
		return toGsonString();
	}
	
	
	/**
	 * 删除一个地址
	 * @param adrdess
	 * @return Integer
	 */
	@ResponseBody
	@RequestMapping(value="/deleteAddress",produces=EncodingConfig.produces)
	public String deleteAddress(String addressId){
		if(addressId==null){//验证前台信息
			responseData.failInfo(ErrorMSG.notKnow);
		}else{//业务操作 删除
			try{
				addressService.deleteAddress(addressId);
				responseData.successInfo(SuccessMSG.deleteSuccessMSG);
			}catch(Exception e){
				responseData.failInfo(ErrorMSG.deleteFail);
			}
		}
		return toGsonString();
	}
}
