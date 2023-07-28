package com.br.apifood.demo.domain.exception;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProblemaConstraintViolacao {
	
	private HttpStatus status;
	private String titulo;
	private List<String> detalhe;

}
