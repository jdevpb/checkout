package com.assessment.checkout.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.assessment.checkout.App;
import com.assessment.checkout.delegate.BillingDelegate;
import com.assessment.checkout.pojo.BillDetailsBean;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = BillingController.class, secure = false)
@ContextConfiguration(classes = { App.class, BillingController.class })
public class BillingControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BillingDelegate billingDelegate;

	@Test
	public void testGetBillDetails() throws Exception {

		BillDetailsBean billDetailsBean = new BillDetailsBean();
		billDetailsBean.setTotalTax(400f);
		billDetailsBean.setCurrency("$");
		billDetailsBean.setPreTaxAmount(2000f);

		Mockito.when(billingDelegate.getBillDetails(Mockito.any(BillDetailsBean.class),
				Mockito.anyString())).thenReturn(billDetailsBean);

		String request = "{\"itemDetailsBean\":[{\"itemDescription\":\"Saffola Vegetable Oil\",\"category\":\"Category C\",\"currencySymbol\":\"$\",\"price\":7.2,\"finalPrice\":7.2,\"totalTax\":0,\"itemTaxDetailsBean\":[{\"taxDescription\":\"Sales Tax\",\"taxRate\":0,\"taxAmount\":0}]},{\"itemDescription\":\"Kellogs Original\",\"category\":\"Category B\",\"currencySymbol\":\"$\",\"price\":2,\"finalPrice\":2.4,\"totalTax\":0.4,\"itemTaxDetailsBean\":[{\"taxDescription\":\"Sales Tax\",\"taxRate\":20,\"taxAmount\":0.4}]},{\"itemDescription\":\"MacBook Pro 15\",\"category\":\"Category A\",\"currencySymbol\":\"$\",\"price\":2000,\"finalPrice\":2200,\"totalTax\":200,\"itemTaxDetailsBean\":[{\"taxDescription\":\"Sales Tax\",\"taxRate\":10,\"taxAmount\":200}]}]}";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/bill").content(request)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE).header("Accept-Language", "en-US");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		// Http status code 200 should be returned
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());

		// All returned values should be same as set in the mock bean
		assertEquals(new Float(400), billDetailsBean.getTotalTax());
		assertEquals(new Float(2000), billDetailsBean.getPreTaxAmount());
		assertEquals("$", billDetailsBean.getCurrency());

		// Total amount should not be returned since it is not set
		assertNull(billDetailsBean.getTotalAmount());

	}

}
