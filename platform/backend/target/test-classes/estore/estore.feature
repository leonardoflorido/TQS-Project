Feature: eStore

  Scenario: eStore Workflow
    Given I am on the eStore home page
    And I want to buy a "MacBook Pro 14"
    And I want to buy a "iPhone 14 Pro"
    And I want to buy a "AirPods"
    Then I want to checkout
    Then I want to enter "Leonardo" in the "First Name" field
    Then I want to enter "Flórido" in the "Last Name" field
    Then I want to enter "leonardo@email.com" in the "Email" field
    Then I want to enter "987654321" in the "Phone" field
    Then I want to enter "Rua da Pega" in the "Address" field
    Then I want to enter "Aveiro" in the "City" field
    Then I want to enter "Aveiro" in the "State" field
    Then I want to enter "3810-123" in the "Zip Code" field
    Then I submit the personal information
    Then I pick the pickup closest to me
    Then I want to enter "Leonardo Flórido" in the "Card Name" field
    Then I want to enter "2345124356787" in the "Card Number" field
    Then I want to enter "423" in the "CVV" field
    Then I want to enter "10/24" in the "Expiration Date" field
    When I submit the payment information
    Then I receive a confirmation message
    Then I want to see my orders

