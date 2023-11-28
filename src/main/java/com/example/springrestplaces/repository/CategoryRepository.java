package com.example.springrestplaces.repository;

import com.example.springrestplaces.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Integer> {


    boolean existsByName(String CategoryName);
}
