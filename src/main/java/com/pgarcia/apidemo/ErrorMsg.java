package com.pgarcia.apidemo;

public class ErrorMsg {
	private static final String RECIPE_EXISTS = "Recipe already exists";
	private static final String RECIPE_DOES_NOT = "Recipe does not exist";
	public static String getRecipeExists() {
		return RECIPE_EXISTS;
	}
	public static String getRecipeDoesNot() {
		return RECIPE_DOES_NOT;
	}

}
