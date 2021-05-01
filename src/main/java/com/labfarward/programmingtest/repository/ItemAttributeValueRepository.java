package com.labfarward.programmingtest.repository;

import com.labfarward.programmingtest.model.AttributeDefinition;
import com.labfarward.programmingtest.model.Item;
import com.labfarward.programmingtest.model.ItemAttributeValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemAttributeValueRepository extends JpaRepository<ItemAttributeValue, Integer> {

    Optional<ItemAttributeValue> findByItemAndAttributeDefinition(Item item, AttributeDefinition attributeDefinition);

    List<ItemAttributeValue> findByItem(Item item);

}
