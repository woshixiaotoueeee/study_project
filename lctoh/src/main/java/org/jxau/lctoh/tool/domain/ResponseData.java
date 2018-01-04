package org.jxau.lctoh.tool.domain;

import org.apache.ibatis.type.Alias;
import org.jxau.lctoh.tool.config.ErrorMSG;
import org.jxau.lctoh.tool.config.SuccessMSG;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 响应前台传输数据
 * @author qdt_PC
 */
@Component
@Alias("ResponseData")
@Scope("prototype")
public class ResponseData {
	private Integer state;			//状态码
	private Object responseInfo;	//成功或失败所相应的详细信息
	
	public Object getResponseInfo() {
		return responseInfo;
	}
	
	public void setResponseInfo(Object responseInfo) {
		this.responseInfo = responseInfo;
	}
	
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "ResponseData [state=" + state + ", responseInfo="
				+ responseInfo + "]";
	}
	
	/**
	 * 响应成功
	 * @param obj
	 * @return ResponseData
	 */
	public ResponseData successInfo(Object obj){
		state=SuccessMSG.success;
		responseInfo=obj;
		return this;
	}
	/**
	 * 响应失败
	 * @param obj
	 * @return ResponseData
	 */
	public ResponseData failInfo(Object obj){
		state=ErrorMSG.fail;
		responseInfo=obj;
		return this;
	}
}
