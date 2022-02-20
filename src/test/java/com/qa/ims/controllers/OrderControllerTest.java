package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.OrderController;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.dao.OrderItemDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.OrderItem;

import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {
	@Mock
	private Utils utils;

	@Mock
	private OrderDAO orderDAO;

	@Mock
	private OrderItemDAO orderItemDAO;

	@Mock
	private ItemDAO itemDAO;

	@Mock
	private Order orderMock;

	@InjectMocks
	private OrderController orderController;
	
	@Test
	public void TestCreate() {
		final Long id = 1L;
		final Order created = new Order(id);
		
		Mockito.when(utils.getLong()).thenReturn(id);
		Mockito.when(orderDAO.create(created)).thenReturn(created);
		
		assertEquals(created, orderController.create());
		
		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(orderDAO, Mockito.times(1)).create(created);
	}

	@Test
	public void testReadAll() {
		List<Order> Orders = new ArrayList<>();
		Orders.add(new Order(1L, 8L));

		List<OrderItem> orderItems = new ArrayList<>();
		orderItems.add(new OrderItem(3l, 2l, 7L, 10L)); //

		Mockito.when(orderDAO.readAll()).thenReturn(Orders);
		Mockito.when(orderItemDAO.readAllOrdersWithItems(1l)).thenReturn(orderItems);

		assertEquals(Orders, orderController.readAll());

		Mockito.verify(orderDAO, Mockito.times(1)).readAll();
	}

	@Test
	public void testDelete() {
		long id = 1L;

		Mockito.when(utils.getLong()).thenReturn(id);
		Mockito.when(orderDAO.delete(id)).thenReturn(1);
		Mockito.when(orderItemDAO.deleteOrderItemViaOrderId(id)).thenReturn(1);

		assertEquals(id, this.orderController.delete());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(orderDAO, Mockito.times(1)).delete(id);
		Mockito.verify(orderItemDAO, Mockito.times(1)).deleteOrderItemViaOrderId(id);
	}

	@Test
	public void testCalculateCostOfOrder() {
		List<OrderItem> orderItemReturned = new ArrayList<>();
		orderItemReturned.add(new OrderItem(1L, 1L, 1L, 2L));

		Mockito.when(utils.getLong()).thenReturn(1L);
		Mockito.when(orderItemDAO.readAllOrdersWithItems(1L)).thenReturn(orderItemReturned);
		Mockito.when(itemDAO.read(1L)).thenReturn(new Item("wallet", 18.99F));

		assertEquals(18.99F * 2L, orderController.calculateCostOfOrder(), 0.0002);

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(orderItemDAO, Mockito.times(1)).readAllOrdersWithItems(1L);
		Mockito.verify(itemDAO, Mockito.times(1)).read(1L);
	}

	@Test
	public void testDeleteItemFromOrder() {
		Mockito.when(utils.getLong()).thenReturn(1L, 1L);
		Mockito.when(orderItemDAO.deleteOrderItemViaItemId(1L, 1L)).thenReturn(1);

		assertEquals(1, orderController.deleteItemFromOrder());

		Mockito.verify(utils, Mockito.times(2)).getLong();
		Mockito.verify(orderItemDAO, Mockito.times(1)).deleteOrderItemViaItemId(1L, 1L);

	}

	@Test
	public void TestAddItemFromOrder() {
		OrderItem orderItemReturns = new OrderItem(1L, 1L, 1L, 2L);
		Mockito.when(utils.getLong()).thenReturn(1L, 1L, 2L);
		Mockito.when(orderItemDAO.addItemsToOrderItem(1L, 1L, 2L)).thenReturn(orderItemReturns);

		assertEquals(orderItemReturns, orderController.addItemToOrder());

		Mockito.verify(utils, Mockito.times(3)).getLong();
		Mockito.verify(orderItemDAO, Mockito.times(1)).addItemsToOrderItem(1L, 1L, 2L);
	}

	

}
