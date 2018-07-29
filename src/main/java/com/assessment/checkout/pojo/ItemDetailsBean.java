package com.assessment.checkout.pojo;

import java.util.List;

public class ItemDetailsBean {

	private String itemDescription;

	private String category;

	private String currencySymbol;

	private Float price;

	private Float finalPrice;

	private Float totalTax;

	private List<ItemTaxDetailsBean> itemTaxDetails;

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCurrencySymbol() {
		return currencySymbol;
	}

	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Float getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(Float finalPrice) {
		this.finalPrice = finalPrice;
	}

	public Float getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(Float totalTax) {
		this.totalTax = totalTax;
	}

	public List<ItemTaxDetailsBean> getItemTaxDetailsBean() {
		return itemTaxDetails;
	}

	public void setItemTaxDetailsBean(List<ItemTaxDetailsBean> itemTaxDetails) {
		this.itemTaxDetails = itemTaxDetails;
	}

	@Override
	public String toString() {
		return "ItemDetailsBean [itemDescription=" + itemDescription + ", category=" + category
				+ ", currencySymbol=" + currencySymbol + ", price=" + price + ", finalPrice="
				+ finalPrice + ", totalTax=" + totalTax + ", itemTaxDetails=" + itemTaxDetails
				+ "]";
	}

}
