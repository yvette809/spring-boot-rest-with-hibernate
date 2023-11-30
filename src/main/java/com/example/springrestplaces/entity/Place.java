package com.example.springrestplaces.entity;

import com.example.springrestplaces.utils.Point2DSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name="place")
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Name is required")
    @Size(max = 50, message = "Name should be at most 50 characters")
    private String name;
    //private int categoryId;

   @JsonIgnore
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @NotBlank(message = "Description is required")
    @Size(max = 255, message = "Description should be at most 255 characters")
    private String description;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.PUBLIC;
    @Column(name="date_created")
    private LocalDateTime dateCreated;
    @Column(name="date_modified")
    private LocalDateTime dateModified;
    @JsonSerialize(using = Point2DSerializer.class)
    @NotBlank(message = "points are required")
    private Point<G2D> coordinates;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    // constructors
    public Place(){}

    public Place(int id, String name, User user, String description, Status status, LocalDateTime dateCreated, LocalDateTime dateModified, Point<G2D> coordinates, Category category) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.description = description;
        this.status = status;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.coordinates = coordinates;
        this.category = category;
    }

    // getters and setters


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


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getDateModified() {
        return dateModified;
    }

    public void setDateModified(LocalDateTime dateModified) {
        this.dateModified = dateModified;
    }

    public Point<G2D> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Point<G2D> coordinates) {
        this.coordinates = coordinates;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    // toString method


    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", user=" + user+
                ", status=" + status +
                ", dateCreated=" + dateCreated +
                ", dateModified=" + dateModified +
                ", coordinates=" + coordinates +
                ", category=" + category +
                '}';
    }

    //equals


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return Objects.equals(coordinates, place.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinates);
    }

    // Enums
    public enum Status {
        PUBLIC,
        PRIVATE
    }


}
