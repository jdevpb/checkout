package com.assessment.checkout.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Contains master details of the item that do not change with the locale.
 * 
 * @author pankaj
 *
 */
@Entity(name = "ITEMMASTER")
@Table(name = "ITEM_MASTER")
public class ItemMaster extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9197902853495335218L;

	@Id
	@GeneratedValue
	@Column(name = "ITEM_CD")
	private Long itemCode;

	@Column(name = "ITEM_NAME")
	private String itemName;

	public Long getItemCode() {
		return itemCode;
	}

	public void setItemCode(Long itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@Override
	public String toString() {
		return "ItemMaster [itemCode=" + itemCode + ", itemName=" + itemName + "]";
	}

}
