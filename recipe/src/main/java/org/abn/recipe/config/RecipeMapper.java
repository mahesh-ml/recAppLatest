package org.abn.recipe.config;

import org.abn.recipe.entity.Recipe;
import org.abn.recipe.model.RecipeDTO;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface RecipeMapper {

    RecipeDTO toRecipeDto(Recipe recipe);

    List<RecipeDTO> toRecipeDtos(List<Recipe> recipes);

    Recipe toRecipe(RecipeDTO recipeDTO);
}
