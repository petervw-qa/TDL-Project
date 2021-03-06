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

import com.qa.application.persistence.domain.ListName;
import com.qa.application.persistence.dto.ListNameDto;
import com.qa.application.service.ListNameService;

@RestController
@CrossOrigin
@RequestMapping("/listname")
public class ListNameController {

	private ListNameService service;

	@Autowired
	public ListNameController(ListNameService service) {
		super();
		this.service = service;
	}

	@PostMapping("/create")
	public ResponseEntity<ListNameDto> create(@RequestBody ListName listName) {
		ListNameDto created = this.service.create(listName);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}

	@GetMapping("/read")
	public ResponseEntity<List<ListNameDto>> readAll() {
		return ResponseEntity.ok(this.service.readAll());
	}

	@GetMapping("/read/{id}")
	public ResponseEntity<ListNameDto> readById(@PathVariable Long id) {
		return ResponseEntity.ok(this.service.readById(id));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<ListNameDto> update(@PathVariable Long id, @RequestBody ListNameDto listName) {
		return new ResponseEntity<>(this.service.update(listName, id), (HttpStatus.ACCEPTED));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ListNameDto> delete(@PathVariable Long id) {
		return this.service.delete(id) ? new ResponseEntity<>(HttpStatus.GONE)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
