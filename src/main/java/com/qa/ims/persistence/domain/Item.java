package com.qa.ims.persistence.domain;

public class Item {

	private Long id;
	private String itemName;
	private Float itemValue;

	public Item(String itemName, Float itemValue) {
		this.setItemName(itemName);
		this.setItemValue(itemValue);
	}

	public Item(Long id, String itemName, Float item_value) {
		this.setId(id);
		this.setItemName(itemName);
		this.setItemValue(item_value);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public float getItemValue() {
		return itemValue;
	}

	public void setItemValue(Float itemValue) {
		this.itemValue = itemValue;
	}

	@Override
	public String toString() {
		return String.format("id: %d itemName: %s itemValue: %s", this.getId(), this.getItemName(), String.format("%.02f",this.getItemValue()));
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemName == null) ? 0 : itemName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((itemValue == null) ? 0 : itemValue.hashCode());
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
		Item other = (Item) obj;
		if (getItemName() == null) {
			if (other.getItemName() != null)
				return false;
		} else if (!getItemName().equals(other.getItemName()))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (itemValue == null) {
			if (other.itemValue != null)
				return false;
		} else if (!itemValue.equals(other.itemValue))
			return false;
		return true;
	}
}
