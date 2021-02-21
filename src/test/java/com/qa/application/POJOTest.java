package com.qa.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.web.util.NestedServletException;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = { "classpath:application-schema.sql", "classpath:application-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class POJOTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Test
	public void testOnCascadeDelete() throws Exception{
		RequestBuilder requestDeleteList=delete("/listname/delete/1");
		this.mvc.perform(requestDeleteList);
		RequestBuilder requestReadItem=get("/listitem/read/1");
		NestedServletException e =assertThrows(NestedServletException.class, ()->{
			this.mvc.perform(requestReadItem);
		});
		assertThat(e.getCause().toString()).isEqualTo("java.util.NoSuchElementException: No value present");
	}

}
