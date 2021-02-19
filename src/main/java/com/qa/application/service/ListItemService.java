package com.qa.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.application.exceptions.ListItemNotFoundException;
import com.qa.application.persistence.domain.ListItem;
import com.qa.application.persistence.dto.ListItemDto;
import com.qa.application.persistence.repo.ListItemRepo;
import com.qa.application.utils.SpringBeanUtil;

@Service
public class ListItemService {

	private ListItemRepo repo;

	private ModelMapper mapper;

	private ListItemDto mapToDto(ListItem listItems) {
		return this.mapper.map(listItems, ListItemDto.class);
	}

	@Autowired
	public ListItemService(ListItemRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	public ListItemDto create(ListItem listItem) {
		return this.mapToDto(this.repo.save(listItem));
	}

	public List<ListItemDto> readAll() {
		return this.repo.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
	}

	public ListItemDto readById(Long id) {
		return this.mapToDto(this.repo.findById(id).orElseThrow(ListItemNotFoundException::new));
	}

	public ListItemDto update(ListItemDto listItemDto, Long id) {
		ListItem toUpdate = this.repo.findById(id).orElseThrow(ListItemNotFoundException::new);
		SpringBeanUtil.mergeNotNull(listItemDto, toUpdate);
		return this.mapToDto(this.repo.save(toUpdate));
	}

	public boolean delete(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

}
