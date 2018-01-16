package org.jxau.lctoh.tool.domain;

import org.apache.ibatis.type.Alias;
import org.jxau.lctoh.tool.Tools;
import org.jxau.lctoh.tool.config.error.ErrorMSG;
import org.jxau.lctoh.tool.config.successMSG.SuccessMSG;
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
	/**状态码*/
	private Integer state;
	/**成功或失败所相应的详细信息*/
	private Object responseInfo;
	
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
		state=SuccessMSG.successType;
		responseInfo=obj;
		return this;
	}
	/**
	 * 响应失败
	 * @param obj
	 * @return ResponseData
	 */
	public ResponseData failInfo(Object obj){
		state=ErrorMSG.failType;
		responseInfo=obj;
		return this;
	}
	
	public String toGsonString(){
		return Tools.ObjectToGsonString(this);
	}
}
