package org.jxau.lctoh.user.admin.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.jxau.lctoh.state.domain.State;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 公告类
 * @author qdt_PC
 */
@Component
@Alias("Notice")
@Scope("prototype")
public class Notice {
	/**公告识别码*/
	private String noticeId;
	/**
	 * 公告发布人
	 * @see org.jxau.lctoh.user.admin.domain.Admin
	 * */
	private Admin noticeAdmin;
	/**公告标题*/
	private String noticeTitle;
	/**公告内容*/
	private String notiveDetails;
	/**公告更新时间*/
	private Date noticeUpdateTime;
	/**
	 * 公告状态
	 * @see org.jxau.lctoh.state.domain.State
	 * */
	private State noticeState;
	public String getNoticeId() {
		return noticeId;
	}
	public Admin getNoticeAdmin() {
		return noticeAdmin;
	}
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public String getNotiveDetails() {
		return notiveDetails;
	}
	public Date getNoticeUpdateTime() {
		return noticeUpdateTime;
	}
	public State getNoticeState() {
		return noticeState;
	}
	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}
	public void setNoticeAdmin(Admin noticeAdmin) {
		this.noticeAdmin = noticeAdmin;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public void setNotiveDetails(String notiveDetails) {
		this.notiveDetails = notiveDetails;
	}
	public void setNoticeUpdateTime(Date noticeUpdateTime) {
		this.noticeUpdateTime = noticeUpdateTime;
	}
	public void setNoticeState(State noticeState) {
		this.noticeState = noticeState;
	}
	@Override
	public String toString() {
		return "Notice [noticeId=" + noticeId + ", noticeAdmin=" + noticeAdmin
				+ ", noticeTitle=" + noticeTitle + ", notiveDetails="
				+ notiveDetails + ", noticeUpdateTime=" + noticeUpdateTime
				+ ", noticeState=" + noticeState + "]";
	}

}
