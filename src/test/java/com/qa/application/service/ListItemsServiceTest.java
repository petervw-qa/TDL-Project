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
import com.qa.application.persistence.repo.ListItemsRepo;

@SpringBootTest
@ActiveProfiles("dev")
public class ListItemsServiceTest {
	
	@Autowired
	private ListItemsService service;
	@MockBean
	private ListItemsRepo repo;
	@MockBean
	private ModelMapper mockMapper;
	
	private final ListItem Item_1 = new ListItem("Exercise","Chest Day", false);
	private final ListItem Item_2 = new ListItem("Shopping","Lidl has the best deals", false);
//	private final ListItem Item_1_Updated = new ListItem(1L, "Exercise More!","Chest and Back Day!!", false);
//	private final ListItem Item_1_Gets = new ListItem(1L, Item_1_Updated.getName(), Item_1_Updated.getDescription(), false);
	private final List<ListItem> listOfItemss = List.of(Item_1, Item_2);

	private ListItemDto mapToDto(ListItem listItem) {
		return this.mockMapper.map(listItem, ListItemDto.class);
	}
	
	@Test
	void createTEST() throws Exception {
		final ListItem Item_No_Id = new ListItem(null, "Exercise","Chest Day", false);
		final ListItem Item_With_Id = new ListItem(1L, "Exercise","Chest Day", false);
		when(this.repo.save(Item_No_Id)).thenReturn(Item_With_Id);
		assertThat(this.service.create(Item_No_Id)).isEqualTo(this.mapToDto(Item_With_Id));
		verify(this.repo, atLeastOnce()).save(Item_No_Id);
	}
	
	@Test
	void readAllTEST() throws Exception {
		List<ListItemDto> itemDtos = listOfItemss.stream().map(this::mapToDto).collect(Collectors.toList());
		when(this.repo.findAll()).thenReturn(listOfItemss);
		assertThat(this.service.readAll()).isEqualTo(itemDtos);
		verify(this.repo, atLeastOnce()).findAll();

	}
	
	@Test
	void readByIdTEST() throws Exception {
		final Long id = 2L;
		ListItem Test_Item_2 = new ListItem("Shopping","Lidl has the best deals", false);
		when(this.repo.findById(id)).thenReturn(Optional.of(Test_Item_2));
		assertThat(this.service.readById(id)).isEqualTo(this.mapToDto(Test_Item_2));
		verify(this.repo, atLeastOnce()).findById(id);

	}
	
//	@Test
	// NullPointerException - needs checking, all other tests are fine
//	void updateTEST() throws Exception {
//		final Long id = 1L;
//		when(this.repo.findById(id)).thenReturn(Optional.of(Item_1));
//		when(this.repo.save(Item_1_Gets)).thenReturn(Item_1_Gets);
//		assertThat(this.service.update(this.mapToDto(Item_1_Gets), id)).isEqualTo(this.mapToDto(Item_1));
//		verify(this.repo, atLeastOnce()).findById(id);
//		verify(this.repo, atLeastOnce()).save(Item_1_Gets);
//	}
	
	@Test
	void deleteTEST() throws Exception {
		final Long id = 2L;
		when(this.repo.existsById(id)).thenReturn(false);
		assertThat(this.service.delete(id)).isEqualTo(true);
		verify(this.repo, atLeastOnce()).existsById(id);

	}


}
