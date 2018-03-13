package org.jxau.lctoh.user.rider.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jxau.lctoh.user.rider.domain.Rider;

/**
 * 管理员类代理接口
 * @author qdt_PC
 */
public interface RiderMapper {

	/**
	 * 根据用户识别码查询骑手信息
	 * @param userId  用户识别码
	 * @return Rider 骑手
	 */
	public Rider findRiderByUserId(@Param("userId")String userId);
	
	
	/**
	 * 根据骑手识别码查询骑手信息
	 * @param riderId  骑手识别码
	 * @return Rider 骑手
	 */
	public Rider findRiderByRiderId(@Param("riderId")String riderId);
	
	/**
	 * 根据管理员状态码查询骑手信息
	 * @param riderStateId  骑手状态码
	 * @return List<Rider> 骑手
	 */
	public List<Rider> findRiderByRiderStateId(@Param("riderStateId")Integer riderStateId);


	public Integer updateRider(Rider rider);
	
	
	
}
