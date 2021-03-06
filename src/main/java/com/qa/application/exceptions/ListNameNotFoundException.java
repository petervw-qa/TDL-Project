package com.qa.application.exceptions;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE, reason = "List not found with that id. Try again!")

public class ListNameNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = -595780099725851137L;

}