package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAOTest {

private final OrderDAO orderDAO = new OrderDAO();
	
	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/IMS-Project-Test-Order.sql");
	}
	
	@Test
	public void testCreate() {
		final Order created = new Order(4L, 5L);
		assertEquals(created, orderDAO.create(created));
	}

	@Test
	public void testReadAll() {
		List<Order> expected = new ArrayList<>();
		expected.add(new Order(1L, 8L));
		expected.add(new Order(2L, 5L));
		expected.add(new Order(3L, 2L));
		assertEquals(expected, orderDAO.readAll());
	}

	@Test
	public void testReadLatest() {
		assertEquals(new Order(3L, 2L), orderDAO.readLatest());
	}

	@Test
	public void testRead() {
		final long id = 1L;
		assertEquals(new Order(id, 8L), orderDAO.read(id));
	}

	@Test
	public void testUpdate() {
		return;
	}

	@Test
	public void testDelete() {
		assertEquals(1, orderDAO.delete(1));
	}
}
