package com.example.springrestplaces.controller;

import com.example.springrestplaces.entity.Category;
import com.example.springrestplaces.entity.Place;
import com.example.springrestplaces.entity.User;
import com.example.springrestplaces.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PlaceController {
    private PlaceService placeService;


    @Autowired
    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping("/places/public")
    ResponseEntity<List<Place>> getAllPublicPlaces(){
        List<Place> publicPlaces = placeService.getAllPublicPlaces();
        if(publicPlaces.isEmpty()){
            System.out.println("places: " + publicPlaces);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }


        return ResponseEntity.ok(publicPlaces);
    }




        @GetMapping("/places")
        public ResponseEntity<List<Place>> getAllPublicPlacesByCategory(@RequestParam String category) {

            List<Place> publicPlacesInCategory = placeService.getAllPublicPlacesInCategory(category);

            if (publicPlacesInCategory.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(publicPlacesInCategory, HttpStatus.OK);
            }
        }


    @GetMapping("/places/user")
    public ResponseEntity<List<Place>> getAllPlacesForUser(@AuthenticationPrincipal UserDetails userDetails){
        // get username of loggedin user
        String userId = userDetails.getUsername();
        List<Place> places = placeService.getALLPlacesForLoggedInUser(userId);
        return  ResponseEntity.ok(places);
    }

    @PostMapping("/places")
    public ResponseEntity<Place> createAPlace(Place place){

        Place newPlace = placeService.createPlace(place);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{placeId}")
    public ResponseEntity<Place> updatePlace(
            @PathVariable int placeId,
            @RequestBody Place updatedPlace,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        try {
            Place updatedPlaceResult = placeService.updatePlace(placeId, updatedPlace);
            return ResponseEntity.ok(updatedPlaceResult);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }


}



