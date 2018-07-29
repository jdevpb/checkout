package com.assessment.checkout.pojo;

public class ItemTaxDetailsBean {

	private String taxDescription;

	private Float taxRate;

	private Float taxAmount;

	public String getTaxDescription() {
		return taxDescription;
	}

	public void setTaxDescription(String taxDescription) {
		this.taxDescription = taxDescription;
	}

	public Float getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(Float taxRate) {
		this.taxRate = taxRate;
	}

	public Float getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(Float taxAmount) {
		this.taxAmount = taxAmount;
	}

	@Override
	public String toString() {
		return "ItemTaxDetailsBean [taxDescription=" + taxDescription + ", taxRate=" + taxRate
				+ ", taxAmount=" + taxAmount + "]";
	}
}
