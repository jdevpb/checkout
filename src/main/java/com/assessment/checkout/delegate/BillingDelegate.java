package com.assessment.checkout.delegate;

import com.assessment.checkout.pojo.BillDetailsBean;

public interface BillingDelegate {

	/**
	 * Gets billing details.
	 * @param billDetailsBean - Input Billing details with item details
	 * @param locale - locale in xx_XX(en_US) format
	 * @return Billing Details
	 */
	BillDetailsBean getBillDetails(BillDetailsBean billDetailsBean, String locale);
}
