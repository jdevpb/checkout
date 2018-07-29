package com.assessment.checkout.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "ITEMTAXDETAILS")
@Table(name = "ITEM_TAX_DETAILS")
public class ItemTaxDetails extends BaseDTO {

	private static final long serialVersionUID = 8968096448310371310L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ITEM_TAX_DETAILS_ID")
	private Long itemTaxDetailsId;

	@Column(name = "ITEM_CATEGORY")
	private String category;

	@Column(name = "TAX_CD")
	private String taxCode;

	@Column(name = "LOCALE")
	private String locale;

	@OneToOne
	@JoinColumn(name = "TAX_CD", referencedColumnName = "TAX_CD", insertable = false,
			updatable = false)
	private TaxDetails taxDetails;

	public Long getItemTaxDetailsId() {
		return itemTaxDetailsId;
	}

	public void setItemTaxDetailsId(Long itemTaxDetailsId) {
		this.itemTaxDetailsId = itemTaxDetailsId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public TaxDetails getTaxDetails() {
		return taxDetails;
	}

	public void setTaxDetails(TaxDetails taxDetails) {
		this.taxDetails = taxDetails;
	}

	@Override
	public String toString() {
		return "ItemTaxDetails [itemTaxDetailsId=" + itemTaxDetailsId + ", category=" + category
				+ ", taxCode=" + taxCode + ", locale=" + locale + ", taxDetails=" + taxDetails
				+ "]";
	}
}