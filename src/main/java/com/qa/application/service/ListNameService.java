package com.qa.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.application.exceptions.ListNameNotFoundException;
import com.qa.application.persistence.domain.ListName;
import com.qa.application.persistence.dto.ListNameDto;
import com.qa.application.persistence.repo.ListNameRepo;
import com.qa.application.utils.SpringBeanUtil;

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

	// Read All method for Service
	public List<ListNameDto> readAll() {
		return this.repo.findAll().stream().map(this::mapToDto).collect(Collectors.toList());

	}

	// Read by ID method for Service
	public ListNameDto readById(Long id) {
		return this.mapToDto(this.repo.findById(id).orElseThrow(ListNameNotFoundException::new));
	}

	// Update method for Service
	public ListNameDto update(ListNameDto listNameDto, Long id) {
		ListName toUpdate = this.repo.findById(id).orElseThrow(ListNameNotFoundException::new);
		toUpdate.setName(listNameDto.getName());
		SpringBeanUtil.mergeNotNull(listNameDto, toUpdate);
		return this.mapToDto(this.repo.save(toUpdate));
	}
	
	// Delete method for Service
		public boolean delete(Long id) {
			this.repo.deleteById(id);
			return !this.repo.existsById(id);
		}

}
