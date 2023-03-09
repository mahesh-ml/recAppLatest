package org.abn.recipe.criteria;

import org.abn.recipe.entity.Recipe;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Objects;

public class SearchCriteriaSpec implements Specification<Recipe> {
    private SearchCriteria searchCriteria;

    public SearchCriteriaSpec(final SearchCriteria
                                      searchCriteria) {
        super();
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<Recipe> root,
                                 CriteriaQuery<?> query, CriteriaBuilder cb) {
        String strToSearch = searchCriteria.getValue().toString().toLowerCase();

        switch (Objects.requireNonNull(
                SearchOperation.getSimpleOperation
                        (searchCriteria.getOperation()))) {
            case CONTAINS:
                if (searchCriteria.getFilterKey().equals("vegetarian")) {
                    return cb.like(cb.lower(root.
                                    get(searchCriteria.getFilterKey())),
                            "%" + strToSearch + "%");
                }
                return cb.like(cb.lower(root
                                .get(searchCriteria.getFilterKey())),
                        "%" + strToSearch + "%");

            case DOES_NOT_CONTAIN:
                if (searchCriteria.getFilterKey().equals("vegetarian")) {
                    return cb.notLike(cb.lower(root.get(searchCriteria.getFilterKey())),
                            "%" + strToSearch + "%");
                }
                return cb.notLike(cb.lower(root
                                .get(searchCriteria.getFilterKey())),
                        "%" + strToSearch + "%");

        }

        return null;
    }
}
