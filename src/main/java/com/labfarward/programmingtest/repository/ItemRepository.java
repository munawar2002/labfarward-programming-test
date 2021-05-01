package com.labfarward.programmingtest.repository;

import com.labfarward.programmingtest.model.Category;
import com.labfarward.programmingtest.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    Optional<Item> findByNameAndCategoryAndActiveIsTrueAndDeletedIsFalse(String name, Category category);

    List<Item> findByCategoryAndActiveIsTrueAndDeletedIsFalse(Category category);
}
