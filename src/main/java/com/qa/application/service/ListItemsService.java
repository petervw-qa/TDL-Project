package com.qa.application.service;

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

}
