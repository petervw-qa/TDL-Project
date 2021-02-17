package com.qa.application.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.application.controller.ListNameController;
import com.qa.application.persistence.domain.ListName;
import com.qa.application.persistence.dto.ListNameDto;
import com.qa.application.service.ListNameService;

@SpringBootTest
public class ListNameControllerTest {
	
	@Autowired
	ListNameController controller;
	@Autowired
	private ModelMapper mapper;
	@MockBean
	private ListNameService service;
	
	private final ListName List_1 = new ListName("Peter's List");
	private final ListName List_2 = new ListName("Mason's List");
	private final List<ListName> listOfLists = List.of(List_1, List_2);
	
	
	private ListNameDto mapToDto(ListName listName) {
		return this.mapper.map(listName, ListNameDto.class);
	}
	
	@Test
	void createTEST() throws Exception {
		when(this.service.create(List_1)).thenReturn(this.mapToDto(List_1));
		assertThat(new ResponseEntity<ListNameDto>(this.mapToDto(List_1), HttpStatus.CREATED)).isEqualTo(this.controller.create(List_1));
		verify(this.service, Mockito.times(1)).create(List_1);
	}
	
	@Test
	void readAllTEST() throws Exception {
		List<ListNameDto> nameDtos = listOfLists.stream().map(this::mapToDto).collect(Collectors.toList());
		when(this.service.readAll()).thenReturn(nameDtos);
		assertThat(this.controller.readAll()).isEqualTo(new ResponseEntity<>(nameDtos, HttpStatus.OK));
		verify(this.service, atLeastOnce()).readAll();
	}
	
	@Test
	void readOneTEST() throws Exception {
		final Long id = 2L;
		final ListNameDto listNameDto = null;
		when(this.service.update(listNameDto, id)).thenReturn(this.mapToDto(List_2));
		assertThat(new ResponseEntity<ListNameDto>(this.mapToDto(List_2), HttpStatus.OK)).isEqualTo(this.controller.readOne(id));
		verify(this.service, atLeastOnce()).readById(id);
	}
	
	@Test
	void updateTEST() throws Exception {
		final Long id = 1L;
		final ListNameDto listNameDto = null;
		when(this.service.update(listNameDto, id)).thenReturn(this.mapToDto(List_1));
		assertThat(new ResponseEntity<ListNameDto>(this.mapToDto(List_1), HttpStatus.ACCEPTED)).isEqualTo(this.controller.update(id, listNameDto));
		verify(this.service, atLeastOnce()).update(listNameDto, id);
	}
	
	@Test
	void deleteTEST() throws Exception {
		
	}
	
	

}
