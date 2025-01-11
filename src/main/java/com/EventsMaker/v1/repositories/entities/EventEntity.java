package com.EventsMaker.v1.repositories.entities;

import java.math.BigDecimal;

public class EventEntity
{
	public final int id;
	public final String title;
	public final String description;
	public final String author;
	public final BigDecimal price;

	public EventEntity(int id, String title, String description, String author, BigDecimal price) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.author = author;
		this.price = price;
	}
}
