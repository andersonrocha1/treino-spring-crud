package com.andersondev.exceptions;

public class RecordNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RecordNotFoundException(Long id) {
		
		super("Não há registros para o ID informado: " + id);
		
	}
	

}
