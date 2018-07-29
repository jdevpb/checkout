package com.assessment.checkout.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.assessment.checkout.dao.BillingRepository;
import com.assessment.checkout.entity.LocaleDetails;
import com.assessment.checkout.pojo.BillDetailsBean;
import com.assessment.checkout.pojo.ItemDetailsBean;
import com.assessment.checkout.pojo.ItemTaxDetailsBean;

@RunWith(MockitoJUnitRunner.class)
public class BillingServiceImplTest {

	@Mock
	BillingRepository billingRepository;

	@InjectMocks
	BillingServiceImpl billingServiceImpl;

	@Test
	public void testGetBillDetailsCalculateTax() {

		// Add Item 1
		ItemDetailsBean itemDetails = new ItemDetailsBean();
		itemDetails.setPrice(1000f);
		itemDetails.setFinalPrice(1100f);
		itemDetails.setTotalTax(100f);

		ItemTaxDetailsBean itemTaxDetails = new ItemTaxDetailsBean();

		itemTaxDetails.setTaxAmount(100f);
		itemTaxDetails.setTaxRate(10f);

		List<ItemTaxDetailsBean> itemTaxDetailsList = new ArrayList<>();
		itemTaxDetailsList.add(itemTaxDetails);

		itemDetails.setItemTaxDetailsBean(itemTaxDetailsList);

		List<ItemDetailsBean> itemDetailsList = new ArrayList<>();
		itemDetailsList.add(itemDetails);

		// Add Item 2
		itemDetails = new ItemDetailsBean();
		itemDetails.setPrice(100f);
		itemDetails.setFinalPrice(120f);
		itemDetails.setTotalTax(20f);

		itemTaxDetails = new ItemTaxDetailsBean();

		itemTaxDetails.setTaxAmount(20f);
		itemTaxDetails.setTaxRate(20f);

		itemTaxDetailsList = new ArrayList<>();
		itemTaxDetailsList.add(itemTaxDetails);

		itemDetails.setItemTaxDetailsBean(itemTaxDetailsList);

		itemDetailsList.add(itemDetails);

		// Add items to bill
		BillDetailsBean billDetailsBean = new BillDetailsBean();
		billDetailsBean.setItemDetailsBean(itemDetailsList);

		LocaleDetails localeDetails = new LocaleDetails();
		localeDetails.setCurrencyCode("USD");
		localeDetails.setCurrencySymbol("$");

		Mockito.when(billingRepository.findByLocale(Mockito.anyString())).thenReturn(localeDetails);

		BillDetailsBean billDetailsResponse = billingServiceImpl.getBillDetails(billDetailsBean,
				"en_US");

		assertEquals(new Float(1100), billDetailsResponse.getPreTaxAmount());
		assertEquals(new Float(1220), billDetailsResponse.getTotalAmount());
		assertEquals(new Float(120), billDetailsResponse.getTotalTax());
	}
}
