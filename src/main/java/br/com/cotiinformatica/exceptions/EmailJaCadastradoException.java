package br.com.cotiinformatica.exceptions;

public class EmailJaCadastradoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage() {
		return "email: Este e-mail já está cadastrado. Tente outro.";
	}

}
