package com.api.recipe.service;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.api.recipe.model.Recipe;
import com.api.recipe.repository.RecipeRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RecipeService {
	private RecipeRepository repo;
	public RecipeService(RecipeRepository repo) {
		this.repo = repo;
	}
	public String parseData() {
		try {
			String json = new String(Files.readAllBytes(Paths.get("F:\\cd\\recipe\\src\\main\\java\\com\\api\\recipe\\US_recipes_null.json")));
			ObjectMapper map = new ObjectMapper();
			JsonNode node = map.readTree(json);
			System.out.println("Node size: " + node.size());
			for (int i = 0; i < node.size(); i++) {
				String ind = Integer.toString(i);
				JsonNode obj = node.get(ind);
				System.out.println(obj.toString());

				Recipe rep = new Recipe();
				
				rep.setCuisine(obj.get("cuisine").asText());
				System.out.println("cuisine updated");
				
				rep.setTitle(obj.get("title").asText());
				System.out.println("title updated");
				
				if (obj.get("rating").isNull()) {
					rep.setRating(0);
					System.out.println("rating updated");
				} else {
					rep.setRating(obj.get("rating").asDouble());
					System.out.println("rating updated");
				}
				
				if (obj.get("prep_time").isNull()) {
					rep.setRating(0);
					System.out.println("prep_time updated");
				} else {
					rep.setPrepTime(obj.get("prep_time").asInt());
					System.out.println("prep_time updated");
				}
				
				if (obj.get("total_time").isNull()) {
					rep.setRating(0);
					System.out.println("total_time updated");
				} else {
					rep.setTotalTime(obj.get("total_time").asInt());
					System.out.println("total_time updated");
				}
				
				if (obj.get("cook_time").isNull()) {
					rep.setRating(0);
					System.out.println("cook_time updated");
				} else {
					rep.setCookTime(obj.get("cook_time").asInt());
					System.out.println("cook_time updated");
				}							
				
				rep.setDescription(obj.get("description").asText());
				System.out.println("description updated");
				
				JsonNode jn = obj.get("nutrients");		
				rep.setNutrients(jn.toString());
				System.out.println("nutrients updated");
				rep.setServes(obj.get("serves").asText());
				System.out.println("serves updated");
				repo.save(rep);
			}
			
			return "Data Saved Successfully!";			
		} catch (Exception e) {
			e.printStackTrace();
			return "Data not saved";
		}		
	}
	public List<Recipe> getAllNP() {
		return repo.findAll();
	}
	public List<Recipe> getAll(int limit, int offset) {
		Pageable pg = PageRequest.of(limit, offset, Sort.by("rating").descending());
		return repo.findAll(pg).toList();
	}
	
	public List<Recipe> getTitle(String title) {
		return repo.findByTitle(title);
	}
	
	public List<Recipe> getCuisine(String cuisine) {
		return repo.findByCuisine(cuisine);
	}
	
	public List<Recipe> getCalories(String cal) {
		List<Recipe> al = repo.findAll();
		List<Recipe> res = new ArrayList<>();
		for (Recipe r : al) {
			String t = r.getNutrients();
			if (t.contains(cal)) {
				res.add(r);
			}
		}
		return res;
	}
	
	public List<Recipe> getTotalTime(int time) {
		return repo.findAll().stream().filter(r -> r.getTotalTime() >= time).toList();
	}
	
	public List<Recipe> getRate(double rate) {
		return repo.findAll().stream().filter(r -> r.getRating() >= rate).toList();
	}
	
}
