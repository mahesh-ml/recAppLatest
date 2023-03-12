package org.abn.recipe.search;

import lombok.Data;

import java.util.List;

@Data
public class SearchDto {
    List<SearchCriteria> searchCriteria;
}
