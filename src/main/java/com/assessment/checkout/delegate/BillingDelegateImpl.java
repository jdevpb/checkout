package com.assessment.checkout.delegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.assessment.checkout.pojo.BillDetailsBean;
import com.assessment.checkout.service.BillingService;

/** 
 * Class to delegate request to one or more services.
 * @author pankaj
 *
 */
@Component
public class BillingDelegateImpl implements BillingDelegate {

	@Autowired 
	BillingService billingService;
	
	@Override
	public BillDetailsBean getBillDetails(BillDetailsBean billDetailsBean, String locale) {
		
		return billingService.getBillDetails(billDetailsBean, locale);
	}

}
