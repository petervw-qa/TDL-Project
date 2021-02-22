package com.qa.application;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.application.persistence.domain.ListItem;
import com.qa.application.persistence.domain.ListName;

import nl.jqno.equalsverifier.EqualsVerifier;

@SpringBootTest
public class ToDoListApplicationTest {
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void equalsContract() {
		EqualsVerifier.forClass(ListName.class)
		.withPrefabValues(ListItem.class, new ListItem("Shopping", "Groceries", true), new ListItem("Exercise", "Push", true));
	}

}
