package com.labfarward.programmingtest.repository;

import com.labfarward.programmingtest.model.AttributeDefinition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeDefinitionRepository extends JpaRepository<AttributeDefinition, Integer> {
}
