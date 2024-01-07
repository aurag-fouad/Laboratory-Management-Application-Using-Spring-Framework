package com.example.labmanagement.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ressources")
public class Ressource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ressource_name")
    private String ressourceName;
    private String type;
    private int cost;

    public Ressource() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRessourceName() {
        return ressourceName;
    }

    public void setRessourceName(String ressourceName) {
        this.ressourceName = ressourceName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Ressource(String ressourceName, String type, int cost) {
        this.ressourceName = ressourceName;
        this.type = type;
        this.cost = cost;
    }
}
