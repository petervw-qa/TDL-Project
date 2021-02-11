package com.qa.application.service;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.application.dto.ListNameDto;
import com.qa.application.persistence.domain.ListName;
import com.qa.application.persistence.repo.ListNameRepo;

@Service
public class ListNameService {
	
	private ListNameRepo repo;
	
	private ModelMapper mapper;
	
	private ListNameDto mapToDto(ListName listname) {
		return this.mapper.map(listname, ListNameDto.class);
	}
	
	@Autowired
	public ListNameService(ListNameRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	
	// Create method for Service
		public ListNameDto create(ListName listName) {
			return this.mapToDto(this.repo.save(listName));
		}
	
		// Read All
		public List<ListNameDto> readAll() {
			return this.repo.findAll().stream().map(this::mapToDto).collect(Collectors.toList());

		}

		// Read by Id
		public ListNameDto readById(Long id) {
			return this.mapToDto(this.repo.findById(id).orElseThrow(ListNameNotFoundException::new));
		}	
	

}
