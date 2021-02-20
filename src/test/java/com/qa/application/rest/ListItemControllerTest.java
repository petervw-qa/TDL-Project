package com.qa.application.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.application.controller.ListItemController;
import com.qa.application.persistence.domain.ListItem;
import com.qa.application.persistence.dto.ListItemDto;
import com.qa.application.service.ListItemService;

@SpringBootTest
public class ListItemControllerTest {

	@MockBean
	private ListItemService mockService;
	@Autowired
	ListItemController controller;
	@Autowired
	private ModelMapper mapper;

	private final ListItem Item_1 = new ListItem(1L, "Shopping", "Groceries", true);
	private final ListItem Item_2 = new ListItem(2L, "Exercise", "Push", true);

	private final List<ListItem> listOfItems = List.of(Item_1, Item_2);

	private ListItemDto mapToDto(ListItem listItem) {
		return this.mapper.map(listItem, ListItemDto.class);
	}

	@Test
	void createTEST() throws Exception {
		when(this.mockService.create(Item_1)).thenReturn(this.mapToDto(Item_1));
		assertThat(new ResponseEntity<ListItemDto>(this.mapToDto(Item_1), HttpStatus.CREATED))
		.isEqualTo(this.controller.create(Item_1));
		verify(this.mockService, atLeastOnce()).create(Item_1);

	}

	@Test
	void readAllTEST() throws Exception {
		List<ListItemDto> eDTOs = listOfItems.stream().map(this::mapToDto).collect(Collectors.toList());
		when(this.mockService.readAll()).thenReturn(eDTOs);
		assertThat(this.controller.readAll())
		.isEqualTo(new ResponseEntity<>(eDTOs, HttpStatus.OK));
		verify(this.mockService, atLeastOnce()).readAll();
	}

	@Test
	void readByIdTEST() throws Exception {
		final Long id = 2L;
		when(this.mockService.readById(id)).thenReturn(this.mapToDto(Item_2));
		assertThat(this.controller.readById(id))
		.isEqualTo(new ResponseEntity<ListItemDto>(this.mapToDto(Item_2), HttpStatus.OK));
		verify(this.mockService, atLeastOnce()).readById(id);
	}

	@Test
	void updateTEST() throws Exception {
		final Long id = 1L;
		final ListItemDto listItemDto = null;
		when(this.mockService.update(listItemDto, id)).thenReturn(this.mapToDto(Item_1));
		assertThat(new ResponseEntity<ListItemDto>(this.mapToDto(Item_1), HttpStatus.ACCEPTED))
			.isEqualTo(this.controller.update(id, listItemDto));
		verify(this.mockService, atLeastOnce()).update(listItemDto, id);
	}

	// testing for httpstatus.gone 410
	@Test
	void delete_path_one_TEST() throws Exception {
		when(this.mockService.delete(Item_2.getId())).thenReturn(true);
		assertThat(this.controller.delete(Item_2.getId()))
		.isEqualTo(new ResponseEntity<>(HttpStatus.GONE));
		verify(this.mockService, atLeastOnce()).delete(Item_2.getId());
	}

	// testing for httpstatus.internal_server_error 500
	@Test
	void delete_path_two_TEST() throws Exception {
		when(this.mockService.delete(Item_2.getId())).thenReturn(false);
		assertThat(this.controller.delete(Item_2.getId()))
		.isEqualTo(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
		verify(this.mockService, atLeastOnce()).delete(Item_2.getId());
	}

}
