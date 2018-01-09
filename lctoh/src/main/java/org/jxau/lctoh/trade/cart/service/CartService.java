package org.jxau.lctoh.trade.cart.service;

import org.jxau.lctoh.position.address.dao.AddressDao;
import org.jxau.lctoh.position.address.domain.Address;
import org.jxau.lctoh.tool.Tools;
import org.jxau.lctoh.tool.config.ErrorMSG;
import org.jxau.lctoh.trade.cart.domain.Cart;
import org.jxau.lctoh.trade.cart.domain.CartItem;
import org.jxau.lctoh.trade.cart.exception.CartException;
import org.jxau.lctoh.trade.dish.dao.DishDao;
import org.jxau.lctoh.trade.dish.domain.Dish;
import org.jxau.lctoh.trade.order.dao.HarvestAddressDao;
import org.jxau.lctoh.trade.order.dao.OrderDao;
import org.jxau.lctoh.trade.order.dao.OrderItemDao;
import org.jxau.lctoh.trade.order.domain.Order;
import org.jxau.lctoh.user.customer.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author qdt_PC
 */
@Service("CartService")
public class CartService {
	
	@Autowired
	private DishDao dishDao;
	
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private OrderItemDao orderItemDao;
	@Autowired
	private HarvestAddressDao harvestAddressDao;
	@Autowired
	private AddressDao addressDao;
	/**
	 * 添加菜肴至购物车
	 * @param cart
	 * @param dishId
	 * @return Cart
	 * @throws CartException 
	 */
	public Cart addDishToCart(Cart cart, String dishId,Integer dishCount) throws CartException {
		Dish dish=dishDao.findDishByDishId(dishId);
		CartItem cartItem=new CartItem();
		cartItem.setDish(dish);
		cartItem.setDishCount(dishCount);
		cartItem.putSubtotal();
		try {
			cart.addDish(cartItem);
		} catch (Exception e) {
			throw new CartException(ErrorMSG.addCartDishError);
		}
		return cart;
	}
	
	/**
	 * 更改购物车信息
	 * @param cart
	 * @param dishId
	 * @param dishcount
	 * @return Cart
	 * @throws CartException 
	 */
	public Cart updateDishToCart(Cart cart, String dishId,Integer dishcount) throws CartException{
		Dish dish=dishDao.findDishByDishId(dishId);
		CartItem cartItem=new CartItem();
		cartItem.setDish(dish);
		cartItem.setDishCount(dishcount);
		cartItem.putSubtotal();
		try {
			cart.updateDish(cartItem);
		} catch (Exception e) {
			throw new CartException(ErrorMSG.updateCartDishError);
		}
		return cart;
	}
	
	
	/**
	 * 将购物车中的商品生成订单
	 * @param cart
	 * @param orderCustomer
	 * @param addressId
	 * @throws CartException
	 */
	public void  putCartToOrder(Cart cart,Customer orderCustomer,String addressId) throws CartException{
		Address address=addressDao.findAddressByAddressId(addressId);
		if(address==null)throw new CartException(ErrorMSG.addressError);
		Order order=cart.toOrder(orderCustomer, address.toHarvestAddress(Tools.getRandomString(32)));
		orderDao.addOrder(order);
		harvestAddressDao.addHarvestAddress(order.getOrderHarvestAddress());
		orderItemDao.addOrderItemList(order.getOrderItemList());
	}
	
	
}
