package com.br.apifood.demo.domain.exception;

@SuppressWarnings("serial")
public class RecursoNaoEncontrado extends RuntimeException {

	public RecursoNaoEncontrado(String msg) {
		super(msg);
	}
}
