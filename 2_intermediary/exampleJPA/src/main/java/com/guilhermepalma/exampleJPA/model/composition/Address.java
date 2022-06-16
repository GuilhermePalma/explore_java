package com.guilhermepalma.exampleJPA.model.composition;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String addres;

    private String complement;

    public Address(String addres) {
        this.addres = addres;
    }

    public Address(String addres, String complement) {
        this.addres = addres;
        this.complement = complement;
    }

    public Address() {

    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }
}
