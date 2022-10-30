package org.abn.recipe.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Externalizable;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="recipe")
@Data
public class Recipe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @CreationTimestamp
    private LocalDateTime creationDateTime;

    private Boolean vegetarian;
    private Integer suitableFor;
    @ElementCollection(targetClass=String.class)
    private List<String> ingredientList;
    private String cookingInstructions;

}
