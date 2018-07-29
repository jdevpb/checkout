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

import com.assessment.checkout.dao.ItemRepository;
import com.assessment.checkout.entity.ItemDetails;
import com.assessment.checkout.entity.ItemTaxDetails;
import com.assessment.checkout.entity.LocaleDetails;
import com.assessment.checkout.entity.TaxDetails;
import com.assessment.checkout.pojo.ItemDetailsBean;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceImplTest {

	@Mock
	ItemRepository itemRepository;

	@InjectMocks
	ItemServiceImpl itemServiceImpl;

	@Test
	public void testGetItemDetailsFinalPrice() {

		ItemDetails itemDetails = new ItemDetails();
		itemDetails.setPrice(1000f);

		ItemTaxDetails itemTaxDetails = new ItemTaxDetails();

		TaxDetails taxDetails = new TaxDetails();
		taxDetails.setRate(10);
		itemTaxDetails.setTaxDetails(taxDetails);

		List<ItemTaxDetails> itemTaxDetailsList = new ArrayList<>();
		itemTaxDetailsList.add(itemTaxDetails);

		itemDetails.setItemTaxDetails(itemTaxDetailsList);

		Mockito.when(itemRepository.findByItemCodeAndLocale(Mockito.anyLong(), Mockito.anyString()))
				.thenReturn(itemDetails);

		ItemDetails itemDetailsResponse = itemServiceImpl.getItemDetails(1, "en_US");

		assertEquals(new Float(1100), itemDetailsResponse.getFinalPrice());
	}

	@Test
	public void testConvertItemDetailsToBeanCalculateTax() {

		ItemDetails itemDetails = new ItemDetails();
		itemDetails.setPrice(1000f);

		LocaleDetails localeDetails = new LocaleDetails();
		localeDetails.setCurrencyCode("$");
		itemDetails.setLocaleDetails(localeDetails);

		TaxDetails taxDetails = new TaxDetails();
		taxDetails.setRate(10);

		ItemTaxDetails itemTaxDetails = new ItemTaxDetails();
		itemTaxDetails.setTaxDetails(taxDetails);

		List<ItemTaxDetails> itemTaxDetailsList = new ArrayList<>();
		itemTaxDetailsList.add(itemTaxDetails);

		itemDetails.setItemTaxDetails(itemTaxDetailsList);

		ItemDetailsBean itemDetailsBean = itemServiceImpl.convertItemDetailsToBean(itemDetails);

		// Test if the tax is calculated correctly and set as expected
		assertEquals(new Float(100), itemDetailsBean.getItemTaxDetailsBean().get(0).getTaxAmount());
	}

	@Test
	public void testConvertItemDetailsToBeanSetValues() {

		ItemDetails itemDetails = new ItemDetails();
		itemDetails.setPrice(1000f);
		itemDetails.setCategory("Category A");
		itemDetails.setDescription("Mac Book Pro 15");
		itemDetails.setFinalPrice(1100f);

		LocaleDetails localeDetails = new LocaleDetails();
		localeDetails.setCurrencyCode("USD");
		localeDetails.setCurrencySymbol("$");
		itemDetails.setLocaleDetails(localeDetails);

		TaxDetails taxDetails = new TaxDetails();
		taxDetails.setRate(10);
		taxDetails.setDescription("Goods & Services Tax");
		taxDetails.setTaxCode("GST");

		ItemTaxDetails itemTaxDetails = new ItemTaxDetails();
		itemTaxDetails.setTaxDetails(taxDetails);

		List<ItemTaxDetails> itemTaxDetailsList = new ArrayList<>();
		itemTaxDetailsList.add(itemTaxDetails);

		itemDetails.setItemTaxDetails(itemTaxDetailsList);

		ItemDetailsBean itemDetailsBean = itemServiceImpl.convertItemDetailsToBean(itemDetails);

		// Test if the values have been set in the response bean
		assertEquals("Category A", itemDetailsBean.getCategory());
		assertEquals("$", itemDetailsBean.getCurrencySymbol());
		assertEquals(new Float(1100), itemDetailsBean.getFinalPrice());
		assertEquals("Mac Book Pro 15", itemDetailsBean.getItemDescription());
		assertEquals(new Float(1000), itemDetailsBean.getPrice());
		assertEquals("Goods & Services Tax",
				itemDetailsBean.getItemTaxDetailsBean().get(0).getTaxDescription());
		assertEquals(new Float(10), itemDetailsBean.getItemTaxDetailsBean().get(0).getTaxRate());
	}

}
