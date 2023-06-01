Feature: Pickup Registration

  Scenario: Register a pickup
    Given the user is on the login page
    And the user wants to register a pickup
    When the user enters "Tabacaria" in the "Name" field
    When the user enters "tabacaria@email.com" in the "Email" field
    When the user enters "987654321" in the "Phone" field
    When the user enters "tabacaria" in the "Password" field
    When the user enters "Rua do Tabaco, 3456-123, Aveiro" in the "Address" field
    When the user clicks on the register button
    Then the user should see the "SuccessPlease Login" message
