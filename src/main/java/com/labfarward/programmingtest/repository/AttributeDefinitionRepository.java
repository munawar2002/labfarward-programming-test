package com.labfarward.programmingtest.repository;

import com.labfarward.programmingtest.model.AttributeDefinition;
import com.labfarward.programmingtest.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttributeDefinitionRepository extends JpaRepository<AttributeDefinition, Integer> {

    Optional<AttributeDefinition> findByNameAndCategoryAndActiveIsTrueAndDeletedIsFalse(String name, Category category);

}
