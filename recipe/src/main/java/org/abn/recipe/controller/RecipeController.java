package org.abn.recipe.controller;

import org.abn.recipe.config.RecipeMapper;
import org.abn.recipe.entity.Recipe;
import org.abn.recipe.model.RecipeDTO;
import org.abn.recipe.service.RecipeService;
import org.abn.recipe.util.NoSuchRecipeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("recipe")
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @Autowired
    private RecipeMapper recipeMapper;

    @GetMapping
    public List<RecipeDTO> findAllRecipe(){
        return recipeService.findAllRecipe().
                 stream().map(recipe -> recipeMapper.toRecipeDto(recipe))
                .collect(Collectors.toList());

    }

    @PostMapping
    public ResponseEntity createRecipe(@RequestBody RecipeDTO recipeDto) throws URISyntaxException {
        Recipe recipe =  recipeMapper.toRecipe(recipeDto);
        var savedRecipe =  recipeService.persistRecipe(recipe);
        if(savedRecipe.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        RecipeDTO  responseDTO = recipeMapper.toRecipeDto(savedRecipe.get());
        return ResponseEntity.created(new URI("/recipe"+ savedRecipe.get().getId())).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateRecipe(@PathVariable Long id, @RequestBody RecipeDTO recipeDto) {
        var currentRecipe =  recipeService.findByRecipeId(id).orElseThrow(NoSuchRecipeException::new);
        var updatedRecipe =  recipeService.updateRecipe(currentRecipe,recipeMapper.toRecipe(recipeDto));
        return ResponseEntity.ok(recipeMapper.toRecipeDto(updatedRecipe));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteRecipe(@PathVariable Long id) {
        var currentRecipe =  recipeService.findByRecipeId(id).orElseThrow(NoSuchRecipeException::new);
        recipeService.deleteRecipe(currentRecipe.getId());
        return ResponseEntity.ok().build();
    }

}
