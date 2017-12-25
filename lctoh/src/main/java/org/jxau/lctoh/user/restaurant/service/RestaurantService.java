package org.jxau.lctoh.user.restaurant.service;

import org.jxau.lctoh.tool.config.ErrorMSG;
import org.jxau.lctoh.user.basis.dao.UserDao;
import org.jxau.lctoh.user.basis.domain.User;
import org.jxau.lctoh.user.basis.exception.UserException;
import org.jxau.lctoh.user.restaurant.dao.RestaurantDao;
import org.jxau.lctoh.user.restaurant.domain.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("RestaurantService")
public class RestaurantService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private RestaurantDao restaurantDao;
	
	/**
	 * 管理员登陆
	 * @param user  用户
	 * @return Restaurant 店家
	 * @throws UserException
	 * @throws Exception
	 */
	public Restaurant login(User user) throws UserException,Exception{
		
		User _user=userDao.findUserByUserAccount(user.getUserAccount());
		if(_user==null) throw new UserException(ErrorMSG.accountError);
		if(!(_user.getUserPassword().equals(user.getUserPassword())))
			throw new UserException(ErrorMSG.passwordError);
		Restaurant restaurant=restaurantDao.findRestaurantByUserId(_user.getUserId());
		if(restaurant==null)throw new UserException(ErrorMSG.powerError);
		
		/**后期可能需要修改*/
		if(restaurant.getRestaurantState().getStateId()!=70001)
			throw new UserException(ErrorMSG.loginStateError);
		return restaurant;
		
	}
}

