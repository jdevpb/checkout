package com.assessment.checkout.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "TAXDETAILS")
@Table(name = "TAX_DETAILS")
public class TaxDetails extends BaseDTO {

	private static final long serialVersionUID = 5650752507090551452L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "TAX_DETAILS_ID")
	private Long taxDetailsId;

	@Column(name = "TAX_CD")
	private String taxCode;

	@Column(name = "TAX_DESCRIPTION")
	private String description;

	@Column(name = "TAX_RATE")
	private Float rate;

	public Long getTaxDetailsId() {
		return taxDetailsId;
	}

	public void setTaxDetailsId(Long taxDetailsId) {
		this.taxDetailsId = taxDetailsId;
	}

	public String getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "TaxDetails [taxDetailsId=" + taxDetailsId + ", taxCode=" + taxCode
				+ ", description=" + description + ", rate=" + rate + "]";
	}

}
