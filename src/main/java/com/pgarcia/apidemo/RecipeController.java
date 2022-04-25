package com.pgarcia.apidemo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class RecipeController {

		private final RecipeRepository repo;
		
		RecipeController(RecipeRepository repo){
			this.repo = repo;
		}
		
		@GetMapping("/recipes")
		ResponseEntity<List<String>> all() {
				List<Recipes> l = repo.findAll();
				List<String> names = new LinkedList<>();
				for(Recipes r : l) {
					names.add(r.getName());
				}
				return new ResponseEntity<>(names,HttpStatus.OK);
		}
		
		 @PostMapping("/recipes")
		 ResponseEntity<String> newRecipes(@RequestBody Recipes newRecipes) {
				List<Recipes> l = repo.findAll();
				
				
				if(l.contains(newRecipes)) {
		
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}else {
					repo.save(newRecipes);
					JSONObject msg = new JSONObject();
					msg.put("error", ErrorMsg.getRecipeExists());
					return new ResponseEntity<>(msg.toString(),HttpStatus.OK);
				}
		  }
		 
		@GetMapping("/")
		public String index() {
			return "up and running";
		}
		 @GetMapping("/recipes/details/{name}")
		  public ResponseEntity<String> getIngredients(@PathVariable String name) {
				List<Recipes> l = repo.findAll();
				System.out.println("Name: " + name);
				int count = 0;
				for(Recipes r: l) {
					if(r.getName().equals(name)) {
						break;
					}
					count++;
				}
				Recipes ing = l.get(count);
				HashMap<String,Integer> map = new HashMap<>();
				JSONObject msg = new JSONObject();
				msg.put("ingredients", Arrays.toString(ing.getIngredients()));
				msg.put("steps",ing.getInstructions().length);
				map.put(Arrays.toString(ing.getIngredients()), ing.getIngredients().length);
		    return new ResponseEntity<>(msg.toString(),HttpStatus.OK);
		  }

		  @PutMapping("/recipes/{name}")
		  ResponseEntity<String> replaceRecipes(@RequestBody Recipes newRecipes, @PathVariable String name) {
		    
				List<Recipes> l = repo.findAll();
				if(l.contains(newRecipes)) {
					Recipes recipe = l.get(l.indexOf(newRecipes));
					recipe.setIngredients(newRecipes.getIngredients());
					recipe.setInstructions(newRecipes.getInstructions());
					recipe.setName(newRecipes.getName());
					repo.saveAndFlush(recipe);
					return new ResponseEntity<>(HttpStatus.OK);
				}else {
					JSONObject msg = new JSONObject();
					msg.put("error", ErrorMsg.getRecipeDoesNot());
					return new ResponseEntity<>(msg.toString(),HttpStatus.BAD_REQUEST);
				}
		  }
}
