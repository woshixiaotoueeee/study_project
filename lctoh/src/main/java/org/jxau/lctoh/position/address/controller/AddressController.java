package org.jxau.lctoh.position.address.controller;


import javax.servlet.http.HttpSession;

import org.jxau.lctoh.position.address.domain.Address;
import org.jxau.lctoh.position.address.service.AddressService;
import org.jxau.lctoh.tool.Tools;
import org.jxau.lctoh.tool.base.controller.BaseController;
import org.jxau.lctoh.tool.config.ConversationMSG;
import org.jxau.lctoh.tool.config.EncodingConfig;
import org.jxau.lctoh.tool.config.ErrorMSG;
import org.jxau.lctoh.tool.config.SuccessMSG;
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
		/**
		 * 判断定位信息是否为空
		 * */
		if(addressId==null){
			responseData.failInfo(ErrorMSG.parameterIsNullError);
		}else{
			try{
				responseData.successInfo(addressService.findAddressByAddressId(addressId));
			}catch(Exception e){
				responseData.failInfo(ErrorMSG.notKnowError);
			}
			
		}
		return Tools.gson.toJson(responseData);
	}
	
	/**
	 * 根据客户识别码查询地址信息
	 * @param customerId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/findAddressByAddressId",produces=EncodingConfig.produces)
	public String findAddressByCustomerId(String customerId){
		if(customerId==null){
			responseData.failInfo(ErrorMSG.parameterIsNullError);
		}else{
			try{
				responseData.successInfo(addressService.findAddressByCustomerId(customerId));
			}catch(Exception e){
				responseData.failInfo(ErrorMSG.notKnowError);
			}
			
		}
		return Tools.gson.toJson(responseData);
	}
	
	
	/**
	 * 更新地址信息
	 * @param adrdess
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateAddress",produces=EncodingConfig.produces)
	public String updateAddress(Address address,HttpSession session){
		if(address==null||address.getAddressId()==null||address.getAddressCity()==null||
				address.getAddressName()==null||address.getAddressProvince()==null||
				address.getAddressInfo()==null||address.getAddressPhone()==null||
				address.getAddressLatitude()==null||address.getAddressLongitude()==null){
			responseData.failInfo(ErrorMSG.parameterIsNullError);
		}
		Customer customer=(Customer) session.getAttribute(ConversationMSG.customerSession);
		if(customer==null){
			responseData.failInfo(ErrorMSG.loginTimerOut);
		}else{
			address.setAddressCustomer(customer);
			try{
				addressService.updateAddress(address);
				responseData.successInfo(SuccessMSG.successMSG);
			}catch(Exception e){
				responseData.successInfo(ErrorMSG.notKnowError);
			}
		}
		return Tools.gson.toJson(responseData);
	}
	
	
	/**
	 * 更新默认地址地址信息
	 * @param adrdess
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateAddressState",produces=EncodingConfig.produces)
	public String updateAddressState(String addressId,HttpSession session){
		if(addressId==null){
			responseData.failInfo(ErrorMSG.parameterIsNullError);
		}else{
			Customer customer=(Customer) session.getAttribute(ConversationMSG.customerSession);
			if(customer==null){
				responseData.failInfo(ErrorMSG.loginTimerOut);
			}else{
				try{
					addressService.updateAddressState(customer,addressId);
					responseData.successInfo(SuccessMSG.successMSG);
				}catch(Exception e){
					responseData.failInfo(ErrorMSG.notKnowError);
				}
			}
		}
		return Tools.gson.toJson(responseData);
	}
	
	
	
	
	
	/**
	 * 添加一个地址
	 * @param adrdess
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateAddress",produces=EncodingConfig.produces)
	public String addAddress(Address address,HttpSession session){
		if(address==null||address.getAddressCity()==null||
				address.getAddressName()==null||address.getAddressProvince()==null||
				address.getAddressInfo()==null||address.getAddressPhone()==null||
				address.getAddressLatitude()==null||address.getAddressLongitude()==null){
			responseData.failInfo(ErrorMSG.parameterIsNullError);
		}
		Customer customer=(Customer) session.getAttribute(ConversationMSG.customerSession);
		if(customer==null){
			responseData.failInfo(ErrorMSG.loginTimerOut);
		}else{
			address.setAddressCustomer(customer);
			address.setAddressId(Tools.getRandomString(32));
			try{
				addressService.addAddress(address);
				responseData.successInfo(SuccessMSG.successMSG);
			}catch(Exception e){
				responseData.successInfo(ErrorMSG.notKnowError);
			}
		}
		return Tools.gson.toJson(responseData);
	}
	
	
	/**
	 * 删除一个地址
	 * @param adrdess
	 * @return Integer
	 */
	@ResponseBody
	@RequestMapping(value="/deleteAddress",produces=EncodingConfig.produces)
	public String deleteAddress(String addressId){
		if(addressId==null){
			responseData.failInfo(ErrorMSG.parameterIsNullError);
		}else{
			try{
				addressService.deleteAddress(addressId);
				responseData.successInfo(SuccessMSG.successMSG);
			}catch(Exception e){
				responseData.failInfo(ErrorMSG.notKnowError);
			}
		}
		return Tools.gson.toJson(responseData);
	}
}
