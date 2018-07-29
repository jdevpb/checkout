package com.assessment.checkout.advice;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.assessment.checkout.exception.CheckoutError;
import com.assessment.checkout.exception.ItemNotFoundException;

/**
 * Controller advice for all controllers in the app. Currently handles
 * exceptions.
 * 
 * @author pankaj
 *
 */
@ControllerAdvice
public class CheckoutControllerAdvice {

	public static final Logger LOGGER = LoggerFactory.getLogger(CheckoutControllerAdvice.class);

	@Autowired
	private MessageSource messageSource;

	/**
	 * Handles ItemNotFoundException for get item API when item code or locale
	 * is invalid.
	 * 
	 * @param exception
	 *            - Exception
	 * @param locale
	 *            - Locale
	 * @return - ResponseEntity
	 */
	@ExceptionHandler(ItemNotFoundException.class)
	public ResponseEntity<Object> handleNotFoundException(final ItemNotFoundException exception,
			final Locale locale) {

		return sendError(exception, HttpStatus.NOT_FOUND, "item.not.found", locale);

	}

	/**
	 * Handles all exceptions other than ItemNotFoundException.
	 * 
	 * @param exception
	 *            - Exception thrown by the method
	 * @param locale
	 *            - locale in xx_XX (en_US) format
	 * @return
	 */
	// Other handlers can also be written for specific exceptions. Ignoring them
	// for POC purposes.
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleRunTimeException(final Exception exception,
			final Locale locale) {

		return sendError(exception, HttpStatus.INTERNAL_SERVER_ERROR, "server.error", locale);
	}

	private ResponseEntity<Object> sendError(final Exception exception, final HttpStatus httpStatus,
			final String messageKey, final Locale locale) {

		LOGGER.error("####EXCEPTION OCCURRED####", exception);

		CheckoutError coError = new CheckoutError();
		coError.setCode(httpStatus.value());
		coError.setMessage(messageSource.getMessage(messageKey, null, locale));

		ResponseEntity<Object> responseEntity = new ResponseEntity<>(coError, httpStatus);
		return responseEntity;
	}

}
