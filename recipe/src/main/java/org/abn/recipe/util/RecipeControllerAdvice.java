package org.abn.recipe.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RecipeControllerAdvice {

    @ExceptionHandler(value = NoSuchRecipeException.class)
    public ResponseEntity<Object> exception(NoSuchRecipeException exception) {
        return new ResponseEntity<>(" No such Recipe  found , Please verify the request details and try again ", HttpStatus.NOT_FOUND);
    }
}
