package org.jxau.lctoh.trade.cart.service;

import org.jxau.lctoh.position.address.dao.AddressDao;
import org.jxau.lctoh.position.address.domain.Address;
import org.jxau.lctoh.state.domain.State;
import org.jxau.lctoh.tool.Tools;
import org.jxau.lctoh.tool.config.error.ErrorMSG;
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
import org.jxau.lctoh.user.rider.dao.DispatchingDao;
import org.jxau.lctoh.user.rider.domain.Dispatching;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Autowired
	private DispatchingDao dispatchingDao;
	
	//@Autowired
	//private CustomerDao customerDao;
	
	
	/**
	 * 添加菜肴至购物车
	 * @param cart
	 * @param dishId
	 * @return Cart
	 * @throws CartException 
	 */
	public Cart addDishToCart(Cart cart, String dishId,Integer dishCount) throws CartException {
		//找到想要添加的菜肴
		Dish dish=dishDao.findDishByDishId(dishId);
		if(dish==null)throw new CartException(ErrorMSG.selectFail);
		//生成并补全订单条目
		CartItem cartItem=new CartItem();
		cartItem.setDish(dish);
		cartItem.setDishCount(dishCount);
		//计算价格
		cartItem.putSubtotal();
		try {
			//添加
			cart.addDish(cartItem);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CartException(ErrorMSG.noDish);
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
		//找到想要添加的菜肴
		Dish dish=dishDao.findDishByDishId(dishId);
		if(dish==null)throw new CartException(ErrorMSG.noDish);
		//生成并补全订单条目
		CartItem cartItem=new CartItem();
		cartItem.setDish(dish);
		cartItem.setDishCount(dishcount);
		//计算价格
		cartItem.putSubtotal();
		try {
			//更新
			cart.updateDish(cartItem);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CartException(ErrorMSG.updateFail);
		}
		return cart;
	}
	
	
	/**
	 * 将购物车中的商品生成订单
	 * @param cart
	 * @param orderCustomer
	 * @throws CartException
	 */
	@Transactional(rollbackFor = Exception.class)
	public String  putCartToOrder(Cart cart,Customer orderCustomer ) throws CartException{
		
		Order order=cart.toOrder(orderCustomer);
		
		/*
		if(orderCustomer.getCustomerBalance().doubleValue()<order.getOrderPrice().doubleValue()){
			throw new CartException(ErrorMSG.insufficienFunds);
		}
		orderCustomer.setCustomerBalance(orderCustomer.getCustomerBalance().subtract(order.getOrderPrice()));
		customerDao.updateCustomer(orderCustomer);
		*/
		Dispatching dispatching=new Dispatching();
		dispatching.setDispatchingOrder(order);
		
		State dispatchingState=new State(110001);
		dispatching.setDispatchingState(dispatchingState);
		
		orderDao.addOrder(order);
		//harvestAddressDao.addHarvestAddress(order.getOrderHarvestAddress());
		orderItemDao.addOrderItemList(order.getOrderItemList());
		dispatchingDao.addDispatching(dispatching);
		
		return order.getOrderId();
	}
	
	
}
