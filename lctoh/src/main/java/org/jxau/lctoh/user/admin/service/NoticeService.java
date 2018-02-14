package org.jxau.lctoh.user.admin.service;

import java.util.List;

import org.jxau.lctoh.user.admin.dao.NoticeDao;
import org.jxau.lctoh.user.admin.domain.Admin;
import org.jxau.lctoh.user.admin.domain.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @author qdt_PC
 */
@Service("NoticeService")
public class NoticeService {
	@Autowired
	private NoticeDao noticeDao;

	/**
	 * 根据公告识别码查询公告信息
	 * @param noticeId
	 * @return
	 */
	public Notice findNoticeByNoticeId(String noticeId){
		return noticeDao.findNoticeByNoticeId(noticeId);
	}
	/**
	 * 根据公告对应状态查询公告信息（查询不同人看的公告）
	 * @param stateId
	 * @return
	 */
	public Notice findNoticeByStateId(Integer stateId){
		return noticeDao.findNoticeByStateId(stateId);
	}
	/**
	 * 查询所有公告信息
	 * @return
	 */
	public List<Notice> findAllNotice(){
		return noticeDao.findAllNotice();
	}
	
	/**
	 * 删除公告信息
	 * @return
	 * @throws Exception 
	 */
	public Integer deleteNoticeByNoticeId(String noticeId,Admin admin) throws Exception{
		Notice notice=noticeDao.findNoticeByNoticeId(noticeId);
		if(notice==null)throw new Exception();
		notice.setNoticeAdmin(admin);
		return noticeDao.deleteNotice(notice);
	}
	/**
	 * 更新公告信息
	 * @return
	 */
	public Integer updateNotice(Notice notice){
		return noticeDao.updateNotice(notice);
	}
	/**
	 * 添加公告信息
	 * @return
	 */
	public Integer addNotice(Notice notice){
		return noticeDao.addNotice(notice);
	}
	
}
