package com.qa.application.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.application.persistence.domain.ListItem;
import com.qa.application.persistence.dto.ListItemDto;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
@Sql(scripts = { "classpath:application-schema.sql",
		"classpath:application-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class ListItemsControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private ObjectMapper jsonifier;

	private final String URI = "/listitems";
//	private final ListName List_1 = new ListName("Peter List");
//	private final ListName List_2 = new ListName("Mason List");
//	private final ListName List_3 = new ListName("Artur List");
	private final ListItem Item_1 = new ListItem(1L, "Groceries Shop", "Buy some chicken", false);
//	private final ListItem Item_2 = new ListItem(2L, "Build PC", "Put together new components", true);
//	private final ListItem Item_3 = new ListItem(3L, "Complete TDL Project", "Still need to finish front end", false);
//	private final List<ListName> listOfLists = List.of(List_1, List_2, List_3);
//	private final List<ListItem> listOfItems = List.of(Item_1, Item_2, Item_3);

	private ListItemDto mapToDto(ListItem listItem) {
		return this.mapper.map(listItem, ListItemDto.class);
	}

	@Test
	void createTEST() throws Exception {
		ListItemDto ListItem_Test = mapToDto(new ListItem("Exercise", "Chest Day", false));
		ListItemDto ListItem_Test_Saved = mapToDto(new ListItem("Exercise", "Chest Day", false));
		String testListItemToJSON = this.jsonifier.writeValueAsString(ListItem_Test);
		ListItem_Test_Saved.setId(1L);
		String testListItemSavedToJSON = this.jsonifier.writeValueAsString(ListItem_Test_Saved);
		RequestBuilder rB = post(URI + "/create").contentType(MediaType.APPLICATION_JSON).content(testListItemToJSON);
		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().json(testListItemSavedToJSON);
		this.mvc.perform(rB).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void updateTEST() throws Exception {
		// still figuring out, left while i continue with other tests
	}

	@Test
	void readAllTEST() throws Exception {
		// still figuring out, left while i continue with other tests
	}

//	@Test
//	void readOneTEST() throws Exception {
//		RequestBuilder rB = get(URI + "/read/" + Item_1.getId()).accept(MediaType.APPLICATION_JSON);
//		ResultMatcher checkStatus = status().isOk();
//		this.mvc.perform(rB).andExpect(checkStatus)
//				.andExpect(content().json(this.jsonifier.writeValueAsString(this.mapToDto(Item_1))));	
//		}

	@Test
	void deleteTEST() throws Exception {
		this.mvc.perform(delete(URI + "/delete/" + Item_1.getId())).andExpect(status().isNoContent());
	}
}
