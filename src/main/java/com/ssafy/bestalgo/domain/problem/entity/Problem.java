package com.ssafy.bestalgo.domain.problem.entity;

import com.ssafy.bestalgo.domain.code.entity.Code;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(unique = true)
    private String name;

    @NotNull
    private String category;

    @OneToMany(mappedBy = "problem")
    private List<Code> codes;

    protected Problem() {
    }

    private Problem(String name, String category) {
        Problems.checkValidName(name);
        Problems.checkValidCategory(category);

        this.name = name;
        this.category = category;
    }

    public static Problem create(String name, String category) {
        return new Problem(name, category);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }
}
