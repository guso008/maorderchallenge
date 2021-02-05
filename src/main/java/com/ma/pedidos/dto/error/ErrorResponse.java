package com.ma.pedidos.dto.error;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ErrorResponse {

	@JsonProperty("Errores")
	private List<Error> errors;

	public ErrorResponse(List<Error> errors) {
		super();
		this.errors = errors;
	}

	public List<Error> getErrors() {
		return errors;
	}

	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}

}
