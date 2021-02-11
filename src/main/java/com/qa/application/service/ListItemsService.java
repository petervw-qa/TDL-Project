package com.qa.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.qa.application.dto.ListItemsDto;
import com.qa.application.persistence.domain.ListItems;
import com.qa.application.persistence.repo.ListItemsRepo;

public class ListItemsService {

	private ListItemsRepo repo;

	private ModelMapper mapper;

	private ListItemsDto mapToDto(ListItems listItems) {
		return this.mapper.map(listItems, ListItemsDto.class);
	}

	@Autowired
	public ListItemsService(ListItemsRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	// Create method for Service
	public ListItemsDto create(ListItems listItems) {
		return this.mapToDto(this.repo.save(listItems));
	}

	// Read All method for Service
	public List<ListItemsDto> readllAll() {
		return this.repo.findAll().stream().map(this::mapToDto).collect(Collectors.toList());

	}

	// Read by ID method for Service
	public ListItemsDto readById(Long id) {
		return this.mapToDto(this.repo.findById(id).orElseThrow());
	}

}
