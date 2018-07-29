package com.assessment.checkout.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Contains item details that depend on the locale. Price currency would be
 * mapped against the locale.
 * 
 * @author pankaj
 *
 */
@Entity(name = "ITEMDETAILS")
@Table(name = "ITEM_DETAILS")
public class ItemDetails extends BaseDTO {

	private static final long serialVersionUID = -6502150335130278647L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ITEM_DETAILS_ID")
	private Long itemDetailsId;

	@Column(name = "ITEM_CD")
	private Long itemCode;

	@Column(name = "ITEM_DESCRIPTION")
	private String description;

	@Column(name = "ITEM_PRICE")
	private Float price;

	@Column(name = "ITEM_CATEGORY")
	private String category;

	@Column(name = "LOCALE")
	private String locale;

	@OneToOne
	@JoinColumn(name = "ITEM_CD", referencedColumnName = "ITEM_CD", insertable = false,
			updatable = false)
	private ItemMaster itemMaster;

	@Transient
	private Float finalPrice;

	@OneToMany
	@JoinColumns({
			@JoinColumn(name = "ITEM_CATEGORY", referencedColumnName = "ITEM_CATEGORY",
					insertable = false, updatable = false),
			@JoinColumn(name = "LOCALE", referencedColumnName = "LOCALE", insertable = false,
					updatable = false) })
	private List<ItemTaxDetails> itemTaxDetails;

	@OneToOne
	@JoinColumn(name = "LOCALE", referencedColumnName = "LOCALE", insertable = false,
			updatable = false)
	private LocaleDetails localeDetails;

	public Long getItemDetailsId() {
		return itemDetailsId;
	}

	public void setItemDetailsId(Long itemDetailsId) {
		this.itemDetailsId = itemDetailsId;
	}

	public Long getItemCode() {
		return itemCode;
	}

	public void setItemCode(Long itemCode) {
		this.itemCode = itemCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public List<ItemTaxDetails> getItemTaxDetails() {
		return itemTaxDetails;
	}

	public ItemMaster getItemMaster() {
		return itemMaster;
	}

	public void setItemMaster(ItemMaster itemMaster) {
		this.itemMaster = itemMaster;
	}

	public Float getFinalPrice() {

		return finalPrice;
	}

	public void setFinalPrice(Float finalPrice) {
		this.finalPrice = finalPrice;
	}

	public void setItemTaxDetails(List<ItemTaxDetails> itemTaxDetails) {
		this.itemTaxDetails = itemTaxDetails;
	}

	public LocaleDetails getLocaleDetails() {
		return localeDetails;
	}

	public void setLocaleDetails(LocaleDetails localeDetails) {
		this.localeDetails = localeDetails;
	}

	@Override
	public String toString() {
		return "ItemDetails [itemDetailsId=" + itemDetailsId + ", itemCode=" + itemCode
				+ ", description=" + description + ", price=" + price + ", category=" + category
				+ ", locale=" + locale + ", itemMaster=" + itemMaster + ", finalPrice=" + finalPrice
				+ ", itemTaxDetails=" + itemTaxDetails + ", localeDetails=" + localeDetails + "]";
	}

}
