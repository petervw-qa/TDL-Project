package com.qa.application.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ListItemsDto {
	
	private Long id;
	private String name;
	private String description;
	private boolean completed;

}
