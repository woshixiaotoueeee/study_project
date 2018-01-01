package org.jxau.lctoh.trade.cart.domain;

import org.apache.ibatis.type.Alias;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Alias("CartItem")
@Scope("prototype")
public class CartItem {

}
