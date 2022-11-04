package org.abn.recipe.service;

import lombok.extern.slf4j.Slf4j;
import org.abn.recipe.entity.Recipe;
import org.abn.recipe.repo.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RecipeService {
    private RecipeRepository recipeRepository;


    public RecipeService(RecipeRepository recipeRepository){
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> findAllRecipe(){
        return recipeRepository.findAll();
    }

    public Optional<Recipe> findByRecipeId(Long recipeId){
        return recipeRepository.findById(recipeId);
    }

    public Optional<Recipe> persistRecipe(Recipe recipe) {
        Optional<Recipe> savedRecipe =  Optional.ofNullable(recipeRepository.save(recipe));
        if(savedRecipe.isEmpty()){
            log.error("Recipe could not be saved , "+ recipe);
        }
        return savedRecipe;
    }

    public Recipe updateRecipe(Recipe foundRecipe, Recipe recipeInp){
        foundRecipe.setVegetarian(recipeInp.getVegetarian());
        foundRecipe.setCookingInstructions(recipeInp.getCookingInstructions());
        foundRecipe.setSuitableFor(recipeInp.getSuitableFor());
        foundRecipe.setIngredientList(recipeInp.getIngredientList());
        return recipeRepository.save(foundRecipe);

    }

    public void deleteRecipe(Long id){
        recipeRepository.deleteById(id);
    }
}