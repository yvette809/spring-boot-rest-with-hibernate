package com.example.springrestplaces.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Other fields

    @OneToMany(mappedBy = "user")
    private List<Place> places;

    // Constructors
    public User(){}

    public User(List<Place> places) {
        this.places = places;
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    // toString method


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", places=" + places +
                '}';
    }
}

