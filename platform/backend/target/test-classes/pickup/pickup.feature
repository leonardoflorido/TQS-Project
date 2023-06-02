Feature: Pickup

  Scenario: Pickup Workflow
    Given the pickup is on the login page
    Then the pickup enters "papelaria@email.com" in the "Email" field
    Then the pickup enters "papelaria" in the "Password" field
    Then the pickup clicks on the login button
    Then the pickup change the status of the order to "In Transit"
    Then the pickup clicks on the logout button
