package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.OrderItem;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.dao.OrderItemDAO;
import com.qa.ims.utils.Utils;

/**
 * Takes in order details for CRUD functionality
 *
 */
public class OrderController implements CrudController<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	private OrderDAO orderDAO;
	private OrderItemDAO orderItemDAO;
	private ItemDAO itemDAO;
	private Utils utils;

	public OrderController(OrderDAO orderDAO, OrderItemDAO orderItemDAO, ItemDAO itemDAO, Utils utils) {
		super();
		this.orderDAO = orderDAO;
		this.utils = utils;
		this.orderItemDAO = orderItemDAO;
		this.itemDAO = itemDAO;
	}

	/**
	 * Reads all orders and order items to the logger
	 */
	@Override
	public List<Order> readAll() {
		List<Order> order = orderDAO.readAll();
		for (Order orders : order) {
			Long orderId = orders.getId();
			List<OrderItem> orderItem = orderItemDAO.readAllOrdersWithItems(orderId);
			LOGGER.info("------------------NEXT ORDER------------------\nORDER-> " + orders.toString());
			for (OrderItem orderItem1 : orderItem) {
				LOGGER.info("Individual Items -> " + orderItem1.toString());
			}
		}
		return order;
	}

	/**
	 * Creates an order by taking in user input
	 */
	@Override
	public Order create() {
		LOGGER.info("Please enter an customer id");
		Long customerId = utils.getLong();
		Order order = orderDAO.create(new Order(customerId));
		LOGGER.info("Order has been created.");
		return order;
	}
	
	/**
	 * Updates an existing order by taking in user input
	 */
	@Override
	public Order update() {
		return null;
	}
	
	/**
	 * Deletes an existing order by the id of the order
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Input id of the order that you would like to delete");
		Long orderId = utils.getLong();
		int deletedOrderItems = orderItemDAO.deleteOrderItemViaOrderId(orderId);
		int deletedOrders = orderDAO.delete(orderId);
		LOGGER.info(String.format(
				"%x orders were deleted from the system and %x items were deleted from those orders.",
				deletedOrders, deletedOrderItems));
		return deletedOrders;
	}
	
	/**
	 * Calculate cost of an order by taking in user input
	 */
	public Float calculateCostOfOrder() {
		Float cost = 0f;
		LOGGER.info("Input id of the order that you would like to know the total cost of?");
		Long orderId = utils.getLong();
		List<OrderItem> orderItem = orderItemDAO.readAllOrdersWithItems(orderId);
		for (OrderItem orderItem1 : orderItem) {
			Long itemId = orderItem1.getItemId();
			Item item = itemDAO.read(itemId);
			cost += item.getItemValue() * orderItem1.getQuantity();
		}
		LOGGER.info(String.format("The total cost of the order with the id: %x is $%s", orderId,
				String.format("%.02f", cost)));
		return cost;
	}
	
	/**
	 * Deletes an item from order by taking in user input
	 */
	public int deleteItemFromOrder() {
		LOGGER.info("Input id of the order that you want to delete an item from");
		Long orderId = utils.getLong();
		LOGGER.info("Input id of the item that you would like to remove. All of the same items be removed if multiple same items in one order.");
		Long itemId = utils.getLong();
		int itemDeletedFromOrder = orderItemDAO.deleteOrderItemViaItemId(itemId, orderId);
		LOGGER.info(String.format("Item id: %d was removed from order id: %d %d many times", itemId, orderId, itemDeletedFromOrder));
		return itemDeletedFromOrder;
	}
	
	/**
	 * Add an item to order by taking in user input
	 */
	public OrderItem addItemToOrder() {
		LOGGER.info("Input id of the order that you want to add an item from");
		Long orderId = utils.getLong();
		LOGGER.info("Input id of the item that you would like to add to the order");
		Long itemId = utils.getLong();
		LOGGER.info("Number of items that should be added to the order");
		Long quantity = utils.getLong();
		OrderItem OrderAdded = orderItemDAO.addItemsToOrderItem(orderId, itemId, quantity);
		LOGGER.info("Item added to the order");
		return OrderAdded;
	}

}
