package com.trackyourweight.exception;

public class WeightAnomalyException extends Exception {
	private static final long serialVersionUID = 1L;

	public WeightAnomalyException (String message) {
	    super(message);
	}
}