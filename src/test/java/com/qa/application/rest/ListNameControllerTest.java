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
import org.springframework.test.context.ActiveProfiles;

import com.qa.application.controller.ListNameController;
import com.qa.application.persistence.domain.ListName;
import com.qa.application.persistence.dto.ListNameDto;
import com.qa.application.service.ListNameService;

@SpringBootTest
@ActiveProfiles("dev")
public class ListNameControllerTest {

	@MockBean
	private ListNameService mockService;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	ListNameController controller;

	private final ListName List_1 = new ListName(1L, "Monday");
	private final ListName List_2 = new ListName(2L, "Tuesday");

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
		assertThat(this.controller.readAll())
		.isEqualTo(new ResponseEntity<>(nameDtos, HttpStatus.OK));
		verify(this.mockService, atLeastOnce()).readAll();
	}

	@Test
	void readByIdTEST() throws Exception {
		final Long id = 2L;
		when(this.mockService.readById(id)).thenReturn(this.mapToDto(List_2));
		assertThat(new ResponseEntity<ListNameDto>(this.mapToDto(List_2), HttpStatus.OK))
		.isEqualTo(this.controller.readById(id));
		verify(this.mockService, atLeastOnce()).readById(id);
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

	// testing for httpstatus.gone 410
	@Test
	void delete_path_one_TEST() throws Exception {
		when(this.mockService.delete(List_2.getId())).thenReturn(true);
		assertThat(this.controller.delete(List_2.getId()))
		.isEqualTo(new ResponseEntity<>(HttpStatus.GONE));
		verify(this.mockService, atLeastOnce()).delete(List_2.getId());
	}

	// testing for httpstatus.internal_server_error 500
	@Test
	void delete_path_two_TEST() throws Exception {
		when(this.mockService.delete(List_2.getId())).thenReturn(false);
		assertThat(this.controller.delete(List_2.getId()))
		.isEqualTo(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
		verify(this.mockService, atLeastOnce()).delete(List_2.getId());
	}

}
