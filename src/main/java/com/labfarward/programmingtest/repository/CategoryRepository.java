package com.labfarward.programmingtest.repository;

import com.labfarward.programmingtest.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Optional<Category> findByNameAndActiveIsTrueAndDeletedIsFalse(String name);


}
