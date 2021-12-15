package com.example.Ejercicio2Sesion101112.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Laptop {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String company;
    private String model;
    private Integer memory;
    private String operatingSistem;

    // contructores

    public Laptop() {
    }

    public Laptop(Long id, String company, String model, Integer memory, String operatingSistem) {
        this.id = id;
        this.company = company;
        this.model = model;
        this.memory = memory;
        this.operatingSistem = operatingSistem;
    }
    // Getter y setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getMemory() {
        return memory;
    }

    public void setMemory(Integer memory) {
        this.memory = memory;
    }

    public String getOperatingSistem() {
        return operatingSistem;
    }

    public void setOperatingSistem(String operatingSistem) {
        this.operatingSistem = operatingSistem;
    }
}
