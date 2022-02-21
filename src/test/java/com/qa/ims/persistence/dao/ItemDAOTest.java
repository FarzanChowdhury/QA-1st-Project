package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;

public class ItemDAOTest {

	private final ItemDAO itemDAO = new ItemDAO();
	
	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/IMS-Project-Test-Schema.sql", "src/test/resources/IMS-Project-Test-Data.sql");
	}
	
	@Test
	public void testCreate() {
		final Item created = new Item(4L, "lamp", 19.00F);
		assertEquals(created, itemDAO.create(created));
	}

	@Test
	public void testReadAll() {
		List<Item> expected = new ArrayList<>();
		expected.add(new Item(1L, "football", 20.00F));
		expected.add(new Item(2L, "basketball", 15.00F));
		expected.add(new Item(3L, "perfume", 40.00F));
		assertEquals(expected, itemDAO.readAll());
	}

	@Test
	public void testReadLatest() {
		assertEquals(new Item(3L, "perfume", 40.00F), itemDAO.readLatest());
	}

	@Test
	public void testRead() {
		final long id = 1L;
		assertEquals(new Item(id, "football", 20.00F), itemDAO.read(id));
	}

	@Test
	public void testUpdate() {
		final Item updated = new Item(1L, "racket", 15.00F);
		assertEquals(updated, itemDAO.update(updated));

	}

	@Test
	public void testDelete() {
		assertEquals(1, itemDAO.delete(1));
	}
}
	
