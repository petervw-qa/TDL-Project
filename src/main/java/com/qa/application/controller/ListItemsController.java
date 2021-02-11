package com.qa.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.qa.application.dto.ListItemsDto;
import com.qa.application.persistence.domain.ListItems;
import com.qa.application.service.ListItemsService;

public class ListItemsController {

	private ListItemsService service;

	@Autowired
	public ListItemsController(ListItemsService service) {
		super();
		this.service = service;
	}

//	Create functionality for Controller
	@PostMapping("/create")
	public ResponseEntity<ListItemsDto> create(@RequestBody ListItems listItems) {
		ListItemsDto created = this.service.create(listItems);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}

}
