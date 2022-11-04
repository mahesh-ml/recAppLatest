package org.abn.recipe.config;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.abn.recipe.entity.Recipe;
import org.abn.recipe.model.RecipeDTO;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-04T17:21:12+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 18.0.2.1 (Oracle Corporation)"
)
@Component
public class RecipeMapperImpl implements RecipeMapper {

    @Override
    public RecipeDTO toRecipeDto(Recipe recipe) {
        if ( recipe == null ) {
            return null;
        }

        RecipeDTO recipeDTO = new RecipeDTO();

        recipeDTO.setId( recipe.getId() );
        recipeDTO.setVegetarian( recipe.getVegetarian() );
        recipeDTO.setSuitableFor( recipe.getSuitableFor() );
        recipeDTO.setCreationDateTime( recipe.getCreationDateTime() );
        List<String> list = recipe.getIngredientList();
        if ( list != null ) {
            recipeDTO.setIngredientList( new ArrayList<String>( list ) );
        }
        recipeDTO.setCookingInstructions( recipe.getCookingInstructions() );

        return recipeDTO;
    }

    @Override
    public List<RecipeDTO> toRecipeDtos(List<Recipe> recipes) {
        if ( recipes == null ) {
            return null;
        }

        List<RecipeDTO> list = new ArrayList<RecipeDTO>( recipes.size() );
        for ( Recipe recipe : recipes ) {
            list.add( toRecipeDto( recipe ) );
        }

        return list;
    }

    @Override
    public Recipe toRecipe(RecipeDTO recipeDTO) {
        if ( recipeDTO == null ) {
            return null;
        }

        Recipe recipe = new Recipe();

        recipe.setId( recipeDTO.getId() );
        recipe.setCreationDateTime( recipeDTO.getCreationDateTime() );
        recipe.setVegetarian( recipeDTO.getVegetarian() );
        recipe.setSuitableFor( recipeDTO.getSuitableFor() );
        List<String> list = recipeDTO.getIngredientList();
        if ( list != null ) {
            recipe.setIngredientList( new ArrayList<String>( list ) );
        }
        recipe.setCookingInstructions( recipeDTO.getCookingInstructions() );

        return recipe;
    }
}
