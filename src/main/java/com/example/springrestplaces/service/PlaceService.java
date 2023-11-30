package com.example.springrestplaces.service;


import com.example.springrestplaces.entity.Category;
import com.example.springrestplaces.entity.Place;
import com.example.springrestplaces.entity.User;
import com.example.springrestplaces.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlaceService {
    private PlaceRepository placeRepository;

    @Autowired
    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public List<Place> getAllPublicPlaces(){
        return placeRepository.findAllByStatus(Place.Status.PUBLIC);

    }
    public List<Place> getAllPublicPlacesInCategory(String categoryName) {
        return placeRepository.findAllByStatusAndCategory_Name(Place.Status.PUBLIC, categoryName);
    }


    //method2
    public List<Place> getAllPublicPlacesAndCategory(Category category){
        List<Place> publicPlacesInCategory = placeRepository.findAllByStatus(Place.Status.PUBLIC)
                .stream()
                .filter(place-> place.getCategory().getName().equals(category.getName()))
                .toList();
        return publicPlacesInCategory;
    }

    public List<Place> getALLPlacesForLoggedInUser(String userId){
        return placeRepository.findAllByUserUserId(userId);
    }

    public Place createPlace( Place placeRequest){
        if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            throw new RuntimeException("User must be logged in to create a place");
        }
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // create a new place entity
        Place newPlace = new Place();
        newPlace.setName(placeRequest.getName());
        newPlace.setDescription(placeRequest.getDescription());
        newPlace.setStatus(placeRequest.getStatus());
        newPlace.setUser(currentUser);
        newPlace.setCategory(placeRequest.getCategory());
        newPlace.setDateCreated(LocalDateTime.now()); // Set the current date/time

        // Assuming placeRequest contains the coordinates
        newPlace.setCoordinates(placeRequest.getCoordinates());
        return placeRepository.save(newPlace);
    }

    public Place updatePlace(Integer placeId,Place place) {
        // Check if user is logged in
        if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            throw new RuntimeException("User must be logged in to update a place");
        }

        // Check if place exists and belongs to the logged-in user
        Optional<Place> existingPlace = placeRepository.findById(placeId);
        if (!existingPlace.isPresent() || !existingPlace.get().getUser().getUserId().equals(getCurrentUserId())) {
            throw new RuntimeException("Place not found or does not belong to loggedInUser");
        }

        // Update place details
        existingPlace.get().setName(place.getName());
        existingPlace.get().setDescription(place.getDescription());
        existingPlace.get().setCategory(place.getCategory());

        // Save updated place to the database
        return placeRepository.save(existingPlace.get());
    }

    private String getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            User currentUser = (User) authentication.getPrincipal();
            return currentUser.getUserId();
        } else {
            return null;
        }
    }

    public void deletePlace(Integer id){
        // user must be logged in or be ad admin
        if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            throw new RuntimeException("User must be logged in to update a place");
        }

        // check if place was created by loggedIn user

        Optional<Place> place = placeRepository.findById(id);
        if (!place.isPresent() || !place.get().getUser().getUserId().equals(getCurrentUserId())) {
            throw new RuntimeException("Place not found or does not belong to loggedInUser");
        }


         placeRepository.delete(place.get());
        System.out.println("Place with id:" + id + "deleted");

    }
}

