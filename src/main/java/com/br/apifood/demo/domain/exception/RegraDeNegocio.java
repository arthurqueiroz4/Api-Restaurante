package com.br.apifood.demo.domain.exception;

@SuppressWarnings("serial")
public class RegraDeNegocio extends RuntimeException{

	public RegraDeNegocio(String msg) {
		super(msg);
	}
}
