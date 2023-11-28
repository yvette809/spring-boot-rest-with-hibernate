package com.example.springrestplaces.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;

import java.time.LocalDateTime;

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
    @NotBlank(message = "points are required")
    private Point<G2D> coordinates;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    // constructors
    public Place(){}

    public Place(String name, User user, Status status, LocalDateTime dateCreated, LocalDateTime dateModified, Point<G2D> coordinate, Category category) {
        this.name = name;
        this.user = user;
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

    public void setUserId(User user) {
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

    // Enums
    public enum Status {
        PUBLIC,
        PRIVATE
    }


}
