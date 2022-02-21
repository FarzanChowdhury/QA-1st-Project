package com.qa.ims.persistence.dao;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.OrderItem;
import com.qa.ims.utils.DBUtils;

public class OrderItemDAO implements Dao<OrderItem>{

	public static final Logger LOGGER = LogManager.getLogger();

	public OrderItem modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		Long fk_order_id = resultSet.getLong("fk_order_id");
		Long fk_item_id = resultSet.getLong("fk_item_id");
		Long quantity = resultSet.getLong("quantity");
		return new OrderItem(id, fk_order_id, fk_item_id, quantity);
	}
	
	@Override
	public List<OrderItem> readAll() {
		return null;
	}

	public OrderItem readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM order_items ORDER BY id DESC LIMIT 1;");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 * Creates an order item in the database
	 * 
	 * @param order item - takes in an order item object. id will be ignored
	 */
	@Override
	public OrderItem create(OrderItem orderItem) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO order_items(fk_order_id, fk_item_id, quantity) VALUES (?, ?, ?)");) {
			statement.setLong(1, orderItem.getOrderId());
			statement.setLong(2, orderItem.getItemId());
			statement.setLong(3, orderItem.getQuantity());
			statement.executeUpdate();
			return readLatest();
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public OrderItem read(Long id) {
		return null;
	}

	@Override
	public OrderItem update(OrderItem orderitem) {
		return null;
	}
	
	@Override
	public int delete(long id) {
		return 0;
	}
	
	/**
	 * Reads all order items that belongs to an order id from the database
	 * 
	 * @return A list of order items that belongs to an order id
	 */
	public List<OrderItem> readAllOrdersWithItems(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM order_items WHERE fk_order_id = ?");) {
			statement.setLong(1, id);
			List<OrderItem> orderList = new ArrayList<>();
			try (ResultSet resultSet = statement.executeQuery();) {
				while (resultSet.next()) {
					orderList.add(modelFromResultSet(resultSet));
				}
			}
			return orderList;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}
	
	/**
	 * Deletes an order item using order id in the database
	 * 
	 * @param order id - order id of the order item
	 */
	public int deleteOrderItemViaOrderId(Long id) { 
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM order_items WHERE fk_order_id = ?");) {
			statement.setLong(1, id); 
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;

	}
	
	/**
	 * Deletes an order item using item id in the database
	 * 
	 * @param id - id of the order item, order id - id of the order
	 */
	public int deleteOrderItemViaItemId(Long id, Long orderId) { 
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM order_items WHERE fk_item_id = ? and fk_order_id = ?");) {
			statement.setLong(1, id); 
			statement.setLong(2, orderId); 
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;// no records deleted

	}
	
	/**
	 * Adds an item to order item in the database
	 * 
	 * @param id - id of the order item, item id - id of the item, quantity - number of items
	 */
	public OrderItem addItemsToOrderItem(Long orderId, Long itemId, Long quantity) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("INSERT INTO order_items(fk_order_id, fk_item_id, quantity) VALUES (?, ?, ?)");) {
			statement.setLong(1, orderId); 
			statement.setLong(2, itemId); 
			statement.setLong(3, quantity);
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
}