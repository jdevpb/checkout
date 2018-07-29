package com.assessment.checkout.service;

import com.assessment.checkout.pojo.BillDetailsBean;

public interface BillingService {

	/**
	 * Generates billing details.
	 * @param billDetailsBean - Input bill details with item details
	 * @param locale - locale in xx_XX (en_US) format
	 * @return
	 */
	BillDetailsBean getBillDetails(BillDetailsBean billDetailsBean, String locale);

}
