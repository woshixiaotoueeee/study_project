package org.jxau.lctoh.position.region.dao;

import java.util.List;





import org.jxau.lctoh.position.region.domain.Province;
import org.jxau.lctoh.position.region.mapper.CityMapper;
import org.jxau.lctoh.position.region.mapper.ProvinceMapper;
import org.jxau.lctoh.tool.base.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author qdt_PC
 */
@Repository("ProvinceDao")
public class ProvinceDao extends BaseDao {
	@Autowired
	private ProvinceMapper provinceMapper;
	@Autowired
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
		return loadProvinceCityList(province);
	}
	
	
	/**
	 * 为单个省份加载城市信息
	 * @param province 需要加载城市信息的省份
	 * @return Province
	 */
	private Province loadProvinceCityList(Province province) {
		province.setCityList(cityMapper.findCityByProvinceId(province.getProvinceId()));
		return province;
	}
	
	
	/**
	 * 为多个省份加载城市信息
	 * @param provinceList 需要加载城市信息的省份
	 * @return List<Province>
	 */
	private List<Province> loadProvinceListCityList(List<Province> provinceList) {
		for(int i=0;i<provinceList.size();i++){
			provinceList.set(i,loadProvinceCityList(provinceList.get(i)));
		}
		return provinceList;
	}
	/**
	 * 根据省份名查找省份
	 * @param provinceName
	 * @return List<Province>  
	 */
	public List<Province> findProvinceByProvinceName(String provinceName){
		List<Province> provinceList=provinceMapper.findProvinceByProvinceName(provinceName);
		if(provinceList==null)return provinceList;
		return loadProvinceListCityList(provinceList);
	}
	
	/**
	 * 查找所有省份
	 * @return List<Province>  
	 */
	public List<Province> findProvinceAll(){
		List<Province> provinceList=provinceMapper.findProvinceAll();
		if(provinceList==null)return provinceList;
		return loadProvinceListCityList(provinceList);
	}
	
	/**
	 * 根据状态码查找省份
	 * @param provinceStateId
	 * @return List<Province>  
	 */
	public List<Province> findProvinceByProvinceStateId(Integer provinceStateId){
		List<Province> provinceList=provinceMapper.findProvinceByProvinceStateId(provinceStateId);
		if(provinceList==null)return provinceList;
		return loadProvinceListCityList(provinceList);
	}
	
	

}
