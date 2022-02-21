package com.qa.ims.persistence.domain;

public class OrderItem {
	
	private Long id;
	private Long orderId;
	private Long itemId;
	private Long quantity;
	
	public OrderItem(Long orderId, Long itemId, Long quantity) {
		this.setOrderId(orderId);
		this.setItemId(itemId);
		this.setQuantity(quantity);
	}

	public OrderItem(Long id, Long orderId, Long itemId, Long quantity) {
		this.setId(id);
		this.setOrderId(orderId);
		this.setItemId(itemId);
		this.setQuantity(quantity);
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getOrderId() {
		return orderId;
	}
	
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	public Long getItemId() {
		return itemId;
	}
	
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	
	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return String.format("id: %d itemId: %d quantity: %d", this.getId(), this.getItemId(), this.getQuantity());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		if (getId() == null) {
			if (other.getId() != null) 
				return false;
		} else if (!id.equals(other.getId()))
			return false;
		if (getOrderId() == null) {
			if (other.getOrderId() != null)
				return false;
		} else if (!orderId.equals(other.getOrderId()))
				return false;
		if (getItemId() == null) {
			if (other.getItemId() != null) 
				return false;
		} else if (!itemId.equals(other.getItemId()))
			return false;
		if (getQuantity() == null) {
			if (other.getQuantity() != null) 
				return false;
		} else if (!quantity.equals(other.getQuantity()))
			return false;
		return true;
	}
}