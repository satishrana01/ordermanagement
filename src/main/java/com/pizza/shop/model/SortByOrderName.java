package com.pizza.shop.model;

import java.util.Comparator;
/***
 * class for sort the object on the basis of name
 * @author Satish Rana
 *
 */
public class SortByOrderName implements Comparator<Object> {

	@Override
	public int compare(Object o1, Object o2) {
		
		Order order1 = (Order) o1;
		Order order2 = (Order) o2;
		
		return order1.getName().compareTo(order2.getName());
	}

}
