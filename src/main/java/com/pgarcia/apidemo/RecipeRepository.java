package com.pgarcia.apidemo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipes, String> {
	
}
