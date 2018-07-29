package com.assessment.checkout.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.checkout.constant.CheckoutConstants;
import com.assessment.checkout.delegate.BillingDelegate;
import com.assessment.checkout.pojo.BillDetailsBean;

/**
 * Controller for billing related services.
 * 
 * @author pankaj
 *
 */
@RestController
public class BillingController {

	@Autowired
	BillingDelegate billingDelegate;
	
	/**
	 * Gets bill details with total tax amount
	 * @param billDetails - Input bill details with item details
	 * @param locale - locale in xx_XX (en_US) format
	 * @return
	 */
	@PostMapping(value = "/bill", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody BillDetailsBean getBillDetails(@RequestBody BillDetailsBean billDetails,
			Locale locale) {

		String localeName = StringUtils.isEmpty(locale) ? CheckoutConstants.DEFAULT_LOCALE
				: locale.toString();
		return billingDelegate.getBillDetails(billDetails, localeName);
	}

}
