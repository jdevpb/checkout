package com.assessment.checkout.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "LOCALEDETAILS")
@Table(name = "LOCALE_DETAILS")
public class LocaleDetails extends BaseDTO {

	private static final long serialVersionUID = -4474236728498525247L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "LOCALE_DETAILS_ID")
	private Long localeDetailsId;

	@Column(name = "LOCALE")
	private String locale;

	@Column(name = "LOCALE_NAME")
	private String localeName;

	@Column(name = "CURRENCY_CD")
	private String currencyCode;

	// Currency details can be further normalized if more locale specific
	// details are to be added
	@Column(name = "CURRENCY")
	private String currency;

	@Column(name = "CURRENCY_SYMBOL")
	private String currencySymbol;

	public Long getLocaleDetailsId() {
		return localeDetailsId;
	}

	public void setLocaleDetailsId(Long localeDetailsId) {
		this.localeDetailsId = localeDetailsId;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getLocaleName() {
		return localeName;
	}

	public void setLocaleName(String localeName) {
		this.localeName = localeName;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCurrencySymbol() {
		return currencySymbol;
	}

	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}

	@Override
	public String toString() {
		return "LocaleDetails [localeDetailsId=" + localeDetailsId + ", locale=" + locale
				+ ", localeName=" + localeName + ", currencyCode=" + currencyCode + ", currency="
				+ currency + ", currencySymbol=" + currencySymbol + "]";
	}

}
