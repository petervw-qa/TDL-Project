package com.qa.application.service;

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
	
	
	
	

}
