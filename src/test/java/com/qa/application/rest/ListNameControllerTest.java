package com.qa.application.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

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
		
	}
	
	@Test
	void readByIdTEST() throws Exception {
		
	}
	
	@Test
	void updateTEST() throws Exception {
		
	}
	
	@Test
	void deleteTEST() throws Exception {
		
	}
	
	

}
