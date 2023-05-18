package ru.delivery.fastdelivery.exceptions;

public class NotFoundException extends RuntimeException {
	public NotFoundException(String s) {
        super(s);
    }
}
