package com.assessment.checkout.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.assessment.checkout.App;
import com.assessment.checkout.entity.ItemDetails;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@DataJpaTest
@ContextConfiguration(classes = { App.class })
@EntityScan(basePackageClasses = { ItemDetails.class })
public class ItemRepositoryTest {

	@Autowired
	private ItemRepository itemRepository;

	@Test
	public void testFindByItemCodeAndLocaleEnglish() {

		ItemDetails itemDetails = itemRepository.findByItemCodeAndLocale(1L, "en_US");

		// Compare the values as inserted from data.sql
		assertEquals("MacBook", itemDetails.getItemMaster().getItemName());
		assertEquals(new Long(1), itemDetails.getItemMaster().getItemCode());
		assertEquals("Category A", itemDetails.getCategory());
		assertEquals("MacBook Pro 15", itemDetails.getDescription());
		assertEquals(new Long(1), itemDetails.getItemCode());
		assertEquals("Category A", itemDetails.getItemTaxDetails().get(0).getCategory());
		assertEquals("en_US", itemDetails.getItemTaxDetails().get(0).getLocale());
		assertEquals("ST10", itemDetails.getItemTaxDetails().get(0).getTaxCode());
		assertEquals("Sales Tax",
				itemDetails.getItemTaxDetails().get(0).getTaxDetails().getDescription());
		assertEquals(new Float(10),
				itemDetails.getItemTaxDetails().get(0).getTaxDetails().getRate());
		assertEquals("ST10", itemDetails.getItemTaxDetails().get(0).getTaxDetails().getTaxCode());
		assertEquals("en_US", itemDetails.getLocale());
		assertEquals("US Dollars", itemDetails.getLocaleDetails().getCurrency());
		assertEquals("USD", itemDetails.getLocaleDetails().getCurrencyCode());
		assertEquals("$", itemDetails.getLocaleDetails().getCurrencySymbol());
		assertEquals(new Float(2000), itemDetails.getPrice());

	}

	@Test
	public void testFindByItemCodeAndLocaleFrench() {

		ItemDetails itemDetails = itemRepository.findByItemCodeAndLocale(1L, "fr_FR");

		// Compare the values as inserted from data.sql
		assertEquals("MacBook", itemDetails.getItemMaster().getItemName());
		assertEquals(new Long(1), itemDetails.getItemMaster().getItemCode());
		assertEquals("Category A", itemDetails.getCategory());
		assertEquals("MacBook Pro 15(French)", itemDetails.getDescription());
		assertEquals(new Long(1), itemDetails.getItemCode());
		assertEquals("Category A", itemDetails.getItemTaxDetails().get(0).getCategory());
		assertEquals("fr_FR", itemDetails.getItemTaxDetails().get(0).getLocale());
		assertEquals("ST10FR", itemDetails.getItemTaxDetails().get(0).getTaxCode());
		assertEquals("Sales Tax (France)",
				itemDetails.getItemTaxDetails().get(0).getTaxDetails().getDescription());
		assertEquals(new Float(10),
				itemDetails.getItemTaxDetails().get(0).getTaxDetails().getRate());
		assertEquals("ST10FR", itemDetails.getItemTaxDetails().get(0).getTaxDetails().getTaxCode());
		assertEquals("fr_FR", itemDetails.getLocale());
		assertEquals("Franc", itemDetails.getLocaleDetails().getCurrency());
		assertEquals("CFP", itemDetails.getLocaleDetails().getCurrencyCode());
		assertEquals("₣", itemDetails.getLocaleDetails().getCurrencySymbol());
		assertEquals(new Float(1600), itemDetails.getPrice());

	}
}
