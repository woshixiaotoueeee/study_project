package org.jxau.lctoh.user.restaurant.service;

import java.math.BigDecimal;
import java.util.List;

import org.jxau.lctoh.position.location.domain.Location;
import org.jxau.lctoh.position.region.dao.CityDao;
import org.jxau.lctoh.position.region.domain.City;
import org.jxau.lctoh.state.domain.State;
import org.jxau.lctoh.tool.Tools;
import org.jxau.lctoh.tool.config.error.ErrorMSG;
import org.jxau.lctoh.tool.config.successMSG.SuccessMSG;
import org.jxau.lctoh.user.basis.dao.UserDao;
import org.jxau.lctoh.user.basis.dao.VerificationCodeDao;
import org.jxau.lctoh.user.basis.domain.User;
import org.jxau.lctoh.user.basis.domain.VerificationCode;
import org.jxau.lctoh.user.basis.exception.UserException;
import org.jxau.lctoh.user.customer.domain.Customer;
import org.jxau.lctoh.user.restaurant.dao.CollectRestaurantDao;
import org.jxau.lctoh.user.restaurant.dao.RestaurantCategoryDao;
import org.jxau.lctoh.user.restaurant.dao.RestaurantDao;
import org.jxau.lctoh.user.restaurant.domain.CollectRestaurant;
import org.jxau.lctoh.user.restaurant.domain.Restaurant;
import org.jxau.lctoh.user.restaurant.domain.RestaurantCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("RestaurantService")
public class RestaurantService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private RestaurantDao restaurantDao;
	@Autowired
	private VerificationCodeDao verificationCodeDao;
	@Autowired
	private CityDao cityDao;
	@Autowired
	private CollectRestaurantDao collectRestaurantDao;
	@Autowired
	private RestaurantCategoryDao restaurantCategoryDao;
	
	
	
	/**
	 * 管理员登陆
	 * @param user  用户
	 * @return Restaurant 店家
	 * @throws UserException
	 */
	public Restaurant login(User user) throws UserException{
		
		User _user=userDao.findUserByUserAccount(user.getUserAccount());
		if(_user==null) throw new UserException(ErrorMSG.accountInexistence);
		if(!(_user.getUserPassword().equals(user.getUserPassword())))
			throw new UserException(ErrorMSG.passwordError);
		Restaurant restaurant=restaurantDao.findRestaurantByUserId(_user.getUserId());
		if(restaurant==null)throw new UserException(ErrorMSG.noPower);
		
		/**后期可能需要修改*/
		if(restaurant.getRestaurantState().getStateId()==70001)
			throw new UserException(ErrorMSG.loginStateError);
		return restaurant;
		
	}
	
	
	/**
	 * 管理员登陆
	 * @param user  用户
	 * @return Restaurant 店家
	 * @throws UserException
	 */
	public Restaurant loginByCode(String userAccount,String code) throws UserException{
		
		User _user=userDao.findUserByUserAccount(userAccount);
		if(_user==null) throw new UserException(ErrorMSG.accountInexistence);
		VerificationCode verificationCode=verificationCodeDao.findVerificationCodeById(_user.getUserId());
		if(verificationCode==null||verificationCode.getVerificationCode().equals(code))throw new UserException(ErrorMSG.codeError);
		if(Tools.getTimeDifferenceFromNowDate(verificationCode.getVerificationCodeUpdateTime())>SuccessMSG.timeExpire)
			throw new UserException(ErrorMSG.codeTimeExpire);
		Restaurant restaurant=restaurantDao.findRestaurantByUserId(_user.getUserId());
		if(restaurant==null)throw new UserException(ErrorMSG.noPower);
		
		/**后期可能需要修改*/
		if(restaurant.getRestaurantState().getStateId()!=70001)
			throw new UserException(ErrorMSG.loginStateError);
		return restaurant;
		
	}
	
	
	
	
	/**
	 * 根据城市id查询店家
	 * @param cityId
	 * @param location
	 * @return
	 */
	public List<Restaurant> getRestaueantByCityId(String cityId,Location location)throws Exception{
		List<Restaurant> listRestaurant=restaurantDao.findRestaurantByCityId(cityId);
		if(location!=null){
			listRestaurant=Tools.completionDistance(listRestaurant, location);
		}
		return listRestaurant;
	}


	/**
	 * 根据location得到餐馆信息
	 * @param location
	 * @return
	 */
	public List<Restaurant> getRestaueantByLocation(Location location) {
		List<City> cityList=cityDao.findCityByCityName(location.getCity());
		if(cityList==null||cityList.size()==0)
			return null;
		List<Restaurant> listRestaurant=restaurantDao.findRestaurantByCityId(cityList.get(0).getCityId());
		listRestaurant=Tools.completionDistance(listRestaurant, location);
		return listRestaurant;
	}


	/**
	 * 根据店家ID得到参观信息
	 * @param restaurantId
	 * @return
	 */
	public Restaurant findRestaurantService(String restaurantId,Location location) {
		Restaurant restaurant= restaurantDao.findRestaurantByRestaurantId(restaurantId);
		if(location!=null){
			Double distance= Tools.getDistance(restaurant.getRestaurantLongitude().doubleValue(),
					restaurant.getRestaurantLatitude().doubleValue(),
					location.getLongitude().doubleValue(),
					location.getLatitude().doubleValue());
			restaurant.setRestaurantDistance(BigDecimal.valueOf(distance));
		}
		return restaurant;
	}
	
	/**
	 * 根据location和分类Id得到餐馆信息
	 * @param location
	 * @param restaurantCategoryId
	 * @return
	 */
	public List<Restaurant> getRestaueantByLocationAndrcid(Location location,String restaurantCategoryId) {
		List<City> cityList=cityDao.findCityByCityName(location.getCity());
		if(cityList==null||cityList.size()==0)
			return null;
		List<Restaurant> listRestaurant=restaurantDao.findRestaurantByRestaurantCategoryIdAndCityId(restaurantCategoryId,cityList.get(0).getCityId());
		listRestaurant=Tools.completionDistance(listRestaurant, location);
		return listRestaurant;
	}
	
	/**
	 * 根据客户查询收藏的店家信息
	 * @param customer
	 * @return
	 */
	public List<Restaurant> findCollectRestaurantByCustomer(Customer customer){
		return restaurantDao.findCollectRestaurantByCustomerId(customer.getCustomerId());
	}
	
	
	
	/**
	 * 添加收藏的店家
	 * @param collectRestaurant
	 * @return
	 */
	public Integer addCollectRestaurant(Customer customer,String restaurantId){
		CollectRestaurant collectRestaurant=new CollectRestaurant();
		collectRestaurant.setCollectCustomer(customer);
		Restaurant restaurant=new Restaurant();
		restaurant.setRestaurantId(restaurantId);
		collectRestaurant.setCollectRestaurant(restaurant);
		return collectRestaurantDao.addCollectRestaurant(collectRestaurant);
	}
	/**
	 * 删除收藏的店家
	 * @param collectRestaurant
	 * @return
	 */
	public Integer deleteCollectRestaurant(Customer customer,String restaurantId){
		CollectRestaurant collectRestaurant=new CollectRestaurant();
		collectRestaurant.setCollectCustomer(customer);
		Restaurant restaurant=new Restaurant();
		restaurant.setRestaurantId(restaurantId);
		collectRestaurant.setCollectRestaurant(restaurant);
		return collectRestaurantDao.deleteCollectRestaurant(collectRestaurant);
	}
	
	/**更新店家信息
	 * @param restaurant
	 */
	public Integer updateRestaurant(Restaurant restaurant) {
		return restaurantDao.updateRestaurant(restaurant);
	}


	public Integer lockAccount(String restaurantId, Integer state) {
		Restaurant restaurant=restaurantDao.findRestaurantByRestaurantId(restaurantId);
		restaurant.setRestaurantState(new State(state));
		return restaurantDao.updateRestaurant(restaurant);
	}
	
	/**根据查询所有分类信息*/
	public List<RestaurantCategory> findAllRestaurantCategory(){
		return restaurantCategoryDao.findAllRestaurantCategory();
	}
}

