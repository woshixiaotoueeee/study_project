package org.jxau.lctoh.tool.domain;

import org.apache.ibatis.type.Alias;
import org.jxau.lctoh.tool.config.ErrorMSG;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Alias("ResponseData")
@Scope("prototype")
public class ResponseData {
	private String state;
	private Object responseInfo;
	
	public Object getResponseInfo() {
		return responseInfo;
	}
	
	public void setResponseInfo(Object responseInfo) {
		this.responseInfo = responseInfo;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "ResponseData [state=" + state + ", responseInfo="
				+ responseInfo + "]";
	}
	public ResponseData successInfo(Object obj){
		state=ErrorMSG.success;
		responseInfo=obj;
		return this;
	}
	public ResponseData failInfo(Object obj){
		state=ErrorMSG.fail;
		responseInfo=obj;
		return this;
	}
}
