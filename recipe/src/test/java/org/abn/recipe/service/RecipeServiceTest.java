package org.abn.recipe.service;

import org.abn.recipe.entity.Recipe;
import org.abn.recipe.repo.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class RecipeServiceTest {

    @Mock
    private RecipeRepository recipeRepository;

    RecipeService recipeService;

    @BeforeEach
    void initUseCase() {
        recipeService = new RecipeService(recipeRepository);
    }

    @Test
    public void savedRecipe_Success() {
        Recipe  recipe = new Recipe();
        recipe.setVegetarian(true);
        recipe.setIngredientList(Arrays.asList("rice","water","lemon"));
        recipe.setCookingInstructions("Boil for 15 minutes and serve");
        recipe.setSuitableFor(1);
        recipe.setCreationDateTime(LocalDateTime.now());

        when(recipeRepository.save(any(Recipe.class))).thenReturn(recipe);

        var savedRecipe = recipeService.persistRecipe(recipe);
        assertThat(savedRecipe.get().getIngredientList()).isNotNull();
        assertThat(savedRecipe.get().getVegetarian()).isTrue();
        assertThat(savedRecipe.get().getCreationDateTime()).isNotNull();

    }

    @Test
    public void recipe_search_found_success() {
        Recipe recipe = buildRecipe(true, Arrays.asList("rice","water","lemon"), "Boil for 15 minutes and serve",1);
        Recipe recipe1 = buildRecipe(false, Arrays.asList("chicken","egg","rice"), "Pressure cook after mixing all ingredients",2);

        List<Recipe> recipeList = new ArrayList<>();
        recipeList.add(recipe);
        recipeList.add(recipe1);

        // providing knowledge
        when(recipeRepository.findAll()).thenReturn(recipeList);

        var fetchedRecipe = recipeService.findAllRecipe();
        assertThat(fetchedRecipe.size()).isGreaterThan(1);
    }

    @Test
    public void recipe_search_not_found() {
            var fetchedRecipe = recipeService.findByRecipeId(182L);
            assertThat(fetchedRecipe.isEmpty());

    }
    private  Recipe buildRecipe(Boolean vegetarian, List<String> ingredients, String cookingInstructions, Integer suitableFor) {
        Recipe recipe = new Recipe();
        recipe.setVegetarian(vegetarian);
        recipe.setIngredientList(ingredients);
        recipe.setCookingInstructions(cookingInstructions);
        recipe.setSuitableFor(suitableFor);
        recipe.setCreationDateTime(LocalDateTime.now());
        return recipe;
    }


}
