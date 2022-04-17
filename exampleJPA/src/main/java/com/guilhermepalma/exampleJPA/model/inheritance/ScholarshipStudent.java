package com.guilhermepalma.exampleJPA.model.inheritance;

import javax.persistence.Entity;

@Entity
public class ScholarshipStudent extends Student {

    private Double valueScholarship;

    public ScholarshipStudent() {
    }

    public ScholarshipStudent(Long id, String name, Double valueScholarship) {
        super(id, name);
        this.valueScholarship = valueScholarship;
    }

    public Double getValueScholarship() {
        return valueScholarship;
    }

    public void setValueScholarship(Double valueScholarship) {
        this.valueScholarship = valueScholarship;
    }
}
