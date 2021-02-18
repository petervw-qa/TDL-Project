package com.qa.application.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
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

	@MockBean // Mock
	ListNameService mockService;
	@Autowired /// Inject mocks
	ListNameController controller;
	@Autowired
	ModelMapper mapper;

	private final ListName List_1 = new ListName("Peter's List");
	private final ListName List_2 = new ListName("Mason's List");
	private final List<ListName> listOfLists = List.of(List_1, List_2);

	private ListNameDto mapToDto(ListName listName) {
		return this.mapper.map(listName, ListNameDto.class);
	}

	@Test
	void createTEST() throws Exception {
		when(this.mockService.create(List_1)).thenReturn(this.mapToDto(List_1));
		assertThat(new ResponseEntity<ListNameDto>(this.mapToDto(List_1), HttpStatus.CREATED))
		.isEqualTo(this.controller.create(List_1));
		verify(this.mockService, Mockito.times(1)).create(List_1);
	}

	@Test
	void readAllTEST() throws Exception {
		List<ListNameDto> nameDtos = listOfLists.stream().map(this::mapToDto).collect(Collectors.toList());
		when(this.mockService.readAll()).thenReturn(nameDtos);
		assertThat(this.controller.readAll()).isEqualTo(new ResponseEntity<>(nameDtos, HttpStatus.OK));
		verify(this.mockService, atLeastOnce()).readAll();
	}

	@Test
	void readOneTEST() throws Exception {
		final Long id = 2L;
		final ListNameDto listNameDto = this.mapToDto(List_2);
		when(this.mockService.readById(id)).thenReturn(listNameDto);
		ResponseEntity<ListNameDto> expected = ResponseEntity.ok(listNameDto);
		ResponseEntity<ListNameDto> actual = this.controller.readOne(id);
		assertEquals(expected,actual);
		verify(this.mockService, times(1)).readById(id);
	}

	@Test
	void updateTEST() throws Exception {
		final Long id = 1L;
		final ListNameDto listNameDto = null;
		when(this.mockService.update(listNameDto, id)).thenReturn(this.mapToDto(List_1));
		assertThat(new ResponseEntity<ListNameDto>(this.mapToDto(List_1), HttpStatus.ACCEPTED))
				.isEqualTo(this.controller.update(id, listNameDto));
		verify(this.mockService, atLeastOnce()).update(listNameDto, id);
	}

	@Test
	void deleteTEST() throws Exception {
		when(this.mockService.delete(List_2.getId())).thenReturn(false);
		assertThat(this.controller.delete(List_2.getId()))
				.isEqualTo(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
		verify(this.mockService, atLeastOnce()).delete(List_2.getId());
	}

}
