package org.jxau.lctoh.position.region.dao;

import java.util.List;


import org.jxau.lctoh.position.region.domain.City;
import org.jxau.lctoh.position.region.mapper.CityMapper;
import org.jxau.lctoh.tool.base.dao.BaseDao;
import org.springframework.stereotype.Repository;
/**
 * @author qdt_PC
 */
@Repository("CityDao")
public class CityDao extends BaseDao {
	
	private CityMapper cityMapper;
	
	public CityMapper getCityMapper() {
		return cityMapper;
	}
	public void setCityMapper(CityMapper cityMapper) {
		this.cityMapper = cityMapper;
	}
	@Override
	public void puttMapper() {
		cityMapper=this.getMapper(CityMapper.class);
	}
	
	
	/**
	 * 根据城市识别码查找城市
	 * @param cityId
	 * @return City  
	 */
	public City findCityByCityId(String cityId){
		return cityMapper.findCityByCityId(cityId);
	}
	
	/**
	 * 根据城市名查找城市
	 * @param cityName
	 * @return List<City>  
	 */
	public List<City> findCityByCityName(String cityName){
		return cityMapper.findCityByCityName(cityName);
	}
	
	
	/**
	 * 查找所有城市
	 * @return List<City>  
	 */
	public List<City> findCityAll(){
		return cityMapper.findCityAll();
	}
	
	/**
	 * 根据状态码查找城市
	 * @param cityStateId
	 * @return List<City>  
	 */
	public List<City> findCityByCityStateId(Integer cityStateId){
		return cityMapper.findCityByCityStateId(cityStateId);
	}
	
	/**
	 * 根据省份识别码查找城市
	 * @param provinceId
	 * @return List<City>  
	 */
	public List<City> findCityByProvinceId(String provinceId){
		return cityMapper.findCityByProvinceId(provinceId);
	}
	
	
}
