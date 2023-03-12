package org.abn.recipe.search;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SearchCriteria {

    private String key;
    private Object value;
    private SearchOperation operation;
}
