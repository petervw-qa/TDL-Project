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

import com.qa.application.persistence.domain.ListItem;
import com.qa.application.persistence.dto.ListItemDto;
import com.qa.application.service.ListItemService;

@RestController
@CrossOrigin
@RequestMapping("/listitem")
public class ListItemController {

	private ListItemService service;

	@Autowired
	public ListItemController(ListItemService service) {
		super();
		this.service = service;
	}

	@PostMapping("/create")
	public ResponseEntity<ListItemDto> create(@RequestBody ListItem listItem) {
		ListItemDto created = this.service.create(listItem);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}
	
	@GetMapping("/read")
	public ResponseEntity<List<ListItemDto>> readAll() {
		return ResponseEntity.ok(this.service.readAll());
	}

	@GetMapping("/read/{id}")
	public ResponseEntity<ListItemDto> readById(@PathVariable Long id) {
		return ResponseEntity.ok(this.service.readById(id));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<ListItemDto> update(@PathVariable Long id, @RequestBody ListItemDto listItemDto) {
		return new ResponseEntity<>(this.service.update(listItemDto, id), (HttpStatus.ACCEPTED));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ListItemDto> delete(@PathVariable Long id) {
		return this.service.delete(id) ? new ResponseEntity<>(HttpStatus.GONE)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
