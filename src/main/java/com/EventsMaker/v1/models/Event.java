package com.EventsMaker.v1.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class Event
{
	public final int id;
	public final String title;
	public final String description;
	public final String author;
	public final BigDecimal price;

	@JsonCreator
	public Event(@JsonProperty("id") int id,
				 @JsonProperty("title")String title,
				 @JsonProperty("description")String description,
				 @JsonProperty("author")String author,
				 @JsonProperty("price") BigDecimal price) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.author = author;
		this.price = price;
	}
}
