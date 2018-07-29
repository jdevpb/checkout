package com.assessment.checkout.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.checkout.constant.CheckoutConstants;
import com.assessment.checkout.delegate.ItemDelegate;
import com.assessment.checkout.pojo.ItemDetailsBean;

/**
 * Controller for item related services.
 * 
 * @author pankaj
 *
 */
@RestController
public class ItemController {

	@Autowired
	ItemDelegate itemDelegate;

	/**
	 * Gets item details based on item code and locale. Item code and locale
	 * combination is unique.
	 * 
	 * @param itemCd
	 *            - Item Code
	 * @param locale
	 *            - Locale
	 * @return Item Details
	 */
	@GetMapping(value = "/item/{itemCd}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ItemDetailsBean getItemDetails(@PathVariable("itemCd") long itemCd,
			Locale locale) {

		String localeName = !StringUtils.isEmpty(locale) ? locale.toString()
				: CheckoutConstants.DEFAULT_LOCALE;
		return itemDelegate.getItemDetails(itemCd, localeName);
	}

}
