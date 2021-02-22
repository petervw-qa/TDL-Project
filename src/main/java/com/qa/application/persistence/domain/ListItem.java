package com.qa.application.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ListItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String name;
	@NotNull
	private String description;
	@NotNull
	private boolean completed;

	@ManyToOne
	private ListName listname = null;

	public ListItem(@NotNull String name, @NotNull String description, @NotNull boolean completed) {
		super();
		this.name = name;
		this.description = description;
		this.completed = completed;
	}

	public ListItem(Long id, @NotNull String name, @NotNull String description, @NotNull boolean completed) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.completed = completed;
	}

}
