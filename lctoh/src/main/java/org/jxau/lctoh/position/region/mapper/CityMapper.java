package org.jxau.lctoh.position.region.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jxau.lctoh.position.region.domain.City;
import org.jxau.lctoh.position.region.domain.Province;

public interface CityMapper {
	/**
	 * 根据城市识别码查找省份
	 * @param cityId
	 * @return City  
	 */
	public City findCityByCityId(@Param("cityId")String cityId);
	
	/**
	 * 根据城市名查找省份
	 * @param cityName
	 * @return List<City>  
	 */
	public List<City> findCityByCityName(@Param("cityName")String cityName);
	
	/**
	 * 查找所有城市
	 * @return List<City>  
	 */
	public List<City> findCityAll();
	
	/**
	 * 根据状态码查找城市
	 * @param cityStateId
	 * @return Province  
	 */
	public List<City> findCityByCityStateId(@Param("cityStateId")Integer cityStateId);
	
	
}
