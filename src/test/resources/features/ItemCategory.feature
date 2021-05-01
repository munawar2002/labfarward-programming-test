Feature: Balance

  @CDC
  Scenario: Save new category
    Given url HOST
    Given path '/categories'
    Given request
    """
    {
      "attributeDefinitions" : [
      {
        "description": "name",
        "name": "name",
        "type": "string"
      },
      {
        "description": "expiry",
        "name": "expiry",
        "type": "date"
      }
    ],
    "description": "device7",
    "name": "device7"
    }
    """
    And header Accept = 'application/json'
    When method POST
    Then status 201
    And match response ==
    """
    {
      "name": "device7",
      "description": "device7",
      "active": true,
      "deleted": false,
      "attributeDefinitions": [
        {
          "name": "name",
          "description": "name",
          "type": "string",
          "active": true,
          "deleted": false
        },
        {
          "name": "expiry",
          "description": "expiry",
          "type": "date",
          "active": true,
          "deleted": false
        }
      ]
    }
    """

  @CDC
  Scenario: Null category name fails the validation
    Given url HOST
    Given path '/categories'
    Given request
    """
    {
      "attributeDefinitions" : [
      {
        "description": "name",
        "name": "name",
        "type": "string"
      },
      {
        "description": "expiry",
        "name": "expiry",
        "type": "date"
      }
    ],
    "description": "device7",
    "name": null
    }
    """
    And header Accept = 'application/json'
    When method POST
    Then status 400
    And match response ==
    """
    {
      "errorCode": "UP-2000",
      "errorMessage": "Category name can't be null"
    }
    """

  @CDC
  Scenario: Null attribute definition name fails the validation
    Given url HOST
    Given path '/categories'
    Given request
    """
    {
      "attributeDefinitions" : [
      {
        "description": "name",
        "name": null,
        "type": "string"
      },
      {
        "description": "expiry",
        "name": "expiry",
        "type": "date"
      }
    ],
    "description": "device8",
    "name": "device8"
    }
    """
    And header Accept = 'application/json'
    When method POST
    Then status 400
    And match response ==
    """
    {
      "errorCode": "UP-2001",
      "errorMessage": "Attribute Definition name can't be null"
    }
    """

  @CDC
  Scenario: Null attribute definition type fails the validation
    Given url HOST
    Given path '/categories'
    Given request
    """
    {
      "attributeDefinitions" : [
      {
        "description": "name",
        "name": "name",
        "type": null
      },
      {
        "description": "expiry",
        "name": "expiry",
        "type": "date"
      }
    ],
    "description": "device8",
    "name": "device8"
    }
    """
    And header Accept = 'application/json'
    When method POST
    Then status 400
    And match response ==
    """
    {
      "errorCode": "UP-2002",
      "errorMessage": "Attribute Definition type can't be null"
    }
    """

  @CDC
  Scenario: Save and update new item
    Given url HOST
    Given path '/categories'
    Given request
    """
    {
      "attributeDefinitions" : [
      {
        "description": "name",
        "name": "name",
        "type": "string"
      },
      {
        "description": "expiry",
        "name": "expiry",
        "type": "date"
      }
    ],
    "description": "Category2",
    "name": "Category2"
    }
    """
    And header Accept = 'application/json'
    When method POST
    Then status 201
    And match response ==
    """
    {
      "name": "Category2",
      "description": "Category2",
      "active": true,
      "deleted": false,
      "attributeDefinitions": [
        {
          "name": "name",
          "description": "name",
          "type": "string",
          "active": true,
          "deleted": false
        },
        {
          "name": "expiry",
          "description": "expiry",
          "type": "date",
          "active": true,
          "deleted": false
        }
      ]
    }
    """

    Given url HOST
    Given path '/categories/1/items'
    Given request
    """
    {
       "attributeValues": [
        {
          "attributeName": "name",
          "value": "Name1"
        },
        {
          "attributeName": "expiry",
          "value": "Today"
        }
      ],
    "description": "Item Description",
    "name": "Item Name"
    }
    """
    And header Accept = 'application/json'
    When method POST
    Then status 201
    And match response ==
    """
    {
      "name": "Item Name",
      "description": "Item Description",
      "active": true,
      "deleted": false,
      "categoryName": "device7",
      "attributeValues": [
        {
          "attributeName": "name",
          "value": "Name1"
        },
        {
          "attributeName": "expiry",
          "value": "Today"
        }
      ]
    }
    """

    Given url HOST
    Given path '/categories/1/items/1'
    Given request
    """
    {
       "attributeValues": [
        {
          "attributeName": "name",
          "value": "Name2"
        },
        {
          "attributeName": "expiry",
          "value": "Tomorrow"
        }
      ],
    "description": "Item Description",
    "name": "Item Name"
    }
    """
    And header Accept = 'application/json'
    When method PUT
    Then status 200
    And match response ==
    """
    {
      "name": "Item Name",
      "description": "Item Description",
      "active": true,
      "deleted": false,
      "categoryName": "device7",
      "attributeValues": [
        {
          "attributeName": "name",
          "value": "Name2"
        },
        {
          "attributeName": "expiry",
          "value": "Tomorrow"
        }
      ]
    }
    """

    Given url HOST
    Given path '/categories/1/items'
    And header Accept = 'application/json'
    When method GET
    Then status 200

  @CDC
  Scenario: Category not found while saving item
    Given url HOST
    Given path '/categories/100/items'
    Given request
    """
    {
       "attributeValues": [
        {
          "attributeName": "name",
          "value": "Name1"
        },
        {
          "attributeName": "expiry",
          "value": "Today"
        }
      ],
    "description": "Item Description",
    "name": "Item Name"
    }
    """
    And header Accept = 'application/json'
    When method POST
    Then status 404
    And match response ==
    """
    {
      "errorCode": "UP-2003",
      "errorMessage": "Category not found with provided categoryId as path param"
    }
    """