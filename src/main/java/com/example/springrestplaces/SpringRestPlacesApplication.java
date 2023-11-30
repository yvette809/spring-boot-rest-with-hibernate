package com.example.springrestplaces;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.geolatte.geom.G2D;
import org.geolatte.geom.crs.CoordinateReferenceSystem;
import org.geolatte.geom.json.GeolatteGeomModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;



@SpringBootApplication
public class SpringRestPlacesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringRestPlacesApplication.class, args);
    }




}
