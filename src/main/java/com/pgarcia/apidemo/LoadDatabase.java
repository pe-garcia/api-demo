package com.pgarcia.apidemo;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class LoadDatabase {
	 private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	  @Bean
	  CommandLineRunner initDatabase(RecipeRepository repository) {

	    return args -> {
	    	
			System.out.println("-------------------------------------------------------------------");
	        //create ObjectMapper instance
	        ObjectMapper objectMapper = new ObjectMapper();

	        //read json file and convert to recipe object
	        DataFile data= objectMapper.readValue(new File("C:\\Users\\Pedga\\OneDrive\\Documents\\eclipse-workspace\\apidemo\\src\\main\\resources\\data.json"), DataFile.class);
	        
	        Recipes[] list = data.getRecipes();
	        for(Recipes r : list) {
		        log.info("Preloading " + repository.save(r));

	        }


	    };
	  }
}
