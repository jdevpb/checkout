package com.assessment.checkout.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assessment.checkout.dao.ItemRepository;
import com.assessment.checkout.entity.ItemDetails;
import com.assessment.checkout.entity.ItemTaxDetails;
import com.assessment.checkout.exception.ItemNotFoundException;
import com.assessment.checkout.pojo.ItemDetailsBean;
import com.assessment.checkout.pojo.ItemTaxDetailsBean;

/**
 * Service class to provide item related services.
 * 
 * @author pankaj
 *
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	ItemRepository itemRepository;
	
	@Override
	public ItemDetails getItemDetails(long itemCd, String localeName) {

		ItemDetails itemDetails = itemRepository.findByItemCodeAndLocale(itemCd, localeName);
		if (null == itemDetails) {
			throw new ItemNotFoundException(
					"Item with code = " + itemCd + " and locale = " + localeName + " not found");
		}
		float tax = 0.0f;
		for (ItemTaxDetails itemTaxDetails : itemDetails.getItemTaxDetails()) {
			if (null != itemTaxDetails.getTaxDetails().getRate()) {
				tax += itemTaxDetails.getTaxDetails().getRate() * itemDetails.getPrice() / 100;
			}
		}
		itemDetails.setFinalPrice(itemDetails.getPrice() + tax);

		return itemDetails;

	}

	@Override
	public ItemDetailsBean convertItemDetailsToBean(ItemDetails itemDetails) {

		float totalTax = 0.0f;
		ItemDetailsBean itemDetailsBean = new ItemDetailsBean();
		itemDetailsBean.setCategory(itemDetails.getCategory());
		itemDetailsBean.setCurrencySymbol(itemDetails.getLocaleDetails().getCurrencySymbol());
		itemDetailsBean.setFinalPrice(itemDetails.getFinalPrice());
		itemDetailsBean.setItemDescription(itemDetails.getDescription());
		itemDetailsBean.setPrice(itemDetails.getPrice());
		ItemTaxDetailsBean itemTaxDetailsBean;
		List<ItemTaxDetailsBean> itemTaxDetailsBeanList = new ArrayList<>();
		for (ItemTaxDetails itemTaxDetails : itemDetails.getItemTaxDetails()) {
			itemTaxDetailsBean = new ItemTaxDetailsBean();
			itemTaxDetailsBean.setTaxRate(itemTaxDetails.getTaxDetails().getRate());
			itemTaxDetailsBean.setTaxDescription(itemTaxDetails.getTaxDetails().getDescription());
			float taxAmount = itemTaxDetails.getTaxDetails().getRate() * itemDetails.getPrice()
					/ 100;
			itemTaxDetailsBean.setTaxAmount(taxAmount);
			totalTax += taxAmount;
			itemTaxDetailsBeanList.add(itemTaxDetailsBean);
		}
		itemDetailsBean.setTotalTax(totalTax);
		itemDetailsBean.setItemTaxDetailsBean(itemTaxDetailsBeanList);
		return itemDetailsBean;
	}

}
