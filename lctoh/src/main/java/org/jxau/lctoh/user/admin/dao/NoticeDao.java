package org.jxau.lctoh.user.admin.dao;

import java.util.Date;
import java.util.List;

import org.jxau.lctoh.tool.base.dao.BaseDao;
import org.jxau.lctoh.user.admin.domain.Notice;
import org.jxau.lctoh.user.admin.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
/**
 * @author qdt_PC
 */
@Repository("NoticeDao")
public class NoticeDao extends BaseDao {
	@Autowired
	private NoticeMapper noticeMapper;
	@Override
	public void puttMapper() {
		noticeMapper=this.getMapper(NoticeMapper.class);
	}
	
	public NoticeMapper getNoticeMapper() {
		return noticeMapper;
	}

	public void setNoticeMapper(NoticeMapper noticeMapper) {
		this.noticeMapper = noticeMapper;
	}

	/**
	 * 根据公告识别码查询公告信息
	 * @param noticeId
	 * @return
	 */
	public Notice findNoticeByNoticeId(String noticeId){
		return noticeMapper.findNoticeByNoticeId(noticeId);
	}
	/**
	 * 根据公告对应状态查询公告信息（查询不同人看的公告）
	 * @param stateId
	 * @return
	 */
	public List<Notice> findNoticeByStateId(Integer stateId){
		return noticeMapper.findNoticeByStateId(stateId);
	}
	/**
	 * 查询所有公告信息
	 * @return
	 */
	public List<Notice> findAllNotice(){
		return noticeMapper.findAllNotice();
	}
	
	/**
	 * 删除公告信息
	 * @return
	 */
	public Integer deleteNotice(Notice notice){
		notice.setNoticeUpdateTime(new Date());
		return noticeMapper.deleteNotice(notice);
	}
	/**
	 * 更新公告信息
	 * @return
	 */
	public Integer updateNotice(Notice notice){
		notice.setNoticeUpdateTime(new Date());
		return noticeMapper.updateNotice(notice);
	}
	/**
	 * 添加公告信息
	 * @return
	 */
	public Integer addNotice(Notice notice){
		notice.setNoticeUpdateTime(new Date());
		return noticeMapper.addNotice(notice);
	}
}
