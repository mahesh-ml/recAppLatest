package org.abn.recipe.service;

import lombok.extern.slf4j.Slf4j;
import org.abn.recipe.entity.Recipe;
import org.abn.recipe.repo.RecipeRepository;
import org.abn.recipe.search.RecipeSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RecipeService {
    private RecipeRepository recipeRepository;

    public Page<Recipe> findBySearchCriteria
            (Specification<Recipe> spec, Pageable page){
        Page<Recipe> searchResult = recipeRepository.findAll(spec,
                page);
        return searchResult;
    }


    public RecipeService(RecipeRepository recipeRepository){
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> findAllRecipe(){
        return recipeRepository.findAll();
    }

    public List<Recipe> findAllBySpec(RecipeSpecification recipeSpecification){
        return recipeRepository.findAll(recipeSpecification);
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
