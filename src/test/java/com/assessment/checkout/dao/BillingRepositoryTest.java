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
import com.assessment.checkout.entity.LocaleDetails;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@DataJpaTest
@ContextConfiguration(classes = { App.class })
@EntityScan(basePackageClasses = { LocaleDetails.class })
public class BillingRepositoryTest {

	@Autowired
	private BillingRepository billingRepository;

	@Test
	public void testFindByLocaleEnglish() {

		LocaleDetails localeDetails = billingRepository.findByLocale("en_US");

		// Compare the values as inserted from data.sql
		assertEquals("US Dollars", localeDetails.getCurrency());
		assertEquals("USD", localeDetails.getCurrencyCode());
		assertEquals("$", localeDetails.getCurrencySymbol());
		assertEquals("en_US", localeDetails.getLocale());
		assertEquals("US English", localeDetails.getLocaleName());
	}
	
	@Test
	public void testFindByLocaleFrench() {

		LocaleDetails localeDetails = billingRepository.findByLocale("fr_FR");

		// Compare the values as inserted from data.sql
		assertEquals("Franc", localeDetails.getCurrency());
		assertEquals("CFP", localeDetails.getCurrencyCode());
		assertEquals("₣", localeDetails.getCurrencySymbol());
		assertEquals("fr_FR", localeDetails.getLocale());
		assertEquals("French", localeDetails.getLocaleName());
	}
}
