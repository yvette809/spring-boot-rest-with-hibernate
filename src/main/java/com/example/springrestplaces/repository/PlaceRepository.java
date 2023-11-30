package com.example.springrestplaces.repository;


import com.example.springrestplaces.entity.Category;
import com.example.springrestplaces.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place,Integer> {
    List<Place> findAllByStatus(Place.Status status);
    List<Place>findAllByStatusAndCategory_Name(Place.Status status, String categoryName);
    List<Place> findAllByUserUserId(String userId);
}
