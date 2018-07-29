package com.assessment.checkout.pojo;

import java.util.List;

public class BillDetailsBean {

	private String currency;

	private Float totalAmount;

	private Float preTaxAmount;

	private Float totalTax;

	private List<ItemDetailsBean> itemDetailsBean;

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Float totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Float getPreTaxAmount() {
		return preTaxAmount;
	}

	public void setPreTaxAmount(Float preTaxAmount) {
		this.preTaxAmount = preTaxAmount;
	}

	public Float getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(Float totalTax) {
		this.totalTax = totalTax;
	}

	public List<ItemDetailsBean> getItemDetailsBean() {
		return itemDetailsBean;
	}

	public void setItemDetailsBean(List<ItemDetailsBean> itemDetailsBean) {
		this.itemDetailsBean = itemDetailsBean;
	}

	@Override
	public String toString() {
		return "BillDetailsBean [currency=" + currency + ", totalAmount=" + totalAmount
				+ ", preTaxAmount=" + preTaxAmount + ", totalTax=" + totalTax + ", itemDetailsBean="
				+ itemDetailsBean + "]";
	}
}
