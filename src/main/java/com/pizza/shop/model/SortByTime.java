package com.pizza.shop.model;

import java.util.Comparator;
/***
 * class for sort the object on the basis of time
 * 
 * @author Satish
 *
 */
public class SortByTime implements Comparator<Object> {

	@Override
	public int compare(Object o1, Object o2) {
		
		Order order1 = (Order) o1;
		Order order2 = (Order) o2;
		
		if (order1.getTime() == order2.getTime())
			return 0;
		else if (order1.getTime() > order2.getTime())
			return 1;
		else
			return -1;
	}

}
