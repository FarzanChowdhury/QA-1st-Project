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

import com.qa.ims.controller.ItemController;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest {

	@Mock
	private Utils utils;

	@Mock
	private ItemDAO dao;

	@InjectMocks
	private ItemController itemController;
	
	@Test
	public void testCreate() {
		
	final String I_NAME = "football";
	final Float I_VALUE = 20.00F;
	final Item created = new Item(I_NAME, I_VALUE);

	Mockito.when(utils.getString()).thenReturn(I_NAME);
	Mockito.when(utils.getFloat()).thenReturn(I_VALUE);
	Mockito.when(dao.create(created)).thenReturn(created);

	assertEquals(created, itemController.create());

	Mockito.verify(utils, Mockito.times(1)).getString();
	Mockito.verify(utils, Mockito.times(1)).getFloat();
	Mockito.verify(dao, Mockito.times(1)).create(created);
}
	
	@Test
	public void testReadAll() {
		List<Item> items = new ArrayList<>();
		items.add(new Item(1L, "football", 20.00F));

		Mockito.when(dao.readAll()).thenReturn(items);

		assertEquals(items, itemController.readAll());

		Mockito.verify(dao, Mockito.times(1)).readAll();
	}

	@Test
	public void testUpdate() {
		Item updated = new Item(1L, "shoes", 16.00F);

		Mockito.when(this.utils.getLong()).thenReturn(1L);
		Mockito.when(this.utils.getString()).thenReturn(updated.getItemName());
		Mockito.when(this.utils.getFloat()).thenReturn(updated.getItemValue());
		Mockito.when(this.dao.update(updated)).thenReturn(updated);

		assertEquals(updated, this.itemController.update());

		Mockito.verify(this.utils, Mockito.times(1)).getLong();
		Mockito.verify(this.utils, Mockito.times(1)).getString();
		Mockito.verify(this.utils, Mockito.times(1)).getFloat();
		Mockito.verify(this.dao, Mockito.times(1)).update(updated);
	}

	@Test
	public void testDelete() {
		final long id = 1L;

		Mockito.when(utils.getLong()).thenReturn(id);
		Mockito.when(dao.delete(id)).thenReturn(1);

		assertEquals(1L, this.itemController.delete());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).delete(id);
	}

	@Test
	public void testDeleteMoreThanOne() {
		final long id = 1L;

		Mockito.when(utils.getLong()).thenReturn(id);
		Mockito.when(dao.delete(id)).thenReturn(1);

		assertEquals(1L, this.itemController.delete());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).delete(id);
	}

}
