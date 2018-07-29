package com.assessment.checkout.dao;

import org.springframework.data.repository.CrudRepository;

import com.assessment.checkout.entity.LocaleDetails;

public interface BillingRepository extends CrudRepository<LocaleDetails, String> {

	LocaleDetails findByLocale(String locale);

}
