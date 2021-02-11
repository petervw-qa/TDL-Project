package com.qa.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.application.dto.ListNameDto;
import com.qa.application.persistence.domain.ListName;
import com.qa.application.service.ListNameService;

@RestController
@CrossOrigin
@RequestMapping("/listname")
public class ListNameController {
	
	private ListNameService service;
	
//	Create Method for Controller
	@PostMapping("/create")
	public ResponseEntity<ListNameDto> create(@RequestBody ListName ListName) {
		ListName created = this.service.create(ListName);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}

}
