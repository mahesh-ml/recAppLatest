package org.abn.recipe.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.abn.recipe.config.RecipeMapper;
import org.abn.recipe.entity.Recipe;
import org.abn.recipe.model.RecipeDTO;
import org.abn.recipe.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class RecipeControllerTest {

    @MockBean
    @Autowired
    private RecipeService recipeService;

    @MockBean
    private RecipeMapper mockRecipeMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    void shouldCreateRecipe() throws Exception {
        RecipeDTO recipeDTO = buildRecipeDTO(true, Arrays.asList("rice","water","lemon"), "Boil for 15 minutes and serve",1);
        Recipe recipe = buildRecipe(true, Arrays.asList("rice","water","lemon"), "Boil for 15 minutes and serve",1);
        when(mockRecipeMapper.toRecipe(ArgumentMatchers.any())).thenReturn(recipe);
        when(recipeService.persistRecipe(recipe)).thenReturn(Optional.ofNullable(recipe));

        mockMvc.perform(post("/recipe").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(recipeDTO)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void shouldDeleteRecipe() throws Exception {
        Recipe recipe = buildRecipe(true, Arrays.asList("rice","water","lemon"), "Boil for 15 minutes and serve",1);

        long id = 1L;
        when(recipeService.findByRecipeId(any())).thenReturn(Optional.of(recipe));
        doNothing().when(recipeService).deleteRecipe(id);
        mockMvc.perform(delete("/recipe/{id}", id))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    void shouldUpdateRecipe() throws Exception {
        long id = 1L;

        Recipe recipe = buildRecipe(true, Arrays.asList("rice","water","lemon"), "Boil for 15 minutes and serve",1);

        RecipeDTO recipeDTOUpdated = buildRecipeDTO(false, Arrays.asList("chicken","fish","egg"), "cook 30 min and serve",3);
        Recipe recipeUpdated = buildRecipe(false, Arrays.asList("chicken","fish","egg"), "cook 30 min and serve",3);

        when(recipeService.findByRecipeId(any())).thenReturn(Optional.ofNullable(recipe));
        when(mockRecipeMapper.toRecipe(ArgumentMatchers.any())).thenReturn(recipe);
        when(recipeService.updateRecipe(any(),any())).thenReturn(recipeUpdated);
        when(mockRecipeMapper.toRecipeDto(ArgumentMatchers.any())).thenReturn(recipeDTOUpdated);

        mockMvc.perform(put("/recipe/{id}", id).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(recipeDTOUpdated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vegetarian").value(recipeDTOUpdated.getVegetarian()))
                .andExpect(jsonPath("$.cookingInstructions").value(recipeDTOUpdated.getCookingInstructions()))
                .andExpect(jsonPath("$.suitableFor").value(recipeDTOUpdated.getSuitableFor()))
                .andDo(print());
    }


    @Test
    void shouldReturnNotFoundUpdateRecipe() throws Exception {
        long id = 1L;

        RecipeDTO recipeDTOUpdated = buildRecipeDTO(false, Arrays.asList("chicken","fish","egg"), "cook 30 min and serve",3);

        when(recipeService.findByRecipeId(any())).thenReturn(Optional.empty());

        mockMvc.perform(put("/recipe/{id}", id).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(recipeDTOUpdated)))
                .andExpect(status().isNotFound())
                .andDo(print());
    }



    private  RecipeDTO buildRecipeDTO(Boolean vegetarian, List<String> ingredients, String cookingInstructions, Integer suitableFor) {
        RecipeDTO recipe = new RecipeDTO();
        recipe.setVegetarian(vegetarian);
        recipe.setIngredientList(ingredients);
        recipe.setCookingInstructions(cookingInstructions);
        recipe.setSuitableFor(suitableFor);
        recipe.setCreationDateTime(LocalDateTime.now());
        return recipe;
    }

    private  Recipe buildRecipe(Boolean vegetarian, List<String> ingredients, String cookingInstructions, Integer suitableFor) {
        Recipe recipe = new Recipe();
        recipe.setId(new Random().nextLong());
        recipe.setVegetarian(vegetarian);
        recipe.setIngredientList(ingredients);
        recipe.setCookingInstructions(cookingInstructions);
        recipe.setSuitableFor(suitableFor);
        recipe.setCreationDateTime(LocalDateTime.now());
        return recipe;
    }
}
