package org.jxau.lctoh.user.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jxau.lctoh.user.admin.domain.Notice;

/**
 * 公告类代理接口
 * @author qdt_PC
 */
public interface NoticeMapper {
	/**
	 * 根据公告识别码查询公告信息
	 * @param noticeId
	 * @return
	 */
	public Notice findNoticeByNoticeId(@Param("noticeId")String noticeId);
	/**
	 * 根据公告对应状态查询公告信息（查询不同人看的公告）
	 * @param stateId
	 * @return
	 */
	public Notice findNoticeByStateId(@Param("stateId")Integer stateId);
	/**
	 * 查询所有公告信息
	 * @return
	 */
	public List<Notice> findAllNotice();
	
	/**
	 * 删除公告信息
	 * @return
	 */
	public Integer deleteNotice(Notice notice);
	/**
	 * 更新公告信息
	 * @return
	 */
	public Integer updateNotice(Notice notice);
	/**
	 * 添加公告信息
	 * @return
	 */
	public Integer addNotice(Notice notice);
	
	
}
