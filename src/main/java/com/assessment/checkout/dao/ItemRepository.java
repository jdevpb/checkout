package com.assessment.checkout.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.assessment.checkout.entity.ItemDetails;

/**
 * Repository for item.
 * 
 * @author pankaj
 *
 */
public interface ItemRepository extends CrudRepository<ItemDetails, Long> {

	/**
	 * Select item details from the DB. Joins on item code and locale
	 * 
	 * @param itemCd
	 *            - Item Code
	 * @param locale
	 *            - Locale in xx_XX(en_US) format
	 * @return ItemDetails Entity
	 */
	@Query("select itemdetails from ITEMDETAILS itemdetails join fetch itemdetails.itemMaster itemmaster "
			+ "join fetch itemdetails.itemTaxDetails itemtaxdetails "
			+ "where itemmaster.itemCode = :itemCd and itemdetails.locale= :locale ")
	ItemDetails findByItemCodeAndLocale(@Param("itemCd") Long itemCd,
			@Param("locale") String locale);

}
