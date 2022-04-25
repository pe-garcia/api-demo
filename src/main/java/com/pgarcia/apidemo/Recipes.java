package com.pgarcia.apidemo;
import java.util.Arrays;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Recipes {
//	private @Id @GeneratedValue Long id;
//	List<Recipes> recipes;
	@Id
	private String name;
public Recipes(String string) {
	this.name = string;
	}

	private String[] instructions;
    private String[] ingredients;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(ingredients);
		result = prime * result + Arrays.hashCode(instructions);
		result = prime * result + Objects.hash(name);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Recipes other = (Recipes) obj;
		return Arrays.equals(ingredients, other.ingredients) && Arrays.equals(instructions, other.instructions)
				&& Objects.equals(name, other.name);
	}
//	public Long getId() {
//		return id;
//	}
//	public void setId(Long id) {
//		this.id = id;
//	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getInstructions() {
		return instructions;
	}
	public void setInstructions(String[] instructions) {
		this.instructions = instructions;
	}
	public String[] getIngredients() {
		return ingredients;
	}
	public void setIngredients(String[] ingredients) {
		this.ingredients = ingredients;
	}
	public Recipes() {
		super();
	}
	@Override
	public String toString() {
		return "Recipes [name=" + name + ", instructions=" + Arrays.toString(instructions)
				+ ", ingredients=" + Arrays.toString(ingredients) + "]";
	}



}
