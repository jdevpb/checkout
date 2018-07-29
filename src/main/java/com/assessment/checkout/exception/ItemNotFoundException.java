package com.assessment.checkout.exception;

public class ItemNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 419412707514319615L;
	
	public ItemNotFoundException(String message) {
		super(message);
	}

}
