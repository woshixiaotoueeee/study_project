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
import org.jxau.lctoh.user.admin.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 公告操作接口
 * @author qdt_PC
 */
@Controller
@RequestMapping("/NoticeController")
public class NoticeController extends BaseController{
	@Autowired
	private NoticeService noticeService;
	
	/**
	 * 查询所有公告
	 * @return
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：为  List&lt;Notice&gt; 类型对象具体属性参考 Notice实体类
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 * @see  org.jxau.lctoh.user.admin.domain.Notice
	 */
	@ResponseBody
	@RequestMapping(value="/findAllNotice",produces=EncodingConfig.produces)
	public String findAllNotice(){
		
		try{
			responseData.successInfo(noticeService.findAllNotice());
		}catch(Exception e){
			e.printStackTrace();
			responseData.failInfo(ErrorMSG.selectFail);
		}
		return toGsonString();
	}
	/**
	 * 根据ID查询公告
	 * @param noticeId String 字符串  公告识别码
	 * @return
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：为Notice对象; 类型对象具体属性参考 Notice实体类
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 * @see  org.jxau.lctoh.user.admin.domain.Notice
	 */
	@ResponseBody
	@RequestMapping(value="/findNoticeByNoticeId",produces=EncodingConfig.produces)
	public String findNoticeByNoticeId(String noticeId){
		if(noticeId==null){
			responseData.failInfo(ErrorMSG.notKnow);
		}else{
			try{
				responseData.successInfo(noticeService.findNoticeByNoticeId(noticeId));
			}catch(Exception e){
				e.printStackTrace();
				responseData.failInfo(ErrorMSG.selectFail);
			}
		}
		
		return toGsonString();
	}
	/**
	 * 查询对应角色的公告
	 * @param stateId Integer 整数 公告角色状态码 120001:客户；120002 店家；120003 骑手
	 * @return
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：为  List&lt;Notice&gt; 类型对象具体属性参考 Notice实体类
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 * @see  org.jxau.lctoh.user.admin.domain.Notice
	 */
	@ResponseBody
	@RequestMapping(value="/findNoticeByStateId",produces=EncodingConfig.produces)
	public String findNoticeByStateId(Integer stateId){
		if(stateId==null){
			responseData.failInfo(ErrorMSG.notKnow);
		}else{
			try{
				responseData.successInfo(noticeService.findNoticeByStateId(stateId));
			}catch(Exception e){
				e.printStackTrace();
				responseData.failInfo(ErrorMSG.selectFail);
			}
		}
		return toGsonString();
	}

	/**
	 * 删除公告
	 * @param noticeId String 字符串  公告识别码
	 * @return
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：操作成功返回的信息  String 字符串
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 */
	@ResponseBody
	@RequestMapping(value="/deleteNotice",produces=EncodingConfig.produces)
	public String deleteNotice(String noticeId,HttpSession session){
		if(noticeId==null){
			responseData.failInfo(ErrorMSG.notKnow);
		}else{
			try{
				Admin admin=(Admin) session.getAttribute(ConversationConfig.adminSession);
				if(admin==null){
					responseData.failInfo(ErrorMSG.loginTimerOut);
				}else{
					noticeService.deleteNoticeByNoticeId(noticeId,admin);
					responseData.successInfo(SuccessMSG.deleteSuccessMSG);
				}
			}catch(Exception e){
				e.getMessage();
				responseData.failInfo(ErrorMSG.deleteFail);
			}
		}
		return toGsonString();
	}
	
	
	/**
	 * 更新公告
	 * @param notice Notice
	 * <pre>
	 * 	notice中需要的参数说明：{
	 * 		noticeId String 公告ID
	 * 		noticeTitle String 公告标题
	 * 		notiveDetails String 公告内容
	 * 		stateID Integer 对应角色
	 * 	}
	 * </pre> 
	 * @return
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：操作成功返回的信息  String 字符串
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 */
	@ResponseBody
	@RequestMapping(value="/updateNotice",produces=EncodingConfig.produces)
	public String updateNotice(Notice notice,HttpSession session){
		Admin admin=(Admin) session.getAttribute(ConversationConfig.adminSession);
		if(admin==null){
			responseData.failInfo(ErrorMSG.loginTimerOut);
		}else{
			if(notice==null||notice.getNoticeId()==null){
				responseData.failInfo(ErrorMSG.notKnow);
			}else if(notice.getNoticeTitle()==null||notice.getNotiveDetails()==null||notice.getNoticeState()==null){
				responseData.failInfo(ErrorMSG.parameterIsNull);
			}else{
				try{
					notice.setNoticeAdmin(admin);
					noticeService.updateNotice(notice);
					responseData.successInfo(SuccessMSG.updateSuccessMSG);
				}catch(Exception e){
					e.getMessage();
					responseData.failInfo(ErrorMSG.updateFail);
				}
			}
		}
		return toGsonString();
	}
	

	/**
	 * 添加公告
	 * @param notice Notice
	 * <pre>
	 * 	notice中需要的参数说明：{
	 * 		noticeTitle String 公告标题
	 * 		notiveDetails String 公告内容
	 * 		stateID Integer 对应角色
	 * 	}
	 * </pre> 
	 * @return
	 * <pre>
	 * json字符串{
	 * 	说明：{
	 * 		Integer state;			//状态码（整形数字）
	 * 		Object responseInfo;	//成功：操作成功返回的信息  String 字符串
	 *  							//失败：为失败原因的信息 String 字符串
	 * 	}
	 * }
	 * </pre>
	 */
	@ResponseBody
	@RequestMapping(value="/addNotice",produces=EncodingConfig.produces)
	public String addNotice(Notice notice,HttpSession session){
		Admin admin=(Admin) session.getAttribute(ConversationConfig.adminSession);
		if(admin==null){
			responseData.failInfo(ErrorMSG.loginTimerOut);
		}else{
			if(notice.getNoticeTitle()==null||notice.getNotiveDetails()==null||notice.getNoticeState()==null){
				responseData.failInfo(ErrorMSG.parameterIsNull);
			}else{
				try{
					notice.setNoticeId(Tools.getRandomString(32));
					notice.setNoticeAdmin(admin);
					noticeService.addNotice(notice);
					responseData.successInfo(SuccessMSG.addSuccessMSG);
				}catch(Exception e){
					e.getMessage();
					responseData.failInfo(ErrorMSG.addFail);
				}
			}
		}
		return toGsonString();
	}
	
}
