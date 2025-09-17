package com.api.recipe.model;

//import com.fasterxml.jackson.databind.JsonNode;

import jakarta.persistence.*;

@Entity
@Table
public class Recipe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "recipe_id")
	private int recipeId;
	@Column(name = "cuisine")
	private String cuisine;
	@Column(name = "title")
	private String title;
	@Column(name = "rating")
	private double rating;
	@Column(name = "prep_time")
	private int prepTime;
	@Column(name = "cook_time")
	private int cookTime;
	@Column(name = "total_time")
	private int totalTime;
	@Column(name = "description", columnDefinition = "text")
	private String description;
	@Column(name = "nutrients", columnDefinition = "text")
	private String nutrients;
	@Column(name = "serves")
	private String serves;

	public String getCuisine() {
		return cuisine;
	}

	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public int getPrepTime() {
		return prepTime;
	}

	public void setPrepTime(int prepTime) {
		this.prepTime = prepTime;
	}

	public int getCookTime() {
		return cookTime;
	}

	public void setCookTime(int cookTime) {
		this.cookTime = cookTime;
	}

	public int getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNutrients() {
		return nutrients;
	}

	public void setNutrients(String nutrients) {
		this.nutrients = nutrients;
	}

	public String getServes() {
		return serves;
	}

	public void setServes(String serves) {
		this.serves = serves;
	}	
}


