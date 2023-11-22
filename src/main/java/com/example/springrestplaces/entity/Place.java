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
    @NotNull(message = "User ID is required")
    private int userId;
    @NotBlank(message = "Description is required")
    @Size(max = 255, message = "Description should be at most 255 characters")
    private String description;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.PUBLIC;
    private LocalDateTime dateCreated;
    private LocalDateTime dateModified;
    @NotBlank(message = "points are required")
    private Point<G2D> coordinate;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    // constructors
    public Place(){}

    public Place(String name, int userId, Status status, LocalDateTime dateCreated, LocalDateTime dateModified, Point<G2D> coordinate, Category category) {
        this.name = name;
        this.userId = userId;
        this.status = status;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.coordinate = coordinate;
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


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public Point<G2D> getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Point<G2D> coordinate) {
        this.coordinate = coordinate;
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
                ", userId=" + userId +
                ", status=" + status +
                ", dateCreated=" + dateCreated +
                ", dateModified=" + dateModified +
                ", coordinate=" + coordinate +
                ", category=" + category +
                '}';
    }

    // Enums
    public enum Status {
        PUBLIC,
        PRIVATE
    }


}
