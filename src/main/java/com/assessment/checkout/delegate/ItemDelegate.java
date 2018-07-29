package com.assessment.checkout.delegate;

import com.assessment.checkout.pojo.ItemDetailsBean;

public interface ItemDelegate {

	/**
	 * Gets item details and converts them to a bean to be returned as service response.
	 * @param itemCd - Item Code
	 * @param localeName - Locale in xx_XX format
	 * @return ItemDetails POJO
	 */
	ItemDetailsBean getItemDetails(long itemCd, String localeName);
}
