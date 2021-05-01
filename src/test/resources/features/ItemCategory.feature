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