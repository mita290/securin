package com.api.recipe.controller;

import java.util.List;

import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.recipe.model.Recipe;
import com.api.recipe.service.RecipeService;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
	private RecipeService rs;
	public RecipeController(RecipeService rs) {
		this.rs = rs;
	}
	
	@GetMapping("/import")
	public String importData() {
		return rs.parseData();
	}
	
	@GetMapping
	public List<Recipe> getAllNoPage() {
		return rs.getAllNP();
	}

	@GetMapping("/{limit}/{offset}")
	public List<Recipe> getAll(@PathVariable int limit, @PathVariable int offset) {
		return rs.getAll(limit, offset);
	}
	
	@GetMapping("/search/title")
	public List<Recipe> getTitle(@DefaultValue("") @RequestParam("title") String title) {
		return rs.getTitle(title);
	}	
	
	@GetMapping("/search/calories")
	public List<Recipe> getCal(@DefaultValue("") @RequestParam("calories") String calories) {
		return rs.getCalories(calories);
	}
	
	@GetMapping("/search/totaltime")
	public List<Recipe> getTotalTime(@RequestParam("time") int time) {
		return rs.getTotalTime(time);
	}
	
	@GetMapping("/search/rating")
	public List<Recipe> getRate(@RequestParam("rating") double rating) {
		return rs.getRate(rating);
	}
}


















