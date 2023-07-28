package com.br.apifood.demo.api.controller.exception;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.br.apifood.demo.domain.exception.ProblemaConstraintViolacao;
import com.br.apifood.demo.domain.exception.ProblemaDetalhe;
import com.br.apifood.demo.domain.exception.RecursoNaoEncontrado;
import com.br.apifood.demo.domain.exception.RegraDeNegocio;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionController {

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> tratadorConstraintViolationException(ConstraintViolationException ex){
		var constraints = ex.getConstraintViolations();
		var detalhes = new ArrayList<String>();
		
		constraints.forEach(constraint -> {
			detalhes.add(constraint.getMessage());
		});
		
		var problema = new ProblemaConstraintViolacao(HttpStatus.BAD_REQUEST, "Há campos nulo no corpo do JSON", detalhes);
		return ResponseEntity.status(problema.getStatus()).body(problema);
	}
	
	@ExceptionHandler(RegraDeNegocio.class)
	public ResponseEntity<?> tratadorRegraDeNegocio(RegraDeNegocio ex){
		var problema = new ProblemaDetalhe(HttpStatus.BAD_REQUEST,"Conflito com a regra de negócio.",ex.getMessage());
		return ResponseEntity.status(problema.getStatus()).body(problema);
	}
	
	@ExceptionHandler(RecursoNaoEncontrado.class)
	public ResponseEntity<?> tratadorRecursoNaoEncontrado(RecursoNaoEncontrado ex){
		var problema = new ProblemaDetalhe(HttpStatus.NOT_FOUND, "Recurso não encontrado.", ex.getMessage());
		return ResponseEntity.status(problema.getStatus()).body(problema);
	}
	
	
}
