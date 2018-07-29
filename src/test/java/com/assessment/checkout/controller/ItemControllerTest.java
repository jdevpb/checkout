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
import com.assessment.checkout.delegate.ItemDelegate;
import com.assessment.checkout.pojo.ItemDetailsBean;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ItemController.class, secure = false)
@ContextConfiguration(classes = { App.class })
public class ItemControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ItemDelegate itemDelegate;

	@Test
	public void testGetItemDetails() throws Exception {

		ItemDetailsBean itemDetailsBean = new ItemDetailsBean();
		itemDetailsBean.setCategory("Category A");
		itemDetailsBean.setCurrencySymbol("$");
		itemDetailsBean.setFinalPrice(2400f);
		itemDetailsBean.setItemDescription("Test");
		itemDetailsBean.setPrice(2000f);
		itemDetailsBean.setTotalTax(400f);

		Mockito.when(itemDelegate.getItemDetails(Mockito.anyLong(), Mockito.anyString()))
				.thenReturn(itemDetailsBean);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/item/1")
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE).header("Accept-Language", "en-US");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		// Http status code 200 should be returned
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());

		// All returned values should be same as set in the mock bean
		assertEquals("Category A", itemDetailsBean.getCategory());
		assertEquals("$", itemDetailsBean.getCurrencySymbol());
		assertEquals("Test", itemDetailsBean.getItemDescription());
		assertEquals(new Float(2400), itemDetailsBean.getFinalPrice());
		assertEquals(new Float(2000), itemDetailsBean.getPrice());
		assertEquals(new Float(400), itemDetailsBean.getTotalTax());

		// ItemTaxDetailsBean should not be returned since it is not set
		assertNull(itemDetailsBean.getItemTaxDetailsBean());

	}

}
