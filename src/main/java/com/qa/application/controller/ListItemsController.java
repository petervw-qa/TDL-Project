package com.qa.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.application.dto.ListItemsDto;
import com.qa.application.persistence.domain.ListItems;
import com.qa.application.service.ListItemsService;

@RestController
@CrossOrigin
@RequestMapping("/listitems")
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

//	Read functionality for Controller
	@GetMapping("/read")
	public ResponseEntity<List<ListItemsDto>> readAll() {
		return ResponseEntity.ok(this.service.readAll());
	}

//	Read by ID functionality for Controller
	@GetMapping("/read/{id}")
	public ResponseEntity<ListItemsDto> readOne(@PathVariable Long id) {
		return ResponseEntity.ok(this.service.readById(id));
	}

//	Update functionality for Controller
	@PutMapping("/update/{id}")
	public ResponseEntity<ListItemsDto> update(@PathVariable Long id, @RequestBody ListItemsDto listItemsDto) {
		return new ResponseEntity<>(this.service.update(listItemsDto, id), HttpStatus.ACCEPTED);
	}

//	Delete functionality for controller
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ListItemsDto> delete(@PathVariable Long id) {
		return this.service.delete(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
