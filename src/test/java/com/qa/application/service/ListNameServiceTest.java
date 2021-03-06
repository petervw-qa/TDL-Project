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

import com.qa.application.persistence.domain.ListName;
import com.qa.application.persistence.dto.ListNameDto;
import com.qa.application.persistence.repo.ListNameRepo;

@SpringBootTest
@ActiveProfiles("dev")
public class ListNameServiceTest {

	@Autowired
	private ListNameService service;
	@MockBean
	private ListNameRepo mockRepo;
	@Autowired
	private ModelMapper mapper;

	private final ListName List_1 = new ListName("Monday");
	private final ListName List_2 = new ListName("Tuesday");
	
	private final ListName List_1_Updated = new ListName(null, "Monday Extended");
	private final ListName List_1_Gets = new ListName(1L, List_1_Updated.getName());
	
	private final List<ListName> listOfLists = List.of(List_1, List_2);

	private ListNameDto mapToDto(ListName listName) {
		return this.mapper.map(listName, ListNameDto.class);
	}

	@Test
	void createTEST() throws Exception {
		final ListName List_No_Id = new ListName(null, "Monday");
		final ListName List_With_Id = new ListName(1L, "Monday");
		when(this.mockRepo.save(List_No_Id)).thenReturn(List_With_Id);
		assertThat(this.service.create(List_No_Id))
		.isEqualTo(this.mapToDto(List_With_Id));
		verify(this.mockRepo, atLeastOnce()).save(List_No_Id);
	}

	@Test
	void readAllTEST() throws Exception {
		List<ListNameDto> nameDtos = listOfLists.stream().map(this::mapToDto).collect(Collectors.toList());
		when(this.mockRepo.findAll()).thenReturn(listOfLists);
		assertThat(this.service.readAll())
		.isEqualTo(nameDtos);
		verify(this.mockRepo, atLeastOnce()).findAll();
	}

	@Test
	void readByIdTEST() throws Exception {
		final Long id = 2L;
		ListName Test_List_2 = new ListName(id, "Tuesday");
		when(this.mockRepo.findById(id)).thenReturn(Optional.of(Test_List_2));
		assertThat(this.service.readById(id))
		.isEqualTo(this.mapToDto(Test_List_2));
		verify(this.mockRepo, atLeastOnce()).findById(id);
	}

	@Test
	void updateTEST() throws Exception {
		final Long id = 1L;
		when(this.mockRepo.findById(id)).thenReturn(Optional.of(List_1));
		when(this.mockRepo.save(List_1_Gets)).thenReturn(List_1_Gets);
		assertThat(this.service.update(this.mapToDto(List_1_Gets), id))
		.isEqualTo(this.mapToDto(List_1));
		verify(this.mockRepo, atLeastOnce()).findById(id);
		verify(this.mockRepo, atLeastOnce()).save(List_1_Gets);
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
		// return !this.repo.existsById(id); pathway missed, next test objective
	}
}
