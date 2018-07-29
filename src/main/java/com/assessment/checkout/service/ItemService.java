package com.assessment.checkout.service;

import com.assessment.checkout.entity.ItemDetails;
import com.assessment.checkout.pojo.ItemDetailsBean;

public interface ItemService {
	
	/**
	 * Gets item details based on item code and locale from the repository.
	 * @param itemCd - Item Code
	 * @param localeName - Locale in xx_XX(en_US) format
	 * @return ItemDetails Entity
	 */
	ItemDetails getItemDetails(long itemCd, String localeName);
	
	/**
	 * Converts ItemDetails Entity to POJO that can be returned as the service response.
	 * Only attributes required in the response are set
	 * @param itemDetails - Entity to be converted
	 * @return ItemDetails POJO
	 */
	ItemDetailsBean convertItemDetailsToBean(ItemDetails itemDetails);

}
