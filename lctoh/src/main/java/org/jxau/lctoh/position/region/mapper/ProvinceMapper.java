package org.jxau.lctoh.position.region.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jxau.lctoh.position.region.domain.Province;

public interface ProvinceMapper {
	
	/**
	 * 根据省份识别码查找省份
	 * @param provinceId
	 * @return Province  
	 */
	public Province findProvinceByProvinceId(@Param("provinceId")String provinceId);
	
	/**
	 * 根据省份名查找省份
	 * @param provinceName
	 * @return List<Province>  
	 */
	public List<Province> findProvinceByProvinceName(@Param("provinceName")String provinceName);
	
	/**
	 * 查找所有省份
	 * @return List<Province>  
	 */
	public List<Province> findProvinceAll();
	
	/**
	 * 根据状态码查找省份
	 * @param provinceStateId
	 * @return Province  
	 */
	public List<Province> findProvinceByProvinceStateId(@Param("provinceStateId")Integer provinceStateId);
	
	
	
	
}
