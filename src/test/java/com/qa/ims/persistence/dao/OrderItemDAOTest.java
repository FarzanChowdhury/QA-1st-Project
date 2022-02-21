package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.OrderItem;
import com.qa.ims.utils.DBUtils;

public class OrderItemDAOTest {

	private final OrderItemDAO orderItemDAO = new OrderItemDAO();
	private final OrderDAO orderDAO = new OrderDAO();
	
	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/IMS-Project-Test-Schema.sql", "src/test/resources/IMS-Project-Test-Data.sql");
	}
	
	@Test
	public void testCreate() {
		final OrderItem created = new OrderItem(4L, 8L, 5L, 9L);
		assertEquals(created, orderItemDAO.create(created));
	}

	@Test
	public void testReadLatest() {
		assertEquals(new OrderItem(3L, 1L, 3L, 2L), orderItemDAO.readLatest());
	}
	
	@Test
	public void testReadAllOrdersWithItems() {
		List<Order> order = orderDAO.readAll();
		List<OrderItem> expected = orderItemDAO.readAllOrdersWithItems(order.get(0).getId());
		List<OrderItem> expectedOrderItem = new ArrayList<>();
		expectedOrderItem.add(new OrderItem(1l, 1l, 1l, 5l));
		expectedOrderItem.add(new OrderItem(2l, 6l, 7l, 8l));
		expectedOrderItem.add(new OrderItem(3l, 1l, 3l, 2l));
		assertEquals(order.get(0).getId(), expected.get(0).getOrderId());
		assertEquals(expectedOrderItem.get(0).getId(), expected.get(0).getId());
		assertEquals(expectedOrderItem.get(0).getQuantity(), expected.get(0).getQuantity());
	}
	
	@Test
	public void testdeleteOrderItemViaOrderId() {
		assertEquals(2, orderItemDAO.deleteOrderItemViaOrderId(1L));
	}
	
	@Test
	public void testdeleteOrderItemViaItemId() {
		assertEquals(1, orderItemDAO.deleteOrderItemViaItemId(1L, 1L));
	}
	
	@Test
	public void TestaddItemsToOrderItem() {
		OrderItem expected = new OrderItem(5L,2L,15l);
		OrderItem orderItem = orderItemDAO.addItemsToOrderItem(5L,2L,15l);
		assertEquals(expected.getOrderId(), orderItem.getOrderId());
		assertEquals(expected.getItemId(), orderItem.getItemId());
		assertEquals(expected.getQuantity(), orderItem.getQuantity());
	}
	
}