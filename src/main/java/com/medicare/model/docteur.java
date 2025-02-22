package com.medicare.model;

public class docteur {
    private int id;
    private String name;
    private String prenom;

    public docteur(int id, String name, String prenom) {
        this.id = id;
        this.name = name;
        this.prenom = prenom;
    }

    public docteur(String name, String prenom) {
        this.name = name;
        this.prenom = prenom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
