package com.qa.application.rest;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

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
import com.qa.application.persistence.domain.ListName;
import com.qa.application.persistence.dto.ListNameDto;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
@Sql(scripts = { "classpath:application-schema.sql",
		"classpath:application-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class ListNameControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private ObjectMapper jsonifier;
	
	private final String URI = "/listname";
	private final ListName List_1 = new ListName("Peter's List");
//	private final ListName List_2 = new ListName("Mason's List");
//	private final ListName List_3 = new ListName("Artur's List");
	private final ListItem Item_1 = new ListItem("Shopping","Go to tesco",false);
	private final ListItem Item_2 = new ListItem("Build PC","Order new components",false);
	private final ListItem Item_3 = new ListItem("Exercise","Chest day!",false);
//	private final List<ListName> listOfLists = List.of(List_1, List_2, List_3);
	private final List<ListItem> listOfItems = List.of(Item_1, Item_2, Item_3);
	
	private ListNameDto mapToDto(ListName listName) {
		return this.mapper.map(listName, ListNameDto.class);
	}
	
	@Test
	void createTEST() throws Exception {
		ListNameDto ListName_Test = mapToDto(new ListName("Peter"));
		ListNameDto ListName_Test_Saved = mapToDto(new ListName("Peter"));
		String testListNameToJSON = this.jsonifier.writeValueAsString(ListName_Test);
		ListName_Test_Saved.setId(4L);
		String testListNameSavedtoJSON = this.jsonifier.writeValueAsString(ListName_Test_Saved);
		RequestBuilder rB = post(URI + "/create").contentType(MediaType.APPLICATION_JSON).content(testListNameToJSON);
		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = (ResultMatcher) content().json(testListNameSavedtoJSON);
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
	
	@Test
	void readOneTEST() throws Exception {
		List_1.setItems(listOfItems);
		RequestBuilder rB = get(URI + "/read/" + List_1.getId()).accept(MediaType.APPLICATION_JSON);
		ResultMatcher checkStatus = status().isOk();
		this.mvc.perform(rB).andExpect(checkStatus).andExpect((ResultMatcher) content().json(this.jsonifier.writeValueAsString(this.mapToDto(List_1))));
	}
	
	@Test
	void deleteTEST() throws Exception {
		this.mvc.perform(delete(URI + "/delete/" + List_1.getId())).andExpect(status().isGone());
	}
	
}
