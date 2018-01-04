package org.jxau.lctoh.position.region.service;

import java.util.List;

import org.jxau.lctoh.position.region.dao.ProvinceDao;
import org.jxau.lctoh.position.region.domain.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author qdt_PC
 */
@Service("ProvinceService")
public class ProvinceService {
	@Autowired
	private ProvinceDao provinceDao;
	
	
	/**
	 * 查找所有省份
	 * @return List<Province>  
	 */
	public List<Province> getAllProvince(){
		return provinceDao.findProvinceAll();
	}
	
}
