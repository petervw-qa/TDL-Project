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

import com.qa.application.controller.ListItemController;
import com.qa.application.persistence.domain.ListItem;
import com.qa.application.persistence.dto.ListItemDto;
import com.qa.application.service.ListItemService;

@SpringBootTest
public class ListItemControllerTest {
	
	@MockBean // Mock
	ListItemService mockService;
	@Autowired /// Inject mocks
	ListItemController controller;
	@Autowired
	ModelMapper mapper;
	
	private final ListItem Item_1 = new ListItem("Exercise","Chest Day", false);
	private final ListItem Item_2 = new ListItem("Shopping","Lidl has the best deals", false);
	private final List<ListItem> listOfItemss = List.of(Item_1, Item_2);
	
	private ListItemDto mapToDto(ListItem listItem) {
		return this.mapper.map(listItem, ListItemDto.class);
	}
	
	@Test
	void createTEST() throws Exception {
		when(this.mockService.create(Item_1)).thenReturn(this.mapToDto(Item_1));
		assertThat(new ResponseEntity<ListItemDto>(this.mapToDto(Item_1), HttpStatus.CREATED))
		.isEqualTo(this.controller.create(Item_1));
		verify(this.mockService, Mockito.times(1)).create(Item_1);
	}
	
	@Test
	void readAllTEST() throws Exception {
		List<ListItemDto> itemDtos = listOfItemss.stream().map(this::mapToDto).collect(Collectors.toList());
		when(this.mockService.readAll()).thenReturn(itemDtos);
		assertThat(this.controller.readAll()).isEqualTo(new ResponseEntity<>(itemDtos, HttpStatus.OK));
		verify(this.mockService, atLeastOnce()).readAll();
	}
	
	@Test
	void readOneTEST() throws Exception {
		final Long id = 2L;
		final ListItemDto listItemDto = this.mapToDto(Item_2);
		when(this.mockService.readById(id)).thenReturn(listItemDto);
		ResponseEntity<ListItemDto> expected = ResponseEntity.ok(listItemDto);
		ResponseEntity<ListItemDto> actual = this.controller.readById(id);
		assertEquals(expected,actual);
		verify(this.mockService, times(1)).readById(id);
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
	
	@Test
	void deleteTEST() throws Exception {
		when(this.mockService.delete(Item_2.getId())).thenReturn(false);
		assertThat(this.controller.delete(Item_2.getId()))
				.isEqualTo(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
		verify(this.mockService, atLeastOnce()).delete(Item_2.getId());
	}

}
