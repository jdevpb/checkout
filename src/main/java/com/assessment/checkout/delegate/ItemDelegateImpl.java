package com.assessment.checkout.delegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.assessment.checkout.pojo.ItemDetailsBean;
import com.assessment.checkout.service.ItemService;

/**
 * Class to delegate item related requests to one or more services.
 * 
 * @author pankaj
 *
 */
@Component
public class ItemDelegateImpl implements ItemDelegate {

	@Autowired
	ItemService itemService;

	@Override
	public ItemDetailsBean getItemDetails(long itemCd, String localeName) {

		return itemService.convertItemDetailsToBean(itemService.getItemDetails(itemCd, localeName));
	}

}
