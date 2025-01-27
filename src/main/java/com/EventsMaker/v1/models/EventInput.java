package com.EventsMaker.v1.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class EventInput
{
	public final int id;
	public final String title;
	public final String description;
	public final BigDecimal price;

	@JsonCreator
	public EventInput(@JsonProperty("id") int id,
					@JsonProperty("title")String title,
					@JsonProperty("description")String description,
					@JsonProperty("price") BigDecimal price) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.price = price;
	}
}
