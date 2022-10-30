package org.abn.recipe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class RecipeDTO {
    @JsonIgnore
    private Long id;
    private Boolean vegetarian;
    private Integer suitableFor;
    private LocalDateTime creationDateTime;
    private List<String> ingredientList;
    private String cookingInstructions;
}
