package com.qa.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.application.persistence.domain.ListItem;
import com.qa.application.persistence.dto.ListItemDto;
import com.qa.application.persistence.repo.ListItemRepo;

@SpringBootTest
@ActiveProfiles("dev")
public class ListItemServiceTest {
	
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private ListItemService service;
	@MockBean
	private ListItemRepo mockRepo;
	
	private final ListItem Item_1 = new ListItem("Shopping","Groceries", true);
	private final ListItem Item_2 = new ListItem("Exercise","push", true);
	
	private final ListItem Item_1_Updated = new ListItem(1L, "Shopping","Groceries and Technology", false);
	private final ListItem Item_1_Gets = new ListItem(1L, Item_1_Updated.getName(), Item_1_Updated.getDescription(), true);
	
	private final List<ListItem> listOfItems = List.of(Item_1, Item_2);

	private ListItemDto mapToDto(ListItem listItem) {
		return this.mapper.map(listItem, ListItemDto.class);
	}
	
	@Test
	void createTEST() throws Exception {
		final ListItem Item_No_Id = new ListItem(null, "Shopping","Groceries", false);
		final ListItem Item_With_Id = new ListItem(1L, "Shopping","Groceries", false);
		when(this.mockRepo.save(Item_No_Id)).thenReturn(Item_With_Id);
		assertThat(this.service.create(Item_No_Id))
		.isEqualTo(this.mapToDto(Item_With_Id));
		verify(this.mockRepo, atLeastOnce()).save(Item_No_Id);
	}
	
	@Test
	void readAllTEST() throws Exception {
		List<ListItemDto> itemDtos = listOfItems.stream().map(this::mapToDto).collect(Collectors.toList());
		when(this.mockRepo.findAll()).thenReturn(listOfItems);
		assertThat(this.service.readAll())
		.isEqualTo(itemDtos);
		verify(this.mockRepo, atLeastOnce()).findAll();
	}
	
	@Test
	void readByIdTEST() throws Exception {
		final Long id = 2L;
		ListItem Test_Item_2 = new ListItem("Shopping","Groceries", true);
		when(this.mockRepo.findById(id)).thenReturn(Optional.of(Test_Item_2));
		assertThat(this.service.readById(id))
		.isEqualTo(this.mapToDto(Test_Item_2));
		verify(this.mockRepo, atLeastOnce()).findById(id);
	}
	
	@Test
	void updateTEST() throws Exception {
		final Long id = 1L;
		when(this.mockRepo.findById(id)).thenReturn(Optional.of(Item_1));
		when(this.mockRepo.save(Item_1_Gets)).thenReturn(Item_1_Gets);
		assertThat(this.service.update(this.mapToDto(Item_1_Gets), id))
		.isEqualTo(this.mapToDto(Item_1));
		verify(this.mockRepo, atLeastOnce()).findById(id);
		verify(this.mockRepo, atLeastOnce()).save(Item_1_Gets);
	}
	
	@Test
	void delete_branch_one_TEST() throws Exception {
		final Long id = 2L;
		when(this.mockRepo.existsById(id)).thenReturn(false);
		assertThat(this.service.delete(id))
		.isEqualTo(true);
		verify(this.mockRepo, atLeastOnce()).existsById(id);
	}
	
	@Test
	void delete_branch_two_TEST() throws Exception {
		// return !this.repo.existsById(id) - missing second branch here; need to test 		
	}



}
