package org.jxau.lctoh.position.region.dao;

import java.util.List;



import org.jxau.lctoh.position.region.domain.Province;
import org.jxau.lctoh.position.region.mapper.CityMapper;
import org.jxau.lctoh.position.region.mapper.ProvinceMapper;
import org.jxau.lctoh.tool.base.dao.BaseDao;
import org.springframework.stereotype.Repository;

@Repository("ProvinceDao")
public class ProvinceDao extends BaseDao {
	
	private ProvinceMapper provinceMapper;
	private CityMapper cityMapper;
	
	
	public ProvinceMapper getProvinceMapper() {
		return provinceMapper;
	}
	public void setProvinceMapper(ProvinceMapper provinceMapper) {
		this.provinceMapper = provinceMapper;
	}
	public CityMapper getCityMapper() {
		return cityMapper;
	}
	public void setCityMapper(CityMapper cityMapper) {
		this.cityMapper = cityMapper;
	}
	
	@Override
	public void puttMapper() {
		provinceMapper=this.getMapper(ProvinceMapper.class);
		cityMapper=this.getMapper(CityMapper.class);
	}
	
	
	/**
	 * 根据省份识别码查找省份
	 * @param provinceId
	 * @return Province  
	 */
	public Province findProvinceByProvinceId(String provinceId){
		Province province= provinceMapper.findProvinceByProvinceId(provinceId);
		if(province==null)return province;
		province.setCityList(cityMapper.findCityByProvinceId(province.getProvinceId()));
		return province;
	}
	
	/**
	 * 根据省份名查找省份
	 * @param provinceName
	 * @return List<Province>  
	 */
	public List<Province> findProvinceByProvinceName(String provinceName){
		List<Province> provinceList=provinceMapper.findProvinceByProvinceName(provinceName);
		if(provinceList==null)return provinceList;
		for(int i=0;i<provinceList.size();i++){
			Province province=provinceList.get(i);
			province.setCityList(cityMapper.findCityByProvinceId(province.getProvinceId()));
		}
		return provinceList;
	}
	
	/**
	 * 查找所有省份
	 * @return List<Province>  
	 */
	public List<Province> findProvinceAll(){
		List<Province> provinceList=provinceMapper.findProvinceAll();
		if(provinceList==null)return provinceList;
		for(int i=0;i<provinceList.size();i++){
			Province province=provinceList.get(i);
			province.setCityList(cityMapper.findCityByProvinceId(province.getProvinceId()));
		}
		return provinceList;
	}
	
	/**
	 * 根据状态码查找省份
	 * @param provinceStateId
	 * @return Province  
	 */
	public List<Province> findProvinceByProvinceStateId(Integer provinceStateId){
		List<Province> provinceList=provinceMapper.findProvinceByProvinceStateId(provinceStateId);
		if(provinceList==null)return provinceList;
		for(int i=0;i<provinceList.size();i++){
			Province province=provinceList.get(i);
			province.setCityList(cityMapper.findCityByProvinceId(province.getProvinceId()));
		}
		return provinceList;
	}
	
	

}
