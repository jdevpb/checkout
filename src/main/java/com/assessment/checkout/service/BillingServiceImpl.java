package com.assessment.checkout.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assessment.checkout.dao.BillingRepository;
import com.assessment.checkout.entity.LocaleDetails;
import com.assessment.checkout.pojo.BillDetailsBean;
import com.assessment.checkout.pojo.ItemDetailsBean;

/**
 * Service class to provide billing related services.
 * 
 * @author pankaj
 *
 */
@Service
public class BillingServiceImpl implements BillingService {

	@Autowired
	BillingRepository billingRepository;

	/**
	 * Generates bill details such as total amount, pre tax amount, total tax
	 * and item level details like unit price, tax, total price.
	 */
	@Override
	public BillDetailsBean getBillDetails(BillDetailsBean billDetailsBean, String locale) {

		float totalAmount = 0.0f;
		float preTaxAmount = 0.0f;
		float totalTaxAmount = 0.0f;
		List<ItemDetailsBean> itemDetailsList = billDetailsBean.getItemDetailsBean();
		for (ItemDetailsBean itemDetails : itemDetailsList) {

			preTaxAmount += itemDetails.getPrice();
			totalAmount += itemDetails.getFinalPrice();
			totalTaxAmount += itemDetails.getTotalTax();

		}
		billDetailsBean.setPreTaxAmount(preTaxAmount);
		billDetailsBean.setTotalAmount(totalAmount);
		billDetailsBean.setTotalTax(totalTaxAmount);
		LocaleDetails localeDetails = billingRepository
				.findByLocale(locale);
		if (null != localeDetails) {
			billDetailsBean.setCurrency(
					billingRepository.findByLocale(locale.toString()).getCurrencySymbol());
		}
		return billDetailsBean;
	}

}
