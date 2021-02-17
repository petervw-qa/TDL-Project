package com.qa.application.persistence.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ListNameDto {
	
	private Long id;
	private String name;
	
	List<ListItemDto> items = new ArrayList<>();

}

