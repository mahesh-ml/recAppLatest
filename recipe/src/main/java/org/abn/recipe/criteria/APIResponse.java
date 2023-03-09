package org.abn.recipe.criteria;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.abn.recipe.entity.Recipe;
import org.springframework.http.HttpStatus;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class APIResponse {

    private List<Recipe> data;
    private HttpStatus responseCode;
    private String message;

}
